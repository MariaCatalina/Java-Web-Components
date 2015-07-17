<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@include file="Liste.jsp"%>
	<%@ page import="java.util.*"%>
	<%@ page import="java.io.*"%>

	<center>

		<%	/* nume user curent */
			String userEmail = request.getUserPrincipal().getName();
			String index = request.getParameter("bookIndex");
	 		int valid, ind;
	 		
	 		/* index-ul cărti selectate */
	 		ind = Integer.parseInt(index);
	 		gestiune.MyBook book =  new gestiune.MyBook();
	 		
	 		/* structura în care se gasete cartea */
	 		Hashtable<Integer,gestiune.MyBook> table = ((gestiune.Gestiune) application.getAttribute("gestiune")).getList();
		
	 		book = table.get(ind);
	 		
	 		/* actualizare numar de exemplare împrumutate */
	 	 	valid = book.setExImprumutate();
	 		
	 		/* daca valid = 1 - cartea poate fi împrumutată */
			 if(valid == 1) {
			 ((gestiune.DataTable)application.getAttribute("tableUser")).addUser(userEmail, table, index);
		%>
			<em>Ai împrumutat cartea:
			<%= table.get(ind).getTitlu() %>
			<em>scrisă de: <%= table.get(ind).getAutor() %>

		<%} else { %>
			
			<em>cartea selectată nu mai are exemplare disponibile </em>
			
		<% } %>

	</center>
</body>
</html>