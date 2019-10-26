package com.org.Controllers;

import com.org.Models.Customer;
import com.org.Models.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ViewUnpaidJournies extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        Cookie id_cookie = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("id")){
                id_cookie = cookie;
                break;
            }
        }

        if(id_cookie != null){
            Integer id = Integer.parseInt(id_cookie.getValue());
            Customer customer = new Customer(id);
            customer.get();

            ArrayList<Journey> journies = Journey.getUnpaidJournies(customer);

            request.setAttribute("journies", journies);
        }

        try{
            request.getRequestDispatcher("/alphaCab/views/customer/view-journies.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!\n" + e.getMessage());
        }
    }
}
