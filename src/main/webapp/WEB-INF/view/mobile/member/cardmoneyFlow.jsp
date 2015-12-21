<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>卡金收支明细</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <div class="income-name">
	    <ul class="bsw shouzhimingxi-wrap">
	       <c:forEach items="${flowList }" var="flow">
	           <li class="shouzhimingxi-list">
	                <div class="list-desc">
	                    <div class="name">
	                       <c:choose>
	                           <c:when test="${flow.businessType == 1 }">消费</c:when>
	                           <c:when test="${flow.businessType == 2 }">充值</c:when>
	                           <c:when test="${flow.businessType == 3 }">转账</c:when>
	                           <c:otherwise>开卡</c:otherwise>
	                       </c:choose>
	                      <%--  -${flow.businessDesc } --%>
	                    </div>
	                    <div class="time">${fn:substring(flow.flowTime, 0, 16) }</div>
	                </div>
	                <c:choose>
                       <c:when test="${flow.flowType == 1 }">
                           <div class="num fu">-${flow.flowAmount }</div>
                       </c:when>
                       <c:otherwise>
                           <div class="num zheng">+${flow.flowAmount }</div>
                       </c:otherwise>
                    </c:choose>
	            </li>
	       </c:forEach>
	    </ul>
	</div>
</div>
<c:if test="${empty flowList }">
    <div class="kongjilv">
        <div class="center">
            <div class="iconfont icon-xingzhuang14"></div>
            <div>近期暂无记录</div>
        </div>
    </div>
</c:if> 
</body>
</html>