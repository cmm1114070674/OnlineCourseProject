package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoImpl.KnowledgePointDao;


@WebServlet("/alterKnowledgePointServlet")
public class AlterKnowledgePointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		KnowledgePointDao dao = new KnowledgePointDao();
		
		request.setCharacterEncoding("UTF-8");

		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		
		int knowledgePointId =Integer.parseInt(request.getParameter("knowledgePointId")) ;
		String alteredPoint = request.getParameter("alteredPoint");
		
		dao.alterKnowledgePoint(knowledgePointId, alteredPoint);
		
		response.sendRedirect("courseKnowledgePoint.jsp?courseId="+courseId+"&permission="+permission);
		
		
	}

}
