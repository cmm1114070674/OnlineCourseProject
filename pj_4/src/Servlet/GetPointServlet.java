package Servlet;

import Bean.FeedbackBean;
import Dao.DaoImpl.FeedbackDaoImpl;
import Service.FeedbackGet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetPointServlet")
public class GetPointServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
        int point = Integer.parseInt(request.getParameter("point"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int permission = Integer.parseInt(request.getParameter("permission"));
        int homeworkId = Integer.parseInt(request.getParameter("homeworkId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int answerId = Integer.parseInt(request.getParameter("answerId"));
        FeedbackBean feedbackBean = new FeedbackBean(0, "well", point, true, answerId, homeworkId, studentId);
        FeedbackDaoImpl.feedback_insert(feedbackBean);
        request.getRequestDispatcher("feedback.jsp?courseId="+courseId+"&homeworkId="+homeworkId
                +"&permission="+permission).forward(request, response);

    }
}
