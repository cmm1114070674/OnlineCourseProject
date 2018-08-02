package Servlet;

import Bean.UserBean;
import Dao.DaoImpl.CourseDaoImpl;
import Dao.DaoImpl.DropCourseDaoImpl;
import Service.MD5Utils;
import Service.UserGet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/dropCourseServlet")
public class DropCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		String cheak = request.getParameter("cheak");

		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();

		UserBean userBean = UserGet.getUserByUsername(username);
		String pass = MD5Utils.md5(cheak);

		System.out.println(courseId);
		System.out.println(username);

		DropCourseDaoImpl dao = new DropCourseDaoImpl();

		CourseDaoImpl courseDao = new CourseDaoImpl();
		int permission = 0;
		if (session.getAttribute("username") == null) {
			permission = 0;
		} else {
			username = session.getAttribute("username").toString();
			permission = courseDao.getPermissoin(courseId, username);
		}
//		UserBean user = UserGet.getUserByUsername(username);
//		CourseDaoImpl.student_course_insert(user.getId(), courseId);
		if(!userBean.getPassword().equals(pass)) {
			response.sendRedirect("courseInformation.jsp?courseId=" + courseId + "&permission=" + permission);
		}
		else {
			dao.dropCourse(username, courseId);
			if (session.getAttribute("username") == null) {
				permission = 0;
			} else {
				username = session.getAttribute("username").toString();
				permission = courseDao.getPermissoin(courseId, username);
			}
			response.sendRedirect("courseInformation.jsp?courseId=" + courseId + "&permission=" + permission);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
