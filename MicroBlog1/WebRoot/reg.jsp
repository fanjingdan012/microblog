<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
	<link href="css/reg.css" rel="stylesheet" />
     <script type="text/javascript"  src="js/checkReg.js"></script>
	<title>注册微博 神马微博</title>
    <base href="<%=basePath%>" />
</head>
<body id="OP">
    	<div id="mainWrapper">
		<div class="title">
			<h2>获得一个神马微博帐号，只需要30秒！</h2>
            
		</div>
		<div class="cWrap">
			<div>
                
				<h3><b>填写下列信息：</b>推荐使用邮箱作为您的用户名</h3>
				<p class="notes">
					请填写所有的选项<p>已有神马微博账号？<a href="index.jsp">登陆</a></p>
				</p>
				<form name="regForm" method="post" action="servlet/RegServlet" id="regForm">
				<table border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<th>
								<span class="ico_wb"></span>微博帐号：
							</th>
							<td>
								<input name="username" maxlength="20" id="TxtUsername" class="inputTxt" type="text" />
								<div id="TxtusernameError" class="errorMsg"></div>
							</td>
						</tr>
						<tr>
							<th>
								昵称：
							</th>
							<td>
							<input name="nickname" maxlength="20" id="TxtNickname" class="inputTxt" type="text" onfocus="checkName()" />
								<div id="TxtnicknameError" class="errorMsg"></div>
							</td>
						</tr>
						<tr>
							<th>
								设定密码：
							</th>
							<td>
							<input name="password" maxlength="20" id="TxtPassword1" class="inputTxt" type="password"onfocus="checkNickName()" />
								<div id="Txtpassword1Error" class="errorMsg"></div>
							</td>
						</tr>
						<tr>
							<th>
								确认密码：
							</th>
							<td>
								<input name="TxtPassword2" maxlength="20" id="TxtPassword2" class="inputTxt" type="password"onfocus="checkPass()" />
								<div id="Txtpassword2Error" class="errorMsg"></div>
							</td>
						</tr>
						<tr>
							<th>
								您来自：
							</th>
							<td>
								<input name="comefrom" maxlength="20" id="TxtComeFrom" class="inputTxt" type="text"onfocus="checkConfirm()" />
								<div id="TxtComefromError" class="errorMsg"></div>
							</td>
						</tr>
						
						<tr>
							<th>
							<input type = "hidden" id="result" name= "result" />
                            </th>
							<td>
							<input name="BtnSubmit" value="开始微博生活" id="BtnSubmit" class="btn" type="submit" onclick="checkAll()" />
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
		</div>
	</div>
  </body>
</html>
