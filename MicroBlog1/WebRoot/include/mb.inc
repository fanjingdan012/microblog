<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			                    <input type ="submit" value = "评论" style="display:inline;"/>
			                    <p>还能输入<em id="charremain<%=j%>">140</em>字</p>
                                </div >
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
