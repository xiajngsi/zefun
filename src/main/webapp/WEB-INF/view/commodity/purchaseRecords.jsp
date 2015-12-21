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
                            <label for="">新增进货记录</label>
                        </div>
                        <div class="clearfix"></div>
                    </div><!--more-toolbar-->
                    <form id="purchaseRecodesForm">
                        <table class="table collect-money-table">
                            <thead>
                                <tr>
                                    <th>供应商名称</th>
                                    <th>商品</th>
                                    <th>进货价格</th>
                                    <th>进货数量</th>
                                    <th>进货时间</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <select data-placeholder="选择供应商" class="chzn-select input-medium" name="supplierId">
                                            <c:forEach var="supplierInfo" items="${supplierInfoList }" varStatus="status">
                                                <option value="${supplierInfo.supplierId }">${supplierInfo.supplierName }</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select data-placeholder="选择商品" class="chzn-select input-medium" name="goodsId">
                                            <c:forEach var="goodsInfo" items="${goodsInfoList }" varStatus="status">
                                                <option value="${goodsInfo.goodsId }">${goodsInfo.goodsName }</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input type="text" class="input80" name="purchasePrice" datatype="n" errormsg="进货价：请输入大于1以上的数字！" nullmsg="请填写进货价！"/></td>
                                    <td><input type="number" class="input80" name="purchaseCount" datatype="n" errormsg="进货数量：请输入大于1以上的数字！" nullmsg="请填写进货数量！"/></td>
                                    <td><input id="recodrsTime" type="text" name="purchaseTime" class="timepicker width100"/>
                                    <button class="btn btn-primary input100 fr" type="button" id="submitBtn">&nbsp;保&nbsp;&nbsp;存&nbsp;</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
		        
		       <%--  <div class="more-toolbar" >
                    <div class="table-toolbar">
                        <form id="goodsForm">
                           	 商品库存信息
	                        <input type="search" placeholder="商品名称/类别/品牌" name="goodsStockName" />
	                        <a href="javascript:void(0)" class="button-search btn" style="margin-left: -10px;" onclick="changePage();">查询</a>
	                        <div class="table-pagination">
	                            <button data-toggle="dropdown" class="btn dropdown-toggle perpage goodsStock">
	                                ${goodsStockPage.pageSize } <span class="iconfa-caret-down afont"></span>
	                            </button>
	                            <ul class="dropdown-menu">
	                                <li><a href="javascript:changePageSize(5)">5</a></li>
	                                <li><a href="javascript:changePageSize(10)">10</a></li>
	                                <li><a href="javascript:changePageSize(20)">20</a></li>
	                                <li><a href="javascript:changePageSize(50)">50</a></li>
	                                <li><a href="javascript:changePageSize(100)">100</a></li>
	                            </ul>
	                            <div class="left-page" id="previousPageButton" onclick="previous()"><i class="FontAwesome iconfa-caret-left afont"></i></div>
	                            <div class="right-page" id="nextPageButton" onclick="next()"><i class="FontAwesome iconfa-caret-right afont"></i></div>
	                        </div>
                        </form>
                    </div>
                    <div class="clearfix"></div>
                </div> 
		        
		        <table class="table table-bordered table-striped goodsStock-table">
		            <thead>
			            <tr>
			                <th>商品类别</th>
			                <th>商品品牌</th>
			                <th>货品名称</th>
			                <th>平均成本</th>
			                <th>上批成本</th>
			                <th>销售价格</th>
			                <th>当前库存</th>
			                <th>报警数量</th>
			            </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var="goodsInfoDto" items="${goodsStockPage.results }" varStatus="status">
		            		<tr>
				            	<td>${goodsInfoDto.categoryName }</td>
				            	<td>${goodsInfoDto.brandName }</td>
				            	<td>${goodsInfoDto.goodsName }</td>
				            	<td>
				            		${fn:substring(goodsInfoDto.goodsPurchaseRecordDto.avgCost,0,5)}
				            	</td>
				            	<td>
				            		${fn:substring(goodsInfoDto.goodsPurchaseRecordDto.prevCost,0,5)}
				            	</td>
				            	<td>${goodsInfoDto.goodsPrice }</td>
				            	<td>${goodsInfoDto.goodsStock }</td>
				            	<td>${goodsInfoDto.warnStock }</td>
			            	</tr>
		            	</c:forEach>
		            </tbody>
		        </table>
		        <div class="clearfix"></div><br/>
		        
		        --%>
		        <!-- 进货记录列表 -->
		        <div class="more-toolbar" >
                    <div class="table-toolbar">
                        <form>
                            商品进货记录
                            <input type="search" placeholder="供应商/商品名称" name="goodsName" />
                            <a href="javascript:void(0)" class="button-search btn" style="margin-left: -10px;" onclick="changePageR();">查询</a>
                            <div class="table-pagination">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage goodspPurchase">
                                    ${purchaseRecordsPage.pageSize } <span class="iconfa-caret-down afont"></span>
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
		                  <th>供应商名称</th>
		                  <th>商品</th>
		                  <th>进货价格</th>
		                  <th>进货数量</th>
		                  <th>进货时间</th>
		                  <th>进货人</th>
		              </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var="goodsPurchaseRecordDto" items="${purchaseRecordsPage.results }" varStatus="status">
		            		<tr>
				                <td>${goodsPurchaseRecordDto.supplierName }</td>
				                <td>${goodsPurchaseRecordDto.goodsName }</td>
				                <td>${goodsPurchaseRecordDto.purchasePrice }</td>
				                <td>${goodsPurchaseRecordDto.purchaseCount }</td>
				                <td>${goodsPurchaseRecordDto.purchaseTime }</td>
				                <td>${goodsPurchaseRecordDto.employeeName }</td>
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

<div class="modal hide" id="select-wordimg-modal-picture3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 580px;">
            
        </div>
    </div>
</div>

<script>
	//获取加载页面时的页码信息
	var pageNo = "${goodsStockPage.pageNo}";
	var pageSize = "${goodsStockPage.pageSize}";
	var totalPage = "${goodsStockPage.totalPage}";
	
	var pageNoR = "${purchaseRecordsPage.pageNo}";
	var pageSizeR = "${purchaseRecordsPage.pageSize}";
	var totalPageR = "${purchaseRecordsPage.totalPage}";
</script>
<script src="<%=basePath %>js/commodity/goodsStock.js"></script>

</body>
</html>