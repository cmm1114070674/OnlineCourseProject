package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoImpl.CourseUnitDao;


@WebServlet("/alterCourseUnitServlet")
public class AlterCourseUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		CourseUnitDao dao = new CourseUnitDao();
		
		request.setCharacterEncoding("UTF-8");
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int courseUnitIndex = Integer.parseInt(request.getParameter("courseUnitIndex"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		
		System.out.println(courseId);
		System.out.println(courseUnitIndex);
		System.out.println("alter course unit servlet :"+permission);
		
		String alteredUnitName = request.getParameter("alteredUnitName");
		System.out.println(alteredUnitName);
		
		dao.alterUnit(courseId, courseUnitIndex, alteredUnitName);
		
		response.sendRedirect("courseKnowledgePoint.jsp?courseId="+courseId+"&permission="+permission);
		
				
	}

}
