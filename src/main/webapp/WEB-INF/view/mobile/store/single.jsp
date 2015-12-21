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
<div class="mendian-login">
    <div class="ml-head">
        <div class="name">${storeInfo.storeName }</div>
        <div class="jigou">&nbsp;</div>
        <div class="address-wrap">
            <div class="address">
                <div>单店</div>
            </div>
        </div>
    </div>

    <ul class="tip">
        <li class="shouyi-sum">
            <div class="wrap">
                <div class="wrap-content">
                    <div class="name">系统期限</div>
                    <div class="num-sum">
                        <span class="num">${storeAccount.balanceDays }</span> <span class="font-size-24">天</span>
                    </div>
                </div>
            </div>
        </li>
        <li class="shouyi-sum">
            <div class="wrap">
                <div class="wrap-content">
                    <div class="name">短信使用量</div>
                    <div class="num-sum">
                        <span class="num">${storeAccount.balanceSms }</span> <span class="font-size-24">万条</span>
                    </div>
                </div>
            </div>
        </li>
    </ul>


    <ul class="zh-about-list">
        <li class="zh-item">
            <a href="<%=basePath%>storedetail/view/info">
                <span class="iconfont icon-dianpu"></span> <span>门店资料</span> <span class="iconfont icon-youbianfangxiang fr"></span>
            </a>
        </li>
        <li class="zh-item">
            <a href="<%=basePath%>storedetail/view/renew/sys">
                <span class="iconfont icon-xitongxufei"></span> <span>系统续费</span> <span class="iconfont icon-youbianfangxiang fr"></span>
            </a>
        </li>
        <li class="zh-item">
            <a href="<%=basePath%>storedetail/view/renew/sms">
                <span class="iconfont icon-chongzhiduanin"></span> <span>短信充值</span> <span class="iconfont icon-youbianfangxiang fr"></span>

            </a>
        </li>
    </ul>

</div>
</body>
</html>