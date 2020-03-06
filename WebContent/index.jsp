<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인</title>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/> --%>
<style>
/* 컨테이너 */
div.container{position: absolute; top:0; left:0; padding-left:200px; height:100%; width:100%; box-sizing:border-box;}
/* 섹션이 현재 1개이기 때문에 100% 2개면 200%, 3개면 300% */
/* 참고	section:nth-of-type(1) {section 중 첫번째 section에 접근} */
section.main {position:relative; height:100%; width:100%; background: url(images/main-bg.jpg) no-repeat center center; background-size: contain;}
section.main > article.quickMenu > ul {position: absolute; top:calc(50% - 50px); left:calc(50% - 25%); width: 50%; height: 100px; display: flex;}
section.main > article.quickMenu {position: relative; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.2);}
section.main > article.quickMenu > ul > li {width: 25%; height:100px;}
section.main > article.quickMenu > ul > li > a {color: #5d5d5d; display:block; font-size: 5em; text-align:center;}
section.main > article.quickMenu > ul > li > a > span {display: block; font-size: 15px;}
section.main > article.quickMenu > ul > li > a:hover {color: #fb929e;}
</style>
</head>
<body>
	<%@ include file="views/common/layout.jsp" %>
	
	<div class="container">
    	<section class="main">
      		<article class="quickMenu">
	         	<ul>
	            	<li><a href="<%= request.getContextPath()%>/views/hospital/searchHp.jsp"><i class="fas fa-map-marker-alt"></i><span>검색</span></a></li>
	                <li><a href="<%= request.getContextPath() %>/list.rs"><i class="far fa-check-circle"></i><span>예약</span></a></li>
	                <li><a href="<%= request.getContextPath()%>/list.kn"><i class="far fa-comments"></i><span>커뮤니티</span></a></li>
	                <li><a href="#"><i class="far fa-calendar-alt"></i><span>일정</span></a></li>   
	         	</ul>
         	</article>
      	</section>
	</div>
</body>
</html>