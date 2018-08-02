package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CourseUnit;
import Bean.KnowledgePointBean;
import Dao.DaoImpl.CourseUnitDao;
import Dao.DaoImpl.KnowledgePointDao;


@WebServlet("/returnAllUnitServlet")
public class ReturnAllUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int permission =  Integer.parseInt(request.getParameter("permission"));
		System.out.println("all unit servlet permission:"+permission);
	
		
		CourseUnitDao dao = new CourseUnitDao();
		List<CourseUnit> units = dao.getAllUnits(courseId);
		Map<Integer,CourseUnit> allUnits = dao.getAllUnitsByOrder(units);
		
		
		KnowledgePointDao knowledgePointDao = new KnowledgePointDao();
		Map<Integer,List<KnowledgePointBean>> knowledgePoints = 
				knowledgePointDao.getAllKnowledgePoints(units);
	    Map<Integer,Long> knowledgePointsCount = knowledgePointDao.getKnowledgePointsCount(units);
		System.out.println(knowledgePointsCount);
	    
	    request.setAttribute("knowledgePointsCount", knowledgePointsCount);
		
		request.setAttribute("allKnowledgePoints", knowledgePoints);
		
		request.setAttribute("allUnits", allUnits);
		
		request.setAttribute("unitBegin", units.size()+1);
		
		request.getRequestDispatcher("courseKnowledgePoint.jsp?courseId="+courseId+"&permission="+permission).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
