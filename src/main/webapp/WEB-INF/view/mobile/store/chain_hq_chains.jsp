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
    <title>我的分店</title>
</head>
<body class="gray-bg">
<div class="login-zongdian">
    <div class="desc">
        <span class="name ">我的分店</span> <span class="fendian-num">(共${chainsCnt }家分店)</span>
    </div>

    <div class="new-shop-list">
            <c:forEach items="${chains }" var="chain" varStatus="chainStatus">
            <ul class="new-shop-item" onclick="show(this);">
                <li class="name" >
                    <span>${chain.storeName }</span>
                    <span class="iconfont icon-xiala fr"></span>
                    <c:set value="" var="status"/>
                    <c:set value="${chainStoreAccounts[chain.storeId] }" var="storeAccount"/>
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
                    <span class="fr mr red">${status }</span>
                </li>
                <li class="shop-detail <c:if test="${!chainStatus.first }">hide</c:if>">
                    <ul class="shop-detail-ul">
                        <li class="normal-li">
                            <span class="key">地址</span>
                            <span class="value fr">${chain.storeAddress }</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">推荐人</span>
                            <c:set var="recommend_name" value="无"/>
                            <c:if test="${recommendAgents[chain.storeId] }">
                                <c:set var="recommend_name" value="recommendAgents[chain.storeId].province + recommendAgents[chain.storeId].city"/>
                            </c:if>
                            <span class="value fr">${recommend_name }</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">系统期限</span>
                            <span class="value fr">剩余${storeAccount.balanceDays }天</span>
                        </li>
                        <li class="recommend-people normal-li">
                            <span class="key">短信剩余量</span>
                            <span class="value fr">剩余${storeAccount.balanceSms }条</span>
                        </li>
                    </ul>
                </li>

                <li class="person-info">
                    <span class="iconfont icon-dianhua4 bule"></span>
                    <span>${chain.storeLinkphone }</span>
                    <span class="fr">联系人:${chain.storeLinkname }</span>
                </li>
            </ul>

            </c:forEach>
        </div>
</div>

<script>
    function show(obj){
      var op = $(obj);
      $(op).find('.shop-detail').removeClass('hide');
      $(op).siblings().find('.shop-detail').addClass('hide');
    }
</script>
</body>
</html>