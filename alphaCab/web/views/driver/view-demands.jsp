<%@ page import="com.org.Models.Demand" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.org.Helpers.Configs" %>

<!DOCTYPE html>
<html>
<head>
	<title>View outstanding demands</title>
</head>
<body>
	<% ArrayList<Demand> demands = (ArrayList<Demand>)request.getAttribute("demands"); %>
	<div>
		<% if(!demands.isEmpty()){ %>
		<table>
			<thead>
				<tr>
					<th>Customer Name</th>
					<th>Address</th>
					<th>Destination</th>
					<th>Details</th>
					<th>Status (Press to accept)</th>
				</tr>
			</thead>
			<tbody>
				<% for(Demand demand : demands){ %>
				<tr>
					<td><% out.print(demand.name); %></td>
					<td><% out.print(demand.address); %></td>
					<td><% out.print(demand.destination); %></td>
					<td><a href='<% out.print(Configs.url_prefix + "driver/order/outstanding/" + demand.id); %>'>View</a></td>
					<td><a href='<% out.print(Configs.url_prefix + "driver/order/accept/" + demand.id); %>'><% out.print(demand.status); %></a></td>
				</tr>
				<% } %>
			</tbody>
		</table>
		<% } else { %>
			<p>No outstanding orders!</p>
		<% } %>
	</div>
</body>
</html>
