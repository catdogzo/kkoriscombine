<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.sql.Timestamp, java.text.SimpleDateFormat"%> 
<%
	String hpName = request.getParameter("hpName");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(request.getParameter("rsDate"));
%>    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 후기 게시판 글쓰기</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.raty.js"></script>
	<style>
		#blogo{margin-left:350px; margin-top: 50px;}
		.outer{width: 800px; height: 650px; background: rgba(255, 255, 255, 0.4); margin-left: 400px; margin-right: auto; margin-top: auto;}
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
		div#photo{position: absolute; left: 400pt;}
		#titleImgArea {width:180px; height:180px; border:1px dashed #AEDEFC; text-align:center; display:table-cell; vertical-align:middle; }
		#titleImgArea:hover, #contentImgArea1:hover, #contentImgArea2:hover, #contentImgArea3:hover {cursor:pointer;}
		#contentImgArea1, #contentImgArea2, #contentImgArea3 {width:180px; height:180px; border:1px dashed #AEDEFC; text-align:center; display:table-cell; vertical-align:middle;}
		figcaption {color: #5d5d5d; background: rgba(255, 223, 223, 0.2); padding: 1em; line-height: 1; position: absolute; left: 0; right: 0; bottom: 0;}
		figure {margin: 0; line-height: 0; position: relative;}
	</style>
</head>
<body>
<%@ include file="../common/layout.jsp" %> 
	<div class="outer">
		<img src="../../images/rvb.png" id="blogo">
	<br>
	<!-- <h1>후기 게시판 글쓰기</h1> -->
	<form action="<%= request.getContextPath() %>/write.rv" method="post" encType="multipart/form-data">
		<div class="writeArea">	
			<table>
				<tr>
					<th colspan="3">제목
						<input type="text" size="50" name="title" class="input">
					</th>			
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td class="aleft">병원명
						<input type="hidden" name="hospital" value="<%=hpName%>">
					</td>
					<td colspan="2" class="aleft">날짜
						<input type="hidden" name="date" value="<%= date %>">
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
					<th colspan="4" class="knb_photo" style="padding-top:20px;"><input type="submit" id="writeBtn" value="글쓰기">
					<button type="button" id="cancleBtn" onclick="location.href='<%= request.getContextPath() %>/list.rv'">취소</button>
					</th>
					<td>							
					</td>
				</tr>
				</tfoot>
			</table>				
			<br><br>
			<div id = "photo">
				<div id="titleImgArea">
					<figure>
						<img id="titleImg" width="180" height="180" style= "border:0">
						<figcaption>사진 첨부</figcaption>
					</figure>
				</div>
				<div id="contentImgArea1">
					<figure>
						<img id="contentImg1" width="180" height="180" style= "border:0"> 
						<figcaption>사진 첨부</figcaption>
					</figure>
				</div>
				<div id="contentImgArea2">
					<figure>
						<img id="contentImg2" width="180" height="180" style= "border:0"> 
						<figcaption>사진 첨부</figcaption>
					</figure>
				</div>
				<div id="contentImgArea3">
					<figure>
						<img id="contentImg3" width="180" height="180" style= "border:0"> 
						<figcaption>사진 첨부</figcaption>
					</figure>
				</div>
				<div id="fileArea">
					<input type="file" id="thumb1" multiple="multiple" name="thumb1" onchange="LoadImg(this,1)">
					<input type="file" id="thumb2" multiple="multiple" name="thumb2" onchange="LoadImg(this,2)">
					<input type="file" id="thumb3" multiple="multiple" name="thumb3" onchange="LoadImg(this,3)">
					<input type="file" id="thumb4" multiple="multiple" name="thumb4" onchange="LoadImg(this,4)">
				</div>
			</div>				
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
	                click: function(score, evt) {
	                	console.log(score);
	                }              
	            });
	        });
		
			// 파일 첨부 창
			$(function(){
				$("#fileArea").hide();
				
				$("#titleImgArea").click(function(){
					$("#thumb1").click();
				});
				$("#contentImgArea1").click(function(){
					$("#thumb2").click();
				});
				$("#contentImgArea2").click(function(){
					$("#thumb3").click();
				});
				$("#contentImgArea3").click(function(){
					$("#thumb4").click();
				});
			});
			
			// 미리 보기
			function LoadImg(value, num){
				if(value.files && value.files[0]){
					var reader = new FileReader();
					
					reader.onload = function(e){								
						switch(num){
						case 1: 
							$("#titleImg").attr("src", e.target.result);
							break;
						case 2:
							$("#contentImg1").attr("src", e.target.result);
							break;
						case 3: 
							$("#contentImg2").attr("src", e.target.result);
							break;
						case 4:
							$("#contentImg3").attr("src", e.target.result);
							break;
						}
					}
					
					reader.readAsDataURL(value.files[0]);
				}
			}
	        
		</script>
</body>
</html>