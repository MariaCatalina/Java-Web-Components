<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	
	<%-- butonul de return --%>
	<form name="form" method="POST" >
		<input type="image" src="images/return1.jpg"  width="90" height="55"  formaction="index">
	</form>
	
	<center>
	<p class="center">
		
		<br><br><br>
		<%-- buton pentru a adauga o carte --%>
		<form action="DeletedBook.jsp" method="POST">
			Autor: <input type="text" name="Autor">
			Titlu: <input type="text" name="Titlu">
			<button type="submit">sterge 
			</button>
		</form>
	
	</p>
	</center>
	
</body>
</html>