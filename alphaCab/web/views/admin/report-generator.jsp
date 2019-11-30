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
					<div>
						<label for="startDate">Select start date</label>
						<input type="date" name="startDate" required>
					</div>
					<div>
						<label for="endDate">End date</label>
						<input type="date" name="endDate" required>
					</div>
					<button type="submit">Generate Report</button>
				</form>
			</div>
        <% } %>
    </body>
</html>
