<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 후기 게시판 글보기</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.raty.js"></script>

<style>
	#blogo{margin-left:350px; margin-top: 50px;}
	.outer{width: 800px; height: 650px; background: rgba(255, 255, 255, 0.4); margin-left: 150px; margin-right: auto; margin-top: auto;}
	.writeArea{width: 650px; height: 600px; margin-top: 50px; margin-left: 150px; margin-right: auto; padding: 10px; border: 1px solid #AEDEFC;}
	table{margin-left: 30px; margin-top: 3px; min-height: 550px;}
	table, th, td{word-spacing: 3px; padding: 3px;}
	table > tfoot > th, td{padding-top: 5px;}
	.input{font-family: inherit; width: 90%; border: 0; border-bottom: 1px solid #575756; outline: 0; background: transparent; transition: border-color 0.2s;}	
	table > tr> title{cursor: text;}
	.aleft{font-weight: 800;}
	#writeTd{width: 300px;}
	content{font-family: inherit; cursor: text; }
	#writeBtn{font-size: 15px; margin-left: 50px;  text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#cancleBtn{font-size: 15px; text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	div#photo{position: absolute; left: 224pt;}
	#titleImgArea {width:180px; height:180px; border:1px dashed #AEDEFC; text-align:center; display:table-cell; vertical-align:middle; }
	#titleImgArea:hover, #contentImgArea1:hover, #contentImgArea2:hover, #contentImgArea3:hover {cursor:pointer;}
	#contentImgArea1, #contentImgArea2, #contentImgArea3 {width:180px; height:180px; border:1px dashed #AEDEFC; text-align:center; display:table-cell; vertical-align:middle;}
	figcaption {color: #5d5d5d; background: rgba(255, 223, 223, 0.2); padding: 1em; line-height: 1; position: absolute; left: 0; right: 0; bottom: 0;}
	figure {margin: 0; line-height: 0; position: relative;}
</style>
</head>
<body>
	<%@ include file="../layout.jsp" %>
		<div class="outer">
			<img src="../../images/rvb.png" id="blogo">
		<br>
		<!-- <h1>후기 게시판 글보기</h1> -->
		<form action="<%= request.getContextPath() %>/insert.th" method="post" encType="multipart/form-data">
			<div class="writeArea">	
					<table>
						<tr>
							<th colspan="3">제목
								<input type="text" size="50" name="title" class="input" style="visibility: hidden">
							</th>			
						</tr>
						<tr>
						<td></td>
						</tr>
						<tr>
							<td class="aleft">병원명
							</td>
							<td colspan="2" class="aleft">날짜
							</td>
							<td class="aleft">
								 <div id="star" ></div> <!-- 별점 -->
							</td>							
						</tr>
						<tr>
							<td class="aleft">내용</td>					
						</tr>
						<tr>
							<td colspan="4">
								<textarea name="content" cols="75" rows="15" style="resize:none;"></textarea>
							</td>
						</tr>
						<tfoot>
						<tr>
							<th colspan="4" class="knb_photo" style="padding-top:20px;"><input type="submit" id="writeBtn" value="수정">
								<input type="submit" id="cancleBtn" value="삭제">
							</th>
							<td>							
							</td>
						</tr>
						</tfoot>
					</table>							
				</div>
			</form>		
		</div>
		<script>
		// 별점 부분
			var i = jQuery.noConflict();
			var star = null;
			i(document).ready(function() {
	            $('div#star').raty({
	                path : "<%= request.getContextPath() %>/images",
	                width : 200,
	                half: false,
	                halfShow: true,
	                readOnly: true,
	                click: function(score, evt) {
	                	console.log(score);
	                }              
	            });
	        });
		        
		</script>
</body>
</html>