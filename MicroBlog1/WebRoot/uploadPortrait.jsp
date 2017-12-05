<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="pj2.*" %>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 //save info
User user = (User)request.getSession().getAttribute("user");
if (user == null){
	response.sendRedirect("./index.jsp");
	return;
}
//get all the microblogs
 
//get hot users
List<User> hotUsers = UserOperator.getHotUsers(9);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/home.css" />
<link rel="stylesheet" href="css/uploadPortrait.css" />
<script type="text/javascript"  src="js/uploadPortrait.js"></script>
<base href="<%=basePath%>" />
<title>神马微博 都是浮云</title>
</head>
<body>
    
	<div id="headWrap">
        <%@ include file="include/header.inc"%>
	</div>
	
    <div id="mainWrapper">	
	    <div class="main">
 
        <div id="uploadPortraitForm">
			<form name="Form1" enctype="multipart/form-data" method="post" action="upload.jsp">
                <p style="font-size:28px;"> 选择头像： <input type="file"name="icon" size="20" maxlength="20" style = "margin : 20px;"/> </p>
                <p> <input type="submit"value="上传"style = "margin : 10px;" /> <input type="reset" value="清除" style = "margin : 10px;"/> </p>
            </form>	        
	    </div>
        <div id="controlForm">
                <form action="servlet/cutImg" method="post" >
                    <input type="button" value="放大" onclick="imgToSize(1);" />
                    <input type="button" value="缩小" onclick="imgToSize(0);" />
	                                           水平<input type="text" name="H"id="showH" value="0" />
                                                      竖直<input type="text" name="V"id="showV" value="0" />
                    <input type="text" name="src" value=<%=user.getIconurl() %> /> 
              
                    <input type="text" name="height"id="height" value="100" /> 
                    <input type="text" name="width" id="width" value="100" />
                    <input type="submit" value="确定" />  
                </form>
        </div>
        <div id="workspace" >
            <div id="jie"></div>
            <div id="img">
                   <img id="oImg" src=<%=user.getIconurl()%> style="position:absolute;top:0px;left:0px; zoom:100%; cursor:move;" onmousedown="mStart();" onmouseup="mEnd();"onmousemove="drag()" />
            </div>
        </div><!-- end workspace -->
    </div><!-- end main -->
	<div class="clear" ></div>
	
   
	<div class="side">
    </div>
	<div class="clear" ></div>
</div><!-- end mainWrapper -->
<div id="Copyright">
<%@ include file="include/footer.inc"%>
</div>
</body>
</html>
