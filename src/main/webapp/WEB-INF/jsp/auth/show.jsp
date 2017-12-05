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

    <title>KEY</title>

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
    <style type="text/css">

    </style>
</head>

<body style="font-family: Arial; padding: 0px; margin: 0px">

    <div id="header" style="background-color: black; width: 100%; height: 60px" >
        <span style="width: 100%; font-size: 25px; font-family: Arial;
        text-align: center; line-height: 60px; color: lightyellow; display: block">BOBLIVE</span>
    </div><br/>
    <div style="width: 90%; margin: auto">
        <a href="/boblive/auth/go">Create</a> <br>
    </div>
    <div style="width: 90%; margin: auto">
        <table width="100%" bgcolor="white" cellpadding="0px" cellspacing="0px" border="1px">
        <thead>
            <tr style="height: 40px;background-color: black; color: gainsboro"><th>Id</th><th>Key</th><th>Mac</th><th>Group</th><th>Active</th><th>ActiveDate</th>
                <th>Level</th><th>MemberDate</th><th>Operation</th></tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="authInfo">
            <tr>
            <td align="center">${authInfo.id}</td>
            <td align="center">${authInfo.key}</td>
            <td align="center">${authInfo.mac}</td>
            <td align="center">${authInfo.group}</td>
            <td align="center">
                <c:choose>
                    <c:when test="${authInfo.active == 1}">
                        ACTIVE
                    </c:when>
                    <c:otherwise>
                        <span style="color: red">NEGATIVE</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td align="center">${authInfo.activeDate}</td>
            <td align="center">${authInfo.level}</td>
            <td align="center">${authInfo.memberDate}</td>
            <td align="center">
                <form action="/boblive/auth/update" method="post">
                    <select name="level">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                    <select name="month">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="3">3</option>
                        <option value="6">6</option>
                        <option value="12">12</option>
                    </select>
                    <input type="hidden" name="key" value="${authInfo.key}">
                    <input type="submit" value="update">
                </form>
            </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
</body>
</html>
