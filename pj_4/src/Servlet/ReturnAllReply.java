package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.FloorBean;
import Bean.PostBean;
import Dao.DaoImpl.FloorDaoImpl;
import Dao.DaoImpl.PostDaoImpl;

@WebServlet("/returnAllReply")
public class ReturnAllReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int postId = Integer.parseInt(request.getParameter("postId"));
		int permission = Integer.parseInt(request.getParameter("permission"));
		System.out.println("postId"+postId);
		
		FloorDaoImpl dao = new FloorDaoImpl();
		List<FloorBean> floors = dao.returnAllReply(postId);
		System.out.println("floors:"+floors);
		
		request.setAttribute("floors", floors);
		
		PostDaoImpl dao2 = new PostDaoImpl();
		PostBean post = dao2.getPostBeanByPostId(postId);
		System.out.println("post:"+post);
		
		request.setAttribute("post", post);
		String posterName = dao2.getPosterNameByPostId(post.getPosterId());
		System.out.println(posterName);
        
		request.setAttribute("posterName", posterName);
		
		request.getRequestDispatcher("discussPost.jsp?courseId="+courseId+"&postId="+postId+"&permission="+permission).forward(request, response);
	}

	
}
