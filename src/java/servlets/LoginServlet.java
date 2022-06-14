package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession();    
        
   
        if(request.getParameter("logout") != null) {
            request.setAttribute("You have succefully logged out.", true);
            session.invalidate();
        }
        else {
            if(session.getAttribute("username") != null) {
                response.sendRedirect("home");
                return;
            }
        }
       
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        
        HttpSession session = request.getSession();
        
 
        User user = new AccountService().login(username, password);
        
      
         if (username == null || username.equals("") || password == null || password.equals("")) {
            request.setAttribute("message", "Username or password is missing.");
        } else {
            AccountService account = new AccountService();


            if (user != null) {
                request.getSession().setAttribute("username", username);

                response.sendRedirect("home");
                return;
            } else {
                request.setAttribute("username", username);
                request.setAttribute("message", "Failed authentication.");
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
    }
}