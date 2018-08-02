package Servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CourseBean;
import Dao.DaoImpl.HomepageDaoImpl;


@WebServlet("/homepageServlet")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HomepageDaoImpl homepageDao = new HomepageDaoImpl();
		List<CourseBean> latestCourses = homepageDao.getLatestCourses();
		
		request.setAttribute("latestCourses", latestCourses);
		List<CourseBean> hottestCourses = homepageDao.getHottestCourses();
		request.setAttribute("hottestCourses", hottestCourses);
		request.getRequestDispatcher("homepage.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
