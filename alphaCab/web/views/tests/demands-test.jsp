<%@ page import="com.org.Models.Demand" %>
<%@ page import="com.org.Helpers.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
	<title>Demands test</title>
</head>
<body>
	<% ArrayList<Demand> demands = (ArrayList<Demand>) request.getAttribute("demands"); %>
	<p style="padding-left: 10px; padding-top: 10px">
		<% for(Demand demand : demands){%>
			<ul style="padding-bottom: 10px; padding-top: 10px">
				<li>id: <% out.print(demand.id); %></li>
				<li>name: <% out.print(demand.name); %></li>
				<li>address: <% out.print(demand.address); %></li>
				<li>destination: <% out.print(demand.destination); %></li>
				<li>date: <% out.print(demand.date.toString()); %></li>
				<li>status: <% out.print(demand.status); %></li>
			</ul>
		<%}%>
	</p>
</body>
</html>
