<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New or Edit User</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script type="text/javascript">
$(document).ready(function() {
   $('#submit_btn').click(function() {
       var userName = $('#username').val();
       var passowrd = $('#password').val();
       var email = $('#email').val();
       var msg = null;
       if(userName == "")
         msg = "Please Enter User Name";
       else if(email == "" || !validateEmail(email)) 
           msg = "Please Enter Valid Email";
       else if(passowrd == "") 
           msg = "Please Enter Password";
       if(msg != null) {
         $('#error_msg').text(msg);
         return false;  
       }
   });
   
   function validateEmail(email) {
	    var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(email);
	}
});
</script>
</head>
<body class="usrmgt-body">	
<div class="um-header">
		<div class="um-logo">
			<a href="#"> <img src="./resources/images/um.png" /></a>
		</div>
		<div class="um-title">
			<h3>${appName}</h3>
		</div>
	</div>
<%@include file="sub-header.jsp"%>	
<form:form action="update" method="post" modelAttribute="user">
<form:hidden path="id"/>
	<div class="container" id="user-container">
	<div class="um-title">
<h3 style="color: #0b7bb7;">Edit User</h3>
</div>
              <label id="error_msg" style="color: red">${errorMsg}</label>
    <div class="form-group">
      <label for="username">UserName:</label>
      <form:input id="username" class="form-control" ReadOnly="True" path="username"/>
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <form:password id="password" class="form-control"  path="password"/>
    </div>
     <div class="form-group">
      <label for="email">Email:</label>
      <form:input id="email" class="form-control" path="email"/>
    </div>
    
    
    <div>
      <input class="btn btn-primary" id="submit_btn" style="margin-top: 25px;" type="submit" value="Update">
    </div>
</div>
</form:form>
</body>
<%@include file="footer.jsp"%>
</html>