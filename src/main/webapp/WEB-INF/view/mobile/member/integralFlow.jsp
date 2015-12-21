<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>我的积分</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <div class="coupon-future mb-footer">
	    <div class="tab tabs">
	        <ul >
	            <li class="score-shop-li">
                    <a href="<%=basePath%>memberCenter/view/shopCenter">
                        <img src="<%=basePath%>images/mobile/active-new.png" alt="" class="hide"/>
                        <div class="tab-word"><span>积分商城</span></div>
                    </a>
                </li>
                <li class="score-shop-li">
                    <a href="<%=basePath%>memberCenter/view/memberCoupon">
                        <img src="<%=basePath%>images/mobile/active-new.png" alt="" class="hide"/>
                        <div class="tab-word"><span>我的优惠券</span></div>
                    </a>
                </li>
                <li class="score-shop-li active">
                    <a href="<%=basePath%>memberCenter/view/integralFlow">
                        <img src="<%=basePath%>images/mobile/active-new.png" alt=""/>
                        <div class="tab-word"><span>我的积分</span></div>
                    </a>
                </li>
	        </ul>
	    </div>
	    <div class="jifen-wrap">
	        <div class="my-score">
	            <span class="help"><span class="iconfont icon-bangzhuzhongxin"></span>使用帮助</span>
	            <div class="position-bottom">
	                <div class="jifen">
	                    <span class="num">${memberInfo.balanceIntegral }</span>
	                    <span class="word">积分</span>
	                </div>
	                <div class="jifen-shuoming">积分可兑换项目抵用券，现金抵用去或礼品券。</div>
	            </div>
	        </div>
	
	        <ul class="title-wrap">
	            <li class="jifenjilv-title">
	                积分记录
	            </li>
	        </ul>
	        <ul class="jifen-ul">
	            <c:forEach items="${integralFlowList }" var="integralFlow">
	                <li class="jifen-li" >
		                <div class="name">${integralFlow.businessType }</div>
		                <div class="time">${integralFlow.flowTime }</div>
		                <c:choose>
		                   <c:when test="${integralFlow.flowType == 1 }">
		                       <div class="jifen-num fu">-${integralFlow.flowAmount }</div>
		                   </c:when>
		                   <c:otherwise>
		                       <div class="jifen-num zheng">+${integralFlow.flowAmount }</div>
		                   </c:otherwise>
		                </c:choose>
		                
		            </li>
	            </c:forEach>
	        </ul>
	    </div>
	</div>
	
	<c:if test="${empty integralFlowList }">
        <div class="kongjilv">
            <div class="center">
                <div class="iconfont icon-xingzhuang14"></div>
                <div>近期暂无记录</div>
            </div>
        </div>
    </c:if>
	
	<div class="footer">
      <ul>
        <li>
          <a href="<%=basePath %>memberCenter/view/home/${session_key_store_id}/1">
            <span class="iconfont icon-wode"></span>
            <span class="word">我的</span>
          </a>
        </li>
        <li>
          <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_id}/1">
            <span class="iconfont icon-yuyue6"></span>
            <span>预约</span>
          </a>
        </li>
        <li  class="active">
          <a href="<%=basePath%>memberCenter/view/shopCenter">
            <span class="iconfont icon-jifen"></span>
            <span>积分</span>
          </a>
        </li>
        <li >
          <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_id}/1">
            <span class="iconfont icon-dianpu2"></span>
            <span>门店</span>
          </a>
        </li>
      </ul>
    </div>
</div>  
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
</body>
</html>