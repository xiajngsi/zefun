﻿<!doctype html>
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
    <title>他人推荐</title>
</head>
<body>
<div class="new-shop">
    <div class="tab t0">
        <ul >
            <li class="tab-more">
                <a href="<%=basePath%>agentdetail/view/newstore">
                    <img src="<%=basePath%>images/mobile/active-new.png" alt="" class="hide"/>
                    <div class="tab-word">
                        <span>自主开发</span>
                    </div>
                </a>
            </li>
            <li class="tab-more active">
                <a href="<%=basePath%>agentdetail/view/newstore/other">
                    <img src="<%=basePath%>images/mobile/active-new.png" alt="" />
                    <div class="tab-word">
                        <span>他人推荐</span>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <div class="clearfix"></div>

    <div class="new-shop-list">
        <c:forEach var="storeInfo" items="${storeInfos }"  varStatus="storeInfoStatus">
        <ul class="new-shop-item" onclick="show(this);">
            <li class="name"  >
                <span>${storeInfo.storeName }</span> <span class="iconfont icon-xiala fr"></span>
            </li>
            <li class="shop-detail <c:if test="${!storeInfoStatus.first }">hide</c:if> ">
                <ul class="shop-detail-ul">
                    <li class="normal-li">
                        <span class="key">商号</span>
                        <span class="value fr">${storeInfo.storeId }</span>
                    </li>
                    <li class="normal-li">
                        <span class="key">门店类型</span>
                        <c:set var="type" value="单店" />
                        <c:if test="${storeInfo.storeType == 2 }">
                        <c:set var="type" value="连锁总店" />
                        </c:if>
                        <c:if test="${storeInfo.storeType == 3 }">
                        <c:set var="type" value="连锁分店" />
                        </c:if>
                        <span class="value fr">${type }</span>
                    </li>
                    <li class="normal-li">
                        <span class="key">地址</span>
                        <c:set var="address" value="--" />
                        <c:if test="${not empty storeInfo.storeAddress }">
                        <c:set var="address" value="${storeInfo.storeAddress }" />
                        </c:if>
                        <span class="value fr">${address }</span>
                    </li>
                    <li class="recommend-people normal-li">
                        <span class="key">推荐人</span>
                        <c:set value="无" var="recommend" />
                        <c:if test="${not empty recommendAgents[storeInfo.storeId] }">
                        <c:set value="${recommendAgents[storeInfo.storeId].province}${recommendAgents[storeInfo.storeId].city }" var="recommend" />
                        </c:if>
                        <span class="value fr">${recommend }</span>
                    </li>
                    <%--
                    <li class="rec-pp-info">
                        <ul>
                            <li class="info-name">
                                <span>跟踪记录</span>
                            </li>
                            <li class="time-reason">
                                <div > <span class="fr time">2015-12-15  12:26</span></div>
                                <div> <span class="reason">电话无人接通。</span></div>
                            </li>
                            <li class="input-reason">
                                <div class="input-wrap">
                                <input type="text"/>
                                </div>
                                <div class="queren">确认</div>
                            </li>
                        </ul>
                    </li>--%>
                </ul>
            </li>
            <li class="person-info">
                <span class="iconfont icon-dianhua4 bule"></span>
                <span>${storeInfo.storeLinkphone }</span>
                <span class="fr">${storeInfo.storeLinkname }</span>
            </li>
        </ul>
        </c:forEach>
    </div>

</div>

<div class="footer">
    <ul>
        <li  class="active">
            <a href="<%=basePath%>agentdetail/view/newstore">
                <span class="iconfont icon-huizhenshenqing"></span>
                <span>新申请</span>
            </a>
        </li>
        <li >
            <a href="<%=basePath%>agentdetail/view/store">
                <span class="iconfont icon-xingzhuang22"></span>
                <span>我的门店</span>
            </a>
        </li>
        <li>
            <a href="<%=basePath%>agentdetail/view/index">
                <span class="iconfont icon-wode"></span>
                <span>我的账户</span>
            </a>
        </li>
        <li>
            <a href="<%=basePath%>agentdetail/view/share">
                <span class="iconfont icon-iconfontfenxiang"></span>
                <span>资源共享</span>
            </a>
        </li>
    </ul>
</div>

<script>
    var clientHeight =  $(window).height();
    $(".gray-bg").css("min-height", clientHeight);

    function show(obj){
        var op = $(obj);
        $(op).find('.shop-detail').removeClass('hide');
        $(op).siblings().find('.shop-detail').addClass('hide');
      }
</script>

</body>
</html>
