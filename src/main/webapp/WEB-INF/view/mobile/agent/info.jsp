<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css"/>

    <script src="<%=basePath%>js/mobile/jquery.min.js"></script>
    <script src="<%=basePath%>js/mobile/base.js"></script>
    <title>我的资料</title>
</head>
<body class="gray-bg">
<div class="wodeziliao">
    <div class="new-shop-list">
        <ul class="new-shop-item">
            <li class="name">
                <span>${agentInfo.companyName }</span>
            </li>
            <li class="shop-detail">
                <ul class="shop-detail-ul none-hide">
                    <li class="normal-li">
                        <span class="key">姓名</span>
                        <span class="value fr">${agentInfo.name }</span>
                    </li>
                    <li class="normal-li">
                        <span class="key">手机号</span>
                        <span class="value fr">${agentInfo.phone}</span>
                    </li>
                    <li class="normal-li">
                        <span class="key">业务类型</span>
                        <c:set var="type" value="其他"/>
                    <c:choose>
                        <c:when test="${agentInfo.companyType == 1 }">
                            <c:set var="type" value="连锁机构"/>
                        </c:when>

                        <c:when test="${agentInfo.companyType == 2 }">
                            <c:set var="type" value="发品商"/>
                        </c:when>

                        <c:when test="${agentInfo.companyType == 3 }">
                            <c:set var="type" value="培训机构"/>
                        </c:when>
                    </c:choose>
                        <span class="value fr">${type }</span>
                    </li>
                    <li class="normal-li">
                        <span class="key">所在省</span>
                        <span class="value fr">${agentInfo.province }</span>
                    </li>
                    <li class="normal-li">
                        <span class="key">所在市</span>
                        <span class="value fr">${agentInfo.city }</span>
                    </li>
                    <li class="normal-li no-bordered">
                        <span class="key">当前状态</span>
                        <c:set var="agent_status" value="申请中" />
                        <c:if test="${agentAccount.agentStatus == 2}">
                        <c:set var="agent_status" value="正常" />
                        </c:if>
                        <c:if test="${agentAccount.agentStatus == 3}">
                        <c:set var="agent_status" value="停用" />
                        </c:if>
                        <span class="value fr">${agent_status }</span>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>

<script>
    var clientHeight =  $(window).height();
    $(".gray-bg").css("min-height", clientHeight);
</script>

</body>
</html>
