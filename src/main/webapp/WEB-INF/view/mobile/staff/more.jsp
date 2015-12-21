<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>特别推荐</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/yg.css"/>
</head>
<body>
<div class="wrap">
<div class="content">
    <div class="yg-more">
	    <div class="tab t0">
	        <ul >
	            <li class="tab-more  active">
	                <a href="javascript:void(0);">
	                    <img src="<%=basePath%>images/mobile/employee/active-new.png" alt=""/>
	                    <div class="tab-word">
	                        <span>推荐</span>
	                    </div>
	                </a>
	                <div class="border-group hide">
	                    <div class="one-border"></div>
	                    <div class="two-border"></div>
	                    <div class="three-border"></div>
	                </div>
	            </li>
	            <li class="tab-more">
	                <a href="javascript:void(0);">
	                    <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
	                    <div class="tab-word">
	                        <span>培训</span>
	                    </div>
	                </a>
	                <div class="border-group">
	                    <div class="one-border"></div>
	                    <div class="two-border"></div>
	                    <div class="three-border"></div>
	                </div>
	            </li>
	        </ul>
	    </div>
	    <div class="clearfix"></div>
	
	    <div class="function-list-wrap mt7">
	        <ul class="function-list">
	            <li class="function-item normal-li">
	                <a href="javascript:void(0);">
	                    <span><i class="iconfont icon-youhuicuxiao"></i> <span class="ml">促销项目</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="javascript:void(0);">
	                    <span><i class="iconfont icon-re"></i> <span class="ml">热销套餐</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="javascript:void(0);">
	                    <span><i class="iconfont icon-gouwudai"></i> <span class="ml">畅销产品</span></span>
	                    <span class="fr normoal-word"><i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="javascript:void(0);">
	                    <span><i class="iconfont icon-gouwudai"></i> <span class="ml">会员卡</span></span>
	                    <span class="fr normoal-word"><i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="javascript:void(0);">
	                    <span><i class="iconfont icon-wodejifen"></i> <span class="ml">积分兑换</span></span>
	                    <span class="fr normoal-word"><i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	        </ul>
	    </div>
	</div>
	
	<div class="footer">
        <ul>
            <li >
                <a href="<%=basePath%>staff/view/reception">
                    <span class="iconfont icon-jiedai"></span>
                    <span>接待</span>
                </a>
            </li>
            <li>
                <a href="<%=basePath%>staff/view/allEarning">
                    <span class="iconfont icon-yeji"></span>
                    <span>业绩</span>
                </a>
            </li>
            <li>
                <a href="<%=basePath%>staff/view/staffCenter">
                    <span class="iconfont icon-wode"></span>
                    <span>我的</span>
                </a>
            </li>
            <li class="active">
                <a href="<%=basePath%>staff/view/more">
                    <span class="iconfont icon-gengduo2"></span>
                    <span>更多</span>
                </a>
            </li>
        </ul>
    </div>
</div>  
</div>  
</body>
</html>