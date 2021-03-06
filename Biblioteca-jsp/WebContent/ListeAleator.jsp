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
	
	<%-- jsp-ul afiseaza tabelul de cărți in ordinea introdusă --%>
	
	<center>
		
	<%  /* preluare date din structură */
		gestiune.Gestiune g = ((gestiune.Gestiune)application.getAttribute("gestiune")); 
	 	Hashtable<Integer,gestiune.MyBook> table = g.getList();
  		gestiune.MyBook book;
  	%>

	
	<% if (request.isUserInRole("bibliotecar")) { %> 
	
	<table style="width:80%" border="2">
  	<tr>
  		
    	<th>Autor</th>
    	<th>Titlu</th> 
    	<th>Număr total de exemplare</th>
    	<th>Număr de exemplare împrumutate</th>
  	</tr>
  	
  	<%	/* parcurgere și afisare în tabel */
  		Set<Integer> keys = table.keySet();
		Iterator<Integer> it = keys.iterator();
	
		while(it.hasNext()){
			book = new MyBook();
			book = table.get(it.next());
	%>
		<tr> 
			<th><%=book.getAutor() %> </th> 
			<th><%=book.getTitlu() %> </th>
			<th><%=book.getExemplare()%> </th>	
			<th><%=book.getExemplareImprumutate() %> </th>
		</tr>
		
		<% } %>
		
		</table>
		
	<% } else { %>	
		
		<table style="width:80%" border="2">
  	<tr>
    	<th>Autor</th>
    	<th>Titlu</th> 
    	<th>Număr total de exemplare</th>
    	<th>Număr de exemplare imprumutate</th>
    	<th>         </th>
  	</tr>
  	<%  /* parcurgere și afișare în tabel */
  		Set<Integer> keys = table.keySet();
		Iterator<Integer> it = keys.iterator();
		
		while(it.hasNext()){
		
			book = new MyBook();
			book = table.get(it.next());
			application.setAttribute("table", book);
	%>
		<tr> 
			<th><%=book.getAutor() %> </th> 
			<th><%=book.getTitlu() %> </th>
			<th><%=book.getExemplare()%> </th>	
			<th><%=book.getExemplareImprumutate() %> </th>
			<th> 
				<form action="Imprumuta.jsp" method="post"> 
					<input type="hidden" name="bookIndex" value="<%=book.getIndex()%>" />
					<button type="submit">Imprumuta </button> 
				</form>
			</th>
		</tr>
		<%} %>
	<% } %>
</body>
</html>