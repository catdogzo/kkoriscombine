<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/layout.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	section.UMmain {
		position:relative; 
		height:100%; width:100%; 
		background-size: contain;
	}
	
	section.UMmain > article.UMquickMenu {
		position: relative; width: 100%; height: 100%; background: white;
	}
	
	section.UMmain > article.UMquickMenu > h1{
		position: relative;
		margin: 0 auto;
		font-size: 40px;
		width: 300px;
		text-align: center;
		vertical-align: miidle;
	}
	
	section.UMmain > article.UMquickMenu > table.mbTable{
		text-align: center;
		position: relative;
		margin: 0 auto;
		width: 80%;
	}
	section.UMmain > article.UMquickMenu > table.mbTable th{text-align: center; background: rgba(255, 167, 163, 0.63);}
	section.UMmain > article.UMquickMenu > table.mbTable tr{height: 35px; border-bottom: 1px solid lightgray;}
	section.UMmain > article.UMquickMenu > table.mbTable td{padding: 7px;}
	
	section.UMmain > article.UMquickMenu > div.pagingArea{
		position: relative;
		margin: 0 auto;
		min-width:420px;
		text-align: center;
		vertical-align: middle;
	}
	section.UMmain > article.UMquickMenu > div.pagingArea > button{
		text-align: center;
		border: 0px;
		min-width: 20px;
		height: 24px;
	}
	
	section.UMmain > article.UMquickMenu > div.search{
		position: relative;
		margin: 0 auto;
		height: 100px;
		font-size: 18px;
		width: 440px;
		text-align: center;
		vertical-align: middle;
		
	}
	section.UMmain > article.UMquickMenu > div.search > select{
		font-size: 10pt; width: 100px; 
		padding: .7em .5em; font-family: inherit; 
		background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; 
		-webkit-appearance: none; 
		-moz-appearance: none; appearance: none; outline:none; border:1px solid #5d5d5d;
	}
	section.UMmain > article.UMquickMenu > div.search > input.search_input{
		width: 250px;  padding: 12px 24px; 
		font-size: 14px; line-height: 18px; 
		color: #575756; background-size: 18px 18px; 
		border-radius: 50px; border: 1px solid #5d5d5d; 
		backface-visibility: hidden; right:20px; outline:none;
	}
	section.UMmain > article.UMquickMenu > div.search > input[type="submit"] {min-width: 50px; height: 30px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 14px; font-weight: 600; border: none; border-radius: 5px;}
	section.UMmain > article.UMquickMenu > div.search > input[type="submit"]:hover {background: #fb929e; color: #fff;}
	
</style>
</head>
<body>
	<div class="container">
    	<section class="UMmain">
			<article class="UMquickMenu" style="background: white;">
			
				<br><br>
				
				<h1>회원 관리 페이지</h1>
				
				<br>
				
				<table class="mbTable" id=mbTable>
					<thead>
						<tr>
							<th style="min-width: 90px;">아이디</th>
							<th style="min-width: 90px;">이름</th>
							<th style="min-width: 90px;">닉네임</th>
							<th style="min-width: 70px;">성별</th>
							<th style="min-width: 170px;">연락처</th>
							<th style="min-width: 80px;">탈퇴여부</th>
						</tr>
					</thead>
					<tbody>
					<%for(int i = 0; i < 15; i++){ %>
						<tr>
							<td>kwakjm</td>
							<td>곽준목</td>
							<td>kapoka</td>
							<td>남</td>
							<td>010-2561-6257</td>
							<td>가입중</td>
						</tr>
					<%} %>
					</tbody>
				</table>
				
				<br>
				
				<div class="pagingArea" align="center">
					<!-- 맨 처음으로 버튼 -->
					<button>&lt;&lt;</button>
					<!-- 이전 페이지로 -->
					<button>&lt;</button>
					<!-- 10개의 페이지목록 -->
					<button>[1]</button>
					<button>[2]</button>
					<button>[3]</button>
					<button>[4]</button>
					<button>[5]</button>
					<button>[6]</button>
					<button>[7]</button>
					<button>[8]</button>
					<button>[9]</button>
					<button>[10]</button>
					<!-- 다음 페이지로 -->
					<button>&gt;</button>
					<!-- 맨끝으로버튼 -->
					<button>&gt;&gt;</button>
				</div>
				
				<br>
				
				<div class=search>
					<select>
						<option>아이디</option>
						<option>병원이름</option>
						<option>원장이름</option>
						<option>연락처</option>
						<option>위치</option>
						<option>탈퇴여부</option>
					</select>
					<input type=text class=search_input placeholder="Search">
					<input type="submit" value="검색">
				</div>
			</article>
		</section>
	</div>
	
</body>
</html>