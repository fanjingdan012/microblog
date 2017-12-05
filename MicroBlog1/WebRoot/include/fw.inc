<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"  src="js/home.js"></script>
<%
//get param action
String action1 = request.getParameter("action");

if("fw".equals(action1)){
System.out.println(action1);
    String idmicroblogsStr = request.getParameter("idmicroblogs");
    System.out.println(idmicroblogsStr);
    if(idmicroblogsStr!=null){
        int idmicroblogs = Integer.parseInt(idmicroblogsStr);
        MicroBlog microblogToFw = MicroblogsOperator.getMBByIdmicroblogs(idmicroblogs);
        if (microblogToFw == null){
            System.out.println("null fw");
	        response.sendRedirect("./error.jsp");
	        return;
        } 
        else{
            String fwAddTxt = "转自 "+microblogToFw.getPublishernickname()+":"+microblogToFw.getTxt();
            
     %>



    <div id="fwWindow">
        <div class="layerBox">
            <div class="layervote layerMoveto">
                 <div class="layerMedia_close">
                      <a href="home.jsp" title="关闭" node-type="close" onclick="hideFw()" class="close">x</a>
                 </div>
                 <div class="shareLayer">
                     <div>随便说说 </div>
                     <div id="originalTxt" node-type="rootFeed">
                         <%=microblogToFw.getTxt() %>
                     </div>
                     <form action="servlet/PublishMicroBlog"method="post" >
				         <div class="cntBox">
				             <textarea rows="2" cols="20" id="msgTxtFw" name="txt" tabindex="1" onfocus ="checkFwTxtLength()" onkeydown = "checkFwTxtLength()" onkeyup = "checkFwTxtLength()" ><%=fwAddTxt%></textarea>
<%int originalID;
  if(microblogToFw.getIdoriginalmicroblog()>0){
      originalID = microblogToFw.getIdoriginalmicroblog();
  }else{
      originalID = microblogToFw.getIdmicroblogs() ;
      
  } %>
				                   <input type="hidden"name="idoriginalmicroblog"id="originBlogId" value=<%=originalID %> />
                                   <input type="hidden"name="idfwmb"value=<%=microblogToFw.getIdmicroblogs() %> /> 
                                   <input type="hidden"name="iduser"value=<%=user.getIduser() %> />
                                   <input type="hidden"name="nickname"value=<%=user.getNickname() %> />
                                   <input type="hidden"name="iconurl"value=<%=user.getIconurl() %> />
			                  </div>
				              <div class="funBox">
				                  <div class="uploadFace">
					                  <div class="ico_pic" ></div>
					                  <a class="txt" id="lnkface">表情</a>
					              </div>
                             </div>    
                             <span class="countTxt">还能输入<em id="charremainFw">140</em>字</span>
				             <input id="submit" class="sendBtn" value="" type="submit" />
	                        </form>	
                        </div>
                 </div>
             </div>
         
       

</div>
<%      
       } 
    }
}
%>