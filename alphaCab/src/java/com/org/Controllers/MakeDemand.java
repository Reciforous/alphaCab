package com.org.Controllers;

import com.org.Helpers.Message;
import com.org.Models.Customer;
import com.org.Models.Demand;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeDemand extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            request.setAttribute("message", null);
            request.getRequestDispatcher("/alphacab/views/customer/make-demand.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request please go back!\n" + e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            Cookie[] cookies = request.getCookies();
            Cookie id_cookie = null;
            Integer id = null;

            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id")){
                    id_cookie = cookie;
                }
            }

            if(id_cookie != null){
                id = Integer.parseInt(id_cookie.getValue());
            }

            Customer customer = new Customer(id);
            customer.get();

            String address = (String) request.getParameter("address");
            String destination = (String) request.getParameter("destination");

            Demand demand = new Demand(customer.name, address, destination);
            Message message = demand.add();

            request.setAttribute("message", message);
            request.getRequestDispatcher("/alphacab/views/customer/make-demand.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request please go back!\n" + e.getMessage());
        }
    }
}
