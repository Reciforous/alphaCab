package com.org.Controllers;

import com.org.Helpers.Message;
import com.org.Models.Demand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeDemand extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            request.getRequestDispatcher("/alphacab/views/customer/make-demand.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request please go back!\n" + e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            String name = (String) request.getParameter("name");
            String address = (String) request.getParameter("address");
            String destination = (String) request.getParameter("destination");

            Demand demand = new Demand(name, address, destination);
            Message message = demand.add();

            request.setAttribute("message", message);
            request.getRequestDispatcher("/alphacab/views/customer/make-demand.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request please go back!\n" + e.getMessage());
        }
    }
}
