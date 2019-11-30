<%-- 
    Document   : drivers
    Created on : Oct 26, 2019, 5:42:10 PM
    Author     : shaaik
--%>

<%@page import="com.org.Models.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/alphaCab/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">

        <script src="/alphaCab/static/js/bootstrap.min.js"></script>

        <title>Admin - Customers</title>
    </head>
    <body>
        <% ArrayList<Customer> customers = (ArrayList<Customer>) request.getAttribute("customers"); %>
    
    
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
                    <li class="nav-item active">
                        <a class="nav-link" href="/alphaCab/admin/customers">Customers<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/journeys">Journeys<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/demands">Demands<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/transactions">Transactions<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/report">Reports<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-6"><h3>Customers</h3></div>
            </div>
              
            <br>
           
            <table class="table table-striped">
            <thead>
                <tr>
                  <th scope="col">Name</th>
                  <th scope="col">Registration</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <% for(Customer customer : customers){%>
                    <tr>
                        <td>              
                            <% out.print(customer.id); %>
                        </td>            
                        <td>
                            <% out.print(customer.name); %>
                        </td>                 
                        <td>
                            <a href="/alphaCab/admin/customers?customer=<% out.print(customer.id); %>">View Details</a>
                        </td>
                    </tr>
                <%}%>
              </tbody>
            </table>
        </div>

    </body>
</html>
