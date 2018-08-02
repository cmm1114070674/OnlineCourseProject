package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MyCourseServlet")
public class MyCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int choose = Integer.parseInt(request.getParameter("choose"));
        int permission = 0;
        if(choose == 1)
            permission = 2;
        if(choose == 2)
            permission = 3;
        request.getServletContext().getRequestDispatcher("/courseInformation.jsp?courseId="+courseId+"&permission="+permission).forward(
                request, response);
    }
}
