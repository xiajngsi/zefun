<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>好友推荐</title>
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
<div class="friend-tuijian">
    <div class="bg-blue text-left mb h60 line-height60 white-word">你的朋友小红向您推荐的发型</div>
    <div class="shop-img">
        <img src="http://7xkv8r.com1.z0.glb.clouddn.com/faxing.jpg?imageView2/1/w/1500/h/700" alt=""/>
    </div>
    <div class="faxing-jieshao">
        <div class="dep-blue-font-color">学院发型</div>
        <div class="font-size-28">
            精致的麻花辫盘发展现出俏皮可爱的感，蝴蝶结头巾的搭配露出光洁饱满的额头，尽显甜美感，盘发步骤也特别的简单，只需要编织两个麻花辫将其缠绕成花苞式即可。<br>

        </div>
    </div>

    <div class="designer-list mt2">
        <ul>
            <!--<li class="desiger">
                <img src="<%=basePath%>images/mobile/img1.png" alt=""/>
                <div class="info">
                    <span>王一束</span><span class="normoal-word">(三星发型师)</span>
                    <input type="number" class="rating input-id" min=0 max=5 step=0.5 data-size="xs" showClear="false" showCaption="false" value="3" >
                    <span>服务 <span class="num blue-word">120</span>人次</span>
                </div>
            </li>-->
            <li class="desiger">
                <img src="<%=basePath%>images/mobile/miaodu.jpg" alt=""/>
                <div class="info">
                    <div>首脑发型保安店</div>
                    <div>服务人次 <span class="blue-word">500</span>人</div>
                    <!--<input type="number" class="rating input-id" min=0 max=5 step=0.5 data-size="xs" showClear="false" showCaption="false" value="4" />-->
                    <span>地点 <span class="num blue-word">南山区首脑店</span></span>
                </div>
            </li>
        </ul>
    </div>


    <div class="coupon-item first-li mt2">
        <a href="<%=basePath %>mobile/test?view=mobile/member/storeInfo">
        <div class="detail ">
            <div class="dep-word">朋友推荐福利 </div>
            <div class="normoal-word medium-font ">领取电子现金抵用券来本店消费可抵扣现金</div>
            <div class="normoal-word medium-font ">仅限首次使用> <span class="c-linqu">点击领取</span></div>
        </div>
        
        <div class="price blue-word" style="padding-top: 2rem">
            <div class="span due-span text-center" style="width: 200px">现金抵用券</div>
            <span class="symbol-price ">￥</span>
            <span class="price-num big-font ml10p">200</span>
        </div>
        </a>
    </div>
    <!--<a href="javascript:void(0);" class="mt2 lingquan coupon-item" >
        <div class="white-word"> <span class="big-font ">￥200</span> <span class="font-size-28">现金抵用券</span></div>
        <div class="lingu-btn">接受推荐并领取</div>
    </a>-->

</div>

<script>
    $(".input-id").rating(
            {
                'clearCaption': '0分',
                'starCaptions':{
                    0.5: '0.5分',
                    1: '1.0分',
                    1.5: '1.5分',
                    2: '2.0分',
                    2.5: '2.5分',
                    3: '3.0分',
                    3.5: '3.5分',
                    4: '4.0分',
                    4.5: '4.5分',
                    5: '5.0分'
                }
            });
</script>       
</body>
</html>