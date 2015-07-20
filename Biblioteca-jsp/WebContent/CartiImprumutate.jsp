
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
	<%@ page import="java.util.*" %>
	<%@ page import="java.io.*" %>
	<%@page import="gestiune.BorrowedBook"%>
	<%@page import="gestiune.DataTable"%>
	
	<br>
	<form action="index" method="POST">
		<input type="image" src="images/return1.jpg"  width="100" height="67">
	</form>
	
	<%  /* datele din structura de date */
		gestiune.DataTable table = (DataTable)application.getAttribute("tableUser");
		ArrayList<BorrowedBook> users = table.getUser();
		BorrowedBook b;
	%>
	<center>
	<table style="width:80%" border="2">
  	<tr>
  		
    	<th>Autor</th>
    	<th>Titlu</th> 
    	<th>Email client</th>
    	<th>Data împrumutării</th>
  	</tr>
  	<%	/* parcurgere cărți imprumutate */
		Iterator<BorrowedBook> it = users.iterator();
  		while(it.hasNext()){ 
  			b = new BorrowedBook();
  			b = it.next();
  			
  			/* se listeza doar cărtile care aparțin user-ului curent */
  			if(b.getUser().equals(request.getUserPrincipal().getName())){
  	%>
			<tr> 
				<th><%=b.getBook().getAutor() %></th> 
				<th><%=b.getBook().getTitlu() %></th>
				<th><%=b.getUser() %></th>	
				<th><%=b.getDate() %></th>
				<th> 
					<form action="Returneaza.jsp" method="post"> 
						<input type="hidden" name="bookB" value="<%=b.getBook().getIndex()%>" />
						<button type="submit">Returnează </button> 
					</form>
				 </th>
			</tr>
		<% } %>
	<% } %>
		</table>
	</center>
	
</body>
</html>