function createTable(results)
{ 
	var table=$("<table border=\"1\"></table");
	table.appendTo($("#main"));
	var tr=$("<tr></tr>");
	tr.appendTo(table);

	var th=$("<th>编号</th>");
	th.appendTo(tr);	
	var th=$("<th>标题</th>");
	th.appendTo(tr);
	var th=$("<th>类型</th>");
	th.appendTo(tr);
	var th=$("<th>uri</th>");
	th.appendTo(tr);
	var th=$("<th>点击数</th>");
	th.appendTo(tr);	
	var th=$("<th>编辑</th>");
	th.appendTo(tr);				
	for(var i=0;i<results.length;i++)
	{
		var tr=$("<tr></tr>");
		tr.appendTo(table);

		var td=$("<td>"+i+"</td>");
		td.appendTo(tr);	

		var td=$("<td></td>");
		var input = $("<input size='30' type='text' value='"+results[i].get("title")+"' id='input_title_"+i+"' />")
		input.appendTo(td);
		td.appendTo(tr);

		var td=$("<td></td>");
		var input = $("<input size='10' type='text' value='"+results[i].get("type")+"' id='input_type_"+i+"' />")
		input.appendTo(td);
		td.appendTo(tr);

		var td=$("<td></td>");
		var input = $("<input size='100' type='text' value='"+results[i].get("uri")+"' id='input_uri_"+i+"' />")
		input.appendTo(td);
		td.appendTo(tr);

		var td=$("<td></td>");
		var input = $("<input size='6' type='text' value='"+results[i].get("checkedCount")+"' id='input_checkedCount_"+i+"' />")
		input.appendTo(td);
		td.appendTo(tr);
		
		var td=$("<td></td>");
		var input = $("<input type='button' value='添加' onclick='addVoiceObject("+i+")'/>")
		input.appendTo(td);
		var input = $("<input type='button' value='修改' onclick='updateVoiceObject("+i+")'/>")
		input.appendTo(td);
		var input = $("<input type='button' value='删除' onclick='delVoiceObject("+i+")'/>")
		input.appendTo(td);
		td.appendTo(tr);
	}
}
function addVoiceObject(i){
	var voiceObject = new VoiceObject();	
	var title=$("#input_title_"+i).val();
	var type=$("#input_type_"+i).val();
	var uri=$("#input_uri_"+i).val();
	var checkedCount=$("#input_checkedCount_"+i).val();	
	voiceObject.set("title",title);
	voiceObject.set("type",type);
	voiceObject.set("uri",uri);
	voiceObject.set("checkedCount",parseInt(checkedCount));	
	voiceObject.save(null,{
		success: function(voiceObject) {
			alert("添加成功");
		},
		error: function(voiceObject,error) {
			alert("Error: " + error.code + " " + error.message);
		}
	});
}
function updateVoiceObject(i){
	var voiceObject = voiceObjectList[i];	
	var title=$("#input_title_"+i).val();
	var type=$("#input_type_"+i).val();
	var uri=$("#input_uri_"+i).val();
	var checkedCount=$("#input_checkedCount_"+i).val();	
	voiceObject.set("title",title);
	voiceObject.set("type",type);
	voiceObject.set("uri",uri);
	voiceObject.set("checkedCount",parseInt(checkedCount));	
	voiceObject.save(null,{
		success: function(voiceObject) {
			alert("修改成功");
		},
		error: function(voiceObject,error) {
			alert("Error: " + error.code + " " + error.message);
		}
	});
}
function delVoiceObject(i){
	var voiceObject = voiceObjectList[i];	
	voiceObject.destroy({
		success: function(voiceObject) {
			alert("删除成功");
			$("#input_title_"+i).parent().parent().remove();
		},
		error: function(voiceObject,error) {
		alert("Error: " + error.code + " " + error.message);
		}
	});
}
function signUp(){
	var user = new Parse.User();
	user.set("username", $("#username").val());
	user.set("password", $("#password").val());
	user.set("email", $("#email").val());

	user.signUp(null, {
	  success: function(user) {
		alert("注册成功");
	  },
	  error: function(user, error) {
		alert("Error: " + error.code + " " + error.message);
	  }
	});
}
function login(){
	Parse.User.logIn($("#username").val(), $("#password").val(), {
	  success: function(user) {
		alert("登录成功");
	  },
	  error: function(user, error) {
		alert("Error: " + error.code + " " + error.message);
	  }
	});	
}	