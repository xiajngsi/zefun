<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>${title }</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content" style="text-align: center; vertical-align: middle;">
	<c:choose>
	    <c:when test="${empty content }">
	        <h1>门店暂未设置${title }</h1>
	    </c:when>
	    <c:otherwise>
	        ${content }
	    </c:otherwise>
	</c:choose>
</div>
</body>
</html>