<%@ include file="header.jsp" %>
<div class="container">
 <form method="POST" class="form-horizontal" action="login" role="form">
  <div class="form-group form-group-lg">
    <label class="col-sm-4 control-label" for="formGroupInputLarge"> Username:</label>
    <div class="col-sm-4">
      <input class="form-control" name="username" type="text" id="formGroupInputLarge" placeholder="Large input">
    </div>
  </div>
  <div class="form-group form-group-lg">
    <label class="col-sm-4 control-label" for="formGroupInputSmall">Password:</label>
    <div class="col-sm-4">
      <input  name="password" class="form-control" type="text" id="formGroupInputSmall" placeholder="Small input">
    </div>
  </div>
    <div class="form-group form-group-sm">
    <label class="col-sm-4 control-label" for="formGroupInputSmall"></label>
    <div class="col-sm-4">
      <input class="btn btn-primary" type="submit" value="submit">
    </div>
  </div>
</form>
</div>
</body>
</html>