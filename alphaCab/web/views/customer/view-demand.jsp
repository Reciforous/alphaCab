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
	<div id="app">
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
					<input name="address" id="address" type="text" readonly value="<% out.print(demand.address); %>" hidden>
					<input type="text" v-model="addressText">
				</div>
				<div>
					<label>Destination: </label>
					<input name="destination" id="destination" type="text" readonly value="<% out.print(demand.destination); %>" hidden>
					<input type="text" v-model="destinationText">
				</div>
				<div>
					<p>Date ordered: <% out.print(sdf.format(demand.date)); %></p>
				</div>

				<%-- This parts needs to be done with javascript, use address and destination to make a post request to --%>
				<%-- maps api, fix value for distance and amount through javascript current set rate on backend for journey is 2.75--%>
				<div>
					<label for="distance">Distance in meters:</label>
					<input type="number" name="distance" readonly v-model="distance">
				</div>
				<div>
					<label for="amount">Amount: </label>
					<input readonly v-model="amount" type="number" step="0.01" min="0.00">
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
	<script src="/alphaCab/static/js/jquery-3.2.1.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD5c-jK4r3n2qvCE5UU-b4r5A_m5KVaOos"></script>
	<script src="/alphaCab/static/js/axios.min.js"></script>
	<script src="/alphaCab/static/js/vue.js"></script>
	<script src="/alphaCab/static/js/getDistance.js"></script>
</body>
</html>
