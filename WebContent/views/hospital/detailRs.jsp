<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="reservation.model.vo.*, pet.model.vo.*" %>
<%
	Reservation rs = (Reservation)request.getAttribute("reservation");
	Hospital hp = (Hospital)request.getAttribute("hp");
	Pet pet = (Pet)request.getAttribute("pet");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 예약 상세 조회</title>
<style>
div.contents {width: 70%; height: 600px; top: calc(50% - 300px); text-align: center;}
div.info {display: inline-block; font-size: 18px; margin-top: 20px;}
table {width: 100%;}
td {border: 1px solid #000; padding: 5px 20px; color: #000;}
</style>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
	<div class="container">
		<div class="contents">
			<h2>예약 상세 조회</h2>
			<div class="info">
				<table class="main">
					<caption></caption>
					<tr>
						<td>동물병원</td>
						<td><%= hp.getHpName() %></td>
					</tr>
					<tr>
						<td>예약일시</td>
					<%
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
						String date = sdf.format(rs.getRsDate());
					%>
						<td><%= date %></td>
					</tr>
					<tr>
						<td>진료과목</td>
						<td><%= rs.getHmCate() %></td>
					</tr>
					<tr>
						<td>특이사항</td>
						<% if(rs.getRsMemo() != null){ %>
						<td><%= rs.getRsMemo() %></td>
						<% } else{ %>
						<td>없음</td>
						<% } %>
					</tr>
					<tr>
						<td>반려동물</td>
						<td><%= pet.getPetName() %></td>
					</tr>
				</table>
				<table class="add">
					<caption>병원 정보</caption>
					<tr>
						<td>위치</td>
						<% if(hp.getHpLoc2() == null){ %>
						<td>[<%= hp.getHpZip() %>] <%= hp.getHpLoc1() %></td>
						<% } else{ %>
						<td>[<%= hp.getHpZip() %>] <%= hp.getHpLoc1() %> <%= hp.getHpLoc2() %></td>
						<% } %>
					</tr>
					<tr>
						<td>연락처</td>
						<td><%= hp.getHpPhone() %></td>
					</tr>
					<tr>
						<td>진료과목</td>
						<td><%= rs.getHmCate() %></td>
					</tr>
					<tr>
						<td>반려동물</td>
						<td><%= pet.getPetName() %></td>
					</tr> 
				</table>
			</div>
		</div>
	</div>
</body>
</html>