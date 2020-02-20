<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.ArrayList, rvBoard.model.vo.RvBoard, common.model.vo.Paging, photo.model.vo.Photo"%>
<%
	ArrayList<RvBoard> rList = (ArrayList<RvBoard>)request.getAttribute("rList");
	ArrayList<Photo> pList = (ArrayList<Photo>)request.getAttribute("pList");
	Paging pg = (Paging)request.getAttribute("pg");
	
	int listCount = pg.getListCount();
	int currentPage = pg.getCurrentPage();
	int maxPage = pg.getMaxPage();
	int startPage = pg.getStartPage();
	int endPage = pg.getEndPage();
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 후기 게시판</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<style>
	#blogo{margin-left:350px; margin-top: 50px;}
	.outer{width: 900px; height: 800px; background: rgba(255, 255, 255, 0.4); margin-left: 150px; margin-right: auto; margin-top: auto;}
	.tableArea{width: 800px; height: 650px; margin-top: 90px; margin-left: 120px; margin-right: auto; padding: 10px;}
	table > class{margin-left: 55px; margin-top: 30px; width: 680px; padding: 30px;}
	td{padding-bottom: 80px; padding-right:20px;}
	.pagingArea{display: inline-block; margin-left: 200px;}
	.pagingArea button{color: black; float: left; padding: 6px 14px; text-decoration: none; transition: background-color .3s; border: 1px solid #ddd; margin: 0 4px; font-size: 15px; font-weight: 700;}	
	.pagingArea button:hover{color: white; border: 1px solid #fb929e;}
	.pagingArea button.disabled{color:gray;}
	#writeBtn{margin-left: 680px; margin-top: 15px; line-height: 30px; text-align: center; background-color: #ffdfdf; color: #5d5d5d; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#writeBtn:hover {background: #FFF6F6; color: #575756;}
	#searchBtn{line-height: 30px; text-align: center; background-color: #AEDEFC; color: #fff; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#searchBtn:hover {background: #FFF6F6; color: #575756;}
	.searchArea{margin-top: 50px;}
	select{font-size: 10pt; width: 100px; padding: .7em .5em; font-family: inherit; background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; -webkit-appearance: none; -moz-appearance: none; appearance: none; outline:none; border:1px solid #5d5d5d;}
	.search_input{width: 250px;  padding: 12px 24px; font-size: 14px; line-height: 18px; color: #575756; background-size: 18px 18px; border-radius: 50px; border: 1px solid #5d5d5d; backface-visibility: hidden; right:20px; outline:none;}
	td > div{width: 90%; height:90%;}
	td > div > img{width: 90%; height:90%;}
	td > div> span{font-size:14px; text-align: center;}
</style>
</head>
<body>
	<%@ include file="../layout.jsp" %>
		<div class="outer">
		<br>
		<!-- <h1>후기 게시판</h1> -->
		
		<img src="../../images/rvb.png" id="blogo">
		<div class="tableArea">
			<table id="table">
				<tbody>
				<tr>
					<td>
						<div>
							<% for(int i = 0; i < rList.size(); i++){
								RvBoard rv = rList.get(i);						
							%>
							<% for(int j = 0; j < pList.size(); j++){
								Photo p = pList.get(j);							
							%>
								<% if(rv.getRvNum() == p.getRvNum()){ %>
									<img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%=p.getPhChng() %>">
								<% } %>	
						<br><br>
						<span>제목 : <%= rv.getRvTitle() %></span>
						<br>
						<span>병원명 : <%= rv.getHpId() %></span>
						</div>
								<% } %>
							<% } %>	
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
					<td>
						<div>
						<img src="../../images/paw.png">
						<br><br>
						<span>제목</span>
						<br>
						<span>병원명</span>
						</div>
					</td>
				</tr>	
				</tbody>
			</table>
			<input type="submit" id="writeBtn" value="글쓰기">	
			<br><br>
      <!-- 페이징 처리  -->
      <div class='pagingArea' align='center'>
         <!-- list가 있을 때만 나타나는 영역이다.  -->
         <% if(!rList.isEmpty()){ %>      
         <!-- 맨 처음으로 -->
         <button onclick ="location.href='<%=request.getContextPath() %>/list.rv?currentPage=1'" class="pBtn">&laquo;</button> 
         
         
         <!-- 이전 페이지로 -->
         <button onclick="location.href='<%=request.getContextPath() %>/list.rv?currentPage=<%= currentPage -1 %>'" id="beforeBtn" class="pBtn"> &lt;</button>
         <script>
            if(<%= currentPage %> <= 1){
               var before = $('#beforeBtn');
               before.attr('disabled', 'true'); // 첫번째 페이지면 클릭이 안되게 한다. 
            }
         
         </script>
         
         <!-- 10개의 페이지 목록 -->
         <% for(int p = startPage; p <= endPage; p++){ %>
            <% if(p == currentPage) { %>
               <button id="choosen" disabled class="pBtn"><%= p %></button>
            <% } else {%>
               <button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= p %>'" class="pBtn"><%= p %></button>
            <% } %>
         <% } %>
         <!-- 다음 페이지 -->
         <button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= currentPage +1 %>'" id="afterBtn" class="pBtn">></button>
         <script>
            if(<%= currentPage %> >= <%= maxPage %>){
               var after = $("#afterBtn");
               after.attr('disabled', 'true');
            }
         </script>         
         
         <!-- 맨 끝으로 -->
         <button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= maxPage %>'">&raquo;</button>           
         <% } %>
	               
				<div class="searchArea" align="center">
					<select>
					<option value="작성자">작성자</option>
					<option value="제목">제목</option>
					<option value="내용">내용</option>
					</select>&nbsp;&nbsp;
					<span class="search_container">
	         		<input class="search_input" type="text" placeholder="Search">
	     			</span>&nbsp;&nbsp;	      
	     			 <input type="submit" value="검색">			
				</div>	
			</div>
		</div>
	</div>
		<script>
			$(function(){
				$('table td div').mouseenter(function(){
					$(this).parent().css({'background':'#AEDEFC', 'cursor':'pointer'});
				}).mouseout(function(){
					$(this).parent().css("background", "none");
				}).click(function(){
					var no = $(this).children().children().eq(0).val();
					location.href='<%= request.getContextPath() %>/detail.rv?no=' + no;
				});
			});
			
		</script>
</body>
</html>