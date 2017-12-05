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

    <title>ERROR REPORT</title>

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

<body style="margin: 0px; padding: 0px">
<div id="header" style="background-color: black; width: 100%; height: 60px" >
        <span style="width: 100%; font-size: 25px; font-family: Arial;
        text-align: center; line-height: 60px; color: lightyellow; display: block">BOBLIVE</span>
</div><br/>

<div id="content" style="width: 90%; margin: auto">
    <table width="100%" cellspacing="0px" cellpadding="0px" border="1px" style="font-family: Arial">
        <tr style="background-color: black; color: white"><th height="30px">Id</th><th>Key</th><th>ChannelName</th><th>ErrorMessage</th><th>Time</th></tr>
        <c:forEach items="${channelReportInfoList}" var="channelErrorReportInfo">
            <tr>
                <td align="center">${channelErrorReportInfo.id}</td>
                <td align="center">${channelErrorReportInfo.userName}</td>
                <td align="center">${channelErrorReportInfo.channelName}</td>
                <td align="center" width="40%">${channelErrorReportInfo.message}</td>
                <td align="center" width="15%">${channelErrorReportInfo.reportTime}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>