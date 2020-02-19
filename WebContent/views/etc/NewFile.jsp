<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<style>
	img:hover{cursor: pointer;}
</style>
</head>
<body>
	<img src="<%=request.getContextPath() %>/images/like.png" style="width: 30px; height: 30px;" id="likeBtn">

	<script>
    $('$likeBtn').on('click', function(){

        var count = 0
        count++;
        if(count == 1){
        	location.href='like.kn';
        }else{
        	$(this).off("click");
        }
    });
	</script>

</body>


</html>