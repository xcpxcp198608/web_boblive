<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>CREATE</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <!--
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <script type="application/javascript" language="JavaScript" src="js/base.js"/>
    -->
    <link rel="shortcut icon" href="Resource/img/favicon.ico">

</head>

<body style="padding: 0px; margin: 0px">
    <div id="header" style="background-color: black; width: 100%; height: 60px" >
        <span style="width: 100%; font-size: 25px; font-family: Arial;
        text-align: center; line-height: 60px; color: lightyellow; display: block">BOBLIVE</span>
    </div><br/>
    <div style="width: 90%; margin: auto">
    <form action="/boblive/auth/" method="post">
        <input type="text" name="count">
        <select name="group">
            <option value="wiatec1">wiatec1</option>
            <option value="wiatec2">wiatec2</option>
        </select>
        <input type="submit" value="Confirm">
    </form>

    <c:forEach items="${keyList}" var="key">
        ${key}<br>
    </c:forEach>
    </div>
</body>
</html>
