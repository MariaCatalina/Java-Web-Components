<html>
<body>
		<%
            session.invalidate();
        %>
        <br><br><br>
	<center>
		<h1>
			<font>You are Sucessfully logged out...</font>
		</h1>
		<a href="${pageContext.request.contextPath}/index">Go-Back To Home Page</a>
	</center>
</body>
</html>