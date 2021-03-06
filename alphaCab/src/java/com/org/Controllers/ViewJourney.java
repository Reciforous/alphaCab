package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    TODO: Move to git

    WHATS NEW: This is for a customer route
 */
public class ViewJourney extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get object id from url
        if(!Functions.authenticateRoute(request, "customer")){
            Functions.redirect(response, "login");
        }
        try{
            Integer id = Integer.parseInt(request.getPathInfo().substring(1));

            Journey journey = new Journey(id);
            journey.get();

            request.setAttribute("journey", journey);

            try{
                request.getRequestDispatcher("/views/customer/view-journey.jsp").forward(request, response);
            } catch(ServletException e){
                response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
            }
        }
        catch(NumberFormatException e){
            Message message = new Message(
                    false,
                    "Error: Invalid url",
                    "error"
            );
            request.setAttribute("message", message);
            try{
                request.getRequestDispatcher("/views/customer/error.jsp").forward(request, response);
            }
            catch(ServletException x){
                response.getWriter().print("There was an error handling your request, please go back!<br>" + x.getMessage());
            }
        }
    }
}
