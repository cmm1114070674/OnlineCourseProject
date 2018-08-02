package Servlet;

import Bean.CourseBean;
import Dao.CourseDao;
import Dao.DaoImpl.CourseDaoImpl;
import Service.CourseGet;
import Service.UserGet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet("/openCourseToShow")
public class OpenCourseToShow extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CourseDaoImpl courseDao = new CourseDaoImpl();

    private static final String UPLOAD_DIRECTORY = "CoursePictureFromDatabase";
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
        String coursename = new String();
        String introduction = new String();

        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);

        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = getUpload(factory);

//        String uploadPath = request.getServletContext().getRealPath(UPLOAD_DIRECTORY) + "\\";

//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }

        String filePath = new String();
        String pictureRelativePath =  new String();

        try {

            @SuppressWarnings("unchecked")
            List<FileItem> formItems  = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {

                for (FileItem item : formItems) {

                    if (!item.isFormField()) {
//                        fileName = new File(item.getName()).getName();
//                        filePath = uploadPath + File.separator + fileName;
//                        File storeFile = new File(filePath);
//
//                        System.out.println(filePath);
//
//                        item.write(storeFile);
                        
    					InputStream inputStream = item.getInputStream();
    					String fileName = item.getName();
    					String extName = fileName.substring(fileName.lastIndexOf("."));
    					
    					Random random = new Random();
    					int randomNumber = random.nextInt(100000);
    					
    				 filePath = System.currentTimeMillis()+randomNumber + extName;
    					System.out.println(fileName);
    					System.out.println(extName);
    					System.out.println(filePath);

    					pictureRelativePath = "CoursePictureFromDatabase" + "\\" + filePath;
    					
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
                        
                    }
                    if (item.isFormField()){
                        if(item.getFieldName().equals("coursename"))
                            coursename = item.getString("utf-8");
                        if(item.getFieldName().equals("introduction"))
                            introduction = item.getString("utf-8");
                    }
                }
            }
        } catch (Exception ex) {
        }
        Date date = new Date();

        CourseBean courseBean = new CourseBean(coursename, introduction, null, filePath,pictureRelativePath, date, 0);
        CourseDaoImpl.course_insert(courseBean);

        ArrayList<CourseBean> courseBeans = CourseGet.getCourseListAll();
        int courseId = 0;
        for(CourseBean i:courseBeans){
            if(i.getName().equals(coursename))
                courseId = i.getId();
        }

        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();

        CourseDaoImpl.teacher_course_insert(UserGet.getUserByUsername(username).getId(), courseId);
        System.out.println(UserGet.getUserByUsername(username).getId());
        System.out.println(courseId);

        int permission = 3;


        request.getRequestDispatcher("courseInformation.jsp?courseId="+courseId+"&permission="+permission).forward(
                request, response);



    }

	public ServletFileUpload getUpload(DiskFileItemFactory factory) {
		ServletFileUpload upload = new ServletFileUpload(factory);


        upload.setFileSizeMax(MAX_FILE_SIZE);


        upload.setSizeMax(MAX_REQUEST_SIZE);


        upload.setHeaderEncoding("UTF-8");
		return upload;
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doPost(request,response);

    }
}
