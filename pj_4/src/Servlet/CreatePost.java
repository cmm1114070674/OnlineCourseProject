package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DaoImpl.PostDaoImpl;


@WebServlet("/createPost")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		
		System.out.println(subject);
		System.out.println(content);
		System.out.println(username);
		System.out.println("courseId"+courseId);
		
		PostDaoImpl dao = new PostDaoImpl();
		int postId = dao.createPost(courseId, subject, content, username);
		response.sendRedirect("discussPost.jsp?courseId="+courseId+"&postId="+postId+"&permission="+permission);
		
	}

}
