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

    <title>CREATE_AUTH</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <script type="application/javascript" language="JavaScript" src="Resource/js/jquery-3.2.1.js"></script>
    <script type="application/javascript" language="JavaScript" src="Resource/js/common.js"></script>
    <script type="application/javascript" language="JavaScript" src="Resource/js/leader/auth_create.js"></script>
    <link rel="shortcut icon" href="Resource/img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="Resource/css/common.css">
    <link rel="stylesheet" type="text/css" href="Resource/css/leader_header.css">
    <link rel="stylesheet" type="text/css" href="Resource/css/leader_active.css">

</head>

<body>
    <div id="header">
        <div id="header_content">
            <h1 id="header_logo">BOBLIVE</h1>
            <a class="header_navigation" href="/boblive/leader/dealer">DEALER</a>
            <a class="header_navigation" href="/boblive/leader/sales">SALES</a>
            <a class="header_navigation" href="/boblive/leader/active">ACTIVE</a>
            <a class="header_navigation" href="/boblive/leader/negative">CODE</a>
            <a id="header_exit" href="/boblive/">EXIT</a>
        </div>
    </div>
    <div style="width: 100%; height: 30px"></div>
    <div id="content">
        <form action="/boblive/leader/create_auth" method="post" style="font-size: 12px; font-style: italic">
            type in count(1 ~ 100):
            <input type="number" min="1" max="100" name="count" value="1">  &nbsp;&nbsp;
            select dealer:
            <select name="dealer"  onchange="dealerSelected(this)" style="width: 100px">
                <option>--choose--</option>
                <c:forEach items="${authDealerInfoList}" var="authDealerInfo">
                    <option value="${authDealerInfo.userName}">${authDealerInfo.userName}</option>
                </c:forEach>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;
            select sales:
            <select name="sales" disabled="disabled" id="salesSelect" style="width: 100px"></select>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" value="create" id="btCreate" disabled="disabled">
        </form>
        <hr/>
        <table border="1px" cellpadding="0px" cellspacing="0px">
        <c:forEach items="${keyList}" var="key">
            <tr>
                <td>${key}</td>
                <td>${sales}</td>
                <td>${dealer}</td>
            </tr>
        </c:forEach>
        </table>
    </div>

    <div id="footer">

    </div>

</body>
</html>
