<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="addItem" method="post" enctype="multipart/form-data">
		<label>Input the name of the product:</label>
		<input type="text" name="name" />
		<label>Input the units of the product:</label>
		<input type="text" name="units">
		<label>Input the description of the product:</label>
	    <input type="text" name="description" />
	    <label>Select a profile image for the product:</label>
	    <input type="file" name="file" />
	    <input type="submit" />
	</form>

</body>
</html>