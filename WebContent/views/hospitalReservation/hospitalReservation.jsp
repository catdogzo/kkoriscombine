<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/layout.jsp" %>
<%
 	String year = null;
 	String month = null;
 	String day = "01";
 	String Ryear = null;
 	String Rmonth = null;
 	String Rday = null;
 	String dayOfTheWeek = null;
 	

 	Calendar cal = Calendar.getInstance();
 	
	switch(cal.get(Calendar.DAY_OF_WEEK)){
	case 1: dayOfTheWeek = "Sun"; break;
	case 2: dayOfTheWeek = "Mon"; break;
	case 3: dayOfTheWeek = "Tue"; break;
	case 4: dayOfTheWeek = "Wed"; break;
	case 5: dayOfTheWeek = "Thu"; break;
	case 6: dayOfTheWeek = "Fri"; break;
	case 7: dayOfTheWeek = "Sat"; break;
 	}
 	
 	java.text.DateFormat df = null;
 	
	if ((year = request.getParameter("year")) == null){
		df = new SimpleDateFormat("yyyy");
 		year = df.format(cal.getTime());
	}
	if((Ryear = request.getParameter("Ryear")) == null){
		Ryear = df.format(cal.getTime());
	}
	
	if ((month = request.getParameter("month")) == null && (Rmonth = request.getParameter("Rmonth")) == null){
		df = new SimpleDateFormat("MM");
 		month = df.format(cal.getTime());
 		Rmonth = df.format(cal.getTime());
	} 
	if((Rmonth = request.getParameter("Rmonth")) == null){
		Rmonth = df.format(cal.getTime());
	}
	df = new SimpleDateFormat("dd");
	if((Rday = request.getParameter("Rday")) == null){
		Rday = df.format(cal.getTime());
	}
	df = new SimpleDateFormat("yyyyMM");
	if (df.format(cal.getTime()).equals(year + month)){
		df = new SimpleDateFormat("dd");
 		day = df.format(cal.getTime());
 		
	}
	
	String prev = null;
	String next = null;

 	if (Integer.parseInt(month) == 1){
  		prev = "hospitalReservation.jsp?year=" + (Integer.parseInt(year) - 1) + "&month=12";
 	}
 	else{
  		String prevMonth = "0" + (Integer.parseInt(month) - 1);
  
  			if (prevMonth.length() == 3){
	  			prevMonth = prevMonth.substring(1);
  			}
  		prev = "hospitalReservation.jsp?year=" + year + "&month=" + prevMonth;
 	}

 	if (Integer.parseInt(month) == 12){
  		next = "hospitalReservation.jsp?year=" + (Integer.parseInt(year) + 1) + "&month=01";
 	} else {
  		String nextMonth = "0" + (Integer.parseInt(month) + 1);
  		if (nextMonth.length() == 3){
   			nextMonth = nextMonth.substring(1);
  		}
  		next = "hospitalReservation.jsp?year=" + year + "&month=" + nextMonth+"";
 	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	section.HRmain {
		position:relative; height:100%; width:100%; 
		background-size: contain;
	}
	section.HRmain > article.HRquickMenu {
		position: relative; width: 100%; height: 100%;
		margin: 0 auto; text-align: center; min-width: 1100px;
	}
	section.HRmain > article.HRquickMenu > h1{
		position: relative;
		margin: 0 auto;
		font-size: 40px;
		width: 270px;
		text-align: center;
		vertical-align: middle;
	}
	
	section.HRmain > article.HRquickMenu > table.calendarBody{
		display: inline-block;
		border:0; cellspacing:1; cellpadding:3;
	}
	section.HRmain > article.HRquickMenu > table.calendarBody th.yearMonth{
		background: #ffdfdf; border: 1px solid #ffdfdf; vertical-align: middle;
		height: 25px;
	}
	section.HRmain > article.HRquickMenu > table.calendarBody th.yearMonth > label:hover{
		cursor: pointer;
	}
	section.HRmain > article.HRquickMenu > table.calendarBody td.verdana_b{
		background: #FCFCF3; font-family:verdana, arial; font-size: 9px; font-weight: bold; height: 16px;
	}
	section.HRmain > article.HRquickMenu > table.calendarBody td{
		background: ; border: 1px solid #ffdfdf; text-align: left;
		width: 110px; height: 110px; vertical-align: top;
	}
	A:link {color: #1f3174; text-decoration: none}
	A:active {color: #1f3174; text-decoration: none}
	A:visited {color: #1f3174; text-decoration: none}
	A:hover {color: #3366cc; text-decoration: underline}
	
	.cal_red { font-family:verdana, arial; font-size: 10px; color: #CC0000 }
	.cal_blue { font-family:verdana, arial; font-size: 10px; color: #6666CC }
	.cal_black { font-family:verdana, arial; font-size: 10px; color: #333333 }
	
	
	
	section.HRmain > article.HRquickMenu > div.todayReservation{
		display: inline-block;
		position: relative;
		min-width: 150px;
		min-width: 180px;
		width: 15%;
		vertical-align: top;
		text-align: center;
		margin: 0 0 0 2%;
		height: 592px;
		overflow: auto;
		border: 1px solid #78dfff;
		padding: 5px;
		margin-bottom: 10px;
	}
	div.todayReservation > label.todayDate{
		border-bottom: 1px solid #5d5d5d;
	}
	div.todayReservation > div.rvInfo > ul{display: none; border: 1px solid #ffdfdf}
	div.todayReservation > div.rvInfo label.reservationTime{cursor:pointer;}
	div.todayReservation > div.rvInfo label > i{vertical-align: top; padding-top: 2px;}
	div.todayReservation > div.rvInfo textarea{
		width: 100px; resize: none;
	}
	
	
	
	
</style>
</head>
<body>
<form id=HRform action="hospitalReservation.jsp" method="get">
	<input type=hidden name=year id=year value=<%=year %>>
	<input type=hidden name=month id=month value=<%=month %>>
	<input type=hidden name=Ryear id=Ryear value=<%=Ryear %>>
	<input type=hidden name=Rmonth value=<%=Rmonth %>>
	<input type=hidden name=Rday value=<%=Rday %>>
	<div class="container">
    	<section class="HRmain">
			<article class="HRquickMenu">
			
				<br><br>
				
				<h1>예약 관리</h1>
				
				<br> <br><br>
				
				<table class=calendarBody>
					<tr>
						<th colspan="7" class=yearMonth>
							<label id="prev"><font color=#FFFFFF>◀</font></label>&nbsp;&nbsp;
					     	<%=year%>    <%=month%>&nbsp;&nbsp;
					      	<label id=next><font color=#FFFFFF>▶</font></label>
					    </th>
					</tr>
			 		<tr class=dayOfTheWeek>
				  		<td class=verdana_b><font color=#CC0000>Sun</font></td>
				  		<td class=verdana_b><font color=#666666>Mon</font></td>
				  		<td class=verdana_b><font color=#666666>Tue</font></td>
				  		<td class=verdana_b><font color=#666666>Wed</font></td>
						<td class=verdana_b><font color=#666666>Thu</font></td>
						<td class=verdana_b><font color=#666666>Fri</font></td>
						<td class=verdana_b><font color=#6666CC>Sat</font></td>
			 		</tr>
			 	
					<%
						cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1 , 1);
						int indent = cal.get(Calendar.DAY_OF_WEEK);
						cal.add(Calendar.MONTH, 1);
					 	cal.add(Calendar.DATE, -1);
					 	int numberOfDays = cal.get(Calendar.DATE);
					
					 	for (int i = 1; i < indent; i++){
					  		if (i == 1){
					   			out.println("<tr>");
					  		}
					  		out.println("<td></td>");
					 	}	
					
					 	Calendar today = Calendar.getInstance();
					 	df = new SimpleDateFormat("yyyyMMdd");
					 
					 	for (int i = 1; i <= numberOfDays; i++){
					  		String cssClass = "cal_black";
					  		String dayDescript = "";
					 		day = "0" + i;
					
					  		if (day.length() == 3){
					   			day = day.substring(1);
					  		}
					
					  		if (((indent + i) - 1) % 7 == 1){  // 일요일
					   			out.println("<tr>");
					   			cssClass = "cal_red";
					  		}
					  		else if (((indent + i) - 1) % 7 == 0){
					   			cssClass = "cal_blue";
					  		}
					
					  		if (df.format(today.getTime()).equals(year + month + day)){
					   			out.println("<td bgcolor='#ffdfdf'>");
					  		} else {
					   			out.println("<td>");
					  		}
					
					  		out.println("<span class=" + cssClass + ">" + i + "</span>");
					  		out.println(dayDescript);
					  		out.println("</td>");
					  
					  		if (((indent + i) - 1) % 7 == 0){
					   			out.println("</tr>"); //토요일
					  		}
					 	}
					
					
					 	if(((indent == 6) && (numberOfDays > 30) ) || ( (indent == 7) && (numberOfDays > 29) ) ){
					  		if (41-numberOfDays-indent > 0){
					   			for (int i = 43 - numberOfDays - indent; i > 0; i--){
					    			out.println("<td> </td>");
					   			}
					  		}
					  		out.println("</tr>");
					 	} else if ( (numberOfDays != 28) || (indent > 1) ){
					  		if (36-numberOfDays-indent > 0){
					   			for (int i = 36 - numberOfDays - indent; i > 0; i--){
					    			out.println("<td> </td>");
					   			}
					  		}
					  		out.println("</tr>");
					 	}
					%>
				</table>
				
				<div class=todayReservation>
					<label class=todayDate><%=Ryear %>/<%=Rmonth %>/<%=Rday %>(<%=dayOfTheWeek %>)</label><br>
					<div class=rvInfo>
						<label class=reservationTime>시간: 10:00&nbsp;<i class="fas fa-sort-down"></i></label>
						<ul>
							<li>이름: 홍홍이</li>
							<li>품종: 러시안블루</li>
							<li>성별: 남</li>
							<li>나이: 7세</li>
							<li>체중: 5.7kg</li>
							<li>
								특이사항:<br>
								<textarea class=rs_memo cols=5 rows=3></textarea>
							</li>
						</ul>
					</div>
				</div>
			</article>
		</section>
	</div>
	</form>
	
	<script>
		$("div.rvInfo > label.reservationTime").click(function(){
			$(this).next().toggle();
		});
		
		console.log($("#Ryear").val());
		
		$("#prev").click(function(){
			var year = <%=year%>;
			var month = <%=month%>;
			
			if(month == "1"){
				year = ""+(Number(year) - 1);
		  		month = "12";
			} else {
				var prevMonth = "0"+String(Number(month)-1);
				if (prevMonth.length == 3){
			  		prevMonth = prevMonth.substring(1);
		  		}
				month = prevMonth;
			}
			$("#year").val(year);
			$("#month").val(month);
			
			$("#HRform").submit();
		});
		
		$("#next").click(function(){
			var year = <%=year%>;
			var month = <%=month%>;
			
			if(month == "12"){
				year = ""+(Number(year) + 1);
		  		month = "01";
			} else {
				var prevMonth = "0"+String(Number(month)+1);
				if (prevMonth.length == 3){
			  		prevMonth = prevMonth.substring(1);
		  		}
				month = prevMonth;
			}
			$("#year").val(year);
			$("#month").val(month);
			
			$("#HRform").submit();
		});
		
		
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>