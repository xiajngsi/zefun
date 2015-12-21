<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>在线充值</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/star-rating.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
    <script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/star-rating.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/jquery.touchSwipe.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/member-mobile.js"> </script>
  </head>
  <body>
  <div class="content">
	  <div style="width: 100%; text-align: center;">
	    <p>
	      <span class="medium-font">账户余额</span><br/>
	      <span>18127.08</span>
	    </p>
	    <p style="margin-top: 30px;">
	        <input type="button" class="mr5p s-modal-control" data-target="#charge" onclick="charge()" value="立即充值" >
	        <input type="button" onclick="detailList()" value="资金明细" >
	    </p>
	  </div>

	<!--在线充值-->
	<div class="s-modal hide" id="charge">
	    <div class="s-modal-wrap">
	        <div class="fix-name">
	            <div class="s-modal-title">
	                <span>在线充值</span>
	                <span class="fr s-modal-miss normoal-word">X</span>
	            </div>
	            <div class="s-modal-body">
	                <input type="tel" id="money"/>
	            </div>
	            <div class="s-modal-foot">
	                <a class="modal-cancel s-modal-miss" href="">取消</a>
	                <a class="modal-confirm" href="">确定</a>
	            </div>
	        </div>
	    </div>
	</div>
</div>
  <script type="text/javascript">
  function detailList(){
      window.location.href = "<%=basePath %>mobile/test?view=mobile/member/incomeList";
  }
  function charge(){
	  $.ajax({
		  type : "POST",
		  url : baseUrl + "wechat/pay/create",
		  data : "goodsName=测试充值&total_fee=0.01",
		  dataType : "json",
		  success : function(e){
			  if (e.code != 0) {
				  dialog(e.msg);
				  return;
			  }
			  callWeixin(e.msg);
		  }
	  });
  }
  function callWeixin(rj) {
	    WeixinJSBridge.invoke('getBrandWCPayRequest', {
	        "appId" : rj.appId,
	        "timeStamp" : rj.timeStamp,
	        "nonceStr" : rj.nonceStr,
	        "package" : rj.package,
	        "signType" : rj.signType,
	        "paySign" : rj.paySign
	    }, function(res) {
	        // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
	        // 因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面
	        if (res.err_msg == "get_brand_wcpay_request:ok") {
//	          WeixinJSBridge.invoke('closeWindow', {}, function(e) {});
	            window.location.href = baseUrl + "memberCenter/view/home/${session_key_store_id}/1";
	        } else {
	            isPay = false;
	        }
	    });
	}
  </script>
</body>
</html>