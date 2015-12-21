<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>我的预约</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
	<div class="yg-dingdan-all">
	   <c:forEach items="${appointmentList }" var="appointment">
	       <div class="dingdan-person mt2" id="${appointment.appointmentId}">
		        <ul class="order-ul bsw">
		            <li class="dingdan-danhao">
		                <span class="danhao fl">
		                    下单时间: ${appointment.createTime }
		                </span>
		                <span name="status">
		                <c:choose>
                           <c:when test="${appointment.appointmentStatus == 1 }">
                               <span class="fr font-blue fs24">等待确认</span>
                           </c:when>
                           <c:when test="${appointment.appointmentStatus == 2 }">
                               <span class="fr yellow-word fs24">预约成功</span>
                           </c:when>
                           <c:when test="${appointment.appointmentStatus == 3 }">
                               <span class="fr font-666 fs24">已服务</span>
                           </c:when>
                           <c:otherwise>
                               <span class="fr font-666 fs24">已取消</span>
                           </c:otherwise>
                        </c:choose>
                        </span>
		            </li>
		            <li class="dingdan-list">
		                <img src="<%=picPath%>${appointment.projectInfo.projectImage}?imageView2/1/w/220/h/220" alt="">
		                <div class="list-desc">
		                    <div class="list-name projectName">${appointment.projectInfo.projectName}</div>
                            <div class="faxingshi">
                                <span class="zhiwei">${appointment.employeeInfo.employeeCode}&nbsp;${appointment.employeeInfo.name}</span>
                                <span class="name">${appointment.employeeInfo.levelName}</span>
                            </div>
		                    <div class="dingdan">
		                        <span class="font-666">预约时间：<span class="font-blue appointmentTime">${appointment.appointmentDate } ${appointment.appointmentTime }</span></span>
		                    </div>
		                </div>
		                <div class="dingdan-edit fr s-modal-control" data-target="#yg-fix-p-select">
		                    <span class="red-price">￥${appointment.appointmentPrice }</span>
		                </div>
		            </li>
		            <c:choose>
                       <c:when test="${appointment.appointmentStatus < 3}">
                            <li class="cancel-reason cancelBody hide">
                              <div class="reason-item">
                                <div class="checker fr">
                                  <div class="beau-radio">
                                    <span class="iconfont icon-gou"></span>
                                  </div>
                                  <input type="radio" class="yellow-radio" value="重新预约" name="check_reason"/>
                                </div>
                                <span class="ml">重新预约</span>
                              </div>
		                      <div class="reason-item">
				                <div class="checker fr">
				                  <div class="beau-radio">
				                    <span class="iconfont icon-gou"></span>
				                  </div>
				                  <input type="radio" class="yellow-radio" value="临时有事" name="check_reason"/>
				                </div>
				                <span class="ml">临时有事</span>
				              </div>
				              <div class="reason-item">
				                <div class="checker fr">
				                  <div class="beau-radio">
				                    <span class="iconfont icon-gou"></span>
				                  </div>
				                  <input type="radio" class="yellow-radio" value="其他考虑" name="check_reason"/>
				                </div>
				                <span class="ml">其他考虑</span>
				              </div>
		                    </li>
		                    <li class="dingdan-jiezhang cancelBody confirm-cancel hide">
		                        <div class="qiandan active cancel-order">
		                            我点错啦
		                        </div>
		                        <div class="qiandan" >
		                            <a href="javascript:cancel('${appointment.appointmentId}', '${appointment.employeeInfo.employeeId}', '${appointment.projectId }')">确认取消</a>
		                        </div>
		                    </li>
		                    <li name="operate" class="dingdan-jiezhang confirm-order">
		                        <div class="qiandan cancel-order">
		                            取消预约
		                        </div>
		                    </li>
                       </c:when>
                       <c:otherwise>
                            <li name="operate" class="dingdan-jiezhang">
                                <div class="qiandan active">
                                    <a href="<%=basePath %>memberCenter/view/projectDetail?projectId=${appointment.projectId }">再次预约</a>
                                </div>
                           </li>
                       </c:otherwise>
                    </c:choose>
		        </ul>
		    </div>
	   </c:forEach>
	</div>
	
	<!--预约提醒-->
    <div class="s-modal hide s-modal-miss" id="appointmentTip">
        <div class="s-modal-wrap">
            <div class="d-member-info">
                <div class="n-modal-title text-center">
                    <span>预约取消成功</span>
                    <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
                    <div class="clearfix"></div>
                </div>
                <div class="s-modal-body">
                    <div class="word text-left">
                        您还可以预约其他时间，点击下方进行操作
                    </div>
                </div>
                <div class="s-modal-foot">
                    <div class="modal-cancel">暂不预约</div>
                    <div class="modal-confirm" onclick="orderAppointment();">前往预约</div>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${empty appointmentList }">
	    <a href="<%=basePath%>memberCenter/view/orderAppointment/${session_key_store_id}/1">
           <div class="kongjilv">
                <div class="center">
                    <div class="iconfont icon-xingzhuang14"></div>
                    <div>您暂无预约单，立即预约享受更多优惠</div>
                </div>
            </div>
        </a>
	</c:if>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript">
    $(function(){
   	  $(".confirm-order .cancel-order").on("click", function () {
   		$(this).parent().addClass("hide").prev().removeClass("hide").prev().removeClass("hide");
   		$("[name='check_reason']:checked").removeAttr("checked").siblings(".beau-radio").removeClass("active");
   	  })
   	  $(".confirm-cancel .cancel-order").on("click", function () {
        $(this).parent().addClass("hide").prev().addClass("hide").next().next().removeClass("hide");
      })
    });
    
    function cancel(appointmentId, employeeId, projectId){
    	var at = $("#" + appointmentId);
    	var appointmentTime = at.find(".appointmentTime:first").html();
    	var projectName = at.find(".projectName:first").html();
    	var reason = $("[name='check_reason']:checked").val();
    	if (isEmpty(reason)) {
    		dialog("请选择取消原因");
    		return;
    	}
    	var data = "appointmentId=" + appointmentId + "&employeeId=" + employeeId + "&projectName=" + 
        projectName + "&appointmentTime=" + appointmentTime + "&reason=" + reason;
    	$.ajax({
    		url : baseUrl + "memberCenter/view/cancelAppointment",
    		type : "POST",
    		data : data,
    		success : function(e){
    			if (e.code != 0) {
                    dialog(e.msg);
                    return;
                }
    			$("#appointmentTip").removeClass("hide");
    			at.find(".cancelBody").addClass("hide");
    			at.find("[name='operate']").removeClass("hide");
    			at.find("[name='status']").html('<span class="fr font-666 fs24">已取消</span>');
    			at.find("[name='operate']").html('<div class="qiandan active"><a href="' + baseUrl + 'memberCenter/view/projectDetail?projectId=' + projectId + '">再次预约</a></div>');
    		}
    	});
    }
    
    function orderAppointment(){
    	window.location.href = "<%=basePath%>memberCenter/view/orderAppointment/${session_key_store_id}/1";
    }
</script>
</body>
</html>