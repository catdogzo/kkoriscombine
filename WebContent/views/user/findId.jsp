<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String findId = (String)request.getAttribute("findId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 아이디 찾기 완료</title>
<style>
	div.contents {width: 400px; height: 250px; top: calc(50% - 125px);}
	div.userInfo {margin: 40px 0; font-size: 18px; text-align: center;}
	div.userInfo > span.data {font-weight: 600; border-bottom: 1px solid #000;}
	div.text-button {text-align: center;}
	div.text-button > a {color: #000; font-size: 14px;}
	div.text-button > a:hover {text-decoration: underline;}
</style>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
	<div class="container">
		<div class="contents">
			<h2>아이디 찾기 완료</h2>
			<div class="userInfo">회원님의 아이디는 <span class="data"><%= findId %></span>입니다.</div>
			<div class="text-button"><a href="<%= request.getContextPath() %>/views/user/login.jsp">로그인 하기</a></div>
			<div class="text-button"><a href="<%= request.getContextPath() %>/views/user/searchPwd.jsp">비밀번호 찾기</a></div>
		</div>
	</div>
</body>
</html>