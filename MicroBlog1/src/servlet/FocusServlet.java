package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj2.RelationOperator;
import pj2.User;
import pj2.UserOperator;

public class FocusServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FocusServlet() {
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
		String iduserSourceStr = request.getParameter("idSource");	
		String iduserTargetStr = request.getParameter("idTarget");
		String cmd = request.getParameter("cmd");
		System.out.println(iduserSourceStr);
		System.out.println(iduserTargetStr);
		int iduserSource = Integer.parseInt(iduserSourceStr);
		int iduserTarget = Integer.parseInt(iduserTargetStr);

	
		try {
			if("focus".equals(cmd)){
				if (RelationOperator.focus(iduserSource,iduserTarget)){
					//request.getSession().setAttribute("user", user);
					String url = "../personPage.jsp";
					url = url + "?iduser="+iduserTarget;
					response.sendRedirect(url);
					System.out.println("关注成功");
					User user = (User)request.getSession().getAttribute("user");
					user.setFocusing(user.getFocusing()+1);
					request.getSession().setAttribute("user", user);
					//TODO
					
				}
			}
			else{
				if (RelationOperator.unfocus(iduserSource,iduserTarget)){
					//request.getSession().setAttribute("user", user);
					String url = "../personPage.jsp";
					url = url + "?iduser="+iduserTarget;
					response.sendRedirect(url);
					System.out.println("解除关注成功");
					//TODO
					User user = (User)request.getSession().getAttribute("user");
					user.setFocusing(user.getFocusing()-1);
					request.getSession().setAttribute("user", user);
					
				}
				
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
