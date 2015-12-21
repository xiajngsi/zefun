<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>选择服务人员</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/swiper.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content">
	<div class="waiting-fix">
		<div class="dingdan-person">
		    <ul class="dingdan-ul no-padding">
		        <li class="dingdan-list">
		            <img src="<%=picPath%>${project.projectImage}" alt=""/>
		            <div class="list-desc">
		                <div class="list-name">${project.projectName}<span class="list-price">￥${project.projectPrice}</span></div>
		                <div class="dingdan">
		                    <span class="state waiting">修改中</span>
		                </div>
		            </div>
		        </li>
		        <hr/>
		        <li class="fix-person">

		            <div class="lunpai-people">
		            
		                <div class="lunpai-content" style="padding:0rem">
		                    <div class="lunpai-kind-project">
		                        <ul class="kind-project-ul">
		                            <c:forEach items="${shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="shiftStatus">
		                                <c:choose>
		                                    <c:when test="${shiftStatus.index == 0}">
		                                        <li class="item active" onclick="switchoverDIV(this, ${shiftStatus.index})">${shiftMahjongDto.shiftMahjongName}</li>
		                                    </c:when>
		                                    <c:otherwise>
		                                        <li class="item" onclick="switchoverDIV(this, ${shiftStatus.index})">${shiftMahjongDto.shiftMahjongName}</li>
		                                    </c:otherwise>
		                                </c:choose>
		                            </c:forEach>
		                        </ul>
		                    </div>
			                 <c:forEach items="${shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="shiftStatus">
			                   <div <c:if test="${shiftStatus.index == 0}">class="lunpai-people"</c:if> <c:if test="${shiftStatus.index != 0}">class="lunpai-people hide"</c:if> name= "${shiftStatus.index}">
			                        <div class="swiper-container-daohang lunpai lunpaiHead${shiftStatus.index }" size="${fn:length(shiftMahjongDto.shiftMahjongEmployeeList)}">
			                            <div class="swiper-wrapper lunpaiBody${shiftStatus.index }">
			                               <c:forEach items="${shiftMahjongDto.shiftMahjongEmployeeList}" var="shiftMahjongEmployee" varStatus="status">
			                                  <div class="swiper-slide score-shop-li line-height-center" onclick="chooseEmployee(this)">
			                                        <div class="lunpai-img" name= "${shiftMahjongEmployee.shiftMahjongEmployeeId}" projectNum = "${listStatus.index}" orderNum = "${shiftStatus.index}" projectId = "${project.projectId}" employeeId = "${shiftMahjongEmployee.employeesId}">
			                                            <img src="<%=picPath%>${shiftMahjongEmployee.headImage}?imageView2/1/w/480/h/400"/>
			                                            <div class="lunpai-yg-name">${shiftMahjongEmployee.employeeCode} ${shiftMahjongEmployee.name}</div>
			                                        </div>
			                                        <div class="lunpai-state" name= "${shiftMahjongEmployee.state}">
			                                            <div class="lunpai-people-state">
			                                                <div class="center kongxianzhong">
			                                                    <div class="zhuangtai">
			                                                        <span class="iconfont icon-iconkafei"></span>
			                                                    </div>
			                                                    <div class="zhuangtai-word"></div>
			                                                    <c:if test="${shiftMahjongEmployee.appointNumber != 0}">
		                                                            <div class="select-word-wrap hide">
		                                                                <span>指定+${shiftMahjongEmployee.appointNumber }</span>
		                                                            </div>   
		                                                        </c:if>
			                                                    <div class="clearfix"></div>
			                                                </div>
			                                            </div>
			                                            <div class="clearfix"></div>
			                                        </div>
			                                   </div>
			                               </c:forEach>
			                            </div>
			                        </div>
			                    </div>
			                 </c:forEach>
			            </div>
		            </div>
		        </li>
		    </ul>
		</div>
		
		<div class="footer-queren">
		    <div class="foot-btn fr mt2 mr2" onclick="submits()">确定</div>
		</div>
	</div>
	
	<!--确认兑换-->
	<div class="s-modal hide s-modal-miss" id="confirmWindow">
	    <div class="s-modal-wrap">
	        <div class="d-member-info">
	            <div class="n-modal-title text-center">
	                <span>确认更换指定人员</span>
	                <div class="clearfix"></div>
	            </div>
	            <div class="s-modal-foot">
	                <div class="modal-cancel" onclick="recover()">取消</div>
	                <div class="modal-confirm" onclick="trueSubmits();">确认</div>
	            </div>
	        </div>
	    </div>
	</div>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript">

var projectInfo = '${projectInfo}';
var detailId = '${detailId}';

var stateList = new Array();
var a1 = {"state":"0","name":"工作中","styles":"gongzuozhong","icon":"icon-scissors"};
var a2 = {"state":"1","name":"空闲中","styles":"kongxianzhong","icon":"icon-iconkafei"};
var a3 = {"state":"2","name":"暂时离开","styles":"zanshilikai","icon":"icon-bbgshizhong"};
var a4 = {"state":"3","name":"离开","styles":"likai","icon":"icon-ico-date"};
var a5 = {"state":"4","name":"指定服务","styles":"zhidingfuwu","icon":"icon-zhiding"};

stateList[0] = a1;
stateList[1] = a2;
stateList[2] = a3;
stateList[3] = a4;
stateList[4] = a5;

var sArr = new Array();
$(function () {
	var swiperList = jQuery(".swiper-container-daohang");
/* 	var clientWidth = $(window).width() - 16;
    var number;
    if($.browser.versions.android){
          number = clientWidth / 128;
    }else if($.browser.versions.ios){
    	number = clientWidth / 144;
    } */
    for (var i = 0; i < swiperList.length; i++) {
        var s = new Swiper(".lunpaiHead" + i, {
            slidesPerView: 'auto',
            paginationClickable: true,
            spaceBetween: 0,
            freeMode: true
        });
        sArr[i] = s;
    }

    $(".s-modal-control").on("click", function(){
        $("body").css("overflow","hidden");
    });

    $(".s-modal-miss").on("click", function () {
        if(!$(".s-gouwuche-modal").hasClass("hide")) {
            $("body").css("overflow-y", "auto");
        }
        $(".s-gouwuche-modal").addClass("hide");
    })
    //渲染员工状态
    var stateObj = $(".lunpai-state");
    for (var i = 0; i < stateObj.length; i++) {
    	var num = $(stateObj[i]).attr("name");
    	
    	for (var k = 0; k < stateList.length; k++) {
    		if(stateList[k].state == num){
    			$(stateObj[i]).find(".center").removeClass("kongxianzhong");
    			$(stateObj[i]).find(".center").addClass(stateList[k].styles);
    			$(stateObj[i]).find(".iconfont").addClass("icon-iconkafei");
    			$(stateObj[i]).find(".iconfont").addClass(stateList[k].icon);
    			$(stateObj[i]).find(".zhuangtai-word").text("");
    			$(stateObj[i]).find(".zhuangtai-word").text(stateList[k].name);
    		}
    	}
    }
})

$("#gouwucheInfo").click(function(){
	if ($("#yg-gouwuche-modal").hasClass("hide")) {
		$("#yg-gouwuche-modal").removeClass("hide");
	}
	else {
		$("#yg-gouwuche-modal").addClass("hide");
	}
});

//点击轮牌名称更换轮牌信息
function switchoverDIV(obj, num){
	$(obj).parents(".kind-project-ul").find(".item").removeClass("active");
	$(obj).addClass("active");
	$(obj).parents(".lunpai-content").find(".lunpai-people").addClass("hide");
	$(obj).parents(".lunpai-content").find(".lunpai-people[name='"+num+"']").removeClass("hide");
	sArr[num].onResize();
}

//选中员工
function chooseEmployee(obj) {
	if (!$(obj).find(".lunpai-img").hasClass("active")) {
		$(obj).parents(".swiper-wrapper").find(".lunpai-img").removeClass("active");
    	$(obj).parents(".swiper-wrapper").find(".lunpai-select").remove();
    	$(obj).find(".lunpai-img").addClass("active");
    	$(obj).find(".lunpai-img").after("<div class='lunpai-select'>"+
								                "<img src='"+baseUrl+"images/mobile/employee/lunpai/lunpai-select.png' alt=''/>"+
								         "</div>");
	}
	else {
		$(obj).parents(".swiper-wrapper").find(".lunpai-img").removeClass("active");
    	$(obj).parents(".swiper-wrapper").find(".lunpai-select").remove();
	}
}

function submits(){
	var arrayObj = new Array();
	var obj = $(".lunpai-img.active");
	for (var i =0; i < obj.length; i++) {
		var projectId = $(obj[i]).attr("projectId");
		var orderNum = $(obj[i]).attr("orderNum");
		var employeeId = $(obj[i]).attr("employeeId");
		var shiftMahjongEmployeeId = $(obj[i]).attr("name");
		var employeeStr = {"shiftMahjongEmployeeId":shiftMahjongEmployeeId, "orderNum":orderNum, "projectId":projectId, "employeeId":employeeId}
		arrayObj.push(employeeStr);
	}
	var employeeObj = JSON.stringify(arrayObj);
	
	$.ajax({
		type : "post",
		url : baseUrl + "staff/action/updateSaveDetail",
		data : "detailId="+detailId+"&projectInfo="+projectInfo+"&employeeObj="+employeeObj,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("修改项目成功, 1.5秒后跳转至接待中心...");
			setTimeout(function(){
				window.location.href = baseUrl + "staff/view/reception";
			}, 1500);
		}
	});
}
</script>
</body>
</html>
