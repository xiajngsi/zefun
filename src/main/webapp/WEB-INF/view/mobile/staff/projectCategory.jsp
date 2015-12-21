<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>选择服务</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/swiper.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
    <style type="text/css">
        .project-list{
            padding-bottom:7rem;
        }
    </style>
</head>
<body>
<div class="wrap">
<div class="content">
<div class="head text-center ">
  <c:choose>
      <c:when test="${memberBaseDto.sex == '女' }">
          <span class="iconfont icon-womanuser fs30 ml5"></span>
      </c:when>
      <c:otherwise>
          <span class="iconfont icon-manuser fs30 ml5"></span>
      </c:otherwise>
  </c:choose>
  <span class="user-name s-modal-control" onclick="memberInfo('${memberBaseDto.name }')">${memberBaseDto.name }</span>
  <div class="bumen fr s-modal-control" onclick="openModal()">
     <span class="iconfont fs30 ml5 more">&#xe833</span>
  </div>
 <%-- <div class="bumen fr">
        <span name = "deptNameDIV">${employeeDeptName}</span> 
        <span class="iconfont icon-arr fs30 ml5">&#xe800</span>
        <!--部门下拉-->
        <div class="bumen-drowndown">
            <ul>
                <c:forEach items="${deptList}" var="dept" varStatus="status">
                   <li onclick="changeDept(${dept.deptId}, '${dept.deptName}')"> ${dept.deptName}</li>
                </c:forEach>
            </ul>
        </div>
    </div> --%>
 </div>

  <c:forEach items="${dtoList}" var="dto" varStatus="status">
      <div <c:if test="${dto.deptId == employeeDeptId}">class="bd-white bodyoh"</c:if> <c:if test="${dto.deptId != employeeDeptId}">class="bd-white bodyoh hide"</c:if> id = "${dto.deptId}">
        <div class="tab t0" style="top:5rem;">
            <ul >
                <li class="score-shop-li  active" onclick="updateDiv(this, 'projectDIV')">
                    <a href="javascript:void(0);">
                        <img src="<%=basePath%>images/mobile/employee/active-new.png" alt=""/>
                        <div class="tab-word">
                            <span class="iconfont icon-wenjianjia ml5 font-size-44"></span>
                            <span>项目</span>
                        </div>
                    </a>
                </li>
                <li class="score-shop-li" onclick="updateDiv(this, 'comboDIV')">
                    <a href="javascript:void(0);">
                        <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
                        <div class="tab-word">
                            <span class="iconfont icon-taocan0240 ml5 font-size-44"></span>
                            <span>套餐</span>
                        </div>
                    </a>
                </li>
                <li class="score-shop-li" onclick="updateDiv(this, 'goodsDIV')">
                    <a href="javascript:void(0);">
                        <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
                        <div class="tab-word">
                            <span class="iconfont icon-shangpin-SZ ml5 font-size-44"></span>
                            <span>商品</span>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        
        <div class="clearfix"></div>
    
        <div class="d-select-project" name = "projectDIV">
            <div class="d-content">
                <div class="project-type">
                    <ul class="project-type-ul">
                        <!-- <li class="type-item active j-tab" categoryTitle="0">
                            <span>常用项目</span>
                        </li> -->
                        <c:forEach items="${dto.project}" var="projectCategoryDto" varStatus="status">
                            <li class="type-item j-tab" categoryTitle="${projectCategoryDto.categoryId }" name="${projectCategoryDto.categoryId}">
                                <span>${projectCategoryDto.categoryName}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class= "pl-tab">
                    <div class="project-list j-tab" categoryContent="0">
                        <%-- <div class="project-list-part">
                            <div class="list-part-title">
                                <span>常用项目</span>
                            </div>
                            <div class="list-item">
                                <ul>
                                    <c:forEach items="${projectInfoListOrder}" var="projectInfoOrder" varStatus="status">
                                       <li name = "${projectInfoOrder.projectId}" types = "1">
                                            <img src="<%=picPath%>${projectInfoOrder.projectImage}?imageView2/1/w/220/h/220" width="109" height="109" alt=""/>
                                            <div class="item-desc" name="${projectInfoOrder.projectImage}">
                                                <div class="name">${projectInfoOrder.projectName}</div>
                                                <div class="d-price">
                                                    <span class="word">价格: </span>
                                                    <span class="num-emphasize">￥${projectInfoOrder.projectPrice}</span>
                                                </div>
                                                <div class="origin-price">服务次数:${projectInfoOrder.salesCount}</div>
                                            </div>
            
                                            <div class="shop-num-control">
                                               <div class="img-wrap" onclick="decrease(this, ${projectInfoOrder.projectId},1)">
                                                  <img src="<%=basePath%>images/mobile/employee/gouwu-jian.png" alt=""/>
                                               </div>
                                               <span class="ml mr">0</span>
                                               <div class="img-wrap" onclick="add(this, ${projectInfoOrder.projectId},1)">
                                                  <img src="<%=basePath%>images/mobile/employee/gouwu-plus.png" alt=""/>
                                               </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div> --%>
                        
                        <c:forEach items="${dto.project}" var="projectCategoryDto" varStatus="status">
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
                
                                                <div class="shop-num-control">
                                                    <div class="img-wrap" onclick="decrease(this, ${projectInfo.projectId},1)">
                                                       <img src="<%=basePath%>images/mobile/employee/gouwu-jian.png" alt=""/>
                                                    </div>
                                                    <span class="ml mr">0</span>
                                                    <div class="img-wrap" onclick="add(this, ${projectInfo.projectId},1)">
                                                       <img src="<%=basePath%>images/mobile/employee/gouwu-plus.png"  alt=""/>
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
        
        
        
        <div class="d-select-project hide" name = "goodsDIV">
            <div class="d-content">
                <div class="project-type">
                    <ul class="project-type-ul">
                        <c:forEach items="${dto.goods}" var="goodsCategoryDto" varStatus="status">
                            <li class="type-item j-tab" categoryTitle="${goodsCategoryDto.categoryId }" name = "${goodsCategoryDto.categoryId}">
                                <span>${goodsCategoryDto.categoryName}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class= "pl-tab">
                    <div class="project-list j-tab">
                        <c:forEach items="${dto.goods}" var="goodsCategoryDto" varStatus="status">
                           <div class="project-list-part" categoryContent="${goodsCategoryDto.categoryId }" name = "${goodsCategoryDto.categoryId}">
                                <div class="list-part-title">
                                    <span>${goodsCategoryDto.categoryName}</span>
                                </div>
                                <div class="list-item">
                                    <ul>
                                        <c:forEach items="${goodsCategoryDto.goodsBaseDtos}" var="goodsInfoDto" varStatus="status">
                                            <li name= "${goodsInfoDto.goodsId}" types = "2">
                                                <img src="<%=picPath%>${goodsInfoDto.goodsImage}?imageView2/1/w/220/h/220" width="109" height="109" alt=""/>
                                                <div class="item-desc" name="${goodsInfoDto.goodsImage}">
                                                    <div class="name">${goodsInfoDto.goodsName}</div>
                                                    <div class="d-price">
                                                        <span class="word">价格: </span>
                                                        <span class="num-emphasize">￥${goodsInfoDto.goodsPrice}</span>
                                                    </div>
                                                    <div class="origin-price">已售数量:${goodsInfoDto.salesCount}</div>
                                                </div>
                
                                                <div class="shop-num-control">
                                                    <div class="img-wrap" onclick="decrease(this, ${goodsInfoDto.goodsId},2)">
                                                       <img src="<%=basePath%>images/mobile/employee/gouwu-jian.png" alt=""/>
                                                    </div>
                                                    <span class="ml mr">0</span>
                                                    <div class="img-wrap" onclick="add(this, ${goodsInfoDto.goodsId},2)">
                                                       <img src="<%=basePath%>images/mobile/employee/gouwu-plus.png" alt=""/>
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
        
        <div class="select-taocan-list">
            <div class="d-select-project hide" name = "comboDIV">
                <div class="d-content">
                        <div class="project-list j-tab">
                            <c:forEach items="${dto.combo}" var="comboInfo" varStatus="status">
                               <div class="project-list-part">
                                    <div class="list-item">
                                        <ul>
                                            <li name= "${comboInfo.comboId}" types = "3">
                                                <img src="<%=picPath%>${comboInfo.comboImage}?imageView2/1/w/220/h/220" width="109" height="109" alt=""/>
                                                <div class="item-desc" name="${comboInfo.comboImage}">
                                                    <div class="name">${comboInfo.comboName}</div>
                                                    <div class="d-price">
                                                        <span class="word">价格: </span>
                                                        <span class="num-emphasize">￥${comboInfo.comboSalePrice}</span>
                                                    </div>
                                                    <div class="origin-price">已售数量:${comboInfo.salesCount}</div>
                                                </div>
                
                                                <div class="shop-num-control">
                                                    <div class="img-wrap" onclick="decrease(this, ${comboInfo.comboId},3)">
                                                       <img src="<%=basePath%>images/mobile/employee/gouwu-jian.png" alt=""/>
                                                    </div>
                                                    <span class="ml mr">0</span>
                                                    <div class="img-wrap" onclick="add(this, ${comboInfo.comboId},3)">
                                                       <img src="<%=basePath%>images/mobile/employee/gouwu-plus.png" alt=""/>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </c:forEach>
                            
                        </div>
                    </div>
            </div>
        </div>
        
    </div>
  </c:forEach>
    
    <!--购物车-->
    <div class="footer-gouwuche" id= "gouwucheInfo">
        <div class="gouwuche">
            <span class="iconfont icon-gouwuche"></span>
            <span class="s-badge">0</span>
        </div>
        <div class="shop-price">
            <span class="">共计: <span class="num-emphasize">￥ 0</span>元</span>
        </div>
        <div class="goumai-btn">
            <button class="btn hide" id = "choiceEmployee">选择人员</button>
            <button class="btn hide" id = "confirmBuy">确认购买</button>
        </div>
    </div>
    
    
    <!--购物车内容-->
    <div class="hide s-modal s-modal-miss no-padding s-gouwuche-modal" id="yg-gouwuche-modal">
        <ul class="gouwuche-ul" id = "gouwucheDetail">
            <li class="touwuche-title clearfix">
                <span class="fl gouwuche-name">购物车</span>
                <span class="iconfont  icon-shanchu fr " onclick="emptyAll()"></span>
            </li>
        </ul>
    </div>
    
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
        
        <!--部门选择模态框-->
		<!--选择、部门-->
		<div class="s-modal hide s-modal-miss" id="yg-select-modal">
		    <div class="s-select-wrap">
		        <div class="bumen-drowndown">
		            <ul>
		                <c:forEach items="${deptList}" var="dept" varStatus="status">
		                   <li onclick="changeDept(${dept.deptId}, '${dept.deptName}')"> ${dept.deptName}</li>
		                </c:forEach>
		                 
		            </ul>
		        </div>
		    </div>
		</div>
</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"> </script>
<script type="text/javascript">
    var memberBaseDtoStr = '${memberBaseDtoStr}';
    var memberBaseDto = eval("(" + memberBaseDtoStr + ")");
    
    var orderId = '${orderId}';
    
    $(function () {
         $(".score-shop-li").on("click",function(){
             var parents = $(this).parents(".bd-white");
             parents.find(".score-shop-li").removeClass("active");
             $(this).addClass("active");
             parents.find(".border-group").removeClass("hide");
             parents.find(".score-shop-li").find("img").addClass("hide");
             $(this).find("img").removeClass("hide");
             $(this).find(".border-group").addClass("hide");
             
         });
         jQuery("*").click(function (event) {
             event = event ? event : window.event;
             var obj = event.srcElement ? event.srcElement : event.target;
             var state = jQuery(obj).parents(".bumen");
             if (jQuery(state).hasClass("bumen")) {
                 jQuery(".bumen-drowndown").show();
             }
             else {
                 jQuery(".bumen-drowndown").hide();
             }
         });
         $(".bumen-drowndown li").click(function(event){
             event = event ? event : window.event;
             event.stopPropagation();
             jQuery(".bumen-drowndown").hide();
         });
        /*给元素定义高度*/
         var contentHeight = function(){
             var height =  document.body.clientHeight;
             var projectUlHeight;
             if($.browser.versions.android){
               projectUlHeight = height - 170 + 80;
             }else if($.browser.versions.ios){
               projectUlHeight = height - 170 + 44;
             }
             $(".project-list").css("height", projectUlHeight);
             $(".project-type-ul").css("height", projectUlHeight);
        }
        
        $(".bumen").on("click",function(){
            $(".bumen-drowndown").toggle();
        })
        
        contentHeight();
        
        var offset = $(".d-content").offset().top;
        var top = offset;
        $(".type-item").click(function(){
            var parents = $(this).parents(".bd-white");
            $(this).addClass("active").siblings().removeClass("active");
            var sub = parents.find("[categoryContent='" + $(this).attr("categorytitle") + "']");
            var container = sub.parent();
            offset = sub.offset().top - container.offset().top + container.scrollTop();
            container.scrollTop(offset);
        });
    })

    
    
    function updateDiv (obj, val) {
        var parents = $(obj).parents(".bd-white");
        parents.find(".d-select-project").addClass("hide");
        parents.find("div[name="+val+"]").removeClass("hide");
    }
    
    $(".gouwuche").click(function(){
        if ($("#yg-gouwuche-modal").hasClass("hide")) {
            $("#yg-gouwuche-modal").removeClass("hide");
        }
        else {
            $("#yg-gouwuche-modal").addClass("hide");
        }
    });
    
    $(".gouwuche-ul").click(function(event){
        event.stopPropagation();
    })
    
    function add(obj, objId, type) {
        var num = $(obj).parents(".shop-num-control").find(".mr").text();
        num ++;
        var elementList = $("li[name ='"+objId+"'][types = '"+type+"']");
        for (var i = 0; i < elementList.length; i++) {
            $(elementList[i]).find(".mr").text(num);
        }
        var parentObj = $(obj).parents(".shop-num-control").parent();
        var name = $(parentObj).find(".name").text();
        var emphasize = $(parentObj).find(".num-emphasize").text();
        var images = $(parentObj).find(".item-desc").attr("name");
        var id = $(parentObj).attr("name");
        if(isEmpty($(".gouwu-li[name ='"+id+"'][types = '"+type+"']").html())) {
            $("#gouwucheDetail").append("<li class='gouwu-li clearfix' name= '"+id+"' types='"+type+"'>"+
                                            "<span class='shop-name fl'>"+name+"</span>"+
                                            "<div class='num-control fr'>"+
                                                "<div class='img-wrap' onclick='gouwudecrease(this)'>"+
                                                    "<img src='<%=basePath%>images/mobile/employee/gouwu-jian.png' alt=''/>"+
                                                "</div>"+
                                                "<span class='ml mr'>1</span>"+
                                                "<div class='img-wrap' onclick='gouwuadd(this)'>"+
                                                    "<img src='<%=basePath%>images/mobile/employee/gouwu-plus.png' alt=''/>"+
                                                "</div>"+
                                            "</div>"+
                                            "<span class='shop-price fr mr2' name='"+images+"'>"+emphasize+"</span>"+
                                        "</li>");
            var badge = $("#gouwucheInfo").find(".s-badge").text();
            badge++;
            $("#gouwucheInfo").find(".s-badge").text(badge);
        }
        else {
            $(".gouwu-li[name ='"+id+"'][types = '"+type+"']").find(".mr").text(num);
            
        }
        
        var moneyAll = parseFloat(($("#gouwucheInfo").find(".num-emphasize").text()).replace("￥", ""));
        moneyAll = moneyAll + parseFloat(emphasize.replace("￥", ""));
        $("#gouwucheInfo").find(".num-emphasize").text("￥ " + moneyAll);
        
        isShow();
    }
    
    function isShow(){
        if (!isEmpty($(".gouwu-li[types = '1']").html())) {
            $("#choiceEmployee").removeClass("hide");
            $("#confirmBuy").addClass("hide");
        }
        else {
            if (!isEmpty($(".gouwu-li[types = '2']").html()) || !isEmpty($(".gouwu-li[types = '3']").html())) {
                $("#choiceEmployee").addClass("hide");
                $("#confirmBuy").removeClass("hide");
            }
            else {
                $("#choiceEmployee").addClass("hide");
                $("#confirmBuy").addClass("hide");
            }
        }
    }
    
    function decrease(obj, objId, type) {
        var num = $(obj).parents(".shop-num-control").find(".mr").text();
        var parentObj = $(obj).parents(".shop-num-control").parent();
        var id = $(parentObj).attr("name");
        var emphasize = $(parentObj).find(".num-emphasize").text();
        if (num != 0) {
            num --;
            var elementList = $("li[name ='"+objId+"'][types = '"+type+"']");
            for (var i = 0; i < elementList.length; i++) {
                $(elementList[i]).find(".mr").text(num);
            }
            if (num == 0) {
                $(".gouwu-li[name ='"+id+"'][types = '"+type+"']").remove();
                var badge = $("#gouwucheInfo").find(".s-badge").text();
                badge--;
                $("#gouwucheInfo").find(".s-badge").text(badge);
            }
            else {
                $(".gouwu-li[name ='"+id+"'][types = '"+type+"']").find(".mr").text(num);
            }
            var moneyAll = parseFloat(($("#gouwucheInfo").find(".num-emphasize").text()).replace("￥", ""));
            moneyAll = moneyAll - parseFloat(emphasize.replace("￥", ""));
            $("#gouwucheInfo").find(".num-emphasize").text("￥ " + moneyAll);
        }
        
        isShow();
    }
    
    function openModal() {
        $("#yg-select-modal").removeClass("hide");
        $("#yg-select-modal").find(".bumen-drowndown").css("display", "block");
    }
    
    function openModal() {
    	$("#yg-select-modal").removeClass("hide");
    	$("#yg-select-modal").find(".bumen-drowndown").css("display", "block");
    }
    
    function changeDept(cDeptId, cDeptName) {
    	$(".bd-white").addClass("hide");
    	$("#"+cDeptId).removeClass("hide");
    	$("#yg-select-modal").addClass("hide");
    	$("#yg-select-modal").find(".bumen-drowndown").css("display", "none");
    }
    
    function memberInfo(name){
        if (name == "散客") {
            return;
        }
        $("#yg-member-info").find("img").attr("src", picUrl + memberBaseDto.headUrl + "?imageView2/1/w/220/h/220");
        $("#yg-member-info").find(".n-name").text(memberBaseDto.name);
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
    
    
    function gouwudecrease(obj) {
        var num = $(obj).parents(".num-control").find(".mr").text();
        var parentObj = $(obj).parents(".gouwu-li");
        num--;
        var id = $(parentObj).attr("name");
        var type = $(parentObj).attr("types");
        var emphasize = $(parentObj).find(".shop-price").text();
        var elementList = $("li[name ='"+id+"'][types = '"+type+"']");
        for (var i = 0; i < elementList.length; i++) {
            $(elementList[i]).find(".mr").text(num);
        }
        if (num != 0) {
            $(obj).parent().find(".mr").text(num);
        }
        else {
            $(parentObj).remove();
            var badge = $("#gouwucheInfo").find(".s-badge").text();
            badge--;
            $("#gouwucheInfo").find(".s-badge").text(badge);
        }
        var moneyAll = parseFloat(($("#gouwucheInfo").find(".num-emphasize").text()).replace("￥", ""));
        moneyAll = moneyAll - parseFloat(emphasize.replace("￥", ""));
        $("#gouwucheInfo").find(".num-emphasize").text("￥ " + moneyAll);
        
        isShow();
    }
    
    function gouwuadd(obj) {
        var num = $(obj).parents(".num-control").find(".mr").text();
        num++;
        $(obj).parents(".num-control").find(".mr").text(num);
        var parentObj = $(obj).parents(".gouwu-li");
        var id = $(parentObj).attr("name");
        var type = $(parentObj).attr("types");
        var emphasize = $(parentObj).find(".shop-price").text();
        var elementList = $("li[name ='"+id+"'][types = '"+type+"']");
        for (var i = 0; i < elementList.length; i++) {
            $(elementList[i]).find(".mr").text(num);
        }
        var moneyAll = parseFloat(($("#gouwucheInfo").find(".num-emphasize").text()).replace("￥", ""));
        moneyAll = moneyAll + parseFloat(emphasize.replace("￥", ""));
        $("#gouwucheInfo").find(".num-emphasize").text("￥ " + moneyAll);
        
        isShow();
    }
    
    function emptyAll() {
        $(".s-badge").text("0");
        $("#gouwucheDetail").empty();
        $("#gouwucheDetail").append("<li class='touwuche-title clearfix'>"+
                                        "<span class='fl gouwuche-name'>购物车</span>"+
                                        "<span class='iconfont  icon-shanchu fr ' onclick='emptyAll()'></span>"+
                                    "</li>");
        $(".mr").text("0");
        $("#gouwucheInfo").find(".num-emphasize").text("￥  0");
    }
    
    $("#choiceEmployee").click(function (){
        var gouwuche = $(".gouwu-li");
        var arrayObj = new Array();
        if (gouwuche.length == 0) {
            dialog("请选择项目！");
            return;
        }
        
        var number = $("#gouwucheInfo").find(".s-badge").text();
        var totilMoney = parseFloat(($("#gouwucheInfo").find(".num-emphasize").text()).replace("￥", ""));
        var totilValue = {"number":number, "totilMoney":totilMoney};
        var totilString = JSON.stringify(totilValue);
        for (var i = 0; i < gouwuche.length; i++) {
            var orderType = $(gouwuche[i]).attr("types");
            var projectId = $(gouwuche[i]).attr("name");
            var projectName = $(gouwuche[i]).find(".shop-name").text();
            var projectPriceStr = parseFloat(($(gouwuche[i]).find(".shop-price").text()).replace("￥", ""));
            var projectCount = $(gouwuche[i]).find(".mr").text();
            var projectImage = $(gouwuche[i]).find(".shop-price").attr("name");
            var objStr = {"orderType":orderType,"projectId":projectId,"projectName":projectName,"projectPriceStr":projectPriceStr,"projectCount":projectCount,"projectImage":projectImage};
            arrayObj.push(objStr);
        }
        
        var objString = JSON.stringify(arrayObj);
        
        var temp = document.createElement("form");
        temp.action = baseUrl + "staff/view/memberShiftMahjongServe";
        temp.method = "post";
        temp.style.display = "none";
        var opt = document.createElement("textarea");
        opt.name = "objString";
        opt.value = objString;
        var tpt = document.createElement("textarea");
        tpt.name = "totilString";
        tpt.value = totilString;
        var mpt = document.createElement("textarea");
        mpt.name = "memberBaseDtoStr";
        mpt.value = memberBaseDtoStr;
        
        var lpt = document.createElement("textarea");
        lpt.name = "orderId";
        lpt.value = orderId;
        
        temp.appendChild(opt);
        temp.appendChild(tpt);
        temp.appendChild(mpt);
        temp.appendChild(lpt);
        document.body.appendChild(temp);
        temp.submit();
    });
    
    $("#confirmBuy").click(function (){
        var gouwuche = $(".gouwu-li");
        var arrayObj = new Array();
        
        for (var i = 0; i < gouwuche.length; i++) {
            var orderType = $(gouwuche[i]).attr("types");
            var projectId = $(gouwuche[i]).attr("name");
            var projectName = $(gouwuche[i]).find(".shop-name").text();
            var projectPriceStr = parseFloat(($(gouwuche[i]).find(".shop-price").text()).replace("￥", ""));
            var projectCount = $(gouwuche[i]).find(".mr").text();
            var projectImage = $(gouwuche[i]).find(".shop-price").attr("name");
            var objStr = {"orderType":orderType,"projectId":projectId,"projectName":projectName,"projectPriceStr":projectPriceStr,"projectCount":projectCount,"projectImage":projectImage};
            arrayObj.push(objStr);
        }
        
        var objString = JSON.stringify(arrayObj);
        var arrayEmployee = new Array();
        var employeeObj = JSON.stringify(arrayEmployee);
        $.ajax({
            url : baseUrl + "staff/action/addOrder",
            type : "POST",
            data : "objString=" + objString + "&employeeObj=" + employeeObj + "&memberBaseDtoStr=" + memberBaseDtoStr + "&orderId=" + orderId,
            success : function(e){
                if (e.code != 0) {
                    dialog(e.msg);
                    return;
                }
                if (isEmpty(orderId)) {
                    dialog("开单成功, 1.5秒后跳转至接待中心...");
                }
                else {
                    dialog("新增成功, 1.5秒后跳转至接待中心...");
                }
                setTimeout(function(){
                    window.location.href = baseUrl + "staff/view/reception";
                }, 1500);
            }
        });
    });
</script>
</body>
</html>