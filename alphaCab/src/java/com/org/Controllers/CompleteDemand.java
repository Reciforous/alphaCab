package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Demand;
import com.org.Models.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    TODO: Move to git
 */
public class CompleteDemand extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "customer")){
            Functions.redirect(response, "login");
        }
        else{
            Integer id = Integer.parseInt(request.getParameter("demandId"));
            Integer distance = Integer.parseInt(request.getParameter("distance"));

            Demand demand = new Demand(id);
            demand.getById();
            Journey journey = new Journey();
            Message message = demand.completed(distance, journey);

            if(message.status){
                Functions.redirect(response, "journies");
            }
            else{
                try{
                    request.getRequestDispatcher("/views/customer/error.jsp").forward(request, response);
                }
                catch(ServletException e){
                    response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
                }
            }
        }
    }
}
