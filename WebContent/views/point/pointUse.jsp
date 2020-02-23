<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int curPt = Integer.parseInt(request.getParameter("curPt"));
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꼬리스컴바인 : 포인트 사용</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<style>
	#blogo{margin-top: 50px;}
	#pointView{border-color: #AEDEFC; cursor: default;}
	#pointBtn{margin-left: 730px; margin-top: 20px; min-width: 400px;}
	.outer{width: 800px; height: 650px; background: rgba(255, 255, 255, 0.4); margin-left: 300px; margin-right: auto; margin-top: auto;}
	.writeArea{width: 800px; height: 600px; margin-top: 10px; margin-left: 180px; margin-right: auto; padding: 10px; border: 1px solid #AEDEFC;}
	table{margin-left: 100px; margin-top: 3px; max-width:600px;}
	table, th, td{word-spacing: 3px; padding: 3px;}
	tr > th{padding-bottom: 10px; padding-top: 5px; font-size: 20pt;}
	tbody > tr > td{padding-left : 40px; padding-bottom : 10px;}
	td > div{width: 35%; height:35%;}
	td > div > img{width: 90%; height:90%; align: center;}
	td > div > input[type="button"]{margin-left: 300px; font-size: 20px; text-align: center; color: #575756; border: 1px solid #575756; border-radius: 5px; width: 140px; height: 35px; cursor: pointer;}
	.pt{text-align: right;}
	#page{margin-left: 270px;}
	#pHistory{font-size: 15px; margin-left: 50px;  text-align: center; background-color: #575756; color: #FFF6F6; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#pHistory:hover {background: #ffe3e4; color: #575756;}
	#pUsing{font-size: 15px; text-align: center; background-color: #575756; color:#FFF6F6; border: 1px solid #575756; border-radius: 5px; width: 80px; height: 35px;}
	#pUsing:hover {background: #ffe3e4; color: #575756;}
	#hList{display:none;}
	.modal-content > input[type="button"]{border: none; background-color: white; cursor: pointer; -webkit-appearance: none; -moz-appearance: none; appearance: none; outline:none;}
	#coupon{background: white;}
	#donate{background: white;}
	
    /* The Modal (background) */
     .modal {display: none; position: fixed; z-index: 1; left: 0; top: 0; width: 100%; height: 100%; overflow: auto; background-color: rgb(0,0,0); background-color: rgba(0,0,0,0.4);}
     /* Modal Content/Box */
     .modal-content {background-color: #fefefe; margin: 20% auto; padding: 20px; border: 1px solid #888; width: 50%; height: 50%;}
     /* The Close Button */
     .close {color: #aaa; float: right; font-size: 28px; font-weight: bold;}
     .close:hover,
     .close:focus {color: black; text-decoration: none; cursor: pointer;}
</style>
</head>
<body>
<%@ include file="../common/layout.jsp" %> 
		<div class="outer">
			<img src="../../images/point.png" id="blogo" style="margin-left: 500px; margin-top: 50px;">
			<br>
			<input type="text" id="pointView" style="margin-left: 450px; margin-top: 50px; width: 220px; height: 30px;"  value="<%= curPt %>pt"  placeholder="보유 포인트" disabled>
		<br>
		<div id="pointBtn">
<!-- 			<input type="submit" id="pUsing" value="포인트 사용"> -->
		</div>
		<div class="writeArea">
				<table>
					<tbody>
					<tr>
						<th>
							쿠폰 구매
						</th>
					</tr>
					<tr>
						<td>
							<div>
								<img src="../../images/coupon.png">
								<input type="button" value="쿠폰 구매" id="coupon">
							</div>				
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td style="border-bottom: 1px solid gray; margin-bottom:5px;">
						</td>
					</tr>
					<tr>
						<th>기부 하기</th>
					</tr>
					<tr>
						<td>
							<div>
								<img src="../../images/dona.png">
								<input type="button" value="기부 하기" id="donate">									
							</div>
						</td>
					</tr>
					</tbody>																																								
				</table>
			</div>
		</div>
	 
	    <!-- The Modal -->
	    <div id="cmodal" class="modal">	 
	      <!-- Modal content -->
	      <iframe name="post1" id="post1" style="display:none" frame_border="0"></iframe> 
	      <form action="<%= request.getContextPath() %>/coupon.pt" method="post" target="post1">
		      <div class="modal-content" style="text-align:center">
		        <span class="close">&times;</span>                                                               
		        <p><font style="font-size:25px; font-weight:500;">쿠폰 구매</font></p>
		        <br><br>
		        <p>구매할 쿠폰 종류를 선택해주세요</p>
				<select name="couponcate">
					<option>--------</option>
					<option value="fee">진료비 1% 10000pt</option>
					<option value="beauty">미용비 1% 10000pt</option>
					<option value="goods">반려동물 용품 구매비 1% 10000pt</option>
				</select>	
				<input type="submit" value="구매" id="cPurchase">
				<br><br>
					<input type="button" id="goHlist" value="쿠폰 사용가능한 병원 리스트">
					<br>
					<span id="hList">a병원 b병원 c병원 d병원 e병원 f병원</span>			
					<br>
					<span><font style="font-size:10px">*사용할 때는 쿠폰을 제시해주세요.</font></span>        
		      </div>
	      </form>	 
	    </div>
	    
	     <!-- The Modal -->
	    <div id="dmodal" class="modal">	 
	      <!-- Modal content -->
	      <iframe name="post2" id="post2" style="display:none" frame_border="0"></iframe> 
	      <form action="request.getContextPath() %>/dona.pt" method="post" target="post2">
		      <div class="modal-content" style="text-align:center">
		        <span class="close">&times;</span>                                                               
			    <p><font style="font-size:25px; font-weight:500;">기부 하기</font></p>
			        <p>기부할 포인트와 보호소를 선택해주세요</p>
			        <br>
					<select name="donaPoint">
						<option>--------</option>
						<option value="100">100pt</option>
						<option value="5000">500pt</option>						
						<option value="1000">1000pt</option>
						<option value="2000">2000pt</option>
						<option value="3000">3000pt</option>
						<option value="4000">3000pt</option>
						<option value="5000">5000pt</option>
						<option value="10000">10000pt</option>
					</select>&nbsp;&nbsp;
					<select name="donaHouse">
						<option>--------</option>
						<option>a보호소</option>
						<option>5보호소</option>
						<option>1보호소</option>
						<option>5보호소</option>
						<option>1보호소</option>
					</select>
					&nbsp;&nbsp;
				<input type="submit" value="기부하기" id="pDonate">											    
		      </div> 
	      </form>	
	    </div>

		
	<script type="text/javascript">	  
		// Get the modal
		var modal = document.getElementById('cmodal');
		// Get the button that opens the modal
		var btn = document.getElementById("coupon");
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];                                          
		// When the user clicks on the button, open the modal 
		btn.onclick = function() {
		    modal.style.display = "block";
		}
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
		    modal.style.display = "none";
		}
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
		    if (event.target == modal) {
		        modal.style.display = "none";
		    }
		}

		// Get the modal
		var modal1 = document.getElementById('dmodal');
		// Get the button that opens the modal
		var btn1 = document.getElementById("donate");
		// Get the <span> element that closes the modal
		var span1 = document.getElementsByClassName("close")[2];                                          
		// When the user clicks on the button, open the modal 
		btn1.onclick = function() {
		    modal1.style.display = "block";
		}
		// When the user clicks on <span> (x), close the modal
		span1.onclick = function() {
		    modal1.style.display = "none";
		}
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
		    if (event.target == modal1) {
		        modal1.style.display = "none";
		    }
		}

		//병원 리스트 관리
		$('#goHlist').click(function(){
	        if($("#hList").css('display') == 'none'){
	            $("#hList").show();
	        }else{
	            $("#hList").hide();
	        }
		});
		
		// 기부 팝업창
		var x = (window.screen.availWidth - document.body.offsetWidth)/2;
		var y = ( window.screen.availHeight - document.body.offsetHeight ) / 2;

		$('#pDonate').click(function(){
			window.open("donateResult.jsp", "기부성공!",'status=no, toolbar=no, scrollbars=no, location=no, menubar=no, directories=0, height=500, width=500, left='+ x + ', top='+ y + ', screenX='+ x + ', screenY= '+ y);
		});
			
		$('#cPurchase').click(function(){
			window.open("couponResult.jsp", "구매확인",'status=no, toolbar=no, scrollbars=no, location=no, menubar=no, height=500, width=500, left='+ x + ', top='+ y + ', screenX='+ x + ', screenY= '+ y);
		});
	
		// 버튼 색
		$('#coupon').mouseenter(function(){
			$('#coupon').css({'background':'#AEDEFC', 'cursor':'pointer'});
			}).mouseout(function(){
				$('#coupon').css("background", "none");
			});
		
		$('#donate').mouseenter(function(){
			$('#donate').css({'background':'#AEDEFC', 'cursor':'pointer'});
			}).mouseout(function(){
				$('#donate').css("background", "none");
			});
		
		
	</script>
</body>
</html>