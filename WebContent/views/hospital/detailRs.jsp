<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="reservation.model.vo.*" %>
<%
	Reservation rs = (Reservation)request.getAttribute("reservation");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 예약 상세 조회</title>
<style>
div.contents {width: 400px; height: 600px; top: calc(50% - 300px);}
table {}
</style>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
	<div class="container">
		<div class="contents">
			<h2>예약 상세 조회</h2>
			<table id="">
				
			</table>
		</div>
	</div>
</body>
</html>