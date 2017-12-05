<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="pj2.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<User> people = UserOperator.getHotUsers(5);
String loginMsg = request.getParameter("loginMsg");
List<User> peoplehere = UserOperator.getHotUsers(10);

User user = (User)request.getSession().getAttribute("user");
if(user!=null){
    user=null;
    request.getSession().setAttribute("user",null);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>神马微博 都是浮云</title>
<link rel="stylesheet" href="css/common.css" />
<link type="text/css" href="css/login.css" rel="stylesheet"/>
</head>
<body>
<div id="headWrap" class="clear">
<div class="headWrap_cnt">
<div class="regBtnBox"><a href="reg.jsp"
	class="regBtn" title="立即开通微博">立即开通微博</a>
</div>

</div>
</div>
<div id="mainWrapper">
<div class="wrapper">
<div class="rightBox">
<div class="loginBox">
<table border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td height="338">
			<div class="login_form" id="Login_Frame"
				style="width: 100%; height: 162px;">
			<div id="login" class="main">
                <%if("false".equals(loginMsg)){ %>
                    <font color = red style= "margin:40px;font-size=36px;">用户名或密码错误</font>
                <%} %>
			<div style="display: block;" id="web_login">
			<form name="loginform" method="post" action="servlet/LoginServlet"
				id="loginform">
			<ul id="g_list" style="width: 100%;">
				<li id="g_u"><span><u class="item" id="label_uin">帐 号：</u></span> <input
					name="username" id="TxtUsername" tabindex="1" class="inputstyle"
					type="text" /></li>
				<li id="wb_tips"><span></span></li>
				<li id="g_p"><span><u class="item" id="label_pwd">密 码：</u></span> <input
					name="password" id="TxtPassword" tabindex="2" class="inputstyle"
					type="password" /></li>
				<li><span></span></li>
			</ul>
            
			<div class="login_button"><input name="BtnSubmit" value="登录"id="BtnSubmit" tabindex="3" class="btn" type="submit" /></div>
			</form>
			</div>
			</div>
			</div>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
<div class="leftBox">
<div class="wording">
<ul id="wording">

	<li>
	<div class="pic"><a> <img alt="userpic"
		src="images/heads/20110106235522_catspirit.jpg" /></a></div>
	<div class="msgBox">
	<p><strong><a> catspirit</a>：</strong> 真理传了些什么呀</p>
	<p class="pubInfo">01月08日 5:32:17 来自网页</p>
	</div>
	</li>

	<li>
	<div class="pic"><a> <img alt="userpic"
		src="images/heads/20110107212007_bellamy.jpg" /></a></div>
	<div class="msgBox">
	<p><strong><a> 真理</a>：</strong> 手机测试</p>
	<p class="pubInfo">01月07日 21:39:33 来自mobile手机</p>
	</div>
	</li>

	<li>
	<div class="pic"><a> <img alt="userpic"
		src="images/heads/20110107212007_bellamy.jpg" /></a></div>
	<div class="msgBox">
	<p><strong><a> 真理</a>：</strong></p>
	<p class="pubInfo">01月07日 21:32:13 来自网页</p>
	</div>
	</li>

</ul>
</div>
<div class="leftBoxBtm">
<div class="people">
<h2><strong class="title">这里的人：</strong></h2>
<ul>
<%for (User uh:peoplehere){ %>
    
	<li><a href="personPage.jsp?iduser=<%=uh.getIduser() %>" > 
        <img src=<%=uh.getIconurl() %>	alt=<%=uh.getNickname() %> /><%=uh.getNickname() %>
        </a>
    </li>
<%} %>
</ul>
</div>
<div class="rank">
<h2><strong class="title left">热门用户：</strong><span class="right">听众数</span></h2>
<ul>
   <%for (User hotUser :people){ %>
	<li><em class="top"></em><a 
        href="personPage.jsp?iduser=<%=hotUser.getIduser() %> " ><%=hotUser.getNickname() %></a><span><%=hotUser.getFans() %></span></li>
<%}%>

</ul>
</div>
</div>
</div>
<div class="clear" />
</div>
</div>
<div id="Copyright">
<%@ include file="include/footer.inc"%>
</div>
</body>
</html>