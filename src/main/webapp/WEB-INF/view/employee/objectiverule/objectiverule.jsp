<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
 <div class="maincontent">
    <div class="contentinner">

        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <label for="">新增目标</label>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table collect-money-table">
                <thead>
                <tr>
                    <th>目标</th>
                    <th>区间</th>
                    <th>奖惩</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                    <input type="hidden" id="updateruleId">
                        <select data-placeholder="选择目标"  id="objectiveProject" class="chzn-select-search input-small" style="width: 150px;">
                            <option value="1">劳动业绩目标</option>
                            <option value="2">指定业绩目标</option>
                            <option value="3">套餐销售目标</option>
                            <option value="4">商品销售目标</option>
                            <option value="5">开卡充值目标</option>
                        </select>
                    </td>
                    <td>
                    <select data-placeholder="选择类型"  id="choiceType" class="chzn-select-search input80" onchange="showtype()">
                            <option value="1">按金额</option>
                            <option value="2">按比例</option>
                        </select>
                        <select data-placeholder="选择大小"  id="contrastType" class="chzn-select-search input60">
                            <option value="1">大于</option>
                            <option value="2">小于</option>
                        </select>
                        <input type="text" id="section"  placeholder="区间比例" class="input60"/><span class="percent-symbol" id="type1" style="display: none">元</span><span class="percent-symbol" id="type2" style="display: none">%</span>
                    </td>
                    <td>
                        <input type="checkbox" id="rewards" name="check-1" value="1" class="lcs_check" checked="checked" autocomplete="on" />
                        <select data-placeholder="选择"  class="chzn-select-search input-small" id="type">
                            <option value="1">固定金额</option>
                            <option value="2">薪资比例</option>
                        </select>
                        <input type="text" class="input80" id="money" placeholder="奖励金额"/>
                        <button class="btn btn-primary input100 fr" id="saveinfo"onclick="saveinfo()">&nbsp;保&nbsp;&nbsp;存&nbsp;</button>
                        <button class="btn btn-primary input100 ml5 fr" id="update" onclick="updateinfo()" style="display: none">修改提交</button>
                        <button class="btn btn-primary input100 fr" onclick="quxiao()" id="quxiao" style="display: none">取消修改</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div id="custom-toolbar" class="more-toolbar">
            <div class="table-toolbar">
                <select data-placeholder="选择目标"  id="search" class="chzn-select-search input-small" style="width: 150px;">
                			<option value="">全部</option>
                            <option value="1">劳动业绩目标</option>
                            <option value="2">指定业绩目标</option>
                            <option value="3">套餐销售目标</option>
                            <option value="4">商品销售目标</option>
                            <option value="5">开卡充值目标</option>
                        </select>
                <button class="button-search btn" style="margin-left: -10px;" onclick="changePage()">查询</button>
                <div class="table-pagination fr">
                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                    10 <span class="iconfa-caret-down afont"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="javascript:changePageSize(5)">5</a></li>
		                <li><a href="javascript:changePageSize(10)">10</a></li>
		                <li><a href="javascript:changePageSize(20)">20</a></li>
		                <li><a href="javascript:changePageSize(50)">50</a></li>
		                <li><a href="javascript:changePageSize(100)">100</a></li>
                </ul>
                <div class="left-page" id="previousPageButton" onclick="previous()"><span class="FontAwesome iconfa-caret-left afont"></span></div>
		            <div class="right-page" id="nextPageButton" onclick="next()"><span class="FontAwesome iconfa-caret-right afont"></span></div>
            </div>
            </div>
            
        </div>
		<div class="clearfix"></div>
        <table class="table table-bordered table-striped collect-money-table1">
            <thead>
            <tr>
                <th>目标</th>
                <th>区间</th>
                <th>奖惩</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.results}" var="list">
            <tr id="${list.ruleId}">
                <td>${list.projectName}</td>
                <td>${list.choiceTypeName}   ${list.contrastTypeName}  ${list.section}<c:if test="${list.choiceType==1}">元</c:if><c:if test="${list.choiceType==2}">%</c:if></td>
                <td>${list.rewardsName} <c:if test="${list.type==2}">薪资的${list.money}%</c:if><c:if test="${list.type==1}">${list.money}元</c:if></td>
            <td>
              <span class="iconfa-pencil project-icon" onclick="openedit(${list.ruleId})" ></span>
              <span class="iconfa-trash project-icon" onclick="deleteinfo(${list.ruleId})"></span>
              </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<script>
jQuery('.lcs_check').lc_switch('奖励', '扣钱');
jQuery('body').delegate('.lcs_check', 'lcs-statuschange', function() {
    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
    if(status == 'checked'){
    	jQuery(this).val(1);
    }else{
    	jQuery(this).val(2);
    }
});

jQuery(function(){
	showtype();
})
var pageNo = "${page.pageNo}";
var pageSize = "${page.pageSize}";
var totalPage = "${page.totalPage}"


</script>
<script type="text/javascript" src="<%=basePath %>js/employee/objectiverule.js"></script>

    </div>
    
    
    <!--RIGHT PANEL结束 -->


</div><!--mainwrapper-->

</body>
</html>