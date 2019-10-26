<%@ page import="com.org.Helpers.Message" %>

<!DOCTYPE html>
<html>
<head>
	<title>Order A Cab</title>
</head>
<body>
	<% Message message = (Message) request.getAttribute("message"); %>
	<div>
		<form action="/order" method="POST">
			<% if(message != null){ %>
			<div>
				<p>Message: <% out.print(message.content); %></p>
			</div>
			<% } %>
			<div>
				<label for="address">Address</label>
				<input type="text" placeholder="Enter Address" name="address" required>
			</div>
			<div>
				<label for="destination">Destination</label>
				<input type="text" placeholder="Enter Destination" name="destination" required>
			</div>
			<div>
				<button type="submit">Order</button>
			</div>
		</form>
	</div>
</body>
</html>
