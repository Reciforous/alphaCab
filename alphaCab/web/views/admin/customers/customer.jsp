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
        <link href="/alphaCab/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <script src="/alphaCab/static/js/bootstrap.min.js"></script>
        <title>Customer</title>
    </head>
    <body>
        
        <!--Navbar-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/alphaCab/admin">AlphaCab</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="/alphaCab/admin/drivers">Drivers<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
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
                <div class="col-6"><h3>Customer!</h3></div>
            </div>
            <br>
            <div class="row">
                <div class="col-6">
                    <form>
                        <div class="form-group">
                            <label for="id">ID</label>
                            <input type="text" class="form-control" aria-describedby="id" readonly value="<% out.print(customer.id); %>" >
                        </div>                                     
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" aria-describedby="name" readonly value="<% out.print(customer.name); %>">
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" aria-describedby="address" readonly value="<% out.print(customer.address); %>">
                        </div>
                        <br>
                        <a href="/alphaCab/admin/customers" class="btn btn-primary" role="button" aria-pressed="true">Go Back</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
