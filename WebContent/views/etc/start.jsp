<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"> 
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/css-stars.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery.barrating.min.js"></script>
<script type="text/javascript"></script>

<title>Insert title here</title>
</head>
<body>

          <div class="col col-fullwidth">
            <div class="star-ratings">
              <p>It can be used to display fractional star ratings.</p>
              <div class="stars stars-example-fontawesome-o">
                <select id="example-fontawesome-o" name="rating" data-current-rating="5.6" autocomplete="off">
                  <option value=""></option>
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                  <option value="6">6</option>
                  <option value="7">7</option>
                  <option value="8">8</option>
                  <option value="9">9</option>
                  <option value="10">10</option>
                </select>
                <span class="title current-rating">
                  Current rating: <span class="value"></span>
                </span>
                <span class="title your-rating hidden">
                  Your rating: <span class="value"></span>&nbsp;
                  <a href="#" class="clear-rating"><i class="fa fa-times-circle"></i></a>
                </span>
              </div>
            </div>
		</div>
	              <div class="stars stars-example-css">
                <select id="example-css" name="rating" autocomplete="off">
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                </select>
                <span class="title">CSS Stars</span>
              </div>	
              
              
              
<select id="example-css" name="rating" autocomplete="off">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
</select>
              
              
              
<script type="text/javascript">
   $(function() {
      $('#example-css').barrating({
        theme: 'css-stars',
        fastClicks: true,
    	, onSelect: function(value, text, event){ 
    		valu
    	}
      });
   });
</script>
</body>
</html>