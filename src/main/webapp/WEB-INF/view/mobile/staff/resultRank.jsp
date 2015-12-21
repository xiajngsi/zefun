<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title></title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/star-rating.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/mobile.css"/>
    <script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/star-rating.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/member-mobile.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/yg.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
  </head>
	<body>
	<div class="wrap">
	   <div class="content">
	        <div class="yg-performance-order">
	
	        <div class="li-titlee h90 line-height90 text-center bg-white s-modal-control" data-target="#modal-performance" id="wayOutId" onclick="updateChecked(this)">${wayOut} 
	           <i class="iconfont icon-zhankai" style="font-size: 25px;padding-left: 15px;"></i>
	        </div>
	        <c:forEach items="${sumlist}" var="sumlist" varStatus="status">
	           <div class="part">
			        <div class=" toggle-ctl  ul-title">
			            <div class="yellow-box"><span class="font-333">${(status.index)+ 1}</span></div>
			            <span class="ml5p">${sumlist.employeeInfo.name}</span>
			            <span class="fr">${sumlist.sumprice}元 
			               <i class="iconfont icon-zhankai" style="padding-left: 15px;"></i>
			            </span>
			        </div>
			        <table class="table" style="display: none">
			            <tbody>
			            <tr class="h60 line-height60 font-gray font-999">
			                <td>均值</td>
			                <td class="text-center">关键指标</td>
			                <td class="text-right">我的表现</td>
			            </tr>
			            <tr class="h90 line-height90">
			                <td class="font-gray">${sumlist.mpprice}元</td>
			                <td class="font-blue text-center">劳动业绩</td>
			                <td class="font-333 text-right">${sumlist.pprice}元</td>
			            </tr>
			            <tr class="h90 line-height90">
			                <td class="font-gray">${sumlist.mcprice}元</td>
			                <td class="font-blue text-center">商品</td>
			                <td class="font-red text-right">${sumlist.cprice}元</td>
			            </tr>
			            <tr class="h90 line-height90">
			                <td class="font-gray">${sumlist.mmean}元</td>
			                <td class="font-blue text-center">客单价</td>
			                <td class="font-333 text-right">${sumlist.mean}元</td>
			            </tr>
			            <tr class="h90 line-height90">
			                <td class="font-gray">${sumlist.mratio}%</td>
			                <td class="font-blue text-center">制定客比例</td>
			                <td class="font-333 text-right">${sumlist.ratio}%</td>
			            </tr>
			            </tbody>
			        </table>
			    </div>
	        </c:forEach>
	</div>
	
	
	<div class="footer">
	    <ul>
	        <li>
	            <a href="<%=basePath%>employeeCenter/view/home/1002/2">
	                <span class="iconfont icon-jiedai"></span>
	                <span class="icon-style">接待</span>
	            </a>
	        </li>
	        <li class="active">
	            <a href="<%=basePath%>staff/view/resultRank/1002/2">
	                <span class="iconfont icon-yeji"></span>
	                <span class="icon-style">业绩</span>
	            </a>
	        </li>
	        <li >
	            <a href="<%=basePath%>mobile/test?view=mobile/stafftest/staffCentre">
	                <span class="iconfont icon-wode"></span>
	                <span class="icon-style">我的</span>
	            </a>
	        </li>
	        <li >
	            <a href="<%=basePath%>mobile/test?view=mobile/stafftest/otherOperate">
	                <span class="iconfont icon-gengduo2"></span>
	                <span class="icon-style">更多</span>
	            </a>
	        </li>
	    </ul>
	</div>
	
	<!--修改排行方式-->
	<div class="s-modal hide s-modal-miss" id="modal-performance">
	    <div class="s-modal-wrap">
	        <div class="modal-performance">
	            <div class="li-titlee h90 line-height90 text-center bg-white s-modal-miss"  id = "wayOutIds">${wayOut} 
	                <i class="iconfont icon-zhankai" style="font-size: 25px;padding-left: 15px;"></i>
	            </div>
	            <ul>
	                <li class="li-titlee h90 line-height90 bg-white s-modal-miss" name = "orderType" onclick="wayOutLI(this,0)">
	                                                      日排行<i class="blue-word iconfont icon-yiwancheng fr "></i>
	                </li>
	                <li class="li-titlee h90 line-height90 padding-lf bg-white s-modal-miss" name = "orderType" onclick="wayOutLI(this,1)">
	                                                      周排行<i class="blue-word iconfont icon-yiwancheng fr hide"></i>
	                </li>
	                <li class="li-titlee h90 line-height90 padding-lf bg-white s-modal-miss" name = "orderType" onclick="wayOutLI(this,2)">
	                                                       月排行<i class="blue-word iconfont icon-yiwancheng fr hide"></i>
	                </li>
	                <li class="li-titlee h90 line-height90 padding-lf bg-white s-modal-miss " name = "orderType" onclick="wayOutLI(this,3)">
	                                                       年排行<i class="blue-word iconfont icon-yiwancheng fr hide"></i>
	                </li>
	
	            </ul>
	        </div>
	    </div>
	</div>
	
	<script>
	    $(function(){
	        $(".toggle-ctl").on("click", function(){
	            var th = $(this);
	            var thParent = th.parent();
	            var tar = thParent.find(".table");
	            if(tar.is(":visible")){
	                th.find(".iconfont").removeClass("icon-shouqi").addClass("icon-zhankai");
	            }else{
	                th.find(".iconfont").removeClass("icon-zhankai").addClass("icon-shouqi");
	            }
	                tar.toggle();
	        });
	
	
	    });
	    
	    function updateChecked(obj){
	    	var orderType = $("li[name='orderType']");
	    	for(var i = 0;i < orderType.length;i++){
	    		if(!$(orderType[i]).find(".icon-yiwancheng").hasClass("hide")){
	    			$(orderType[i]).find(".icon-yiwancheng").addClass("hide");
	    		}
	    		if($.trim($(orderType[i]).text()) == $.trim($(obj).text())){
	    			$(orderType[i]).find(".icon-yiwancheng").removeClass("hide");
	    		}
	    	}
	    	
	    }
	    
	    function wayOutLI(obj,type){
	    	$("#wayOutId").text($(obj).text());
	    	$("#wayOutIds").text($(obj).text());
	    	window.location.href = baseUrl +"staff/view/resultRank/1002/2?type="+type;
	    }
	</script>
	
	 </div>
	 </div>
   </body>
</html>