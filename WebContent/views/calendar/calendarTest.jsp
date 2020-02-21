<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<%-- <%@ include file="../common/layout.jsp" %> --%>

  <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <title>달력</title>
    <style type="text/css">
    	@font-face {
    			font-family: 'lotteMart';
	  	   	 	src: url(../font/12롯데마트행복Medium.ttf) format('truetype');
	  	    }
    
     	div.h3 {
    		font-size: 40px;
	  	    margin-left: 690px;
	  	    margin-top: 50px;
	  		font-family: 'lotteMart';
    		
    	}
    	
    	table{
  	     		margin-left: 500px;
   	            margin-top: 70px;
   	            border-radius: 30px;
    			border: 0px;
    			font-family: 'lotteMart';
    			
    	}
    	
     	#calendar{
    		background: rgb(255, 246, 246);
    		font-family: 'lotteMart';
    		
    		}
    		
    	.calendar td:hover { /* 마우스 빨간 포인트 */
    		background: red;
    		cursor:pointer;
    		
    	}
    		
   
        td{
            width: 70px;
            height: 70px;
    		font-family: 'lotteMart';
            text-align: center;
            font-size: 20px;
            font-weight: bold; 
            font-family: aria;
            border: 0px;
            border-radius: 50%;
            
        }
        
    </style>
<script type="text/javascript">
        var today = new Date();
        var date = new Date();
        function prevCalendar() {
         today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
         buildCalendar(); 
        }
 
        function nextCalendar() {
             today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
             buildCalendar();
        }
        function buildCalendar(){
            var doMonth = new Date(today.getFullYear(),today.getMonth(),1);
            var lastDate = new Date(today.getFullYear(),today.getMonth()+1,0);
            var tbCalendar = document.getElementById("calendar");
            var tbCalendarYM = document.getElementById("tbCalendarYM");
             tbCalendarYM.innerHTML = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월"; 
            while (tbCalendar.rows.length > 2) {
                  tbCalendar.deleteRow(tbCalendar.rows.length-1);
             }
             var row = null;
             row = tbCalendar.insertRow();
             var cnt = 0;
             for (i=0; i<doMonth.getDay(); i++) {
                  cell = row.insertCell();
                  cnt = cnt + 1;
             }
             for (i=1; i<=lastDate.getDate(); i++) { 
                  cell = row.insertCell();
                  cell.innerHTML = i;
                  cnt = cnt + 1;
                  
              if (cnt % 7 == 1) {
                cell.innerHTML = + i
            }    
              if (cnt%7 == 0){
                  cell.innerHTML =+ i
                  row = calendar.insertRow();
              }
              if (today.getFullYear() == date.getFullYear()
                 && today.getMonth() == date.getMonth()
                 && i == date.getDate()) {
                cell.bgColor = "aedefc";
               }
             }
            
        }
            
        $(function() {
                var date = $('#tbCalendarYM').text();
            $('td').click(function () {
                console.log(this);
                var day = this.innerHTML;
                var date2 = date +" "+ day+"일";
                $('#tbCalendarYM').text(date2);
                date2 = "";
            });
        });
        
    </script>
</head>
<body>

    <div class="h3"><h3>캘린더</h3></div>
    
<table id="calendar" class="calendar">
    <tr>
        <td onclick="prevCalendar()"><</td>
        <td align="center" id="tbCalendarYM" colspan="5">
         yyyy년 m월</td>
        <td onclick="nextCalendar()">></td>
    </tr>
    <tr>
        <td align="center"><font color ="red">Sun</td>
        <td align="center">Mon</td>
        <td align="center">Tue</td>
        <td align="center">Wed</td>
        <td align="center">Thu</td>
        <td align="center">Fri</td>
        <td align="center"><font color ="skyblue">Sat</td>
    </tr> 
</table>


<div id='buildCal'>
<!--달력 -->
<!-- 달력 실행 -->
<script language="javascript" type="text/javascript">
   	buildCalendar();
   	
   	
</script>
</div>

</body>
</html>