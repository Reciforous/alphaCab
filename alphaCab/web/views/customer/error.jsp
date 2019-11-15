<%@ page import="com.org.Helpers.*" %>

<!DOCTYPE html>
<html>
<head>
	<title>Oops!</title>
</head>
<body>
	<% Message message = (Message) request.getAttribute("message"); %>
	<div>
		<p>
			<b><% out.print(message.content); %></b>
		</p>
	</div>
	<div>
		<a href="<% out.print(Configs.url_prefix + "home"); %>"></a>
	</div>
</body>
</html>
