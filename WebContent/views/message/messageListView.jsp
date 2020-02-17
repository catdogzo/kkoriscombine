<%@ page language="java" contentType="text/html; charset=UTF-8"	
    pageEncoding="UTF-8"%>

<%@ page import = "java.util.ArrayList, message.model.vo.*" %>    
<%
	/* ArrayList<message> msg = (ArrayList<message>)request.getAttribute("msg");
	PageInfo pi = (PageInfo)request.getAttribute("pi"); */

	
%>
<!DOCTYPE html>
<html>
<head>

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
	   height:32px ;
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
	margin-right: 140px;
	margin-bottom: 20px;
}
input#searchButton {
	margin-bottom: 20px;

}

input.textMsg{
	margin-bottom: 20px;

}

button#deleteButton{
	margin-right: 140px	;

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
	<%@ include file="../layout.jsp" %>
	
	<!-- 검색, 목록 -->
	
	<div class="outer">
	<div class="messageOuter">
		<div class="inContentTitle"><img src="readMsg.png" weight="100px" height="200px"><!-- <img src="WebContent/images/sendMsg.png"> --></div>
		<form method="get"> 
			<div style="width:100% ; text-align:right ;">
			   	<input type="text" class="textMsg" size="20" name="msgNum" value="">
			   	<input type="submit" id="searchButton" class="button" value="검색">
			   	<input type="button" value="보낸쪽지 목록" id="receiveMsgButton" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false">

		<div class="tableTitle">
			<table id="msgArea">
				<tr>
				   	<th><div class="tableCol checkBox"><input type="checkbox" style="width:20px; height: 20px;" onclick="lfCheckAll(this.checked)"></div></th>
				  	<th><div class="tableCol receiver" style="padding:5px;">수신자</div></th>
				  	<th><div class="tableCol subject" style="padding:5px;">제목</div></th>
				  	<th><div class="tableCol sendDate" style="padding:5px;">발신일</div></th>
				</tr>
				
				<!-- 이따가 조회 리스트 추가 -->
				<tr>
					<td><input type="checkbox" class="checkBox" style="width:20px; height: 20px;"></td>
					<td>kh</td>
					<td>쪽지1</td>
					<td id="currentDate">
						<script language="JavaScript"> 
						var today = new Date( ); 
						document.getElementById("currentDate").value = document.write(today.getMonth( )+1 , "월 " , today.getDate( ) , "일");
						</script>
					</td>
				</tr>	
				<tr>
					<td><input type="checkbox" class="checkBox" style="width:20px; height: 20px;"></td>
					<td>kh</td>
					<td>쪽지2</td>
					<td id="currentDate">
						<script language="JavaScript"> 
						var today = new Date( ); 
						document.getElementById("currentDate").value = document.write(today.getMonth( )+1 , "월 " , today.getDate( ) , "일");
						</script>
					</td>
				</tr>	
				<tr>
					<td><input type="checkbox" class="checkBox" style="width:20px; height: 20px;"></td>
					<td>kh</td>
					<td>쪽지3</td>
					<td id="currentDate">
						<script language="JavaScript"> 
						var today = new Date( ); 
						document.getElementById("currentDate").value = document.write(today.getMonth( )+1 , "월 " , today.getDate( ) , "일");
						</script>
					</td>
				</tr>	
				<tr>
					<td><input type="checkbox" class="checkBox" style="width:20px; height: 20px;"></td>
					<td>kh</td>
					<td>쪽지4</td>
					<td id="currentDate">
						<script language="JavaScript"> 
						var today = new Date( ); 
						document.getElementById("currentDate").value = document.write(today.getMonth( )+1 , "월 " , today.getDate( ) , "일");
						</script>
					</td>
				</tr>	
				<tr>
					<td><input type="checkbox" class="checkBox" style="width:20px; height: 20px;"></td>
					<td>kh</td>
					<td>쪽지5</td>
					<td id="currentDate">
						<script language="JavaScript"> 
						var today = new Date( ); 
						document.getElementById("currentDate").value = document.write(today.getMonth( )+1 , "월 " , today.getDate( ) , "일");
						</script>
					</td>
				</tr>	
				<tr>
					<td><input type="checkbox" class="checkBox" style="width:20px; height: 20px;"></td>
					<td>kh</td>
					<td>쪽지6</td>
					<td id="currentDate">
						<script> 
						var today = new Date( ); 
						document.getElementById("currentDate").value = document.write(today.getMonth( )+1 , "월 " , today.getDate( ) , "일");
						</script>
					</td>
				</tr>	

				
			</table>
		</div>
			</div>
		</form>
		

		
			<!-- 하단에 페이징 번호 -->
		<div class="pagingArea" align="center">
			<%-- <% if(!list.isEmpty()){ %> --%>	
			<button onclick = "">&lt;&lt;</button>
		
		
			<!-- 이전 페이지 -->
		<button onclick="">&lt;</button>
		<!-- 이전 페이지 갈 작동 함수 필요 -->
				<script>
					<%-- if(<%= currentPage %> <= 1){
						var before = $('#beforeBtn');
						before.attr('disabled', 'true'); // 첫번째 페이지면 클릭이 안되게 한다. 
					} --%>
				
				</script>
				
				<!-- 10개의 페이지 목록 -->
				<%-- <% for(int p = startPage; p <= endPage; p++){ %>
					<% if(p == currentPage) { %>
						<button id="choosen" disabled><%= p %></button>
					<% } else {%>
						<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=<%= p %>'"><%= p %></button>
					<% } %>
				<% } %> --%>
				<!-- 다음 페이지 -->
				<button onclick="location.href='<%= request.getContextPath() %>/list.ms?currentPage=<%-- <%= currentPage +1 %> --%>'" id="afterBtn">&gt;</button>
				<script>
					<%-- if(<%= currentPage %> >= <%= maxPage %>){
						var after = $("#afterBtn");
						after.attr('disabled', 'true');
					} --%>
				</script>			
				
				<!-- 맨 끝으로 -->
				<button onclick="location.href='<%= request.getContextPath() %>/list.ms?currentPage=<%-- <%= maxPage %> --%>'">&gt;&gt;</button>			
				<%-- <% } %> --%>
		</div>	

	</div>

	


	<form name="frm" action="location.href='<%= request.getContextPath() %>/delete.ms" method="post"> <!-- 메세지 삭제 누르면 실행 -->
		<input type="hidden" name="action" value="ssgId">
		<input type="hidden" name="type" value="many">

	<!-- 쪽지 리스트 출력 부분-->
	</form>
	
	<!-- 버튼 위치 -->
	<div style="width:100% ; text-align:right"> </div>
    <!-- 페이징 쪽 번호 생기는 곳 -->
    
    <!-- 삭제버튼  -->
    <div style="width:100% ; clear:both ; padding:10px 0 0 0 ; text-align:right">
    <button id="deleteButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">선택쪽지 삭제</span>
    </button>
	</div>
    
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
	</script>

	
	
</body>
</html>