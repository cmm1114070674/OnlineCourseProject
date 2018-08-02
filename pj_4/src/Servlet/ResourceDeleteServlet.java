package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UploadFileDao;


@WebServlet("/resourceDeleteServlet")
public class ResourceDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UploadFileDao dao = new UploadFileDao();
		
		int resourceId = Integer.parseInt(request.getParameter("resourceId"));
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		System.out.println(resourceId);
		System.out.println(courseId);
		
		dao.deleteFile(resourceId);
		
		request.getRequestDispatcher("courseResource.jsp?courseId="+courseId).forward(request, response);
	}



}
