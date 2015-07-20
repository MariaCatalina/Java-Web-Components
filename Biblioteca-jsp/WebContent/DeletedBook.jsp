<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@ include file="DeleteBook.jsp" %>
	
	<%-- jsp-ul sterge cartea din structura de date în functie de parametri primiți --%>
		
		<br><br><br>
			
		<%
			String autor = (String)request.getParameter("Autor"); 
			String titlu = (String)request.getParameter("Titlu");		
			gestiune.Gestiune g = ((gestiune.Gestiune)application.getAttribute("gestiune")); 	
			int result = -2;
			
			if(!autor.isEmpty() && !titlu.isEmpty())
				result = g.deleteB(autor, titlu); 
		%>
		<center>
		
		<% if(result == 1) { %>
		
			 <em>Ai șters cartea: </em> <br> <%=titlu %> <em>de </em><%=autor %> <br>
			
		<% } else %>
		
		<% if( result == 2) { %>
				
				<em>Ai șters ultimul exemplar din cartea: </em> <br>
				<%=titlu %> 
				<em>de <%=autor %></em> <br>
		
		<% } else %>
			<%	if( result == 3) { %>
				
				<em>Nu exista această carte.<br></em>
				<em>Incercă din nou!</em>
			
			<% } else { %>
					<em>Datele introduse nu sunt valide.<br> Încearcă din nou!</em>
			<% } %>
				
		</center>
		
		
</body>
</html>