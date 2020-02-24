<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, common.model.vo.Paging, point.model.vo.Point" %>
<%
	ArrayList<Point> list = (ArrayList<Point>)request.getAttribute("list");
	Paging pg = (Paging)request.getAttribute("pg");
	
	int curPt = 0;
	int point = 0;
	String ptHis = null;
	
	if(list.isEmpty()){
		curPt = 0;
	}else{
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getPtAdd() != 0){
				curPt += list.get(i).getPtAdd();
			}else {
				curPt -= list.get(i).getPtUse();
			}
			
			switch(list.get(i).getPtCate()){
			case 1 : ptHis = "리뷰 작성"; break;
			case 2 : ptHis = "좋아요 포인트"; break;
			case 3 : ptHis = "쿠폰 구매"; break;
			case 4 : ptHis = "포인트 기부"; break;
			}
			list.get(i).setPtHis(ptHis);		
		}
	}
	
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
<title>꼬리스컴바인 : 포인트</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<style>
<style>
	#blogo{margin-top: 50px;}
	#pointView{border-color: #AEDEFC; cursor: default;}
	#pointBtn{margin-left: 900px; margin-top: 20px; min-width: 300px;}
	.outer{width: 100px; height: 800px; background: rgba(255, 255, 255, 0.4); margin-left: 300px; margin-right: auto; margin-top: auto;}
	.writeArea{width: 800px; height: 600px; margin-top: 10px; margin-left: 180px; margin-right: auto; padding: 10px; border: 1px solid #FB929E;}
	table{margin-left: auto; margin-right: auto; margin-top: 3px; max-width:536px; overflow: auto;}
	table, th, td{word-spacing: 3px; padding: 3px;}
	tr > th{padding-bottom: 50px; padding-top: 20px; font-size: 20pt;}
	tbody > tr > td{padding-left : 40px; padding-bottom : 10px;}
	tr.label{font-size: 15pt; font-weight: 800;}
	.pagingArea{display: inline-block; margin-left: 150px; width: 200px; margin-top: 100px;}
	.pagingArea button{color: black; float: left; padding: 5px 12px; text-decoration: none; transition: background-color .3s; border: 1px solid #ddd; margin: 0 4px; font-size: 10px; font-weight: 700;}	
	.pagingArea button:hover{color: #fb929e; border: 1px solid #fb929e;}
	.pagingArea button.disabled{color:gray;}	
	.pt{text-align: right;}
	.input{font-family: inherit; width: 100%; border: 0; outline: 0; background: transparent; transition: border-color 0.2s;}	
	#page{margin-left: 270px;}
/* 	#pHistory{font-size: 15px; margin-left: 50px;  text-align: center; background-color: #575756; color: #FFF6F6; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#pHistory:hover {background: #ffe3e4; color: #575756; cursor: pointer;} */
	#pUsing{font-size: 15px; text-align: center; background-color: #575756; color:#FFF6F6; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px; }
	#pUsing:hover {background: #ffe3e4; color: #575756; cursor: pointer;}
</style>
</head>
<body>
<%@ include file="../common/layout.jsp" %> 
		<!-- 포인트 -->	
		<div class="outer">
			<img src="<%= request.getContextPath() %>/images/point.png" id="blogo" style="margin-left: 500px; margin-top: 50px;">
			<br>
			<form action="views/point/pointUse.jsp" method="post">
				<input type="text" id="pointView" style="margin-left: 450px; margin-top: 50px; width: 220px; height: 30px;" value="<%= curPt %>pt" placeholder="보유 포인트" disabled>
				<input type="hidden" name="curPt" value="<%= curPt%>">
		<br>
		<div id="pointBtn">
			<input type="submit" id="pUsing" value="포인트 사용">
		</div>
			</form>
		<div class="writeArea">			
				<table>
					<thead>				
					<tr>
						<th colspan="3">포인트 기록
						</th>			
					</tr>
					</thead>
					<tr class="label">
						<td style="width: 150px;">날짜</td>
						<td style="width: 200px;">포인트 내역</td>
						<td style="width: 100px;" class="pt">포인트</td>
					</tr>
					<tbody>				
					<% if(list.isEmpty()) { %>
					<tr>
					</tr>
					<% } else {
							for(Point pt : list) {	
					%>			
					<tr>
						<td><%= pt.getPtDate() %><input type="hidden" name ="date" value='<%= pt.getPtDate() %>'></td>
						<td><%= pt.getPtHis() %><input type="hidden" name ="ptHis" value='<%= pt.getPtHis() %>'></td>
						
							<% if(pt.getPtAdd() != 0){ %>
								<td class="pt"><%= pt.getPtAdd() %>pt<input type="hidden" name ="ptHis" value='<%= pt.getPtAdd() %>'></td>						
							<% } else { %>
								<td class="pt"><%= pt.getPtUse() %>pt<input type="hidden" name ="ptHis" value='<%= pt.getPtUse() %>'></td>							
							<% } %>
					</tr>
	           	    <%       }				               
                    } %>
                   </tbody>	
                   <tfoot>
                   <tr>
                   	<td></td>
                   </tr>
                   <tr>
                   		<td colspan=3>
	     					 <!-- 페이징 처리  -->
					      <div class='pagingArea' align='center'>
					         <!-- list가 있을 때만 나타나는 영역이다.  -->
					         <% if(!list.isEmpty()){ %>      
					         <!-- 맨 처음으로 -->
					         <button onclick ="location.href='<%=request.getContextPath() %>/list.pt?currentPage=1'" class="pBtn">&laquo;</button> 
					         
					         
					         <!-- 이전 페이지로 -->
					         <button onclick="location.href='<%=request.getContextPath() %>/list.pt?currentPage=<%= currentPage -1 %>'" id="beforeBtn" class="pBtn"> &lt;</button>
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
					               <button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.pt?currentPage=<%= p %>'" class="pBtn"><%= p %></button>
					            <% } %>
					         <% } %>
					         <!-- 다음 페이지 -->
					         <button onclick="location.href='<%= request.getContextPath() %>/list.pt?currentPage=<%= currentPage +1 %>'" id="afterBtn" class="pBtn">></button>
					         <script>
					            if(<%= currentPage %> >= <%= maxPage %>){
					               var after = $("#afterBtn");
					               after.attr('disabled', 'true');
					            }
					         </script>         
					         
					         <!-- 맨 끝으로 -->
					         <button onclick="location.href='<%= request.getContextPath() %>/list.pt?currentPage=<%= maxPage %>'">&raquo;</button>           
					         <% } %>
		  					</div> 
	  					</td>	                       
                   </tr>
                   </tfoot> 					
				</table>	
			</div>
		</div>						
		<script>
/* 			$(function(){
				$('tbody td').mouseenter(function(){
					$(this).parent().css({'background':'#ffe3e4', 'cursor':'pointer'});
				}).mouseout(function(){
					$(this).parent().css("background", "none");
				});			
			}); */
		</script>
</body>
</html>