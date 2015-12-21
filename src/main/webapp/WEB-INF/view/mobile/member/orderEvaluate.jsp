<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>服务评价</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/star-font.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/star-rating.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
	<div class="service-evaluation mb-footer" >
       <c:forEach items="${orderInfo.orderDetailList }" var="detail">
          <c:if test="${detail.orderType == 1}">
	          <div class="order-part">
	              <ul class="order-ul">
	                  <li class="fuwu-list">
	                      <img src="<%=picPath %>${detail.projectImage }?imageView2/1/w/122/h/122">
	                      <div class="list-desc">
	                          <div class="verticle-middle">
	                            <div class="list-name">${detail.projectName } </div>
	                            <%-- <div class="dingdan">
	                                <span class="font-666 text-through">门店价格: <span class="">¥${detail.projectPrice }</span></span>
	                            </div> --%>
	                            <div class="dingdan">
	                                <input detailId="${detail.detailId }" projectId="${detail.projectId }" type="number" name="projectEvaluate" class="rating input-id mt0" data-size="md" value="5" >
	                            </div>
	                          </div>
	                      </div>
	                      <%-- <div class="dingdan-edit fr s-modal-control" data-target="#yg-fix-p-select">
	                          <span class="list-price">￥${detail.realPrice }</span>
	                      </div> --%>
	                  </li>
	              </ul>
	              <ul>
	                  <li class="please-pingjia">
	                      <span class=" font-size-24">为了更好地为您服务，请给服务人员打分。</span>
	                  </li>
	              </ul>
	    
	              <div class="se-content">
	                  <c:forEach items="${detail.stepList }" var="step">
	                      <div class="score">
	                          <img src="<%=picPath %>${step.employeeInfo.headImage }?imageView2/1/w/90/h/68" alt=""/>
	                          <div class="fuwu-p">
	                              <div class="name">${step.employeeInfo.name }</div>
	                              <div class="zhiwei">${step.employeeInfo.levelName }</div>
	                          </div>
	                          <input employeeId="${step.employeeInfo.employeeId }" detailId="${detail.detailId }" type="number" name="employeeEvaluate" class="rating input-id mt0" data-size="md" value="5" >
	                      </div>
	                  </c:forEach>
	                  <div class="evalua-content mt2">
		                  <textarea id="content_${detail.detailId }" cols="30" rows="2" placeholder="评价内容可以使10-500个字，写下您的服务体验，我们将根据您的体验来提高和改善我们的服务"></textarea>
		              </div>
	              </div>
	          </div>
	       </c:if>
       </c:forEach>
    </div>
    
    <div class="s-modal hide s-modal-miss" id="evaluateSuccessTip">
        <div class="s-modal-wrap">
            <div class="d-member-info">
                <div class="n-modal-title text-center">
                    <span>评价成功</span>
                    <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
                    <div class="clearfix"></div>
                </div>
                <div class="s-modal-body">
                    <div class="word text-left">
                        谢谢您的评价，期待您的再次到来
                    </div>
                </div>
                <div class="s-modal-foot">
                    <div class="modal-confirm" onclick="backHome();">返回主页</div>
                </div>
            </div>
        </div>
    </div>
	
	<div class="foot-confirm">
	    <a href="javascript:evaluate();" class="foot-confirm-btn">提交评价</a>
	</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/star-rating.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript">
$(".rating").rating({showCaption : false, step : 1});

var orderId = "${orderInfo.orderId }";
var data = 
{
   "orderId" : orderId
};

function backHome(){
    window.location.href = baseUrl + "memberCenter/view/home/${session_key_store_id}/1";
}

function evaluate(){
	var curTime = getNowFormatDate();

	var employeeEvaluateList = [];
	var employeeList = $('[name="employeeEvaluate"]');
	for (var i = 0; i < employeeList.length; i++) {
		var rating = $(employeeList[i]);
		var evaluate = {"employeeId" : rating.attr("employeeId"), "detailId" : rating.attr("detailId"), "evaluateRate" : rating.val(), "createTime" : curTime};
		employeeEvaluateList.push(evaluate);
	}
	
	var projectEvaluateList = [];
    var projectList = $('[name="projectEvaluate"]');
    for (var i = 0; i < projectList.length; i++) {
        var rating = $(projectList[i]);
        var detailId = rating.attr("detailId");
        var evaluate = {"orderId" : orderId, "detailId" : detailId, "projectId": rating.attr("projectId"), "evaluateRate" : rating.val(), "time" : curTime, "comment" : $("#content_" + detailId).val()};
        projectEvaluateList.push(evaluate);
    }
    
	data["emloyeeList"] = employeeEvaluateList;
	data["projectList"] = projectEvaluateList;
	$.ajax({
		url : baseUrl + "memberCenter/action/orderEvaluate",
		type : "POST",
		contentType: "application/json",
        data: JSON.stringify(data),
        dataType: "json",
		success : function(e){
			if (e.code != 0) {
                dialog(e.msg);
                return;
            }
			$("#evaluateSuccessTip").removeClass("hide");
		}
	});
}
</script>
</body>
</html>