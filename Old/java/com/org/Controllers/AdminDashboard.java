package com.org.Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminDashboard extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Cookie[] cookies = request.getCookies();
            Cookie auth_cookie = null;

            for(Cookie cookie : cookies){
                if(cookie.getName().equals("Authentication")){
                    auth_cookie = cookie;
                    break;
                }
            }

            if(auth_cookie == null){
                request.getRequestDispatcher("/login").forward(request, response);
            }
            else {
                request.getRequestDispatcher("/views/admin/home.html").forward(request, response);
            }
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!\n" + e.getMessage());
        }
    }
}
