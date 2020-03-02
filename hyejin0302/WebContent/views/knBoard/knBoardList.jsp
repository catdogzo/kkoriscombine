<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import = "java.util.*, knBoard.model.vo.KnBoard, common.model.vo.Paging" %>
<%
	ArrayList<KnBoard> list = (ArrayList<KnBoard>)request.getAttribute("list");
	Paging pg = (Paging)request.getAttribute("pg");
	String searchCategory = (String)request.getAttribute("searchCategory");
	String searchTag = (String)request.getAttribute("searchTag");	
	
	int listCount = pg.getListCount();
	int currentPage = pg.getCurrentPage();
	int maxPage = pg.getMaxPage();
	int startPage = pg.getStartPage();
	int endPage = pg.getEndPage();

	String pagingAddress = "";
	if(searchCategory == null && searchTag == null){
		pagingAddress = "/list.kn?currentPage=";
	} else {
		pagingAddress = "/search.kn?searchCategory="+searchCategory+"&searchTag="+searchTag+"&currentPage=";
	}	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 지식인 게시판</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/justcontext.js"></script>

<style>
	#blogo{margin-left:300px; margin-top: 50px;}
	.outer{width: 900px; height: 800px; background: rgba(255, 255, 255, 0.4); margin-left: 400px; margin-right: auto; margin-top: auto;}
	.tableArea{width: 800px; height: 650px; margin-top: 80px; margin-left: 120px; margin-right: auto; padding: 10px;}
	table > class{margin-left: 100px; margin-top: 30px; width: 680px;}
	table, th, td{word-spacing: 5px; padding: 5px; height: 30px;}
	thead th{border-bottom: 1px solid  #5d5d5d;; background-color: #fcc6c9; height: 30px; font-weight: 600; text-align: center;}
	tbody th{font-weight: 600; background-color: #fff6f6; border-bottom: 1px solid #5d5d5d; text-align: center;}
	tbody td{border-bottom: 1px solid #5d5d5d; font-family: inherit; text-align: center; font-size: 11pt;}
	#writeBtn{margin-left: 600px; margin-top: 15px; font-size: 15px; border-radius: 5px; width: 80px; height: 35px; font-size: 14px; font-weight: 600; text-align: center; border: 1px solid #575756; border-radius: 5px;background: #ffdfdf; color: #5d5d5d;}
	#writeBtn:hover{cursor: pointer; ; background: #fb929e; color: #fff;}
	.pagingArea{display: inline-block; margin-left: 250px; margin-top: 10px;}
	.pagingArea button{color: black; float: left; padding: 6px 14px; text-decoration: none; transition: background-color .3s; border: 1px solid #ddd; margin: 0 4px; font-size: 15px; font-weight: 700;}	
	.pagingArea button:hover{background-color: white; border: 1px solid #fb929e;}
	.pagingArea button.disabled{color:gray;}
	span > #searchBtn{line-height: 30px; text-align: center; background-color: #ffe3e4; border: 1px solid #fcc6c9; border-radius: 5px; border-radius: 5px; width: 80px; height: 35px  position: fixed;}
	span > #searchBtn:hover{cursor: pointer; ; background: #fb929e; color: #fff;}
	.searchArea{margin-top: 80px; margin-left: 100px;}
	span > input{width: 200px;}
	div > select{font-size: 10pt; width: 100px; padding: .7em .5em; font-family: inherit; background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; -webkit-appearance: none; -moz-appearance: none; appearance: none; outline:none; border:1px solid #5d5d5d;}	
	.search_input{padding: 12px 24px; font-size: 14px; line-height: 18px; color: #575756; background-size: 18px 18px; border-radius: 50px; border: 1px solid #5d5d5d; backface-visibility: hidden; right:20px; outline:none;}
	.jctx {display: none; z-index: 1000; position: absolute; overflow: hidden; border: 1px solid #595959; white-space: nowrap; font-family: sans-serif; font-size: 14px; border-radius: 2px; padding: 0; opacity: 0; -webkit-transition:opacity 200ms; -moz-transition:opacity 200ms; -o-transition:opacity 200ms;  -ms-transition:opacity 200ms;}
	.jctx-white {background: white; color: black;}
	.jctx-white-shadow {box-shadow: 0 0 15px gray;}
	.jctx li {padding: 5px 8px; cursor: pointer;}
	.jctx li.disabled {color: darkgrey; cursor: default;}
	.jctx-white li:hover {background-color: #fcc6c9;}
	.jctx-white li.disabled:hover {background-color: lightgrey;}
	.jctx i {padding-right: 6px;}
	.jctx hr {background-color: grey; height: 1px; border: 0; margin: 2px 0px;}
	
</style>
</head>
<body>
<%@ include file="../common/layout.jsp" %>
		<div class="outer">
			<br>
			
			<img src="<%= request.getContextPath() %>/images/knb.png" id="blogo">
			<div class="tableArea">
				<table id="table">
					<thead> <!-- 게시판 라벨 부분 -->
					<tr>
						<th width="40px">No</th>
						<th width="320px" class="title">제목</th>
						<th width="110px">작성자</th>
						<th width="110px">작성일</th>
						<th>조회수</th>
						<th></th>
					</tr>
					</thead>
					<tbody> <!-- 게시판 내용 부분-->
					<% if(list.isEmpty()) { %>
					<tr>
					</tr>
					<% } else {
							for(KnBoard kn : list) {
					%>
					<tr>
						<th><%= kn.getKnNum() %><input type="hidden" name ="knNum" value='<%= kn.getKnNum() %>'></th>
						<td class="title"><%= kn.getknTitle() %><input type="hidden" name ="title" value='<%= kn.getknTitle() %>'></td>
						<td class="jctx-host jctx-id-foo"><%= kn.getUsNick() %><input type="hidden" name ="nickname" value='<%= kn.getUsNick() %>'></td>
						<td><%= kn.getKnDate() %><input type="hidden" name ="kndate" value='<%= kn.getKnDate() %>'></td>
						<td><%= kn.getKnView() %><input type="hidden" name ="knview" value='<%= kn.getKnView() %>'></td>
						<td style="hidden">	        		
						
						<ul class="jctx jctx-id-foo jctx-white jctx-white-shadow">
	    					<li data-action="쪽지보내기"><a href="<%= request.getContextPath() %>/views/message/messageSendView.jsp?id=<%= kn.getUsId() %>">쪽지보내기</a></li>
	    				</ul>
	    				</td>
					</tr>
	           	    <%       
							}
	               
	                    } %>				
					</tbody>
				</table>
				<%if(loginAu != null) {%>
				<input type=button id=writeBtn value=글쓰기 onclick="location.href='<%=request.getContextPath()%>/views/knBoard/knBoardWrite.jsp'">
				<%} %>
				<br><br>
				
      <div class='pagingArea' align='center'>
         <% if(!list.isEmpty()){ %>      
         <button onclick ="location.href='<%=request.getContextPath() %>/list.kn?currentPage=1'" class="pBtn">&laquo;</button> 

         <button onclick="location.href='<%=request.getContextPath() %>/list.kn?currentPage=<%= currentPage -1 %>'" id="beforeBtn" class="pBtn"> &lt;</button>
         <script>
            if(<%= currentPage %> <= 1){
               var before = $('#beforeBtn');
               before.attr('disabled', 'true');
            }
         
         </script>

         <% for(int p = startPage; p <= endPage; p++){ %>
            <% if(p == currentPage) { %>
               <button id="choosen" disabled class="pBtn"><%= p %></button>
            <% } else {%>
               <button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.kn?currentPage=<%= p %>'" class="pBtn"><%= p %></button>
            <% } %>
         <% } %>
         
         <button onclick="location.href='<%= request.getContextPath() %>/list.kn?currentPage=<%= currentPage +1 %>'" id="afterBtn" class="pBtn">></button>
         <script>
            if(<%= currentPage %> >= <%= maxPage %>){
               var after = $("#afterBtn");
               after.attr('disabled', 'true');
            }
         </script>         
         
         <button onclick="location.href='<%= request.getContextPath() %>/list.kn?currentPage=<%= maxPage %>'">&raquo;</button>           
         <% } %>	               	
			</div>
			<form action="<%=request.getContextPath()%>/search.kn">
				<div class="searchArea" style="margin-left: 150px;">
					<select name=searchCategory>
					<option value="작성자">작성자</option>
					<option value="제목">제목</option>
					<option value="내용">내용</option>
					</select>&nbsp;&nbsp;
					<span class="search_container">
		        		<input name=searchTag class="search_input" type="text" placeholder="Search">
		        		&nbsp;&nbsp;	
		        		<input id="searchBtn" type="submit" value="검색" onclick="location.href='<%=request.getContextPath() %>/search.kn'">		
		    		</span>           				
				</div>
			</form>
		</div>
	</div>
	
		<script>
		// 버튼 관련
			$(function(){
				$('tbody td').mouseenter(function(){
					$(this).parent().css({'background':'#ffe3e4', 'cursor':'pointer'});
				}).mouseout(function(){
					$(this).parent().css("background", "none");
				}).click(function(){
					var no = $(this).parent().children().children('input').val();
					location.href='<%= request.getContextPath() %>/detail.kn?no=' + no;
				});
			});
			$('button').mouseenter(function(){
				$('button').css({'background':'#ffe3e4', 'cursor':'pointer'});
			}).mouseout(function(){
				$('button').parent().css("background", "none");
			});
   
			
		</script>
</body>
</html>