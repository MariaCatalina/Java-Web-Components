<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- prima metoda 
<jsp:useBean id="person" class="foo.Employee" scope="request" />

Person created by servlet: <jsp:getProperty property="name" name="person"/>
--%>

<%-- useBean with body 

<jsp:useBean id="person" type="foo.Employee" scope="request">
	<jsp:setProperty name="person" property="name" value="Fred" />
</jsp:useBean>

Name is: <jsp:getProperty property="name" name="person"/>
--%>

<jsp:useBean id="person" type="foo.Person" class="foo.Employee">
	<jsp:setProperty name="person" property="name" 
		value="<%=request.getParameter("username") %>" />
</jsp:useBean>

</body>
</html>