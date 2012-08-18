package com.xxxman.voice.activity;

import java.util.ArrayList;
import java.util.List;

import com.xxxman.voice.adapter.TitleBarAdapter;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TitlesFragment extends ListFragment {
    private MainActivity myActivity = null;
	int mCurCheckPosition = 0;

    public void onAttach(Activity myActivity) {
        super.onAttach(myActivity);
        this.myActivity = (MainActivity) myActivity;
    }
    
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
        List<String> stringList = new ArrayList<String>();
        stringList.add("相声");
        stringList.add("评书");
        stringList.add("小说");
        setListAdapter(new TitleBarAdapter(getActivity(),stringList));
        ListView lv = getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setSelection(mCurCheckPosition);
// 
//        myActivity.showDetails(mCurCheckPosition);
    }
 
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("curChoice", mCurCheckPosition);
    }
 
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
//        myActivity.showDetails(pos);
        mCurCheckPosition = pos;
    }
}
