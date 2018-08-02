package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutServlet")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String page = request.getParameter("page");
		
		int courseId = 0;
		if(request.getParameter("courseId") != null)
			courseId = Integer.parseInt(request.getParameter("courseId"));
		
		if(courseId != 0)
			page = page + "?courseId=" + courseId;
		
		System.out.println(page);
		System.out.println(courseId);
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect(page);
		
	}


}
