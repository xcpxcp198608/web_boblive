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

    <title>ACTIVE</title>

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
    <script type="application/javascript" language="JavaScript" src="Resource/js/dealer/active.js"></script>
    <link rel="shortcut icon" href="Resource/img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="Resource/css/common.css">
    <link rel="stylesheet" type="text/css" href="Resource/css/leader_header.css">
    <link rel="stylesheet" type="text/css" href="Resource/css/leader_active.css">

</head>

<body>
    <div id="header">
        <div id="header_content">
            <h1 id="header_logo">BOBLIVE</h1>
            <a class="header_navigation" href="/boblive/dealer/sales">SALES</a>
            <a class="header_navigation" href="/boblive/dealer/active">
                <span style="color: white">ACTIVE</span>
            </a>
            <a class="header_navigation" href="/boblive/dealer/negative">CODE</a>
            <input type="text" id="txSearch" placeholder="Search">
            <a id="header_exit" href="/boblive/">EXIT</a>
        </div>
    </div>
    <div id="content">
        <br>
        <div id="dFilter">
            <form action="/boblive/dealer/active/" method="post">
                select sales:<select id="salesSelect" name="sales" class="select1">
                    <option value="">-- sales --</option>
                    <c:forEach items="${authSalesInfoList}" var="authSalesInfo">
                        <option value="${authSalesInfo.userName}">${authSalesInfo.userName}</option>
                    </c:forEach>
                </select>
                select level:<select name="level" class="select1">
                    <option value="0">-- level --</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                </select>
                select active date:<input type="date" name="activeDate" class="select1">
                &nbsp;&nbsp;
                select expire date:<input type="date" name="memberDate" class="select1">
                &nbsp;&nbsp;
                <input type="submit" value="confirm" class="button1">
            </form>
        </div>
        <div id="dTable">
            <table id="content_table" border="1px" cellpadding="0px" cellspacing="0px" id="tab1">
                <thead>
                    <tr id="content_table_th">
                        <th>ID</th><th>KEY</th><th>MAC</th><th>SALES</th><th>DEALER</th>
                        <th>
                            <select id="sLevel" class="select">
                                <option value="0">LEVEL</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                            </select>
                        </th>
                        <th>ACTIVE TIME</th><th>EXPIRE DATE</th><th>UPDATE</th>
                        <th>
                            <select id="sOnline" class="select">
                                <option value="0">ONLINE</option>
                                <option value="online">ON</option>
                                <option value="offline">OFF</option>
                            </select>
                        </th>
                        <th>
                            <select id="sStatus" class="select">
                                <option value="0">STATUS</option>
                                <option value="active">ACT</option>
                                <option value="negative">DEACT</option>
                            </select>
                        </th>
                        <th>ACT</th><th>DEACT</th>
                    </tr>
                </thead>
                <c:forEach items="${authorizationInfoList}" var="authorizationInfo" varStatus="status">
                <tr style="height: 24px; font-size: 12px; color: lightslategrey">
                    <td align="center">${authorizationInfo.id}</td>
                    <td align="center">${authorizationInfo.key}</td>
                    <td align="center">${authorizationInfo.mac}</td>
                    <td align="center">${authorizationInfo.sales}</td>
                    <td align="center">${authorizationInfo.dealer}</td>
                    <td align="center">${authorizationInfo.level}</td>
                    <td align="center">${authorizationInfo.activeDate}</td>
                    <td align="center">${authorizationInfo.memberDate}</td>
                    <td align="center">
                        <input type="hidden" name="id" value="${authorizationInfo.id}">
                        l:<select name="level">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                        </select>
                        d:<select name="days">
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="7">7</option>
                            <option value="15">15</option>
                            <option value="30">30</option>
                            <option value="60">60</option>
                            <option value="90">90</option>
                            <option value="365">365</option>
                        </select>
                        <input type="button" value="update" >
                    </td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${authorizationInfo.online == true}">
                                <img src="Resource/img/green.png" alt="online" >
                            </c:when>
                            <c:when test="${authorizationInfo.online == false}">
                                <img src="Resource/img/gray.png" alt="offline">
                            </c:when>
                        </c:choose>
                    </td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${authorizationInfo.effective == true}">
                                <img src="Resource/img/blue.png" alt="active">
                            </c:when>
                            <c:when test="${authorizationInfo.effective == false}">
                                <img src="Resource/img/red.png" alt="negative">
                            </c:when>
                        </c:choose>
                    </td>
                    <td align="center">
                        <img width="16px" src="Resource/img/activate.png" alt="activate">
                    </td>
                    <td align="center">
                        <img width="16px" src="Resource/img/deactivate.png" alt="deactivate">
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            Total: <span id="totalCount">1</span>
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
