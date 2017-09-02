package web;
import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Delete extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws
	ServletException,IOException
	{
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
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
			String id = request.getParameter("stdNumber");
			String infor = null;
			if(db.judge(id))
			{
				if (db.delete(id))
				{
					infor="删除成功";
				}
				else
				{
					infor="删除失败";
				}
			}
			else
			{
				infor="学号:"+id+"  不存在";
			}
			request.setAttribute("styles",infor);
			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request,response);
		}	
	}
}