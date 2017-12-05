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

    <title>SALES</title>

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
    <script type="application/javascript" language="JavaScript" src="Resource/js/leader/sales.js"></script>
    <link rel="shortcut icon" href="Resource/img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="Resource/css/common.css">
    <link rel="stylesheet" type="text/css" href="Resource/css/leader_sales.css">
    <link rel="stylesheet" type="text/css" href="Resource/css/leader_header.css">
    <link rel="stylesheet" type="text/css" href="Resource/css/leader_active.css">

</head>

<body>
    <div id="header">
    <div id="header_content">
        <h1 id="header_logo">BOBLIVE</h1>
        <a class="header_navigation" href="/boblive/leader/dealer">DEALER</a>
        <a class="header_navigation" href="/boblive/leader/sales">
            <span style="color: white">SALES</span>
        </a>
        <a class="header_navigation" href="/boblive/leader/active">ACTIVE</a>
        <a class="header_navigation" href="/boblive/leader/negative">CODE</a>
        <input type="text" id="txSearch" placeholder="Search">
        <a id="header_exit" href="/boblive/">EXIT</a>
    </div>
    </div>
    <br>
    <div id="content">
        <div>
            <a id="add_sales" style="display: block; float: right">
                <img src="Resource/img/add1.png" alt="add">
            </a>
        </div><br>
        <div id="dTable">
            <table id="content_table" border="1px" cellpadding="0px" cellspacing="0px">
                <thead>
                    <tr id="content_table_th">
                        <th id="thId" width="6%">ID</th><th width="17%">USERNAME</th><th width="17%">PASSWORD</th>
                        <th width="17%">DEALER</th><th width="22%">REGISTER_TIME</th><th width="22%">UPDATE_TIME</th>
                        <th width="10%">ACT</th><th width="10%">DEACT</th><th width="12%">UPDATE</th>
                        <th width="10%">TEAM</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${authSalesInfoList}" var="authSalesInfo">
                   <tr class="tr1">
                       <td align="center">${authSalesInfo.id}</td>
                       <td align="center"><span style="color: darkred">${authSalesInfo.userName}</span></td>
                       <td align="center">
                           <input class="password_input" value="${authSalesInfo.password}">
                       </td>
                       <td align="center">${authSalesInfo.dealer}</td>
                       <td align="center">${authSalesInfo.registerTime}</td>
                       <td align="center">${authSalesInfo.updateTime}</td>
                       <td align="center">
                           <img width="16px" src="Resource/img/activate.png" alt="activate">
                       </td>
                       <td align="center">
                           <img width="16px" src="Resource/img/deactivate.png" alt="deactivate">
                       </td>
                       <td align="center">
                           <img width="16px" src="Resource/img/update.png" alt="deactivate">
                       </td>
                       <td align="center">
                           <a href="/boblive/leader/active?sales=${authSalesInfo.userName}" target="_blank">
                               <img width="16px" src="Resource/img/team.png" alt="deactivate">
                           </a>
                       </td>
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
        <div id="createFragment">
            <div id="dCreate">
                <a href="javascript:;" id="closeCreate">×</a><br>
                <p>CREATE SALES</p><hr><br>
                <div>
                    <input type="text" id="iUserName" placeholder="user name"><br><br>
                    <input type="password" id="iPassword" placeholder="password"><br><br>
                    <select id="sDealer">
                        <option value="0">-- choose dealer --</option>
                        <c:forEach items="${authDealerInfoList}" var="authDealerInfo">
                            <option value="${authDealerInfo.userName}">${authDealerInfo.userName}</option>
                        </c:forEach>
                    </select><br><br>
                    <input type="button" id="btConfirm" value="Create" disabled="disabled"><br>
                    <p id="createNotice">sdfsd</p>
                </div>
            </div>
        </div>
    </div>

    <div id="footer">

    </div>

</body>
</html>
