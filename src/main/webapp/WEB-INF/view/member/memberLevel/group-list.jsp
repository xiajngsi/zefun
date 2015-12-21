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
      	<!-- 页面代码 -->
   <div class="maincontent">
    <div class="contentinner">
    
         <div class="more-toolbar" >
            <div class="table-toolbar">
                <span class="font-size-16 btn-color mr10">会员分组</span>
            </div>
            <div class="clearfix"></div>
        </div><!--more-toolbar-->
        
        
        <c:if test="${type == 1 }">
		<table class="table table-bordered table-striped member-list-table">
            <thead>
            <tr>
                <th>名单代号</th>
                <th>筛选人数</th>
                <th class="input-xxlarge">筛选条件</th>
                <th>本月新增人数</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${groupDtos }" var="groupDto">
            <tr>
                <td style="cursor: pointer">${groupDto.groupName }</td>
                <td>${groupDto.memberCount }</td>
                <td>${groupDto.groupCondition }</td>
                <td>${groupDto.newMember }</td>
                <td>${groupDto.createTime }</td>
                <td>
                    <i class="iconfa-search project-icon chart_message" id="${groupDto.groupId }"></i>
                    <i class="iconfa-trash project-icon delete_group" id="${groupDto.groupId }"></i>
                </td>
            </tr>
            </c:forEach>
           
            </tbody>
        </table>
		</c:if>
        
        
		<c:if test="${type == 2 }">
		<table class="table table-bordered table-striped member-list-table">
            <thead>
            <tr>
                <th>名单代号</th>
                <th>筛选人数</th>
                <th class="input-xxlarge">筛选条件</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${groupDtos }" var="groupDto">
            <tr>
                <td style="cursor: pointer">${groupDto.groupName }</td>
                <td>${groupDto.memberCount }</td>
                <td>${groupDto.groupCondition }</td>
                <td>${groupDto.createTime }</td>
                <td>
                    <i class="iconfa-search project-icon chart_message" id="${groupDto.groupId }"></i>
                    <i class="iconfa-trash project-icon delete_group" id="${groupDto.groupId }"></i>
                </td>
            </tr>
            </c:forEach>
           
            </tbody>
        </table>
		</c:if>
        
        <br><br>
      <!--根据筛选名称显示人员列表-->
      <div id="renyuan" class="hide">
      <div class="more-toolbar">
        <div class="table-toolbar">
            <span id="group_name_show" style="font-size: 14px"></span>
			<div class="table-pagination">
                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                    50 <span class="iconfa-caret-down afont"></span>
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
        <!-- <br> -->
        <table class="table table-bordered table-striped member-list-table">
            <thead>
            <c:if test="${type == 2 }">
            <tr>
            	<th>所属门店</th>
                <th>手机号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>会员等级</th>
                <th>生日</th>
                <th>储值余额</th>
                <th>积分余额</th>
                <th>累计消费</th>
                <th>消费均额</th>
                <th>注册日期</th>
            </tr>
            </c:if>
            <c:if test="${type != 2 }">
            <tr>
                <th>手机号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>会员等级</th>
                <th>生日</th>
                <th>储值余额</th>
                <th>积分余额</th>
                <th>累计消费</th>
                <th>消费均额</th>
                <th>注册日期</th>
            </tr>
            </c:if>
            </thead>
            <tbody id="serch_member_list">
            </tbody>
        </table>
      </div>
        <!--根据筛选名称显示人员列表-->
    </div>
	</div>
</div>
     
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->
<%@ include file="/template/memberData.jsp" %>
</body>
<script>
	//获取加载页面时的页码信息
	var pageNo = 1;
	var pageSize = 50;
	var totalPage = 0;
	var type = '${type}';
</script>
<script src="<%=basePath %>js/member/group-list.js" type="text/javascript"></script>

</html>