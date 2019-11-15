package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Demand;
import com.org.Models.Driver;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    TODO: Move to this git
 */
public class ViewAcceptedDemand extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "driver")){
            Functions.redirect(response, "login");
        }
        else{
            Cookie[] cookies = request.getCookies();
            Cookie cookie = Functions.getCookie(cookies, "id");

            assert cookie != null;
            String registration = cookie.getValue();
            Driver driver = new Driver(registration);
            driver.get();

            Demand demand = Demand.getPending(driver);
            if(demand == null){
                request.setAttribute("message", new Message(
                        false,
                        "You currently do not have any orders accepted!",
                        "error"
                    ));
            }
            else{
                request.setAttribute("message", null);
            }

            request.setAttribute("demand", demand);

            try{
                request.getRequestDispatcher("/views/driver/view-demand.jsp").forward(request, response);
            }
            catch(ServletException e){
                response.getWriter().print("There was an error handling your request, Please go back!<br>" + e.getMessage());
            }
        }
    }
}
