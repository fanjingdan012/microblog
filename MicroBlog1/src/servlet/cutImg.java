package servlet;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj2.User;
import pj2.UserOperator;
import pj2.ImgDeal;
public class cutImg extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public cutImg() {
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
		String HStr = request.getParameter("H");
		String VStr = request.getParameter("V");
		String heightStr = request.getParameter("height");
		String widthStr = request.getParameter("width");
		String src= request.getParameter("src");
		int h = Integer.parseInt(HStr);
		int v = Integer.parseInt(VStr);
		int height = Integer.parseInt(heightStr);
		int width = Integer.parseInt(widthStr);
        System.out.println(request.getRealPath(src));
		File file = new File(request.getRealPath(src));
        
    	try {
			BufferedImage img = ImageIO.read(file);
			System.out.print(200-h+100);
			System.out.println(width);
			System.out.print(200-v+100);
			System.out.println(height);
			ImgDeal.saveSubImage(ImgDeal.resize(img, width, height),new Rectangle(200-h,200-v,100,100), new File(request.getRealPath(src)));
			

			response.sendRedirect("../home.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
