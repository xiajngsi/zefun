<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>更换服务人员</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/swiper.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content mb-footer">
	<div class="waiting-fix">
		<div class="dingdan-person">
		    <ul class="dingdan-ul no-padding">
		        <li class="dingdan-title pl1">
		            <div class="dis-ib s-modal-control" id="#yg-member-info" onclick="memberInfo('${memberBaseDto.name}')">
		                <img src="<%=picPath%>${memberBaseDto.headUrl}" alt=""/>
		                <span class="name">${memberBaseDto.name}</span>
		                <span class="shenfen">${memberBaseDto.levelName}</span>
		            </div>
		        </li>
		        <li class="dingdan-list">
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
		        <li class="dingdan-title">
		            <div class="dingdan"><span class = "font-666">下一服务步骤：</span>${shiftMahjong.shiftMahjongName}</div>
		        </li>
		        
		        <div class="clearfix"></div>
		        
		        <li class="fix-person">
		            <div class="lunpai-people">
		                <div class="swiper-container-daohang lunpai">
		                    <div class="swiper-wrapper">
		                    
		                         <c:forEach items="${shiftMahjongEmployeeList}" var="shiftMahjongEmployee" varStatus="status">
		                              <div class="swiper-slide score-shop-li line-height-center s-modal-control" data-target="#yg-rotating" onclick="chooseEmployee(this)">
			                                <div class="lunpai-img" name= "${shiftMahjongEmployee.shiftMahjongEmployeeId}" isType = "0">
			                                    <img src="<%=picPath%>${shiftMahjongEmployee.headImage}?imageView2/1/w/220/h/220" width="236" height="196" alt="" class=""/>
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
	                <span>确认更换人员</span>
	                <div class="clearfix"></div>
	            </div>
	            <div class="s-modal-foot">
	                <div class="modal-cancel" onclick="recover()">取消</div>
	                <div class="modal-confirm" onclick="trueSubmits();">确认</div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!--会员信息模态框-->
	<!--会员信息-->
	<div class="s-modal hide s-modal-miss" id="yg-member-info" style="z-index:99999999">
	    <div class="s-modal-wrap">
	        <div class="d-member-info">
	            <div class="n-modal-title">
	                <img src="" alt=""/>
	                <div class="information">
	                    <div><span class="name"></span> <span class="sex"></span></div>
	                    <div><span class="shop-name"></span> <span class="shenfen"></span></div>
	                </div>
	                <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
	                <div class="clearfix"></div>
	            </div>
	            <div class="s-modal-body">
	                <ul class="info-ul">
	                    <li>
	                        <span class="fl item-name">卡金金额</span>
	                        <span class="fr item-content" name= "balanceAmount"></span>
	                    </li>
	                    <li>
	                        <span class="fl item-name">剩余积分</span>
	                        <span class="fr item-content" name= "balanceIntegral"></span>
	                    </li>
	                    <li>
	                        <span class="fl item-name">平均消费频率</span>
	                        <span class="fr item-content" name= "avgConsumeDays"></span>
	                    </li>
	                    <li>
	                        <span class="fl item-name">平均消费</span>
	                        <span class="fr item-content" name= "avgConsumeAmount"></span>
	                    </li>
	                    <li>
	                        <span class="fl item-name">上次来店</span>
	                        <span class="fr item-content" name= "lastConsumeTime"></span>
	                    </li>
	                    <li>
	                        <span class="fl item-name">礼金余额</span>
	                        <span class="fr item-content" name= "giftmoneyAmount"></span>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>
	
	<div class="hide s-modal-miss no-padding s-gouwuche-modal" id="yg-rotating">
	    <div class="yg-state yg-rotating">
	        <ul id = "rotatingStateUl">
	            <li onclick="changeAssignState(1)">
	                <div class="state-item zhiding">
	                    <div >指定</div>
	                </div>
	            </li>
	            <li onclick="changeAssignState(2)">
	                <div class="state-item zhipai">
	                    <div>指派</div>
	                </div>
	            </li>
	        </ul>
	    </div>
    </div>

</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript">
	var clientHeight = $(window).height();
	var mH = clientHeight/2 -60;
	$(".yg-state").css("margin-top",mH);
    
    var shiftMahjongStepId = '${shiftMahjongStepId}';
    var type = '${type}';
    var detailId = '${detailId}';
    var shiftMahjongId = '${shiftMahjongId}';
    
    var shiftMahjongEmployeeIdOld = '${shiftMahjongEmployeeId}';
    
    var isType = '${isType}';
    
    var memberBaseDtoStr = '${memberBaseDtoStr}';
    var memberBaseDto = eval("(" + memberBaseDtoStr + ")");
    
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
        /*手机端导航滑动*/
/*         var clientWidth = $(window).width() - 16;
        var number;
	    if($.browser.versions.android){
	          number = clientWidth / 128;
	    }else if($.browser.versions.ios){
	    	number = clientWidth / 144;
	    } */
        var swiper = new Swiper('.swiper-container-daohang', {
            slidesPerView: 'auto',
            paginationClickable: true,
            spaceBetween: 0,
            freeMode: true
        });
        
        $(".s-modal-control").on("click", function(){
            $("body").css("overflow","hidden");
        });
        
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
        
        assembleShow($(".lunpai-img[name='"+shiftMahjongEmployeeIdOld+"']"), isType);
        
        
    })
    
    $(".s-modal-miss").on("click", function () {
      if(!$(".s-gouwuche-modal").hasClass("hide")) {
        $("body").css("overflow-y", "auto");
      }
      $(".s-gouwuche-modal").addClass("hide");
    })
    
    
    function assembleShow (obj, objIsType) {
    	
    	if (objIsType != 0) {
    		
    		var state = $(chooseObj).find(".lunpai-state").attr("name");
        	if (state == 3 || state == 2) {
        		dialog("员工为离开状态，可能无法及时提供服务！");
        	}
    		
    		$(obj).addClass("active");
            $(obj).attr("isType", objIsType);
            
            var str = null;
            
            if (objIsType == 1) {
            	str = "<div class='lunpai-select'>"+
    			        "<img src='"+baseUrl+"images/mobile/employee/lunpai/lunpai-select-zhiding.png' alt=''/>"+
    				  "</div>";
            }
            
            if (objIsType == 2) {
            	str = "<div class='lunpai-select'>"+
    			        "<img src='"+baseUrl+"images/mobile/employee/lunpai/lunpai-select-zhipai.png' alt=''/>"+
    				  "</div>";
            }
            
            if (objIsType == 3) {
            	str = "<div class='lunpai-select'>"+
    			        "<img src='"+baseUrl+"images/mobile/employee/lunpai/lunpai-select-lunpai.png' alt=''/>"+
    				  "</div>";
            }
            
            $(obj).after(str);
    	}
    	
    }
    
    var chooseObj = null;
        
    //选中员工
    function chooseEmployee(obj) {
    	var shiftMahjongEmployeeId = $(obj).find(".lunpai-img").attr("name");
    	var objIsType = $(obj).find(".lunpai-img").attr("isType");
    	$("#rotatingStateUl").empty();
        if (shiftMahjongEmployeeId == shiftMahjongEmployeeIdOld) {
        	if (objIsType == 0) {
        		if (isType == 3) {
        			$("#rotatingStateUl").append("<li onclick='changeAssignState(3)'>"+
											    	    "<div class='state-item lunpai'>"+
											                "<div >轮牌</div>"+
											            "</div>"+
											     "</li>");
        		}
        		else if (isType == 2){
        			$("#rotatingStateUl").append("<li onclick='changeAssignState(2)'>"+
											    	    "<div class='state-item zhipai'>"+
											                "<div >指派</div>"+
											            "</div>"+
											     "</li>");
        		}
        		else {
        			$("#rotatingStateUl").append("<li onclick='changeAssignState(1)'>"+
											    	    "<div class='state-item zhiding'>"+
											                "<div >指定</div>"+
											            "</div>"+
											     "</li>");
        		}
        	}
        	else {
        		$("#rotatingStateUl").append("<li onclick='changeAssignState(0)'>"+
										    	    "<div class='state-item likai'>"+
										                "<div >取消</div>"+
										            "</div>"+
										     "</li>");
        	}
    		
    	}
        else {
        	if (objIsType == 0) {
        		$("#rotatingStateUl").append("<li onclick='changeAssignState(1)'>"+
										    	    "<div class='state-item zhiding'>"+
										                "<div >指定</div>"+
										            "</div>"+
										     "</li>"+
										     "<li onclick='changeAssignState(2)'>"+
										    	    "<div class='state-item zhipai'>"+
										                "<div>指派</div>"+
										            "</div>"+
										     "</li>");
        	}
        	else {
        		$("#rotatingStateUl").append("<li onclick='changeAssignState(0)'>"+
										    	    "<div class='state-item likai'>"+
										                "<div >取消</div>"+
										            "</div>"+
										     "</li>");
        	}
        }
    	chooseObj = obj;
    }
    
    function changeAssignState(objIsType) {
    	$(chooseObj).parents(".swiper-wrapper").find(".lunpai-img").removeClass("active");
    	$(chooseObj).parents(".swiper-wrapper").find(".lunpai-img").attr("isType", 0);
    	$(chooseObj).parents(".swiper-wrapper").find(".lunpai-select").remove();
    	
    	assembleShow($(chooseObj).find(".lunpai-img"), objIsType);
    	
    }
    
    
    function recover() {
    	$(".lunpai-img[name='"+shiftMahjongEmployeeIdOld+"']").parent().click();
    }
    
    function memberInfo(name){
    	if (name == "散客") {
    		return;
    	}
		$("#yg-member-info").find("img").attr("src", picUrl + memberBaseDto.headUrl + "?imageView2/1/w/220/h/220");
		$("#yg-member-info").find(".name").text(memberBaseDto.name);
		$("#yg-member-info").find(".sex").text(memberBaseDto.sex);
		$("#yg-member-info").find(".shop-name").text(memberBaseDto.storeName);
		$("#yg-member-info").find(".shenfen").text(memberBaseDto.levelName);
		$("#yg-member-info").find("span[name= 'balanceAmount']").text(memberBaseDto.balanceAmount);
		$("#yg-member-info").find("span[name= 'balanceIntegral']").text(memberBaseDto.balanceIntegral);
		$("#yg-member-info").find("span[name= 'avgConsumeDays']").text(memberBaseDto.avgConsumeDays);
		$("#yg-member-info").find("span[name= 'avgConsumeAmount']").text(memberBaseDto.avgConsumeAmount);
		$("#yg-member-info").find("span[name= 'lastConsumeTime']").text(memberBaseDto.lastConsumeTime);
		$("#yg-member-info").find("span[name= 'giftmoneyAmount']").text(memberBaseDto.giftmoneyAmount);
		$("#yg-member-info").removeClass("hide");

    }
    
    function submits(){
    	var shiftMahjongEmployeeId = $(".lunpai-img.active").attr("name");
    	var newIsType = $(".lunpai-img.active").attr("isType");
    	if (isEmpty(shiftMahjongEmployeeId)) {
    		shiftMahjongEmployeeId = "";
    	}
    	if (isEmpty(shiftMahjongEmployeeIdOld)) {
    		shiftMahjongEmployeeIdOld = "";
    	}

    	if (shiftMahjongEmployeeIdOld != shiftMahjongEmployeeId) {
    		$("#confirmWindow").removeClass("hide");
    		return;
    	}
    	if (type == 1) {
    		dialog("员工未做更换，请更换/回退");
    		return;
    	}
    	trueSubmits();
    }
    
    function trueSubmits() {
    	var shiftMahjongEmployeeId = $(".lunpai-img.active").attr("name");
    	var newIsType = $(".lunpai-img.active").attr("isType");
    	if (isEmpty(shiftMahjongEmployeeId)) {
    		shiftMahjongEmployeeId = "";
    	}
    	if (isEmpty(newIsType)) {
    		newIsType = 0;
    	}
    	$.ajax({
			type : "post",  
			url : baseUrl + "staff/action/serverAssociateShiftMahjong",
			data : "shiftMahjongStepId="+shiftMahjongStepId+"&type="+type+"&detailId="+detailId+"&shiftMahjongEmployeeId="+shiftMahjongEmployeeId+"&shiftMahjongEmployeeIdOld="+shiftMahjongEmployeeIdOld+"&shiftMahjongId="+shiftMahjongId+"&isType="+newIsType,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				dialog("移交成功, 1.5秒后跳转至接待中心...");
				setTimeout(function(){
					window.location.href = baseUrl + "staff/view/reception";
				}, 1500);
				
			}
		});
    }
</script>
</body>
</html>
