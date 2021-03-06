/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Controllers;

import com.org.Models.Driver;
import com.org.Models.Transaction;
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
public class ViewTransactions extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if (request.getParameterMap().containsKey("transaction")) {
                String transactionID = request.getParameter("transaction");
                Transaction transaction = new Transaction(parseInt(transactionID));
                transaction.get();
                request.setAttribute("transaction", transaction);
                request.getRequestDispatcher("/views/admin/transactions/transaction.jsp").forward(request, response);

            } else {
                ArrayList<Transaction> transactions = Transaction.getTransactions();
                request.setAttribute("transactions", transactions);
                request.getRequestDispatcher("/views/admin/transactions/transactions.jsp").forward(request, response);
            }
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, Please go back!\n" + e.getMessage());
        }
    }
}
