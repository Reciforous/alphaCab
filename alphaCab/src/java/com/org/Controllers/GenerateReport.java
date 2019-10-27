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
import java.util.ArrayList;
import com.org.Models.Transaction;
import com.org.Models.Journey;
import com.org.Models.Driver;
import com.org.Models.Report;
/**
 *
 * @author rasen
 */
public class GenerateReport extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{
        request.setAttribute("report", null);
        try{
            request.getRequestDispatcher("/alphaCab/views/admin/report-generator.jsp").forward(request, response);
        }
        catch(ServletException e){
            response.getWriter().print("There was an error handling your request, Please go back!\n" + e.getMessage());
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String start_date = request.getParameter("startDate");
        String end_date = request.getParameter("endDate");
        
        Report report = new Report();
        report.getAmounts(start_date, end_date);
        
        request.setAttribute("report", report);
        try{
            request.getRequestDispatcher("/alphaCab/views/admin/report-generator.jsp").forward(request, response);
        }
        catch(ServletException e){
            response.getWriter().print("There was an error handling your request, Please go back!\n" + e.getMessage());
        }
    }
}
