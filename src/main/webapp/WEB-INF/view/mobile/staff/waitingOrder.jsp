<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
  <meta content="telephone=no" name="format-detection" />
  <title>等待中心</title>
  <link rel="stylesheet" href="<%=basePath%>css/mobile/swiper.min.css"/>
  <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
  <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content">
	<div class="yg-dingdan-all">
	    <div class="dingdan-person">
	        <ul class="dengdai-ul" >
	           <c:forEach items="${list}" var="obj">
	                <li class="dengdai-list">
		                <div class="img-warp">
		                    <img src="<%=picPath%>${obj.projectImage}" alt=""/>
		                    <div class="fuwuhao-wrap">
		                        <span class="fuwuhao"><span>${obj.detailCode}</span></span>
		                    </div>
		                </div>
		                <div class="list-desc">
		                    <div class="list-name">${obj.projectName}<span class="list-price">￥${obj.discountAmount}</span></div>
		                    <div class="faxingshi">
		                        <span class="zhiwei">${obj.shiftMahjongName}</span>
		                        <span class="name font-333">${obj.name}</span>
		                    </div>
		                    <div class="dingdan">
		                        <span class="font-999">已等待时间</span>
		                        <span class="time font-333">${obj.differ}</span><span>  分钟</span>
		                    </div>
		                </div>
		                <c:if test="${obj.name != '未指定'}">
		                    <div class="dingdan-edit fr s-modal-control" data-target="#yg-fix-p-select">
			                    <div class="normal-btn" onclick="updateEmployee(${obj.detailId}, ${obj.shiftMahjongStepId})">换人</div>
			                </div>
		                </c:if>
		            </li>
	           </c:forEach>
	            
	        </ul>
	    </div>
	</div>
	<c:if test="${empty list }">
       <div class="kongjilv">
            <div class="center">
                <div class="iconfont icon-xingzhuang14"></div>
                <div>暂无等候中的顾客，继续保持</div>
            </div>
        </div>
    </c:if>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script>
function updateEmployee(detailId, shiftMahjongStepId){
	var temp = document.createElement("form");
	temp.action = baseUrl + "staff/view/waitingCentreShiftMahjong";
	temp.method = "post";
	temp.style.display = "none";
	var opt = document.createElement("textarea");
	opt.name = "detailId";
	opt.value = detailId;
	var tpt = document.createElement("textarea");
	tpt.name = "shiftMahjongStepId";
	tpt.value = shiftMahjongStepId;
	temp.appendChild(opt);
	temp.appendChild(tpt);
    document.body.appendChild(temp);
	temp.submit();
}
</script>
</body>
</html>