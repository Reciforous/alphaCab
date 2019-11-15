package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*  TODO: Move to git

    WHATS NEW:
        Fixes login
 */
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(Functions.authenticateRoute(request, "admin")){
            Functions.redirect(response, "admin/home");
        }
        else if(Functions.authenticateRoute(request, "driver")){
            Functions.redirect(response, "driver/home");
        }
        else if(Functions.authenticateRoute(request, "customer")){
            Functions.redirect(response, "home");
        }
        else{
            try{
                request.getRequestDispatcher("/views/login.html").forward(request, response);
            } catch(ServletException e){
                response.getWriter().print("There was an error handling your request, Please go back!<br>" + e.getMessage());
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email);
        System.out.println(password);

        try{
            User user = new User(email, password);
            ArrayList<Cookie> cookies = user.login();

            for(Cookie cookie : cookies){
                System.out.println(cookie.getValue());
            }

            if(!cookies.isEmpty()){
                for(Cookie cookie : cookies){
                    if(cookie != null){
                        response.addCookie(cookie);
                    }
                }
                if(user.type.equals("customer")){
                    // response.sendRedirect("/home");
                    Functions.redirect(response, "home");
                }
                else if(user.type.equals("driver")){
                    // response.sendRedirect("/driver/home");
                    Functions.redirect(response, "driver/home");
                }
                else{
                     // response.sendRedirect("/admin/home");
                    Functions.redirect(response, "admin/home");
                }
            }
            else{
                response.getWriter().print("Wrong email or password");
            }
        }
        catch (NullPointerException e){
            response.getWriter().print("Wrong email or password");
            e.printStackTrace();
        }
    }
}
