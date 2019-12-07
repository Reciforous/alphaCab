package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Models.Driver;
import com.org.Models.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/*
    TODO: Move this to git

    WHATS NEW:
        - Simplified route authentication
 */
public class DriverDashboard extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!Functions.authenticateRoute(request, "driver")){
            Functions.redirect(response, "login");
        }
        else{
            Cookie cookie = Functions.getCookie(request.getCookies(), "id");
            assert cookie != null;

            Driver driver = new Driver(cookie.getValue());
            driver.get();

            Float amount = 0.00f;

            if (driver.name != null){
                ArrayList<Transaction> transactions = Transaction.getTransactionsForDriver(driver);
                if(!transactions.isEmpty()){
                    for(Transaction transaction : transactions){
                        amount += transaction.amount;
                    }

                }
                request.setAttribute("total_journies", transactions.size());
            }
            request.setAttribute("amount", amount);

            try{
                request.getRequestDispatcher("/views/driver/home.jsp").forward(request, response);
            }
            catch(ServletException e){
                response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
            }
        }
    }
}
