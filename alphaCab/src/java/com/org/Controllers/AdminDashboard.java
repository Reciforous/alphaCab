package com.org.Controllers;

import com.org.Helpers.Functions;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    TODO: Move this to git

    WHATS NEW:
        - Simplified route authentication
 */
public class AdminDashboard extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!Functions.authenticateRoute(request, "admin")){
            Functions.redirect(response, "login");
        }
        else{
            try{
                request.getRequestDispatcher("/views/admin/home.html").forward(request, response);
            }
            catch(ServletException e){
                response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
            }
        }
    }
}
