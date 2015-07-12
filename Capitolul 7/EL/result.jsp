<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>El Example</title>
</head>
<body>
${person.name}'s dog ${person.dog.name}'s toys are: ${person.dog.toys[0].name},${person.dog.toys[1].name},${person.dog.toys[2].name} 

</body>
</html>