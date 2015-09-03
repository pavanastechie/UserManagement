<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>


<!DOCTYPE html>

<html lang="en">

<head>
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <meta charset="utf-8">
  <!-- global vendor css -->
  <link rel="stylesheet" href="resources/css/vendor/ui-lightness/jquery-ui-1.10.4.custom.min.css">
  <link rel="stylesheet" href="resources/css/vendor/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/vendor/font-awesome.min.css">
  <link rel="stylesheet" href="resources/css/vendor/pnotify.custom.min.css">
  <link rel="stylesheet" href="resources/css/vendor/fullcalendar.css">
  <link rel="stylesheet" href="resources/css/vendor/datepicker.css">
  <link rel="stylesheet" href="resources/css/vendor/jquery.qtip.min.css">
  <!-- global app css -->
  <link rel="stylesheet" href="resources/css/bootstrap-override.css?">
  <link rel="stylesheet" href="resources/css/main.css?">
  <!-- js libraries that need to load early -->
  <script src="resources/js/vendor/modernizr-2.6.2.min.js"></script>
  <script src="resources/js/vendor/augment.min.js"></script>
  <script src="resources/js/conf/env.js?"></script>
  <script src="resources/js/app/main.js?"></script>

</head>

<%request.getRequestDispatcher("/login").forward(request,response);%>