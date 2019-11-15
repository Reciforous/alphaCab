<%-- TODO: Add to git --%>
<%@ page import="com.org.Models.Demand" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.org.Helpers.*" %>

<!DOCTYPE html>
<html>
<head>
	<title>Pending Order</title>
</head>
<body>
	<% 
	Demand demand = (Demand) request.getAttribute("demand"); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	%>
	<div>
		<div>
			<% if(demand != null){ %>
			<form action="<% out.print(Configs.url_prefix + "order/complete"); %>" method="POST">
				<div>
					<input type="number" hidden readonly name="demandId" value="<% out.print(demand.id); %>">
					<label>Ordered By: </label>
					<input name="customerName" type="text" readonly value="<% out.print(demand.name); %>">
				</div>
				<div>
					<label>Address: </label>
					<input name="address" type="text" readonly value="<% out.print(demand.address); %>">
				</div>
				<div>
					<label>Destination: </label>
					<input name="destination" type="text" readonly value="<% out.print(demand.destination); %>">
				</div>
				<div>
					<p>Date ordered: <% out.print(sdf.format(demand.date)); %></p>
				</div>

				<%-- This parts needs to be done with javascript, use address and destination to make a post request to --%>
				<%-- maps api, fix value for distance and amount through javascript current set rate on backend for journey is 2.75--%>
				<div>
					<input type="number" name="distance" hidden readonly value="10">
					<label for="amount">Amount: </label>
					<input readonly type="number" step="0.01" min="0.00" value="20.04">
				</div>
				<% if(demand.status.equals("Accepted")){ %>
				<div>
					<button type="submit">Accept Charges</button>
				</div>
				<% } %>
				
			</form>
			<div>
				<form action="<% out.print(Configs.url_prefix + "order/cancel/" + demand.id); %>" method="POST">
					<button type="submit">Cancel Order</button>
				</form>
			</div>
			<div>
				<a href="<% out.print(Configs.url_prefix + "home"); %>">Go back</a>
			</div>
			<%
			}
			else{
			%>
			<p>You have no orders pending do you want to <a href="<% out.print(Configs.url_prefix + "order"); %>">place an order?</a></p>
			<%}%>
		</div>
	</div>
</body>
</html>
