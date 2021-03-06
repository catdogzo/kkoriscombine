<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 지식인 게시판</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.contextMenu.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.contextMenu.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.ui.position.js"></script>
<style>
	#blogo{margin-left:300px; margin-top: 50px;}
	.outer{width: 900px; height: 800px; background: rgba(255, 255, 255, 0.4); margin-left: 150px; margin-right: auto; margin-top: auto;}
	.tableArea{width: 800px; height: 650px; margin-top: 30px; margin-left: 120px; margin-right: auto; padding: 10px;}
	table > class{margin-left: 55px; margin-top: 30px; width: 680px;}
	table, th, td{word-spacing: 5px; padding: 5px; height: 30px;}
	thead th{border-bottom: 1px solid  #5d5d5d;; background-color: #fcc6c9; height: 30px; font-weight: 600; text-align: center;}
	tbody th{font-weight: 600; background-color: #fff6f6; border-bottom: 1px solid #5d5d5d; text-align: center;}
	tbody td{border-bottom: 1px solid #5d5d5d; font-family: inherit; text-align: center; font-size: 11pt;}
	#writeBtn{margin-left: 600px; margin-top: 15px;}
	#page{margin-left: 300px;}
	span > #searchBtn{line-height: 30px; text-align: center; background-color: #ffe3e4; color:#ff4e59; border: 1px solid #fcc6c9; border-radius: 5px; width: 80px; height: 35px;  position: fixed;}
	.searchArea{margin-top: 50px; position: absolute; left: 400px;}
	select{font-size: 10pt; width: 100px; padding: .7em .5em; font-family: inherit; background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; -webkit-appearance: none; -moz-appearance: none; appearance: none; outline:none; border:1px solid #5d5d5d;}
	.search_input{width: 250px;  padding: 12px 24px; font-size: 14px; line-height: 18px; color: #575756; background-size: 18px 18px; border-radius: 50px; border: 1px solid #5d5d5d; backface-visibility: hidden; right:20px; outline:none;}
	
</style>
</head>
<body>
	<%@ include file="../layout.jsp" %>
		<div class="outer">
		<br>
		<!-- <h1>지식 공유게시판</h1> -->
		
		<img src="../../images/knb.png" id="blogo">
		<div class="tableArea">
			<table id="table">
				<thead> <!-- 게시판 라벨 부분 -->
				<tr>
					<th width="40px">No</th>
					<th width="320px" class="title">제목</th>
					<th width="110px">작성자</th>
					<th width="110px">작성일</th>
					<th>조회수</th>
				</tr>
				</thead>
				<tbody> <!-- 게시판 내용 부분-->
				<tr>
					<th>9</th>
					<td class="title">asdasdsdasdasdsad</td>
					<td><span id="conBtn">kh</span></td>
					<td>s</td>
					<td>d</td>
				</tr>
				<tr>
					<th>8</th>
					<td class="title">d</td>
					<td>khkh</td>
					<td>d</td>
					<td>d</td>
				</tr>
				<tr>
					<th>7</th>
					<td class="title">d</td>
					<td>kh</td>
					<td>d</td>
					<td>d</td>
				</tr>
				<tr>
					<th>6</th>
					<td class="title">d</td>
					<td>kh</td>
					<td>d</td>
					<td>d</td>
				</tr>
				<tr>
					<th>5</th>
					<td class="title">d</td>
					<td>kh</td>
					<td>d</td>
					<td>d</td>
				</tr>
				<tr>
					<th>4</th>
					<td class="title">d</td>
					<td>kh</td>
					<td>d</td>
					<td>d</td>
				</tr>
				<tr>
					<th>3</th>
					<td class="title">d</td>
					<td>kh</td>
					<td>d</td>
					<td>d</td>
				</tr>
				<tr>
					<th>2</th>
					<td class="title">d</td>
					<td>kh</td>
					<td>d</td>
					<td>d</td>
				</tr>
				<tr>
					<th>1</th>
					<td class="title">d</td>
					<td>kh</td>
					<td>d</td>
					<td>d</td>
				</tr>
				<tr>
					<th>0</th>
					<td class="title">d</td>
					<td>kh</td>
					<td>d</td>
					<td>d</td>
				</tr>
				</tbody>
			</table>
			<input type="submit" id="writeBtn" value="글쓰기">	
			<br><br>
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
		<script>
		// 버튼 관련
			$(function(){
				$('tbody td').mouseenter(function(){
					$(this).parent().css({'background':'#ffe3e4', 'cursor':'pointer'});
				}).mouseout(function(){
					$(this).parent().css("background", "none");
				});			
			});
			$('button').mouseenter(function(){
				$('button').css({'background':'#ffe3e4', 'cursor':'pointer'});
			}).mouseout(function(){
				$('button').parent().css("background", "none");
			});

			// context메뉴
			var i = jQuery.noConflict();
		    i(function() {
		        $.contextMenu({
		            selector: '#conBtn', 
		            trigger: 'left',
		            callback: function(key, options) {
		                var m = "clicked: " + key;
		            },
		            items: {
		                "쪽지 보내기":{name: "쪽지 보내기"},
		                "작성글 조회":{name: "작성글 조회"},
		                "닫기": {name: "닫기", icon: function($element, key, item){ return 'context-menu-icon context-menu-icon-quit'; }}
		            }
		        });
		    }); 
		    
		    // 상세보기
			
		</script>
</body>
</html>