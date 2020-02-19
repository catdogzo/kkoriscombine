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

</head>
<body>
 <p>
  날짜 입력:  <input type="text" id="alternate" size="30"><div id="datepicker"></div>
 </p>
 
</body>
</html>