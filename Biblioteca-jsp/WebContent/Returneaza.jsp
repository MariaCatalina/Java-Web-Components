<%@page import="gestiune.Gestiune"%>
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
	<%@page import="gestiune.MyBook"%>
	<%@page import="gestiune.DataTable"%>

	<%-- jsp-ul creaza pagina accesată de butonul Returnează --%>
	
	<%-- butonul de return --%>
	<form action="index" method="POST">
		<input type="image" src="images/return1.jpg"  width="90" height="55"  formaction="index">
	</form>
	
	<center>
	
	<%	
		String userEmail = request.getUserPrincipal().getName();
		String b = request.getParameter("bookB");	
		int index = Integer.parseInt(b);
		
		gestiune.Gestiune g = (gestiune.Gestiune)application.getAttribute("gestiune");
		Hashtable<Integer,MyBook> hash = g.getList();
		
		/* actualizează numărul de exemplare de cărți împrumutate */
		hash.get(index).removeB();
		 
		/* se actualizeaza cartea in structura de date */
		((DataTable)application.getAttribute("tableUser")).removeReturn(hash.get(index));
	%>	
	
	<em>Ai returnat cartea: </em> <%= hash.get(index).getTitlu() %>
	<em>scrisă de: </em> <%= hash.get(index).getAutor() %>
	
	</center>
</body>
</html>