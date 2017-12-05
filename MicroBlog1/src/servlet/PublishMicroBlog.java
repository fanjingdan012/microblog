package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj2.MicroblogsOperator;
import pj2.User;
import pj2.UserOperator;

public class PublishMicroBlog extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PublishMicroBlog() {
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
		String idpublisherStr = request.getParameter("iduser");
		String txt = request.getParameter("txt");	
		int idpublisher = Integer.parseInt(idpublisherStr);
		String publishernickname = request.getParameter("nickname");
		String publishericonurl = request.getParameter("iconurl");
		String idoriginalmicroblogStr = request.getParameter("idoriginalmicroblog");
		String idfwmbStr = request.getParameter("idfwmb");
		int idoriginalmicroblog = 0;
		int idfwmb = 0;
		String videourl = request.getParameter("videourl");
		String picurl = request.getParameter("picurl");
		System.out.println(idpublisherStr);
		if(idoriginalmicroblogStr!=null&&idfwmbStr!=null){
			System.out.print("fwfwfwfwfwfw");
			idoriginalmicroblog = Integer.parseInt(idoriginalmicroblogStr);
			idfwmb = Integer.parseInt(idfwmbStr);
		}
		try {
			if (MicroblogsOperator.publishMicroBlog(txt,idpublisher,idoriginalmicroblog,idfwmb,publishericonurl,publishernickname,videourl,picurl)){
				User user = (User)request.getSession().getAttribute("user");
				user.setMbnum(user.getMbnum()+1);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("../home.jsp");
				System.out.println("发布成功");
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
