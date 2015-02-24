<%@ include file="header.jsp" %>
<div class="container" style="margin-top:30px; ">
	<div style="text-align:center; padding-bottom:70px;">
	  <span style="font-size:20px;">balance:<% out.println(request.getAttribute("balance")); %></span>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <a class="btn btn-danger depositLink">click to deposit</a>
    </div>
  <div id="depositForm" style="display:none;">
  <label>input the amount of money you want to deposit:</label>
  	<input id="depositAmount" name="amount" type="text" />
  	<input type="submit" id="depositSub" value="submit">
  </div>
	<div class="basketList" >
		<div class="basketTmeplate" style="display:none;">
			<div>
			<div class="proCSS">   Product Name: <span class="productName">1111</span></div>
			<div class="proCSS">  Product Units: <span class="units">10</span></div>
			<div class="proCSS"> Cost: <span class="itemMoney">1000</span></div>
			    <button style="float: right;"class="delete btn btn-warning" onclick="deleteBasket(this)">delete</button>
		    </div>
		    <hr/>
		</div>     
	</div>
	<div class="payAction" style="padding-top:50px; float:right;">
	<div style="display:inline-block; margin-right:20px;">Total Price:<span class="totalPrice"></span></div>
	<button class="payItems btn btn-success">Pay the items</button>
	</div>
</div>
<style>
.proCSS
{
   display:inline-block;
   width:350px;
   
}
</style>
<script type="text/javascript">
   $(function(){
		$.get( "basketList" )
		  .done(function( data ) {
		   var json = jQuery.parseJSON(data);
		   var totalMoney = 0;
	       for(var m=0; m<json.length;m++)
	       {
	    	  $('.basketTmeplate').find(".productName").text(json[m].product);
	    	  $('.basketTmeplate').find(".units").text(json[m].units);
	    	  $('.basketTmeplate').find(".itemMoney").text(json[m].totalPrice);
	    	  totalMoney = totalMoney + parseInt(json[m].totalPrice);
	    	  var finalHtmlString = $('.basketTmeplate').html();
	          $('.basketList').append(finalHtmlString);
	       }
	       $(".totalPrice").text(totalMoney);
		  });
	   $(".depositLink").click(function(){
		 /*   $("#depositForm").css('display','block'); */
		   var amount = prompt("Please enter the amount you want to deposit", "1000");
	       if(isNaN(amount))
            {
	    	   alert("the amount must be a number");
            }
	       else
    	   {
	    	   var finalAmount = parseInt(amount);
	    	   $.get( "deposit",{amount:finalAmount} )
	    		  .done(function() {
	    			  location.reload();
	    		  });
    	   }
		   return false;
	   });
	   $('#depositSub').click(function(){
		   var amount =parseInt($(this).parents('#depositForm').find('#depositAmount').val());
	       if(isNaN(amount))
            {
	    	   alert("the amount must be a number");
            }
	       else
    	   {
	    		$.get( "deposit",{amount:amount} )
	    		  .done(function() {
	    			  location.reload();
	    		  });
    	   }
	     /*   return false; */
	   });
	   $('.payItems').click(function(){
		   var totalAmount =parseInt($(this).parents('.payAction').find('.totalPrice').text());
		   var balance = parseInt(<% out.println(request.getAttribute("balance")); %>);
	       if(totalAmount>balance)
            {
	    	   alert("you don't have enough balance to pay.");
            }
	       else
    	   {
	    		$.get( "payItems",{totalPrice:totalAmount})
	    		  .done(function(data) {
	    			  if(data!="")
    				    alert(data);
	    			  location.reload();
	    		  });
    	   }
	     /*   return false; */
	   });
	   
   })
   	var deleteBasket = function(selector) {
		 var name = $(selector).parent('div').find(".productName").text();
 		$.get( "deleteBasket",{itemName:name} )
		  .done(function() {
			  location.reload();
		  });
	};
</script>
</body>
</html>