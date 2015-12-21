<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>设置支付密码</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <div class="fix-password">
	    <div class="fix-desc">设置您的支付密码</div>
	    <div class="input-wrap-wrap">
	        <div class="input-wrap">
	            <input type="password" id="pwd" placeholder="新密码"/>
	        </div>
	        <div class="input-wrap">
	            <input type="password" id="repeatPwd" placeholder="重复密码"/>
	        </div>
	    </div>
	    <div class="btn-group mt2">
	        <div class="normal-btn btn fr" onclick="updatePwd();">确定</div>
	        <div class="btn-cancel btn mr2 fr" onclick="history.go(-1)">取消</div>
	    </div>
	</div>
</div>  

<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/md5.js"></script>
<script>
    
    //修改个人信息
    function updatePwd(){
    	var pwd = $("#pwd").val();
        var repeatPwd = $("#repeatPwd").val();
        if (isEmpty(pwd)) {
        	dialog("请输入您要设置的密码");
        	return;
        }
        if(pwd != repeatPwd){
            dialog("两次密码输入不一致");
            return;
        }
        pwd = CryptoJS.MD5(CryptoJS.MD5(pwd).toString().toUpperCase()).toString().toUpperCase();
        
    	$.ajax({
    		url : baseUrl + "memberCenter/action/setPwd",
    		type : "POST",
    		data : "pwd=" + pwd,
    		success : function(e){
    			if (e.code != 0) {
    				dialog(e.msg);
    	            return;
    			}
    			dialog("设置成功");
    			window.location.href = baseUrl + "memberCenter/view/home/${session_key_store_id}/1";
    		}
    	});
    }
    
</script>
</body>
</html>