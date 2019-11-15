package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Demand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    TODO: Move this to git

    WHATS NEW:
        - This is a driver route
 */
public class ViewOutstandingDemand extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "driver")){
            Functions.redirect(response, "login");
        }
        else{
            try{
                Integer demand_id = Integer.parseInt(request.getPathInfo().substring(1));
                Demand demand = new Demand(demand_id);
                demand.getById();

                request.setAttribute("demand", demand);

                try{
                    request.getRequestDispatcher("/views/driver/view-demand.jsp").forward(request, response);
                } catch(ServletException e){
                    response.getWriter().print("There was an error handling your request, please go back<br>" + e.getMessage());
                }
            }
            catch(NumberFormatException e){
                Message message = new Message(
                        false,
                        "Error: Invalid url",
                        "error"
                );
                try{
                    request.getRequestDispatcher("/views/driver/error.jsp").forward(request, response);
                }
                catch(ServletException x){
                    response.getWriter().print("There was an error handling your request, please go back<br>" + e.getMessage());
                }
            }
        }
    }
}
