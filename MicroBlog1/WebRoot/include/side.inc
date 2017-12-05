<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<div id="UIn">
			<div class="userDetail">
				<a href="uploadPortrait.jsp" title="上传头像">
					<img src=<%=user.getIconurl() %> style="border-width: 0px;" />
				</a>
				<p>
					<strong><a href="personPage.jsp?iduser=<%=user.getIduser() %>"><span id="ctl00_ctl00_MainFrame_TxtNickname"><%=user.getNickname() %></span></a></strong>
					</p><div style="clear: left;">来自：<%=user.getComefrom() %></div>
					

			</div>
			<div class="nums">
				<p>
					<a href="listRS.jsp?action=fans">追随我<br />
						<strong id="followerNum"><span id="txtFollower"><%=user.getFans() %></span></strong></a></p>
				<p>
					<a href="listRS.jsp?action=focusing">我追随<br />
						<strong><span id="txtFollowing"><%=user.getFocusing() %></span></strong></a></p>
				<p>
					<a href="listRS.jsp?action=mb">微博数<br />
						<strong id="talkNum"><span id="txtBlogs"><%=user.getMbnum() %></span></strong></a></p>
			</div>
		</div>

		<div id="iploadPortrait" class="SC fold">
		<h3><a href="uploadPortrait.jsp?iduser=<%=user.getIduser() %>">修改头像</a></h3>
		</div>
		
		<div id="following" class="SC fold">
		<h3><a href="listRS.jsp?action=focusing">我的关注</a></h3>
		</div>
		<div id="people" class="SC fold">
		<h3><a href="listRS.jsp?action=hotUser">热门用户</a></h3>
		<ul>
            <%for (User hotUser :hotUsers){ %>
	            <li><a href="personPage.jsp?iduser=<%=hotUser.getIduser() %>"> <img alt=<%=hotUser.getNickname() %> src=<%=hotUser.getIconurl() %> /> <%=hotUser.getNickname() %></a></li>
            <%}%>    
        </ul>
		</div>