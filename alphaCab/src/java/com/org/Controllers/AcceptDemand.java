package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Demand;
import com.org.Models.Driver;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    TODO: Move this to git
 */

/**
 * Controller method for accepting a demand, works more or less like an API
 */
public class AcceptDemand extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // Authenticate user
        if(Functions.authenticateRoute(request, "driver")){
            // Get id of driver from cookie
            Cookie[] cookies = request.getCookies();
            Cookie registration_cookie = Functions.getCookie(cookies, "id");

            assert registration_cookie != null;
            Driver driver = new Driver(registration_cookie.getValue());
            driver.get();

            // Get demand id from URL
            Integer id = Integer.parseInt(request.getPathInfo().substring(1));
            Demand demand = new Demand(id);
            demand.getById();

            // Check if provided demand is not valid
            if(demand.name == null || demand.destination == null || demand.address == null){
                response.getWriter().print("Error: No demand with id: " + demand.id);
            } else{
                // if valid accept, update and push to demand queue
                Message message = demand.accept(driver);

                if(message.status){
                    // if successful send redirect to drivers/orders
                    Functions.redirect(response, "driver/orders");
                }
                else{
                    try{
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/views/driver/error.jsp").forward(request, response);
                    }
                    catch(ServletException e){
                        response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
                    }
                }
            }
        }
        else{
            Functions.redirect(response, "login");
        }

    }
}
