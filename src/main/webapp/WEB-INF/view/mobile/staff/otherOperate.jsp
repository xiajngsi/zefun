<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>其它操作</title>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/star-rating.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/mobile.css"/>
    <script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/star-rating.min.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/jquery.touchSwipe.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/member-mobile.js"> </script>
    <script type="text/javascript" src="<%=basePath%>js/mobile/yg.js"> </script>
  </head>
	<body>
	<div class="wrap">
      <div class="content">
        <div class="per-center-login">
			<div class="yg-person-center">
			    <div class="tab">
			        <ul class="tab-two-part">
			            <li class="score-shop-li  active ">
			                <a href="">
			                    <img src="assets/images/topselected.png" alt=""/>
			                    <div class="tab-word"><span>推荐</span></div>
			                </a>
			            </li>
			            <li class="score-shop-li">
			                <a href="">
			                    <img src="assets/images/topselected.png" alt="" class="hide"/>
			                    <div class="tab-word"><span>培训</span></div>
			                </a>
			            </li>
			        </ul>
			    </div>
			    <div class="function-list-wrap mt7">
			        <ul class="function-list">
			            <li class="function-item normal-li active s-modal-control" data-target="#daka-success">
			                <a href="javascript:void(0);">
			                    <span><i class="iconfont icon-zhangdandaikou " ></i> <span class="ml">促销项目</span> <div class="have-message"></div></span>
			                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
			                </a>
			            </li>
			            <li class="function-item normal-li">
			                <a href="my-package.html">
			                    <span><i class="iconfont icon-bbggerenxihu"></i> <span class="ml">热销套餐</span></span>
			                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
			                </a>
			            </li>
			            <li class="function-item normal-li">
			                <a href="yg-performance-state.html">
			                    <span><i class="iconfont icon-youhui"></i> <span class="ml">畅销产品</span></span>
			                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
			                </a>
			            </li>
			            <li class="function-item normal-li">
			                <a href="#">
			                    <span><i class="iconfont icon-svg41"></i> <span class="ml">会员卡</span></span>
			                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
			                </a>
			            </li>
			            <li class="function-item normal-li">
			                <a href="person-info.html">
			                    <span><i class="iconfont icon-svg41"></i> <span class="ml">积分换购</span></span>
			                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
			                </a>
			            </li>
			        </ul>
			    </div>
			
			</div>
			</div>
			
			<div class="footer">
			    <ul>
			        <li>
			            <a href="<%=basePath%>staff/view/staffPerformance">
			                <i class="iconfont icon-touxiang"></i>
			                <span>接待</span>
			            </a>
			        </li>
			        <li>
			            <a href="<%=basePath%>staff/view/resultRank">
			                <i class="iconfont icon-yuyue"></i>
			                <span>业绩</span>
			            </a>
			        </li>
			        <li>
			            <a href="<%=basePath%>mobile/test?view=mobile/staff/staffCentre">
			                <i class="iconfont icon-yuyue"></i>
			                <span>我的</span>
			            </a>
			        </li>
			        <li class="active">
			            <a href="<%=basePath%>mobile/test?view=mobile/staff/otherOperate">
			                <i class="iconfont icon-jiamengdian"></i>
			                <span>更多</span>
			            </a>
			        </li>
			    </ul>
			</div>
	   </div>
  </body>
</html>