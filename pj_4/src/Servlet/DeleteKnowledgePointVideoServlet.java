package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoImpl.KnowledgePointResourceDao;


@WebServlet("/deleteKnowledgePointVideoServlet")
public class DeleteKnowledgePointVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int resourceId = Integer.parseInt(request.getParameter("resourceId"));
		int courseId = Integer.parseInt(request.getParameter("courseId")); 
		int knowledgePointId = Integer.parseInt(request.getParameter("knowledgePointId")); 
		int permission = Integer.parseInt(request.getParameter("permission")); 
		System.out.println(resourceId);
		System.out.println(courseId);
		System.out.println(knowledgePointId);
		System.out.println("delete knowledge point resource permission:"+permission);
		
		KnowledgePointResourceDao knowledgePointResourceDao = new KnowledgePointResourceDao();
		
		knowledgePointResourceDao.deleteResource(resourceId);
		
		request.getRequestDispatcher("knowledgePointResourceGetServlet?courseId="
		+courseId+"&knowledgePointId="+knowledgePointId+"&permission="+permission).forward(request, response);;
		
	}



}
