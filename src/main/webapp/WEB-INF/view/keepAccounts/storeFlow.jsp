<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<body>

<div class="mainwrapper">
    <!--loading start-->
   <%@ include file="/loading.jsp" %>
    <!--loading end-->
   <%@ include file="/menu.jsp" %>

    <div class="rightpanel" style="margin-left: 200px;">
      <%@ include file="/top.jsp" %>

		<div class="maincontent">
		    <div class="contentinner">
		
		        <div class="widgetcontent">
		            <div class="more-toolbar" >
		                <div class="table-toolbar">
		                    <span class="font-size-16 btn-color">新增账单</span>
		                </div>
		                <div class="clearfix"></div>
		            </div><!--more-toolbar-->
		            <table class="table collect-money-table">
		                <thead>
		                <tr>
		                    <th class="dropdown-toggle" data-toggle="dropdown">记账金额</th>
		                    <th>流水类型</th>
		                    <th>记账部门</th>
		                    <th class="dropdown-toggle" data-toggle="dropdown">记账类别</th>
		                    <th>记账项目</th>
		                    <th>收支来源及去向</th>
		                    <th>经手人</th>
		                    <th>分摊月数</th>
		                </tr>
		                </thead>
		                <tbody>
		                <tr>
		                    <td><input type="text" name="flowAmount" class="input80" onkeyup="checkNum(this)"/></td>
		                    <td>
		                        <select data-placeholder="流水类型" name="flowType" class="chzn-select input120">
		                            <option value="">选择流水类型</option>
	                                <option value="2">收入</option>
	                                <option value="1">支出</option>
	                            </select>
		                    </td>
		                    
		                    <td>
		                        <select data-placeholder="记账部门" name="deptId" class="chzn-select input80" onchange="employeeValue(this)">
		                            <option value="">选择部门</option>
		                            <c:forEach items="${deptInfoDtoList}" var="deptInfoDto" varStatus="status">
		                               <option value="${deptInfoDto.deptId}">${deptInfoDto.deptName}</option>
		                            </c:forEach>
		                        </select>
		                    </td>
		                    
		                    <td>
		                        <select data-placeholder="记账类别" name="businessType" class="chzn-select input120">
		                              <option value="">选择记账类别</option>
		                              <c:forEach items="${businessTypeList}" var="businessType">
		                                 <option value="${businessType.codeName}">${businessType.codeName}</option>
		                              </c:forEach>
		                        </select>
		                    </td>
		                    <td>
		                        <select data-placeholder="记账项目" name="businessProject" class="chzn-select input120">
		                              <option value="">选择记账项目</option>
			                          <c:forEach items="${businessProjectList}" var="businessProject">
		                                 <option value="${businessProject.codeName}">${businessProject.codeName}</option>
		                              </c:forEach>
		                        </select>
		                    </td>
		                    <td>
		                        <select data-placeholder="收支来源及去向" name="flowSource" class="chzn-select input120">
		                             <option value="">选择收支来源及去向</option>
		                             <c:forEach items="${flowSourceList}" var="flowSource">
	                                    <option value="${flowSource.codeName}">${flowSource.codeName}</option>
	                                 </c:forEach>
		                        </select>
		                    </td>
		                    <td class="expend">
		                        <select data-placeholder="选择经手人" name="principalId" class="chzn-select input100">
		                            <option value="">选择经手人</option>
		                        </select>
		                    </td>
		                    <td><input type="text" name= "flowNum" class="input40 mr10" onkeyup="checkNum(this)"/></td>
		                </tr>
		                <tr>
		                    <td colspan="8">
		                       <input type="text" class="wp80 mr10" name="businessDesc" placeholder="备注说明"/>
		                        <button class="btn btn-primary input100" id="save">&nbsp;保&nbsp;&nbsp;存&nbsp;</button>
		                    </td>
		                </tr>
		                </tbody>
		            </table>
		        </div>
		
		        <div class="widgetcontent">
		            <div class="more-toolbar" >
		                    <div class="table-toolbar" id="container">
		                        <span class="font-size-16 btn-color mr10">收支明细</span>
		                        <span >${toDate}</span>
		                        <input type="text" class="datetimepickerBegin input80 ml10" id="begin_time" name="beginDate" daysOffset="0" placeholder="选择日期"/>
		                        <span>至</span>
		                        <input type="text" class="datetimepickerEnd input80" id="end_time" name="endDate" daysOffset="30" placeholder="选择日期"/>
		                        <button class="btn" id="findDate">查询</button>
		
		                        <div class="table-pagination fr">
		                            <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
		                                10 <span class="iconfa-caret-down afont"></span>
		                            </button>
		                            <ul class="dropdown-menu">
		                                <li><a href="javascript:changePageSize(10)">10</a></li>
		                                <li><a href="javascript:changePageSize(20)">20</a></li>
		                                <li><a href="javascript:changePageSize(50)">50</a></li>
		                                <li><a href="javascript:changePageSize(100)">100</a></li>
		                            </ul>
		                            <div class="left-page" id="previousPageButton"><i class="FontAwesome iconfa-caret-left afont"></i></div>
		                            <div class="right-page" id="nextPageButton"><i class="FontAwesome iconfa-caret-right afont"></i></div>
		                        </div><!--table-pagination-->
		                    </div><!--table-toolbar-->
		                    <div class="clearfix"></div>
		                </div><!--more-toolbar-->
		
		            <table class="table table-bordered table-striped collect-money-table">
		                <thead>
		                <tr>
		                    <th class="dropdown-toggle" data-toggle="dropdown"> 收支类别</th>
		                    <th class="dropdown-toggle" data-toggle="dropdown">收支项目</th>
		                    <th>收支来源/去向</th>
		                    <th>收入金额</th>
		                    <th>支出金额</th>
		                    <th>经手人</th>
		                    <th>记账人</th>
		                    <th>记账时间</th>
		                    <th>分期月数</th>
		                    <th class= "width150">备注说明</th>
		                    <th></th>
		                </tr>
		                </thead>
		                <tbody id = "storeflowTbody">
		                  <c:forEach items="${page.results }" var="storeflow">
		                        <tr id="${storeflow.flowId}">
			                        <td>${storeflow.businessType}</td>
			                        <td>${storeflow.businessProject}</td>
			                        <td>${storeflow.flowSource}</td>
			                        <td><c:if test="${storeflow.flowType == 2}">${storeflow.flowAmount}</c:if></td>
			                        <td class="expend"><c:if test="${storeflow.flowType == 1}">${storeflow.flowAmount}</c:if></td>
			                        <td>${storeflow.principal.name}</td>
			                        <td>${storeflow.operator.name}</td>
			                        <td>${storeflow.flowTime}</td>
			                        <td>${storeflow.flowNum}</td>
			                        <td class="ellipsis-text width150" data-toggle="tooltip" data-placement="top" title="${storeflow.businessDesc}">${storeflow.businessDesc}</td>
			                        <td>
			                            <i class="iconfa-trash project-icon" onclick="deleteStoreflow(${storeflow.flowId})"></i>
			                        </td>
			                    </tr>
		                  </c:forEach>
		                </tbody>
		                <%-- <tfoot>
		                <tr>
		                    <td colspan="10">
		                        <button class="btn fr">
		                            <img src="<%=basePath %>images/out_icon.png" alt="" class="vatp"/>
		                            <span class="ml10">导出</span>
		                        </button>
		                    </td>
		                </tr>
		                </tfoot> --%>
		            </table>
		        </div>
		    </div>
		</div>

<script type="text/javascript">
	//获取加载页面时的页码信息
	var pageNo = "${page.pageNo}";
	var pageSize = "${page.pageSize}";
	var totalPage = "${page.totalPage}";
	var beginDate = "${beginDate}";
	var endDate = "${endDate}";
</script>
<script type="text/javascript" src="<%=basePath %>js/keepAccounts/storeFlow.js"></script>
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

</body>
</html>