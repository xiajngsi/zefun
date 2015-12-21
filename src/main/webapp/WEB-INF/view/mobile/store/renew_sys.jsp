<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css"/>

    <script src="<%=basePath%>js/mobile/jquery.min.js"></script>
    <script src="<%=basePath%>js/mobile/base.js"></script>
    <title>系统充值</title>
</head>
<body class="gray-bg">
<div class="pay">
    <ul class="list ">
        <li class="pay-title bsw">
            选择付款方式
        </li>
        <c:forEach var="setting" items="${rechargeSettings }" varStatus="settingStatus">
        <li class="way <c:if test="${settingStatus.first }">mt20</c:if> <c:if test="${settingStatus.last }">bsw</c:if> ">
            <div class="checker">
                <div class="beau-radio">
                    <span class="iconfont icon-gou"></span>
                </div>
                <input type="radio" class="yellow-radio" name="payType" value="${setting.id }"/>
            </div>
            <div class="desc-wrap">
                <span class="desc">${setting.description }</span>
            </div>
        </li>

        </c:forEach>
        <li class="way bsw btn-way">
            <div class="pay-btn" onclick="pay(this);">
                付款
            </div>
        </li>
    </ul>
</div>

<script>
    /*漂亮的单选和多选*/
    $(".checker").on("click", function () {
        var radio = $(".yellow-radio");
        for(var i=0;i<radio.length;i++)
        {
            if(radio[i].checked)
            {
                $(radio[i]).siblings(".beau-radio").addClass("active");
            }else{
                $(radio[i]).siblings(".beau-radio").removeClass("active");
            }
        }
    });

    function pay(obj){
      if(!$('input[name="payType"]:checked').length) {
           show_dialog('请选择一种支付类型');
           return;
        }
        var payType = $('input[name="payType"]:checked').val();
        var o_e = $(obj).attr('onclick');
        $(obj).removeAttr('onclick');
        $.ajax({
            type : "POST",
            url : baseUrl + "storedetail/view/renew",
            data : "payType=" + payType + "&goodsName=系统续费",
            dataType : "json",
            success : function(e){
                if (e.code != 0) {
                    dialog(e.msg);
                    $(obj).attr('onclick', o_e);
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
//              WeixinJSBridge.invoke('closeWindow', {}, function(e) {});
                  window.location.href = baseUrl + "storedeatil/view/index";
              } else {
                  isPay = false;
              }
          });
      }
</script>
</body>
</html>