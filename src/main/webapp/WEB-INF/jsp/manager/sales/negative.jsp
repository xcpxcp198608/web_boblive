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

    <title>NEGATIVE</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link rel="shortcut icon" href="Resource/img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="Resource/css/common.css">
    <link rel="stylesheet" type="text/css" href="Resource/css/leader_header.css">
    <link rel="stylesheet" type="text/css" href="Resource/css/leader_active.css">
    <script type="application/javascript" language="JavaScript" src="Resource/js/jquery-3.2.1.js"></script>
    <script type="application/javascript" language="JavaScript" src="Resource/js/common.js"></script>
    <script type="application/javascript" language="JavaScript" src="Resource/js/sales/negative.js"></script>

</head>

<body>
    <div id="header">
        <div id="header_content">
            <h1 id="header_logo">BOBLIVE</h1>
            <a class="header_navigation" href="/boblive/sales/active">ACTIVE</a>
            <a class="header_navigation" href="/boblive/sales/negative">
                <span style="color: white">CODE</span>
            </a>
            <input type="text" id="txSearch" placeholder="Search">
            <a id="header_exit" href="/boblive/">EXIT</a>
        </div>
    </div>
    <br>
    <div id="content">
        <div id="dTable">
            <table id="content_table" border="1px" cellpadding="0px" cellspacing="0px">
                <thead>
                    <tr id="content_table_th">
                        <th>ID</th><th>KEY</th><th>SALES</th><th>DEALER</th><th>LEVEL</th>
                        <th>CREATE TIME</th><th>EXPIRE DATE</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${authorizationInfoList}" var="authorizationInfo">
                        <tr style="height: 24px; font-size: 12px; color: lightslategrey">
                            <td align="center">${authorizationInfo.id}</td>
                            <td align="center">${authorizationInfo.key}</td>
                            <td align="center">${authorizationInfo.sales}</td>
                            <td align="center">${authorizationInfo.dealer}</td>
                            <td align="center">${authorizationInfo.level}</td>
                            <td align="center">${authorizationInfo.activeDate}</td>
                            <td align="center">${authorizationInfo.memberDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
        </table>
        </div>
        <div id="noticeFragment">
            <div id="notice">
                <p id="notice_content">Notice</p>
            </div>
        </div>
    </div>

    <div id="footer">

</div>

</body>
</html>
