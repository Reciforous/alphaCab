package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Models.Customer;
import com.org.Models.Demand;
import com.org.Models.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
    TODO: Move to git
 */
public class ViewPendingDemand extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "customer")){
            Functions.redirect(response, "login");
        }
        else{
            Cookie[] cookies = request.getCookies();
            Cookie id_cookie = Functions.getCookie(cookies, "id");

            assert id_cookie != null;

            Integer id = Integer.parseInt(id_cookie.getValue());
            Customer customer = new Customer(id);
            customer.get();

            Demand demand = Demand.getAccepted(customer);

            if(demand == null || demand.id == null){
                request.setAttribute("demand", null);
            }
            else{
                request.setAttribute("demand", demand);
            }

            try{
                request.getRequestDispatcher("/views/customer/view-demand.jsp").forward(request, response);
            }
            catch(ServletException e){
                response.getWriter().print("There was an error handling your request. Please go back!<br>" + e.getMessage());
            }
        }
    }
}
