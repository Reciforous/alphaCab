package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Models.Demand;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminCancelDemand extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "admin")){
            Functions.redirect(response, "login");
        }
        else{
            Integer id = Integer.parseInt(request.getPathInfo().substring(1));
            Demand demand = new Demand(id);
            demand.getById();
            demand.cancel();

            Functions.redirect(response, "admin/demands");
        }
    }
}
