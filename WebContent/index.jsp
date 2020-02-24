<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="views/common/layout.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 KKORISCOMBINE</title>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<link href="css/index.css"  rel="stylesheet" type="text/css">
<style>
	<% if(loginAu != null && loginAu.getAuKind().equals("ADMIN")){%>
	section.main > article.quickMenu > ul.userMenu{position: absolute; top:calc(40% - 55px); left:calc(50% - 25%); width: 50%; height: 100px; display: flex;}
	section.main > article.quickMenu > ul.insertMenu{position: absolute; top:calc(40% + 55px); left:calc(50% - 25%); width: 50%; height: 100px; display: flex;}
	<%}%>
	
</style>
</head>
<body>
	
	<div class="container">
    	<section class="main">
      		<article class="quickMenu">
	         	<ul class=userMenu>
	            	<li><a href="#"><i class="fas fa-map-marker-alt"></i><span>검색</span></a></li>
	            	<%if(loginAu != null && loginAu.getAuKind().equals("HOPITAL")){ %>
	                <li><a href="views/hospitalReservation/hospitalReservation.jsp"><i class="far fa-check-circle"></i><span>예약</span></a></li>
	                <%} else {%>
	                <li><a href="#"><i class="far fa-check-circle"></i><span>예약</span></a></li>
	                <%} %>
	                <li><a href="<%= request.getContextPath() %>/list.kn"><i class="far fa-comments"></i><span>커뮤니티</span></a></li>
	                <li><a href="#"><i class="far fa-calendar-alt"></i><span>일정</span></a></li>
	            </ul>
	            <%if(loginAu != null && loginAu.getAuKind().equals("ADMIN")) {%>
	            <ul class=insertMenu>
					<li><a href="views/userManagement/userManagementListView.jsp"><i class="fas fa-user-friends"></i><span>회원관리</span></a></li>
					<li><a href="<%=request.getContextPath() %>/noticeList.bo"><i class="fas fa-volume-up"></i><span>공지사항</span></a></li>
					<li><a href="views/hospitalManagement/hospitalManagementListView.jsp"><i class="far fa-list-alt"></i><span>병원목록</span></a></li>
				</ul>
				<%} %>
         	</article>	
      	</section>
	</div>
</body>
</html>