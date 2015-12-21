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
    <title>我推荐的门店</title>
</head>
<body class="gray-bg">
<div class="resource-share">
    <div class="tab t0">
        <ul >
            <li class="tab-more  active" style="width: 33.333%">
                <a href="<%=basePath%>agentdetail/view/share">
                    <img src="<%=basePath%>images/mobile/active-new.png" alt=""/>
                    <div class="tab-word">
                        <span>我推荐的</span><br/>
                        <span>门店</span>
                    </div>
                </a>
            </li>
            <li class="tab-more" style="width: 33.333%">
                <a href="<%=basePath%>agentdetail/view/share/agent">
                    <img src="<%=basePath%>images/mobile/active-new.png" alt="" class="hide"/>
                    <div class="tab-word">
                        <span>我推荐的</span><br/>
                        <span>渠道商</span>
                    </div>
                </a>
            </li>
            <li class="tab-more" style="width: 33.333%">
                <a href="<%=basePath%>agentdetail/view/shared/store">
                    <img src="<%=basePath%>images/mobile/active-new.png" alt="" class="hide"/>
                    <div class="tab-word">
                        <span>推荐给我的</span><br/>
                        <span>门店</span>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <div class="clearfix"></div>

    <!--我推荐的门店-->
    <div class="tab-target" id="tab1">
        <div class="new-shop-list">
            <c:forEach var="storeInfo" items="${storeInfos }"  varStatus="storeInfoStatus">

            <ul class="new-shop-item" onclick="show(this);">
                <li class="name"  >
                    <span>${storeInfo.storeName }</span>
                    <span class="fr">
                        <c:set var="storeAccount" value="${storeAccounts[storeInfo.storeId] }"/>
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
                        <span>${ status}</span>
                        <span class="iconfont icon-xiala"></span>
                    </span>
                </li>
                <li class="shop-detail <c:if test="${!storeInfoStatus.first }">hide</c:if>">
                    <ul class="shop-detail-ul ">
                        <li class="normal-li">
                            <span class="key">地址</span>
                            <c:set var="address" value="--" />
                          <c:if test="${not empty storeInfo.storeAddress }">
                          <c:set var="address" value="${storeInfo.storeAddress }" />
                          </c:if>
                            <span class="value fr">${address}</span>
                        </li>
                        <li class="recommend-people normal-li">
                            <span class="key">当地渠道商</span>
                            <c:set var="agent" value="无"/>
                            <c:if test="${not empty agents[storeInfo.storeId] }">
                            <c:set var="agent" value="${agents[storeInfo.storeId].province }${agents[storeInfo.storeId].city }"/>
                            </c:if>
                            <span class="value fr">${agent }</span>
                        </li>
                    </ul>
                </li>
                <li class="person-info">
                    <span class="iconfont icon-dianhua4 bule"></span>
                    <span>${storeInfo.storeLinkphone }</span>
                    <span class="fr">联系人：${storeInfo.storeLinkname }</span>
                </li>
            </ul>

            </c:forEach>
        </div>

    </div>
</div>

<div class="footer">
    <ul>
        <li >
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
        <li >
            <a href="<%=basePath%>agentdetail/view/index">
                <span class="iconfont icon-wode"></span>
                <span>我的账户</span>
            </a>
        </li>
        <li class="active">
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
