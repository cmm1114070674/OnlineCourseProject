package Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import Dao.DaoImpl.CourseDaoImpl;


@WebServlet("/alterCoursePictureServlet")
public class AlterCoursePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int courseId = Integer.parseInt(request.getParameter("courseId"));
		System.out.println(courseId);

		request.setCharacterEncoding("UTF-8");

		ServletFileUpload upload = getServletFileUpload();
		
		CourseDaoImpl  dao = new CourseDaoImpl();

		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					InputStream inputStream = item.getInputStream();
					String fileName = item.getName();
					String extName = fileName.substring(fileName.lastIndexOf("."));
					String filePath = courseId + extName;
					System.out.println(fileName);
					System.out.println(extName);
					System.out.println(filePath);

					filePath = getServletContext().getRealPath("CoursePictureFromDatabase") + "\\" + filePath;
					System.out.println(filePath);

					OutputStream out = new FileOutputStream(filePath);

					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = inputStream.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					inputStream.close();
					out.close();
					dao.alterPicturePath(courseId, filePath);
					dao.alterPictureRelativePath(courseId, extName);
					dao.alterPictureBlob(courseId, filePath);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("course.jsp?courseId="+courseId);
		
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
