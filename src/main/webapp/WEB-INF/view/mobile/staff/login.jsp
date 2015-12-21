<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>内部登录</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content">
	<div class="register">
	    <div class="input-wrap mt2">
	        <input type="tel" id="phone" placeholder="账号"/>
	    </div>
	    <div class="input-wrap mt">
	        <input type="password" id="password" placeholder="密码"/>
	    </div>
	    <div class="btn-group" onclick="javascript:login();">
	        <div class="normal-btn btn w100p">登录</div>
	    </div>
	</div>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script src="<%=basePath%>js/mobile/base.js"></script>
<script src="<%=basePath%>js/mobile/md5.js"></script>
<script>
	function login(){
		var phone = $("#phone").val();
		var password = $("#password").val();
		password = CryptoJS.MD5(CryptoJS.MD5(password).toString().toUpperCase()).toString().toUpperCase();
		$.ajax({
            type : "post",
            url : baseUrl + "staff/action/login",
            data : "phone=" + phone + "&password=" + password,
            dataType : "json",
            success : function(e){
                if (e.code != 0) {
                	dialog(e.msg);
                    return;
                }
                window.location.href = baseUrl + "staff/view/home/${session_key_store_id}/2";
            }
        });
	}
</script>
</body>
</html>