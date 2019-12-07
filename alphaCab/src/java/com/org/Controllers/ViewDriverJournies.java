package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Models.Driver;
import com.org.Models.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewDriverJournies extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "driver")){
            Functions.redirect(response, "login");
        }
        else{
            Cookie cookie = Functions.getCookie(request.getCookies(), "id");
            assert cookie != null;
            Driver driver = new Driver(cookie.getValue());
            driver.get();
            ArrayList<Journey> journies = Journey.getByRegistration(driver);

            request.setAttribute("journies", journies);
            try{
                request.getRequestDispatcher("/views/driver/view-journies.jsp").forward(request, response);
            }
            catch(ServletException e){
                response.getWriter().print("There was an error handling your request. Please go back <br>" + e.getMessage());
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "driver")){
            Functions.redirect(response, "login");
        }
        else{
            Cookie cookie = Functions.getCookie(request.getCookies(), "id");
            assert cookie != null;
            Driver driver = new Driver(cookie.getValue());
            driver.get();
            ArrayList<Journey> journies = Journey.getByRegistration(driver);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date start = new Date();
            Date end = new Date();
            try{
                start = sdf.parse(request.getParameter("startDate"));
                end = sdf.parse(request.getParameter("endDate"));
            }
            catch(ParseException e){
                response.getWriter().print("There was an error handling your request! Please go back <br> " + e.getMessage());
            }

            ArrayList<Journey> temp = new ArrayList<>();
            for(Journey journey : journies){
                if(!journey.date.before(start) || journey.date.after(end)){
                    temp.add(journey);
                }
            }

            journies = temp;


            request.setAttribute("journies", journies);
            try{
                request.getRequestDispatcher("/views/driver/view-journies.jsp").forward(request, response);
            }
            catch(ServletException e){
                response.getWriter().print("There was an error handling your request. Please go back <br>" + e.getMessage());
            }
        }
    }
}
