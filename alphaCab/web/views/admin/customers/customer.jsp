<%-- 
    Document   : drivers
    Created on : Oct 26, 2019, 5:42:10 PM
    Author     : shaaik
--%>

<%@page import="com.org.Models.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Customer customer = (Customer) request.getAttribute("customer"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer</title>
    </head>
    <body>
        <h1>Customer!</h1>
        <p>ID: <% out.print(customer.id); %></p>
        <p>Name: <% out.print(customer.name); %></p>
        <p>Address: <% out.print(customer.address); %></p>
    </body>
</html>
