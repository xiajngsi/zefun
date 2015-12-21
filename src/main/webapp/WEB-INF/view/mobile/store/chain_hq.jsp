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
    <title>我的门店</title>
</head>
<body class="gray-bg">
<div class="mendian-login-zongdian">
    <div class="ml-head">
        <div class="name">${storeInfo.storeName }</div>
        <div class="address-wrap">
            <div class="address">
                <div>连锁总店</div>
            </div>
        </div>
    </div>


    <ul class="zh-about-list">
        <li class="zh-item">
            <a href="<%=basePath%>storedetail/view/info">
                <span class="iconfont icon-dianpu"></span> <span>门店资料</span> <span class="iconfont icon-youbianfangxiang fr"></span>
            </a>
        </li>
        <li class="zh-item">
            <c:choose>
                <c:when test="${chainsCnt > 0 }">
	                <a href="<%=basePath%>storedetail/view/chains?storeId=${storeInfo.storeId}">
	                  <span class="iconfont icon-bianlidian"></span> <span>我的分店</span> <span class="iconfont icon-youbianfangxiang fr"></span>
	                </a>
                </c:when>
                <c:otherwise>
	                <a href="javascript:void(0);">
	                  <span class="iconfont icon-bianlidian"></span> <span>我的分店</span> <span class="iconfont icon-youbianfangxiang fr"></span>
	                </a>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>

</div>
</body>
</html>