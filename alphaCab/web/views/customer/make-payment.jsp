<%@ page import="com.org.Models.Journey" %>
<%@ page import="com.org.Models.Customer" %>
<%@ page import="com.org.Helpers.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
	<title>Payment Gateway</title>
</head>
<body>
	<% 
	Message message = (Message) request.getAttribute("message"); 
	if (message.status){
		Journey journey = (Journey) request.getAttribute("journey");
		Customer customer = (Customer) request.getAttribute("customer");
		// Boolean paid = (Boolean) request.getAttribute("paid");
		Boolean paid = false;	
	}
	%>
	<% if(message.status){ %>
		<form method="POST" action=<% out.print("/pay/" + journey.id); %>>
			<label for="customer">Customer Name</label>
			<input type="text" value="<% out.print(customer.name); %>">
			<label for="journeyId">ID</label>
			<input type="number" name="journeyId" value="<% out.print(journey.id); %>">
			<label for="amount">Amount</label>
			<input type="text" name="amount" value="<% out.print(journey.distance * 2.75); %>">
			<button type="submit">Pay Now</button>
		</form>
	<%}%>
</body>
</html>
