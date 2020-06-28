<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 후기 게시판</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<style>
	#blogo{margin-left:350px; margin-top: 50px;}
	.outer{width: 900px; height: 800px; background: rgba(255, 255, 255, 0.4); margin-left: 150px; margin-right: auto; margin-top: auto;}
	.tableArea{width: 800px; height: 650px; margin-top: 90px; margin-left: 120px; margin-right: auto; padding: 10px;}
	table > class{margin-left: 55px; margin-top: 30px; width: 680px; padding: 30px;}
	td{padding-bottom: 80px; padding-right:20px;}
	#page{margin-left: 350px;}
	#writeBtn{margin-left: 680px; margin-top: 15px; line-height: 30px; text-align: center; background-color: #ffdfdf; color: #5d5d5d; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#writeBtn:hover {background: #FFF6F6; color: #575756;}
	#searchBtn{line-height: 30px; text-align: center; background-color: #AEDEFC; color: #fff; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#searchBtn:hover {background: #FFF6F6; color: #575756;}
	.searchArea{margin-top: 50px;}
	select{font-size: 10pt; width: 100px; padding: .7em .5em; font-family: inherit; background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; -webkit-appearance: none; -moz-appearance: none; appearance: none; outline:none; border:1px solid #5d5d5d;}
	.search_input{width: 250px;  padding: 12px 24px; font-size: 14px; line-height: 18px; color: #575756; background-size: 18px 18px; border-radius: 50px; border: 1px solid #5d5d5d; backface-visibility: hidden; right:20px; outline:none;}
	td > div{width: 90%; height:90%;}
	td > div > img{width: 90%; height:90%;}
	td > div> span{font-size:14px; text-align: center;}
</style>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
		<div class="outer">
		<br>
		<!-- <h1>후기 게시판</h1> -->
		
		<img src="../../images/rvb.png" id="blogo">
		<div class="tableArea">
			<table id="table">
				<tbody>
				<tr>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
				</tr>	
				</tbody>
			</table>
			<input type="submit" id="writeBtn" value="글쓰기">	
			<br><br>
				<span id="page">[1] [2] [3] </span>
				<div class="searchArea" align="center">
					<select>
					<option value="제목">제목</option>
					<option value="내용">내용</option>
					</select>&nbsp;&nbsp;
					<span class="search_container">
	         		<input class="search_input" type="text" placeholder="Search">
	     			</span>&nbsp;&nbsp;	      
	     			 <input type="submit" id="searchBtn" value="검색">			
				</div>	
		</div>
	</div>
		<script>
			$(function(){
				$('table td div').mouseenter(function(){
					$(this).parent().css({'background':'#AEDEFC', 'cursor':'pointer'});
				}).mouseout(function(){
					$(this).parent().css("background", "none");
				});			
			});

			
		</script>
</body>
</html>