<%@ page language="java" contentType="text/html; charset=UTF-8"	
    pageEncoding="UTF-8"%>

<%@ page import = "java.util.ArrayList, message.model.vo.*" %>    
<%
	ArrayList<Message> mList = (ArrayList<Message>)request.getAttribute("mList");
	PageInfo pi = (PageInfo)request.getAttribute("pi"); 

	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<script src="<%= request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<style>
.header{
	top:0px;}	
div.tableTitle {
	   text-align:center; 
	   align-content:center;
	   width: 1000px;
	   margin-left: 320px;
	   margin-right: 50px;
	   border-top: solid 1px;
	   border-bottom:solid 2px;
	   height:35px ;
	   clear:both ;
	   background-color:#fb929e ;
	   font-weight:bold ;
}

div.messageOuter{
	margin-top: 0px;

}

div.inContentTitle{
	margin-left: 300px;
}

div.pagingArea {
	margin-top: 400px;	
}

div.tableCol.checkBox{
	margin-left: 40px;
	margin-right: 40px;
}

div.tableCol.receiver{
	margin-left: 40px;
	margin-right: 40px;

}
div.tableCol.subject{
	margin-left: 250px;
	margin-right: 250px;


}
div.tableCol.sendDate{
	margin-left: 70px;
	margin-right: 70px;


}

input#receiveMsgButton {
	position: fixed;
	margin-right: 200px;
	margin-bottom: 20px;
	margin-left: 30px;
	
}

div.input-button {display: block; text-align: center; padding: 20px;}
input[type="button"]{min-width: 50px; height: 30px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 14px; font-weight: 600; border: none; border-radius: 5px;}
input[type="button"]:hover {background: #fb929e; color: #fff;}

input#searchButton {
	margin-bottom: 20px;
	width: 20px;
	

}

input.textMsg{
	margin-bottom: 20px;

}

button#deleteButton{
	margin-left: 1000px;
	margin-right: 200px;
	margin-bottom: 20px;

}

button.delBtn{display: block; text-align: center; padding: 20px; margin-right: 140px;}
button.delBtn{min-width: 50px; height: 30px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 14px; font-weight: 600; border: none; border-radius: 5px;}
button.delBtn{background: #fb929e; color: #fff;}


div.messageControll{
	align-content: center;
	margin-right: 200px; 
	text-align:right ;
}	



/* div.tableRow {
    border-bottom:solid 1px #eaeaea ;
    width:100% ;
    clear:both ;
    height:30px ;
}

div.tableCol {
    padding:8px 3px 0 3px ;
    overflow-x:hidden;
    overflow-y:hidden ;
    height:20px ;
} */
	


</style>
<meta charset="UTF-8">
<title>메세지 보관함</title>
</head>
<body>
	<!-- 공유css -->	
	<%@ include file="../common/layout.jsp" %>
	
	<!-- 검색, 목록 -->
	
	<div class="outer">
	<div class="messageOuter">
		<div class="inContentTitle"><img src="WebContent/images/sendMsg.png" width="100px" height="200px"></div>
		<form method="get"> 
			<div class="messageControll">
			   	<input type="text" class="textMsg" size="20" name="msgNum" value="">
			   	<input type="submit" id="searchButton" class="button" value="검색">
			   	<input type="button" value=" 받은쪽지 목록 " id="receiveMsgButton" class="receiveMsgButton" role="button" aria-disabled="false">
			</div>
		<div class="tableTitle">
			<table id="msgArea">
				<tr>
				   	<th><div class="tableCol checkBox"><input type="checkbox" name="check" id="aCheck" style="width:20px; height: 20px;" onclick="allCheck(this);"></div></th>
				  	<th><div class="tableCol receiver" style="padding:5px;">수신자</div></th>
				  	<th><div class="tableCol subject" style="padding:5px;">제목</div></th>
				  	<th><div class="tableCol sendDate" style="padding:5px;">발신일</div></th>
				</tr>
				
				<!-- 이따가 조회 리스트 추가 -->
				<% if(mList.isEmpty()){%>
				
				<tr>
					<td colspan="4">조회된 리스트가 없습니다.</td>
				</tr>
				<% } else { 
						for(Message m : mList) {
				%>
				<tr>
					<td><input type="checkbox" name="check" class="check" style="width:20px; height: 20px;"></td>
					<td><%= m.getRsgId() %><input type="hidden" value='<%= m.getRsgId() %>'> </td>
					<td><%= m.getMsgTitle() %><input type="hidden" value='<%= m.getMsgTitle() %>'></td>
					<td><%= m.getMsgDate() %><input type="hidden" value='<%= m.getMsgDate() %>'></td>
				</tr>
				
				<% 		}
					
					}%>
			</table>
		</div>
		</form>
			</div>
		
			<!-- 하단에 페이징 번호 -->
		<div class="pagingArea" align="center">
			<% if(!mList.isEmpty()){ %>	
			<button onclick ="location.href='<%=request.getContextPath() %>/list.ms?currentPage=1'">&lt;&lt;</button>
		
		
			<!-- 이전 페이지 -->
		<button onclick="location.href='<%=request.getContextPath() %>/list.ms?currentPage=<%= currentPage -1 %>'" id="beforeBtn">&lt;</button>
		<!-- 이전 페이지 갈 작동 함수 필요 -->
				<script>
					if(<%= currentPage %> <= 1){
						var before = $('#beforeBtn');
						before.attr('disabled', 'true'); // 첫번째 페이지면 클릭이 안되게 한다. 
					}
				
				</script>
				
				<!-- 10개의 페이지 목록 -->
				<% for(int p = startPage; p <= endPage; p++){ %>
					<% if(p == currentPage) { %>
						<button id="choosen" disabled><%= p %></button>
					<% } else {%>
						<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.ms?currentPage=<%= p %>'"><%= p %></button>
					<% } %>
				<% } %>
				<!-- 다음 페이지 -->
				<button onclick="location.href='<%= request.getContextPath() %>/list.ms?currentPage=<%-- <%= currentPage +1 %> --%>'" id="afterBtn">&gt;</button>
				<script>
					if(<%= currentPage %> >= <%= maxPage %>){
						var after = $("#afterBtn");
						after.attr('disabled', 'true');
					}
				</script>			
				
				<!-- 맨 끝으로 -->
				<button onclick="location.href='<%= request.getContextPath() %>/list.ms?currentPage=<%= maxPage %>'">&gt;&gt;</button>			
				<% } %>
		</div>	

	</div>

	<form name="frm" action="location.href='<%= request.getContextPath() %>/delete.ms" method="post"> <!-- 메세지 삭제 누르면 실행 -->
		<input type="hidden" name="action" value="ssgId">
		<input type="hidden" name="type" value="many">

	<!-- 쪽지 리스트 출력 부분-->
	</form>
	
	<!-- 버튼 위치 -->
	<div style="text-align:right"> </div>
    <!-- 페이징 쪽 번호 생기는 곳 -->
    
    <!-- 삭제버튼  -->
    <div class="delBtn" style="text-align:right">
    <button id="deleteButton" class="deleteButton" role="button" aria-disabled="false" onclick=>선택쪽지 삭제</button>
	</div>
    
	
	
	<script>
	$(function(){
		$('tbody td').mouseenter(function(){
			$(this).parent().css({'background':'#ffe3e4', 'cursor':'pointer'});
		}).mouseout(function(){
			$(this).parent().css("background", "none");
		});			
		});
	
	
/* 		$('button').mouseenter(function(){
			$('button').css({'background':'#ffe3e4', 'cursor':'pointer'});
		}).mouseout(function(){
			$('button').parent().css("background", "none");
		}); */
		
		
		 function allCheck() {

			 var check = document.getElementsByName("check");
			 
		 	for(var i=0; i < check.length; i++) {
			 	 if(check[0].checked == true) {
			 		check[i].checked = true;
				 } else {
					 check[i].checked = false;
				   } 
				 
			 }
			 
		 }
		
		
		$(".check").click(function(){
			$('#aCheck').prop("checked", false);
		})
	</script>

	
	
</body>
</html>