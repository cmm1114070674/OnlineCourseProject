package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UploadFileDao;


@WebServlet("/resourceDownloadServlet")
public class ResourceDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UploadFileDao dao = new UploadFileDao();
		
		int resourceId = Integer.parseInt(request.getParameter("resourceId"));
		System.out.println(resourceId);
		
		String resourcePath = dao.getResourcePath(resourceId);
		String resourceName = dao.getResourceName(resourceId);
		System.out.println(resourcePath);
		System.out.println(resourceName);
		
		InputStream inputStream = new FileInputStream(resourcePath);
		
		response.setContentType("multipart/form-data");
		
		response.setHeader("Content-Disposition", "attachment;filename="+resourceName);
		
        try{
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while((len = inputStream.read(b)) > 0){
                out.write(b,0,len);
            }
            out.flush();
            out.close();
            inputStream.close();
        }catch (IOException ioe){
           ioe.printStackTrace();
        }
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
