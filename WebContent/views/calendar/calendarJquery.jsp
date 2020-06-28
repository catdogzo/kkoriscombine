<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet" href="../../css/calendar.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script>
 $(function() { 
	var date = $("#datepicker").text();
	$("#datepicker").datepicker({
		dateFormat: 'yy-MM-dd',
		 monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월","7월", "8월", "9월", "10월", "11월", "12월" ],
	     dayNamesMin: ['일','월','화','수','목','금','토'],
		 showMonthAfterYear: true, //년 뒤에 월 표시
		 altField: "#alternate",
     	 altFormat: "yy년 m월 dd일",
		 showButtonPanel: true,
		 currentText: 'today',
		 /* minDate: "0D",
    	 maxDate: "+1Y" */
	  });
	 });
 
</script>

<script>
	/* 일정입력 함수 */
	function insert(){
		console.log(11);
		var eventText = $('.eventInput').val();
		console.log(eventText);
		$('.insertCal').text(eventText);
		
	}

/* 	console.log("11");
$('#insert1').click(function(){
	console.log("11");

	var eventText = $('.eventInput').val();
	console.log(eventText);
$('.insertCal').text(eventText);
}); */

</script>
</head>
<body>
	<%-- <%@ include file="../layout.jsp" %> --%>

	<div class="calendar">
 	<p>
 		날짜 입력: <input type="text" id="alternate" size="15">&nbsp;&nbsp;&nbsp;
 		일정 입력: <input type="text" name="eventInput" class="eventInput"> 
 		<button  class="insert" onclick="insert();">입력</button>
 		<div id="datepicker"></div>
 	</p>
 	</div>
 	<div class="insertCal"></div>
 	
 	
 	
</body>
</html>