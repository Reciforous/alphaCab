package com.org.Controllers;

import com.org.Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Login extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            request.getRequestDispatcher("/views/login.html").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, Please go back!\n" + e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email);
        System.out.println(password);

        User user = new User(email, password);
        Cookie cookie = user.login();

        if(cookie != null){
            response.addCookie(cookie);
            if(user.type.equals("customer")) {
                response.sendRedirect("/alphaCab/home");
            }
            else if(user.type.equals("admin")) {
                response.sendRedirect("/alphaCab/admin/home");
            }
            else{
                response.sendRedirect("/alphaCab/driver/home");
            }

        }
        else{
            response.getWriter().print("Wrong email or password");
        }
    }
}
