<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta name="x5-orientation" content="portrait">
    <meta content="telephone=no" name="format-detection" />
    <title>我的业绩</title>

    <!--字体图标库-->
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <!--整体的样式-->
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>

    <script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
</head>
<body>

<div class="wrap">
<div class="content">

<div class="yg-performance-state">
    <div class="tab t0">
        <ul >
            <li class="score-shop-li active w50p" onclick="changeShow(this, 1)">
                <a href="javascript:void(0);">
                    <img src="<%=basePath%>images/mobile/employee/active-new.png" alt=""/>
                    <div class="tab-word">
                        <span class="iconfont ml5 font-size-44"></span>
                        <span>今日</span>
                    </div>
                </a>
                <div class="border-group hide">
                    <div class="one-border"></div>
                    <div class="two-border"></div>
                    <div class="three-border"></div>
                </div>
            </li>
            <li class="score-shop-li w50p" onclick="changeShow(this, 2)">
                <a href="javascript:void(0);">
                    <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
                    <div class="tab-word">
                        <span class="iconfont ml5 font-size-44"></span>
                        <span>本月</span>
                    </div>
                </a>
                <div class="border-group">
                    <div class="one-border"></div>
                    <div class="two-border"></div>
                    <div class="three-border"></div>
                </div>
            </li>
        </ul>
    </div>
    <div class="clearfix"></div>

    <ul class="pt7 yeji " id = "toDayUl">
        <li class="yeji-sum">
            <span class="name">今日提成</span> <span class="fr num">${commissionValue} <span class="word">元</span></span>
        </li>
        <li class="font-333 yeji-item yeji-laodong" id = "projectCalculateLi">
            <div class="font-size-28">劳动业绩</div>
            <div class="today-num">
                <span class="fr">今日${toDayProjectCalculate}</span>
            </div>
            <div class="progress-control ">
                <div class="progress-wrap">
                    <div class="progress today" style="width:0%;"></div>
                </div>
                <div class="progress-wrap">
                    <div class="progress yestaday" style="width:0%;"></div>
                </div>
            </div>
            <div class="yestaday-num">
                <span class="fr">昨日${yesterdayProjectCalculate}</span>
            </div>
        </li>
        <li class="font-333 yeji-item yeji-zhiding" id = "assignProjectCalculateLi">
            <div class="font-size-28"> 指定业绩</div>
            <div class="today-num">
                <span class="fr">今日${toDayAssignProjectCalculate}</span>
            </div>
            <div class="progress-control ">
                <div class="progress-wrap">
                    <div class="progress today" style="width:0%;"></div>
                </div>
                <div class="progress-wrap">
                    <div class="progress yestaday" style="width:0%;"></div>
                </div>
            </div>
            <div class="yestaday-num">
                <span class="fr">昨日${yesterdayAssignProjectCalculate}</span>
            </div>
        </li>
        <li class="font-333 yeji-item yeji-taocan" id = "comboCalculateLi">
            <div class="font-size-28">套餐销售业绩</div>
            <div class="today-num">
                <span class="fr">今日${toDayComboCalculate}</span>
            </div>
            <div class="progress-control ">
                <div class="progress-wrap">
                    <div class="progress today" style="width:0%;"></div>
                </div>
                <div class="progress-wrap">
                    <div class="progress yestaday" style="width:0%;"></div>
                </div>
            </div>
            <div class="yestaday-num">
                <span class="fr">昨日${yesterdayComboCalculate}</span>
            </div>
        </li>
        <li class="font-333 yeji-item yeji-shangpin" id = "goodsCalculateLi">
            <div class="font-size-28">商品销售业绩</div>
            <div class="today-num">
                <span class="fr">今日${toDayGoodsCalculate}</span>
            </div>
            <div class="progress-control ">
                <div class="progress-wrap">
                    <div class="progress today" style="width:0%;"></div>
                </div>
                <div class="progress-wrap">
                    <div class="progress yestaday" style="width:0%;"></div>
                </div>
            </div>
            <div class="yestaday-num">
                <span class="fr">昨日${yesterdayGoodsCalculate}</span>
            </div>
        </li>
        <li class="font-333 yeji-item yeji-kaxiang" id = "chargeCalculateLi">
            <div class="font-size-28">卡项销售业绩</div>
            <div class="today-num">
                <span class="fr">今日${toDayChargeCalculate}</span>
            </div>
            <div class="progress-control ">
                <div class="progress-wrap">
                    <div class="progress today" style="width:0%;"></div>
                </div>
                <div class="progress-wrap">
                    <div class="progress yestaday" style="width:0%;"></div>
                </div>
            </div>
            <div class="yestaday-num">
                <span class="fr">昨日${yesterdayChargeCalculate}</span>
            </div>
        </li>
    </ul>
    
    <!--本月-->
    <div class="yeji-month hide" id = "monthUl">
        <ul class="pt7 yeji ">
	        <li class="yeji-sum">
	            <span class="name">本月提成</span> <span class="fr num">${monthCommissionValue} <span class="word">元</span></span>
	        </li>
	        <li class="font-333 yeji-item yeji-laodong" id = "monthProjectCalculateLi">
	            <div class="font-size-28">劳动业绩</div>
	            <div class="today-num">
	                <span class="fr">实现${monthProjectCalculate}</span>
	            </div>
	            <div class="progress-control ">
	                <div class="progress-wrap">
	                    <div class="progress today"></div>
	                </div>
	                <div class="progress-wrap">
	                    <div class="progress yestaday"></div>
	                </div>
	            </div>
	            <div class="yestaday-num">
	                <span class="fr">目标${totalProjectCalculate}</span>
	            </div>
	        </li>
	        <li class="font-333 yeji-item yeji-zhiding" id = "monthAssignProjectCalculateLi">
	            <div class="font-size-28"> 指定业绩</div>
	            <div class="today-num">
	                <span class="fr">实现${monthAssignProjectCalculate}</span>
	            </div>
	            <div class="progress-control ">
	                <div class="progress-wrap">
	                    <div class="progress today"></div>
	                </div>
	                <div class="progress-wrap">
	                    <div class="progress yestaday"></div>
	                </div>
	            </div>
	            <div class="yestaday-num">
	                <span class="fr">目标${totalAssignProjectCalculate}</span>
	            </div>
	        </li>
	        <li class="font-333 yeji-item yeji-taocan" id = "monthComboCalculateLi">
	            <div class="font-size-28">套餐销售业绩</div>
	            <div class="today-num">
	                <span class="fr">实现${monthComboCalculate}</span>
	            </div>
	            <div class="progress-control ">
	                <div class="progress-wrap">
	                    <div class="progress today"></div>
	                </div>
	                <div class="progress-wrap">
	                    <div class="progress yestaday"></div>
	                </div>
	            </div>
	            <div class="yestaday-num">
	                <span class="fr">目标${totalComboCalculate}</span>
	            </div>
	        </li>
	        <li class="font-333 yeji-item yeji-shangpin" id = "monthGoodsCalculateLi">
	            <div class="font-size-28">商品销售业绩</div>
	            <div class="today-num">
	                <span class="fr">实现${monthGoodsCalculate}</span>
	            </div>
	            <div class="progress-control ">
	                <div class="progress-wrap">
	                    <div class="progress today"></div>
	                </div>
	                <div class="progress-wrap">
	                    <div class="progress yestaday"></div>
	                </div>
	            </div>
	            <div class="yestaday-num">
	                <span class="fr">目标${totalGoodsCalculate}</span>
	            </div>
	        </li>
	        <li class="font-333 yeji-item yeji-kaxiang" id = "monthChargeCalculateLi">
	            <div class="font-size-28">卡项销售业绩</div>
	            <div class="today-num">
	                <span class="fr">实现${monthChargeCalculate}</span>
	            </div>
	            <div class="progress-control ">
	                <div class="progress-wrap">
	                    <div class="progress today"></div>
	                </div>
	                <div class="progress-wrap">
	                    <div class="progress yestaday"></div>
	                </div>
	            </div>
	            <div class="yestaday-num">
	                <span class="fr">目标${totalChargeCalculate}</span>
	            </div>
	        </li>
	    </ul>
	    
        <div class="yeji-ticheng" id = "detailDiv">
            <span class="iconfont icon-left fl" onclick="decreaseDetail()"></span>
            <span class="name font-size-36"> </span>
            <span class="iconfont icon-right fr" onclick="addDetail()"></span>
        </div>
        
        <div class="yeji-table-wrap">


        <table class="head-table yeji-sum-table" >
            <thead>
	            <tr>
	                <th>绩效考核项</th>
	                <th>业绩目标</th>
	                <th>实际完成</th>
	                <th>达标率</th>
	            </tr>
            </thead>
            <tbody id = "detailTbody">
	            
            </tbody>
        </table>
      </div>
    </div>
</div>

</div>
</div>
<script>

    var listStr = '${listStr}';
    var list = eval("(" + listStr + ")");
    
    var detailNum = 0;
    
    /* 今日 */
    //劳动业绩目标
    var toDayProjectCalculate = '${toDayProjectCalculate}';
    var yesterdayProjectCalculate = '${yesterdayProjectCalculate}';
    //指定项目目标
    var toDayAssignProjectCalculate = '${toDayAssignProjectCalculate}';
    var yesterdayAssignProjectCalculate = '${yesterdayAssignProjectCalculate}';
    //套餐销售目标
    var toDayComboCalculate = '${toDayComboCalculate}';
    var yesterdayComboCalculate = '${yesterdayComboCalculate}';
    //商品销售目标
    var toDayGoodsCalculate = '${toDayGoodsCalculate}';
    var yesterdayGoodsCalculate = '${yesterdayGoodsCalculate}';
    //开卡/充值目标
    var toDayChargeCalculate = '${toDayChargeCalculate}';
    var yesterdayChargeCalculate = '${yesterdayChargeCalculate}';
    
    /* 本月 */
    //劳动业绩目标
    var monthProjectCalculate = '${monthProjectCalculate}';
    var totalProjectCalculate = '${totalProjectCalculate}';
    //指定项目目标
    var monthAssignProjectCalculate = '${monthAssignProjectCalculate}';
    var totalAssignProjectCalculate = '${totalAssignProjectCalculate}';
    //套餐销售目标
    var monthComboCalculate = '${monthComboCalculate}';
    var totalComboCalculate = '${totalComboCalculate}';
    //商品销售目标
    var monthGoodsCalculate = '${monthGoodsCalculate}';
    var totalGoodsCalculate = '${totalGoodsCalculate}';
    //开卡/充值目标
    var monthChargeCalculate = '${monthChargeCalculate}';
    var totalChargeCalculate = '${totalChargeCalculate}';
    
    $(function(){
    	initialization(yesterdayProjectCalculate, toDayProjectCalculate, "projectCalculateLi");
    	initialization(yesterdayAssignProjectCalculate, toDayAssignProjectCalculate, "assignProjectCalculateLi");
    	initialization(yesterdayComboCalculate, toDayComboCalculate, "comboCalculateLi");
    	initialization(yesterdayGoodsCalculate, toDayGoodsCalculate, "goodsCalculateLi");
    	initialization(yesterdayChargeCalculate, toDayChargeCalculate, "chargeCalculateLi");
    	
    	initialization(totalProjectCalculate, monthProjectCalculate, "monthProjectCalculateLi");
    	initialization(totalAssignProjectCalculate, monthAssignProjectCalculate, "monthAssignProjectCalculateLi");
    	initialization(totalComboCalculate, monthComboCalculate, "monthComboCalculateLi");
    	initialization(totalGoodsCalculate, monthGoodsCalculate, "monthGoodsCalculateLi");
    	initialization(totalChargeCalculate, monthChargeCalculate, "monthChargeCalculateLi");
    })
    
    function initialization(yesterdays, toDays, idLi) {
    	var yesterday = parseFloat(yesterdays);
    	var toDay = parseFloat(toDays);
    	if ((yesterday == 0 && toDay == 0) || (yesterday != 0 && toDay == 0)) {
    		$("#" + idLi).find(".today").css("width", "0%");
    		$("#" + idLi).find(".yestaday").css("width", "100%");
    	}
    	else if (yesterday == 0 && toDay != 0) {
    		$("#" + idLi).find(".today").css("width", "100%");
    		$("#" + idLi).find(".yestaday").css("width", "0%");
    	}
    	else {
    		if (toDay <= yesterday) {
    			var num = toDay/yesterday * 100 + "%";
    			$("#" + idLi).find(".today").css("width", num);
        		$("#" + idLi).find(".yestaday").css("width", "100%");
    		}
    		else {
    			var num = yesterday/toDay * 100 + "%";
    			$("#" + idLi).find(".today").css("width", "100%");
        		$("#" + idLi).find(".yestaday").css("width", num);
    		}
    	}
    	
    	detailObject();
    }
    
    function changeShow(obj, type) {
    	$("#toDayUl").addClass("hide");
    	$("#monthUl").addClass("hide");
    	$(".score-shop-li").removeClass("active");
    	$(".score-shop-li").find("img").addClass("hide");
    	$(".border-group").removeClass("hide");
    	$(obj).find("img").removeClass("hide");
    	$(obj).find(".border-group").addClass("hide");
    	$(obj).addClass("active");
    	if (type == 1) {
    		$("#toDayUl").removeClass("hide");
    	}
    	else {
    		$("#monthUl").removeClass("hide");
    	}
    }
    
    function decreaseDetail() {
    	detailNum = detailNum + 1;
    	detailObject();
    }
    
    function addDetail(){
    	detailNum = detailNum - 1;
    	detailObject();
    }
    
    function detailObject() {
    	var obj =  list[detailNum];
    	var dates = obj.dates;
    	var year = dates.split("-")[0];
    	var month = dates.split("-")[1];
    	
    	$("#detailDiv").find(".icon-right").removeClass("hide");
    	$("#detailDiv").find(".icon-left").removeClass("hide");
    	
    	if (detailNum == 0) {
    		$("#detailDiv").find(".icon-right").addClass("hide");
    	}
    	else if (detailNum + 1  == list.length) {
    		$("#detailDiv").find(".icon-left").addClass("hide");
    	}
    	
    	$("#detailDiv").find(".name").text(year + "年"+month+"月 绩效考核");
    	
    	$("#detailTbody").empty();
    	$("#detailTbody").append("<tr>"+
					                "<td>劳动业绩</td>"+
					                "<td>"+obj.totalProjectSales+"</td>"+
					                "<td>"+obj.actualTotalProjectSales+"</td>"+
					                "<td>"+obj.projectScale+"%</td>"+
					             "</tr>"+
					             "<tr>"+
					                "<td>指定业绩</td>"+
					                "<td>"+obj.assignProjectSales+"</td>"+
					                "<td>"+obj.actualAssignProjectSales+"</td>"+
					                "<td>"+obj.assignProjectScale+"%</td>"+
					             "</tr>"+
					             "<tr>"+
					                "<td>套餐销售业绩</td>"+
					                "<td>"+obj.comboSales+"</td>"+
					                "<td>"+obj.actualComboSales+"</td>"+
					                "<td>"+obj.comboScale+"%</td>"+
					             "</tr>"+
					             "<tr>"+
					                "<td>商品销售业绩</td>"+
					                "<td>"+obj.goodsSales+"</td>"+
					                "<td>"+obj.actualGoodsSales+"</td>"+
					                "<td>"+obj.goodsScale+"%</td>"+
					             "</tr>"+
					             "<tr>"+
					                "<td>卡项销售业绩</td>"+
					                "<td>"+obj.chargeSales+"</td>"+
					                "<td>"+obj.actualChargeSales+"</td>"+
					                "<td>"+obj.chargeScale+"%</td>"+
					            "</tr>");
    }
    
</script>
</body>
</html>
