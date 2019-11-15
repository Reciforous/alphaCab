/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Controllers;

import com.org.Models.Customer;
import com.org.Models.Driver;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shaaik
 */

/*
    TODO: Move to git
 */
public class ViewDrivers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if (request.getParameterMap().containsKey("driver")) {
                String driverRregistration = request.getParameter("driver");
                Driver driver = new Driver(driverRregistration);
                driver.get();
                request.setAttribute("driver", driver);
                request.getRequestDispatcher("/views/admin/drivers/driver.jsp").forward(request, response);

            } else {
                ArrayList<Driver> drivers = Driver.getDrivers();
                request.setAttribute("drivers", drivers);
                request.getRequestDispatcher("/views/admin/drivers/drivers.jsp").forward(request, response);
            }
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, Please go back!<br>" + e.getMessage());
        }
    }
}
