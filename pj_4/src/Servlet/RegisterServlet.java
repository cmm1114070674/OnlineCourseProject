package Servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.RegisterBean;
import Dao.DaoImpl.RegisterDaoImpl;
import Service.MD5Utils;
import Service.MailUtil;



@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RegisterDaoImpl registerDao = new RegisterDaoImpl();
		
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Utils.md5(password);
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		System.out.println("register:"+username);
		
		
		if(registerDao.checkUsername(username)) {
			String activeCode = username;
			int activeFlag = 0;
			Date registerTime = new Date();
			RegisterBean registerBean = new RegisterBean(username, password, email, 
					phone, activeCode, activeFlag, new java.sql.Date(registerTime.getTime()));
			registerDao.save(registerBean);
			try {
				MailUtil.sendActiveMail(email, activeCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("register.jsp?register=1");
		}
		else {
			response.sendRedirect("register.jsp?register=0");
		}
	}

}
