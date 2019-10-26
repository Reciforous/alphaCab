<%-- 
    Document   : drivers
    Created on : Oct 26, 2019, 5:42:10 PM
    Author     : shaaik
--%>

<%@page import="com.org.Models.Transaction"%>
<%@page import="com.org.Models.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Transactions!</h1>
        <% ArrayList<Transaction> transactions = (ArrayList<Transaction>) request.getAttribute("transactions"); %>
        
    <table style="border: 1px solid black;">
        <tr>
            <th style="border: 1px solid black;">Id</th>
            <th style="border: 1px solid black;">Journey ID</th>
            <th style="border: 1px solid black;">Amount</th>
            <th style="border: 1px solid black;">Date</th>

        </tr>
        <% for(Transaction transaction : transactions){%>
            <tr style="border: 1px solid black;">
                <td style="border: 1px solid black;">
                    <% out.print(transaction.id); %>
                </td>
                <td style="border: 1px solid black;">
                    <% out.print(transaction.journey_id); %>
                </td> 
                <td style="border: 1px solid black;">
                    <% out.print(transaction.amount); %>
                </td>
                <td style="border: 1px solid black;">
                    <% out.print(transaction.transaction_date); %>
                </td>
                <td style="border: 1px solid black;">
                    <a href="/alphaCab/admin/drivers">View Details</a>
                </td>
            </tr>
        <%}%>
    </table>

    </body>
</html>
