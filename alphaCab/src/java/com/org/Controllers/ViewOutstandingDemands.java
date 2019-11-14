package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Models.Demand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/*
    TODO: Move to git
 */
public class ViewOutstandingDemands extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "driver")){
            Functions.redirect(response, "login");
        }
        else{
            ArrayList<Demand> demands = Demand.getByStatus("Outstanding");
            try{
                request.setAttribute("demands", demands);
                request.getRequestDispatcher("/views/driver/view-demands.jsp").forward(request, response);
            } catch(ServletException e){
                response.getWriter().print("There was an error handling your request. Please go back!<br>" + e.getMessage());
            }
        }
    }
}
