<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>错误提示</title>
  <style>
    body,html{
      background-color: #fff;
      height: 100%;
    }
    .notfind{
      width: 80%;
      position: absolute;
      top:50%;
      left:50%;
      -webkit-transform: translate(-50%, -50%);
      -moz-transform: translate(-50%, -50%);
      -ms-transform: translate(-50%, -50%);
      -o-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
    }
    .notfind img{
      width: 100%;
    }
    .notfind a{
      position: absolute;
      width:20%;
      height: 12%;

      bottom:0;
      left:38%;
    }
    .notfind span{
      position: absolute;
      bottom:20%;
      left:24%;
      color: #92c6c6;
      font-size: 24px;
    }
  </style>
</head>
<body>
<div class="notfind">
  <img src="<%=picPath %>zefun/error/404.png"/>
  <c:choose>
    <c:when test="${empty tip }">
        <span>对不起，您访问的地址未找到</span>
    </c:when>
    <c:otherwise>
        <span>${tip }</span>
    </c:otherwise>
  </c:choose>
  <a href="javascript:back();"></a>
</div>
<script type="text/javascript">
function back(){
	if (WeixinJSBridge) {
		WeixinJSBridge.call('closeWindow');
	}
	else {
		window.location.href = "<%=basePath%>";
	}
}
</script>
</body>
</html>