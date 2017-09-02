package web;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.*;

public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = null;
        String user = request.getParameter("uuid");
        String password = request.getParameter("passwd");
        DatabaseManager db = new DatabaseManager(user,password);
        if (db.loginDB() ==null)
        {
            String infor = "user or password mistake";
            request.setAttribute("styles",infor);
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request,response);
        }
        else
        {
            session = request.getSession(true);
            if (!session.isNew())
            {
                session.invalidate();
                session = request.getSession(true);
            }
            session.setMaxInactiveInterval(300);
            session.setAttribute("uuid",user);
            session.setAttribute("passwd",password);
            response.sendRedirect("index.html");
            
        }  
    }
}