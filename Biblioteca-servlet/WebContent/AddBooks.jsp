<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Biblioteca online</title>

	<%@ include file="Header.jsp" %>
	<%@ page import="java.util.*" %>
	
	<%-- jsp-ul creează pagina de adăugare a cărtilor --%>	
	
	<%-- butonul de return --%>
	<form action="index" method="get" >
		<input type="image" src="images/return1.jpg"  width="90" height="55">
	</form>
	
	<center>
	<p class="center">
			
		<br><br><br>
		<%-- buton pentru a adauga o carte --%>
		
		<form action="CarteAdaugata" method="post">
			Autor: <input type="text" name="Autor">
			Titlu: <input type="text" name="Titlu"><br>
			Nr. Exemplare: <input type="text" name="Numar"><br>
			<button type="submit">adaugă 
			</button>
		</form>
	</center>
</head>
</html>