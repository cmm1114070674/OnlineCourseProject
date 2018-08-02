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

import Bean.KnowledgePointResource;
import Bean.KnowledgePointResourceSwfBean;
import Dao.DaoImpl.KnowledgePointResourceDao;
import Dao.DaoImpl.KnowledgePointResourceSwfBeanDao;
import Service.DocConverter;
import Service.InvalidExtNameException;


@WebServlet("/knowledgePointResourceUpload")
public class KnowledgePointResourceUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String FILE_PATH = "video";

	KnowledgePointResourceDao dao = new KnowledgePointResourceDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println();
		System.out.println("��������/knowledgePointResourceUploadҳ������");
		
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		int knowledgePointId = Integer.parseInt(request.getParameter("knowledgePointId"));

		System.out.println("knowledge point resource :"+permission);
		
		String path = null;

		ServletFileUpload upload = getServletFileUpload();

		try {
			Map<String, FileItem> uploadFiles = new HashMap<>();
			List<FileItem> items = upload.parseRequest(request);
			List<KnowledgePointResource> beans = buildKnowledgePointResourceBeans(items, uploadFiles, knowledgePointId);
			validateExtName(beans);
			upload(uploadFiles);
			List<Integer> keys = saveBeans(beans);
			List<KnowledgePointResource> beansFromDatabase = getBeansFromDatabase(keys);
			List<KnowledgePointResourceSwfBean> swfs = buildKnowledgePointResourceSwfBeans(beansFromDatabase);
			convertDoc2Swf(beansFromDatabase);
			saveSwfs(swfs);
			
			response.sendRedirect("knowledgePointResourceGetServlet?courseId="
			+ courseId+"&knowledgePointId="+knowledgePointId+"&permission="+permission);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("courseKnowledgePoint.jsp?courseId=" 
			+ courseId+"&knowledgePointId="+knowledgePointId+"&permission="+permission).forward(request, response);
		}

	}


	private List<KnowledgePointResource> getBeansFromDatabase(List<Integer> keys) {
		List<KnowledgePointResource> beansFromDatabase = new ArrayList<>();
		beansFromDatabase = dao.getBeanListByKeys(keys);
		return beansFromDatabase;
	}


	private void saveSwfs(List<KnowledgePointResourceSwfBean> swfs) {
		KnowledgePointResourceSwfBeanDao dao = new KnowledgePointResourceSwfBeanDao();
		for(KnowledgePointResourceSwfBean swf : swfs) {
			dao.saveSwfs(swf);
		}
	}

	private void convertDoc2Swf(List<KnowledgePointResource> beans) {
		for(KnowledgePointResource bean : beans) {
			String extName = bean.getResourceExtendName();
			if(extName.equals("ppt") || extName.equals("pptx")) {
				String fileString = bean.getResourcePath();
				DocConverter d = new DocConverter(fileString);
				d.conver();
				System.err.println(d.getswfPath());
			}
		}
	}

	private List<KnowledgePointResourceSwfBean> buildKnowledgePointResourceSwfBeans(
			List<KnowledgePointResource> beans) {
		List<KnowledgePointResourceSwfBean> swfs = new ArrayList<>();
		
		for(KnowledgePointResource bean : beans) {
			String extName = bean.getResourceExtendName();
			if(extName.equals("ppt") || extName.equals("pptx")) {
				String resourcePath = getResourceSwfPath(bean);
				String resourceRelativePath = getresourceRelativeSwfPath(bean);
				KnowledgePointResourceSwfBean swf = new KnowledgePointResourceSwfBean(bean.getResourceId(),bean.getKnowledgePointId(),
						resourcePath, resourceRelativePath, bean.getResourceName(), bean.getResourceDesc());
				swfs.add(swf);
			}
		}
		return swfs;
	}

	private String getresourceRelativeSwfPath(KnowledgePointResource bean) {
		String originResourcePath = bean.getResourcePath();
		String resourcePath1 = originResourcePath.replaceAll("\\\\", "/");
		String 	resourcePath = resourcePath1.substring(0, resourcePath1.lastIndexOf("."))
				+".swf";
		String swfpath = FILE_PATH + resourcePath.substring(resourcePath.lastIndexOf("/"));
		System.err.println(swfpath);

		return swfpath;
	
	}

	private String getResourceSwfPath(KnowledgePointResource bean) {
		String originResourcePath = bean.getResourcePath();
		String resourcePath = originResourcePath.substring(0,originResourcePath.lastIndexOf(".") ) 
				+ ".swf";
		return resourcePath;
	}

	private List<KnowledgePointResource> buildKnowledgePointResourceBeans(List<FileItem> items,
			Map<String, FileItem> uploadFiles, int knowledgePointId) {

		List<KnowledgePointResource> beans = new ArrayList<>();


		Map<String, String> descs = new HashMap<>();

		for (FileItem item : items) {
			if (item.isFormField())
				try {
					descs.put(item.getFieldName(), item.getString("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					System.out.println("error");
					e.printStackTrace();
				}
		}

		for (FileItem item : items) {
			if (!item.isFormField()) {
				String fieldName = item.getFieldName();
				String index = fieldName.substring(fieldName.length() - 1);

				String fileName = item.getName();
				String resourceName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				
				
				System.out.println(resourceName);
				String resourceExtendName = fileName.substring(fileName.lastIndexOf(".") + 1);
				System.out.println(resourceExtendName);

				String resourceDesc = descs.get("dsc" + index);

				String resourcePath = getFilePath(fileName);
				System.out.println(resourcePath);

				String resourceRelativePath = getResourceRelativePath(resourcePath);
				System.out.println(resourceRelativePath);

				KnowledgePointResource knowledgePointResource = new KnowledgePointResource(knowledgePointId,
						resourceName, resourcePath, resourceRelativePath, resourceExtendName, resourceDesc);
				beans.add(knowledgePointResource);

				uploadFiles.put(resourcePath, item);
			}
		}
		System.out.println(beans);
		return beans;
	}

	private String getResourceRelativePath(String resourcePath) {
		String resourceSavedName = getResourceSavedName(resourcePath);

		String resourceRelativePath = FILE_PATH + "\\" + resourceSavedName;
		return resourceRelativePath;
	}

	private String getResourceSavedName(String resourcePath) {
		String resourceSavedName = resourcePath.substring(resourcePath.lastIndexOf("\\") + 1);
		return resourceSavedName;
	}

	private List<Integer> saveBeans(List<KnowledgePointResource> beans) {
		List<Integer> keys = dao.saveWithKeysReturned(beans);
		return keys;
	}


	private void upload(Map<String, FileItem> uploadFiles) throws IOException {
		for (Map.Entry<String, FileItem> uploadFile : uploadFiles.entrySet()) {
			String filePath = uploadFile.getKey();
			FileItem item = uploadFile.getValue();

			upload(filePath, item.getInputStream());
		}

	}

	private void upload(String filePath, InputStream inputStream) throws IOException {
		OutputStream out = new FileOutputStream(filePath);

		byte[] buffer = new byte[1024];
		int len = 0;

		while ((len = inputStream.read(buffer)) != -1) {
			out.write(buffer, 0, len);
			out.flush();
		}
		inputStream.close();
		out.close();
	}

	private void validateExtName(List<KnowledgePointResource> beans) {
		String exts = "pptx,ppt,mp4";
		List<String> extList = Arrays.asList(exts.split(","));

		for (KnowledgePointResource bean : beans) {
			String extName = bean.getResourceExtendName();

			if (!extList.contains(extName)) {
				throw new InvalidExtNameException(bean.getResourceName() + "�ļ�����չ�����Ϸ�");
			
			}
		}
	}

	/**
	 * 
	 * @param items
	 * @param uploadFiles
	 * @return
	 */


	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private String getFilePath(String fileName) {
		String extName = fileName.substring(fileName.lastIndexOf("."));
		Random random = new Random();
		int randomNumber = random.nextInt(100000);

//		String filePath = "C:\\Users\\qiu yongting\\eclipse-workspace\\pj\\WebContent\\video" + "\\" + System.currentTimeMillis() + randomNumber
//				+ extName;
		
		String filePath = getServletContext().getRealPath(FILE_PATH) + "\\" + System.currentTimeMillis() + randomNumber
				+ extName;
		return filePath;
	}

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
