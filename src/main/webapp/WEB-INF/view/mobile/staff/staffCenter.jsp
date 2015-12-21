<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>个人中心</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content mb5">
	<div class="yg-person">
	    <div class="person-info-login  yg-member-info">
	        <a href="<%=basePath%>staff/view/staffInfo">
		        <img src="<%=picPath%>${employeeInfo.headImage}?imageView2/1/w/230/h/230" alt="" class="person-img"/>
		        <div class="info">
		            <div class="name">${employeeInfo.name }</div>
		            <div class="font-white">${employeeInfo.levelName } </div>
		        </div>
	        </a>
	        <c:choose>
	           <c:when test="${empty signStatus }">
	               <img src="<%=basePath%>images/mobile/employee/qiandao.png" onclick="sign(this);" class="qiandao"/>
	               <img src="<%=basePath%>images/mobile/employee/signout.png" onclick="sign(this);" class="qiandao hide"/>
	           </c:when>
	           <c:when test="${signStatus == '1' }">
                   <img src="<%=basePath%>images/mobile/employee/signout.png" onclick="sign(this);" class="qiandao"/>
                   <img src="<%=basePath%>images/mobile/employee/yiqiantu.png" onclick="dialog('您今日已完成签到、签退操作！')" class="qiandao hide"/>
	           </c:when>
	           <c:otherwise>
	               <img src="<%=basePath%>images/mobile/employee/yiqiantu.png" onclick="dialog('您今日已完成签到、签退操作！')" class="qiandao"/>
	           </c:otherwise>
	        </c:choose>
	    </div>
	
	    <div class="function-list-wrap mt2">
	        <ul class="function-list">
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/staffAppoint/${session_key_store_id}/2/1">
	                    <span><i class="iconfont icon-iconfontreneps"></i> <span class="ml">我的预约</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
                    <a href="<%=basePath%>staff/view/employeeOrderView/${session_key_store_id}/2">
                        <span><i class="iconfont icon-dingdan"></i> <span class="ml">我的订单</span></span>
                        <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
                    </a>
                </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/staffEarning">
	                    <span><i class="iconfont icon-yejitongji"></i> <span class="ml">我的业绩</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/myShiftMahjong">
	                    <span><i class="iconfont icon-quanbulunpai"></i> <span class="ml">我的轮牌</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	        </ul>
	
	        <!-- <ul class="function-list mt2">
	            <li class="function-item normal-li ">
	                <a href="javascript:Tip();">
	                    <span><i class="iconfont icon-jiandaojilu"></i> <span class="ml">签到记录</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li ">
	                <a href="javascript:Tip();">
	                    <span><i class="iconfont icon-qingjia"></i> <span class="ml">请假</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li ">
	                <a href="javascript:Tip();">
	                    <span><i class="iconfont icon-iconfontgongzidan"></i> <span class="ml">工资单</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	        </ul> -->
	    </div>
	</div>
	
	<div class="footer">
        <ul>
            <li >
                <a href="<%=basePath%>staff/view/reception">
                    <span class="iconfont icon-jiedai"></span>
                    <span>接待</span>
                </a>
            </li>
            <li>
                <a href="<%=basePath%>staff/view/allEarning">
                    <span class="iconfont icon-yeji"></span>
                    <span>业绩</span>
                </a>
            </li>
            <li class="active">
                <a href="<%=basePath%>staff/view/staffCenter">
                    <span class="iconfont icon-wode"></span>
                    <span>我的</span>
                </a>
            </li>
            <li >
                <a href="<%=basePath%>staff/view/more">
                    <span class="iconfont icon-gengduo2"></span>
                    <span>更多</span>
                </a>
            </li>
        </ul>
    </div>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript">
//加载时弹出框效果
function sign(obj){
	$.ajax({
		url : baseUrl + "staff/action/signOperate",
		type : "POST",
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				$(obj).addClass("hide").siblings(".qiandao").removeClass("hide");
			}
			dialog(e.msg);
		}
	});
}

function tip(){
	dialog("暂未开放，敬请期待！");
}
</script>
</body>
</html>