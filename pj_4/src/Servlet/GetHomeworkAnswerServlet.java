package Servlet;

import Bean.HomeworkAnswerBean;
import Dao.DaoImpl.HomeworkanswerDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetHomeworkAnswerServlet")
public class GetHomeworkAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
        String answer = request.getParameter("answer");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int permission = Integer.parseInt(request.getParameter("permission"));
        int homeworkId = Integer.parseInt(request.getParameter("homeworkId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        HomeworkAnswerBean homeworkAnswerBean = new HomeworkAnswerBean(0, answer, true, homeworkId, studentId);
        HomeworkanswerDaoImpl.homeworkanswer_insert(homeworkAnswerBean);
        request.getRequestDispatcher("homework.jsp?courseId="+courseId+"&permission="+permission).forward(request, response);

    }
}
