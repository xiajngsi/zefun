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
    <title>我的门店资料</title>
</head>
<body class="gray-bg">
<div class="login-dandian">
    <div class="new-shop-list">
            <ul class="new-shop-item">
                <li class="name">
                    <span>${storeInfo.storeName }</span>
                </li>
                <li class="shop-detail">
                    <ul class="shop-detail-ul none-hide">
                        <li class="normal-li">
                            <span class="key">门店属性</span>
                            <span class="value fr">连锁分店</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">账号状态</span>
                            <c:set value="已激活" var="aStatus"/>
                            <c:set value="--" var="pc_user_name"/>
                            <c:set value="--" var="account_time"/>
                            <c:choose>
                                <c:when test="${empty userAccount }">
                                <c:set value="申请中" var="aStatus"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="${userAccount.userName }" var="pc_user_name"/>
                                    <c:set value="${userAccount.createTime }" var="account_time"/>
                                </c:otherwise>
                            </c:choose>

                            <span class="value fr">${aStatus }</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">电脑端用户名</span>
                            <span class="value fr">${pc_user_name }</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">电脑端登录密码</span>
                            <span class="value fr">默认123456</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">开通日期</span>
                            <span class="value fr">${account_time }</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">当前状态</span>
                            <c:set value="" var="status"/>
                            <c:choose>
                                <c:when test="${storeAccount.storeStatus == 1 }">
                                    <c:set value="申请中" var="status"/>
                                </c:when>
                                <c:when test="${storeAccount.storeStatus == 2 }">
                                    <c:set value="试运营" var="status"/>
                                </c:when>
                                <c:when test="${storeAccount.storeStatus == 3 }">
                                    <c:set value="正常营业" var="status"/>
                                </c:when>
                                <c:when test="${storeAccount.storeStatus == 4 }">
                                    <c:set value="已过期" var="status"/>
                                </c:when>
                                <c:when test="${storeAccount.storeStatus == 5 }">
                                    <c:set value="已删除" var="status"/>
                                </c:when>
                            </c:choose>
                            <span class="value fr">${status }</span>
                        </li>
                        <c:if test="${storeAccount.storeStatus != 1 }">
                        <li class="xufei">
                            <span class="key" onclick="window.location.href='<%=basePath%>storedetail/view/renew/sys'">系统期限</span>
                            <div class="xufei-btn fr">系统续费</div>
                            <span class="mr fr">剩余 <span class="red">${storeAccount.balanceDays }</span>天</span>
                        </li>
                        <li class="xufei no-bordered">
                            <span class="key">短信使用量</span>
                            <div class="xufei-btn fr" onclick="window.location.href='<%=basePath%>storedetail/view/renew/sms'">短信充值</div>
                            <span class="mr fr">剩余 <span class="red">${storeAccount.balanceSms }</span>条</span>
                        </li>
                        </c:if>
                    </ul>
                </li>
            </ul>
        </div>
</div>
</body>
</html>