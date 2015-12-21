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
    <title>我的账户</title>
</head>
<body class="gray-bg">
<div class="wodezhanghu">
    <div class="ml-head">
        <div style="overflow: hidden">
            <div class="name">${agentInfo.companyName }</div>
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
            <div class="jigou">${type }</div>
        </div>
        <div class="address-wrap">
            <div class="address">
                <div>${agentInfo.city }</div>
            </div>
        </div>
        <div class="h-btn">
            <div class="md-btn s-modal-control" onclick="share(1);">
                推荐新渠道
            </div>
            <div class="qd-btn s-modal-control" onclick="share(2);">
                推荐新门店
             </div>
        </div>
    </div>

    <a href="<%=basePath%>agentdetail/view/income">
	    <ul class="tip">
	        <li class="shouyi-sum">
	            <div class="wrap">
	                <div class="wrap-content">
	                    <div class="name">账户收益</div>
	                    <div class="num-sum">
	                        <span class="num">${agentAccount.totalAmount }</span> <span class="font-size-24">元</span>
	                    </div>
	                </div>
	            </div>
	        </li>
	        <li class="shouyi-sum">
	            <div class="wrap">
	                <div class="wrap-content">
	                    <div>获得账户/剩余</div>
	                    <div class="num-sum">
	                        <span class="num">${agentAccount.totalAccount }</span> <span class="font-size-48">/</span><span class="shegnyu">${agentAccount.balanceAccount }</span><span>个</span>
	                    </div>
	                </div>
	            </div>
	        </li>
	    </ul>
    </a>

    <ul class="zh-about-list">
        <a href="<%=basePath%>agentdetail/view/info">
	        <li class="zh-item">
	           <span class="iconfont icon-wodeziliao"></span> <span>我的资料</span> <span class="iconfont icon-youbianfangxiang fr"></span>
	        </li>
        </a>
        <a href="<%=basePath%>agentdetail/view/stat">
	        <li class="zh-item">
	            <span class="iconfont icon-shujutongji"></span> <span>业绩排行</span> <span class="iconfont icon-youbianfangxiang fr"></span>
	        </li>
        </a>
        <a href="">
	        <li class="zh-item">
	          <span class="iconfont icon-iconfonticonfontwodehuiyi"></span> <span>我的会议</span> <span class="iconfont icon-youbianfangxiang fr"></span>
	        </li>
        </a>
    </ul>

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
        <li class="active">
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

<!--选择推荐新门店-->
<div class="s-modal no-padding hide s-modal-miss" id="shareTip">
    <div class="s-modal-wrap">
        <div class="jd-wrap">
            <img src="<%=picPath %>agent_share_tip.png" alt=""/>
            <div class="jd-main">
                已为你生成 <span id="typeName">新渠道</span> 的推广链接, 请点击右上角分享吧！
                <div class="jd-btn">我知道了</div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
<script type="text/javascript">
var appId = "<%=request.getAttribute("appId")%>";
var timestamp = "<%=request.getAttribute("timestamp")%>";
var nonceStr = "<%=request.getAttribute("nonceStr")%>";
var signature = "<%=request.getAttribute("signature")%>";
</script>
<script src="<%=basePath%>js/mobile/member/wxapi.js"></script>
<script src="<%=basePath%>js/mobile/base.js"></script>
<script>
    var name = "${agentInfo.name}";
    
    var title = '';
    var desc = '2016美业的首选！智放为美业设计的门店智能营销管理系统颠覆现场作业流程！让系统帮你运转门店！无卡化经营！无纸化开单！直接降低成本！智能轮牌！系统自动识别员工牌位状态进行派单！只需一次点击，业绩，提成自动入帐！微信互动预约！顾客与员工通过系统中确认预约！会员自助买单！智能识别优惠！全面解放收银前台!顾客朋友圈分享！让顾客成为你的拓客好帮手！';
    var link = '';
    var imgUrl = picUrl + 'maywant_logo.jpg?imageView2/1/w/200/h/200';
    
    function share(type){
    	var str = "新渠道";
    	if (type == 1) {
    		title = name + '诚邀您加入美联网智放系统渠道合作伙伴，开启美业互联网新篇章！';
    	    link = baseUrl + 'agentapply/view/agentApply?code=${code}';
    	} else {
    		title = name + '诚邀您体验智放智能营销管理系统，无卡化经营！无纸化开单！智能轮牌！';
            link = baseUrl + 'storeapply/view/storeApply?code=${code}';
            str = "新门店";
    	}
    	$("#typeName").html(str);
    	$("#shareTip").show();
    	registerShare();
    }
    
    function registerShare(){
        wx.onMenuShareAppMessage({
          title: title,
          desc: desc,
          link: link,
          imgUrl: imgUrl
        });

        wx.onMenuShareTimeline({
          title: title,
          link: link,
          imgUrl: imgUrl
        });
    }
    
    $("#shareTip").click(function(){
    	$(this).hide();
    });
</script>
</body>
</html>
