package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Customer;
import com.org.Models.Journey;
import com.org.Models.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    TODO: Move to git

    WHATS NEW:
        Fixed payment, though now we need to make a change on the table end and make journeyId unique so we dont end up with multiple payments for the same journey
 */
public class MakeTransaction extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!Functions.authenticateRoute(request, "customer")){
            Functions.redirect(response, "login");
        }
        else{
            try{
                Integer journey_id = Integer.parseInt(request.getPathInfo().substring(1));
                Journey journey = new Journey(journey_id);
                journey.get();

                Customer customer = new Customer(journey.customer_id);
                customer.get();

                if(journey.date == null){
                    Message message = new Message(
                            false,
                            "Error: No result",
                            "error"
                    );
                    request.setAttribute("message", message);
                } else{
                    Message message = new Message(
                            true,
                            "No Errors",
                            "success"
                    );
                    request.setAttribute("message", message);
                }

                // Calculate payment amount in pounds / meter
                Float amount = journey.distance * 0.75f;

                request.setAttribute("journey", journey);
                request.setAttribute("customer", customer);
                request.setAttribute("paid", Boolean.FALSE);
            } catch(NumberFormatException e){
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
            } catch(ServletException e){
                response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(!Functions.authenticateRoute(request, "customer")){
            Functions.redirect(response, "login");
        }
        else{
            try{
                Integer journey_id = Integer.parseInt(request.getPathInfo().substring(1));
                Float amount = Float.parseFloat(request.getParameter("amount"));

                Transaction transaction = new Transaction(journey_id, amount);
                Message message = transaction.add();

                Journey journey = new Journey(journey_id);
                journey.get();

                Customer customer = new Customer(journey.customer_id);
                customer.get();

                if(message.status){
                    Boolean paid = Boolean.TRUE;
                    request.setAttribute("paid", paid);
                } else{
                    request.setAttribute("paid", Boolean.FALSE);
                }

                request.setAttribute("message", message);
                request.setAttribute("journey", journey);
                request.setAttribute("customer", customer);
            } catch(NumberFormatException e){
                Message message = new Message(
                        false,
                        "Error: Invalid journey id or amount",
                        "error"
                );
                request.setAttribute("message", message);
            }

            try{
                request.getRequestDispatcher("/views/customer/make-payment.jsp").forward(request, response);
            } catch(ServletException e){
                response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
            }
        }
    }
}
