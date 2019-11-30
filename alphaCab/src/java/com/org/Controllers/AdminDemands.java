/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Demand;
import com.org.Models.Journey;
import com.org.Models.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shaaik
 */
public class AdminDemands extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ArrayList<Demand> demands = Demand.getDemands();
            request.setAttribute("demands", demands);
            request.getRequestDispatcher("/views/admin/demands.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, Please go back!\n" + e.getMessage());
        }
    }

public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            if (request.getParameterMap().containsKey("demand")) {
                String demandID = request.getParameter("demand");
                Demand demand = new Demand(parseInt(demandID));
                demand.cancel();
            }
            request.getRequestDispatcher("/views/admin/demands.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request please go back!<br>" + e.getMessage());
        }
    }
}
