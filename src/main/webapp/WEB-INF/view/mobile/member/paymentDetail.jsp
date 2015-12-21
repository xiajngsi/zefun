<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>支付信息</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <div class="xiaofeimingxi">
	  <div class="xiaofei-item">
	    <div class="dingdan-title">
	      <span class="fl font-666 fs24">订单号：${orderPayment.orderCode }</span>
	      <span class="fr fs24">${orderPayment.createTime }</span>
	    </div>
	
	    <ul class="order-ul mb2 pb2">
	       <c:forEach items="${orderPayment.detailList }" var="detail">
	           <li class="xiaofei-list">
	            <div class="xiaofei-col one">
	              <span class="fl">
	               ${detail.projectName }
	               <c:if test="${detail.orderType != 1 }">
	                   X ${detail.projectCount }
	               </c:if>
	              </span>
	              <span class="fr">
	               ￥${detail.projectPrice }
	              </span>
	            </div>
	            <c:if test="${detail.offType == '优惠券抵扣' }">
	               <div class="clearfix"></div>
	                <div class="xiaofei-col two">
	                  <span class="fl">会员折扣优惠</span>
	                  <span class="fr">
	                     -￥${detail.projectPrice - detail.discountAmount }
	                  </span>
	                </div>
	            </c:if>
	            <c:if test="${detail.offAmount > 0 }">
	               <div class="clearfix"></div>
	                <div class="xiaofei-col two">
	                  <span class="fl">${detail.offType }</span>
	                  <span class="fr">
	                     -￥${detail.offAmount }
	                  </span>
	                </div>
	            </c:if>
	            <c:if test="${detail.appointOff > 0 }">
	               <div class="clearfix"></div>
	                <div class="xiaofei-col three">
	                  <span class="fl">在线预约优惠</span>
	                  <span class="fr">
	                    -￥${detail.appointOff}
	                  </span>
	                </div>
	            </c:if>
	          </li>
	       </c:forEach>
	    </ul>
	    <ul class="xiaofei-zongjie bsw ">
	      <li>
	        <div class="xiaofei-col two">
	          <span class="fl">卡金支付</span>
	          <span class="fr">-￥${orderPayment.cardAmount }</span>
	        </div>
	        <div class="clearfix"></div>
	        <div class="xiaofei-name xiaofei-col">
	          <span class="fl">实际消费金额</span>
	          <span class="red-price fr s-price">￥${orderPayment.receivableAmount }</span>
	        </div>
	        <div class="clearfix"></div>
	      </li>
	      <li class="share">
	        <div class="tip-word">本次消费，您获得了 <span class="num">${integralAmount }</span> 积分</div>
	        <div>赶快分享你的新造型，领取额外积分！</div>
	        <c:choose>
	            <c:when test="${order.orderStatus == 3 }">
	                <div onclick="goEvaluate()" class="normal-btn fr ml2 mt2">立即评价</div>
	            </c:when>
	            <c:otherwise>
	                <!-- <div onclick="goShare()" class="normal-btn fr ml2 mt2">分享给好友</div> -->
	            </c:otherwise>
	        </c:choose>
	      </li>
	    </ul>
	  </div>
	
	  <div class="good-shop bsw">
	    <img src="<%=picPath %>${storeInfo.storeLogo }?imageView2/1/w/125/h/125" class="fl"/>
	    <div class="desc fl" >
	      <div> <span class="shop-name">${storeInfo.storeName }</span></div>
	      <div> <span class="shop-addr">${storeInfo.storeAddress }</span> </div>
	      <div> <span class="shop-desc">电话：${storeInfo.storeTel }</span> </div>
	    </div>
	  </div>
	</div>
</div>
<script type="text/javascript">
function goEvaluate(){
	window.location.href = "<%=basePath %>memberCenter/view/orderEvaluate?orderId=${orderPayment.orderId}";
}
function goShare(){
    window.location.href = baseUrl + "memberCenter/view/share?orderId=${orderPayment.orderId}";
}
</script>
</body>
</html>