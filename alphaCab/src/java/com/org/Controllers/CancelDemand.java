package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Customer;
import com.org.Models.Demand;
import com.org.Models.Driver;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    TODO: Move this to get
 */
public class CancelDemand extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!(Functions.authenticateRoute(request, "customer") || Functions.authenticateRoute(request, "driver"))){
            Functions.redirect(response, "login");
        }
        else{
            // For security purposes we dont let users post to this controller
            Cookie type_cookie = Functions.getCookie(request.getCookies(), "type");
            Cookie id_cookie = Functions.getCookie(request.getCookies(), "id");

            assert type_cookie != null && id_cookie != null;

            Demand demand = new Demand();
            String redirect_url;
            String error_page;

            if(type_cookie.getValue().equals("driver")){
                error_page = "/views/driver/error.jsp";
                redirect_url = "driver/home";
                Driver driver = new Driver(id_cookie.getValue());
                driver.get();
                demand = Demand.getPending(driver);
            }
            else{
                error_page = "/views/customer/error.jsp";
                redirect_url = "home";
                Customer customer = new Customer(Integer.parseInt(id_cookie.getValue()));
                customer.get();
                demand = Demand.getAccepted(customer);
            }

            Message message = demand.cancel();
            if(message.status){
                Functions.redirect(response, redirect_url);
            }
            else{
                try{
                    request.getRequestDispatcher(error_page).forward(request, response);
                }
                catch(ServletException e){
                    response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
                }
            }
        }
    }
}
