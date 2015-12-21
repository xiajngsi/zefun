<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
	<div class="mainwrapper">
		<!--loading start-->
		<%@ include file="/loading.jsp" %>
		<!--loading end-->
		<%@ include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px;">
			<%@ include file="/top.jsp"%>

		<div class="maincontent">
		    <div class="contentinner">
		        <h4 class="widgettitle">
		            <span class="dingdanzhuantai">活动统计</span>
		        </h4>
		    <div class="more-toolbar" >
	            <div class="table-toolbar">
	                <span class="font-size-16 btn-color mr10">图文统计</span>
	                <span class="video fr font-333" >视频帮助
	                    <span class="iconfont icon-shipin" style="margin-top: 1px;"></span>
	                </span>
	            </div>
	            <div class="clearfix"></div>
	        </div><!--more-toolbar-->
		        <table class="table table-bordered table-striped member-list-table">
		            <thead>
		            <tr>
		                <th>图文名称</th>
		                <th>创建时间</th>
		                <th>图文热度</th>
		                <th>图文欢迎度</th>
		                <th>最多发送对象</th>
		            </tr>
		            </thead>
		            <tbody id="init_member">
		            <c:forEach items="${itemStatusDtos }" var="itemStatusDto">
		            <tr>
		                <td>${itemStatusDto.title }</td>
		                <td>${itemStatusDto.createTime }</td>
		                <td>${itemStatusDto.hotCount }家使用</td>
		                <td>1230人阅读</td>
		                <td>${itemStatusDto.hasRead }</td>
		            </tr>
		            </c:forEach>
		            
		            </tbody>
		        </table>
		    </div>
		</div>

   		<div class="clearfix"></div>
		<div id="star"></div>
		</div>
	</div>
</body>
</html>