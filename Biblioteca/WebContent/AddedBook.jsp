<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="AddBooks.jsp" %>
	
		<br><br><br>
		<center>
				Ai adaugat o carte noua <br> <br>
				
				<%String autor = (String)request.getParameter("Autor"); %>
				<%String titlu = (String)request.getParameter("Titlu"); %>
				
				Autor: <%=autor %> <br>
				Titlu: <%=titlu %> <br>
			
		</center>
		
		<%	gestiune.MyBook b = new gestiune.MyBook(); 
			b.setBook(autor,titlu,1,0);
			((gestiune.Gestiune) application.getAttribute("gestiune")).addB(b); 
		%>
	
</body>
</html>