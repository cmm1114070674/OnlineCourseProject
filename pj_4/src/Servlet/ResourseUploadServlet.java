package Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Bean.FileUploadBean;
import Dao.UploadFileDao;
import sun.security.action.GetBooleanSecurityPropertyAction;


@WebServlet("/resourceUploadServlet")
public class ResourseUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	private static final String FILE_PATH = "/WEB-INF/courseResources";
	private static final String FILE_PATH = "courseResources";
	
	private UploadFileDao dao = new UploadFileDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		System.out.println(courseId);
		System.out.println("resource upload permission:"+permission);
		
		String path = null;

        ServletFileUpload upload = getServletFileUpload();
		  
		  try {
			  Map<String,FileItem> uploadFiles = new HashMap<>();
			  List<FileItem> items = upload.parseRequest(request);
			  
			  List<FileUploadBean> beans = buildFileUploadBeans(items,uploadFiles,courseId);
			
			  upload(uploadFiles);
			  saveBeans(beans);
			  
			  path = "courseResource.jsp?courseId="+courseId+"&permission="+permission;
			  request.getRequestDispatcher(path).forward(request, response);
		  }
		  catch(Exception e) {
			  request.getRequestDispatcher("courseResource.jsp?courseId="+courseId+"&permission="+permission).forward(request, response);
			  e.printStackTrace();
		  }
		  
		  
	
	}


	private void saveBeans(List<FileUploadBean> beans) {
		System.out.println("qwe");
		dao.save(beans);
		System.out.println("123");
	}


	private void upload(Map<String, FileItem> uploadFiles) throws IOException {
		for(Map.Entry<String, FileItem> uploadFile : uploadFiles.entrySet()) {
			String filePath = uploadFile.getKey();
			FileItem item = uploadFile.getValue();
			
			upload(filePath,item.getInputStream());
		}
		
	}


	private void upload(String filePath, InputStream inputStream) throws IOException {
		OutputStream out = new FileOutputStream(filePath);
		
		byte[] buffer = new byte[1024];
		int len = 0;
		
		while((len = inputStream.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		inputStream.close();
		out.close();
		
		System.out.println(filePath);
	}


//	private void validateExtName(List<FileUploadBean> beans) {
//		String exts = "pptx,docx,doc";
//		List<String> extList = Arrays.asList(exts.split(","));
//		
//		for(FileUploadBean bean : beans) {
//			String fileName = bean.getFileName();
//			String extName = fileName.substring(fileName.lastIndexOf(".")+1);
//			
//			if(!extList.contains(extName)) {
//				throw new InvalidExtNameException(fileName+"�ļ�����չ�����Ϸ�");
//			}
//		}
//		
//	}


	/**
	 * 
	 * @param items
	 * @param uploadFiles
	 * @return
	 */
	private List<FileUploadBean> buildFileUploadBeans(List<FileItem> items,
			Map<String,FileItem> uploadFiles,int courseId) {
		List<FileUploadBean> beans = new ArrayList<>();
		
		Map<String,String> descs = new HashMap<>();
		
		for(FileItem item : items) {
			if(item.isFormField())
				try {
					descs.put(item.getFieldName(),item.getString("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					System.out.println("error");
					e.printStackTrace();
				}
		}
		for(FileItem item : items) {
			if(!item.isFormField()) {
				String fieldName = item.getFieldName();
				String index = fieldName.substring(fieldName.length()-1);
				
				String fileName = item.getName();
				String resourceName = fileName.substring(fileName.lastIndexOf("\\")+1);
				
				String resourceDesc = descs.get("dsc"+index);
				String resourcePath = getFilePath(fileName);
				
				FileUploadBean bean = new FileUploadBean(courseId, resourcePath, resourceName, resourceDesc);
				beans.add(bean);
				
				uploadFiles.put(resourcePath,item);
				
			}
			
		}
		System.out.println(beans);
		return beans;
	}


	/**
	 * @param fileName
	 * @return
	 */
	private String getFilePath(String fileName) {
		String extName = fileName.substring(fileName.lastIndexOf("."));
		Random random = new Random();
		int randomNumber = random.nextInt(100000);
		
		String filePath = getServletContext().getRealPath(FILE_PATH)+"\\"
		+System.currentTimeMillis()+randomNumber+extName;
		System.out.println(filePath);
		return filePath;
	}

/**
 * @return
 */
	public ServletFileUpload getServletFileUpload() {
				
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				  factory.setSizeThreshold(1024 * 500);
				  File tempDirectory = new File("C:\\Users\\qiu yongting\\files\\tempFile");
				  factory.setRepository(tempDirectory);
			      ServletFileUpload upload = new ServletFileUpload(factory);
				  upload.setSizeMax(1024 * 1024 * 1024 * 5);
				  upload.setFileSizeMax(1024 * 1024 * 1024 * 5);
		return upload;
	}

}
