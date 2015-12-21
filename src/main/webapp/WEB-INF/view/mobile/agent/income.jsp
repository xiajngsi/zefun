<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="/base.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css"/>

    <script src="<%=basePath%>js/mobile/jquery.min.js"></script>
    <script src="<%=basePath%>js/mobile/base.js"></script>
    <title>我的收益</title>
</head>
<body class="gray-bg">
<div class="zhanghushouyi">
    <!--渠道收益-->
    <div class="qudao-shouyi">
        <div class="part-title">
            <div  class="block-div"></div>
            <span >我的渠道收益</span>
        </div>

        <ul class="tip">
            <li class="shouyi-sum">
                <div class="wrap">
                    <div class="wrap-content">
                        <div class="name">账户收益</div>
                        <div class="num-sum">
                            <span class="num">${agentAccount.totalAmount }</span> <span class="font-size-24">元</span>
                        </div>
                    </div>
                </div>
            </li>
            <li class="shouyi-sum">
                <div class="wrap">
                    <div class="wrap-content">
                        <div>获得账户/剩余</div>
                        <div class="num-sum">
                            <span class="num">${agentAccount.totalAccount }</span> <span class="font-size-48">/</span><span class="shegnyu">${agentAccount.balanceAccount }</span><span>个</span>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <!--<div class="qudao-sum">
            <ul class="tip">
                <li>
                    <div class="wrap">
                        <div>收益金额</div>
                        <div class="num-sum">
                            <span class="num">668.50</span> <span>万元</span>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="wrap">
                        <div>获得账户/剩余</div>
                        <div class="num-sum">
                            <span class="num">6680</span>
                            <span class="font-size-48">/</span>
                            <span class="shegnyu">26</span>
                            <span>个</span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>-->
        <div class="chart">
            <div id="chartContainer">&nbsp;</div>
        </div>
    </div>

    <!--客户收益-->
    <div class="qudao-shouyi part">
        <div class="part-title">
            <div  class="block-div"></div>
            <span >我的客户收益</span>
            <div class="chart-control fr">
                <div class="shouyitu active" onclick="$(this).addClass('active').siblings().removeClass('active');$('#kehushouyi-bing').removeClass('hide');$('#kehushouyi').addClass('hide');">收益图</div>
                <div class="zoushitu" onclick="$(this).addClass('active').siblings().removeClass('active');$('#kehushouyi').removeClass('hide');$('#kehushouyi-bing').addClass('hide');">走势图</div>
            </div>
        </div>
        <div class="chart">
            <div id="kehushouyi"  class="hide"></div>
            <div id="kehushouyi-bing"></div>
        </div>
    </div>

    <!--我的推荐收益-->
    <div class="qudao-shouyi part">
        <div class="part-title">
            <div  class="block-div"></div>
            <span >我的推荐收益</span>
            <div class="chart-control fr">
                <div class="shouyitu active" onclick="$(this).addClass('active').siblings().removeClass('active');$('#tuijianshouyi-bing').removeClass('hide');$('#tuijianshouyi').addClass('hide');">收益图</div>
                <div class="zoushitu" onclick="$(this).addClass('active').siblings().removeClass('active');$('#tuijianshouyi').removeClass('hide');$('#tuijianshouyi-bing').addClass('hide');">走势图</div>
            </div>
        </div>
        <div class="chart">
            <div id="tuijianshouyi" class="hide"></div>
            <div id="tuijianshouyi-bing"  ></div>
        </div>
    </div>


</div>
<c:set var="monthsStr" value="01,02,03,04,05,06,07,08,09,10,11,12" />
<c:set var="months" value="${fn:split(monthsStr, ',')}"/>
<script type="text/javascript" src="<%=basePath%>js/mobile/fusioncharts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/fusioncharts.charts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/fusioncharts.theme.fint.js"></script>
<script>

    FusionCharts.ready(function () {
        var visitChart = new FusionCharts({
            type: 'msline',
            renderAt: 'chartContainer',
            width: '100%',
            height: '350',
            dataFormat: 'json',
            dataSource: {
                "chart": {
                    /*"caption": "Number of visitors last week",
                    "subCaption": "Bakersfield Central vs Los Angeles Topanga",*/
                    "captionFontSize": "14",
                    "subcaptionFontSize": "14",
                    "subcaptionFontBold": "0",
                    "paletteColors": "#fabc53",
                    "bgcolor": "#ffffff",
                    "showBorder": "0",
                    "showShadow": "0",
                    "showCanvasBorder": "0",
                    "usePlotGradientColor": "0",
                    "legendBorderAlpha": "0",
                    "legendShadow": "0",
                    "showAxisLines": "1",
                    "showAlternateHGridColor": "0",
                    "divlineThickness": "1",
                    "divLineIsDashed": "1",
                    "divLineDashLen": "1",
                    "divLineGapLen": "1",
                    "xAxisName": "(月)",
                    "yAxisName": "(万元)",
                    "rotateYAxisName": "90",
                    "showValues": "1"
                },
                "categories": [
                    {
                        "category": [
                            { "label": "1" },
                            { "label": "2" },
                            { "label": "3" },
                            { "label": "4" },
                            { "label": "5" },
                            { "label": "6" },
                            { "label": "7" },
                            { "label": "8" },
                            { "label": "9" },
                            { "label": "10" },
                            { "label": "11" },
                            { "label": "12" }
                        ]
                    }
                ],
                "dataset": [
                    {
                        "seriesname": "月收益(${incomeYear})",
                        "data": [
                                 <c:forEach var="month" items="${months }" varStatus="monthStatus" >
                                 <c:set var="ym" value="${incomeYear}-${month }"/>
                                 <c:set var="val" value="{ 'value': '0.0' }" />
                                 <c:if test="${not empty agentIncomes[ym] and agentIncomes[ym].income > 0 }">
                                 <c:set var="val" value="{ 'value': '${agentIncomes[ym].income }' }" />
                                 </c:if>
                                 <c:if test="${!monthStatus.last}">
                                 <c:set var="val" value="${val }," />
                                 </c:if>
                                 <c:out value="${val }" escapeXml="false"/>
                                 </c:forEach>
                        ]
                    }
                ]
            }
        }).render();
    });

    /*客户收益*/
    FusionCharts.ready(function () {
        var visitChart = new FusionCharts({
            type: 'msline',
            renderAt: 'kehushouyi',
            width: '100%',
            height: '350',
            dataFormat: 'json',
            dataSource: {
                "chart": {
                    /*"caption": "Number of visitors last week",
                    "subCaption": "Bakersfield Central vs Los Angeles Topanga",*/
                    "captionFontSize": "14",
                    "subcaptionFontSize": "14",
                    "subcaptionFontBold": "0",
                    "paletteColors": "#a9d86e,#f8a20f",
                    "bgcolor": "#ffffff",
                    "showBorder": "0",
                    "showShadow": "0",
                    "showCanvasBorder": "0",
                    "usePlotGradientColor": "0",
                    "legendBorderAlpha": "0",
                    "legendShadow": "0",
                    "showAxisLines": "0",
                    "showAlternateHGridColor": "0",
                    "divlineThickness": "1",
                    "divLineIsDashed": "1",
                    "divLineDashLen": "1",
                    "divLineGapLen": "1",
                    "xAxisName": "(月)",
                    "yAxisName": "(万元)",
                    "rotateYAxisName": "90",
                    "showValues": "1"
                },
                "categories": [
                    {
                        "category": [
                            { "label": "1" },
                            { "label": "2" },
                            { "label": "3" },
                            { "label": "4" },
                            { "label": "5" },
                            { "label": "6" },
                            { "label": "7" },
                            { "label": "8" },
                            { "label": "9" },
                            { "label": "10" },
                            { "label": "11" },
                            { "label": "12" }
                        ]
                    }
                ],
                "dataset": [
                    {
                        "seriesname": "单店",
                        "data": [
                    <c:forEach var="month" items="${months }" varStatus="monthStatus" >
                    <c:set var="ym" value="${incomeYear}-${month }"/>
                    <c:set var="val" value="{ 'value': '0.0' }" />
                    <c:if test="${not empty singleMonthsIncome[ym] and singleMonthsIncome[ym].income > 0 }">
                    <c:set var="val" value="{ 'value': '${singleMonthsIncome[ym].income }' }" />
                    </c:if>
                    <c:if test="${!monthStatus.last}">
                    <c:set var="val" value="${val }," />
                    </c:if>
                    <c:out value="${val }" escapeXml="false"/>
                    </c:forEach>
                        ]
                    },
                    {
                        "seriesname": "连锁店",
                        "data": [
            <c:forEach var="month" items="${months }" varStatus="monthStatus" >
            <c:set var="ym" value="${incomeYear}-${month }"/>
            <c:set var="val" value="{ 'value': '0.0' }" />
            <c:if test="${not empty chainMonthsIncome[ym] and chainMonthsIncome[ym].income > 0 }">
            <c:set var="val" value="{ 'value': '${chainMonthsIncome[ym].income }' }" />
            </c:if>
            <c:if test="${!monthStatus.last}">
            <c:set var="val" value="${val }," />
            </c:if>
            <c:out value="${val }" escapeXml="false"/>
            </c:forEach>
                        ]
                    }
                ]
            }
        }).render();
    });

    /*客户收益饼图*/
    FusionCharts.ready(function () {
        var revenueChart = new FusionCharts({
            type: 'doughnut2d',
            renderAt: 'kehushouyi-bing',
            width: '100%',
            height: '300',
            dataFormat: 'json',
            dataSource: {
                "chart": {
                    /*"caption": "Split of Revenue by Product Categories",
                    "subCaption": "Last year",*/
                    "numberPrefix": "$",
                    "paletteColors": "#a9d86e,#f8a20f",
                    "bgColor": "#ffffff",
                    "showBorder": "0",
                    "use3DLighting": "0",
                    "showShadow": "0",
                    "enableSmartLabels": "0",
                    "startingAngle": "310",
                    "showLabels": "0",
                    "showPercentValues": "1",
                    "showLegend": "1",
                    "legendShadow": "0",
                    "legendBorderAlpha": "0",
                    "defaultCenterLabel": "总收益${totalIncome}万",
                    "centerLabel": "收益 $label: $value",
                    "centerLabelBold": "1",
                    "showTooltip": "1",
                    "decimals": "0",
                    "captionFontSize": "14",
                    "subcaptionFontSize": "14",
                    "subcaptionFontBold": "0"
                },
                "data": [
                    {
                        "label": "单店",
                        "value": "${singleTotalIncome}"
                    },
                    {
                        "label": "连锁店",
                        "value": "${chainTotalIncome}"
                    }
                ]
            }
        }).render();
    });

    /*推荐客户收益*/
    FusionCharts.ready(function () {
        var visitChart = new FusionCharts({
            type: 'msline',
            renderAt: 'tuijianshouyi',
            width: '100%',
            height: '350',
            dataFormat: 'json',
            dataSource: {
                "chart": {
                    /*"caption": "Number of visitors last week",
                    "subCaption": "Bakersfield Central vs Los Angeles Topanga",*/
                    "captionFontSize": "14",
                    "subcaptionFontSize": "14",
                    "subcaptionFontBold": "0",
                    "paletteColors": "#f9ac28,#cce0b5,#fbd086,#a9d86e",
                    "bgcolor": "#ffffff",
                    "showBorder": "0",
                    "showShadow": "0",
                    "showCanvasBorder": "0",
                    "usePlotGradientColor": "0",
                    "legendBorderAlpha": "0",
                    "legendShadow": "0",
                    "showAxisLines": "0",
                    "showAlternateHGridColor": "0",
                    "divlineThickness": "1",
                    "divLineIsDashed": "1",
                    "divLineDashLen": "1",
                    "divLineGapLen": "1",
                    "xAxisName": "(月)",
                    "yAxisName": "(万元)",
                    "rotateYAxisName": "90",
                    "showValues": "1"
                },
                "categories": [
                    {
                        "category": [
                            { "label": "1" },
                            { "label": "2" },
                            { "label": "3" },
                            { "label": "4" },
                            { "label": "5" },
                            { "label": "6" },
                            { "label": "7" },
                            { "label": "8" },
                            { "label": "9" },
                            { "label": "10" },
                            { "label": "11" },
                            { "label": "12" }
                        ]
                    }
                ],
                "dataset": [
                    {
                        "seriesname": "我推荐的单店",
                        "data": [
              <c:forEach var="month" items="${months }" varStatus="monthStatus" >
              <c:set var="ym" value="${incomeYear}-${month }"/>
              <c:set var="val" value="{ 'value': '0.0' }" />
              <c:if test="${not empty myRecSingleMonthsIncome[ym] and myRecSingleMonthsIncome[ym].income > 0 }">
              <c:set var="val" value="{ 'value': '${myRecSingleMonthsIncome[ym].income }' }" />
              </c:if>
              <c:if test="${!monthStatus.last}">
              <c:set var="val" value="${val }," />
              </c:if>
              <c:out value="${val }" escapeXml="false"/>
              </c:forEach>
                        ]
                    },
                    {
                        "seriesname": "我推荐的连锁店",
                        "data": [
            <c:forEach var="month" items="${months }" varStatus="monthStatus" >
            <c:set var="ym" value="${incomeYear}-${month }"/>
            <c:set var="val" value="{ 'value': '0.0' }" />
            <c:if test="${not empty myRecChainMonthsIncome[ym] and myRecChainMonthsIncome[ym].income > 0 }">
            <c:set var="val" value="{ 'value': '${myRecChainMonthsIncome[ym].income }' }" />
            </c:if>
            <c:if test="${!monthStatus.last}">
            <c:set var="val" value="${val }," />
            </c:if>
            <c:out value="${val }" escapeXml="false"/>
            </c:forEach>
                        ]
                    },{
                        "seriesname": "推荐给我的单店",
                        "data": [
              <c:forEach var="month" items="${months }" varStatus="monthStatus" >
              <c:set var="ym" value="${incomeYear}-${month }"/>
              <c:set var="val" value="{ 'value': '0.0' }" />
              <c:if test="${not empty recMeSingleMonthsIncome[ym] and recMeSingleMonthsIncome[ym].income > 0 }">
              <c:set var="val" value="{ 'value': '${recMeSingleMonthsIncome[ym].income }' }" />
              </c:if>
              <c:if test="${!monthStatus.last}">
              <c:set var="val" value="${val }," />
              </c:if>
              <c:out value="${val }" escapeXml="false"/>
              </c:forEach>
                        ]
                    },{
                        "seriesname": "推荐给我的连锁店",
                        "data": [
            <c:forEach var="month" items="${months }" varStatus="monthStatus" >
            <c:set var="ym" value="${incomeYear}-${month }"/>
            <c:set var="val" value="{ 'value': '0.0' }" />
            <c:if test="${not empty recMeChainMonthsIncome[ym] and recMeChainMonthsIncome[ym].income > 0 }">
            <c:set var="val" value="{ 'value': '${recMeChainMonthsIncome[ym].income }' }" />
            </c:if>
            <c:if test="${!monthStatus.last}">
            <c:set var="val" value="${val }," />
            </c:if>
            <c:out value="${val }" escapeXml="false"/>
            </c:forEach>

                        ]
                    }
                ]
            }
        }).render();
    });

    /*推荐客户收益饼图*/
    FusionCharts.ready(function () {
        var revenueChart = new FusionCharts({
            type: 'doughnut2d',
            renderAt: 'tuijianshouyi-bing',
            width: '100%',
            height: '350',
            dataFormat: 'json',
            dataSource: {
                "chart": {
                    /*"caption": "Split of Revenue by Product Categories",
                    "subCaption": "Last year",*/
                    "numberPrefix": "$",
                    "paletteColors": "#d1e5b9,#f8a20f,#fbd086,#a9d86e",
                    "bgColor": "#ffffff",
                    "showBorder": "0",
                    "use3DLighting": "0",
                    "showShadow": "0",
                    "enableSmartLabels": "0",
                    "startingAngle": "310",
                    "showLabels": "0",
                    "showPercentValues": "1",
                    "showLegend": "1",
                    "legendShadow": "0",
                    "legendBorderAlpha": "0",
                    "defaultCenterLabel": "总收益${recTotalIncome}万",
                    "centerLabel": "收益 $label: $value",
                    "centerLabelBold": "1",
                    "showTooltip": "1",
                    "decimals": "0",
                    "captionFontSize": "14",
                    "subcaptionFontSize": "14",
                    "subcaptionFontBold": "0"
                },
                "data": [
                    {
                        "label": "我推荐的单店",
                        "value": "${myRecSingleTotalIncome}"
                    },
                    {
                        "label": "我推荐的连锁店",
                        "value": "${myRecChainTotalIncome}"
                    },
                    {
                        "label": "推荐给我的单店",
                        "value": "${recMeSingleTotalIncome}"
                    },
                    {
                        "label": "推荐给我的连锁店",
                        "value": "${recMeChainTotalIncome}"
                    }
                ]
            }
        }).render();
    });
</script>
</body>
</html>
