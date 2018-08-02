package Servlet;

import Bean.UserBean;
import Dao.ChooseCourseDao;
import Dao.DaoImpl.ChooseCourseDaoImpl;
import Dao.DaoImpl.CourseDaoImpl;
import Service.UserGet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/chooseCourseServlet")
public class ChooseCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ChooseCourseDao chooseCourseDao = new ChooseCourseDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		System.out.println(username+courseId);
		

		chooseCourseDao.chooseCourse(username, courseId);

		
		response.sendRedirect("course.jsp?courseId="+courseId+"&permission=2");
	}


}
