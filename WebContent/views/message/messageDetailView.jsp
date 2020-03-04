<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.Date, message.model.vo.Message"%>
<% 
	Message m = ((Message)request.getAttribute("message"));
	String mNum = request.getParameter("mNum");
	String rNick = m.getRNick();
	String sNick = m.getSNick();
	String mTitle = m.getMsgTitle();
	String mCon = m.getMsgCon();
	Date mDate = m.getMsgDate();
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
	   height:35px ;
	   clear:both ;
	   background-color:#fb929e ;
	   font-weight:bold ;
}

div.inContentTitle{
	margin-left: 300px;
	width: 200px;
}


span.messageControll {
	margin-top: 200px;
	margin-left: 300px;
	margin-bottom: 10px;
}


input#deleteNoBtn {
	margin-top :500px;
	margin-left: 700px;
	min-width: 50px; 
	height: 30px; 
	cursor: pointer; 
	background: #ffdfdf; color: #5d5d5d; 
	font-size: 14px; 
	font-weight: 600; 
	border: none; 
	border-radius: 5px;
}

input#receiveMsgButton{
	text-align: left;
	margin-left: 10px;
}


input#deleteNoBtn:hover {background: #fb929e; color: #fff;}

div.input-button {display: block; text-align: center; padding: 15px;}
input[type="button"] {min-width: 50px; height: 30px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 14px; font-weight: 600; border: none; border-radius: 5px;}
input[type="button"]:hover {background: #fb929e; color: #fff;}

</style>

<meta charset="UTF-8">
<title>쪽지 상세보기</title>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
	
	
		<div class="outer">
	<div class="messageOuter">
		<div class="inContentTitle"><img src="WebContent/images/readMsg.png" width="190px" height="200px"></div>
		<form action="<%= request.getContextPath() %>/views/message/messageReplyForm.jsp" id="detailForm" method="post"> 
			<span class="messageControll">
			   	<!-- <input type="text" class="textMsg" size="20" name="msgNum" > -->
			   	<input type="button" value=" 보낸쪽지함 " id="sendMsgButton" class="sendMsgButton" role="button" onclick="location.href='<%= request.getContextPath() %>/listSend.ms'">
			   	<input type="button" value=" 받은쪽지함 " id="receiveMsgButton" class="receiveMsgButton" role="button" onclick="location.href='<%= request.getContextPath() %>/list.ms'">
			</span> 
		<div class="tableTitle">
			<table id="msgArea">
				<tr>
					<th colspan="6">
					   <%= mTitle %>
						<input type="hidden" name="mNum" value="<%= mNum %>">										
						<input type="hidden" size="50" name="title" value="<%= mTitle %>">
					</th>				
				</tr>
				<tr>
					<th>발신자</th>
					<td><input type="hidden" name="ssgId" value="<%= sNick %>"><%= sNick %></td>
					<th>수신자</th>
					<td><input type="hidden" name="rsgId" value="<%= rNick %>"><%= rNick %></td>
					<th>발신일</th>
					<td><input type="hidden" name="date" value="<%= mDate %>"><%= mDate %></td>
				</tr>
				
				<tr>
					<td colspan="6">
						<textarea name="hidden" cols="130" rows="25" style="resize:none;" readonly><%= mCon %></textarea>
					</td>
				</tr>
				
			</table>
		</div>
		</form>
			</div>
	</div>

	<input type="button" onclick="deleteNo();" id="deleteNoBtn" value="삭제">	
	<script>
		function deleteNo(){
			var bool = confirm('정말로 삭제하시겠습니까?');
			if(bool){
				$('#detailForm').attr('action', '<%= request.getContextPath() %>/delete.ms');
				$('#detailForm').submit();
			}
		}			
	</script>
	
	


</body>
</html>