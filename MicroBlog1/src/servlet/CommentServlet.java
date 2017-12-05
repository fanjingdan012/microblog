package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj2.CommentOperator;
import pj2.MicroblogsOperator;

public class CommentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CommentServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idpublisherStr = request.getParameter("idpublisher");
		String txt = request.getParameter("txt");	
		int idpublisher = Integer.parseInt(idpublisherStr);
		String publishernickname = request.getParameter("publishernickname");
		String idtargetmicroblogStr = request.getParameter("idtargetmicroblog");
		int idtargetmicroblog = Integer.parseInt(idtargetmicroblogStr);
			
		try {
			if(txt.length()>140){
				txt=txt.substring(0, 139);
			}
			if (CommentOperator.commentMicroBlog(txt,idpublisher,idtargetmicroblog,publishernickname)){
				//request.getSession().setAttribute("user", user);
				if("listRS.jsp".equals(request.getParameter("from"))){
					if("search".equals(request.getParameter("action"))){
						response.sendRedirect("../listRS.jsp?action=search&keyword="+request.getParameter("keyword"));
					}
					else{
						response.sendRedirect("../listRS.jsp?action="+request.getParameter("action"));
					}
				}
				else if("personPage.jsp".equals(request.getParameter("from"))){
                    String iduser= request.getParameter("paramIduser");
                    response.sendRedirect("../personPage.jsp?iduser="+iduser);
					
				}
				else{
				    response.sendRedirect("../home.jsp");
				}
				System.out.println("ÆÀÂÛ³É¹¦");
				//TODO
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("../error.jsp");
		}	
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
