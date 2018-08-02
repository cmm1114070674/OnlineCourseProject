package Servlet;

import Bean.HomeworkBean;
import Dao.DaoImpl.HomeworkDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateHomeworkServlet")
public class UpdateHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int permission = Integer.parseInt(request.getParameter("permission"));
        int homeworkId = Integer.parseInt(request.getParameter("homeworkId"));
        HomeworkBean homeworkBean = new HomeworkBean(homeworkId, topic, content, courseId);
        HomeworkDaoImpl.homework_update(homeworkBean);
        request.getRequestDispatcher("homework.jsp?courseId="+courseId+"&permission"+permission).forward(request, response);
    }
}
