package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Customer;
import com.org.Models.Demand;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*
    TODO: Move to git

    WHATS NEW:
       Simplified version of doing user permission
 */
public class MakeDemand extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!Functions.authenticateRoute(request, "customer")){
            Functions.redirect(response, "login");
        }
        else{
            try{
                request.setAttribute("message", null);
                request.getRequestDispatcher("/views/customer/make-demand.jsp").forward(request, response);
            } catch(ServletException e){
                response.getWriter().print("There was an error handling your request please go back!<br>" + e.getMessage());
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "customer")){
            Functions.redirect(response, "login");
        }
        else{
            try{
                Cookie[] cookies = request.getCookies();
                Integer id = null;
                Cookie id_cookie = Functions.getCookie(cookies, "id");

                if(id_cookie != null){
                    id = Integer.parseInt(id_cookie.getValue());
                }

                Message message;
                Customer customer = new Customer(id);
                customer.get();

                if(!Demand.checkIfOrdered(customer)){
                    String address = (String) request.getParameter("address");
                    String destination = (String) request.getParameter("destination");

                    Demand demand = new Demand(customer.name, address, destination);
                    message = demand.add();

                    System.out.println(message.status);
                    if(message.status){
                        Demand.addToDemandQueue(demand, customer);
                    }
                }
                else{
                    message = new Message(
                            false,
                            "You already have an order pending in queue",
                            "success"
                    );
                }

                request.setAttribute("message", message);
                request.getRequestDispatcher("/views/customer/make-demand.jsp").forward(request, response);
            }
            catch(ServletException e){
                response.getWriter().print("There was an error handling your request please go back!<br>" + e.getMessage());
            }
        }
    }
}
