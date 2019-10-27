<%@page import="com.org.Models.Journey" %>

<!DOCTYPE html>
<html>
<head>
	<title>View Journey</title>
</head>
<body>
	<% Journey journey = (Journey) request.getAttribute("journey"); %>
	<div>
		<ul>
			<li>id: <% out.print(journey.id); %></li>
			<li>Destination: <% out.print(journey.destination); %></li>
			<li>Distance traveled: <% out.print(journey.distance); %></li>
			<li>Amount: <% out.print(journey.distance * 2.75f); %> </li>
			<a href=<% out.print("/pay/" + journey.id); %>>Pay Now</a>
		</ul>
	</div>
</body>
</html>
