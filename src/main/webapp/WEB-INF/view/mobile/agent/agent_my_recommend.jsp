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
    <title>我推荐的渠道商</title>
</head>
<body class="gray-bg">
<div class="resource-share">
    <div class="tab t0">
        <ul >
            <li class="tab-more  " style="width: 33.333%">
                <a href="<%=basePath%>agentdetail/view/share">
                    <img src="<%=basePath%>images/mobile/active-new.png" alt="" class="hide"/>
                    <div class="tab-word">
                        <span>我推荐的</span><br/>
                        <span>门店</span>
                    </div>
                </a>
            </li>
            <li class="tab-more active" style="width: 33.333%">
                <a href="<%=basePath%>agentdetail/view/share/agent">
                    <img src="<%=basePath%>images/mobile/active-new.png" alt="" />
                    <div class="tab-word">
                        <span>我推荐的</span><br/>
                        <span>渠道商</span>
                    </div>
                </a>
            </li>
            <li class="tab-more " style="width: 33.333%">
                <a href="<%=basePath%>agentdetail/view/shared/store">
                    <img src="<%=basePath%>images/mobile/active-new.png" alt=""  class="hide"/>
                    <div class="tab-word">
                        <span>推荐给我的</span><br/>
                        <span>门店</span>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <div class="clearfix"></div>

    <!--我推荐的渠道商-->
    <div class="tab-target" id="tab2">

        <div class="new-shop-list">
            <c:forEach var="agentInfo" items="${recommededAgentInfos }" varStatus="agentInfoStatus">

            <ul class="new-shop-item" onclick="show(this);">
                <li class="name"  >
                    <span>${agentInfo.province }${agentInfo.city }</span>
                    <span class="fr">
                        <span class="iconfont icon-xiala"></span>
                    </span>
                </li>
                <li class="shop-detail <c:if test="${!agentInfoStatus.first }">hide</c:if>">
                    <ul class="shop-detail-ul">
                        <li class="normal-li">
                            <span class="key">公司名称</span>
                            <span class="value fr">${agentInfo.companyName }</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">业务类型</span>
                             <c:set var="type" value="其他"/>
                        <c:choose>
                            <c:when test="${agentInfo.companyType == 1 }">
                                <c:set var="type" value="连锁机构"/>
                            </c:when>

                            <c:when test="${agentInfo.companyType == 2 }">
                                <c:set var="type" value="发品商"/>
                            </c:when>

                            <c:when test="${agentInfo.companyType == 3 }">
                                <c:set var="type" value="培训机构"/>
                            </c:when>
                        </c:choose>

                            <span class="value fr">${type }</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">当前状态</span>
                            <c:set var="agentAccount" value="${recommendedAgentAccounts[agentInfo.agentId] }"/>
                            <c:set var="agent_status" value="申请中" />
                          <c:if test="${not empty agentAccount and agentAccount.agentStatus == 2}">
                          <c:set var="agent_status" value="正常" />
                          </c:if>
                          <c:if test="${not empty agentAccount and agentAccount.agentStatus == 3}">
                          <c:set var="agent_status" value="停用" />
                          </c:if>
                            <span class="value fr">${agent_status }</span>
                        </li>
                    </ul>
                </li>
                <li class="person-info">
                    <span class="iconfont icon-dianhua4 bule"></span>
                    <span>${agentInfo.name }</span>
                    <span class="fr">联系人：${agentInfo.phone }</span>
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
