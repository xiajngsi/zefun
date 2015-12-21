<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>预约服务人员</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/star-font.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/star-rating.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/swiper.min.css"/>
  </head>
<body>
<div class="content">
    <div class="project-detail">
	    <div class="project-detail-descript">
	        <div class="project-detail-descript">
	            <div class="swiper-container">
	                <div class="swiper-wrapper">
	                    <c:forEach items="${imgList }" var="img">
	                        <div class="swiper-slide">
	                            <img name="projectImg" src="<%=picPath%>${img}?imageView2/1/w/640/h/640" alt=""/>
	                        </div>
	                    </c:forEach>
	                </div>
	            </div>
	        </div>
	        <div class="project-main bsw">
	            <div class="project-info">
	                <div class="name font-size-34">${project.projectName }</div>
	                <div>
	                   <span class="font-size-44 red-price">￥${discountAmount - project.appointmentPrice }</span><span class="project-p-name">${levelName }专享价</span>
	                   <c:if test="${project.appointmentPrice > 0 }">
	                       <span class="youhui">预约-${project.appointmentPrice }</span>
	                   </c:if>
                    </div>
	                <div class="font-size-24 font-999">
		                <span >门店价：</span> 
		                <span class="text-through">¥${project.projectPrice }</span>
		                <span class="ml">已服务${project.salesCount }人</span>
	                </div>
	            </div>
	            <div class="project-share">
	                <div><span class="iconfont icon-fenxiang"></span></div>
	                <!-- <div><span>分享</span></div> -->
	            </div>
	        </div>
	    </div>
	
	    <div class="unline-tab-control project-state">
	        <ul class="tab-list">
	            <li class="unline-tab active" data-target="#tab1"><span>预约</span></li>
	            <li class="unline-tab" data-target="#tab2"><span>详情</span></li>
	            <li class="unline-tab" data-target="#tab3"><span>评价</span></li>
	        </ul>
	        <div class="tab-target" id="tab1">
	            <ul class="designer-list">
	                <c:forEach items="${project.employeeList }" var="employee">
	                    <li class="designer-item-content">
	                        <img src="<%=picPath%>${employee.headImage}?imageView2/1/w/260/h/260" alt=""/>
	                        <div class="info">
	                          <div class="info-wrap">
	                            <div class="fs30 font-666">${employee.name } <span class="ml normoal-word font-size-28 font-666">(${employee.levelName })</span></div>
	                            <input type="number" class="rating input-id" data-size="xs" disabled value="${employee.serviceScore }">
	                            <div><span class="normoal-word fs22">服务 <span class="blue-word">${employee.servicePeoples }</span>人次</span></div>
	                          </div>
	                        </div>
	                        <a href="javascript:appoint('${employee.employeeId }','${employee.name }','${employee.levelName }')" class="order-btn fr">预约</a>
	                    </li>
	                </c:forEach>
	            </ul>
	        </div>
	        <div id="tab2" class="tab-target hide">
	            <ul>
	                <li class="project-content-li medium-font">
	                    <div class="project-desc">
	                        <span class="font-999 fs24">${project.projectDesc }</span>
	                    </div>
	                </li>
	            </ul>
	        </div>
	        <div id="tab3" class="tab-target hide">
	           <c:choose>
	               <c:when test="${empty evaluateList }">
	                   <ul class="pingjia-item">
                           <li class="pingjia-content font-999">
                               <br/>
                               暂无评价，赶紧预约来抢板凳吧
                           </li>
                       </ul>
	               </c:when>
	               <c:otherwise>
	                   <c:forEach items="${evaluateList }" var="evaluate">
		                   <ul class="pingjia-item">
		                        <li class="pingjia-person">
		                            <img src="<%=picPath%>${evaluate.memberInfo.headUrl }?imageView2/1/w/70/h/70" alt="" class="person-circle-img"/>
		                            <span class="name font-size-24">${evaluate.memberInfo.name }</span>
		                            <span class="identity font-size-18 blue-word">${evaluate.memberInfo.levelName }</span>
		                            <div class="inline-star">
		                               <input  type="number" class="rating input-id " data-size="xs" disabled value="${evaluate.evaluateRate }" style="display: inline-block">
		                            </div>
		                        </li>
		                        <li class="pingjia-content font-999">
		                            ${evaluate.comment }
		                        </li>
		                    </ul>
		               </c:forEach>
	               </c:otherwise>
	           </c:choose>
	        </div>
	    </div>
	</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/star-rating.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
<script>
$(".input-id").rating({showCaption : false, step : 0.5});
$(function(){
	var allSliderNum = 3;
    var mySwiper = new Swiper ('.swiper-container', {
        autoplay: 2000,
        direction : 'horizontal',
        pagination : '.photo-num',
    });

    $(".unline-tab").on("click", function () {
        var data = $(this).data("target");
        console.log(data);
        var unlineTab = $(".unline-tab");
        unlineTab.removeClass("active");
        $(this).addClass("active");
        $(".tab-target").addClass("hide");
        $(data).removeClass("hide");

    })
    
	var width = $(window).width();
	$("[name='projectImg']").height(width);
	$(".swiper-container").height(width);
});
    
    var projectId = "${project.projectId}";
    var projectName = "${project.projectName}";
    var projectStepOrder = "${project.projectStepOrder}";
    var shiftMahjongId = "${project.shiftMahjongId}";
    function appoint(employeeId, employeeName, levelName){
    	var temp = document.createElement("form");
        temp.action = baseUrl + "memberCenter/view/dateAppointment";
        temp.method = "post";
        temp.style.display = "none";
        var opt = document.createElement("textarea");
        opt.name = "employeeId";
        opt.value = employeeId;
        temp.appendChild(opt);
        
        var opt1 = document.createElement("textarea");
        opt1.name = "employeeName";
        opt1.value = employeeName;
        temp.appendChild(opt1);
        
        var opt2 = document.createElement("textarea");
        opt2.name = "levelName";
        opt2.value = levelName;
        temp.appendChild(opt2);
        
        var opt3 = document.createElement("textarea");
        opt3.name = "projectId";
        opt3.value = projectId;
        temp.appendChild(opt3);
        
        var opt4 = document.createElement("textarea");
        opt4.name = "projectName";
        opt4.value = projectName;
        temp.appendChild(opt4);
        
        var opt5 = document.createElement("textarea");
        opt5.name = "projectStepOrder";
        opt5.value = projectStepOrder;
        temp.appendChild(opt5);
        
        var opt6 = document.createElement("textarea");
        opt6.name = "shiftMahjongId";
        opt6.value = shiftMahjongId;
        temp.appendChild(opt6);
        
        
        document.body.appendChild(temp);
        temp.submit();
    }
</script>
</body>
</html>