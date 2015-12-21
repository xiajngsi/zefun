<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>接待大厅</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content">
    <div class="function-list-wrap">
        <ul class="function-list">
            <li class="function-item normal-li">
                    <span>
                        <i class="iconfont icon-shouji3" style="margin-left:-0.0625rem;"></i>
                        <input type="tel" class="w60p" name="mobileNum" style="margin-left:1rem;text-align: left;" placeholder="请输入会员手机号"/>
                    </span>
                    <a href="javascript:void(0);" style="display: inline">
                        <div id="mobileConfirm" class="btn fr confirm-reception" style="margin-top:.5rem;">确认</div>
                    </a>
            </li>
            <li class="function-item normal-li">
                <a href="javascript:void(0);" onclick="disperseClient('男')">
                    <span><i class="iconfont icon-manuser"></i> <span class="ml">男散客</span></span>
                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
                </a>
            </li>
            <li class="function-item normal-li">
                <a href="javascript:void(0);" onclick="disperseClient('女')">
                    <span><i class="iconfont icon-womanuser"></i> <span class="ml">女散客</span></span>
                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
                </a>
            </li>
        </ul>
    </div>

    <c:if test="${session_key_role_id != 4 }">
	    <div class="function-list-wrap">
	        <ul class="function-list">
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/waitingCentre">
	                    <span>
	                        <i class="iconfont icon-denghou"></i>
	                        <span class="ml">等候大厅</span>
	                        <div class="have-message"></div>
	                    </span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/order/all">
	                    <span>
	                        <i class="iconfont icon-yiwwancheng"></i>
	                        <span class="ml">全部订单</span>
	                        <div class="have-message"></div>
	                    </span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/selectAllShiftMahjong">
	                    <span>
	                        <i class="iconfont icon-quanbulunpai"></i>
	                        <span class="ml">全部轮牌</span>
	                    </span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	        </ul>
	    </div>
    </c:if>
	
	<div class="footer">
        <ul>
            <li class="active" >
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
            <li>
                <a href="<%=basePath%>staff/view/staffCenter">
                    <span class="iconfont icon-wode"></span>
                    <span>我的</span>
                </a>
            </li>
            <li>
                <a href="<%=basePath%>staff/view/more">
                    <span class="iconfont icon-gengduo2"></span>
                    <span>更多</span>
                </a>
            </li>
        </ul>
    </div>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript">


$("#mobileConfirm").click(function(){
	if ($("input[name='mobileNum']").val() == "") {
		dialog("手机号码不能为空");
		return;
	} 
	$.ajax({
        type : "post",
        url : baseUrl + "staff/action/selectBaseInfo",
        data : "phone="+$("input[name='mobileNum']").val(),
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            var data = e.msg;
            
            var memberBaseDto = JSON.stringify(data);
            submits(memberBaseDto);
        }
    });
});

function disperseClient(sex) {
	var memberBase = {"sex":sex};
	var memberBaseDto = JSON.stringify(memberBase);	
	submits(memberBaseDto);
}

function submits (memberBaseDto) {
	var temp = document.createElement("form");
    temp.action = baseUrl + "staff/view/selectCategory";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("textarea");
    opt.name = "memberBaseDto";
    opt.value = memberBaseDto;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
}
</script>
</body>
</html>