package com.org.Controllers;

import com.org.Helpers.Message;
import com.org.Models.Demand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDemand extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            String name = "JK Johnson";
            String address = "Ak 920194 BD";
            String destination = "Kings Cross Station";
            Demand demand = new Demand(name, address, destination);
            Message message = demand.add();
            System.out.println(message.content);
            request.getRequestDispatcher("/views/admin/home.html").forward(request, response);

        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!\n" + e.getMessage());
        }
    }
}
