<%-- TODO: Add to git --%>
<%@ page import="com.org.Models.Demand" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.org.Helpers.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>View Order</title>
</head>
<body>
	<% 
	Demand demand = (Demand) request.getAttribute("demand"); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Message message = null;
	if(request.getAttribute("message") != null){
		message = (Message) request.getAttribute("message");
	}
	%>
	<% if(demand != null){ %>
	<div>
		<div>
			<p><b>Customer name: </b> <% out.print(demand.name); %></p>
			<p><b>Address:</b> <% out.print(demand.address); %></p>
			<p><b>Destination: </b> <% out.print(demand.destination); %></p>
			<p><b>Requested Date:</b> <% out.print(sdf.format(demand.date)); %></p>
			<% if(demand.status.equals("Outstanding")){ %>
			<p><a href="<% out.print(Configs.url_prefix + "driver/order/accept/" + demand.id); %>">Accept Demand</a></p>
			<% } %>
			<% if(demand.status.equals("Accepted")){ %>
			<form action="<% out.print(Configs.url_prefix + "driver/order/cancel/" + demand.id); %>" method="POST">
				<button type="submit">Cancel Order</button>	
			</form>
			<% } %>
			<p><a href="<% out.print(Configs.url_prefix + "driver/home"); %>"></a></p>
		</div>
	</div>
	<% } else{ %>
	<p><% out.print(message.content); %> Want to <a href="<% out.print(Configs.url_prefix + "driver/orders"); %>">accept an order?</a></p>
	<% } %>
</body>
</html>
