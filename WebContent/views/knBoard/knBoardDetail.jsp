<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="knBoard.model.vo.*, java.util.ArrayList, java.sql.Date, photo.model.vo.*"%>
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
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.contextMenu.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.contextMenu.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.ui.position.js"></script>
	<style>
		#blogo{margin-left:350px; margin-top: 50px;}
		.outer{width: 1000px; height: 900px; background: rgba(255, 255, 255, 0.4); margin-left: 350px; margin-right: auto; margin-top: auto;}
		.writeArea{width: 900px; height: 800px; margin-top: 80px; margin-left: 150px; margin-right: auto; padding: 10px; border: 1px solid #fcc6c9;}
		table{margin-left: 30px; margin-top: 3px; min-height: 550px;}
		table, th, td{word-spacing: 3px; padding: 4px;}
		table > tfoot > th, td{padding-top: 5px;}
		#knt{font-size: 25px; font-weight: 900; text-align: center;}
		.input{visibility: hidden; font-family: inherit; width: 90%; border: 0; border-bottom: 1px solid #575756; outline: 0; background: transparent; transition: border-color 0.2s;}	
		content{font-family: inherit;}
		.aleft{font-weight: 600; min-width:300px;}
		tr > td > #title{font-weight: 800; color: gray;}
		#writeBorder{border: 1px solid #fcc6c9; width: 800px; height: 550px; }
		#kncon{align: auto; width: 850px; max-height: 680px; margin-left: 10px; margin-top: 10px; margin-right: 10px; margin-botton: 10px; overflow: auto;}
		#kncon > img {width:200px; height:200px;}
		#writeTd{min-width: 250px; }
		Button#replyBtn{font-size: 15px; text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
		#updateBtn{font-size: 15px; margin-left: 50px;  text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
		#deleteBtn{font-size: 15px; text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
		table#knb_reply{margin-left:150px; padding: 0;}
		tr#knr_area{max-height: 80px;}
		#replySelectTable {margin-left: 250px; padding: 0px;}
		#replySelectTable > tr {max-height: 200px;}
		#addReply{min-width: 100px; height: 30px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 14px; font-weight: 600; border: none; border-radius: 5px;}
		#addReply:hover {background: #fb929e; color: #fff;}
		#reSendBtn{width: 60px; height: 60px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 16px; font-weight: 600; border: none; border-radius: 5px;}
		#likeBtn:hover{cursor: pointer;}
		
	</style>
</head>
<body>
<%@ include file="../common/layout.jsp" %>   
		<div class="outer">
			<img src="<%= request.getContextPath() %>/images/knb.png" id="blogo">
		<br>
		<!-- <h1>지식 공유게시판 글보기</h1> -->
		<form action="views/knBoard/knBoardUpdate.jsp" id="detailForm" method="post">
			<div class="writeArea">			
				<table>
					<tr>	
						<td class="aleft">제목 &nbsp;&nbsp;
<%-- 							<input type="text" size="50" id="title" name="title" class="input" value="<%= kn.getknTitle() %>" readonly>	 --%>
							<span id = "knt"><%= kn.getknTitle() %></span>
						</td>				
					</tr>				
					<tr>
						<td class="aleft" width= "300px">글쓴이&nbsp;&nbsp;
						<span id="menuBtn" style="font-size:15px; font-weight: 300"><%= kn.getUsNick() %></span>
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
						<tr>
							<% if(loginAu != null && loginUser.getUsNick().equals(kn.getUsNick())) {%>
							<td id="writeTd">
								<input type="submit" id="updateBtn" value="수정">
								<input type="button" onclick="deleteKn();" id="deleteBtn" value="삭제">
							</td>
							<% } %>
						</tr>
					</table>
				<!--  넘길 값 -->				
				<input type="hidden" value="<%= kn.getKnNum() %>" name="no">
				<input type="hidden" value="<%= kn.getknTitle() %>" name="title">		
				<input type="hidden" value="<%= kn.getKnCon() %>" name="content">	
				<input type="hidden" value="<%= kn.getUsNick() %>" name="knNick">										
				</div>		
				</form>	
				<table id="knb_reply">
					<thead>
					<tr id="knr_area">
					<% if(loginAu != null ){%>		
						<td>
							<textarea rows="3" cols="110" id="replyContent" style="resize: none; border: 1px solid #fcc6c9;"></textarea>	
						</td>
						<td><button id="addReply">댓글 등록</button>
						<script>
						    // 댓글 관련
				    		var id = '<%= loginUser.getUsId() %>';
							var no = <%= kn.getKnNum() %>;								
				    		$('#addReply').click(function(){	
								$.ajax({
									url: '<%= request.getContextPath() %>/insertReply.kn',
									type: 'post',
									data: {id: id, content: $('#replyContent').val(), no: no},
									success: function(data){
										$replyTable = $('#replySelectTable');
										$replyTable.html("");
										for(var key in data){
											var $tr = $('<tr>');
											var $writerTd = $('<td>').text(data[key].getUsId).css('width', '100px');
											var $contentTd = $('<td>').text(data[key].KnrCon).css('width', '140px');
											var $dateTd = $('<td>').text(data[key].KnrDate).css('width', '200px');
											var $like = '<button>' + '<img src="<%=request.getContextPath() %>/images/like.png" + "style="width: 30px; height: 30px;">'+'</button>';
											$tr.append($writerTd);
											$tr.append($contentTd);
											$tr.append($dateTd);
											$tr.append($like);
											$replyTable.append($tr);

										}														
										$('#replyContent').val('');
									}
								
								});
				    			
							}); 
							       	    				    		
						</script>
						</td>	
						<% } %>
					</tr>
				</table>
				<% if(loginAu != null ){%>							
				<table id="replySelectTable">
					<% if(list.isEmpty()){ %>
						<tr><td colspan="3"></td></tr>
					<% } else { %>
						<% for(int i = 0; i < list.size(); i++){ %>
							<tr id="knrWriter">
								<td width="100px"><%= list.get(i).getUsId() %></td>							
								<td width="400px"><%= list.get(i).getKnrCon() %></td>
								<td width="200px"><%= list.get(i).getKnrDate() %>

								</td>
							</tr>						
						<% } %>
					<% } %>						
				</table>
				<script>
		    	    // 좋아요 버튼
		    		 $('#likeBtn').on('click', function(e){
			   	        var count = 0
			   	        var rptr = $('#knrWriter');
			   	        var rptd =  rptr.children();
			   	        var usId = rptd.eq(0).text();
			   	        var logUS = '<%= loginUser.getUsNick() %>';
			   	        count++;
			   	        console.log(count);
			   	        console.log(usId);
			   	        console.log(logUS);
		   	        $('#likeC').text(count);
		   	        	if(usId != logUS){
		   			        if(count == 1){
		   			        	$.ajax({
		   			        		url: '<%= request.getContextPath() %>/like.kn',
		   			        		data: {usId:usId},
		   			        		type: 'post',
		   							sucess: function(data){
		   								console.log('2');
		   							},		   			        		
		   			        	});
		   			        }	        	
		   		        }
		       		});	
				</script>
				<% } %>
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
    
    		// context메뉴
    		var i = jQuery.noConflict();
    	    i(function() {
    	        $.contextMenu({
    	            selector: '#menuBtn', 
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
    	    
	</script>
</body>
</html>