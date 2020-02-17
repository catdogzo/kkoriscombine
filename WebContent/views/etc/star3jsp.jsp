<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>jQuery Raty - A Star Rating Plugin</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery.raty.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.raty.js"></script>
 
    </head>
    <body>
        <div id="star" ></div> <!-- 별점 -->
 
        <div style="padding-top:20px;">
            <label for="starRating">Value : </label><input type="text" id="starRating">
        </div>
 
        <div style="padding-top:20px;">
            <label for="displayStarRating">Value : </label>
            <span id="displayStarRating" style="padding-left:20px;"></span>
        </div>
    </body>
 
    <script type="text/javascript">
    	$(document).ready(function() {
            $('div#star').raty({
                path : "<%= request.getContextPath() %>/images",
                width : 200,
                half: false,
                halfShow: true,
                click: function(score, evt) {
                    $("#starRating").val(score);
                    $("#displayStarRating").html(score);
                }
			                
            });
        });
    </script>
</html>