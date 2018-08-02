package Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.star.system.SystemShellExecuteException;

import Bean.KnowledgePointResource;
import Bean.KnowledgePointResourceSwfBean;
import Dao.DaoImpl.KnowledgePointResourceDao;
import Dao.DaoImpl.KnowledgePointResourceSwfBeanDao;


/**
 * Servlet implementation class KnowledgePointResourceGetServlet
 */
@WebServlet("/knowledgePointResourceGetServlet")
public class KnowledgePointResourceGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static KnowledgePointResourceDao dao = new KnowledgePointResourceDao();
	
	private static KnowledgePointResourceSwfBeanDao swfDao = 
			new KnowledgePointResourceSwfBeanDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int knowledgePointId = Integer.parseInt(request.getParameter("knowledgePointId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		System.out.println(courseId);
		System.out.println(knowledgePointId);
		System.out.println("get knowledge point resource"+permission);
		
		List<KnowledgePointResource> videos = 
				dao.getKnowledgePointVideos(knowledgePointId);
		List<KnowledgePointResourceSwfBean> swfs = swfDao.getKnowledgePointPpts(knowledgePointId);
		System.out.println(videos);
		System.out.println(swfs);
		
		request.setAttribute("videos",videos);
		
		request.setAttribute("ppts",swfs);
		request.getRequestDispatcher("courseKnowledgePoint.jsp?courseId="+courseId+"&knowledgePointId="+knowledgePointId
				+"&permission="+permission)
		.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
