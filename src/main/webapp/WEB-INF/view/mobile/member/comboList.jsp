<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>我的套餐</title>
    <!--字体图标库-->
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <!--整体的样式-->
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <div class="package">
        <c:forEach items="${comboList }" var="combo">
            <div class="package-list mt2">
	            <ul class="taocan-ul bsw">
	                <li class="taocan-list">
	                    <img src="<%=picPath%>${combo.comboImage}?imageView2/1/w/220/h/220">
	                    <div class="list-desc">
	                        <div class="list-name">${combo.comboName }</div>
	                        <div class="faxingshi">
	                            <span class="word">套餐价格</span>
	                            <span class="num">￥${combo.comboPrice }</span>
	                        </div>
	                        <div class="dingdan">
	                            <span class="s-price">原价 <span class="text-through">￥${combo.projectAmount }</span></span>
	                        </div>
	                    </div>
	                    <div class="dingdan-edit fr s-modal-control" data-target="#yg-fix-p-select">
	                        <span class="list-price"><span class="iconfont icon-zhankai control-span"></span></span>
	                    </div>
	                </li>
	            </ul>
	            <ul class="taocan-detail-wrap bsw" style="display: none">
	               <c:forEach items="${combo.projectList }" var="project">
	                    <li class="taocan-detail">
	                        <div ><span class="name">${project.projectName }</span> <span class="cishu">*${project.remainingCount }</span></div>
	                        <div class="detail"><span>${project.projectPrice }/每次，共可使用${project.projectCount }次</span></div>
	                        <c:choose>
	                           <c:when test="${project.remainingCount ==0 }">
	                               <div class="normal-btn btn-cancel font-999">马上预约</div>
	                           </c:when>
	                           <c:otherwise>
	                               <a href="<%=basePath%>memberCenter/view/projectDetail?projectId=${project.projectId}">
	                                   <div class="normal-btn">马上预约</div>
	                               </a>
	                           </c:otherwise>
	                        </c:choose>
	                    </li>
	               </c:forEach>
	            </ul>
	        </div>
        </c:forEach>
    </div> 
</div>
<c:if test="${empty comboList }">
   <div class="kongjilv">
        <div class="center">
            <div class="iconfont icon-xingzhuang14"></div>
            <div>您暂未购买任何套餐，欢迎来店购买</div>
        </div>
    </div>
</c:if>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script>
    $(".control-span").on("click", function(){
        var th = $(this);
        th.css("transform","rotate(180deg)");
        var parent  = th.parents(".package-list");
        var target = parent.find(".taocan-detail-wrap").toggle();
    });
</script>
</body>
</html>