<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="knBoard.model.vo.*, java.util.ArrayList, java.sql.Date, photo.model.vo.Photo"%>
<%
	KnBoard kn = (KnBoard)request.getAttribute("board");
	ArrayList<KnReply> list = (ArrayList<KnReply>)request.getAttribute("list");
	ArrayList<Photo> pList = (ArrayList<Photo>)request.getAttribute("pList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 지식 공유 글보기</title>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/justcontext.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
	<style>
		#blogo{margin-left:350px; margin-top: 50px;}
		.outer{width: 1000px; height: 900px; background: rgba(255, 255, 255, 0.4); margin-left: 350px; margin-right: auto; margin-top: auto;}
		.writeArea{width: 900px; height: 800px; margin-top: 80px; margin-left: 150px; margin-right: auto; padding: 10px; border: 1px solid #fcc6c9;}
		#tableD{margin-left: 30px; margin-top: 3px; min-height: 550px;}
		#tableD.th, td{word-spacing: 3px; padding: 4px;}
		#tableD.tfoot > th, td{padding-top: 5px;}
		#knt{font-size: 25px; font-weight: 900; text-align: center;}
		content{font-family: inherit;}
		.aleft{font-weight: 600; min-width:300px;}
		tr > td > #title{font-weight: 800; color: gray;}
		#writeBorder{border: 1px solid #fcc6c9; width: 800px; height: 550px; }
		#kncon{align: auto; width: 850px; max-height: 680px; margin-left: 10px; margin-top: 10px; margin-right: 10px; margin-botton: 10px; overflow: auto;}
		#kncon > #imgArea > img {width:150px; height:150px; align-content: center;}
		#writeTd{min-width: 250px; }
		#updateBtn{margin-left: 50px;  text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
		#deleteBtn{font-size: 14px; font-weight: 600; text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px; background: #ffdfdf; color: #5d5d5d;}
		#deleteBtn:hover{cursor: pointer; background: #fb929e; color: #fff;}	
		#listBtn{font-size: 15px; font-weight: 600; text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px; background: #ffdfdf; color: #5d5d5d;}
		#listBtn:hover{cursor: pointer; background: #fb929e; color: #fff;}		
		table#knb_reply{margin-left:150px; padding: 0;}
		tr#knr_area{max-height: 80px;}
		#knb_replyTable {margin-left: 250px; padding-top: 0px; padding-bottom: 0px;}
		#knb_replyTable > tr {max-height: 100px;}
		#RreplyBtn{min-width: 100px; height: 30px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 14px; font-weight: 600; border: none; border-radius: 5px;}
		#RreplyBtn:hover {background: #fb929e; color: #fff;}
		#reSendBtn{width: 60px; height: 60px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 16px; font-weight: 600; border: none; border-radius: 5px;}
		#likeBtn:hover{cursor: pointer;}
		.jctx {display: none; z-index: 1000; position: absolute; overflow: hidden; border: 1px solid #595959; white-space: nowrap; font-family: sans-serif; font-size: 14px; border-radius: 2px; padding: 0; opacity: 0; -webkit-transition:opacity 200ms; -moz-transition:opacity 200ms; -o-transition:opacity 200ms;  -ms-transition:opacity 200ms;}
		.jctx-white {background: white; color: black;}
		.jctx-white-shadow {box-shadow: 0 0 15px gray;}
		.jctx li {padding: 5px 8px; cursor: pointer;}
		.jctx li.disabled {color: darkgrey; cursor: default;}
		.jctx-white li:hover {background-color: #fcc6c9;}
		.jctx-white li.disabled:hover {background-color: lightgrey;}
		.jctx i {padding-right: 6px;}
		.jctx hr {background-color: grey; height: 1px; border: 0; margin: 2px 0px;}
		
	</style>
</head>
<body>
<%@ include file="../common/layout.jsp" %>   
	<div class="outer">
		<img src="<%= request.getContextPath() %>/images/knb.png" id="blogo">
	<br>

	<form action="views/knBoard/knBoardUpdate.jsp" id="detailForm" method="post">
		<div class="writeArea">			
			<table id="tableD" >
				<tr>	
					<td class="aleft">제목 &nbsp;&nbsp;
						<span id = "knt"><%= kn.getknTitle() %></span>
					</td>				
				</tr>				
				<tr>
					<td class="aleft" width= "300px">글쓴이&nbsp;&nbsp;
						<span class="jctx-host jctx-id-foo" style="font-size:15px; font-weight: 300"><%= kn.getUsNick() %></span>
					</td>
					<td class="aleft">날짜&nbsp;&nbsp;
						<span style="font-size:15px; font-weight: 300"><%= kn.getKnDate() %></span>
					</td>
				</tr>
				<tr>
					<td class="aleft">내용</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id = "writeBorder">
							<div id = "kncon">
								<%= kn.getKnCon() %>
								<br><br>
								<div id = "imgArea">
									<% if(pList != null) { %>
										<% for(int i = 0; i < pList.size(); i++){ %>
											<img id="detailImg" class="detailImg" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= pList.get(i).getPhChng() %>">
											<input type="hidden" value="<%= pList.get(i).getPhChng() %>" name="detailImg<%= i %>">
											<input type="hidden" value="<%= pList.get(i).getPhNum() %>" name="detailImgId<%= i %>">
											
										<% } %>
									<% }  %>
								</div>
							</div>
						</div>	
					</td>
				</tr>
					<tr>
						<td colspan="4" id="writeTd">
						<% if(loginAu != null && loginUser.getUsNick().equals(kn.getUsNick())) {%>						
							<input type="submit" id="updateBtn" value="수정">
							<input type="button" onclick="deleteKn();" id="deleteBtn" value="삭제">
							<input type="button" onclick="location.href='javascript:history.back();'" id="listBtn" value="목록으로">												
						<% } else { %>
							<input type="button" onclick="location.href='javascript:history.back();'" id="listBtn" value="목록으로"
							style= "margin-left: 400px; margin-top: 30px;">									
						<% } %>							
						</td>					
					</tr>
				</table>
			<!--  넘길 값 -->				
				<input type="hidden" value="<%= kn.getKnNum() %>" name="no">
				<input type="hidden" value="<%= kn.getknTitle() %>" name="title">		
				<input type="hidden" value="<%= kn.getKnCon() %>" name="content">	
				<input type="hidden" value="<%= kn.getUsNick() %>" name="knNick">										
			</div>		
			</form>	
			<% if(loginAu != null ){%>						
			<table id="knb_reply">
				<thead>
				<tr id="knr_area">
					<td>
						<textarea rows="3" cols="110" id="rCon" style="resize: none; border: 1px solid #fcc6c9;"></textarea>	
					</td>
					<td><button id="RreplyBtn">댓글 등록</button></td>
				</tr>
			</table>
		<% } %>
		<% if(loginAu != null ){%>				
		<div id="knb_replyArea">						
			<table id="knb_replyTable">
				<% if(list.isEmpty()){ %>
					<tr><td colspan="3"></td></tr>
				<% } else { %>
					<% for(int i = 0; i < list.size(); i++){ %>
						<tr id="knrWriter">
							<td width="100px" height="50px;"><%= list.get(i).getUsId() %></td>							
							<td width="400px" height="50px;"><%= list.get(i).getKnrCon() %></td>
							<td width="200px" height="50px;"><%= list.get(i).getKnrDate() %></td>
						</tr>						
					<% } %>						
				<% } %>						
			</table>
		</div>
			<script>

			    // 댓글 관련						
	    		$('#RreplyBtn').click(function(){	
		    		var id = '<%= loginUser.getUsId() %>';
					var no = <%= kn.getKnNum() %>;		
					var content = $('#rCon').val();		
				
					$.ajax({
						url: '<%= request.getContextPath() %>/insertReply.kn',
						type: 'post',
						data: {id: id, content: content, no: no},
						success: function(data){
							$knb_replyTable = $('#knb_replyTable');
							$knb_replyTable.html("");
							for(var i in data){
								var $tr = $('<tr>');
								var $writerTd = $('<td width="100px" height="50px;">').text(data[i].usId);							
								var $contentTd = $('<td width="400px" height="50px;">').text(data[i].knrCon);
								var $dateTd = $('<td width="200px" height="50px;">').text(data[i].knrDate);

								$tr.append($writerTd);
								$tr.append($contentTd);
								$tr.append($dateTd);
								$knb_replyTable.append($tr);
							}
							
							$('#rCon').val('');
						}	
					});
			
				});			
			</script>
			<% } %>
			<ul class="jctx jctx-id-foo jctx-white jctx-white-shadow">
				<li data-action="쪽지보내기"><a href="<%= request.getContextPath() %>/views/message/messageSendView.jsp?id=<%= kn.getUsId() %>">쪽지보내기</a></li>
				
			</ul>
	</div>
	<script>
		       
    	// 삭제
	      function deleteKn(){
	         var bool = confirm('정말로 삭제하시겠습니까?');
	         if(bool){
	            $('#detailForm').attr('action', '<%= request.getContextPath() %>/delete.kn');
	            $('#detailForm').submit();
	         }
	      }    	
	       	    				    		
		</script>
    	    
</body>
</html>