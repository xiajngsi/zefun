<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
 <style>
    .hide{
        display: none!important;
    }
</style>
<body>
<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp"%>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp"%>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
        <%@ include file="/top.jsp"%>
		<div class="maincontent">
		    <div class="contentinner">
		        <!-- <div class="widgetcontent">
		            <div class="more-toolbar" >
		                <div class="table-toolbar">
		                    <span class="font-size-16 btn-color">新增预约</span>
		                </div>
		                <div class="clearfix"></div>
		            </div>more-toolbar
		            <table class="table collect-money-table">
		                <thead>
		                <tr>
		                    <th>预约电话</th>
		                    <th>预约姓名</th>
		                    <th>预约项目</th>
		                    <th>预约员工</th>
		                    <th>预约日期</th>
		                    <th>预约时间</th>
		                    <th></th>
		                </tr>
		                </thead>
		                <tbody>
		                <tr>
		                    <td>
		                        <input type="text" class="input80 dropdown-toggle" placeholder="散客/会员" data-toggle="dropdown"/>
		                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
		                            <li><a tabindex="-1" href="#">13728193712 刘毅亮</a></li>
		                            <li><a tabindex="-1" href="#">13817291346 王青青</a></li>
		                        </ul>
		                    </td>
		                    <td>
		                        <input type="text" class="input70" placeholder="姓名"/>
		                    </td>
		                    <td>
		                        <select data-placeholder="预约项目"  class="chzn-select-search input120">
		                            <option value="United Kingdom">泰式洗剪吹</option>
		                            <option value="United States">泰式洗发</option>
		                            <option value="United Kingdom">欧莱雅烫发</option>>
		                        </select>
		                    </td>
		                    <td>
		                        <select data-placeholder="预约员工"  class="chzn-select-search input100">
		                            <option value="United Kingdom">101 刘德华</option>
		                            <option value="United States">102 张学友</option>
		                            <option value="United Kingdom">103 张柏芝</option>
		                        </select>
		                    </td>
		                    <td>
		                        <input type="text" class="datetimepicker input80" daysOffset="1" />
		                    </td>
		                    <td class="expend">
		                        <select data-placeholder="预约时间"  class="chzn-select input80">
		                            <option value="United Kingdom">09:00</option>
		                            <option value="United Kingdom">10:00</option>
		                            <option value="United Kingdom">11:00</option>
		                            <option value="United Kingdom">12:00</option>
		                            <option value="United Kingdom">13:00</option>
		                            <option value="United Kingdom">14:00</option>
		                            <option value="United Kingdom">15:00</option>
		                            <option value="United Kingdom">16:00</option>
		                        </select>
		                    </td>
		                    <td>
		                        <button class="btn btn-primary input80">保存预约</button>
		                    </td>
		                </tr>
		                </tbody>
		            </table>
		        </div> -->
		
		        <div class="widgetcontent">
		            <div class="more-toolbar" >
		                <div class="table-toolbar">
		                    <span class="font-size-16 btn-color">查看预约情况：</span>
		                    <select data-placeholder="选择部门"  class="chzn-select input80">
		                        <option value="United Kingdom">所有部门</option>
		                        <option value="United States">美发部</option>
		                        <option value="United Kingdom">美容部</option>
		                        <option value="United States">美甲部</option>
		                        <option value="United States">足浴部</option>
		                    </select>
		                    <div class="table-pagination">
	                            <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
	                                ${page.pageSize } <span class="iconfa-caret-down afont"></span>
	                            </button>
	                            <ul class="dropdown-menu">
	                                <li><a href="javascript:changePageSize(10)">10</a></li>
	                                <li><a href="javascript:changePageSize(20)">20</a></li>
	                                <li><a href="javascript:changePageSize(50)">50</a></li>
	                                <li><a href="javascript:changePageSize(100)">100</a></li>
	                            </ul>
	                            <div class="left-page" id="previousPageButton" onclick="previous()"><i class="FontAwesome iconfa-caret-left afont"></i></div>
	                            <div class="right-page" id="nextPageButton" onclick="next()"><i class="FontAwesome iconfa-caret-right afont"></i></div>
	                        </div>
		                </div><!--table-toolbar-->
		                <div class="clearfix"></div>
		            </div><!--more-toolbar-->
		            <table class="table table-bordered table-striped day-yuyue-table appoint-table">
		                <thead>
			                <tr>
	                            <th>员工</th>
	                            <th>预约顾客</th>
			                    <th>预约电话</th>
	                            <th>预约项目</th>
	                            <th>预约日期</th>
	                            <th>预约时间</th>
	                            <th>操作</th>
			                </tr>
		                </thead>
		                <tbody>
		                  <c:forEach items="${page.results }" var="appoint">
		                      <tr id="${appoint.appointmentId }">
		                          <td>${appoint.employeeInfo.employeeCode } ${appoint.employeeInfo.name }</td>
		                          <td>${appoint.name }</td>
		                          <td>${appoint.phone }</td>
		                          <td>${appoint.projectInfo.projectName }</td>
		                          <td>${appoint.appointmentDate }</td>
		                          <td>${appoint.appointmentTime }</td>
		                          <td>
		                              <span class="iconfont icon-dizhixiugai" onclick="editAppoint(${appoint.appointmentId })"></span>
		                              <span class="iconfont icon-xx ml10" onclick="deleteAppoint(${appoint.appointmentId })"></span>
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
    </div>
</div>

<script type="text/javascript">
    //获取加载页面时的页码信息
    var pageNo = "${page.pageNo}";
    var pageSize = "${page.pageSize}";
    var totalPage = "${page.totalPage}"
</script>
<script type="text/javascript" src="<%=basePath %>js/cashier/appointManage.js"></script>
</body>
</html>