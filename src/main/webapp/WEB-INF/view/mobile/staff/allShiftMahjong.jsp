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
    <title>全部轮牌</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/swiper.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content">
 <div class="bd-gray">
   <c:if test="${typeNumber >= 2 }">
        <div class="swiper-container-top tab" style="height:6.25rem;">
	        <div class="swiper-wrapper" style="background-image:none;">
	           <c:forEach items="${deptList }" var="dept">
		          <div class="swiper-slide score-shop-li" style="background-image:none;box-shadow:none;" onclick="updateDept(this, '${dept.deptId }')">
		            <img src="<%=basePath%>/images/mobile/employee/active-new.png" class="hide"/>
		            <div class="tab-word">
		             ${dept.deptName }
		            </div>
		          </div>
	          </c:forEach>
	        </div>
	    </div>
	    <div class="clearfix"></div>
   </c:if>
   <c:if test="${typeNumber == 1 }">
      <c:set var="oneClass" value="mt1r" />
   </c:if>
  <div id="lunpaiDIV">
  </div>
</div>
<div class="hide s-modal-miss no-padding s-gouwuche-modal" id="yg-state">
    <div class="yg-state">
      <div class="state-item coffee fl" onclick="changeState(1)">
        <img src="<%=basePath%>images/mobile/employee/kongxianzhong.png" alt=""/>
        <div class="word">空闲</div>
      </div>
      <div class="state-item naozhong" onclick="changeState(3)">
        <img src="<%=basePath%>images/mobile/employee/likai.png" alt=""/>
        <div class="word">离开</div>
      </div>
      <div class="state-item jishiqi fr" onclick="changeState(2)">
        <img src="<%=basePath%>images/mobile/employee/zanshilikai.png" alt=""/>
        <div class="word">暂时离开</div>
      </div>
    </div>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script>

	var deptId = "${deptId}";
	
	var clientHeight = $(window).height();
	var mH = clientHeight/2 -60;
	$(".yg-state").css("margin-top",mH);
	
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

	//全局修改状态点击对象
	var overallObj = "";

	//全局修改状态轮牌员工标识
	var shiftMahjongEmployeeId = "";
	//全局原状态
	var oldState = "";
	$(function (){
		updatePage();
		
		calculate();
		
		$(".swiper-slide:first").addClass("active").find("img").removeClass("hide");
	    
	    $(".s-modal-control").on("click", function(){
	      $("body").css("overflow","hidden");
	    });
	
	    $(".s-modal-miss").on("click", function () {
	      if(!$(".s-gouwuche-modal").hasClass("hide")) {
	        $("body").css("overflow-y", "auto");
	      }
	      $(".s-gouwuche-modal").addClass("hide");
	    })
	
	    var swiper = new Swiper('.swiper-container-top', {
	      pagination: '.swiper-pagination',
	      slidesPerView: '${typeNumber}',
	      paginationClickable: true,
	      spaceBetween: 0,
	      freeMode: true
	    });
	    
	    /*判断浏览器类型*/
	    var browser = {
	    	versions:function(){
	        var u = navigator.userAgent, app = navigator.appVersion;
	        return {
	          trident: u.indexOf('Trident') > -1, //IE内核
	          presto: u.indexOf('Presto') > -1, //opera内核
	          webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
	          gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
	          mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
	          ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
	          android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
	          iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
	          iPad: u.indexOf('iPad') > -1, //是否iPad
	          webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
	        };
	      }(),
	      language:(navigator.browserLanguage || navigator.language).toLowerCase()
	    }
	
	    /*给元素定义高度*/
	    var contentHeight = function(){
	      var height =  document.body.clientHeight;
	      var projectUlHeight;
	      if(browser.versions.android){
	        projectUlHeight = height - 162 + 53;
	      }else if(browser.versions.ios){
	        projectUlHeight = height - 162 + 44;
	      }
	      $(".project-list").css("height", projectUlHeight);
	      $(".project-type-ul").css("height", projectUlHeight);
	    }
	
	    contentHeight();
	    
	  })
  
	function calculate() {
		/*手机端导航滑动*/
		var swiperList = $(".swiper-container-daohang");
	    /* var clientWidth = $(window).width() - 16;
	    var dpi = window.devicePixelRatio || 1;
	    var number;
	    if(($.browser.versions.ios && dpi == 2) || dpi == 3 || dpi == 1 || dpi == 1.2){
	        number = clientWidth / 144;
	    }
	    else {
	    	number = clientWidth / 128;
	    }
	    var sArr = new Array(); */
	    for (var i = 0; i < swiperList.length; i++) {
            var s = new Swiper(".lunpaiHead" + i, {
                slidesPerView: 'auto',
                paginationClickable: true,
                spaceBetween: 0,
                freeMode: true
            });
        } 
	    
	}
	function updateDept(obj, deptIds){
		var tabObj = jQuery(obj).parent().find(".swiper-slide");
		for (var i = 0; i < tabObj.length; i++) {
			jQuery(tabObj[i]).removeClass("active");
			jQuery(tabObj[i]).find("img").addClass("hide");
		}
		jQuery(obj).addClass("active");
		jQuery(obj).find("img").removeClass("hide");
		deptId = deptIds;
		updatePage();
		calculate();
	}
    //更新轮牌页面（按部门）
	function updatePage(){
		$.ajax({
			type : "post",
			url : baseUrl + "staff/action/selectshiftMahjong",
			data : "deptId="+deptId,
			dataType : "json",
			async:false,//使用同步的Ajax请求
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				var shiftMahjongDtoList = e.msg;
				$("#lunpaiDIV").empty();
				for (var i = 0; i < shiftMahjongDtoList.length; i++) {
					var shiftInfo = shiftMahjongDtoList[i];
	                var lunpaiProject = $("<div class='lunpai-project first-pt7 ${oneClass}'></div>");
	                var lunpaiContent = $("<div class='lunpai-content'></div>");
	                
					lunpaiContent.append("<div class='lunpai-kind-project'>"+
								               "<ul class='all-rotating-ul'>"+
								               "<li class='item'>"+
								               shiftInfo.shiftMahjongName +
								               "</li>"+
								             "</ul>"+
								           "</div><hr>");
					
					var shiftObj = $("<div class='lunpai-people'></div>");
					var daohangObj = $("<div class='swiper-container-daohang lunpai lunpaiHead"+i+"' size='"+shiftMahjongDtoList.length+"'></div>");

					var shiftMahjongEmployeeList = shiftMahjongDtoList[i].shiftMahjongEmployeeList;
					
					var swiperWrapper = $("<div class='swiper-wrapper'></div>");
					
					swiperWrapper = infoDIV(shiftMahjongEmployeeList, swiperWrapper);
					
					daohangObj.append(swiperWrapper);
					shiftObj.append(daohangObj);
					
					lunpaiContent.append(shiftObj);
					
					lunpaiProject.append(lunpaiContent);
					
					jQuery("#lunpaiDIV").append(lunpaiProject);
				}
			}
		});
	}

	//员工排位
	function infoDIV(shiftMahjongEmployeeList, swiperWrapper){
		
		for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
			var shiftMahjongEmployee = shiftMahjongEmployeeList[j];
			var slideObj = jQuery("<div class='swiper-slide score-shop-li line-height-center' onclick = 'openModel(this ,"+shiftMahjongEmployee.shiftMahjongEmployeeId+", "+shiftMahjongEmployee.state+")'></div>");
			
			var imgs = picUrl+shiftMahjongEmployee.headImage + "?imageView2/1/w/220/h/220";
			
			slideObj.append("<div class='lunpai-img'>"+
			                        "<img src='"+imgs+"' alt='' class=''/>"+
			                        "<div class='lunpai-yg-name'>"+shiftMahjongEmployee.employeeCode+" "+ shiftMahjongEmployee.name+"</div>"+
			                "</div>");
			
			for (var k = 0; k < stateList.length; k++) {
				if(stateList[k].state == shiftMahjongEmployee.state){
					var lunpaiState = $("<div class='lunpai-state'></div>");
					var lunpaiPeopleState =$("<div class='lunpai-people-state'></div>");
					var center = $("<div class='center "+stateList[k].styles+" s-modal-control'></div>");
					 center.append("<div class='zhuangtai'>"+
				                      "<span class='iconfont "+stateList[k].icon+"'></span>"+
				                    "</div>"+
				                    "<div class='zhuangtai-word'>"+
				                      ""+stateList[k].name+""+
				                    "</div>");
					if (shiftMahjongEmployee.appointNumber == 0) {
						center.append("<div class='select-word-wrap'>"+
				                         "<span>未指定</span>"+
					                   "</div>"+
					                   "<div class='clearfix'></div>");
					}
					else {
						center.append("<div class='select-word-wrap'>"+
				                         "<span>指定+"+shiftMahjongEmployee.appointNumber+"</span>"+
					                   "</div>"+
					                   "<div class='clearfix'></div>");
					}
					lunpaiPeopleState.append(center);
					lunpaiState.append(lunpaiPeopleState);
					slideObj.append(lunpaiState);
					break;
				}
			}
			
			swiperWrapper.append(slideObj);
		}
		return swiperWrapper;
	}
	
	function openModel(obj, shiftMahjongEmployeeIds, states){
		if (states == 0 || states == 4) {
			dialog("员工忙碌中，不能修改状态！");
			return;
		}
		oldState = states;
		overallObj = $(obj).parents(".swiper-container-daohang").find(".swiper-wrapper");
		shiftMahjongEmployeeId = shiftMahjongEmployeeIds;
		$("#yg-state").removeClass("hide");
	}
	
	function changeState(num) {
		if (oldState == num) {
			return;
		}
		jQuery.ajax({
			type : "post",
			url : baseUrl + "staff/action/updateState",
			data : "shiftMahjongEmployeeId="+shiftMahjongEmployeeId+"&state="+num,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
	            var date = e.msg;
	            
	            $(overallObj).empty();
	            
                infoDIV(date.shiftMahjongEmployeeList, overallObj);
                
                dialog("状态修改成功！");
			}
		});
	}
	
	function renewalShift() {
		jQuery('.slider4').bxSlider({
	        slideWidth: 108,
	        minSlides: 1,
	        maxSlides: 15,
	        moveSlides: 1,
	        slideMargin: 0,
	        pager: false
	    });
	}
</script>
</div>
</body>
</html>