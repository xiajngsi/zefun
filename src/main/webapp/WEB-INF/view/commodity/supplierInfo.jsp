<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		                    <label for="">新增供应商</label>
		                </div>
		                <div class="clearfix"></div>
		            </div><!--more-toolbar-->
		            <form id="supplierInfoForm">
			            <table class="table collect-money-table">
			                <thead>
				                <tr>
				                    <th class="dropdown-toggle" data-toggle="dropdown">供应商名称</th>
				                    <th class="dropdown-toggle" data-toggle="dropdown">经营品牌</th>
				                    <th>商品类别</th>
				                    <th>联系人</th>
				                    <th>手机号</th>
				                </tr>
			                </thead>
			                <tbody>
				                <tr>
				                    <td>
				                    	<input type="text" class="input80" name="supplierName" id="supplierName" datatype="s2-18" errormsg="供应商名称至少2个字符,最多18个字符！" nullmsg="供应商名称不能为空!" />
				                    	<input type="hidden" class="input80" name="supplierId" id="supplierId"/>
				                    </td>
				                    <td>
				                        <select data-placeholder="选择品牌" class="chzn-select input-xlarge" name="supplyBrand" id="supplyBrand" multiple="multiple" datatype="*" errormsg="请选择经营品牌！" nullmsg="请选择经营品牌！">
				                            <c:forEach var="goodsBrand" items="${goodsBrandList }" varStatus="status">
				                        		<option value="${goodsBrand.codeNo }">${goodsBrand.codeName }</option>
				                        	</c:forEach>
				                        </select>
				                    </td>
				                    <td>
				                        <select data-placeholder="选择商品类别" class="chzn-select input-xlarge" name="supplyCategory" id="supplyCategory" multiple="multiple" datatype="*" errormsg="请选择商品类别！" nullmsg="请选择商品类别！">
				                            <c:forEach var="goodsCategory" items="${goodsCategoryList }" varStatus="status">
				                        		<option value="${goodsCategory.categoryId }">${goodsCategory.categoryName }</option>
				                        	</c:forEach>
				                        </select>
				                    </td>
				                    <td class="expend"><input type="text" class="input80" name="linkName" id="linkName" datatype="*" errormsg="请输入联系人！" nullmsg="请输入联系人！"/></td>
				                    <td><input type="text" class="input80" name="linkPhone" id="linkPhone" datatype="m" errormsg="手机号码格式错误！" nullmsg="请输入手机号！"/>
				                    <button class="btn btn-primary input80 fr" type="button" id="submitBtn" >&nbsp;保&nbsp;&nbsp;存&nbsp;</button></td>
				                </tr>
			                </tbody>
			            </table>
		            </form>
		        </div>
		        
		        <div class="more-toolbar" >
		            <div class="table-toolbar">
		                供应商列表
		                <input type="search" placeholder="供应商名称/品牌/类别" id="inputSupplierInfoName" />
		                <button class="button-search btn" style="margin-left: -10px;" onclick="querySupplierInfo();">查询</button>
		            	<div class="table-pagination">
			                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
			                    ${page.pageSize } <span class="iconfa-caret-down afont"></span>
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
		            </div>
		            <div class="clearfix"></div>
		        </div>
		        <table class="table table-bordered table-striped collect-money-table">
		            <thead>
			            <tr>
			                <th>供应商名称</th>
			                <th class="drop-th">经营品牌
<!-- 			                    <span class="dropdown-toggle" data-toggle="dropdown">经营品牌 <span class="caret"></span></span> -->
<!-- 			                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu"> -->
<%-- 			                        <c:forEach var="goodsBrand" items="${goodsBrandList }" varStatus="status"> --%>
<%-- 		                        		<li value="${goodsBrand.brandId }"><a tabindex="-1" href="#" onclick="changePage('',${goodsBrand.brandId });">${goodsBrand.brandName }</a></li> --%>
<!-- 		                        		<li class="divider"></li> -->
<%-- 		                        	</c:forEach> --%>
<!-- 			                    </ul> -->
			                </th>
			                <th class="drop-th">供货类别 
<!-- 			                    <span class="dropdown-toggle" data-toggle="dropdown">供货类别 <span class="caret"></span></span> -->
<!-- 			                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu"> -->
<%-- 			                        <c:forEach var="goodsCategory" items="${goodsCategoryList }" varStatus="status"> --%>
<%-- 		                        		<li value="${goodsCategory.categoryId }"><a tabindex="-1" href="#" onclick="changePage('','',${goodsCategory.categoryId });">${goodsCategory.categoryName }</a></li> --%>
<!-- 		                        		<li class="divider"></li> -->
<%-- 		                        	</c:forEach> --%>
<!-- 			                    </ul> -->
			                </th>
			                <th>联系人</th>
			                <th>手机号</th>
			                <th></th>
			            </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var="supplierInfo" items="${page.results }" varStatus="status">
		            		<tr>
				                <td>${supplierInfo.supplierName }</td>
				                <td>${supplierInfo.supplyBrandStr }</td>
				                <td>${supplierInfo.supplyCategoryStr }</td>
				                <td>${supplierInfo.linkName }</td>
				                <td>${supplierInfo.linkPhone }</td>
				                <td>
				                	<i class="iconfa-pencil project-icon" onclick="showEditSupplierInfo(${supplierInfo.supplierId});"></i>
			                        <i class="iconfa-trash project-icon" onclick="deleteSupplierInfo(${supplierInfo.supplierId});"></i>
				                </td>
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
	var pageNo = "${page.pageNo}";
	var pageSize = "${page.pageSize}";
	var totalPage = "${page.totalPage}";
</script>
<script src="<%=basePath %>js/commodity/supplierInfo.js"></script>

</body>
</html>