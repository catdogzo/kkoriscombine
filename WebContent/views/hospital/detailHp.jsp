<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%
 	String year = null;
 	String month = null;
 	String day = "01";

 	Calendar cal = Calendar.getInstance();
 	java.text.DateFormat df = null;

	 if ((year = request.getParameter("year")) == null){
		 df = new SimpleDateFormat("yyyy");
 		 year = df.format(cal.getTime());
	 }

	 if ((month = request.getParameter("month")) == null){
  		df = new SimpleDateFormat("MM");
 		 month = df.format(cal.getTime());
	 }

 	df = new SimpleDateFormat("yyyyMM");
 
	if (df.format(cal.getTime()).equals(year + month)){
 		df = new SimpleDateFormat("dd");
 		day = df.format(cal.getTime());
	}
	
	String url = request.getContextPath() + "/detail.hp?hpId=" + request.getParameter("hpId");
	String prev = null;
	String next = null;
	
 	if (Integer.parseInt(month) == 1){
  		prev = url + "&year=" + (Integer.parseInt(year) - 1) + "&month=12";
 	} else{
  		String prevMonth = "0" + (Integer.parseInt(month) - 1);
  
		if (prevMonth.length() == 3){
 			prevMonth = prevMonth.substring(1);
		}
  		prev = url + "&year=" + year + "&month=" + prevMonth;
 	}

 	if (Integer.parseInt(month) == 12){
  		next = url + "&year=" + (Integer.parseInt(year) + 1) + "&month=01";
 	} else {
  		String nextMonth = "0" + (Integer.parseInt(month) + 1);
  		if (nextMonth.length() == 3){
   			nextMonth = nextMonth.substring(1);
  		}
  		next = url + "&year=" + year + "&month=" + nextMonth;
 	}
%>
<%
	Hospital hp = (Hospital)request.getAttribute("hp");
	ArrayList<HpMedical> hmList = (ArrayList<HpMedical>)request.getAttribute("hmList");
	HashMap<String, String> cateMap = (HashMap<String, String>)request.getAttribute("cateMap");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 병원 예약</title>
<style>
	div.contents {width: 100%; height: 100%;}
	
	div.hpInfo, form#rsForm {position: relative; width: 100%; padding: 0 2%; height: 400px; margin-top: 30px;}
	div.hpInfo > div, form#rsForm > div {display:inline-block; width: 45%; height: 100%; vertical-align: middle; margin: 0 2%;}
	div.hpInfo > div.left.border > p {margin-top: 164px;}
	div.hpInfo > div.hpImg > img {width: 100%;}
	div.hpInfo > div.right > div.right-contents > p {font-size: 18px; margin-bottom: 10px;}
	div.hpInfo > div.right > div.right-contents > p.hpName {font-size: 24px; font-weight: 600; margin-bottom: 30px;}
	div.hpInfo > div.right > div.right-contents > p.hpIntro {font-size: 16px; margin-top: 10px; border-top: 1px solid #dfdfdf; border-bottom: 1px solid #dfdfdf; padding: 10px 0;}
	div.right-contents {}
	p {text-align: center; color: #000;}
	.border {border: 1px solid #000;}
	
	form#rsForm > div.left > div.select-box {margin-bottom: 20px;}
	span {display: block; font-size: 18px;}
	form#rsForm > div.left > div {display: inline-block; width: 45%; height: 100%; margin: 0 1%; vertical-align: middle;}
	form#rsForm > div.left > div.calendar {height: 200px; overflow: hidden;}
	form#rsForm > div.left > div.rsInfo {padding: 10px;}
	form#rsForm > div.left > div.rsInfo > div.select-box {width: 80%;}
	
	/* 캘린더 */
	table {border: 0; color: #000;}
	table.cal {width: 100%;}
	table.inner {width: 100%;}
	table.inner td {font-size: 20; font-weight: 600;}
	td {vertical-align: middle;}
	table.date td > p {text-align: center; font-size: 16px; font-weight: 600; margin: 0;}
	.day {font-weight: 600;}
	.red {color: #ff4848}
	.blue {color: #0042ed}
	.black {color: #000}
	.onclick {background: #aedefc}
</style>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
	<div class="container">
		<div class="contents">
			<h2>병원 예약</h2>
			<div class="hpInfo">
				<% if(hp.getHpPhoto() == null){ %>
				<div class="left border"><p>등록된 사진이 없습니다.</p></div>
				<% } else{%>
				<div class="left hpImg"><img src="<%= hp.getHpPhoto()%>"></div>
				<% } %>
				<div class="right">
				<div class="right-contents">
					<p class="hpName"><%= hp.getHpName() %></p>
					<p>[<%= hp.getHpZip() %>] <%= hp.getHpLoc1() %> <%=hp.getHpLoc2() %></p>
					<p><i class="fas fa-phone-alt"></i> <%= hp.getHpPhone() %></p>
					<p>
						진료시간: <%= hp.getHpStart() %>:00 ~ <%= hp.getHpEnd() %>:00
						<% if(!hp.getHpLunch().equals("noTime")){ %>
						<br>점심시간: <%= hp.getHpLunch() %>:00 ~ <%= Integer.parseInt(hp.getHpLunch())+1 %>:00
						<% } %>
					</p>
					<% if(hp.getHpIntro() != null){ %>
					<p class="hpIntro"><%= hp.getHpIntro() %></p>
					<% } %>
				</div>
				</div>
			</div>
			<form action="<%= request.getContextPath() %>/complete.rs" method="post" name="rsForm" id="rsForm">
				<div class="left">
					<div class="calendar">
						<table class="cal header"> <!-- 캘린더 헤더 -->
					 		<tr>
					  			<td>
							 		<table class="inner">
							    		<tr>
							     			<td align=center valign=middle height=25>
							      				<a href=<%=prev%>><b class="prev">&lt;</b></a>
							     				<b><%=year%> <%=month%></b>
							      				<a href=<%=next%>><b class="next">&gt;</b></a>
							     			</td>
							    		</tr>
							   		</table>
					 	 		</td>
					 		</tr>
						</table>
						<table class="cal date">
							<tr align=center>
								<td width=40 class="day red">Sun</td>
								<td width=40 class="day">Mon</td>
								<td width=40 class="day">Tue</td>
								<td width=40 class="day">Wed</td>
								<td width=40 class="day">Thu</td>
								<td width=40 class="day">Fri</td>
								<td width=40 class="day blue">Sat</td>
							</tr>
						
						<%
							cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1 , 1);
							int indent = cal.get(Calendar.DAY_OF_WEEK);
							cal.add(Calendar.MONTH, 1);
						 	cal.add(Calendar.DATE, -1);
						 	int numberOfDays = cal.get(Calendar.DATE);
						
						 	for (int i = 1; i < indent; i++){
						  		if (i == 1){
						   			out.println("<tr valign='top' height='30'>");
						  		}
						  		out.println("<td width='30'></td>");
						 	}	
						
						 	Calendar today = Calendar.getInstance();
						 	df = new SimpleDateFormat("yyyyMMdd");
						 
						 	for (int i = 1; i <= numberOfDays; i++){
						  		String cssClass = "black";
						  		String dayDescript = "";
						 		day = "0" + i;
						
						  		if (day.length() == 3){
						   			day = day.substring(1);
						  		}
						
						  		if (((indent + i) - 1) % 7 == 1){  // 일요일
						   			out.println("<tr valign='top' height='30'>");
						   			cssClass = "red";
						  		}
						  		else if (((indent + i) - 1) % 7 == 0){
						   			cssClass = "blue";
						  		}
						
						  		if (df.format(today.getTime()).equals(year + month + day)){
						   			out.println("<td width='30' class='onclick'>");
						  		} else {
						   			out.println("<td width='30'>");
						  		}
						
						  		out.println("<p class=" + cssClass + ">" + i + "</p>");
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
					</div>
					<div class="rsInfo">
						<span>내원 시간</span>
						<div class="select-box">
							<select name="rsTime" id="rsTime">
								
							</select>
						</div>
						<span>진료 선택</span>
						<div class="select-box">
							<select name="hmCate" id="hmCate">
							<% for(HpMedical hm : hmList){ %>
								<option value=<%= hm.getHmCate() %>><%= cateMap.get(hm.getHmCate()) %></option>
							<% } %>
							</select>
						</div>
					</div>
				</div>
				<div class="right">
					
				</div>
				<% if(loginUser != null){ %>
				<% } %>
			</form>
		</div>
	</div>
	<script>
		$(function(){
			$('div.right-contents').each(function(){ // 병원정보영역 수직중앙으로 위치 조정
				var marginTop = 200 - $(this).height()/2;
				$(this).css('margin-top', marginTop);
			});
			
			$('table.date tr:not(:nth-of-type(1)) > td').click(function(){ // 내원 날짜
				var year = <%= year %>;
				var month = <%= month %>;
				var day = $(this).children().text();
				$('table.date td').removeClass('onclick');
				$(this).addClass('onclick');
				
				var rsTime = $('div.rsInfo select#rsTime');
				console.log(rsTime);
				
			});
			
			$('select#hmCate').each(function(){
				$option1 = $('<option>');
				$option1.prop('disabled', 'true').text('--진료과목--');
				$option2 = $('<option>');
				$option2.prop('disabled', 'true').text('--검사--');
				$option3 = $('<option>');
				$option3.prop('disabled', 'true').text('--건강검진--');
				$option4 = $('<option>');
				$option4.prop('disabled', 'true').text('--예방접종--');
				$option5 = $('<option>');
				$option5.prop('disabled', 'true').text('--중성화수술--');
				
				var opList = $(this).children('option');
				for(var i in opList){
					var value = opList.eq(i).val();
 					if(value.includes('HC')){
 						opList.eq(i).before().before($option1);
 						$option1 = null;
					} else if(value.includes('HI')){
						opList.eq(i).before().before($option2);
 						$option2 = null;
					} else if(value.includes('HS')){
						opList.eq(i).before().before($option3);
 						$option3 = null;
					} else if(value.includes('HV')){
						opList.eq(i).before().before($option4);
 						$option4 = null;
					} else if(value.includes('HZ')){
						opList.eq(i).before().before($option5);
 						$option5 = null;
					}
 				}
			});
		});
	</script>
</body>
</html>