/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Controllers;

import com.org.Helpers.Message;
import com.org.Models.Customer;
import com.org.Models.Driver;
import com.org.Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shaaik
 */
public class RegisterCustomer extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.getRequestDispatcher("/views/customer/register.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, Please go back!\n" + e.getMessage());
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String type = "customer";
        
        ArrayList<Customer> customers = Customer.getAllCustomers();
        
        Integer new_id = customers.get(customers.size() - 1).id + 1;

        Customer customer = new Customer(name, address);
        Message message = customer.add();
        
        User user = new User(email, password, type, new_id);
        message = user.add();
        response.sendRedirect("/alphaCab/");
    }
}
