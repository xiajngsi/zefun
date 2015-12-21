<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>我的优惠券</title>
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
                        <img src="<%=basePath%>images/mobile/active-new.png" alt=""  class="hide"/>
                        <div class="tab-word"><span>积分商城</span></div>
                    </a>
                </li>
                <li class="score-shop-li active">
                    <a href="<%=basePath%>memberCenter/view/memberCoupon">
                        <img src="<%=basePath%>images/mobile/active-new.png" alt="" class=""/>
                        <div class="tab-word"><span>我的优惠券</span></div>
                    </a>
                </li>
                <li class="score-shop-li">
                    <a href="<%=basePath%>memberCenter/view/integralFlow">
                        <img src="<%=basePath%>images/mobile/active-new.png" alt="" class="hide"/>
                        <div class="tab-word"><span>我的积分</span></div>
                    </a>
                </li>
            </ul>
        </div>
        
        <div class="caibao-warp pt5">
            <c:forEach items="${couponList }" var="couponInfo">
                <c:set var="serviceUrl" value="" />
                <c:set var="couponType" value="" />
                <c:choose>
                    <c:when test="${couponInfo.isOverdue == 1 }">
                        <c:set var="serviceUrl" value="javascript:void();" />
                        <c:set var="couponType" value="guoqi" />
                    </c:when>
                    <c:when test="${couponInfo.couponType == 2 }">
                        <c:set var="serviceUrl" value="memberCenter/view/orderAppointment/${session_key_store_id}/1" />
                        <c:set var="couponType" value="shangpin" />
                    </c:when>
                    <c:when test="${couponInfo.couponType == 1 }">
                        <c:set var="serviceUrl" value="memberCenter/view/projectDetail?projectId=${couponInfo.projectId }" />
                        <c:set var="couponType" value="xiangmu" />
                    </c:when>
                    <c:when test="${couponInfo.couponType == 3 }">
                        <c:set var="serviceUrl" value="memberCenter/view/orderAppointment/${session_key_store_id}/1" />
                        <c:set var="couponType" value="taocan" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="serviceUrl" value="memberCenter/view/orderAppointment/${session_key_store_id}/1" />
                        <c:set var="couponType" value="tongyong" />
                    </c:otherwise>
                </c:choose>
                <ul class="caibao-ul">
		            <li class="caibao-list quan-${couponType }">
		                <div class="quan-top">
		                    <div class="quan-price">
		                        <span class="rmb">￥</span>
		                        <span class="num">${couponInfo.couponPrice }</span>
		                    </div>
		                    <div class="time">到期时间：${couponInfo.overdueTime }</div>
		                    <c:choose>
		                      <c:when test="${couponInfo.isOverdue == 1 }">
		                          <div class="normal-btn yuyue">已过期</div>
		                      </c:when>
		                      <c:otherwise>
		                          <a href="<%=basePath%>${ serviceUrl}">
		                              <div class="normal-btn yuyue">立即预约</div>
		                          </a>
		                      </c:otherwise>
		                    </c:choose>
		                </div>
		                <div class="quan-bm">
		                    <c:choose>
		                      <c:when test="${couponInfo.couponType == 0 }">
		                          <div class="type">通用优惠券</div>
                                  <div class="use">用于所有商品/项目的现金抵扣</div>
		                      </c:when>
		                      <c:otherwise>
		                          <c:set var="couponName" value="商品" />
		                          <c:choose>
		                              <c:when test="${couponInfo.couponType == 1 }">
		                                  <c:set var="couponName" value="项目" />
		                              </c:when>
		                          </c:choose>
		                          <div class="type">${couponName }优惠券</div>
                                  <div class="use">用于“${couponInfo.projectName }”现金抵扣</div>
		                      </c:otherwise>
		                    </c:choose>
		                </div>
		            </li>
		        </ul>
            </c:forEach>
	    </div>
    </div>
    
    <c:if test="${empty couponList }">
        <a href="<%=basePath%>memberCenter/view/shopCenter">
	        <div class="kongjilv">
	            <div class="center">
	                <div class="iconfont icon-xingzhuang14"></div>
	                <div>您暂无优惠券，立即前往积分商城兑换</div>
	            </div>
	        </div>
        </a>
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
        <li class="active" >
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