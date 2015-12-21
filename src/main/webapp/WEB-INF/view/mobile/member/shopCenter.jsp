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
	            <li class="score-shop-li active">
                    <a href="<%=basePath%>memberCenter/view/shopCenter">
                        <img src="<%=basePath%>images/mobile/active-new.png" alt="" class=""/>
                        <div class="tab-word"><span>积分商城</span></div>
                    </a>
                </li>
                <li class="score-shop-li">
                    <a href="<%=basePath%>memberCenter/view/memberCoupon">
                        <img src="<%=basePath%>images/mobile/active-new.png" alt="" class="hide"/>
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
	
	    <div class="jifenduihuan-wrap pt5">
	        <ul>
	           <c:forEach items="${page.results }" var="couponInfo">
	               <c:set var="couponType" value="通用" />
	               <c:choose>
	                   <c:when test="${couponInfo.couponType == 2 }">
	                       <c:set var="couponType" value="商品" />
	                   </c:when>
	                   <c:when test="${couponInfo.couponType == 1 }">
                           <c:set var="couponType" value="项目" />
                       </c:when>
	               </c:choose>
	               <li class="duihuanquan-list mt2 bsw">
	                    <c:choose>
	                       <c:when test="${couponInfo.couponType == 0 }">
	                           <div class="img-price jifen-tongyong">
		                            <img src="<%=basePath%>images/mobile/jifen-tongyong.jpg" alt="" class="price-img"/>
		                            <div class="quan-price">
		                                <span class="rmb">￥</span>
		                                <span class="s-num">${couponInfo.couponPrice }</span>
		                            </div>
		                            <div class="name">适用所有项目/商品</div>
		                        </div>
                           </c:when>
                           <c:otherwise>
                               <div class="img-price">
	                               <img src="<%=picPath%>${couponInfo.projectImage}?imageView2/1/w/720/h/440" alt="" class="price-img"/>
	                               <div class="num"> <span class="rmb">￥</span><span>${couponInfo.couponPrice }</span></div>
	                               <div class="name">${couponInfo.projectName}</div>
                               </div>
                           </c:otherwise>
	                    </c:choose>
		                
	                    <div class="desc fr">
	                        <div class="name">${couponInfo.couponPrice }元${couponType }券</div>
	                        <div class="need-jifen">需要积分${couponInfo.couponVantages }</div>
	                        <div class="normal-btn s-modal-control" onclick="exchange('${couponInfo.couponId}',this)">立即兑换</div>
	                        <span name="tip" class="hide">
	                           您确定使用${couponInfo.couponVantages }积分，兑换${couponInfo.couponPrice }元${couponInfo.projectName}${couponType }现金抵用券吗？
	                           该券将在<span style="color: red;">${couponInfo.overdueTime }</span>过期，兑换后尽快到店使用哦。
	                       </span>
	                    </div>
	                </li>
	           </c:forEach>
	        </ul>
	    </div>
	</div>
	
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
	
	<!--确认兑换-->
	<div class="s-modal hide s-modal-miss" id="confirmWindow">
	    <div class="s-modal-wrap">
	        <div class="d-member-info">
	            <div class="n-modal-title text-center">
	                <span>确认兑换</span>
	                <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
	                <div class="clearfix"></div>
	            </div>
	            <div class="s-modal-body">
	                <div class="word text-left tip">
	                    
	                </div>
	            </div>
	            <div class="s-modal-foot">
	                <div class="modal-cancel">取消</div>
	                <div class="modal-confirm" onclick="exchangeConfirm();">确认</div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!--积分不足-->
	<div class="s-modal hide s-modal-miss" id="duihuan-buzu">
	    <div class="s-modal-wrap">
	        <div class="d-member-info">
	            <div class="n-modal-title text-center">
	                <span>抱歉！积分余额不足</span>
	                <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
	                <div class="clearfix"></div>
	            </div>
	            <div class="s-modal-body">
	                <div class="body-title">您可以通过以下方式兑换积分:</div>
	                <div class="word text-left">
	                    <p>1.您在对会员卡进行充值或消费时均可获得积分奖励。</p>
	                    <p>2.您可以再积分商城兑换优惠券，在消费项目或购买商品时均可抵现。</p>
	                </div>
	            </div>
	            <div class="s-modal-foot">
	                <div class="modal-confirm" style="width: 480px;">确认</div>
	            </div>
	        </div>
	    </div>
	</div>
</div>  
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script>
    var couponId = '';
    function exchange(c, obj){
    	$(".tip").html($(obj).next().html());
    	$("#confirmWindow").removeClass("hide");
    	couponId = c;
    } 
    
    function exchangeConfirm(){
    	$.ajax({
    		url : baseUrl + "memberCenter/action/exchangeCoupon",
    		type : "POST",
    		data : "couponId=" + couponId,
    		success : function(e){
    			if (e.code != 0) {
    				dialog(e.msg);
    			} else {
	    			dialog("兑换成功，已放入您的优惠券列表！");
    			}
    		}
    	});
    }
    
    $(function () {
       var width = document.body.scrollWidth;
        imgWidth = width - 168;
       $(".price-img").css("width", imgWidth);
    })
</script>
</body>
</html>