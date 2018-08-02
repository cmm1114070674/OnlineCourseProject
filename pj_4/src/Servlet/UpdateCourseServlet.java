package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateCourseServlet")
public class UpdateCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
        int isExisted = 1;
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String coursename = request.getParameter("coursename");
        String introduction = request.getParameter("introduction");
        String path = request.getParameter("path");
        request.getRequestDispatcher("openCourse.jsp?isExisted="+isExisted+"&courseId="+courseId+
                "&coursename="+coursename+"&introduction="+introduction+"&path="+path).forward(request, response);
    }
}
