<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/head.jsp" %>
<body>

<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
      	<%@ include file="/top.jsp" %>
      	<!-- 页面代码 -->
      	
		<div class="maincontent">
		    <div class="contentinner">
		        <div class="widgetcontent">
                    <div class="more-toolbar" >
                        <div class="table-toolbar">
                            <label for="">新增出货记录</label>
                        </div>
                        <div class="clearfix"></div>
                    </div><!--more-toolbar-->
                    <form id="purchaseRecodesForm">
                        <table class="table collect-money-table">
                            <thead>
                                <tr>
                                    <th>商品</th>
                                    <th>出货人员</th>
                                    <th>出货数量</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <select data-placeholder="选择商品" class="chzn-select input-medium" name="goodsId">
                                            <c:forEach var="goodsInfo" items="${goodsInfoList }" varStatus="status">
                                                <option value="${goodsInfo.goodsId }" goodsName="${goodsInfo.goodsName }">${goodsInfo.goodsName }</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select data-placeholder="选择员工" class="chzn-select input-medium" name="employeeId">
                                            <c:forEach var="employeeInfo" items="${employeeInfos }" varStatus="status">
                                                <option value="${employeeInfo.employeeId }" employeeName="${employeeInfo.name }">${employeeInfo.name }</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                    <input type="number" class="input80" name="shipmentCount" ></td>
                                    <td><a class="btn btn-primary input100 fr" href="javascript:saveShipMendRecord()" type="button" >&nbsp;保&nbsp;&nbsp;存&nbsp;</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
		        
		        <div class="clearfix"></div><br/>
		        
		    	<!-- 出货记录列表 -->
		        <div class="more-toolbar" >
                    <div class="table-toolbar">
                        <form>
                           	 商品出货记录
                            <input type="search" placeholder="商品名称" name="goodsName" />
                            <a href="javascript:void(0)" class="button-search btn" style="margin-left: -10px;" onclick="changePageR();">查询</a>
                            <div class="table-pagination">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage goodspPurchase" >
                                    ${page.pageSize } <span class="iconfa-caret-down afont"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="javascript:changePageSizeR(5)">5</a></li>
                                    <li><a href="javascript:changePageSizeR(10)">10</a></li>
                                    <li><a href="javascript:changePageSizeR(20)">20</a></li>
                                    <li><a href="javascript:changePageSizeR(50)">50</a></li>
                                    <li><a href="javascript:changePageSizeR(100)">100</a></li>
                                </ul>
                                <div class="left-page" id="previousPageButtonR" onclick="previousR()"><i class="FontAwesome iconfa-caret-left afont"></i></div>
                                <div class="right-page" id="nextPageButtonR" onclick="nextR()"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                            </div>
                        </form>
                    </div>
                    <div class="clearfix"></div>
                </div>
				
		        <table class="table table-bordered table-striped goodsPurchaseRecord-table">
		            <thead>
		              <tr>
		                  <th>商品</th>
		                  <th>出货数量</th>
		                  <th>出货人</th>
		                  <th>出货时间</th>
		                  <th>最后操作人</th>
		              </tr>
		            </thead>
		            <tbody id="refresh">
		            	<c:forEach var="shipMentRecord" items="${page.results }" varStatus="status">
		            		<tr>
				                <td>${shipMentRecord.goodsName }</td>
				                <td>${shipMentRecord.shipmentCount }</td>
				                <td>${shipMentRecord.employeeName }</td>
				                <td>${shipMentRecord.shipmentTime }</td>
				                <td>${shipMentRecord.operatorName }</td>
				            </tr>
		            	</c:forEach>
		            </tbody>
		        </table>
		        
		    </div>
		</div>
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<script>
//获取加载页面时的页码信息
var pageNoR = "${page.pageNo}";
var pageSizeR = "${page.pageSize}";
var totalPageR = "${page.totalPage}";
var employee = eval("("+'${employee}'+")");
</script>
<script>
function saveShipMendRecord(){
	var goodsId = jQuery("select[name='goodsId']").val();
	var goodsName = jQuery("select[name='goodsId']").find("option:selected").attr("goodsName");
	var employeeId = jQuery("select[name='employeeId']").val();
	var employeeName = jQuery("select[name='employeeId']").find("option:selected").attr("employeeName");
	var shipmentCount = jQuery("input[name='shipmentCount']").val();
	if(shipmentCount == ""){
		dialog("请输入出货数量");
		return;
	}
	if(shipmentCount <= 0){
		dialog("出货数量必须是整数");
		return;
	}
	var date = "goodsId="+goodsId+"&goodsName="+goodsName+"&employeeId="+employeeId+"&employeeName="+employeeName+"&shipmentCount="+shipmentCount;
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"goodsInfo/save/shipment/record",
        data: date,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
        	console.log(data.msg);
        	if(data.code == 0){
        		dialog("出货成功");
        		for (var i = 0; i < employee.length; i++) {
					if(employee[i].employeeId == data.msg.operatorId){
						console.log(employee[i].employeeName);
						var str = '<tr>'+
		                '<td>'+data.msg.goodsName+'</td>'+
		                '<td>'+data.msg.shipmentCount+'</td>'+
		                '<td>'+data.msg.employeeName+'</td>'+
		                '<td>'+data.msg.shipmentTime+'</td>'+
		                '<td>'+employee[i].name+'</td>'+
		            '</tr>';
		            jQuery("#refresh").append(str);
					}
				}
        	}else{
        	dialog(data.msg);
        	}
        }
    });
}
/** 进货记录分页 */
//上一页
function previousR(){
	if(pageNoR <= 1){
		return;
	}
	pageNoR--;
	changePageR();
}

//下一页
function nextR(){
	if(pageNoR >= totalPageR){
		return;
	}
	pageNoR++;
	changePageR();
}

//更改每页显示数量
function changePageSizeR(size){
	pageNoR = 1;
	pageSizeR = size;
	jQuery(".perpage").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePageR();
}

//分页处理
function changePageR(){
	var data = "pageNo=" + pageNoR + "&pageSize=" + pageSizeR;
	var goodsName = jQuery("input[name='goodsName']").val();
	if(goodsName!=""&&typeof(goodsName)!="undefined")data = data + "&goodsName="+goodsName;
	console.log(data);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "goodsInfo/serch/shipment/record",
		data : data,
		dataType : "json",
		success : function(e){
			console.log(e.msg.results);
			refreshTableDataR(e.msg.results);
		}
	});
}
function refreshTableDataR(records){
	jQuery("#refresh").empty();
	for (var i = 0; i < records.length; i++) {
		var str= '<tr><td>'+records[i].goodsName+' </td><td>'+records[i].shipmentCount+' </td>'+
		'<td>'+records[i].employeeName+' </td><td>'+records[i].shipmentTime+' </td><td>'+records[i].operatorName+'</td></tr>';
		jQuery("#refresh").append(str);
	}
}
</script>
</body>
</html>