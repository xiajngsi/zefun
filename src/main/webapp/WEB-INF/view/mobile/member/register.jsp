<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>注册</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
  <body>
<div class="content">
    <div class="register">
	    <div class="mt4 mb2 font-666">&nbsp;&nbsp;&nbsp;验证手机号，轻松管理会员卡~</div>
	    <input type="tel" class="tel-num clearInput" id="phone" placeholder="请输入新手机号码"/><span class="iconfont icon-guanbi ml-4 hide"></span>
	    <div class="yanzheng">
	        <input type="tel" class="yanzheng-input" id="verifyCode" placeholder="输入验证码"/>
	        <div class="btn code" id="getDiv" onclick="getVerifyCode()">获取验证码</div>
	        <div class="register-time btn hide" id="timeDiv">
	            剩&nbsp;余&nbsp;&nbsp;<span class="time font-size-28 font-weight" id="time">120"</span>
	        </div>
	    </div>
	    <div class="clearfix"></div>
	    <div class="zhuce-btn">
	        <a href="javascript:register();">
	            <div class="normal-btn font-size-28">注  册</div>
	        </a>
	    </div>
	</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript">
  var storeId = "${storeId}";
  var number = 120;
  var timer = null;
  var time = $("#time");
  function clock(){
	  number--;
	  if(number <= 0){
		  $("#timeDiv").addClass("hide");
	      $("#getDiv").removeClass("hide");
		  number = 120;
		  window.clearInterval(timer);
	  }else{
		  time.html(number + '"');
	  }
  }

  function getVerifyCode(){
	  var phone = $("#phone").val();
	  var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
      if (!reg.test(phone)) {
    	  dialog(" 请输入正确的手机号码 ");
          return;
      }
	  
	  $("#getDiv").addClass("hide");
	  $("#timeDiv").removeClass("hide");
	  timer = setInterval("clock()",1000);
	  $.ajax({
          type : "POST",
          url : baseUrl + "memberCenter/action/getVerifyCode",
          data : "storeId=" + storeId + "&phone=" + $("#phone").val(),
          dataType : "json",
          success : function(e){
              if (e.code != 0) {
                  dialog(e.msg);
                  return;
              }
          }
      });
  }
  function register(){
	  var phone = $("#phone").val();
	  var verifyCode = $("#verifyCode").val();
	  $.ajax({
		  type : "POST",
		  url : baseUrl + "memberCenter/action/register",
		  data : "storeId=" + storeId + "&phone=" + phone + "&verifyCode=" + verifyCode,
		  dataType : "json",
		  success : function(e){
			  if (e.code != 0) {
				  dialog(e.msg);
				  return;
			  }
			  window.location.href = e.msg;
		  }
	  });
  }
</script>
</body>
</html>