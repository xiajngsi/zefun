<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>业绩排行</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content mb-footer">
    <div class="yg-performance-order">
        <div class="tab t0">
            <ul >
                <li class="score-shop-li  active" onclick="changeShow(this, 1)">
                    <a href="javascript:void(0);">
                        <img src="<%=basePath%>images/mobile/employee/active-new.png" alt=""/>
                        <div class="tab-word">
                            <span>日排行</span>
                        </div>
                    </a>
                </li>
                <li class="score-shop-li" onclick="changeShow(this, 2)">
                    <a href="javascript:void(0);">
                        <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
                        <div class="tab-word">
                            <span>周排行</span>
                        </div>
                    </a>
                </li>
                <li class="score-shop-li" onclick="changeShow(this, 3)">
                    <a href="javascript:void(0);">
                        <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
                        <div class="tab-word">
                            <span>月排行</span>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        <div class="clearfix"></div>
        
        <div id = "toDayDiv">
            <c:forEach items="${toDayList}" var="toDay" varStatus="toDayStatus">
                 <div <c:if test="${toDayStatus.index == 0}">class="part mt7"</c:if> <c:if test="${toDayStatus.index != 0}">class="part"</c:if>>
                    <div class=" toggle-ctl  ul-title">
                        <div class="fl">
                            <span class="font-333">${toDayStatus.index + 1}</span>
                            <img src="<%=picPath%>${toDay.headImage}" alt="" class="person-img"/>
                        </div>
                        <div class="desc fl">
                            <div><span>${toDay.employeeCode}</span> <span> ${toDay.name}</span></div>
                        </div>
                        <span class="fr"><span class="blue-word">${toDay.projectValue}元 </span><i class="iconfont icon-zhankai" style="padding-left: 15px;"></i></span>
                        <div class="clearfix"></div>
                    </div>
                    <div class="table-wrap">
                        <table class="table" <c:if test="${toDay.employeeId != employeeId}">style="display: none;"</c:if>>
                        <tbody>
                        <tr class="h60 line-height60 font-gray font-999">
                            <td>均值</td>
                            <td class="text-center">关键指标</td>
                            <td class="text-right">我的表现</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${toDayTotalProjectScale}元</td>
                            <td class="font-999 text-center">劳动业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.projectValue < toDayTotalProjectScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="text-right ${underClass }">${toDay.projectValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${toDayTotalAssignProjectScale}元</td>
                            <td class="font-999 text-center">指定业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.assignProjectValue < toDayTotalAssignProjectScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.assignProjectValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${toDayTotalScale}%</td>
                            <td class="font-999 text-center">指定比例</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.scale < toDayTotalScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.scale}%</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${toDayTotalComboScale}元</td>
                            <td class="font-999 text-center">套餐业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.comboValue < toDayTotalComboScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.comboValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${toDayTotalGoodsScale}元</td>
                            <td class="font-999 text-center">商品业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.goodsValue < toDayTotalGoodsScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.goodsValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${toDayTotalChargeScale}元</td>
                            <td class="font-999 text-center">卡项业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.chargeValue < toDayTotalChargeScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.chargeValue}元</td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </c:forEach>
            
        </div>
        
        <div id = "weekDiv" class = "hide">
            <c:forEach items="${weekList}" var="week" varStatus="weekStatus">
                 <div <c:if test="${weekStatus.index == 0}">class="part mt7"</c:if> <c:if test="${weekStatus.index != 0}">class="part"</c:if>>
                    <div class=" toggle-ctl  ul-title">
                        <div class="fl">
                            <span class="font-333">${weekStatus.index + 1}</span>
                            <img src="<%=picPath%>${week.headImage}" alt="" class="person-img"/>
                        </div>
                        <div class="desc fl">
                            <div><span>${week.employeeCode}</span> <span> ${week.name}</span></div>
                        </div>
                        <span class="fr"><span class="blue-word">${week.projectValue}元 </span><i class="iconfont icon-zhankai" style="padding-left: 15px;"></i></span>
                        <div class="clearfix"></div>
                    </div>
                    <div class="table-wrap">
                        <table class="table" <c:if test="${week.employeeId != employeeId}">style="display: none;"</c:if>>
                        <tbody>
                        <tr class="h60 line-height60 font-gray font-999">
                            <td>均值</td>
                            <td class="text-center">关键指标</td>
                            <td class="text-right">我的表现</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${weekTotalProjectScale}元</td>
                            <td class="font-999 text-center">劳动业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${week.projectValue < weekTotalProjectScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="text-right ${underClass }">${week.projectValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${weekTotalAssignProjectScale}元</td>
                            <td class="font-999 text-center">指定业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${week.assignProjectValue < weekTotalAssignProjectScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${week.assignProjectValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${weekTotalScale}%</td>
                            <td class="font-999 text-center">指定比例</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${week.scale < weekTotalScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${week.scale}%</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${weekTotalComboScale}元</td>
                            <td class="font-999 text-center">套餐业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${week.comboValue < weekTotalComboScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${week.comboValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${weekTotalGoodsScale}元</td>
                            <td class="font-999 text-center">商品业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${week.goodsValue < weekTotalGoodsScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${week.goodsValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${weekTotalChargeScale}元</td>
                            <td class="font-999 text-center">卡项业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${week.chargeValue < weekTotalChargeScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${week.chargeValue}元</td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </c:forEach>
            
         </div>
         
         <div id = "monthDiv" class = "hide">
            <c:forEach items="${monthList}" var="month" varStatus="monthStatus">
                 <div <c:if test="${monthStatus.index == 0}">class="part mt7"</c:if> <c:if test="${monthStatus.index != 0}">class="part"</c:if>>
                    <div class=" toggle-ctl  ul-title">
                        <div class="fl">
                            <span class="font-333">${monthStatus.index + 1}</span>
                            <img src="<%=picPath%>${month.headImage}" alt="" class="person-img"/>
                        </div>
                        <div class="desc fl">
                            <div><span>${month.employeeCode}</span> <span> ${month.name}</span></div>
                        </div>
                        <span class="fr"><span class="blue-word">${month.projectValue}元 </span><i class="iconfont icon-zhankai" style="padding-left: 15px;"></i></span>
                        <div class="clearfix"></div>
                    </div>
                    <div class="table-wrap">
                        <table class="table" <c:if test="${month.employeeId != employeeId}">style="display: none;"</c:if>>
                        <tbody>
                        <tr class="h60 line-height60 font-gray font-999">
                            <td>均值</td>
                            <td class="text-center">关键指标</td>
                            <td class="text-right">我的表现</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${monthTotalProjectScale}元</td>
                            <td class="font-999 text-center">劳动业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${month.projectValue < monthTotalProjectScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${month.projectValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${monthTotalAssignProjectScale}元</td>
                            <td class="font-999 text-center">指定业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${month.assignProjectValue < monthTotalAssignProjectScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${month.assignProjectValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${monthTotalScale}%</td>
                            <td class="font-999 text-center">指定比例</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${month.scale < monthTotalScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${month.scale}%</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${monthTotalComboScale}元</td>
                            <td class="font-999 text-center">套餐业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${month.comboValue < monthTotalComboScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${month.comboValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${monthTotalGoodsScale}元</td>
                            <td class="font-999 text-center">商品业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${month.goodsValue < monthTotalGoodsScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${month.goodsValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${monthTotalChargeScale}元</td>
                            <td class="font-999 text-center">卡项业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${month.chargeValue < monthTotalChargeScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${month.chargeValue}元</td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </c:forEach>
            
        </div>

    </div>
    
    <div class="footer">
        <ul>
            <li >
                <a href="<%=basePath%>staff/view/reception">
                    <span class="iconfont icon-jiedai"></span>
                    <span>接待</span>
                </a>
            </li>
            <li class="active">
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
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script>
function changeShow(obj, type) {
    $("#toDayDiv").addClass("hide");
    $("#weekDiv").addClass("hide");
    $("#monthDiv").addClass("hide");
    $(".score-shop-li").removeClass("active");
    $(".score-shop-li").find("img").addClass("hide");
    $(".border-group").removeClass("hide");
    $(obj).find("img").removeClass("hide");
    $(obj).find(".border-group").addClass("hide");
    $(obj).addClass("active");
    if (type == 1) {
        $("#toDayDiv").removeClass("hide");
    }
    else if (type == 2) {
        $("#weekDiv").removeClass("hide");
    }
    else {
        $("#monthDiv").removeClass("hide");
    }
}
</script>
</body>
</html>