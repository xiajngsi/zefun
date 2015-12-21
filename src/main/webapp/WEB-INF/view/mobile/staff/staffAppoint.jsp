<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>我的预约</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content">
    <div class="tab">
        <ul >
            <li class="score-shop-li">
                <a href="<%=basePath %>staff/view/staffAppoint/${session_key_store_id}/2/1">
                    <img src="<%=basePath%>images/mobile/employee/active-new.png" class="hide" />
                    <div class="tab-word">
                        <span>新预约</span>
                    </div>
                </a>
            </li>
            <li class="score-shop-li">
                <a href="<%=basePath %>staff/view/staffAppoint/${session_key_store_id}/2/2">
                    <img src="<%=basePath%>images/mobile/employee/active-new.png"  class="hide"/>
                    <div class="tab-word">
                        <span>已确认</span>
                    </div>
                </a>
            </li>
            <li class="score-shop-li">
                <a href="<%=basePath %>staff/view/staffAppoint/${session_key_store_id}/2/3">
                    <img src="<%=basePath%>images/mobile/employee/active-new.png"  class="hide"/>
                    <div class="tab-word">
                        <span>已取消</span>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <div class="clearfix mt7"></div>
    <div class="yg-dingdan-all">
        <c:forEach items="${appointmentList }" var="appointment">
            <div class="dingdan-person" id="${appointment.appointmentId}">
              <ul class="dingdan-ul" >
                <li class="dingdan-title">
                    <div class="dis-ib s-modal-control">
                        <img src="<%=picPath%>${appointment.memberInfo.headUrl }?imageView2/1/w/220/h/220" />
                        <span class="name">${appointment.memberInfo.name}</span>
                        <span class="shenfen font-size-20">${appointment.memberInfo.levelName}</span>
                    </div>
                    <div class="dis-ib fr">
                        <span class="font-size-24 font-666">${appointment.createTime }</span>
                    </div>
                </li>
                <li class="dingdan-list">
                    <img src="<%=picPath%>${appointment.projectInfo.projectImage}?imageView2/1/w/220/h/220" />
                    <div class="list-desc">
                        <div class="list-name projectName">${appointment.projectInfo.projectName}</div>
                        <div class="faxingshi">
                            <span class="zhiwei">${appointment.shiftMahjongName}</span>
                        </div>
                        <div class="dingdan">
                            <!-- <span class="iconfont icon-yuyue4"></span> -->
                            <span class="font-blue appointmentTime">${appointment.appointmentDate } ${appointment.appointmentTime }</span>
                        </div>
                    </div>
                    <div class="dingdan-edit fr">
                        <span class="list-price">¥${appointment.appointmentPrice }</span>
                    </div>
                </li>
                <c:choose>
                    <c:when test="${appointment.appointmentStatus == 1 }">
                        <li class="cancel-reason hide">
                          <input type="text" name="reason" placeholder="请输入取消原因"/>
                        </li>
                        <li class="dingdan-jiezhang confirm-cancel hide">
                            <span class="fl"></span>
                            <div class="qiandan normal-btn cancel-order">
                                暂不拒绝
                            </div>
                            <div class="qiandan neg-btn" >
                                <a href="javascript:cancel(2, '${appointment.appointmentId}', '${appointment.memberId}', '${appointment.projectId }')">确认拒绝</a>
                            </div>
                        </li>
                        <li class="dingdan-jiezhang confirm-order">
                            <span class="fl fs24" name="status">
                                <span class="blue-word">待确认</span>
                             </span>
                            <div class="qiandan normal-btn">
                                <a href="javascript:agreeTip(1, '${appointment.appointmentId}', '${appointment.memberId}', '${appointment.projectId }')">接受预约</a>
                            </div>
                            <div class="qiandan neg-btn cancel-order"" >
                                拒绝预约
                            </div>
                        </li>
                    </c:when>
                    <c:when test="${appointment.appointmentStatus == 2 }">
                        <li class="dingdan-jiezhang">
                             <span class="fl fs24" name="status">
                                <span class="yellow-word">已接受</span>
                             </span>
                             <div class="qiandan normal-btn" onclick="startServiceTip(${appointment.appointmentId})">立即开单</div>
                        </li>
                    </c:when>
                    <c:when test="${appointment.appointmentStatus == 3 }">
                        <li class="dingdan-jiezhang">
                             <span class="fl fs24" name="status">
                                <span class="normoal-word">已开单</span>
                             </span>
                             <span class="fs24 normoal-word fr">顾客已到店服务&nbsp;&nbsp;</span>
                        </li>
                    </c:when>
                    <c:when test="${appointment.appointmentStatus == 4 }">
                        <li class="dingdan-jiezhang">
                            <span class="fl fs24" name="status">
                                <span class="normoal-word">已取消</span>
                            </span>
                            <span class="fs24 normoal-word fr">顾客取消了预约&nbsp;&nbsp;</span>
                        </li>
                    </c:when>
                    <c:when test="${appointment.appointmentStatus == 5 }">
                        <li class="dingdan-jiezhang">
                            <span class="fl fs24" name="status">
                                <span class="normoal-word">已拒绝</span>
                            </span>
                            <span class="fs24 normoal-word fr">您拒绝了预约&nbsp;&nbsp;</span>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
        </div>
        </c:forEach>
    </div>
    
    <!-- 同意确认提示框 -->
    <div class="s-modal hide s-modal-miss" id="agreeTip">
        <div class="s-modal-wrap">
            <div class="d-member-info">
                <div class="n-modal-title text-center">
                    <span>接受预约</span>
                    <div class="clearfix"></div>
                </div>
                <div class="s-modal-body">
                    <div class="word text-left">
                                                                      如果您接受了此次预约，请安排好个人时间提供服务
                    </div>
                </div>
                <div class="s-modal-foot">
                    <div class="modal-cancel">暂不接受</div>
                    <div class="modal-confirm" onclick="operate();">接受预约</div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 开单确认提示框 -->
    <div class="s-modal hide s-modal-miss" id="startServiceTip">
        <div class="s-modal-wrap">
            <div class="d-member-info">
                <div class="n-modal-title text-center">
                    <span>预约开单</span>
                    <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
                    <div class="clearfix"></div>
                </div>
                <div class="s-modal-body">
                    <div class="word text-left">
                        这是一个预约订单，你确定开单吗？
                    </div>
                </div>
                <div class="s-modal-foot">
                    <div class="modal-cancel">我点错了</div>
                    <div class="modal-confirm" onclick="startService();">立即开单</div>
                </div>
            </div>
        </div>
    </div>
</div>    
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript" >
var type = parseInt("${type}");
$(function(){
  $(".score-shop-li").eq(type - 1).addClass("active").find("img").removeClass("hide");	
  $(".confirm-order .cancel-order").on("click", function () {
    $(this).parent().addClass("hide").prev().removeClass("hide").prev().removeClass("hide").find("[name='reason']").val('');
  })
  $(".confirm-cancel .cancel-order").on("click", function () {
    $(this).parent().addClass("hide").prev().addClass("hide").next().next().removeClass("hide");
  })
});
var type, appointmentId, memberId, projectId;

function agreeTip(t, a, m, p){
    type = t;
    appointmentId = a;
    memberId = m;
    projectId = p;
    $("#agreeTip").removeClass("hide");
}

function startServiceTip(a){
    appointmentId = a;
    $("#startServiceTip").removeClass("hide");
}

function cancel(t, a, m, p){
    type = t;
    appointmentId = a;
    memberId = m;
    projectId = p;
    operate();
}

function operate(){
    var at = $("#" + appointmentId);
    var appointmentTime = at.find(".appointmentTime:first").html();
    var projectName = at.find(".projectName:first").html();
    var reason = at.find("[name='reason']:first").val();
    if (type == 2 && isEmpty(reason)) {
        dialog("请填写取消原因");
        return;
    }
    var data = "type=" + type + "&appointmentId=" + appointmentId + "&memberId=" + memberId + "&projectName=" + 
        projectName + "&appointTime=" + appointmentTime + "&reason=" + reason;
    $.ajax({
        url : baseUrl + "staff/action/appointOperate",
        type : "POST",
        data : data,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            at.find(".cancelBody").addClass("hide");
            at.find("li").last().removeClass("hide");
            var content = '<span class="fl fs24" name="status"><span class="normoal-word">已拒绝</span></span><span class="fs24 normoal-word fr">您拒绝了预约&nbsp;&nbsp;</span>';
            if (type == 1) {
                content = '<span class="fl fs24" name="status"><span class="yellow-word">已接受</span></span><div class="qiandan normal-btn" onclick="startServiceTip(' + appointmentId + ')">立即开单</div>';
            }
            at.find("li").last().html(content);
        }
    });
}

function startService(){
    $.ajax({
        url : baseUrl + "staff/action/startAppoint",
        type : "POST",
        data : "appointmentId=" + appointmentId,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("开单成功");
            window.location.href = baseUrl + "staff/view/selectOrderDetail/${session_key_store_id}/2?orderId=" + e.msg.orderId;
        }
    });
}
</script>
</body>
</html>