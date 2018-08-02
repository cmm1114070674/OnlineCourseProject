package Servlet;

import Bean.CourseBean;
import Dao.DaoImpl.CourseDaoImpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/courseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CourseDaoImpl courseDao = new CourseDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int courseId = Integer.parseInt(request.getParameter("courseId"));

		String picturePath = getServletContext().getRealPath("CoursePictureFromDatabase")+"\\"+courseId+".jpg";
		CourseBean course = courseDao.getCourse(courseId,picturePath);
		request.setAttribute("course", course);
		
	
		int permission;
		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null) {
			permission = 0;
		}
		else {
			String username = session.getAttribute("username").toString();
			permission = courseDao.getPermissoin(courseId, username);
		}
		
		request.getRequestDispatcher("courseInformation.jsp?courseId="+courseId+"&permission="+permission).forward(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
