<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>

<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    
    <div class="rightpanel" style="margin-left: 200px;">
    <%@ include file="/top.jsp" %>

 
 <div class="maincontent" style="overflow-y: auto">
    <div class="contentinner">

        <div class="more-toolbar" >
            <div class="table-toolbar zhibanbiao">
                <span class="font-size-16 btn-color mr10 fl">营业状况分析表 <small>左右连锁东海店</small></span>
                <div class="s-btn-group fl">
                    <button data-toggle="dropdown" class="btn dropdown-toggle">东海店 <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="#">东海店</a></li>
                        <li><a href="#">左右造型连锁店</a></li>
                    </ul>
                    <button data-toggle="dropdown" class="btn dropdown-toggle">2015年 <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="#">2015年</a></li>
                        <li><a href="#">2014年</a></li>
                        <li><a href="#">2013年</a></li>
                        <li><a href="#">2012年</a></li>
                    </ul>
                    <button data-toggle="dropdown" class="btn dropdown-toggle">2月 <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="#">1月</a></li>
                        <li><a href="#">2月</a></li>
                        <li><a href="#">3月</a></li>
                        <li><a href="#">4月</a></li>
                        <li><a href="#">5月</a></li>
                        <li><a href="#">6月</a></li>
                        <li><a href="#">7月</a></li>
                        <li><a href="#">8月</a></li>
                        <li><a href="#">9月</a></li>
                        <li><a href="#">10月</a></li>
                        <li><a href="#">11月</a></li>
                        <li><a href="#">12月</a></li>
                    </ul>
                </div>
                <span class="video fr">视频帮助 <span class="iconfont icon-shipin" style="margin-top: 1px;"></span></span>
            </div>
            <div class="clearfix"></div>
        </div><!--more-toolbar-->

            <div class="widgetcontent">
                <table  class="table">
                    <thead>
                    <tr>
                        <th class="nav_td bold">劳动业绩 </th>
                        <th class="nav_td bold">业绩金额</th>
                        <th class="nav_td bold">占比</th>
                        <th class="nav_td bold">本月目标</th>
                        <th class="nav_td bold">完成率</th>
                        <th class="nav_td bold">上月同期</th>
                        <th class="nav_td bold">增长</th>
                        <th class="nav_td bold">增长率</th>
                        <th class="nav_td bold">去年同期</th>
                        <th class="nav_td bold">增长</th>
                        <th class="nav_td bold">增长率</th>
                    </tr>
                    </thead>
                    <tr>
                        <td class="bold">常规项目</td>
                        <td>19203</td>
                        <td>20000</td>
                        <td>2000</td>
                        <td>2000</td>
                        <td>2000</td>
                        <td>2000</td>
                        <td>2000</td>
                        <td>2000</td>
                        <td>2000</td>
                        <td>2000</td>
                    </tr>
                    <tr>
                        <td class="bg_gray bold">套餐</td>
                        <td class="bg_gray">21445</td>
                        <td class="bg_gray">21445</td>
                        <td class="bg_gray">21445</td>
                        <td class="bg_gray">21445</td>
                        <td class="bg_gray">21445</td>
                        <td class="bg_gray">21445</td>
                        <td class="bg_gray">21445</td>
                        <td class="bg_gray">21445</td>
                        <td class="bg_gray">21445</td>
                        <td class="bg_gray">21445</td>
                    </tr>
                    <tr>
                        <td class="bold">疗程</td>
                        <td>12408</td>
                        <td>12408</td>
                        <td>12408</td>
                        <td>12408</td>
                        <td>12408</td>
                        <td>12408</td>
                        <td>12408</td>
                        <td>12408</td>
                        <td>12408</td>
                        <td>12408</td>
                    </tr>
                    <tr class="bg-gray">
                        <td class="browm bold">劳动汇总</td>
                        <td class="browm">9379</td>
                        <td class="browm ">9379</td>
                        <td class="browm ">9379</td>
                        <td class="browm ">9379</td>
                        <td class="browm ">9379</td>
                        <td class="browm ">9379</td>
                        <td class="browm ">9379</td>
                        <td class="browm ">9379</td>
                        <td class="browm ">9379</td>
                        <td class="browm ">9379</td>
                    </tr>
                </table>
                <table class="table " style="margin-top:15px;width:95%;float:right">
                    <thead>
                    <tr>
                        <th class="nav_td bold topline_black">常规项目分析</th>
                        <th class="nav_td bold topline_black">业绩金额</th>
                        <th class="nav_td bold topline_black">金额占比</th>
                        <th class="nav_td bold topline_black">客数</th>
                        <th class="nav_td bold topline_black">客数占比</th>
                        <th class="nav_td bold topline_black">上月同期</th>
                        <th class="nav_td bold topline_black">增长</th>
                        <th class="nav_td bold topline_black">增长率</th>
                        <th class="nav_td bold topline_black">去年同期</th>
                        <th class="nav_td bold topline_black">增长</th>
                        <th class="nav_td bold topline_black">增长率</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>洗剪吹系列</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="bg_gray">烫发系列</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>染发系列</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="bg_gray bottomline_black">护理系列</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                    </tr>
                </table>
                <table class="table " style="margin-top:15px;width:95%;float:right">
                    <thead>
                    <tr>
                        <th class="nav_td bold topline_black">套餐分析</th>
                        <th class="nav_td bold topline_black">业绩金额</th>
                        <th class="nav_td bold topline_black">金额占比</th>
                        <th class="nav_td bold topline_black">客数</th>
                        <th class="nav_td bold topline_black">客数占比</th>
                        <th class="nav_td bold topline_black">上月同期</th>
                        <th class="nav_td bold topline_black">增长</th>
                        <th class="nav_td bold topline_black">增长率</th>
                        <th class="nav_td bold topline_black">去年同期</th>
                        <th class="nav_td bold topline_black">增长</th>
                        <th class="nav_td bold topline_black">增长率</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>洗护套餐</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="bg_gray">烫染套餐</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>情人节套餐</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="bg_gray bottomline_black">美颜套餐</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                    </tr>
                </table>
                <table class="table " style="margin-top:15px;width:95%;float:right">
                    <tr>
                        <td class="nav_td bold topline_black">疗程分析</td>
                        <td class="nav_td bold topline_black">业绩金额</td>
                        <td class="nav_td bold topline_black">金额占比</td>
                        <td class="nav_td bold topline_black">客数</td>
                        <td class="nav_td bold topline_black">客数占比</td>
                        <td class="nav_td bold topline_black">上月同期</td>
                        <td class="nav_td bold topline_black">增长</td>
                        <td class="nav_td bold topline_black">增长率</td>
                        <td class="nav_td bold topline_black">去年同期</td>
                        <td class="nav_td bold topline_black">增长</td>
                        <td class="nav_td bold topline_black">增长率</td>
                    </tr>
                    <tr>
                        <td>头皮护理</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="bg_gray">生发</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                        <td class=" bg_gray">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>美甲</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="bg_gray bottomline_black">减肥</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                        <td class=" bg_gray bottomline_black">&nbsp;</td>
                    </tr>
                </table>
                <div class="clear_both"></div>
              </div>
            <div class="widgetcontent">
                    <table class="table " style="border:0;margin-top:15px;">
                        <thead>
                        <tr>
                            <th class="nav_td bold">商品销售 </th>
                            <th class="nav_td bold">业绩金额</th>
                            <th class="nav_td bold">占比</th>
                            <th class="nav_td bold">本月目标</th>
                            <th class="nav_td bold">完成率</th>
                            <th class="nav_td bold">上月同期</th>
                            <th class="nav_td bold">增长</th>
                            <th class="nav_td bold">增长率</th>
                            <th class="nav_td bold">去年同期</th>
                            <th class="nav_td bold">增长</th>
                            <th class="nav_td bold">增长率</th>
                        </tr>
                        </thead>
                        <tr>
                            <td class="bold">洗护产品</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="bg_gray bold">烫发产品</td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                        </tr>
                        <tr>
                            <td class="bold">染发产品</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="bg_gray bold">造型产品</td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                        </tr>

                        <tr class="bg-gray">
                            <td class="browm bold">商品汇总</td>
                            <td class="browm"></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                        </tr>
                    </table>
                    <table class="table " style="border:0;margin-top:15px;">
                        <thead>
                            <tr>
                                <th class="nav_td bold">卡项销售 </th>
                                <th class="nav_td bold">新开充值业绩</th>
                                <th class="nav_td bold">占比</th>
                                <th class="nav_td bold">本月目标</th>
                                <th class="nav_td bold">完成率</th>
                                <th class="nav_td bold">上月同期</th>
                                <th class="nav_td bold">增长</th>
                                <th class="nav_td bold">增长率</th>
                                <th class="nav_td bold">去年同期</th>
                                <th class="nav_td bold">增长</th>
                                <th class="nav_td bold">增长率</th>
                            </tr>
                        </thead>
                        <tr>
                            <td class="bold">铂金卡</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="bg_gray bold">蓝宝石卡</td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                        </tr>
                        <tr>
                            <td class="bold">红宝石卡</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="bg_gray bold">资格卡</td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                        </tr>

                        <tr class="bg-gray">
                            <td class="browm bold">卡项汇总</td>
                            <td class="browm"></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                        </tr>
                    </table>
                    <table class="table table-striped" style="margin-top:15px;width:95%;float:right">
                        <thead>
                        <tr>
                            <th class="nav_td bold topline_black">用卡分析</th>                                                  </td>
                            <th class="nav_td bold topline_black">卡总数</th>
                            <th class="nav_td bold topline_black">用卡人数</th>
                            <th class="nav_td bold topline_black">用卡人数占比</th>
                            <th class="nav_td bold topline_black">用卡金额</th>
                            <th class="nav_td bold topline_black">用卡金额占比</th>
                            <th class="nav_td bold topline_black">月初卡金节约</th>
                            <th class="nav_td bold topline_black">当前卡金结余</th>
                            <th class="nav_td bold topline_black">余额浮动率</th>
                        </tr>
                        </thead>
                        <tr>
                            <td>铂金卡</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="bg_gray">蓝宝石卡</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>红宝石卡</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="bg_gray bottomline_black">其他卡</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="9" class="browm bottomline_black" style="padding:0; margin:0;height:2px"></td>
                        </tr>
                        <tr>
                            <td class="bold">用卡汇总</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <table class="table table-striped" style="margin-top:15px;width:95%;float:right">
                        <tr>
                            <td class="nav_td bold topline_black">卡金消费分析                                                             </td>
                            <td class="nav_td bold topline_black">常规项目</td>
                            <td class="nav_td bold topline_black">占比</td>
                            <td class="nav_td bold topline_black">消费/单</td>
                            <td class="nav_td bold topline_black">套餐</td>
                            <td class="nav_td bold topline_black">占比</td>
                            <td class="nav_td bold topline_black">消费/单</td>
                            <td class="nav_td bold topline_black">疗程</td>
                            <td class="nav_td bold topline_black">占比</td>
                            <td class="nav_td bold topline_black">消费/单</td>
                            <td class="nav_td bold topline_black">商品</td>
                            <td class="nav_td bold topline_black">占比</td>
                            <td class="nav_td bold topline_black">消费/单</td>
                            <td class="nav_td bold topline_black">平均消费/单</td>
                        </tr>
                        <tr>
                            <td>铂金卡</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="bg_gray">蓝宝石卡</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                            <td class=" bg_gray">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>红宝石卡</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="bg_gray bottomline_black">其他卡</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                            <td class=" bg_gray bottomline_black">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="14" class="browm bottomline_black" style="padding:0; margin:0;height:2px"></td>
                        </tr>
                        <tr>
                            <td class="bold">卡金消费汇总</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <div class="clear_both"></div>
                    </div>
            <div class="widgetcontent">
                    <table class="table " style="border:0;margin-top:15px;">
                        <thead>
                        <tr class="nav_td">
                            <th class="nav_td bold">客情分析 </th>
                            <th class="nav_td bold"> 客数</th>
                            <th class="nav_td bold">客数占比</th>
                            <th class="nav_td bold">业绩</th>
                            <th class="nav_td bold">业绩占比</th>
                            <th class="nav_td bold">客单价</th>
                            <th class="nav_td bold">上月客数</th>
                            <th class="nav_td bold">增长</th>
                            <th class="nav_td bold">上月业绩</th>
                            <th class="nav_td bold">增长</th>
                            <th class="nav_td bold">上月客单价</th>
                            <th class="nav_td bold">增长</th>
                        </tr>
                        </thead>
                        <tr>
                            <td class="bold">会员</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="bg_gray bold">散客</td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                            <td class="bg_gray"></td>
                        </tr>
                        <tr class="bg-gray">
                            <td class="browm bold">客情汇总</td>
                            <td class="browm"></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                            <td class="browm "></td>
                        </tr>
                    </table>
                </div>

    </div>
</div>
</div>
</div>

</body>
</html>