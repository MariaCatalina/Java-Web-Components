<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Biblioteca online</title>
</head>
<body>

	<%@include file="Liste.jsp" %>
	<%@ page import="java.util.*" %>
	<%@ page import="java.io.*" %>
	
	<center>
	<% gestiune.Gestiune g = ((gestiune.Gestiune)application.getAttribute("gestiune")); %>
	<% Hashtable<Integer,gestiune.MyBook> table = g.getList();
  		gestiune.MyBook book; 
  		LinkedList<gestiune.MyBook> list = new LinkedList<gestiune.MyBook>(); 
  			list = g.getSortByAutor();
  			%>
  			
		<% if (request.isUserInRole("bibliotecar")) { %> 
	<table style="width:80%" border="2">
  	<tr>
  		
    	<th>Autor</th>
    	<th>Titlu</th> 
    	<th>Numar total de exemplare</th>
    	<th>Numar de exemplare imprumutate</th>
  	</tr>
  	
	<% Iterator it =  list.iterator();
		while(it.hasNext()){
			book = new MyBook();
			book = (MyBook)it.next();
		//book = new gestiune.MyBook();
		//book = table.get(copy);
		%>
		<tr> 
			<th><%=book.getAutor() %> </th> 
			<th><%=book.getTitlu() %> </th>
			<th><%=book.getExemplare()%> </th>	
			<th><%=book.getExemplareImprumutate() %> </th>
		</tr>
	<%	} %>
		</table>
		
	<% } else { %>	
		<table style="width:80%" border="2">
  	<tr>
  		
    	<th>Autor</th>
    	<th>Titlu</th> 
    	<th>Numar total de exemplare</th>
    	<th>Numar de exemplare imprumutate</th>
    	<th>     </th>
  	</tr>
  	
  	
  			
  			
	<% Iterator it =  list.iterator();
		while(it.hasNext()){
			book = new MyBook();
			book = (MyBook)it.next();
		//book = new gestiune.MyBook();
		//book = table.get(copy);
		%>
		<tr> 
			
			<th><%=book.getAutor() %> </th> 
			<th><%=book.getTitlu() %> </th>
			<th><%=book.getExemplare()%> </th>	
			<th><%=book.getExemplareImprumutate() %> </th>
			<th> <form> <button type="submit">Imprumuta </button> </form> </th>
		</tr>
<%} %>
		</table>
			
	<% } %>
</body>
</html>