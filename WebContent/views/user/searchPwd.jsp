<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스 컴바인 : 비밀번호 찾기</title>
<style>
div.contents.search {width: 50%; max-width: 400px; height: 400px; top: calc(50% - 200px);}
#searchBtn {width: 30%; margin-top: 20px; margin-left: 35%;}
</style>
</head>
<body>
	<%@ include file="../common/layout.jsp" %>
	<div class="container search">
		<div class="contents search">
			<h2>비밀번호 찾기</h2>
			<form action="<%= request.getContextPath() %>/searchPwd.au" method="post" id="searchPwd" name="searchPwd">
					<div class="input-container"><label class="input-label">아이디</label><input type="text" name="userId" id="userId"></div>
					<div class="input-container"><label class="input-label">이름</label><input type="text" name="userName" id="userName"></div>
					<div class="input-container">
						<label class="input-label">이메일</label><input type="email" name="email" id="email">
						<p class="input-info">* 가입시 입력했던 이메일을 정확히 입력해주세요.</p>
					</div>
					<input type="submit" id="searchBtn" class="center" value="찾기">
			</form>
		</div>
	</div>
	<script src="<%= request.getContextPath() %>/js/common.js"></script>
</body>
</html>