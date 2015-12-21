<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>修改服务</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
    <style type="text/css">
        .content{
            height:100% !important;
            
        }
    </style>
</head>
<body>
<div class="wrap">
<div class="content">
	<div class="bd-white">
	    <ul class="current-project" style="position: fixed;top: 0rem;width: 100%;">
	        <li class="dengdai-list">
	            <img src="<%=picPath%>${orderDetailDto.projectImage}" alt=""/>
	            <div class="list-desc">
	                <div class="list-name">${orderDetailDto.projectName}<span class="list-price">￥${orderDetailDto.discountAmount}</span></div>
	                <div class="faxingshi">
	                    <span class="zhiwei">${orderDetailDto.stepList[0].shiftMahjongName}</span>
	                    <span class="name">${orderDetailDto.stepList[0].employeeInfo.employeeCode} ${orderDetailDto.stepList[0].employeeInfo.name}</span>
                        <c:if test="${step.isAppoint == 1 }">
                            <span class="state">
                           <span class="iconfont icon-zhiding"></span>
                         </span>
                        </c:if>
	                </div>
	                <div class="dingdan">
	                    <c:if test="${orderDetailDto.stepList[0].isOver == 1 }">
	                        <span class="state doing">服务中</span>
	                    </c:if>
	                    <c:if test="${orderDetailDto.stepList[0].isOver == 2 }">
	                        <span class="state waiting">等待中</span>
	                    </c:if>
	                    <span class="time">${orderDetailDto.stepList[0].beginTime}</span>
	                </div>
	            </div>
	        </li>
	    </ul>
	    <div class="d-change-project" style="padding-top: 10rem;">
	        <div class="d-content">
	        
	            <div class="project-type">
	                <ul class="project-type-ul">
	                    <c:forEach items="${projectCategoryDtoList}" var="projectCategoryDto" varStatus="status">
	                        <li class="type-item j-tab" categoryTitle="${projectCategoryDto.categoryId }" name = "${projectCategoryDto.categoryId}">
		                        <span>${projectCategoryDto.categoryName}</span>
		                    </li>
	                    </c:forEach>
	                </ul>
	            </div>
	
	            <div class="project-list j-tab">
	                <c:forEach items="${projectCategoryDtoList}" var="projectCategoryDto" varStatus="status">
	                   <div class="project-list-part" categoryContent="${projectCategoryDto.categoryId }" name = "${projectCategoryDto.categoryId}">
		                    <div class="list-part-title">
		                        <span>${projectCategoryDto.categoryName}</span>
		                    </div>
		                    <div class="list-item">
		                        <ul>
		                            <c:forEach items="${projectCategoryDto.projectList}" var="projectInfo" varStatus="status">
		                                <li name = "${projectInfo.projectId}" types = "1">
			                                <img src="<%=picPath%>${projectInfo.projectImage}?imageView2/1/w/220/h/220" width="109" height="109" alt=""/>
			                                <div class="item-desc" name="${projectInfo.projectImage}">
			                                    <div class="name">${projectInfo.projectName}</div>
			                                    <div class="d-price">
			                                        <span class="word">价格: </span>
			                                        <span class="num-emphasize">￥${projectInfo.projectPrice}</span>
			                                    </div>
			                                    <div class="origin-price">服务次数:${projectInfo.salesCount}</div>
			                                </div>
			
			                                <div class="change-project-redio">
			                                    <div class="checker">
			                                        <div class="beau-radio">
			                                            <span class="iconfont icon-gou"></span>
			                                        </div>
			                                        <input type="radio" class="yellow-radio" name="xuanze-paiwei"/>
			                                    </div>
			                                </div>
			                            </li>
		                            </c:forEach>
		                        </ul>
		                    </div>
		                </div>
	                </c:forEach>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="footer-queren">
	    <div class="foot-btn fr mt2 mr2"  onclick="submits()">确定</div>
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
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript">

var detailId = '${detailId}';
var projectId = '${projectId}';
$(function () {
	/*给元素定义高度*/
    var contentHeight = function(){
      var height =  document.body.clientHeight;
      var projectUlHeight;
      if($.browser.versions.android){
        projectUlHeight = height - 195 + 53;
      }else if($.browser.versions.ios){
        projectUlHeight = height - 195 + 37;
      }
      $(".project-list").css("height", projectUlHeight);
      $(".project-type-ul").css("height", projectUlHeight);
    }
    
    contentHeight();
    
    $("li[name='"+projectId+"']").find(".beau-radio").addClass("active");
    
    var offset = $(".d-content").offset().top;
    var top = offset;
    
    $(".type-item").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        var sub = $("[categoryContent='" + $(this).attr("categorytitle") + "']");
        var container = sub.parent();
        offset = sub.offset().top - container.offset().top + container.scrollTop();
        container.scrollTop(offset);
    });
});

function recover() {
	$("li[name='"+projectId+"']").find(".beau-radio").addClass("active");
}

function submits(){
   	var activeObj = $(".beau-radio.active");
   	var obj = $(activeObj).parents("li[types = '1']");
   	
   	var projectIdNew = $(obj).attr("name");
    
    if (projectIdNew == projectId) {
    	dialog("项目未做更换，请更换/回退");
    	return;
    }
    trueSubmits();
}

function trueSubmits(){
	var activeObj = $(".beau-radio.active");
	var obj = $(activeObj).parents("li[types = '1']");
	var objStr = {};
	
	var projectId = $(obj).attr("name");
	var projectName = $(obj).find(".name").text();
	var projectPrice = parseFloat(($(obj).find(".num-emphasize").text()).replace("￥", ""));
	var projectImage = $(obj).find(".item-desc").attr("name");
	objStr = {"projectId":projectId,"projectName":projectName,"projectPrice":projectPrice,"projectImage":projectImage};
	
	
	var projectInfo = JSON.stringify(objStr);
	var temp = document.createElement("form");
	temp.action = baseUrl + "staff/view/confirmUpdateProject";
	temp.method = "post";
	temp.style.display = "none";
	var opt = document.createElement("textarea");
	opt.name = "detailId";
	opt.value = detailId;
	var tpt = document.createElement("textarea");
	tpt.name = "projectInfo";
	tpt.value = projectInfo;
	var ppt = document.createElement("textarea");
	ppt.name = "projectId";
	ppt.value = projectId;
	temp.appendChild(opt);
	temp.appendChild(tpt);
	temp.appendChild(ppt);
    document.body.appendChild(temp);
	temp.submit();
}
</script>
</body>
</html>
