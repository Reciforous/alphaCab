package com.org.Controllers;

import com.org.Helpers.Message;
import com.org.Models.Customer;
import com.org.Models.Journey;
import com.org.Models.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class MakeTransaction extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Integer journey_id = Integer.parseInt(request.getPathInfo().substring(1));
            Journey journey = new Journey(journey_id);
            journey.get();

            Customer customer = new Customer(journey.customer_id);
            customer.get();

            if(journey.date == null){
                System.out.println("Im hitting this");
                Message message = new Message(
                        false,
                        "Error: No result",
                        "error"
                );
                request.setAttribute("message", message);
            }else{
                Message message = new Message(
                        true,
                        "No Errors",
                        "success"
                );
                request.setAttribute("message", message);
            }

            // Calculate payment amount in pounds / meter
            Float amount = journey.distance * 0.75f;

            // Please put these details in a form so that when doing the post request, user just needs to press button called pay
            // also make set these details as disabled
            request.setAttribute("journey", journey);
            request.setAttribute("customer", customer);
            request.setAttribute("amount", amount);
        }
        catch (NumberFormatException e){
            Message message = new Message(
                    false,
                    "Error: Invalid journey id",
                    "error"
            );
            request.setAttribute("message", message);
            request.setAttribute("paid", Boolean.FALSE);
        }

        try{
            request.getRequestDispatcher("/views/customer/make-payment.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!\n" + e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            Integer journey_id = Integer.parseInt(request.getPathInfo().substring(1));
            Float amount = Float.parseFloat(request.getParameter("amount"));

            Transaction transaction = new Transaction(journey_id, amount);
            Message message = transaction.add();
            if(message.status){
                Boolean paid = Boolean.TRUE;
                request.setAttribute("paid", paid);
            }
            else{
                request.setAttribute("paid", Boolean.FALSE);
            }

            request.setAttribute("message", message);

        }
        catch (NumberFormatException e){
            Message message = new Message(
                    false,
                    "Error: Invalid journey id or amount",
                    "error"
            );
            request.setAttribute("message", message);
        }

        try{
            request.getRequestDispatcher("/views/customer/make-payment.jsp").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!\n" + e.getMessage());
        }
    }
}
