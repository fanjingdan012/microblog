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
 
String iduserStr = request.getParameter("iduser");
int iduser = Integer.parseInt(iduserStr);
User u = UserOperator.getUserByIduser(iduser);
if (u == null){
	response.sendRedirect("./error.jsp");
	return;
}
List<User> hotUsers = UserOperator.getHotUsers(9);
//get all the microblogs
List<MicroBlog> microblogsPublished = MicroblogsOperator.getMBByIdpublisher(u.getIduser());  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/home.css" />
<link rel="stylesheet" href="css/personPage.css" />
<script type="text/javascript"  src="js/home.js"></script>
<base href="<%=basePath%>" />
<title>神马微博 都是浮云</title>
</head>
<body>
	<div id="headWrap">
        <%@ include file="include/header.inc"%>
	</div>
    <div id="mainWrapper">
	<div class="main">
       <div id = "personInfo">
           <img id = "bigHead"src=<%=u.getIconurl() %> width = 200px; height=200px; />
           <div id="otherInfo" >
               <ul >
                   <li><%=u.getNickname() %></li>
                   <li>来自：<%=u.getComefrom() %></li>
                   <li>他的关注：<%=u.getFocusing() %>个</li>
                   <li>他的粉丝：<%=u.getFans() %>个</li>
                   <li>他的id：<%=u.getIduser() %></li>
               </ul>
               <%if(u.getIduser()!=user.getIduser()){ %>
               <%if(RelationOperator.isFocusing(user.getIduser(),u.getIduser())){ %>
                   <form action = "servlet/FocusServlet" method = "post">
                   <input type = "hidden" name = "idSource" value =<%=user.getIduser() %> />
                   <input type = "hidden" name = "idTarget" value = <%=u.getIduser() %> />
                   <input type = "hidden" name = "cmd" value = "unfocus" /> 
                   <input type = "submit" value = " 解除关注 "/>
                   </form> 
               <%}else{ %>
               
               <form action = "servlet/FocusServlet" method = "post">
                   <input type = "hidden" name = "idSource" value =<%=user.getIduser() %> />
                   <input type = "hidden" name = "idTarget" value = <%=u.getIduser() %> />
                   <input type = "hidden" name = "cmd" value = "focus" /> 
                   <input type = "submit" value = " 关注 "/>
               </form>
               <%} %>
               <%} %>
           </div>
		
	    </div>
		<div class="AL myPubList">
			<div class="title">
				<h3>
					<b class="ico_mypub"><em></em></b>
					他的广播</h3>
				<div class="orderType">
					<b>全部</b>
				</div>
			</div>
			<ul style="margin-bottom: 574px;" id="talkList" class="LC">	
                <%int j = 0;
                for (MicroBlog mb : microblogsPublished){%>
			        <li>
                <%j++; %>
				<div class="userPic">
				    <a href="personPage.jsp?iduser=<%=mb.getIdpublisher() %>"><img alt="userpic" src=<%= mb.getPublishericonurl() %> /></a>
                </div>
				<div class="msgBox">
					<div class="userName"><strong><a href="personPage.jsp?iduser=<%=mb.getIdpublisher() %>"><%=mb.getPublishernickname() %></a></strong>:</div>
					<div class="msgCnt">&nbsp;<%=mb.getTxt() %></div>
					
                    <div class="mediaWrap">
                    <%if(mb.getPicurl()!=null&&mb.getPicurl()!=""){ %>
                       <img src=<%=mb.getPicurl() %> />
               
                    <%}if(mb.getVideourl()!=null&&mb.getVideourl()!=""){ %>
                        <div id="ctl00_ctl00_MainFrame_LeftPanel_Mblogs_ctl05_video" class="videoBox">	
							<div class="vWrap">
								<div style="display: block;" class="vBox loading">
									<embed allownetworking="internal" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" quality="high" src=<%=mb.getVideourl() %> height="200" width="200" /></div>
							</div>
						</div>
                    <%} %>
                    </div>
                    <%if (mb.getIdoriginalmicroblog()!=0){ 
                         MicroBlog mbo = MicroblogsOperator.getMBByIdmicroblogs(mb.getIdoriginalmicroblog());%>
                        <div class="replyBox">
						    <div class="msgBox">
								<div class="msgCnt">
									<strong><a href="personPage.jsp?iduser=<%=mbo.getIdpublisher() %>"><%=mbo.getPublishernickname() %></a>:</strong>
									<%=mbo.getTxt() %>
								</div>
								<div class="pubInfo">
									<span class="left">
									    <%=mbo.getTime() %> 来自网页
										<a class="zfNum" >转播(<b class="relayNum"><%=mbo.getFwnum() %></b>)</a>
                                    </span>
                                </div>
							</div>
						</div>
	                <%} %><!-- end fw if -->
					<div class="pubInfo">
						<span class="left"><%=mb.getTime() %>来自网页</span>
						<div class="funBox">
							<a href="home.jsp?action=fw&idmicroblogs=<%=mb.getIdmicroblogs() %>" onclick = "showFw()" class="relay" id="69">转播(<%=mb.getFwnum() %>)</a><span>|</span>
                            <a href="javascript:showComment(<%=j %>)"class="reply">评论(<%=mb.getCommentnum() %>)</a><span>|</span> 
						</div>
                    </div>
                    <div class="commentBox"id="commentBox<%=j%>"style="display:none;">
		                <div class="logininput new_position">
                            <form action="servlet/CommentServlet"method="post" >
				                <div class="cntBox">
				                <textarea id="cmt<%=j%>"name = "txt" style="height: 36px; border-style: solid; border-width: 1px; font-size: 12px; line-height: 18px;width:300px;" class="lf" onfocus= "checkCmtTxtLength(<%=j %>)"onkeyup="checkCmtTxtLength(<%=j %>)"onkeydown="checkCmtTxtLength(<%=j %>)"></textarea>
				                <input type="hidden"name="idtargetmicroblog"value =<%=mb.getIdmicroblogs() %>  />
                                <input type="hidden"name="idpublisher"value=<%=user.getIduser() %> />
                                <input type="hidden"name="publishernickname"value=<%=user.getNickname() %> />
			                    <input type="hidden"name="from"value="personPage.jsp" />
                                <input type="hidden"name="paramIduser"value=<%=request.getParameter("iduser")%> />
                                <input type ="submit" value = "评论" style="display:inline;"/>
			                    <p>还能输入<em id="charremain<%=j%>">140</em>字</p>
                                </div >
                            </form>
		                </div>
                        <ul class="PL_list oddline">
                        <%List<Comment>comments = CommentOperator.getCommentByMB(mb.getIdmicroblogs());
                        for(Comment c:comments){ %>
			                <li class="MIB_linedot3" >
			                    <div class="txt">
				                     <div class="txtinfo">
								         <a href="personPage.jsp?iduser=<%=c.getIdpublisher() %>"><%=c.getPublishernickname()%></a> <%=c.getTxt() %><span class="MIB_txtbl">(<%=c.getTime() %>)</span>
									 </div>
				                </div>
                            </li>
                        <%} %>		
		                </ul>
                    </div><!-- end comment box -->
                    
				</div><!-- end mshBox -->
			</li>

                <%} %><!-- end for -->	   
            </ul>
		</div>
		<div class="clear" ></div>
	</div>
	<div class="side">
        <%@ include file="include/side.inc"%>
	</div>

	<div class="clear" ></div>
</div>
<div id="Copyright">
    <%@ include file="include/footer.inc"%>
</div>
</body>
</html>
