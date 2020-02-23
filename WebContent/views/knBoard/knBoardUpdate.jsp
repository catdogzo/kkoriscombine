<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, java.sql.Date, photo.model.vo.*"%>
<%
	String title = request.getParameter("title");
	String con = request.getParameter("content");
	String nick = request.getParameter("knNick");
	int no = Integer.parseInt(request.getParameter("no"));
	ArrayList<String> images = new ArrayList<String>();
	for(int i = 1; i < images.size(); i++){
		images.add(request.getParameter("detailImg" + i) == null ? "" : "src=" + request.getContextPath() + "/thumbnail_uploadFiles/" + request.getParameter("detailImg" + i));
	}
	ArrayList<String> fIds = new ArrayList<String>();
	for(int i = 0; i < 4; i++){
		fIds.add(request.getParameter("detailImgId" + i) == null ? "" : request.getParameter("detailImgId" + i));
	}	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 지식 공유 글수정</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<style>
		#blogo{margin-left:350px; margin-top: 50px;}
		.outer{width: 800px; height: 650px; background: rgba(255, 255, 255, 0.4); margin-left: 400px; margin-right: auto; margin-top: auto;}
		.writeArea{width: 650px; height: 600px; margin-top: 80px; margin-left: 150px; margin-right: auto; padding: 10px; border: 1px solid #fcc6c9;}
		table{margin-left: 30px; margin-top: 3px; min-height: 550px;}
		table, th, td{word-spacing: 3px; padding: 3px;}
		table > tfoot > th, td{padding-top: 5px;}
		.input{font-family: inherit; width: 90%; border: 0; border-bottom: 1px solid #575756; outline: 0; background: transparent; transition: border-color 0.2s;}	
		table > tr> title{cursor: text;}
		title{cursor: text;}
		content{font-family: inherit; cursor: text; }
		.aleft{font-weight: 800;}
		tr > td > #title{font-weight: 800; color: gray;}
		#writeBorder{border: 1px solid #fcc6c9; width: 800px; height: 550px; }
		#kncon{align: auto; width: 850px; max-height: 680px; margin-left: 10px; margin-top: 10px; margin-right: 10px; margin-botton: 10px; overflow: auto;}
		#kncon > img {width:200px; height:200px;}
		#writeTd{min-width: 250px; }
		#writeBtn{font-size: 15px; margin-left: 50px;  text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
		#cancleBtn{font-size: 15px; text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
		div#photo{position: absolute; left: 400pt; margin-top: 10px;}
		#titleImgArea {width:180px; height:180px; border:1px dashed #fcc6c9; text-align:center; display:table-cell; vertical-align:middle; }
		#titleImgArea:hover, #contentImgArea1:hover, #contentImgArea2:hover, #contentImgArea3:hover {cursor:pointer;}
		#contentImgArea1, #contentImgArea2, #contentImgArea3 {width:180px; height:180px; border:1px dashed #fcc6c9; text-align:center; display:table-cell; vertical-align:middle;}
		figcaption {color: #5d5d5d; background: rgba(255, 223, 223, 0.2); padding: 1em; line-height: 1; position: absolute; left: 0; right: 0; bottom: 0;}
		figure {margin: 0; line-height: 0; position: relative;}
	</style>
</head>
<body>
<%@ include file="../common/layout.jsp" %>   
		<div class="outer">
			<img src="../../images/knb.png" id="blogo">
		<br>
		<!-- 지식 공유게시판 글수정 -->

		<form action="<%= request.getContextPath() %>/update.kn" method="post" encType="multipart/form-data">
			<div class="writeArea">			
				<table>
					<tr>
						<td class="aleft" rownum="2">제목
						</td>				
					</tr>
					<tr>
						<td><input type="text" size="50" name="title" class="input" value="<%= title %>"></td>
					</tr>					
<!-- 					<tr>
						<td class="aleft" width= "380px">글쓴이
						</td>
						<td class="aleft">날짜
						</td>
					</tr> -->
					<tr>
						<td class="aleft">내용</td>
					</tr>
					<tr>
						<td colspan="4">
							<textarea name="content" cols="75" rows="15" style="resize:none;"><%= con %></textarea>		
							<br><br>
							<% for(int i = 0; i < images.size(); i++){ %>
								<img id="contentImg<%=i%>" width="120" height="100" <%= images.get(i-1) %>>
								<input type="hidden" id="detailImgId<%=i%>" name="detailImgId<%=i%>" value="<%= fIds.get(i) %>">  
							<% } %>
						</td>
					</tr>
					<tr>
						<th colspan="4" class="knb_photo">
							<input type="submit" id="writeBtn" value="수정">
							<input type="button" onclick="location.href='<%= request.getContextPath() %>/list.kn'" id="cancleBtn" value="취소">
						</th>
					</tr>
				</table>				
				<br><br><br>			
			</div>	
			<input type="hidden" name="bNum" value="1">
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
					<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)">
					<input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this,2)">
					<input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this,3)">
					<input type="file" id="thumbnailImg4" multiple="multiple" name="thumbnailImg4" onchange="LoadImg(this,4)">
				</div>
			</div>
				<!-- 보낼 값 -->
				<input type="hidden" name="no" value="<%= no %>">
				<input type="hidden" name="title" value="<%= title %>">
				<input type="hidden" name="con" value="<%= con %>">
		</form>			
	</div>
	<script>
		// 내용 작성 부분의 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
		$(function(){
			$("#fileArea").hide();
			
			$("#titleImgArea").click(function(){
				$("#thumbnailImg1").click();
			});
			$("#contentImgArea1").click(function(){
				$("#thumbnailImg2").click();
			});
			$("#contentImgArea2").click(function(){
				$("#thumbnailImg3").click();
			});
			$("#contentImgArea3").click(function(){
				$("#thumbnailImg4").click();
			});
		});
		
		// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
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