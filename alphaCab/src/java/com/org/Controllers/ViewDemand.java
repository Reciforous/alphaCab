/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.org.Models.Demand;

public class ViewDemand extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {        
        Integer id = Integer.parseInt(request.getParameter("demandID"));

        Demand demands = new Demand(id);
        demands.getById();

        request.setAttribute("demands", demands);
        try{
            request.getRequestDispatcher("/views/tests/demands-test.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!\n" + e.getMessage());
        }
    }
    
}
