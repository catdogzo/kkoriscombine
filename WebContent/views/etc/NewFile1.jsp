<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/justcontext.js"></script>
<style>
.jctx {display: none; z-index: 1000; position: absolute; overflow: hidden; border: 1px solid #595959; white-space: nowrap; font-family: sans-serif; font-size: 12px; border-radius: 2px; padding: 0; opacity: 0; -webkit-transition:opacity 200ms; -moz-transition:opacity 200ms; -o-transition:opacity 200ms;  -ms-transition:opacity 200ms;}
.jctx-white {background: white; color: black;}
.jctx-white-shadow {box-shadow: 0 0 15px gray;}
.jctx li {padding: 5px 8px; cursor: pointer;}
.jctx li.disabled {color: darkgrey; cursor: default;}
.jctx-white li:hover {background-color: #fcc6c9;}
.jctx-white li.disabled:hover {background-color: lightgrey;}
.jctx i {padding-right: 6px;}
.jctx hr {background-color: grey; height: 1px; border: 0; margin: 2px 0px;}
</style>
<title>Insert title here</title>
</head>
<body>


<p class="jctx-host jctx-id-foo">Right-click this</p>

<ul class="jctx jctx-id-foo jctx-white jctx-white-shadow">
	<li data-action="쪽지보내기"><a href="http://naver.com">쪽지보내기</a></li>
</ul>


</body>
</html>