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
    <title>选择门店</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <div class="shop-select">
        <div class="shop-liansuo bsw">
		   <span class="shop-name">${storeList[0].storeName }</span>
		</div>
	    <c:forEach items="${storeList }" var="store">
	       <a href="<%=basePath %>${fn:replace(url, '{storeId}', store.storeId)}">
	         <div class="select-shop-item bsw mb10p">
			    <img src="<%=picPath %>${store.storeLogo}" class="fl"/>
			    <div class="desc fl" >
			      <div> <span class="shop-name">${store.storeName }</span></div>
			      <div> <span class="shop-addr">${store.storeAddress }</span> </div>
			      <div> <span class="shop-desc">电话：${store.storeTel }</span> </div>
			    </div>
			  </div>
		    </a>
	    </c:forEach>
    </div>
</div>  
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
</body>
</html>