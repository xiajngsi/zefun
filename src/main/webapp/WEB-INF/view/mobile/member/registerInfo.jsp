<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>完善资料</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/star-rating.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <div class="perfect-per-info">
	    <div class="register-info">
	        <div class="registre-ul-wrap">
	            <ul class="perfect-ul">
	                <li class="person-img">
	                    <div class="img-wrap">
	                        <c:choose>
				               <c:when test="${empty headimgurl }">
				                   <img id="headImg" src="<%=picPath%>userhead/default.png?imageView2/1/w/200/h/200" alt="" class="per-img" onclick="chooseImgage()"/>
				               </c:when>
				               <c:otherwise>
				                   <img id="headImg" src="${headimgurl }" alt="" class="per-img" onclick="chooseImgage()"/>
				               </c:otherwise>
				           </c:choose>
	                    </div>
	                </li>
	                <li class="normal-li ">
	                    <span class="span-label"><i class="iconfont icon-person"></i>昵称</span>
	                    <input type="text" id="nickname" value="${nickname }" placeholder="请输入昵称" class="w300 fr"/>
	                </li>
	                <li class="normal-li ">
	                    <span class="span-label"><i class="iconfont icon-sex1 "></i>性别</span>
	                    <c:choose>
	                       <c:when test="${sex == 2 }">
	                            <div class="part2">
		                            <span class="word">女</span>
		                            <div class="checker">
		                                <div class="beau-radio active">
		                                    <span class="iconfont icon-gou"></span>
		                                </div>
		                                <input type="radio" checked="checked" value="女" class="yellow-radio" name="sex"/>
		                            </div>
		                        </div>
		                        <div class="part1">
		                            <span class="word">男</span>
		                            <div class="checker">
		                                <div class="beau-radio">
		                                    <span class="iconfont icon-gou"></span>
		                                </div>
		                                <input type="radio" class="yellow-radio" value="男" name="sex"/>
		                            </div>
		                        </div>
	                       </c:when>
	                       <c:otherwise>
	                            <div class="part2">
                                    <span class="word">女</span>
                                    <div class="checker">
                                        <div class="beau-radio">
                                            <span class="iconfont icon-gou"></span>
                                        </div>
                                        <input type="radio" checked="checked" class="yellow-radio" value="女" name="sex"/>
                                    </div>
                                </div>
                                <div class="part1">
                                    <span class="word">男</span>
                                    <div class="checker">
                                        <div class="beau-radio active">
                                            <span class="iconfont icon-gou"></span>
                                        </div>
                                        <input type="radio" checked="checked" class="yellow-radio" value="男" name="sex"/>
                                    </div>
                                </div>
	                       </c:otherwise>
	                    </c:choose>
	                    
	                </li>
	                <li class="normal-li ">
	                    <span class="span-label">支付密码</span>
	                    <input type="password" id="password" placeholder="请输入密码" class="w300 fr"/>
	                </li>
	            </ul>
	        </div>
	    </div>
	    
	    <a href="javascript:register();" class="confirm-btn mt2">进入主页</a>
	</div>
</div>  
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
<script type="text/javascript">
var appId = "<%=request.getAttribute("appId")%>";
var timestamp = "<%=request.getAttribute("timestamp")%>";
var nonceStr = "<%=request.getAttribute("nonceStr")%>";
var signature = "<%=request.getAttribute("signature")%>";
</script>
<script src="<%=basePath%>js/mobile/member/wxapi.js"></script>
<script src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
<script src="<%=basePath%>js/mobile/md5.js"></script>
<script src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script>
    $(function(){
        $(".hide-radio").on("click", function(){
            var radioElement =  $(".hide-radio");
            if(radioElement.attr("checked")){
                radioElement.parents(".radio-wrap").find(".radio-label").removeClass("hide");
            }else{
                radioElement.parents(".radio-wrap").find(".radio-label").addClass("hide");
            }
        });
        /* $("#password").on("keydown", function(event){
            if (!((event.keyCode >= 48 && event.keyCode <= 57) 
                    || (event.keyCode >= 96 && event.keyCode <= 105) 
                    || event.keyCode == 8) || (event.keyCode != 8 && $(this).val().length >= 6)) {
                dialog("密码规则为六位数字");
                return false;
            }
        }); */
    });
    
    var headUrl = "${headimgurl }";
    if(headUrl == null || headUrl == ''){
        headUrl = "userhead/default.png";
    }
    
    function register(){
        var nickName = $("#nickname").val();
        if(isEmpty(nickName)){
        	dialog("昵称还没填呢~");
            return;
        }
        if (getByteLen(nickName) > 15) {
            dialog("昵称太长啦，缩短一点试试吧～");
            return;
        }
        var password = $("#password").val();
        if(isNaN(password) || password.length != 6){
        	dialog("支付密码只能为6为数字");
            return;
        }
        password = CryptoJS.MD5(CryptoJS.MD5(password).toString().toUpperCase()).toString().toUpperCase();
        var sex = $("[name='sex']:checked").val();
        $.ajax({
            type : "post",
            url : baseUrl + "memberCenter/action/registerInfo",
            data : "nickname=" + nickName + "&paycode=" + password + "&headUrl=" + headUrl + "&sex=" + sex,
            dataType : "json",
            success : function(e){
                if (e.code != 0) {
                    dialog(e.msg);
                    return;
                }
                window.location.href = baseUrl + "memberCenter/view/home/${session_key_store_id}/1";
            }
        });
    }
    
    function getByteLen(val) {
        var len = 0;
        for (var i = 0; i < val.length; i++) {
           var length = val.charCodeAt(i);
           if(length>=0&&length<=128)
            {
                len += 1;
            }
            else
            {
                len += 2;
            }
        }
        return len;
    }
    
    var userId = "${session_key_user_id}";
    function chooseImgage(){
        wx.chooseImage({
            count: 1,
            sizeType: ['original'],
            sourceType: ['album', 'camera'], 
            success: function (res) {
                var localIds = res.localIds; 
                wx.uploadImage({
                    localId: localIds[0], 
                    isShowProgressTips: 1,
                    success: function (res) {
                        var serverId = res.serverId;
                        var key = "userhead/" + userId + "/" + new Date().getTime();
                        $.ajax({
                            type : "post",
                            url : baseUrl + "wechat/fetch/media",
                            data : "mediaid=" + serverId + "&key=" + key,
                            dataType : "json",
                            success : function(e){
                                if (e.code != 0) {
                                    dialog(e.msg);
                                    return;
                                }
                                headUrl = key;
                                $("#headImg").attr("src", picUrl + key + "?imageView2/1/w/200/h/200");
                            }
                        });
                    }
                });
            }
        });
    }
</script>
</body>
</html>