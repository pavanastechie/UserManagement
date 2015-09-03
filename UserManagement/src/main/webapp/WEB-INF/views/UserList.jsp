<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body class="usrmgt-body" style="height: 500px">
	<div class="um-header">
		<div class="um-logo">
			<a href="#"> <img src="./resources/images/um.png" /></a>
		</div>
		<div class="um-title">
			<h3>${appName}</h3>
		</div>
	</div>
	<div align="center">
		<%@include file="header.jsp"%>
		<div style="width: 305px;font-size: 17px; height: 40px; margin-right: 85px;">
	<label id="success_msg" style="color: green; font-size: 20px; margin-top: 5px;">${successMsg}</label>
	</div>
		<table class="table table-striped" style="margin-top: 50px;">
			<th>User Id</th>
			<th>Username</th>
			<th>Email</th>
			<th>Actions</th>

			<c:forEach var="user" items="${userList}" varStatus="status">
				<tr>
					<td class="users-userid">${user.id}</td>
					<td class="users-username">${user.username}</td>
					<td class="users-email">${user.email}</td>
					<td class="users-actions"><a
						class="btn btn-primary btn-sm edit_user" id="edit_user_${user.id}"
						href="edit?id=${user.id}">Edit</a> <a
						class="btn btn-danger btn-sm delete_user"
						id="delete_user_${user.id}"onclick="deleteUser('delete?id=${user.id}');">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
<div id="modal-content" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3 style="font-size: 24px">Delete User</h3>
            </div>
            <div class="modal-body">
                <p  style="font-size: 20px">
                   Are you sure, Do you want to delete user?
                
                </p>
            </div>
            <div class="modal-footer"> 
                <a href="#"  style="font-size: 20px" id="cancel-delete-user" class="btn" data-dismiss="modal">Cancel</a>
                 <a id="delete-user-data"  style="font-size: 20px" class="btn btn-primary">Delete</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
//set focus when modal is opened
// everytime the button is pushed, open the modal, and trigger the shown.bs.modal event
function deleteUser(url) {
	console.log(url);
	
    $('#modal-content').modal({
        show: true
    });
    $('#delete-user-data').attr('href', url);
}
</script>
</html>
<%@include file="footer.jsp"%>