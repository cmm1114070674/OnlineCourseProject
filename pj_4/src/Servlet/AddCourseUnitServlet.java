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

import Bean.CourseUnit;
import Dao.DaoImpl.CourseUnitDao;



@WebServlet("/addCourseUnitServlet")
public class AddCourseUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		CourseUnitDao dao = new CourseUnitDao();
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		System.out.println(courseId);
		System.out.println("add unit permission:"+permission);
		
		request.setCharacterEncoding("UTF-8");
		
		ServletFileUpload upload = getServletFileUpload();
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			List<CourseUnit> units = new ArrayList<>();
			
			buildListUnits(courseId, items, units);
			
			System.out.println(units);
			
			dao.save(units);
			
			request.getRequestDispatcher("courseKnowledgePoint.jsp?courseId="+courseId+"&permission="+permission).forward(request, response);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
	}

	public void buildListUnits(int courseId, List<FileItem> items, List<CourseUnit> units) {
		for(FileItem item : items) {
			if(item.isFormField())
				try {
					String fieldName = item.getFieldName();
					int courseUnitIndex = Integer.parseInt(fieldName.substring(4));
					String courseUnitName = item.getString("UTF-8");
					
					CourseUnit unit = new CourseUnit(courseId, courseUnitName, courseUnitIndex);
					units.add(unit);
					
				} catch (Exception e) {
					System.out.println("error");
					e.printStackTrace();
				}
		}
	}

	public ServletFileUpload getServletFileUpload() {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		  //create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
return upload;
}
}
