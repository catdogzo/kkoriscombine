<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Hospital hp = (Hospital)request.getAttribute("hp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 병원 예약</title>
<style>
	div.contents {width: 100%; height: 100%;}
	
	div.hpInfo, form#rsForm {position: relative; width: 100%; padding: 0 2%; height: 400px; margin-top: 20px;}
	div.hpInfo > div, form#rsForm > div {display:inline-block; width: 45%; height: 100%; vertical-align: middle; margin: 0 2%;}
	div.hpInfo > div.left.border > p {margin-top: 164px;}
	div.hpInfo > div.hpImg > img {width: 100%;}
	div.hpInfo > div.right > div.right-contents > p {font-size: 18px; margin: 10px 0;}
	div.hpInfo > div.right > div.right-contents > p.hpName {font-size: 24px; font-weight: 600; margin: 30px 0;}
	div.hpInfo > div.right > div.right-contents > p.hpIntro {font-size: 16px; margin-top: 20px;}
	div.right-contents {}
	p {text-align: center; color: #000;}
	.border {border: 1px solid #000;}
	
	
</style>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
	<div class="container">
		<div class="contents">
			<h2>병원 예약</h2>
			<div class="hpInfo">
				<% if(hp.getHpPhoto() == null){ %>
				<div class="left border"><p>등록된 사진이 없습니다.</p></div>
				<% } else{%>
				<div class="left hpImg"><img src="<%= hp.getHpPhoto()%>"></div>
				<% } %>
				<div class="right">
				<div class="right-contents">
					<p class="hpName"><%= hp.getHpName() %></p>
					<p>[<%= hp.getHpZip() %>] <%= hp.getHpLoc1() %> <%=hp.getHpLoc2() %></p>
					<p><i class="fas fa-phone-alt"></i> <%= hp.getHpPhone() %></p>
					<p>
						진료시간: <%= hp.getHpStart() %>:00 ~ <%= hp.getHpEnd() %>:00
						<% if(!hp.getHpLunch().equals("noTime")){ %>
						<br>점심시간: <%= hp.getHpLunch() %>:00 ~ <%= Integer.parseInt(hp.getHpLunch())+1 %>:00
						<% } %>
					</p>
					<% if(hp.getHpIntro() != null){ %>
					<p class="hpIntro"><%= hp.getHpIntro() %></p>
					<% } %>
				</div>
				</div>
			</div>
			<form action="<%= request.getContextPath() %>/complete.rs" method="post" name="rsForm" id="rsForm">
				<div class="left">
					
				</div>
				<div class="right">
					
				</div>
				<% if(loginUser != null){ %>
				<% } %>
			</form>
		</div>
	</div>
</body>
</html>