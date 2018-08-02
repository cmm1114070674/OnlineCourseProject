package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.FileUploadBean;
import Dao.UploadFileDao;


@WebServlet("/allResourceServlet")
public class AllResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int courseId  = Integer.parseInt(request.getParameter("courseId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		System.out.println("get resources permission:"+permission);
		
		UploadFileDao dao = new UploadFileDao();
		
		List<FileUploadBean> beans = dao.getFiles(courseId);
		
		request.setAttribute("allResources", beans);
		
		request.getRequestDispatcher("courseResource.jsp?courseId="+courseId+"&permission="+permission).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
