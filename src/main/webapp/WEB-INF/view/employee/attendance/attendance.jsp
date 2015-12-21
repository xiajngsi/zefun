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

        <div class="more-toolbar" >
            <div class="table-toolbar">
                <label for="" style="display: inline-block;padding-right: 5px;">打卡列表</label>
                  <input type="search" id="search" placeholder="员工工号/姓名"/>
                  <button class="button-search btn width100 ml-10" onclick="changePage()">查询</button>
                <div class="table-pagination fr">
                    <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                        10 <span class="caret"></span>
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
                </div><!--table-pagination-->
            </div><!--table-toolbar-->
            <div class="clearfix"></div>
        </div><!--more-toolbar-->

        <table class="table table-bordered table-striped member-list-table">
            <thead>
            <tr>
                <th>工号</th>
                <th>姓名</th>
                <th>类型</th>
                <th>签到时间</th>
                <th>迟到时间(分钟)</th>
                <th>签退时间</th>
                <th>早退时间(分钟)</th>
                <th>日期</th>
            </tr>
            </thead>
            <tbody>
           <c:forEach items="${page.results }" var="employee">
            <tr>
                <td>${employee.employeeInfo.employeeCode }</td>
                <td >${employee.employeeInfo.name }</td>
                <td>类型</td>
                <td>${employee.signInTime }</td>
                <td>${employee.signInOffset }</td>
                <td>${employee.signOutTime }</td>
                <td>${employee.signOutOffset }</td>
                <td>${employee.attendanceDate }</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>

  //获取加载页面时的页码信息
    var pageNo = "${page.pageNo}";
    var pageSize = "${page.pageSize}";
    var totalPage = "${page.totalPage}"
</script>
<script type="text/javascript" src="<%=basePath %>js/employee/attendance.js"></script>
<script type="text/javascript">
	checkPageButton();
</script>
    </div>
    <!--RIGHT PANEL结束 -->

</div>

</body>
</html>