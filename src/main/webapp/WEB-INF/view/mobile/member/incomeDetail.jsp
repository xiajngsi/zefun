<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>收支明细</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/star-rating.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
    <script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/star-rating.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/jquery.touchSwipe.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/member-mobile.js"> </script>
  </head>
<body>
<div class="content">
        <div class="income-detail">
    <ul>
        <li></li>
        <li class="normal-li">
            <span class="dep-blue-font-color">单号</span>
            <span class="fr dep-word">201508261709380002</span>
        </li>
        <li class="normal-li">
            <span class="dep-blue-font-color">类型</span>
            <span class="fr">转出</span>
        </li>
        <li class="normal-li">
            <span class="dep-blue-font-color">支出</span>
            <span class="fr">180.00元</span>
        </li>
        <li class="normal-li">
            <span class="dep-blue-font-color">余额</span>
            <span class="fr">1980.00元</span>
        </li>
        <li class="normal-li">
            <span class="dep-blue-font-color">时间</span>
            <span class="fr">2015-08-08 17:00</span>
        </li>
        <li class="normal-li">
            <span class="dep-blue-font-color">备注</span>
            <span class="fr">转账给张三</span>
        </li>
    </ul>
</div>

        </div>
</body>
</html>