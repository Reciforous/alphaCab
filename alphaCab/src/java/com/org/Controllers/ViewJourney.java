package com.org.Controllers;

import com.org.Models.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewJourney extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("journeyId"));

        Journey journey = new Journey(id);
        journey.get();

        request.setAttribute("journey", journey);

        try{
            request.getRequestDispatcher("/alphacab/views/customer/view-journey.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!\n" + e.getMessage());
        }
    }
}
