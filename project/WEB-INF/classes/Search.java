package web;
import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class Search extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws
	ServletException,IOException
	{
		ArrayList al = new ArrayList();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		if (session.isNew())
		{
			session.invalidate();
			response.sendRedirect("login.html");
		}
		else
		{
			String user = (String)session.getAttribute("uuid");
			String passwd = (String)session.getAttribute("passwd");
			DatabaseManager db = new DatabaseManager(user,passwd);
			String name = request.getParameter("name");
			String id = request.getParameter("stdNumber");
			al=db.search(name,id);
			request.setAttribute("arr",al);
			RequestDispatcher view = request.getRequestDispatcher("search.jsp");
			view.forward(request,response);
		}
	}
}