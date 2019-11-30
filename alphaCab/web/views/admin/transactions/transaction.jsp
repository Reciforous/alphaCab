<%-- 
    Document   : drivers
    Created on : Oct 26, 2019, 5:42:10 PM
    Author     : shaaik
--%>

<%@page import="com.org.Models.Transaction"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Transaction transaction = (Transaction) request.getAttribute("transaction"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction</title>
    </head>
    <body>
        <h1>Customer!</h1>
        <p>Transaction ID: <% out.print(transaction.id); %></p>
        <p>Journey ID: <% out.print(transaction.journey_id); %></p>
        <p>Transaction Date: <% out.print(transaction.transaction_date); %></p>
        <p>Amount: <% out.print(transaction.amount); %></p>
    </body>
    
</html>
