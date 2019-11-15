/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.org.Models.Journey;

/**
 *
 * @author nasrusaeid
 */
public class CreateJourney extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            request.getRequestDispatcher("").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request please go back!\n" + e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        Cookie[] cookies = request.getCookies();
        Cookie id_cookie = Functions.getCookie(cookies, "id");
        
        try{
            assert id_cookie != null;
            int id = Integer.parseInt(id_cookie.getValue());
            String destination = (String) request.getParameter("destination");
            int distance = Integer.parseInt((String) request.getParameter("distance"));
            String registration = (String) request.getParameter("registration");

            Journey journey = new Journey(id, destination, distance, registration);
            Message message = journey.add();

            request.setAttribute("message", message);
            request.getRequestDispatcher("/views/customer/create-journey.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request please go back!<br>" + e.getMessage());
        }
    }
}
