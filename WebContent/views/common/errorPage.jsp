<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String msg = (String)request.getAttribute("msg"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 실패</title>
</head>
<body>
	<h1 align="center"><%= msg %></h1>
</body>
</html>