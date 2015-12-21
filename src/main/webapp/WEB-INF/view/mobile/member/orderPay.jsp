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
    <title>确认付款</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/mui.picker.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/mui.poppicker.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <div class="my-order-doing mb-footer">
	    <ul class="order-ul">
	        <li class="dingdan-danhao">
	            <span class="danhao fl">
	                单号: ${orderDto.orderCode}
	            </span>
	            <span class="fr fs24">${orderDto.createTime}</span>
	        </li>
	        <c:if test="${not empty orderDto.orderDetails and fn:length(orderDto.orderDetails) > 0}">
                <c:forEach var="detail" items="${orderDto.orderDetails}">
			        <li class="dingdan-list" data-type="${detail.orderType}">
			            <img src="<%=picPath%>${detail.projectImage}?imageView2/1/w/220/h/220">
			            <div class="list-desc">
			                <div class="list-name">
                                 ${detail.projectName}
                                 <c:if test="${detail.orderType != 1 }">
                                      X ${detail.projectCount}
                                 </c:if>
                            </div>
			                <div class="origin-price"><span class="text-through font-666 font-size-18">门店价格: ￥${detail.projectPrice}</span></div>
			                <div class="origin-price">
			                    <span class="font-666 font-size-18">会员价格: </span>
			                    <span class="red-price font-size-30">￥${detail.discountAmount}</span>
			                </div>
			            </div>
			            <div class="order-edit fr" name="payOffContent" highestDiscount="${detail.highestDiscount }" realMoney="${detail.discountAmount }" discountAmount="${detail.discountAmount }" projectPrice="${detail.projectPrice}" detailId="${detail.detailId }" uid="0" offType="0" offId="0" offAmount="0">
			                <div class="list-price blue-price" name="offAmount"></div>
			                <div class="name" name="offName"></div>
			            </div>
			        </li>
	           </c:forEach>
           </c:if>
	    </ul>
	    
	    <c:if test="${appointOff > 0 }">
		    <ul class="border-radius-ul">
	          <li class="two-line-li" style="height: 4.5rem;">
	              <span class="fl font-size-30 font-333" style="line-height: 4.5rem;">预约优惠金额</span>
	              <div class="fr text-right" style="line-height: 4.5rem;">
	                  <div class="blue-price font-size-44" style="line-height: 4.5rem;">-${appointOff }</div>
	              </div>
	          </li>
	        </ul>
        </c:if>
        
	    <ul class="border-radius-ul">
          <li class="two-line-li">
              <span class="fl font-size-30 font-333 line-height100">礼金余额</span>
              <%-- <div class="fr mt text-right line-height80">
                  <div class="blue-price font-size-44">${orderDto.memberInfo.giftmoneyAmount }</div>
              </div> --%>
              
              <div class="fr mt text-right">
                  <div class="blue-price font-size-44">${orderDto.memberInfo.giftmoneyAmount }</div>
                  <div class="font-size-20 font-999"><span class="font-size-18">系统会自动为您选择最大礼金抵扣值</span></div>
              </div>
          </li>
        </ul>
	
	    <ul class="border-radius-ul">
	      <li class="two-line-li">
	          <span class="fl font-size-30 font-333 line-height100">卡金支付</span>
	          <div class="fr mt text-right">
	              <div class="blue-price font-size-44" id="cardMoney">-0.00</div>
	              <div class="font-size-20 font-999"><span class="font-size-18">余额：</span><span id="balanceAmount">${orderDto.memberInfo.balanceAmount}</span></div>
	          </div>
	      </li>
	    </ul>
	    <div class="pay">
            <span id="totalMoney" class="word">
                <span id="chargeTip">
                     余额不足，请充值
                </span> 
                <div id="payBtn" onclick="orderpay('${orderDto.orderId}');" class="normal-btn fr mt2">确定付款</div>
            </span>
        </div>
	</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/mui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/mui.picker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/mui.poppicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/base/big.js"></script>
<script type="text/javascript">
var detailList = eval('${detailStr}');
var allOffMap = JSON.parse('${allOffStr}');
var memberBalanceAmount = '${orderDto.memberInfo.balanceAmount}';
var appointOff = new Big('${appointOff}');
var orderId = '${orderDto.orderId}';
</script>
<script type="text/javascript" src="<%=basePath%>js/mobile/memberPay.js"></script>
</body>
</html>