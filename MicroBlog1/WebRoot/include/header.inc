<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<h1 id="logo">
			<a href="home.jsp">微博</a></h1>
		<div class="topMenu">
			<span class="topusername"><a href="home.jsp">
				<span id="txtNickname"><%=user.getNickname() %></span>
			</a></span><span><a href="uploadPortrait.jsp">设置</a></span>  <span><a href="index.jsp">
					退出</a></span></div>
		<div id="nav">
			<ul>
				<li class="home"><a href="home.jsp">我的主页</a></li>
				<li class="news"><a href="listRS.jsp?action=allmb">微博广场</a></li>
			</ul>
		</div>
		<div class="tSearch">
			<form id="searchForm" method="get" action="listRS.jsp">
			<label for="searchKey">
				
			</label>
			<input id="searchKeyS" maxlength="50" name="keyword" class="inputTxt" type="text" />
			<input name="action" value="search" id="action" type="hidden" />
			<input class="inputBtn" value="" type="submit" />
			</form>
		</div>