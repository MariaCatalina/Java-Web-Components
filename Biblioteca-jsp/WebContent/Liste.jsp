<%@page import="gestiune.MyBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@include file="Header.jsp" %>
	<%@ page import="java.util.*" %>
	<%@ page import="java.io.*" %>

	<%-- jsp-ul creează butoane de afișare a listelor de cărți --%>
	
	<%-- butonul de return --%>
	<br>
	<form action="index" method="POST">
		<input type="image" src="images/return1.jpg"  width="100" height="67">
	</form>
	<center>

	<style>
	ul {
    	float: left;
    	width: 100%;
    	padding: 5em;
    	margin: 0;
    	list-style-type: none;
	}

	a {
    	float: left;
    	width: 6em;
    	text-decoration: none;
    	color: black;
    	background-color: white;
    	padding: 0.2em 0.6em;
    	border-right: 1px solid white;
	}

	a:hover {
    	background-color: #7BB661;
	}

	li {
    	display: inline;
	}
</style>
  
		<ul>
		<li><a href="ListeAleator.jsp">Afișare aleatorore </a></li>
		<li><a href="ListeAutor.jsp">Afișare după autor</a></li>
		<li><a href="ListeTitlu.jsp">Afișare după titlu</a></li>
		</ul>

	</center>
</body>
</html>