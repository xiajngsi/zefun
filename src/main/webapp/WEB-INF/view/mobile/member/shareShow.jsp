<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>分享发型</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
	<div class="click-share bg-white">
	  <img id="headImg" src="<%=picPath %>mobile/share/hairstyle_show.png?imageView2/1/w/640/h/640" class="click-share-img"/>
	  <div class="share-btn-group">
	    <div class="btn-content">
	      <div class="paizhao" onclick="chooseImgage()">
	        <div class="paizhao-wrap" >
	          <span class="iconfont icon-xiangji4"></span>
	        </div>
	        <div class="word">拍照</div>
	      </div>
	      <div class="fenxiang s-modal-control" data-target="#share-tip">
	        <div class="fenxiang-wrap">
	          <span class="iconfont icon-caidan"></span>
	        </div>
	        <div class="word" onclick="share();">分享给朋友</div>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 分享提示 -->
	<div class="s-modal hide s-modal-miss no-padding" id="share-tip">
	  <div class="s-modal-wrap">
	    <div class="share-tip">
	      <img src="<%=basePath %>images/mobile/share-tip.png" alt=""/>
	      <div class="know">我知道了</div>
	    </div>
	  </div>
	</div>
	
	<!-- 分享成功 -->
	<div class="s-modal hide s-modal-miss" id="shareSuccessTip">
        <div class="s-modal-wrap">
            <div class="d-member-info">
                <div class="n-modal-title text-center">
                    <span>分享成功</span>
                    <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
                    <div class="clearfix"></div>
                </div>
                <div class="s-modal-body">
                    <div class="word text-left">
                        <span id="integralNum"></span>积分已赠送到您账户，期待您的再次到来
                    </div>
                </div>
                <div class="s-modal-foot">
                    <div class="modal-confirm" onclick="backHome();">返回主页</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
<script type="text/javascript">
var appId = "<%=request.getAttribute("appId")%>";
var timestamp = "<%=request.getAttribute("timestamp")%>";
var nonceStr = "<%=request.getAttribute("nonceStr")%>";
var signature = "<%=request.getAttribute("signature")%>";
</script>
<script src="<%=basePath%>js/mobile/member/wxapi.js"></script>
<script src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
<script src="<%=basePath%>js/qiniu/hmac-sha1.js"></script>
<script src="<%=basePath%>js/mobile/base.js"></script>
<script>
function share(){
	$("#share-tip").removeClass("hide");
}

function backHome(){
	window.location.href = baseUrl + "memberCenter/view/home/${session_key_store_id}/1";
}

var title = '我刚在这家店弄的发型，在线预约不排队，还有折扣哦～';
var content = "无内容";
var desc = '';
var link = baseUrl + 'mobile/test?view=mobile/member/shareInfo';
var imgUrl = 'http://7xkv8r.com1.z0.glb.clouddn.com/faxing.jpg?imageView2/1/w/200/h/200';

var hairImg = "";
var orderId = "${orderId}";

wx.ready(function () {
	registerShare();
});

//注册分享事件
function registerShare(){
	wx.onMenuShareAppMessage({
	  title: title,
	  desc: desc,
	  link: link,
	  imgUrl: imgUrl,
	  success: shareCallback,
	  cancel: shareCancel
	});

	wx.onMenuShareTimeline({
	  title: title,
	  link: link,
	  imgUrl: imgUrl,
	  success: shareCallback,
      cancel: shareCancel
	});
}

function shareCallback(res){
	if (isEmpty(hairImg)) {
		dialog("您还没有拍发型呢");
		return;
	}
	/* $.ajax({
        url : baseUrl + "memberCenter/action/share",
        type : "post",
        data : "orderId=" + orderId + "&imgUrl=" + hairImg + "&content=" + content,
        dataType : "json",
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            $("#integralNum").html(e.msg);
            $("#shareSuccessTip").removeClass("hide");
        },
        error : function(e){
        	dialog("err : " + e);
        },
        beforeSend : function(){
            dialog("提交");
        }
    }); */
}

function shareCancel(res){
    dialog("您取消了分享");
}

function chooseImgage(){
    wx.chooseImage({
        count: 1,
        sizeType: ['original'],
        sourceType: ['camera'], 
        success: function (res) {
            var localIds = res.localIds; 
            wx.uploadImage({
                localId: localIds[0], 
                isShowProgressTips: 1,
                success: function (res) {
                    var serverId = res.serverId;
                    var key = "userhead/share/" + new Date().getTime();
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
                            imgUrl = picUrl + key + "?imageView2/1/w/200/h/200";
                            hairImg = key;
                            registerShare();
                            
                            var imgSrc = picUrl + key + "?imageView2/1/w/640/h/640";
                            $("#headImg").attr("src", imgSrc);
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