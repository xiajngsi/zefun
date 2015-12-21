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
                            <label for="">新增品牌</label>
                        </div>
                        <div class="clearfix"></div>
                    </div><!--more-toolbar-->
                    <form id="purchaseRecodesForm">
                        <table class="table collect-money-table">
                            <thead>
                                <tr>
                                    <th>品牌名称</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="text" class="input80" name="brandName" /></td>
                                    <td><a class="btn btn-primary input100 fr" href="javascript:saveBrand()" type="button" >&nbsp;保&nbsp;&nbsp;存&nbsp;</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
		        
		        <div class="clearfix"></div><br/>
		        
		    	<!-- 商品品牌 -->
		        <div class="more-toolbar" >
                    <div class="table-toolbar">
                        <form>
                           	 商品品牌
                            <input type="search" placeholder="商品名称" name="brandName" id="brandName"/>
                            <a href="javascript:void(0)" class="button-search btn" style="margin-left: -10px;" onclick="changePageB();">查询</a>
                            <div class="table-pagination">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage goodspPurchase" >
                                    ${page.pageSize } <span class="iconfa-caret-down afont"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="javascript:changePageSizeB(5)">5</a></li>
                                    <li><a href="javascript:changePageSizeB(10)">10</a></li>
                                    <li><a href="javascript:changePageSizeB(20)">20</a></li>
                                    <li><a href="javascript:changePageSizeB(50)">50</a></li>
                                    <li><a href="javascript:changePageSizeB(100)">100</a></li>
                                </ul>
                                <div class="left-page" id="previousPageButtonR" onclick="previousB()"><i class="FontAwesome iconfa-caret-left afont"></i></div>
                                <div class="right-page" id="nextPageButtonR" onclick="nextB()"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                            </div>
                        </form>
                    </div>
                    <div class="clearfix"></div>
                </div>
				
		        <table class="table table-bordered table-striped goodsPurchaseRecord-table">
		            <thead>
		              <tr>
		                  <th>品牌名称</th>
		                  <th>创建时间</th>
		                  <th>最后操作人</th>
		              </tr>
		            </thead>
		            <tbody id="refresh">
		            	<c:forEach var="goodsBrand" items="${page.results }" varStatus="status">
		            		<tr>
				                <td>${goodsBrand.brandName }</td>
				                <td>${goodsBrand.createTime }</td>
				                <td>${goodsBrand.employeeName }</td>
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
	var pageNoB = "${page.pageNo}";
	var pageSizeB = "${page.pageSize}";
	var totalPageB = "${page.totalPage}";
</script>
<script>
function saveBrand(){
	var brandName = jQuery("input[name='brandName']").val();
	if(brandName == ""){
		dialog("请输入品牌名称");
		return;
	}
	var date = "brandName="+brandName;
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"goodsInfo/save/brand",
        data: date,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
        	dialog("新增成功");
        	var records = data.msg.results;
        	for (var i = 0; i < records.length; i++) {
        		var str= '<tr><td>'+records[i].brandName+'<td>'+records[i].createTime+' </td><td>'+records[i].employeeName+' </td></tr>';
        		jQuery("#refresh").append(str);
        	}
        }
    });
}
/** 进货记录分页 */
//上一页
function previousB(){
	if(pageNoB <= 1){
		return;
	}
	pageNoB--;
	changePageB();
}

//下一页
function nextB(){
	if(pageNoB >= totalPageB){
		return;
	}
	pageNoB++;
	changePageB();
}

//更改每页显示数量
function changePageSizeB(size){
	pageNoB = 1;
	pageSizeB = size;
	jQuery(".perpage").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePageB();
}

//分页处理
function changePageB(){
	var data = "pageNo=" + pageNoB + "&pageSize=" + pageSizeB;
	var brandName = jQuery("#brandName").val();
	if(brandName!=""&&typeof(brandName)!="undefined")data = data + "&brandName="+brandName;
	console.log(data);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "goodsInfo/serch/brand",
		data : data,
		dataType : "json",
		success : function(e){
			console.log(e.msg.results);
			refreshTableDataB(e.msg.results);
		}
	});
}
function refreshTableDataB(records){
	jQuery("#refresh").empty();
	for (var i = 0; i < records.length; i++) {
		var str= '<tr><td>'+records[i].brandName+'<td>'+records[i].createTime+' </td><td>'+records[i].employeeName+' </td></tr>';
		jQuery("#refresh").append(str);
	}
}
</script>
</body>
</html>