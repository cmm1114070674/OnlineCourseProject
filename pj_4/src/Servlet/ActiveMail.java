package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DaoImpl.RegisterDaoImpl;



@WebServlet("/activeMail")
public class ActiveMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		RegisterDaoImpl dao = new RegisterDaoImpl();
		
		String activeCode = request.getParameter("activeCode");
		System.out.println(activeCode);
		
		if(dao.checkActiveCode(activeCode)) {
			dao.changeActiveFlagByActiveCode(activeCode);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("username", activeCode);
		
		response.sendRedirect("homepage.jsp");
		
	}

}
