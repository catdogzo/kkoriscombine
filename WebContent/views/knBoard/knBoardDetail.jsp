<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 지식 공유 글보기</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.contextMenu.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.contextMenu.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.ui.position.js"></script>
	<style>
		#blogo{margin-left:300px; margin-top: 50px;}
		.outer{width: 800px; height: 650px; background: rgba(255, 255, 255, 0.4); margin-left: 150px; margin-right: auto; margin-top: auto;}
		.writeArea{width: 650px; height: 600px; margin-top: 30px; margin-left: 150px; margin-right: auto; padding: 10px; border: 1px solid #fcc6c9;}
		table{margin-left: 30px; margin-top: 3px; min-height: 550px;}
		table, th, td{word-spacing: 3px; padding: 3px;}
		table > tfoot > th, td{padding-top: 5px;}
		.input{visibility: hidden; font-family: inherit; width: 90%; border: 0; border-bottom: 1px solid #575756; outline: 0; background: transparent; transition: border-color 0.2s;}	
		table > tr> title{cursor: text;}
		title{cursor: text;}
		content{font-family: inherit; cursor: text; }
		.aleft{font-weight: 800;}
		Button#replyBtn{font-size: 15px; text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
		#updateBtn{font-size: 15px; margin-left: 50px;  text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
		#deleteBtn{font-size: 15px; text-align: center; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
		table#knb_reply{margin-left:150px; padding: 0;}
		td#reply_area{/* display: none;  */ max-height: 80px;}
		#replyBtn {min-width: 100px; height: 30px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 14px; font-weight: 600; border: none; border-radius: 5px;}
		#replyBtn:hover {background: #fb929e; color: #fff;}
		#reSendBtn{width: 60px; height: 60px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 16px; font-weight: 600; border: none; border-radius: 5px;}
	</style>
</head>
<body>
	<%@ include file="../layout.jsp" %>
		<div class="outer">
			<img src="../../images/knb.png" id="blogo">
		<br>
		<!-- <h1>지식 공유게시판 글보기</h1> -->
		<form action="<%= request.getContextPath() %>/insert.no" method="post">
			<div class="writeArea">			
				<table>
					<tr>
						<td class="aleft">제목
							<input type="text" size="50" name="title" class="input">
						</td>				
					</tr>
					<tr>
						<td></td>
					</tr>					
					<tr>
						<td class="aleft" width= "300px">글쓴이
						<span id="menuBtn" style="font-size:15px; font-weight: 300">수요일</span>
						</td>
						<td class="aleft">날짜
						</td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td class="aleft">내용</td>
					</tr>
					<tr>
						<td colspan="4">
							<textarea name="content" cols="75" rows="15" style="resize:none;"></textarea>
						</td>
					</tr>
						<tr>
							<td colspan="2">
								<button type="button" id="replyBtn">댓글달기</button>
							</td>
							<td id="writeTd">
								<input type="submit" id="updateBtn" value="수정">
								<input type="submit" id="deleteBtn" value="삭제">
							</td>
						</tr>
					</table>
				</div>			
				<table id="knb_reply">
					<thead>
					<tr>
						<td id="reply_area">
							<textarea rows="3" cols="80" style="resize: none; border: 1px solid #fcc6c9;"></textarea>	
						</td>
						<td><button id="reSendBtn">등록</button>
						</td>
					</tr>
					</thead>
				</table>		
				

		</form>			
	</div>
	<script>
	$('#replyBtn').click(function(){
        if($("#reply_area").css('display') == 'none'){
            $("#reply_area").show();
        }else{
            $("#reply_area").hide();
        }
	});
	
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