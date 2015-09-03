<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Management</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script type="text/javascript">
$(document).ready(function() {
   $('#submit_btn').click(function() {
	   var userName = $('#username').val();
	   var passowrd = $('#password').val();
	   var msg = null;
	   if(userName=="")
	     msg = "Please Enter User Name";
	   else if(passowrd =="") 
	     msg = "Please Enter Password";
	   
	   if(msg != null) {
	     $('#error_msg').text(msg);
		 return false;  
	   }
   });
});
</script>
</head>
<body class="usrmgt-body">
		
    <form:form action="users" method="post" modelAttribute="user">
    <div class="um-header">
		<div class="um-logo">
			<a href="#"> <img src="./resources/images/um.png" /></a>
		</div>
		<div class="um-title">
			<h3>${appName}</h3>
		</div>
	</div>
	  <div class="container login-div" id="login-container">
        <h3>Login</h3>
        <label id="error_msg" style="color: red">${errorMsg}</label>
        <div class="form-group">
          <label for="username">UserName:</label>
          <form:input id="username" class="form-control" path="username"/>
       </div>
       <div class="form-group">
        <label for="password">Password:</label>
        <form:password id="password" class="form-control"  path="password"/>
      </div>
      <div class="form-group">
        <input class="btn btn-primary" id="submit_btn"  type="submit" value="Login">
      </div>
     </div>	
</form:form>
	
</body>
</html>