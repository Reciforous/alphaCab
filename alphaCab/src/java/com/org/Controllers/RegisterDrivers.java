/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Controllers;

import com.org.Helpers.Configs;
import com.org.Helpers.Message;
import com.org.Models.Demand;
import com.org.Models.Driver;
import com.org.Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shaaik
 */
public class RegisterDrivers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.getRequestDispatcher("/views/admin/drivers/register.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, Please go back!<br>" + e.getMessage());
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String name = request.getParameter("name");
        String registration = request.getParameter("registration");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String type = "driver";

        Driver driver = new Driver(registration, name);
        Message message = driver.add();
        User user = new User(email, password, type, registration);
        message = user.add();
        response.sendRedirect(Configs.url_prefix + "admin/drivers");
    }
}
