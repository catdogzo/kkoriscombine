<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<link rel="Content-Type: text/html"  href="<%= request.getContextPath() %>/css/index.css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.raty.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
 <div></div>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.raty.js">
			// 별점 부분


$('div').raty({ score: 3 });
</script>
			 


<div id="star" data-rating="3"></div> 


<div id="star" data-rating="3"></div> 
			  ddd
			  
</body>
</html>