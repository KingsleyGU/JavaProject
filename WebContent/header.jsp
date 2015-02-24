<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<script src="js/jquery-2.1.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	
	String userId = "";
	if(cookies == null)
		return;
	for(Cookie cook : cookies){
/* 		  userId = userId+"111111"+cook.getName() + " " + cook.getValue(); */
         if("username".equals(cook.getName())){
	        userId = cook.getValue();
	   }
	}
	if(userId.equals(""))
	{
%>
<a href="login.jsp">login</a>
<a href="register.jsp">register</a>
<%
	}
	else
	{
%>
<div class="client">welcome,
<a href="customerInfo"><% out.println(userId); %></a>
</div>
<%
	}
%>
