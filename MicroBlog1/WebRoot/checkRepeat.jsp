<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="pj2.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String username = request.getParameter("name");
if(UserOperator.getUserByUsername(username)!=null){//reged
    response.getWriter().write("f");
}
else{
    response.getWriter().write("t");
}
%>


