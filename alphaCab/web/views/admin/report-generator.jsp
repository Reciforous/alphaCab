<%-- 
    Document   : report-generator
    Created on : Oct 27, 2019, 3:27:17 PM
    Author     : rasen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"  %>
<%@page import="com.org.Models.Report" %>
<%@page import="com.org.Models.Driver" %>
<%@page import="com.org.Helpers.Configs" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/alphaCab/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">

        <script src="/alphaCab/static/js/bootstrap.min.js"></script>
        <title>Report Generator</title>
    </head>
    <body>
        <% 
        Report report = (Report) request.getAttribute("report"); 
        Float total = 0.0f;

        if(report != null ){
            for(Float amount : report.amounts){
                total += amount;
            }
        }
        %>
        
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
                    <li class="nav-item active">
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
                <div class="col-6"><h3>Report</h3></div>
            </div>
              
            <br>
            
            <div class="row">
                <div class="col-6">
                    
                        <% if(report != null){ %>
                            <% for(int i = 0; i < report.drivers.size(); i++){ %>
                            <div>
                                <ul>
                                        <li>Driver Name: <% out.print(report.drivers.get(i).name); %></li>
                                        <li>Amount Earned: <% out.print(report.amounts.get(i)); %></li>
                                </ul>
                            </div>
                            <div>
                            </div>
                            <% } %>
                            <hr>
                            <p>Total amount: <% out.print(total); %></p>
                            <a href='<% out.print(Configs.url_prefix + "admin/home"); %>'>Go back</a>
                        <% } else{ %>
                                        <div>
                                                <form action='<% out.print(Configs.url_prefix + "admin/report"); %>' method="POST">
                                                    
                                                    <div class="form-group">
                                                        <label for="startDate">Select start date</label>
                                                        <input type="date" name="startDate" class="form-control" aria-describedby="startDate" placeholder="Select start date" required>
                                                    </div>                                     
                                                    <div class="form-group">
                                                            <label for="endDate">End date</label>
                                                            <input type="date" name="endDate" class="form-control" required>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Generate Report</button>
                                                </form>
                                        </div>
                        <% } %>
                    
                </div>
            </div>
            
        </div>
         
        
    </body>
</html>
