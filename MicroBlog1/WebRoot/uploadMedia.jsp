<!-- 2007-9-27 Created by Christen @ PConline -->
<%@ page language="java" contentType="text/html; charset=GBK"
     pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="pj2.*"%>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%!
     // ���潫�ϴ�֮�������õ��ŷ�����images/headsĿ¼��
     // ���������ϴ�֮������СΪ 5 MB
     String saveDirectory = "/media/";
     int maxPostSize = 5 * 1024 * 1024 ;

     // ���������ϴ��������ݵı�������̬ΪString
     String filename = null;
     String contentType = null;
     // ���������ϴ��������ݵ���������̬ΪString
     int count=0;
%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//save info
User user = (User)request.getSession().getAttribute("user");
if (user == null){
	response.sendRedirect("./index.jsp");
	return;
}
request.setCharacterEncoding("utf-8");
MultipartRequest theMultipartRequest = new MultipartRequest(request , request.getRealPath(saveDirectory) , maxPostSize );
    String fileName="";
     Enumeration theEnumeration = theMultipartRequest.getFileNames();
     while(theEnumeration.hasMoreElements()){
     String fieldName = (String)theEnumeration.nextElement();
     fileName = theMultipartRequest.getFilesystemName(fieldName);
     String contentType = theMultipartRequest.getContentType(fieldName);
     File theFile = theMultipartRequest.getFile(fieldName);
      out.println(fieldName+"<br>");
      out.println(fileName+"<br>");
      out.println(contentType+"<br>");
      out.println(theFile.getAbsolutePath()+"<br>");
     }
     String url = "media/"+fileName;
     if(UserOperator.UpdateIconurl(url,user.getIduser())){
         user.setIconurl(url);
         request.getSession().setAttribute("user", user);
        // response.sendRedirect("home.jsp");
     }
     else{
         response.sendRedirect("error.jsp");
     }
     
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>

<body>

</body>
</html>

