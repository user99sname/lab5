package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");

        if (username == null || username.equals("")) {
            response.sendRedirect("login");
            return;
        }

        String operation = request.getParameter("operation");
       
        if (operation != null && operation.equals("logout")) {
            session.invalidate();
            session = request.getSession();
        }


        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        return; 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}