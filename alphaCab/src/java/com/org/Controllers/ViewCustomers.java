/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Controllers;

import com.org.Models.Customer;
import com.org.Models.Driver;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shaaik
 */
public class ViewCustomers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if (request.getParameterMap().containsKey("customer")) {
                String customerID = request.getParameter("customer");
                Customer customer = new Customer(parseInt(customerID));
                customer.get();
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/views/admin/customers/customer.jsp").forward(request, response);

            } else {
                ArrayList<Customer> customers = Customer.getAllCustomers();
                request.setAttribute("customers", customers);
                request.getRequestDispatcher("/views/admin/customers/customers.jsp").forward(request, response);
            }
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, Please go back!\n" + e.getMessage());
        }
    }

}
