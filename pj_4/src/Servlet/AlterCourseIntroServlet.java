package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoImpl.CourseDaoImpl;


@WebServlet("/alterCourseIntroServlet")
public class AlterCourseIntroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		String courseIntro = request.getParameter("courseIntro");
		System.out.println(courseId);
		System.out.println(courseIntro);
		
		CourseDaoImpl dao = new CourseDaoImpl();
		
		dao.alterCourseIntro(courseId, courseIntro);
		
		response.sendRedirect("course.jsp?courseId="+courseId);
		
	}

}
