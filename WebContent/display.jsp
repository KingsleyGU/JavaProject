<%@ include file="header.jsp" %>
<div class="container" style="padding-bottom:100px;">
	<div id="outerDiv" class="row">
		<div class="innerDiv" style="display:none;">
			<div class="col-md-4 itemBlock" style="margin-top:40px; text-align:center;">
			<image class="prodouctImage img-rounded" style="width:250px;height:250px;">
			<div class="descItem">Product Name: <span class="name">name</span></div>
			<div class="descItem">Product Price: <span class="price">price</span></div>
			<div class="descItem">Product Units: <span class="units"></span></div>
			<div class="descItem">Description: <span class="description">achajhsdc</span></div>
			<button class="basketBtn btn btn-info" onclick="myFunction(this)">put this item to basket</button>
			</div>
		</div>
		<div class="buyitem" style="display:none;">
		<input id="itemPrice" type="text"  >
		<input id="itemName" type="text" name="itemName">
		<label>Input the amount you want:</label>
		<input id="amountItem" type="text" name="amount">
		<input id="putBtn" type="submit" value="go">
		</div>
	</div>
</div>
</body>
<style>
.descItem
{

	weight:bold;
	margin:10px;
	font-size:14px;	
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$.get( "display" )
	  .done(function( data ) {
	   var json = jQuery.parseJSON(data);
       for(var m=0; m<json.length;m++)
       {
    	  $('.innerDiv').find(".name").text(json[m].name);
    	  $('.innerDiv').find(".price").text(json[m].price);
    	  $('.innerDiv').find(".units").text(json[m].units);
    	  $('.innerDiv').find(".description").text(json[m].description);
    	  $('.innerDiv').find(".prodouctImage").attr("src", "uploads/" + json[m].image);
    	  var finalHtmlString = $('.innerDiv').html();
          $('#outerDiv').append(finalHtmlString);
       }
	  });

	   $('#putBtn').click(function(){
		   var itemName = $(this).parents(".buyitem").find("#itemName").val();
		   var amount = parseInt($(this).parents(".buyitem").find("#amountItem").val());
		   var price = parseInt($(this).parents(".buyitem").find("#itemPrice").val());
	       if(isNaN(amount))
            {
	    	   alert("the amount must be a number");
            }
	       else
    	   {
	    		$.get( "basket",{itemName:itemName, amount:amount, price:price} )
	    		  .done(function() {
	    			  location.reload();
	    		  });
    	   }
	     /*   return false; */
	   })
	});
	var myFunction = function(selector) {
	 /*    $(".buyitem").css('display','block'); */
		   var name = $(selector).parent('.itemBlock').find(".name").text();
		   var price = $(selector).parent('.itemBlock').find(".price").text();
		    var amount = prompt("Please enter the amount you want to buy", "1");
	       if(isNaN(amount))
            {
	    	   alert("the amount must be a number");
            }
	       else
    	   {
	    	   var finalAmount = parseInt(amount);
	    		$.get( "basket",{itemName:name, amount:finalAmount, price:price} )
	    		  .done(function() {
	    			  location.reload();
	    		  });
    	   }
	};
</script>
</html>