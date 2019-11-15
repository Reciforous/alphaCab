<%@ page import="com.org.Models.Transaction" %>
<%@ page import="com.org.Models.Customer" %>
<%@ page import="com.org.Models.Journey" %>
<%@ page import="com.org.Helpers.*" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
	<title>Payment Gateway</title>
</head>
<body>
	<% 
	Message message = (Message) request.getAttribute("message");
	Journey journey = null;
	Customer customer = null;
	Float amount = null;
	Boolean paid = (Boolean) request.getAttribute("paid");
	if(message.status){
		journey = (Journey) request.getAttribute("journey");
		customer = (Customer) request.getAttribute("customer");
	%>
		<% if(paid){ %>
			<div>
				<p><% out.print(message.content); %></p>
			</div>
		<% } %>
		<form action='<% out.print(Configs.url_prefix + "pay/" + journey.id); %>' method="POST">
			<label for="customer">Customer Name</label>
			<input type="text" readonly value='<% out.print(customer.name); %>'>

			<label for="journeyId">ID</label>
			<input type="number" readonly name="journeyId" value='<% out.print(journey.id); %>'>

			<label for="amount">Amount</label>
			<input type="text" name="amount" readonly value='<% out.print(journey.distance * 2.75); %>'>

			<button type="submit">Pay Now</button>
		</form>
	<%} else{%>
		<div>
			<p><% out.print(message.content); %></p>
		</div>
	<% } %>
	<div>
		
	</div>
</body>
</html>
