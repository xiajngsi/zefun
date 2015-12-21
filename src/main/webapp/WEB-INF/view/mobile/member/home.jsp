<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>会员中心</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
    <script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
  </head>
<body>
<div class="content">
    <!--个人中心页面-->
	<div class="bg-gray mb-footer">
	    <div class="person-info-login">
	        <a href="<%=basePath%>memberCenter/view/info">
	            <img src="<%=picPath%>${memberBaseInfo.headUrl }?imageView2/1/w/220/h/220" alt=""/>
	            <div class="info">
	                <div class="name">${memberBaseInfo.name } <span class="sex">${memberBaseInfo.sex }</span></div>
	                <div class="shop-name">${memberBaseInfo.storeName }</div>
	            </div>
	            <div class="member-flag fr">
	                <span>${memberBaseInfo.levelName }</span>
	            </div>
	        </a>
	    </div>
	    <div class="score-money-list-wrap">
	        <ul>
	            <li class="money-li">
	                <a href="<%=basePath%>memberCenter/view/cardmoneyFlow">
	                  <div><span>储值余额 </span></div>
	                  <div><span class="ml num">${memberBaseInfo.balanceAmount }</span></div>
	                </a>
	            </li>
	            
	            <li class="score-li">
	               <a href="<%=basePath%>memberCenter/view/giftmoneyFlow">
	                 <div><span>礼金余额 </span></div>
	                 <div><span class="ml num">${memberBaseInfo.giftmoneyAmount }</span></div>
	               </a>
	            </li>
	        </ul>
	    </div>
	    <div class="function-list-wrap mt2">
	        <ul class="function-list">
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>memberCenter/view/orderList">
	                  <span><i class="iconfont icon-dingdan"></i> <span class="ml">我的订单</span></span>
	                  <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
                    <a href="<%=basePath%>memberCenter/view/appointmentList/${session_key_store_id}/1">
                      <span><i class="iconfont icon-iconfontreneps"></i> <span class="ml">我的预约</span></span>
                      <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
                    </a>
                </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>memberCenter/view/comboList">
	                  <span><i class="iconfont icon-huli2"></i> <span class="ml">我的套餐</span></span>
	                  <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>memberCenter/view/memberCoupon">
	                  <span><i class="iconfont icon-youhuiquan0240"></i> <span class="ml">我的优惠券</span></span>
	                  <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	        </ul>
	
	        <ul class="function-list mt2">
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>memberCenter/view/info">
	                    <span><i class="iconfont icon-ordinaryset"></i> <span class="ml">设置</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	        </ul>
	    </div>
	</div>
	
	<div class="footer">
	  <ul>
	    <li class="active">
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
	    <li >
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
</body>
</html>