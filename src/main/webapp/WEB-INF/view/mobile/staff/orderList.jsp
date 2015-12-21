<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>今日订单</title>
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
        <div class="yg-dingdan-all">
          <div class="tab t0">
		      <ul>
		        <li <c:if test="${type == 1}">class="score-shop-li  active"</c:if><c:if test="${type != 1}">class="score-shop-li"</c:if> onclick="changeDiv(1, ${orderType})">
		          <a href="javascript:void(0);">
		            <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" <c:if test="${type != 1}">class="hide"</c:if>/>
		            <div class="tab-word">
		              <span>进行中</span>
		            </div>
		          </a>
		          <div <c:if test="${type == 1}">class="border-group hide"</c:if><c:if test="${type != 1}">class="border-group"</c:if>>
		            <div class="one-border"></div>
		            <div class="two-border"></div>
		            <div class="three-border"></div>
		          </div>
		        </li>
		        <li <c:if test="${type == 2}">class="score-shop-li  active"</c:if><c:if test="${type != 2}">class="score-shop-li"</c:if> onclick="changeDiv(2, ${orderType})">
		          <a href="javascript:void(0);">
		            <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" <c:if test="${type != 2}">class="hide"</c:if>/>
		            <div class="tab-word">
		              <span>待结账</span>
		            </div>
		          </a>
		          <div <c:if test="${type == 2}">class="border-group hide"</c:if><c:if test="${type != 2}">class="border-group"</c:if>>
		            <div class="one-border"></div>
		            <div class="two-border"></div>
		            <div class="three-border"></div>
		          </div>
		        </li>
		        <li <c:if test="${type == 3}">class="score-shop-li  active"</c:if><c:if test="${type != 3}">class="score-shop-li"</c:if> onclick="changeDiv(3, ${orderType})">
		          <a href="javascript:void(0);">
		            <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" <c:if test="${type != 3}">class="hide"</c:if>/>
		            <div class="tab-word">
		              <span>已完成</span>
		            </div>
		          </a>
		          <div <c:if test="${type == 3}">class="border-group hide"</c:if><c:if test="${type != 3}">class="border-group"</c:if>>
		            <div class="one-border"></div>
		            <div class="two-border"></div>
		            <div class="three-border"></div>
		          </div>
		        </li>
		      </ul>
		   </div>
		   <div class="clearfix"></div>
		   <div>
	       <c:forEach items="${orderList}" var="order">
              <div class="dingdan-person first-pt7" id="orderId_${order.orderId }">
                <ul class="dingdan-ul" >
                    <li class="dingdan-title">
                        <c:choose>
                            <c:when test="${!empty order.memberInfo.memberId }">
                                  <div class="dis-ib s-modal-control" onclick="memberInfo(${order.memberInfo.memberId})">
                                    <img src="<%=picPath %>${order.memberInfo.headUrl}?imageView2/1/w/84/h/84" alt=""/>
                                    <span class="name">${order.memberInfo.name}</span>
                                    <span class="shenfen font-size-20">${order.memberInfo.levelName}</span>
                                  </div>
                            </c:when>
                            <c:otherwise>
                                  <div class="dis-ib s-modal-control">
                                    <c:choose>
                                        <c:when test="${order.sex == '男' }">
                                            <span class="iconfont icon-manuser fs30 ml5"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="iconfont icon-womanuser fs30 ml5"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <span class="name">${order.memberInfo.name}</span>
                                  </div>
                            </c:otherwise>
                        </c:choose>
                        
                        <span name="statusContent">
                        
                        <c:choose>
                            <c:when test="${order.orderStatus == 3 || order.orderStatus == 4}">
		                          <span class="fr font-size-24 yellow-word">已结账</span>
                           </c:when>
                            <c:when test="${order.orderStatus == 5 }">
                                  <span class="fr font-size-24 font-666">已通知买单</span>
	                       </c:when>
	                       <c:when test="${order.orderStatus == 2 }">
	                           <span class="fr font-size-24 yellow-word">待结账</span>
	                       </c:when>
	                       <c:otherwise>
	                              <span class="fr font-size-24 blue-word">进行中</span>
	                       </c:otherwise>
                        </c:choose>
                        </span>
                    </li>
                    <c:forEach items="${order.orderDetailList }" var="orderDetail">
                      <c:if test="${orderDetail.orderType != 1}">
                        <li class="dingdan-list" onclick="selectOrderDetail(${order.orderId});">
                            <img src="<%=picPath %>${orderDetail.projectImage}" alt=""/>
                            <div class="list-desc">
                                <div class="list-name">${orderDetail.projectName } <span class="list-price">￥${orderDetail.discountAmount }</span></div>
                                <c:if test="${orderDetail.orderType == 2 }">
                                       <div class="faxingshi">
                                        </div>
                                        <div class="dingdan">
                                            <span class= "state shop">商品</span>
                                        </div>
                                </c:if>
                                <c:if test="${orderDetail.orderType == 3 }">
                                       <div class="faxingshi">
                                        </div>
                                        <div class="dingdan">
                                            <span class= "state shop">套餐</span>
                                        </div>
                                </c:if>
                            </div>
                            <div class="dingdan-edit fr s-modal-control">
                                <span class="font-999">X</span>
                                <span class="font-999">${orderDetail.projectCount}</span>
                            </div>
                        </li>
                      </c:if>
                      <c:if test="${orderDetail.orderType == 1}">
                        <li class="dingdan-list" onclick="selectOrderDetail(${order.orderId});">
                            <img src="<%=picPath %>${orderDetail.projectImage}" alt=""/>
                            <div class="list-desc">
                                <div class="list-name">${orderDetail.projectName }</div>
                                <c:choose>
                                   <c:when test="${orderDetail.orderStatus == 3 }">
                                       <div class="faxingshi">
                                        </div>
                                        <div class="dingdan">
                                            <span class="state yiwancheng">已完成</span>
                                            <span class="time">${orderDetail.serviceLength}</span><span class="font-999">  分钟</span>
                                            
                                        </div>
                                   </c:when>
                                   <c:otherwise>
                                        <c:set var="step" value="${orderDetail.stepList[0]}" />
                                            <div class="faxingshi">
                                                   <span class="zhiwei">${step.shiftMahjongName}</span>
                                                   <span class="name">${step.employeeInfo.employeeCode} ${step.employeeInfo.name}</span>
                                                    <%-- <c:if test="${step.isAppoint == 1 }">
                                                        <div class="state-wrap">
								                            <span class="state">
								                                <span class="iconfont icon-zhiding"></span>
								                            </span>
								                        </div>
                                                    </c:if> --%>
                                                    <c:choose>
	                                                       <c:when test="${step.isAssign == 1 }">
	                                                           <div class="state-wrap">
                                                                <span class="zhiding-icon">
                                                                                                                                                                                                              指定
                                                                </span>
                                                              </div>
	                                                       </c:when>
	                                                       <c:when test="${step.isDesignate == 1 }">
	                                                           <div class="state-wrap">
                                                                <span class="zhipai-icon">
                                                                                                                                                                                                              指派
                                                                </span>
                                                              </div>
	                                                       </c:when>
	                                                   </c:choose>
                                               </div>
                                               <div class="dingdan">
                                                   <c:choose>
                                                       <c:when test="${step.isOver == 1 }">
                                                           <span class="state doing">服务中</span>
                                                       </c:when>
                                                       <c:otherwise>
                                                           <span class="state waiting">等待中</span>
                                                       </c:otherwise>
                                                   </c:choose>
                                                   <span class="stepTime time">${step.beginTime }</span>
                                               </div>
                                   </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="dingdan-edit fr">
                            <span class="list-price">
                            ￥${orderDetail.discountAmount}
                            </span>
                        </div>
                        </li>
                      </c:if>
                    </c:forEach>
                    <li class="dingdan-danhao">
                        <span  class="danhao fl">
                                                                               单号: ${order.orderCode }
                        </span>
                        <span class="dingdan-price fr">
                                                                                合计: <span class="d-price">￥${order.discountAmount}</span>
                        </span>
                    </li>
                    <c:if test="${order.orderStatus == 2 || order.orderStatus == 1}">
                          <div class="clearfix"></div>
                          <li class="dingdan-jiezhang notify">
                              <c:if test="${order.orderStatus == 2}">
				                  <c:choose>
	                                  <c:when test="${empty orderInfo.memberInfo.memberId }">
	                                                             散客前台结账
	                                  </c:when>
	                                  <c:otherwise>
	                                      <div class="qiandan normal-btn" onclick="notifyPayment(${order.orderId}, this)">
	                                                             通知买单
	                                      </div>
	                                  </c:otherwise>
	                              </c:choose>
                              </c:if>
                              <%-- <c:if test="${orderType == 1}">
                                  <div class="qiandan neg-btn s-modal-control" data-target="#yg-guazhang" onclick="freeClick(${order.orderId})">
				                                                               挂账
					              </div>
                              </c:if> --%>
                              <c:if test="${session_key_role_id != 4 }">
	                              <div class="qiandan neg-btn" onclick="deleteOrderModel(${order.orderId})">
	                                                         取消订单
	                              </div>
                              </c:if>
                          </li>
                    </c:if>
                </ul>
            </div>
          </c:forEach>            
         </div>
          
        </div>
        <c:if test="${type != 3}">
            <div class="today-dingdan-all">
	            <span>今天已完成 <span class="d-red">${statisticsCount}</span>单，收入 <span class="d-red">${statisticsMoney }</span>元</span>
	        </div>
        </c:if>
        
        <!--会员信息模态框-->
        <!--会员信息-->
        <div class="s-modal hide s-modal-miss" id="yg-member-info">
            <div class="s-modal-wrap yg-member-info">
                <div class="d-member-info">
                    <div class="n-modal-title">
                        <img src="" alt=""/>
                        <div class="information">
                            <div><span class="n-name"></span> <span class="sex"></span></div>
                            <div><span class="shop-name"></span> <span class="shenfen"></span></div>
                        </div>
                        <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
                        <div class="clearfix"></div>
                    </div>
                    <div class="s-modal-body no-padding">
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
                                <span class="fl item-name">消费频率</span>
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
        
        <!--删除订单-->
        <div class="s-modal hide s-modal-miss" id="deleteOrderModel">
            <div class="s-modal-wrap">
                <div class="d-member-info">
                    <div class="n-modal-title text-center">
                        <span>是否确认取消该订单？</span>
                        <div class="clearfix"></div>
                    </div>
                    <div class="s-modal-foot">
                        <div class="modal-cancel" onclick="recover()">取消</div>
                        <div class="modal-confirm" onclick="deleteOrderInfo();">确认</div>
                    </div>
                </div>
            </div>
        </div>
            
        <!--挂账-->
		<div class="s-modal-miss no-padding s-modal hide" id="yg-guazhang">
		    <ul class="yijiao-ul bottom-ul clearfix">
		        <li class="bottom-modal-title">
		            <span class="iconfont icon-shebaozhangtao mr2"></span>
		            <span class=" gouwuche-name">挂账</span>
		        </li>
		        <li class="paiwei-li">
		            <label class="paiwei-label">金额</label>
		            <input type="number" name = "debtAmount" class="dingdan-modal-big-length" placeholder="请输入金额"/><span class="iconfont icon-zhifuzongjine ml-5"></span>
		        </li>
		        <li class="paiwei-li">
		            <label class="paiwei-label">备注</label>
		            <input type="text" name = "orderEvaluate" class="dingdan-modal-big-length" placeholder="请输入备注"/>
		        </li>
		        <li class="paiwei-btn">
		            <div class="queren btn" onclick="confirmFree(2)">确定挂账</div>
		        </li>
		    </ul>
		</div>
		
        <c:choose>
	      <c:when test="${empty orderList}">
	          <div class="kongjilv">
	            <div class="center">
	                <div class="iconfont icon-xingzhuang14"></div>
	                <div>现在还没有订单哦，加油吧</div>
	            </div>
	        </div>
	      </c:when>
	      <c:otherwise>
	          <div class="h120" style="background-color: #eee; text-align: center;"><span class="font-999">已显示全部内容</span></div>
	      </c:otherwise>
	    </c:choose>
    </div>
    </div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script>
  var orderListStr = '${orderListStr}';
  var orderList = eval("(" + orderListStr + ")");
  
  var deleteOrderId = null;
  function deleteOrderModel(orderId) {
    $("#deleteOrderModel").removeClass("hide");
    deleteOrderId = orderId;
  }
  
  function deleteOrderInfo() {
	     $("#deleteOrderModel").addClass("hide");
	     $.ajax({
	        type : "post",  
	        url : baseUrl + "staff/view/deleteOrderInfo",
	        data : "orderId="+deleteOrderId,
	        dataType : "json",
	        success : function(e){
	            if(e.code != 0){
	                dialog(e.msg);
	                return;
	            }
	            dialog("取消成功, 正在为您刷新页面...");
	            setTimeout(function(){
	                window.location.href = window.location.href;
	            }, 1500);
	        }
	    });
	 }
  
  var storeId = '${storeId}';
     $(function(){
         $(".bottom-ul", ".fix-p-select").on("click", function (event) {
             event.stopPropagation();
         })
         
         $(".yellow-radio").on("click", function () {
          var radio = $(".yellow-radio");
          for(var i=0;i<radio.length;i++)
          {
              if(radio[i].checked)
              {
                  $(radio[i]).siblings(".beau-radio").addClass("active");
              }else{
                  $(radio[i]).siblings(".beau-radio").removeClass("active");
              }
          }
      });
      
      //为步骤进行时间进行定时事件绑定
      $(".yijiao-ul").click(function (event) {
             event.stopPropagation();
         })
         
         //为步骤进行时间进行定时事件绑定
      $(".fix-p-select").click(function (event) {
             event.stopPropagation();
         })
         
         //为步骤进行时间进行定时事件绑定
      $(".s-modal-wrap").click(function (event) {
             event.stopPropagation();
         })  
         
         $("#yg-fuwu-yijiao").click(function(event){
             $("#yg-fuwu-yijiao-model").removeClass("hide");
         });
      
     });
     
     /* //取消
     function yijiaoCancel(obj) {
         $(obj).parents(".yijiao-ul").parent().addClass("hide");
     } */
     
     
function recover() {
	 $("#deleteOrderModel").addClass("hide");
}    
     
function memberInfo(memberId){
    for (var i = 0; i < orderList.length; i++) {
        var memberBase = orderList[i].memberInfo;
        if(memberBase.memberId == memberId){
            $("#yg-member-info").find("img").attr("src", picUrl + memberBase.headUrl + "?imageView2/1/w/220/h/220");
            $("#yg-member-info").find(".n-name").text(memberBase.name);
            $("#yg-member-info").find(".sex").text(memberBase.sex);
            $("#yg-member-info").find(".shop-name").text(memberBase.storeName);
            $("#yg-member-info").find(".shenfen").text(memberBase.levelName);
            $("#yg-member-info").find("span[name= 'balanceAmount']").text(memberBase.balanceAmount);
            $("#yg-member-info").find("span[name= 'balanceIntegral']").text(memberBase.balanceIntegral);
            $("#yg-member-info").find("span[name= 'avgConsumeDays']").text(memberBase.avgConsumeDays);
            $("#yg-member-info").find("span[name= 'avgConsumeAmount']").text(memberBase.avgConsumeAmount);
            $("#yg-member-info").find("span[name= 'lastConsumeTime']").text(memberBase.lastConsumeTime);
            $("#yg-member-info").find("span[name= 'giftmoneyAmount']").text(memberBase.giftmoneyAmount);
            $("#yg-member-info").removeClass("hide");
            return;
        }
    }
}

//通知顾客买单
function notifyPayment(orderId, obj){
    $.ajax({
        url : baseUrl + "staff/action/order/notify",
        type : "POST",
        data : "orderId=" + orderId,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("已通知买单");
            $(obj).parent().remove();
            $("#orderId_" + orderId + " [name='statusContent']").html('<span class="fr font-666 fs22">已通知买单</span>');
        }
    });
}

function selectOrderDetail(orderId){
	var temp = document.createElement("form");
    temp.action = baseUrl + "staff/view/selectOrderDetail/${session_key_store_id}/2";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("textarea");
    opt.name = "orderId";
    opt.value = orderId;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
}

$(".score-shop-li").click(function (event) {
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	$(".score-shop-li").removeClass("active");
	$(".score-shop-li").find("img").addClass("hide");
	$(".score-shop-li").find(".border-group").removeClass("hide");
	
	$(obj).parents(".score-shop-li").addClass("active");
	$(obj).parents(".score-shop-li").find("img").removeClass("hide");
	$(obj).parents(".score-shop-li").find(".border-group").addClass("hide");
});

function changeDiv(type, orderType) {
	//全部订单
	if (orderType == 1) {
		var temp = document.createElement("form");
	    temp.action = baseUrl + "staff/view/order/all";
	    temp.method = "post";
	    temp.style.display = "none";
	    var opt = document.createElement("textarea");
	    opt.name = "type";
	    opt.value = type;
	    temp.appendChild(opt);
	    document.body.appendChild(temp);
	    temp.submit();
	}
	else {
		var temp = document.createElement("form");
	    temp.action = baseUrl + "staff/view/employeeOrderView/${session_key_store_id}/2";
	    temp.method = "post";
	    temp.style.display = "none";
	    var opt = document.createElement("textarea");
	    opt.name = "type";
	    opt.value = type;
	    temp.appendChild(opt);
	    document.body.appendChild(temp);
	    temp.submit();
	}
}
//订单标识
var freeOrderId = "";
//原折扣价格
var oldDiscountAmount = "";

//点击签单
function freeClick(orderIds) {
	
	freeOrderId = orderIds;
	
	$("input[name='debtAmount']").val("");
	$("input[name='orderEvaluate']").val("");
	
	$.ajax({
        url : baseUrl + "staff/action/selectOrderInfo",
        type : "POST",
        data : "orderId=" + orderIds,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            var orderInfo = e.msg;
            
           	if (orderInfo.debtAmount != 0) {
               	$("input[name='debtAmount']").val(orderInfo.debtAmount);
               }
               $("input[name='orderEvaluate']").val(orderInfo.orderEvaluate);
            oldDiscountAmount = orderInfo.discountAmount;
        }
    });
}

function confirmFree(type) {
	if(type == 1) {
		/* var freeAmount = $("input[name='freeAmount']").val();
		var orderRemark = $("input[name='orderRemark']").val();
		if (isEmpty(freeAmount)) {
			dialog("签单金额不能为空！");
			return;
		}
		if (isEmpty(orderRemark)) {
			dialog("签单备注不能为空！");
			return;
		}
	    if (oldDiscountAmount < freeAmount) {
	    	dialog("签单金额不能大于折扣金额");
	    	return;
	    }
		$.ajax({
	        url : baseUrl + "staff/action/saveFreeInfo",
	        type : "POST",
	        data : "orderId=" + freeOrderId + "&amount= " + freeAmount + "&remark=" + orderRemark + "&type=" + type,
	        success : function(e){
	            if (e.code != 0) {
	                dialog(e.msg);
	                return;
	            }
	            dialog("签单成功！");
	            $("#yg-qiandan").addClass("hide");
	            
	            $("#orderId_" + freeOrderId).find(".d-price").text("￥" + e.msg);
	        }
	    }); */
	}
	else {
		var debtAmount = $("input[name='debtAmount']").val();
		var orderEvaluate = $("input[name='orderEvaluate']").val();
		if (isEmpty(debtAmount)) {
			dialog("挂账金额不能为空！");
			return;
		}
		if (isEmpty(orderEvaluate)) {
			dialog("挂账备注不能为空！");
			return;
		}
	    if (oldDiscountAmount < orderEvaluate) {
	    	dialog("挂账金额不能大于折扣金额");
	    	return;
	    }
		$.ajax({
	        url : baseUrl + "staff/action/saveDebtInfo",
	        type : "POST",
	        data : "orderId=" + freeOrderId + "&amount= " + debtAmount + "&remark=" + orderEvaluate + "&type=" + type,
	        success : function(e){
	            if (e.code != 0) {
	                dialog(e.msg);
	                return;
	            }
	            dialog("挂账成功！");
	            
	            $("#yg-guazhang").addClass("hide");
	            
	            $("#orderId_" + freeOrderId).find(".d-price").text("￥" + e.msg);
	        }
	    });
	}
}
</script>
</body>
</html>