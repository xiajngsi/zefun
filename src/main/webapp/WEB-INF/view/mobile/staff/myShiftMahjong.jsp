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
    <title>我的轮牌</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/swiper.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content">
 <div class="bd-gray mb-footer">
  <c:forEach items="${shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="listStatus">
      <div class="lunpai-project">
	    <div class="lunpai-content">
	      <div class="lunpai-kind-project">
	        <ul class="all-rotating-ul">
	          <li class="item">
	            <span>${shiftMahjongDto.shiftMahjongName}</span>
	          </li>
	        </ul>
	      </div>
	      <div class="lunpai-people">
	        <div class="swiper-container-daohang lunpai lunpaiHead${listStatus.index}" size="${fn:length(shiftMahjongDto.shiftMahjongEmployeeList)}">
	          <div class="swiper-wrapper">
	            <c:forEach items="${shiftMahjongDto.shiftMahjongEmployeeList}" var="shiftMahjongEmployee" varStatus="shiftStatus">
		            <div class="swiper-slide score-shop-li line-height-center">
		              <div class="lunpai-img">
		                <img src="<%=picPath%>${shiftMahjongEmployee.headImage}" alt="" class=""/>
		                <div class="lunpai-yg-name">${shiftMahjongEmployee.employeeCode} ${shiftMahjongEmployee.name}</div>
		              </div>
		              <div class="lunpai-state" name= "${shiftMahjongEmployee.state}">
	                      <div class="lunpai-people-state">
	                          <div class="center kongxianzhong">
	                              <div class="zhuangtai">
	                                  <span class="iconfont icon-iconkafei"></span>
	                              </div>
	                              <div class="zhuangtai-word"></div>
	                              <div class="select-word-wrap">
	                            <span><c:if test="${shiftMahjongEmployee.appointNumber == 0}">未指定</c:if><c:if test="${shiftMahjongEmployee.appointNumber != 0}">指定+${shiftMahjongEmployee.appointNumber}</c:if></span>
	                        </div>
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
	    </div><!--lunpai-content-->
	  </div>
  </c:forEach>
     
</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script>	
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

	$(function (){
		
		var swiperList = $(".swiper-container-daohang");
/* 	    var clientWidth = $(window).width() - 16;
	    var number;
	    if($.browser.versions.android){
	          number = clientWidth / 128;
	    }else if($.browser.versions.ios){
	    	number = clientWidth / 144;
	    } */
	    var sArr = new Array();
	    for (var i = 0; i < swiperList.length; i++) {
            var s = new Swiper(".lunpaiHead" + i, {
                slidesPerView: 'auto',
                paginationClickable: true,
                spaceBetween: 0,
                freeMode: true
            });
            sArr[i] = s;
            sArr[i].onResize();
        } 
	    
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
	    
	    /*判断浏览器类型*/
	    var browser={
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
</script>
</div>
</body>
</html>