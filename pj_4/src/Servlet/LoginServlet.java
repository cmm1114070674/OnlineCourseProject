package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.LoginBean;
import Dao.DaoImpl.LoginDaoImpl;
import Service.MD5Utils;



@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginDaoImpl loginDao = new LoginDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Utils.md5(password);
		String page = request.getParameter("page");
	    int courseId = Integer.parseInt(request.getParameter("courseId"));
	    if(courseId != 0)
	    	page = page +"&courseId=" +courseId;
		System.out.println(page);
		System.out.println(courseId);
		
		LoginBean loginBean = new LoginBean(username, password);
		
		if(loginDao.checkLogin(loginBean)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			request.setAttribute("login", "1");
			request.getRequestDispatcher("login.jsp?page="+page).forward(request, response);
		}
		else {
			request.setAttribute("login", "0");
			request.getRequestDispatcher("login.jsp?page="+page).forward(request, response);
		}

	}

}
