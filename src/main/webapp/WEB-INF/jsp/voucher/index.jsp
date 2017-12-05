<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>Login</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.2.1.js"
            integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
            crossorigin="anonymous"></script>
    <style type="text/css">
        #div_sign_in {margin: 200px auto auto;  width: 400px; height: 300px; background-color: #82070d;
            box-shadow: 0 0 8px #aaa;}
        #div_sign_in_1 {width: 300px; height: 200px; margin: 25px auto auto}
        h3 {width: 100%; text-align: center; align-content: center}
        #error_message_sign_in {color: #ffe626; font-size: 16px}
        #btSignIn {width: 100%; background-color: #07ae4a
        }

    </style>
    <script type="application/javascript" src="Resource/js/voucher/index.js"></script>
</head>

<body style="background-color: #000">

<div id="div_sign_in">
    <div id="div_sign_in_1">
        <br/>
        <h3 style="color: whitesmoke">BOBLIVE VOUCHER</h3>
        <br/>
        <div>
            <div class="input-group">
        <span class="input-group-addon" id="basic-addon1">
          <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
        </span>
                <input type="text" id="sign_in_username" class="form-control" placeholder="Username" aria-describedby="basic-addon1" name="username">
            </div>
            <br/>
            <div class="input-group">
        <span class="input-group-addon" id="basic-addon2">
          <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
        </span>
                <input type="password" id="sign_in_password" class="form-control" placeholder="Password" aria-describedby="basic-addon2" name="password">
            </div>
            <br/>
            <button id="btSignIn" type="submit" class="btn btn-primary">SignIn</button>
        </div>
        <br/>
        <div style="clear: both"><span id="error_message_sign_in"></span></div>
    </div>
</div>


<div id="loading" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%;
  background-color: rgba(0,0,0,0.3); z-index: 1000; display: none">
    <div style="width: 25%; margin: 350px auto">
        <div class="progress">
            <div class="progress-bar progress-bar-striped active" role="progressbar"
                 aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
            </div>
        </div>
    </div>
</div>

<div id="notice" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%;
  background-color: rgba(0,0,0,0.3); z-index: 1001; display: none">
    <div style="width:50%; margin: 100px auto">
        <h4 id="notice_message" style="color: #82070d; font-size: 20px; width: 100%; text-align: center;"></h4>
    </div>
</div>
</body>
</html>

