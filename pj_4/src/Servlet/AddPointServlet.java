package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Bean.KnowledgePointBean;
import Dao.DaoImpl.KnowledgePointDao;


@WebServlet("/addPointServlet")
public class AddPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		KnowledgePointDao dao = new KnowledgePointDao();
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int unitId = Integer.parseInt(request.getParameter("courseUnitId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		System.out.println(unitId);
		System.out.println("add point permission:"+permission);

		request.setCharacterEncoding("UTF-8");
		
		  ServletFileUpload upload = getServletFileUpload();
		  
		  List<KnowledgePointBean> points = new ArrayList<>();
		  
		  try {
			List<FileItem> items = upload.parseRequest(request);
			
			buildKnowledgePointBeans(unitId, points, items);
			
			System.out.println(points);
		
			dao.saveBasicInfo(points);
		
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
            response.sendRedirect("courseKnowledgePoint.jsp?courseId="+courseId+"&permission="+permission);		
	}

	public void buildKnowledgePointBeans(int unitId, List<KnowledgePointBean> points, List<FileItem> items)
			throws UnsupportedEncodingException {
		for(FileItem item : items) {
			String fieldName = item.getFieldName();
			int knowledgePointIndex = Integer.parseInt(fieldName.substring(10));
			String knowledgePoint = item.getString("UTF-8");
			
			KnowledgePointBean point = new 
					KnowledgePointBean(unitId, knowledgePointIndex, knowledgePoint);
			points.add(point);
		}
	}

	
	public ServletFileUpload getServletFileUpload() {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
	      ServletFileUpload upload = new ServletFileUpload(factory);
		  
return upload;
}

}
