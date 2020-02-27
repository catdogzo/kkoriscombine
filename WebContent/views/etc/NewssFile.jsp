<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 지식인 게시판</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<style>
	
	/* select 옵션  */
	div > select{font-size: 10pt; width: 100px; padding: .7em .5em; font-family: inherit; background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; -webkit-appearance: none; -moz-appearance: none; appearance: none; outline:none; border:1px solid #5d5d5d;}
	/* 검색 창(input type text를 css로 꾸민 것 'search_input'은 클래스 이름)*/
	div > span > .search_input{width: 250px;  padding: 12px 24px; font-size: 14px; line-height: 18px; color: #575756; background-size: 18px 18px; border-radius: 50px; border: 1px solid #5d5d5d; backface-visibility: hidden; right:20px; outline:none;}
	
		/* div */
	.searchArea{margin-top: 50px;}	
	#searchBtn{line-height: 30px; text-align: center; background-color: #ffe3e4; color:#ff4e59; border: 1px solid #fcc6c9; border-radius: 5px; width: 80px; height: 35px;  position: fixed;}
</style>
</head>	
<body>
	<%@ include file="../layout.jsp" %>
		<div class="outer">
		<br>
		<!-- <h1>지식 공유게시판</h1> -->
		
		<img src="../../images/knb.png" id="blogo">
		<div class="tableArea">

				<span id="page">[1] [2] [3] </span>
				<div class="searchArea" align="center">
					<select>
					<option value="작성자">작성자</option>
					<option value="제목">제목</option>
					<option value="내용">내용</option>
					</select>&nbsp;&nbsp;
					<span class="search_container">
	         		<input class="search_input" type="text" placeholder="Search">
	     			</span>&nbsp;&nbsp;	      
	     			 <input type="submit" value="검색">			
				</div>	
		</div>
	</div>


			
		</script>
</body>
</html>