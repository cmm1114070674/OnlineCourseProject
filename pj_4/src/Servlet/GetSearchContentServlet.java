package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getSearchContentServlet")
public class GetSearchContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String searchContent = request.getParameter("searchContent");
		System.out.println(searchContent);
		request.getRequestDispatcher("searchpage.jsp?word="+searchContent).forward(request, response);
		
		
	}

}
