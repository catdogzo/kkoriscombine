<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 포인트</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<style>
<style>
	#blogo{margin-left:00px; margin-top: 50px;}
	#pointView{border-color: #AEDEFC; cursor: default;}
	#pointBtn{margin-left: 530px; margin-top: 20px;}
	.outer{width: 800px; height: 650px; background: rgba(255, 255, 255, 0.4); margin-left: 150px; margin-right: auto; margin-top: auto;}
	.writeArea{width: 600px; height: 550px; margin-top: 10px; margin-left: 180px; margin-right: auto; padding: 10px; border: 1px solid #FB929E;}
	table{margin-left: 30px; margin-top: 3px; max-width:536px;}
	table, th, td{word-spacing: 3px; padding: 3px;}
	tr > th{padding-bottom: 50px; padding-top: 20px; font-size: 20pt;}
	tbody > tr > td{padding-left : 40px; padding-bottom : 10px;}
	.pt{text-align: right;}
	.input{font-family: inherit; width: 100%; border: 0; outline: 0; background: transparent; transition: border-color 0.2s;}	
	#page{margin-left: 270px;}
	#pHistory{font-size: 15px; margin-left: 50px;  text-align: center; background-color: #575756; color: #FFF6F6; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#pHistory:hover {background: #ffe3e4; color: #575756;}
	#pUsing{font-size: 15px; text-align: center; background-color: #575756; color:#FFF6F6; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#pUsing:hover {background: #ffe3e4; color: #575756;}
</style>
</head>
<body>
	<%@ include file="../layout.jsp" %>
		<!-- 포인트 -->	
		<div class="outer">
			<img src="../../images/point.png" id="blogo" style="margin-left: 400px; margin-top: 50px;">
			<br>
			<input type="text" id="pointView" style="margin-left: 350px; margin-top: 50px; width: 220px; height: 30px;" placeholder="보유 포인트">
		<br>
		<div id="pointBtn">
			<input type="submit" id="pHistory" value="포인트 기록">
			<input type="submit" id="pUsing" value="포인트 사용">
		</div>
		<div class="writeArea">
			<form action="<%= request.getContextPath() %>/insert.no" method="post">
				<table>
					<thead>				
					<tr>
						<th colspan="3">포인트 기록
						</th>			
					</tr>
					</thead>
					<tr>
						<td style="width: 150px;">날짜</td>
						<td style="width: 200px;">게시글 등록</td>
						<td style="width: 50px;" class="pt">100pt</td>
					</tr>
					<tr>
						<td>날짜</td>
						<td>게시글 등록</td>
						<td class="pt">100pt</td>
					</tr>
					<tr>
						<td>날짜</td>
						<td>게시글 등록</td>
						<td class="pt">100pt</td>
					</tr>
					<tr>
						<td>날짜</td>
						<td>게시글 등록</td>
						<td class="pt">100pt</td>
					</tr>
					<tr>
						<td>날짜</td>
						<td>쿠폰 구매</td>
						<td class="pt">-3000pt</td>
					</tr>
					<tr>
						<td>날짜</td>
						<td>게시글 등록</td>
						<td class="pt">100pt</td>
					</tr>
					<tr>
						<td>날짜</td>
						<td>게시글 등록</td>
						<td class="pt">100pt</td>
					</tr>
					<tr>
						<td>날짜</td>
						<td>게시글 등록</td>
						<td class="pt">100pt</td>
					</tr>
					<tr>
						<td>날짜</td>
						<td>게시글 등록</td>
						<td class="pt">100pt</td>
					</tr>																																								
				</table>
				<br><br><br>
				<span id="page">[1] [2] [3] </span>
			</form>
			</div>
		</div>
									
		<script>
			$(function(){
				$('tbody td').mouseenter(function(){
					$(this).parent().css({'background':'#ffe3e4', 'cursor':'pointer'});
				}).mouseout(function(){
					$(this).parent().css("background", "none");
				});			
			});
		</script>
</body>
</html>