<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>智慧美业，绽放未来</title>
<link rel="stylesheet" href="<%=basePath %>css/style.default.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>css/zhifang.css" type="text/css" />
</head>
<body class="loginbody">
<div class="loginwrapper">
    <div class="loginwrap zindex100 animate2 bounceInDown">
        <h1 class="logintitle">
            <img src="<%=basePath %>images/login-logo.png" alt=""/>
        </h1>
        <div class="loginwrapperinner">
            <p class="animate4 bounceIn"><input type="text" id="username" name="username" placeholder="用户名" /></p>
            <p class="animate5 bounceIn"><input type="password" id="password" name="password" placeholder="密码" /></p>
            <p class="animate6 bounceIn login-p">
                <button class="btn btn-default btn-block login-btn loginButton">登&nbsp;&nbsp;录</button>
            </p>
            <p class="animate7 fadeIn"><a href="javascript:void(0);" class="fr"><span class="iconfont icon-bangzhuzhongxin"></span> 忘记密码</a></p>
        </div>
    </div>
    <div class="loginshadow animate3 fadeInUp"></div>
</div>
<script type="text/javascript" src="<%=basePath %>js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/jquery-migrate-1.1.1.min.js"></script>
<script type="text/javascript">
jQuery.noConflict();

jQuery(document).ready(function(){
  
  jQuery.noConflict();

  jQuery(document).ready(function(){
      jQuery("#username").focus();
      
      var anievent = (jQuery.browser.webkit)? 'webkitAnimationEnd' : 'animationend';
      jQuery('.loginwrap').bind(anievent,function(){
          jQuery(this).removeClass('animate2 bounceInDown');
      });
      
      jQuery('#username,#password').focus(function(){
          if(jQuery(this).hasClass('error')) jQuery(this).removeClass('error');
      });
      
      jQuery(document).keyup(function(e){
          if(e.keyCode == 13){
            jQuery('.loginButton').click();
          }
      });
      
      jQuery('.loginButton').click(function(){
        if(jQuery('#username').val() == '' || jQuery('#password').val() == '') {
                if(jQuery('#username').val() == '') {
                    jQuery('#username').focus();
                    jQuery('#username').addClass('error');
                } else {
                    jQuery('#username').removeClass('error');
                }
                if(jQuery('#password').val() == '') {
                    jQuery('#password').focus();
                    jQuery('#password').addClass('error'); 
                }else {
                    jQuery('#password').removeClass('error');
                }
                jQuery('.loginwrap').addClass('animate0 wobble').bind(anievent,function(){
                    jQuery(this).removeClass('animate0 wobble');
                });
            }
        
        var username = jQuery("#username").val();
            var password = jQuery("#password").val();
            password = CryptoJS.MD5(CryptoJS.MD5(password).toString().toUpperCase()).toString().toUpperCase();
            jQuery.ajax({
                type : "post",
                url : baseUrl + "user/login",
                data : "username=" + username + "&password=" + password,
                dataType : "json",
                success : function(e){
                    if (e.code != 0) {
                      if (e.code == 9001) {
                        jQuery('#username').focus();
                        jQuery('#username').addClass('error');
                      } else {
                        jQuery('#password').focus();
                        jQuery('#password').addClass('error');
                      }
                        jQuery('.loginwrap').addClass('animate0 wobble').bind(anievent,function(){
                            jQuery(this).removeClass('animate0 wobble');
                        });
                        return;
                    }else{
                    	window.location.href = baseUrl + e.msg;
                    }
                }
            });
      });
  });
});
</script>
</body>
</html>