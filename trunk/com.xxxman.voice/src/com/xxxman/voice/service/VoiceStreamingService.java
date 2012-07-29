package com.xxxman.voice.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public  class VoiceStreamingService extends Service{

    private static final int INTIAL_KB_BUFFER =  96*10/8;
    private int totalKbRead = 0;
    private final Handler handler = new Handler();
    private MediaPlayer     mediaPlayer;
    private File downloadingMediaFile; 
    private boolean isInterrupted;
    private Context context;
    private int counter = 0;
    private static Runnable r;
    private static Thread playerThread;
    private LocalBinder localBinder = new LocalBinder();
    
	public VoiceStreamingService(Context  context) {
	     this.context = context;
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return localBinder;
	}
    public class LocalBinder extends Binder {  
        public VoiceStreamingService getService() {  
            return VoiceStreamingService.this; 
        }  
    }  
	    
    public void startStreaming(final String mediaUrl) throws IOException {    
        r = new Runnable() {   
            public void run() {   
                try {
                    Log.i("downloadAudioIncrement", "downloadAudioIncrement");
                    downloadAudioIncrement(mediaUrl);
                } catch (IOException e) {
                    Log.e(getClass().getName(), "Unable to initialize the MediaPlayer for fileUrl=" + mediaUrl, e);
                    return;
                }   
            }   
        }; 
        playerThread = new Thread(r);
        playerThread.start();
    }

    public void downloadAudioIncrement(String mediaUrl) throws IOException {
        
        URLConnection cn = new URL(mediaUrl).openConnection();   
        cn.addRequestProperty("User-Agent","NSPlayer/10.0.0.4072 WMFSDK/10.0");
        cn.connect();   
        InputStream stream = cn.getInputStream();
        if (stream == null) {
            Log.e(getClass().getName(), "Unable to create InputStream for mediaUrl:" + mediaUrl);
        }    
        downloadingMediaFile = new File(context.getCacheDir(),"downloadingMedia.dat");
        
        if (downloadingMediaFile.exists()) {
            downloadingMediaFile.delete();
        }

        FileOutputStream out = new FileOutputStream(downloadingMediaFile);   
        byte buf[] = new byte[16384];
        int totalBytesRead = 0, incrementalBytesRead = 0;
        do {
            int numread = stream.read(buf);   
            if (numread <= 0)   
                break;   
            out.write(buf, 0, numread);
            totalBytesRead += numread;
            incrementalBytesRead += numread;
            totalKbRead = totalBytesRead/1000;
            
            testMediaBuffer();
        } while (validateNotInterrupted());   
        stream.close();
    }  

    private boolean validateNotInterrupted() {
        if (isInterrupted) {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
            return false;
        } else {
            return true;
        }
    }
    
    private void  testMediaBuffer() {
        Runnable updater = new Runnable() {
            public void run() {
                if (mediaPlayer == null) {
                    if ( totalKbRead >= INTIAL_KB_BUFFER) {
                        try {
                            startMediaPlayer();
                        } catch (Exception e) {
                            Log.e(getClass().getName(), "Error copying buffered conent.", e);                
                        }
                    }
                } else if ( mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition() <= 1000 ){ 
                    transferBufferToMediaPlayer();
                }
            }
        };
        handler.post(updater);
    }
    
    private void startMediaPlayer() {
        try {   
            File bufferedFile = new File(context.getCacheDir(),"playingMedia" + (counter++) + ".dat"); 
            
            moveFile(downloadingMediaFile,bufferedFile);
            
            Log.e(getClass().getName(),"Buffered File path: " + bufferedFile.getAbsolutePath());
            Log.e(getClass().getName(),"Buffered File length: " + bufferedFile.length()+"");
            
            mediaPlayer = createMediaPlayer(bufferedFile);
            mediaPlayer.start();         
        } catch (IOException e) {
            Log.e(getClass().getName(), "Error initializing the MediaPlayer.", e);
            return;
        }   
    }
    private MediaPlayer createMediaPlayer(File mediaFile)throws IOException {
        MediaPlayer mPlayer = new MediaPlayer();
        mPlayer.setOnErrorListener(
                new MediaPlayer.OnErrorListener() {
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Log.e(getClass().getName(), "Error in MediaPlayer: (" + what +") with extra (" +extra +")" );
                        return false;
                    }
                });

        FileInputStream fis = new FileInputStream(mediaFile);
        mPlayer.setDataSource(fis.getFD());
        mPlayer.prepare();
        return mPlayer;
    }

    private void transferBufferToMediaPlayer() {
        try {
            boolean wasPlaying = mediaPlayer.isPlaying();
            int curPosition = mediaPlayer.getCurrentPosition();
            
            File oldBufferedFile = new File(context.getCacheDir(),"playingMedia" + counter + ".dat");
            File bufferedFile = new File(context.getCacheDir(),"playingMedia" + (counter++) + ".dat");

            bufferedFile.deleteOnExit();   
            moveFile(downloadingMediaFile,bufferedFile);
            mediaPlayer.pause();
            mediaPlayer = createMediaPlayer(bufferedFile);
            mediaPlayer.seekTo(curPosition);
            boolean atEndOfFile = mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition() <= 1000;
            if (wasPlaying || atEndOfFile){
                mediaPlayer.start();
            }
            oldBufferedFile.delete();
            
        }catch (Exception e) {
            Log.e(getClass().getName(), "Error updating to newly loaded content.", e);                    
        }
    }
    public void moveFile(File    oldLocation, File    newLocation) throws IOException {

        if ( oldLocation.exists()) {
            BufferedInputStream  reader = new BufferedInputStream( new FileInputStream(oldLocation) );
            BufferedOutputStream  writer = new BufferedOutputStream( new FileOutputStream(newLocation, false));
            try {
                byte[]  buff = new byte[8192];
                int numChars;
                while ( (numChars = reader.read(  buff, 0, buff.length ) ) != -1) {
                    writer.write( buff, 0, numChars );
                  }
            } catch( IOException ex ) {
                throw new IOException("IOException when transferring " + oldLocation.getPath() + " to " + newLocation.getPath());
            } finally {
                try {
                    if ( reader != null ){                        
                        writer.close();
                        reader.close();
                    }
                } catch( IOException ex ){
                    Log.e(getClass().getName(),"Error closing files when transferring " + oldLocation.getPath() + " to " + newLocation.getPath() ); 
                }
            }
        } else {
            throw new IOException("Old location does not exist when transferring " + oldLocation.getPath() + " to " + newLocation.getPath() );
        }
    }
}
