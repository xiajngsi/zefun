<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>修改登录密码</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
	<div class="content mb5" style="background-color: #fff">
		<div class="fix-password">
		    <div class="fix-desc">修改密码：</div>
		    <div class="input-wrap-wrap">
		        <div class="input-wrap">
		            <input type="password" id="oldPwd" placeholder="当前密码"/></span>
		        </div>
		        <div class="input-wrap">
		            <input type="password" id="newPwd" placeholder="新密码"/>
		        </div>
		        <div class="input-wrap">
		            <input type="password" id="repeatPwd" placeholder="重复密码"/>
		        </div>
		    </div>
		    <div class="btn-group mt2">
		        <div class="normal-btn btn fr" onclick="updatePassword()">确定</div>
		        <div class="btn-cancel btn mr2 fr" onclick="history.go(-1);">取消</div>
		    </div>
		</div>
	</div>
</div>
<script src="<%=basePath%>js/mobile/md5.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript">
//修改支付密码
function updatePassword(){
    var oldPwd = $("#oldPwd").val();
    var newPwd = $("#newPwd").val();
    var repeatPwd = $("#repeatPwd").val();
    
    if (isEmpty(oldPwd)) {
    	dialog("请输入旧密码");
        return;
    }
    
    if (isEmpty(newPwd)) {
        dialog("请输入新密码");
        return;
    }
    
    if(newPwd != repeatPwd){
        dialog("两次密码输入不一致");
        return;
    }
    
    oldPwd = CryptoJS.MD5(CryptoJS.MD5(oldPwd).toString().toUpperCase()).toString().toUpperCase();
    newPwd = CryptoJS.MD5(CryptoJS.MD5(newPwd).toString().toUpperCase()).toString().toUpperCase();
    $.ajax({
        url : baseUrl + "staff/action/updatePwd",
        type : "POST",
        data : "oldPwd=" + oldPwd + "&newPwd=" + newPwd,
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            dialog("修改成功");
            history.go(-1);
        }
    });
}
</script>
</body>
</html>