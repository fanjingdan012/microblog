// JavaScript Document
/**
* 检查用户名
**/
var bool  = 0;
function checkName(){
	var nameValue=document.getElementById("TxtUsername").value;
	var errorMesg=document.getElementById("TxtusernameError");
	var pattern = /^\w*$/; 

	if(nameValue==""){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;用户名不能为空</font>";
		return false;
	}
	else if(nameValue.length>14||nameValue.length<2){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;用户名长度为2~14位</font>";
		return false;
	}
	else if(!pattern.test(nameValue)){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;用户名只能是英文、数字或下划线</font>";
		return false;
	}
	else{		
		checkRepeat(nameValue);
		return true;
	}
}
function checkRepeat(nameValue){
	var errorMesg=document.getElementById("TxtusernameError");
	errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;正在验证是否重复</font>";
	var xmlhttp;
	xmlhttp=null;
	var url = "checkRepeat.jsp";
	if (window.XMLHttpRequest){// code for Firefox, Opera, IE7, etc.
	  xmlhttp=new XMLHttpRequest();  
	}
	else if (window.ActiveXObject) {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (xmlhttp!=null){
	  xmlhttp.open("POST",url,true);	
	  xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  var postStr = "name="+nameValue;
	  xmlhttp.send(postStr);
	  xmlhttp.onreadystatechange=function(){
		  
		  
		  
		  if(xmlhttp.readyState==4 && xmlhttp.status == 200){
			  var result=xmlhttp.responseText;
			if(result.charAt(0)=="t"){
             
				errorMesg.innerHTML="<font color='green'>&nbsp;&nbsp;正确</font>";				
		  	}
			else if(result.charAt(0)=="f"){
				errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;该用户名已注册过</font>";
			}
			
			
		  }
		  
		  
	  };

	}
	else{
	    alert("Your browser does not support XMLHTTP.");
		bool=-1;
	}
}

/**
* 检查昵称
**/
function checkNickName(){
	var nameValue=document.getElementById("TxtNickname").value;
	var errorMesg=document.getElementById("TxtnicknameError");
	if(nameValue==""){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;昵称不能为空</font>";
		return false;
	}
	else if(nameValue.length>12){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;昵称长度为1~12位</font>";
		return false;
	}
	else{
		errorMesg.innerHTML="<font color='green'>&nbsp;&nbsp;正确</font>";
		return true;
	}
}
/**
* 检查密码
**/
function checkPass(){
	var nameValue=document.getElementById("TxtPassword1").value;
	var errorMesg=document.getElementById("Txtpassword1Error");
	var pattern1 = /^\S*$/;
	var pattern2 = /^\d*$/;
	if(nameValue==""){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;密码不能为空</font>";
		return false;
	}
	else if(nameValue.length<6){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;密码长度不能小于6位</font>";
		return false;
	}
	else if(pattern1.test(nameValue) && !pattern2.test(nameValue)){
		errorMesg.innerHTML="<font color='green'>&nbsp;&nbsp;正确</font>";
		return true;
	}
	else{
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;密码只能由数字、英文及符号组成，且不能全为数字</font>";
		return false;
	}
	
		
}
/**
* 检查密码强度
**/
function checkPass1(){
    var nameValue=document.getElementById("TxtPassword1").value;
	var errorMesg=document.getElementById("Txtpassword1Error");
	if(nameValue.length>5){
		if(nameValue.length<7){
			errorMesg.innerHTML="<font color='green'>&nbsp;&nbsp;密码强度：<font color='orange'><strong>弱</strong></font></font>";
	}
	else if(nameValue.length<13){
		errorMesg.innerHTML="<font color='green'>&nbsp;&nbsp;密码强度：<font color='yellow'><strong>中</strong></font></font>";
	}
	else{
		errorMesg.innerHTML="<font color='green'>&nbsp;&nbsp;密码强度：<strong>强</strong></font>";
	}
	}
	
		
}
/**
* 检查确认密码
**/
function checkConfirm(){
	var nameValue=document.getElementById("TxtPassword2").value;
	var nameValue1=document.getElementById("TxtPassword1").value;
	var errorMesg=document.getElementById("Txtpassword2Error");
	if(nameValue==""){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;确认密码不能为空</font>";
		return false;
	}
	else if(nameValue1!=nameValue){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;两次密码不一致</font>";
	    return false;
	}
	else{
		errorMesg.innerHTML="<font color='green'>&nbsp;&nbsp;正确</font>";
		return true;
	}
}
/**
* 检查来处
**/
function checkFrom(){
	var nameValue=document.getElementById("TxtComeFrom").value;
	var errorMesg=document.getElementById("TxtComefromError");
	
	if(nameValue==""){
		errorMesg.innerHTML="<font color='red'>&nbsp;&nbsp;来处不能为空</font>";
		return false;
	}
	else{
		errorMesg.innerHTML="<font color='green'>&nbsp;&nbsp;正确</font>";
		return true;
	}
	
}
/**
* 检查所有
**/
function checkAll(){
	var errorMesgName=document.getElementById("TxtusernameError");
	var checkName = (errorMesgName.innerHTML.charAt(13)=="g"||errorMesgName.innerHTML.charAt(12)=="g"||errorMesgName.innerHTML.charAt(14)=="g");
	if(checkName && 
	   checkNickName() && 
	   checkPass() && 
	   checkConfirm() &&
	   checkFrom()){		
		alert("注册成功！");
		document.getElementById("result").value="true";
        return true;
	}
	else{
		/*alert("失败");
		alert(errorMesgName.innerHTML.charAt(13));
		alert(errorMesgName.innerHTML.charAt(12));
		alert(errorMesgName.innerHTML.charAt(11));
		alert(errorMesgName.innerHTML);
		alert(checkName);
		alert(checkNickName());
		alert( checkPass() );
		alert( checkConfirm());
		alert(checkFrom());*/
		document.getElementById("result").value="false";
		return false;
		
	}
	
	
}