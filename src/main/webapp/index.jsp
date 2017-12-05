<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title>LOGIN</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />  
  <meta name="apple-mobile-web-app-capable" content="yes" />  
  <meta name="format-detection" content="telephone=no" />  
  <!--
  <link rel="stylesheet" type="text/css" href="css/base.css">
  <script type="application/javascript" language="JavaScript" src="js/base.js"/>
  <link rel="shortcut icon" href="img/xxx.ico">
  -->
  <link rel="shortcut icon" href="Resource/img/favicon.ico">
  <style type="text/css">
    .input {
      font-size: 24px;
    }
    #submit {
      width: 250px;
      height: 40px;
      line-height: 40px;
      font-size: 22px;
      border: none;
    }
  </style>
</head>
<body style="padding: 0px; margin: 0px; background-color: black">
<div style="text-align: center; margin: 300px auto auto;" >
  <form method="post" action="/boblive/manager/login">
    <input class="input" type="text" name="userName" size="20"><br/><br>
    <input class="input" type="password" name="password" size="20"><br><br><br>
    <input class="input" id="submit" type="submit" value="Login">
  </form>
</div>
</body>
</html>
