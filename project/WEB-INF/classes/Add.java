package web;
import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class Add extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException
	{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession(true);
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
			String age = request.getParameter("age");
			String id = request.getParameter("stdNumber");
			String major = request.getParameter("major");
			String infor = null;
			if (!db.judge(id))
			{
				if (db.add(name,age,id,major))
				{
					infor="添加成功";
				}
				else
				{
					infor="添加失败";
				}
			}
			else
			{
				infor="学号:"+id+"  已经存在";
			}
			request.setAttribute("styles",infor);
			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request,response);
		}
		
	}
}