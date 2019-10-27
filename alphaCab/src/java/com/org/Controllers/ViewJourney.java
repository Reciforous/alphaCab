package com.org.Controllers;

import com.org.Models.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewJourney extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get object id from url
        Integer id = Integer.parseInt(request.getPathInfo().substring(1));

        Journey journey = new Journey(id);
        journey.get();

        request.setAttribute("journey", journey);

        try{
            request.getRequestDispatcher("/views/customer/view-journey.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!\n" + e.getMessage());
        }
    }
}
