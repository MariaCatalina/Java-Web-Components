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
	
		<br><br><br>
			
		<%String autor = (String)request.getParameter("Autor"); %>
		<%String titlu = (String)request.getParameter("Titlu"); %>
				
		<%	gestiune.Gestiune g = ((gestiune.Gestiune)application.getAttribute("gestiune"));  
			
			int result = g.deleteB(autor, titlu); 
		%>
		<center>
		
		<% if(result == 1) { %>
			 Ai sters cartea <br>
		 <%=titlu %> de <%=autor %> <br>
			
		<% } else %>
		
		<% if( result == 2) { %>
				Ai sters ultimul exemplar din cartea <br>
				<%=titlu %> 
				de <%=autor %> <br>
			<% } else%>
		
		<%	if( result == 3) { %>
				Nu exista aceasta carte.<br>
				Incerca din nou!
			<% } %>
				
		</center>
		
		
</body>
</html>