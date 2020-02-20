<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hospital.model.vo.*" %>
<%
	Hospital hp = (Hospital)request.getAttribute("hp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 병원 예약</title>
<style>
	div.hpImg {width: 300px; height: 400px;}
</style>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
	<div class="container">
		<div class="contents">
			<h2>병원 예약</h2>
			<div class="hpImg"><img src="<%= hp.getHpPhoto()%>"></div>
			<div class="hpInfo">
				<p class="hpName"><%= hp.getHpName() %></p>
				<p><%= hp.getHpDName() %> 원장님</p>
				<p>[<%= hp.getHpZip() %>] <%= hp.getHpLoc1() %> <%=hp.getHpLoc2() %></p>
				<p><%= hp.getHpPhone() %></p>
				<p>진료시간: <%= hp.getHpStart() %> ~ <%= hp.getHpEnd() %></p>
				<% if(!hp.getHpLunch().equals("noTime")){ %>
				<p>점심시간: <%= hp.getHpLunch() %></p>
				<% } %>
				<pre><%= hp.getHpIntro() %></pre>
			</div>
			<form action="<%= request.getContextPath() %>/complete.rs" method="post" name="rsForm" in="rsForm">
			
			</form>
		</div>
	</div>
</body>
</html>