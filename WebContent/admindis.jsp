<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<script src="js/jquery-2.1.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
   <div class="userList">
	   <div class="userDiv" style="display:none">
		   <div>
			   <span class="name">gu</span>
			   <span class="password">12345678</span>
			   <span class="balance">1000</span>
			   <span class="bannedOrNot"></span>
			   <button class="banUser" onclick="myFunction(this)">ban</button>
		   </div>
	   </div>
   </div>
</div>
<div class="changeUnitsDiv">
	<div class="productList" style="display:none">
		<div>
			   <span class="proName">babab</span>
			   <span class="proUnits">111111</span>
			   <span class="proPrice">111111</span>
			   <button class="changeUnits" onclick="changeProduct(this)">change</button>
		</div>
	</div>
</div>
<div class="changeForm" style="display:none">
    <input name="proName" class="newName" type="hidden">
    <label>input the amount of units</label>
    <input name="units" class="unitsNum" type="text">
    <label>input the price</label>
    <input name="price" class="priceNum" type="text" >
    <input class="submitChange" type="submit" value="submit">
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$.get( "UserList" )
	  .done(function( data ) {
	  	 var json = jQuery.parseJSON(data);
	     for(var m=0; m<json.length;m++)
	     {
	   	  $('.userDiv').find(".name").text(json[m].username);
		  $('.userDiv').find(".password").text(json[m].password);
		  $('.userDiv').find(".balance").text(json[m].balance);
      	  if(json[m].banned)
  		  {
      		 $('.userDiv').find(".bannedOrNot").text("banned");
      		 $('.userDiv').find(".banUser").text("unban it");
  		  }
      	  else
   		  {
      		 $('.userDiv').find(".bannedOrNot").text("not banned");
      		 $('.userDiv').find(".banUser").text("ban it");
   		  }
		  var finalHtmlString = $('.userDiv').html();
	      $('.userList').append(finalHtmlString);
	     }
	  });
	$.get( "ProductList" )
	  .done(function( data1 ) {
	  	 var json1 = jQuery.parseJSON(data1);
 	     for(var m=0; m<json1.length;m++)
	     {
 	     	  $('.productList').find(".proName").text(json1[m].name);
	      	  $('.productList').find(".proUnits").text(json1[m].units);
	      	  $('.productList').find(".proPrice").text(json1[m].price); 
	      	  var finalHtmlString1 = $('.productList').html();
	          $('.changeUnitsDiv').append(finalHtmlString1); 
	     }  
	  });
	$('.submitChange').click(function(){
       var proName = $(".changeForm").find('.newName').val();
       var units =  $(".changeForm").find('.unitsNum').val();
       var price = $(".changeForm").find('.priceNum').val();
		$.get( "modifyProduct",{proName:proName,units:units,price:price} )
		  .done(function() {
			  location.reload();
		  });
	})
})
	var myFunction = function(selector) {
        var username = $(selector).parent("div").find(".name").text();
		$.get( "banUsers",{username:username} )
		  .done(function() {
			  location.reload();
		  });
	};
	var changeProduct = function(selector) {
		$(".changeForm").css('display','block');
        var proName = $(selector).parent("div").find(".proName").text();
        var originalUnits = $(selector).parent("div").find(".proUnits").text();
        var originalPrice = $(selector).parent("div").find(".proPrice").text();
        $(".changeForm").find('.newName').val(proName);
        $(".changeForm").find('.unitsNum').val(originalUnits);
        $(".changeForm").find('.priceNum').val(originalPrice);
        
	};
</script>
</html>