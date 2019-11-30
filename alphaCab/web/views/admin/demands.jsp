<%-- 
    Document   : drivers
    Created on : Oct 26, 2019, 5:42:10 PM
    Author     : shaaik
--%>

<%@page import="com.org.Models.Demand"%>
<%@page import="com.org.Models.Journey"%>
<%@page import="com.org.Models.Transaction"%>
<%@page import="com.org.Models.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/alphaCab/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">

        <script src="/alphaCab/static/js/bootstrap.min.js"></script>

        <title>Admin - Demands</title>
    </head>
    <body>
        <% ArrayList<Demand> demands = (ArrayList<Demand>) request.getAttribute("demands"); %>
        
   
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/alphaCab/admin">AlphaCab</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/drivers">Drivers<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/customers">Customers<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/journeys">Journeys<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/alphaCab/admin/demands">Demands<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/transactions">Transactions<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-6"><h3>Demands</h3></div>
            </div>
              
            <br>
           
            <table class="table table-striped">
            <thead>
                <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Address</th>
                <th>Destination</th>                
                <th>Date</th>
                <th></th>



            </tr>
              </thead>
              <tbody>
                <% for(Demand transaction : demands){%>
                    <tr>
                        <td>
                            <% out.print(transaction.id); %>
                        </td>
                     
                        <td>
                            <% out.print(transaction.name); %>
                        </td>
                        <td>
                            <% out.print(transaction.address); %>
                        </td>
                        <td>
                            <% out.print(transaction.destination); %>
                        </td>
                        <td>
                            <% out.print(transaction.date); %>
                        </td>
                        <td>
                            <form action="/alphaCab/admin/demands?demand=<% out.print(transaction.id); %>" method="post">
                                <button type="submit" name="your_name" value="your_value" class="btn-link">Cancel</button>
                            </form>
                            <a href="/alphaCab/admin/demands?demand<% out.print(transaction.id); %>">Cancel</a>
                        </td>
                    </tr>
                <%}%>
              </tbody>
            </table>
        </div>
    </body>
</html>