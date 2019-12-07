package com.org.Controllers;

import com.org.Helpers.Configs;
import com.org.Helpers.Functions;
import com.org.Models.Customer;
import com.org.Models.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewDriverJourney extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!Functions.authenticateRoute(request, "driver")){
            Functions.redirect(response, "login");
        }
        else{
            try{
                Integer id = Integer.parseInt(request.getPathInfo().substring(1));
                Journey journey = new Journey(id);
                journey.get();

                Customer customer = new Customer(journey.customer_id);
                customer.get();

                request.setAttribute("journey", journey);
                request.setAttribute("customer", customer);

                try{
                    request.getRequestDispatcher("/views/driver/view-journey.jsp").forward(request, response);
                }
                catch(ServletException e){
                    response.getWriter().print("There was an error handling your ");
                }
            }
            catch(NumberFormatException e){
                response.getWriter().print("<!DOCTYPE html><html><head><title>404</title></head><body>404: Page not found <a href='" + Configs.url_prefix + "driver/home'>go back!</a></body><html>");
            }
        }
    }
}
