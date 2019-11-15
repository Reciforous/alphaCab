package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Models.Customer;
import com.org.Models.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/*
    TODO: Move to git
 */
public class ViewUnpaidJournies extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Use cookie to get customer ID
        Cookie[] cookies = request.getCookies();
        Cookie id_cookie = Functions.getCookie(cookies, "id");

        if(id_cookie != null){
            Integer id = Integer.parseInt(id_cookie.getValue());
            Customer customer = new Customer(id);
            customer.get();

            ArrayList<Journey> journies = Journey.getUnpaidJournies(customer);

            request.setAttribute("journies", journies);
        }

        try{
            request.getRequestDispatcher("/views/customer/view-journies.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
        }
    }
}
