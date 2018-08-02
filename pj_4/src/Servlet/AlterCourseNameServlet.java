package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoImpl.CourseDaoImpl;


@WebServlet("/alterCourseNameServlet")
public class AlterCourseNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		String courseName = request.getParameter("courseName");
		System.out.println(courseId);
		System.out.println(courseName);
		
		CourseDaoImpl dao = new CourseDaoImpl();
		
		dao.alterCourseName(courseId, courseName);
		
		response.sendRedirect("course.jsp?courseId="+courseId);
		
	}

}
