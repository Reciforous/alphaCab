<%@ page import="java.util.ArrayList" %>
<%@ page import="com.org.Models.Journey" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
<head>
	<title>View Unpaid Journies</title>
</head>
<body>
	<% 
		ArrayList<Journey> journies = (ArrayList<Journey>) request.getAttribute("journies");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	%>
	<% if(!journies.isEmpty()){ %>
		<% for(Journey journey : journies){ %>
			<ul>
				<li>Id: <% out.print(journey.id); %></li>
				<li>Destination: <% out.print(journey.destination); %></li>
				<%-- <li>Distance travelled: <% out.print(journey.distance); %></li> --%>
				<li>Date: <% out.print(sdf.format(journey.date)); %> </li>
				<a href="#">View Details</a>
			</ul>
		<% } %>
	<% } else{ %>
	<p><b>No Payments pending</b></p>
	<% } %>
	<div>
		
	</div>
</body>
</html>
