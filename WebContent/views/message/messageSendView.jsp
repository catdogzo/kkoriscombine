<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.Date, message.model.vo.Message, knBoard.model.vo.KnBoard"%>
<%-- <% 
	Message m = ((Message)request.getAttribute("message"));
	String mNum = request.getParameter("mNum");
	String ssgId = m.getSsgId();
	String rsgId = m.getRsgId();
	String mTitle = m.getMsgTitle();
	String mCon = m.getMsgCon();
	Date mDate = m.getMsgDate();
%>  --%>  
<%
	String rsgId = request.getParameter("id");
	System.out.println("rsgId11 + " + rsgId);
	String rnick = (String)request.getSession().getAttribute("nickname");
	System.out.println("msg닉뭐임 + " + rnick);

	String ssgId = ((AllUser)session.getAttribute("loginAu")).getAuId();
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
	   margin-top: 100px;
	   margin-left: 500px;
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

}


input#sendMsgButton {
	text-align: left;
	margin-right: 200px;
	margin-bottom: 20px;
	margin-left: 30px;
	
}

textarea.con{
	margin-top:20px;
}

div.input-button {display: block; padding: 20px;}
input[type="button"]{min-width: 50px; height: 30px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 14px; font-weight: 600; border: none; border-radius: 5px;}
input[type="button"]:hover {background: #fb929e; color: #fff;}

input#searchButton {
	margin-bottom: 20px;
	width: 20px;
	

}

input.text{
	margin-bottom: 20px;

}

button#deleteButton{
	margin-left: 700px;
	margin-right: 200px;
	margin-bottom: 20px;
	min-width: 60px; 
	height: 30px; 
	cursor: pointer; 
	background: #aedefc; color: #5d5d5d; 
	font-size: 14px; 
	font-weight: 600; 
	border: none; 
	border-radius: 5px;

}

button#deleteButton:hover{background: #0774b7; color:white; }
div.messageControll{
	align-content: center;
	margin-right: 200px; 
	text-align:right ;
}	

div.pagingArea{margin-left: 70px;}
div.pagingArea button {
	min-width: 60px; 
	height: 30px; 
	cursor: pointer; 
	background: #ffdfdf; color: #5d5d5d; 
	font-size: 14px; 
	font-weight: 600; 
	border: none; 
	border-radius: 5px;
}
div.pagingArea button:hover {background: #fb929e; color: #fff;}



div.menu{
	margin-top:600px;
	
}

table#msgArea {	
	border-spacing: 5px;
	text-align: left;
	margin-left:10px;
	padding-bottom: 20px;
	height: 600px;
}
div.title{
	border-spacing: 5px;
	padding-bottom: 20px;
	margin-top:100px;
}


div.button {display: block; padding: 20px;}
button[type="submit"]{min-width: 50px; height: 30px; cursor: pointer; background: #ffdfdf; color: #5d5d5d; font-size: 14px; font-weight: 600; border: none; border-radius: 5px;}
button[type="submit"]:hover {background: #fb929e; color: #fff;}

div#msgArea:nth-child(1) {padding-bottom: 10px;}
div#msgArea:nth-child(2) {padding-bottom: 30px;}
 
</style>

<meta charset="UTF-8">
<title>쪽지 보내기</title>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
	
	
	<div class="outer">
		<div class="messageOuter">
			<div class="inContentTitle">쪽지 보내기<!-- <img src="WebContent/images/readMsg.png" width="190px" height="200px"> --></div>
			<form action="<%= request.getContextPath() %>/insert.ms" method="post"> 
			<div class="tableTitle">
				<table id="msgArea">
					<tr>
						<th>수신자 : <input type="hidden" name="rsgId" value="<%= rsgId %>"><input type="hidden" name="rnick" value="<%= rnick %>"><%= rnick %> </th>
					</tr>
					<tr class="title">
						<th>제목 : <input type="text" size="120" name="title" placeholder="제목을 입력하세요"></th>
					</tr>
					<tr>
						<td>
							<textarea class="con" name="con" cols="130" rows="25" style="resize:none;"></textarea>
						</td>
					</tr>
					
				</table>
			</div>
					<div class="menu" align="center">
						<button type="submit" id="insertBtn">&nbsp;등록하기&nbsp;</button> 
						<span onclick="location.href='<%= request.getContextPath() %>/list.ms'" id="menuBtn">취소</span>
					</div>
			</form>
		</div>
	</div>
	
	
	
	
	
	<script>
		
	
	</script>
	
	


</body>
</html>