<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>店铺介绍</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/swiper.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <c:choose>
        <c:when test="${!empty storeInfo}">
            <div class="shop-intro mb-footer">
		        <div class="shop-info">
		            <div class="project-detail-descript">
		                <div class="swiper-container">
		                    <div class="swiper-wrapper">
		                       <c:forEach items="${imgArray }" var="img">
		                            <div class="swiper-slide">
		                                <img class="lazy" name="lazyImage" src="<%=picPath %>img_lazy_loding.png" data-original="<%=picPath %>${img}"/>
		                            </div>
		                       </c:forEach>
		                    </div>
		                </div>
		            </div>
		            <div class="address-tel">
		                <ul>
		                    <li class="icon-address" style="padding-left: 0px;">
		                        <i class="iconfont icon-icon"></i>
		                    </li>
		                    <li class="address">
		                        <div class="address-specific">${storeInfo.storeAddress }</div>
		                    </li>
		    
		                    <li class="tel s-modal-control" data-target="#shop-tel">
		                        <i class="iconfont icon-dianhua1"></i>
		                    </li>
		                </ul>
		            </div>
		        </div>
		    
		        <div class="function-list-wrap mt2">
		            <ul class="function-list">
		                <li class="function-item normal-li">
		                    <a href="<%=basePath %>memberCenter/view/storeShow?type=1">
		                        <span><i class="iconfont icon-xingzhuang22"></i> <span class="ml">门店介绍</span> </span>
		                        <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
		                    </a>
		                </li>
		                <li class="function-item normal-li">
		                    <a href="<%=basePath %>memberCenter/view/storeShow?type=2">
		                        <span><i class="iconfont icon-xingzhuang23"></i> <span class="ml">技术展示</span></span>
		                        <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
		                    </a>
		                </li>
		                <li class="function-item normal-li">
		                    <a href="<%=basePath %>memberCenter/view/storeShow?type=3">
		                        <span><i class="iconfont icon-xingzhuang24"></i> <span class="ml">特色服务</span></span>
		                        <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
		                    </a>
		                </li>
		            </ul>
		            <%-- <ul class="function-list mt2">
		                <li class="function-item normal-li">
		                    <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_id}/1">
		                        <span><i class="iconfont icon-xingzhuang21"></i> <span class="ml">名师预约</span></span>
		                        <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
		                    </a>
		                </li>
		            </ul> --%>
		        </div>
		    </div>
		    <!--店铺电话-->
		    <div class="s-modal hide  s-modal-miss" id="shop-tel">
		        <div class="s-modal-wrap">
		            <div class="shop-tel">
		                <a href="wtai://wp/mc;0735-11205678" class="modal-btn shop-modal-tel blue-word">${storeInfo.storeTel }</a>
		                <a href="" class="modal-btn shop-modal-cancel">取消</a>
		            </div>
		        </div>
		    </div>
        </c:when>
        <c:otherwise>
            <div class="kongjilv">
                <div class="center">
                    <div class="iconfont icon-xingzhuang14"></div>
                    <div>该门店暂未设置任何暂时内容，稍后再来看看吧</div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
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
        <li >
          <a href="<%=basePath%>memberCenter/view/shopCenter">
            <span class="iconfont icon-jifen"></span>
            <span>积分</span>
          </a>
        </li>
        <li  class="active">
          <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_id}/1">
            <span class="iconfont icon-dianpu2"></span>
            <span>门店</span>
          </a>
        </li>
      </ul>
    </div>
</div>  
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script>
	$(document).ready(function (){
		var width = $(window).width();
		$(".swiper-container").height(width);
		
       var mySwiper = new Swiper ('.swiper-container', {
           autoplay: 2000,
           direction : 'horizontal',
       });
       
       $("img[name='lazyImage']").each(function(){
    	   var dw = width * 2;
           $(this).attr("src", $(this).attr("data-original") + "?imageView2/1/w/" + dw + "/h/" + dw)
       });

		//图片延迟加载
		var winHeight = $(window).height();
		$("img.lazy").lazyload({ 
		  threshold : 100,
		  skip_invisible : false,
		  effect: "fadeIn"
		}); 
    });
</script>
</body>
</html>