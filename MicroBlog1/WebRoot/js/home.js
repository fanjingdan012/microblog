function checkFwTxtLength(){
    var charremainFw = document.getElementById("charremainFw");
	var msgTxtFw = document.getElementById("msgTxtFw");
	var c = msgTxtFw.value.length;
	var remain = 140-c;
	charremainFw.innerHTML=remain;
	if(c>=140){
		msgTxtFw.value = msgTxtFw.value.substring(0,139);
	}
}
function checkCmtTxtLength(j){
    var charremainC = document.getElementById("charremain"+j);
	var msgTxtCmt = document.getElementById("cmt"+j);
	var c = msgTxtCmt.value.length;
	var remain = 140-c;
	charremainC.innerHTML=remain;
	if(c>=140){
		msgTxtCmt.value = msgTxtCmt.value.substring(0,139);
	}
}

function addface(i){
	var ul=document.getElementById("faceUl");
	ul.style.display="none";
	var txt ;
	txt ="<F"+i+">";
	switch (i){
		case 1:txt="<微笑>";break;
		case 2:txt="<难过>";break;
		case 3:txt="<喜欢>";break;
		dafault:txt="<F"+i+">";
	}
    document.getElementById("msgTxt").value=document.getElementById("msgTxt").value+txt;	
}
function showFace(){
	var ul=document.getElementById("faceUl");
	ul.style.display="block";
}
function addphoto(){
	var dvpic = document.getElementById("dvpic");
	dvpic.style.display="block";
	var dvvideo=document.getElementById("dvvideo");
	dvvideo.style.display="none";
}
function addvideo(){
	var dvpic = document.getElementById("dvpic");
	dvpic.style.display="none";
	var dvvideo=document.getElementById("dvvideo");
	dvvideo.style.display="block";
	
}


function showFw(){
	//display
	var fwWindow = document.getElementById("fwWindow");
	fwWindow.style.display = "block";
	var bgBlack = document.getElementById("bgBlack");
	bgBlack.style.display = "block";
    getMBInfo(idmicroblogs)
	//add param
	//var msgTxtFw = document.getElementById("msgTxtFw");//textarea
	/*msgTxtFw.value = "转自" +publishernickname+"："+txt;*/
	//var originalTxt = document.getElementById("originalTxt");
	//originalTxt.innerHTML="<p>"+idmicroblogs+"</p>";
	//getMBInfo(idmicroblogs);
	//msgTxtFw.value = idmicroblogs;
	
}
function hideFw(){
	var fwWindow = document.getElementById("fwWindow");
	fwWindow.style.display = "none";
	var bgBlack = document.getElementById("bgBlack");
	bgBlack.style.display = "none";
}
function showComment(j){
	//display
	var commentBox = document.getElementById("commentBox"+j);
	if(commentBox!=undefined){
	    commentBox.style.display = "block";
	}
	//add param
	/*var msgTxtFw = document.getElementById("msgTxtFw");//textarea
	msgTxtFw.value = "转自" +publishernickname+"："+txt;
	var originalTxt = document.getElementById("originalTxt");
	originalTxt.innerHTML="<p>"+txt+"</p>";
	*/
}
function hideComment(j){
	var commentBox = document.getElementById("CommentBox"+j);
	commentBox.style.display = "none";
}	




//ajax
function getMBInfo(idmicroblogs){
  xmlHttp=GetXmlHttpObject();
  if (xmlHttp==null)  {
    alert ("您的浏览器不支持AJAX！");
    return;
  } 
  var url="home.jsp";
  url=url+"?idmicroblogs="+idmicroblogs;
  xmlHttp.onreadystatechange=stateChanged;
  xmlHttp.open("GET",url,true);
  xmlHttp.send(null);
}









