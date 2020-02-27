<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="rvBoard.model.vo.*, java.util.ArrayList, java.sql.Date, photo.model.vo.*"  %>
<%
	RvBoard rv = (RvBoard)request.getAttribute("board");
	String id = rv.getUsId();
	String swriter = id.substring(0, 2);
	for(int i = 0; i < id.length(); i++) {
		swriter += "*";
	}
	ArrayList<Photo> pList = (ArrayList<Photo>)request.getAttribute("pList");
	
%>
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
	.outer{idth: 1000px; height: 900px; background: rgba(255, 255, 255, 0.4); margin-left: 320px; margin-right: auto; margin-top: auto;}
	.writeArea{width: 900px; height: 800px; margin-top: 80px; margin-left: 150px; margin-right: auto; padding: 10px; border: 1px solid #AEDEFC;}
	table{margin-left: 30px; margin-top: 3px; min-height: 550px;}
	table, th, td{word-spacing: 3px; padding: 3px;}
	table > tfoot > th, td{padding-top: 5px;}
	.input{font-family: inherit; width: 90%; border: 0; border-bottom: 1px solid #575756; outline: 0; background: transparent; transition: border-color 0.2s;}	
	table > tr> title{cursor: text;}
	.aleft{font-weight: 800;}
	#writeTd{width: 300px;}
	#writeBorder{border: 1px solid #AEDEFC; width: 800px; height: 550px; }
	#rvt{font-size: 25px; font-weight: 900; text-align: center;}
	#rvcon{align: auto; width: 850px; max-height: 680px; margin-left: 10px; margin-top: 10px; margin-right: 10px; margin-botton: 10px; overflow: auto;}
	#rvcon > img {width:200px; height:200px;}
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
<%@ include file="../common/layout.jsp" %> 
		<div class="outer">
		<img src="<%= request.getContextPath() %>/images/rvb.png" id="blogo">
		<br>
		<!-- <h1>후기 게시판 글보기</h1> -->
		<form action="<%= request.getContextPath() %>/rvBoard/rvBoardUpdate.jsp" id="detailForm" method="post">
			<div class="writeArea">	
					<table>
						<tr>
							<td class="aleft" colspan="2">제목
								<input type="hidden" value="<%= rv.getRvNum() %>" name="no">
								<span id="rvt"><input type="text" size="0" name="title" class="input" style="visibility: hidden"><%=rv.getRvTitle() %></span>
							</td>			
							<td class="aleft">글쓴이
								<%= swriter %></td>
						</tr>
						<tr>
							<td class="aleft" style="max-width:300px;">병원명
								<%= rv.getHpId() %>
							</td>
							<td colspan="2" class="aleft">날짜
								<%= rv.getRvDate() %>
							</td>
							<td>
								 <div id="star" ></div> <!-- 별점 -->
							</td>							
						</tr>
						<tr>
							<td class="aleft">내용</td>					
						</tr>
						<tr>
							<td colspan="4">
							<div id = "writeBorder">
								<div id = "rvcon">
									<%= rv.getRvCon() %>
									<br><br>
									<% if(pList != null) { %>
										<% for(int i = 0; i < pList.size(); i++){ %>
											<img id="detailImg" class="detailImg" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= pList.get(i).getPhChng() %>">
											<input type="hidden" value="<%= pList.get(i).getPhNum() %>" name="detailImg<%= i %>">
										<% } %>
									<% }  %>
								</div>
							</div>	
							</td>
						</tr>
						<tfoot>
						<tr>
							<% if(loginAu != null && loginUser.getUsId().equals(rv.getUsId())) {%>
							<th colspan="4" class="rvb_photo" style="padding-top:20px;"><input type="submit" id="writeBtn" value="수정">
								<input type="button" onclick="deleteRv();" id="cancleBtn" value="삭제">
							</th>
							<% } %>
							<td>							
							</td>
						</tr>
						</tfoot>
					</table>	
				<!--  넘길 값 -->				
				<input type="hidden" value="<%= rv.getRvTitle() %>" name="title">		
				<input type="hidden" value="<%= rv.getRvCon() %>" name="content">	
				<input type="hidden" value="<%= rv.getHpId() %>" name="hpName">
				<input type="hidden" value="<%= rv.getUsId() %>" name="writer">		
				<input type="hidden" value="<%= rv.getRvStar() %>" name="star">		
				<input type="hidden" value="<%= rv.getRvDate() %>" name="date">									
				</div>
			</form>		
		</div>
		<script>
		// 별점 부분
			var i = jQuery.noConflict();
			i(document).ready(function() {
	            $('div#star').raty({
	                path : "<%= request.getContextPath() %>/images",
	                score : <%= rv.getRvStar() %>,
	                width : 200,
	                half: false,
	                halfShow: true,
	                readOnly: true
	            });
	        });
			
	    	// 삭제
		      function deleteRv(){
		         var bool = confirm('정말로 삭제하시겠습니까?');
		         if(bool){
		            $('#detailForm').attr('action', '<%= request.getContextPath() %>/delete.rv');
		            $('#detailForm').submit();
		         }
		      }   		        
		</script>
</body>
</html>