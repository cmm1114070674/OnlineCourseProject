package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DaoImpl.FloorDaoImpl;
import Dao.DaoImpl.PostDaoImpl;

@WebServlet("/addReplyServlet")
public class AddReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		String replyTo = request.getParameter("replyTo");
		String replyContent = request.getParameter("replyContent");
		
		HttpSession session = request.getSession();
		String replier = session.getAttribute("username").toString();
		
		FloorDaoImpl dao = new FloorDaoImpl();
		dao.addReply(postId, replier, replyContent, replyTo);
		
		PostDaoImpl dao2 = new PostDaoImpl();
		dao2.updateLatestReplyTime(postId);
		
		response.sendRedirect("discussPost.jsp?postId="+postId+"&courseId="+courseId+"&permission="+permission);
	}

}
