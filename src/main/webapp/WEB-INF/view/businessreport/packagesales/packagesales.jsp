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

 
 <div class="maincontent">
    <div class="contentinner">
        <h4 class="widgettitle">
            <span class="dingdanzhuantai">套餐销售</span>
            <span class="video" style="float: right; font-weight: 400;color: #333;">视频帮助 <span class="iconfont icon-video" style="margin-top: 1px;"></span></span>
        </h4>
        <div class="report-title">
            <ul>
                <li>
                    <h1>3326</h1>
                    <p>本<span name="searchType">日</span> 总套餐销售数量</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>本<span name="searchType">日</span>总套餐销售业绩</p>
                </li>
                <li>
                    <h1>3326</h1>
                    <p>本<span name="searchType">日</span>平均套餐价格</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>上<span name="searchType">日</span>上期总销售业绩</p>
                </li>
                <li>
                    <h1 class="red">18.85%</h1>
                    <p>营业实收<span name="searchType">日</span>增长率</p>
                </li>
            </ul>
        </div>

        <div id="custom-toolbar" >
            <div class="table-toolbar">
                <span class="mr10">卡项销售分析</span>
                <input type="search" placeholder="往期现金收入分布/选择日期" id="history-time" name="date"/>
                <button class="button-search btn" style="margin-left: -10px;">查询</button>
                <div class="select-target-report">
                    <span class="curent-report ml30"><a href="report-shops-sell.html">日</a></span>
                    <span class="report-category ml30"><a href="report-shops-sell-week.html">日</a></span>
                    <span class="report-category ml10"><a href="report-shops-sell-month.html">月</a></span>
                    <span class="report-category ml10"><a href="report-shops-sell-month-cash.html">月走势</a></span>
                    <span class="report-category ml10"><a href="report-shops-sell-year-cash.html">年走势</a></span>
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="card-sale" style="min-width:700px;height:400px"></div><!--cash-day-->
        </div>

        <!--套餐销售业绩分类汇总-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">套餐销售业绩分类汇总</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->

            <table class="table table-bordered" style="border-bottom:1px solid #123234;">
                <thead>
                <tr>
                    <th >部门</th>
                    <th >系列</th>
                    <th >数量</th>
                    <th >数量占比</th>
                    <th >金额</th>
                    <th >金额占比</th>
                    <th >平均购买单价</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td rowspan="2">美发部</td>
                    <td>A套餐</td>
                    <td>5</td>
                    <td>5%</td>
                    <td>1500</td>
                    <td>15%</td>
                    <td>300</td>
                </tr>
                <tr>
                    <td>B套餐</td>
                    <td>5</td>
                    <td>5%</td>
                    <td>1500</td>
                    <td>15%</td>
                    <td>300</td>
                </tr>
                <tr class="light-tr">
                    <td>美发部套餐合计</td>
                    <td></td>
                    <td>5</td>
                    <td>5%</td>
                    <td>1500</td>
                    <td>15%</td>
                    <td>300</td>
                </tr>
                <tr>
                    <td rowspan="2">美发部</td>
                    <td>A套餐</td>
                    <td>5</td>
                    <td>5%</td>
                    <td>1500</td>
                    <td>15%</td>
                    <td>300</td>
                </tr>
                <tr>
                    <td>B套餐</td>
                    <td>5</td>
                    <td>5%</td>
                    <td>1500</td>
                    <td>15%</td>
                    <td>300</td>
                </tr>
                <tr class="light-tr">
                    <td>美发部套餐合计</td>
                    <td></td>
                    <td>5</td>
                    <td>5%</td>
                    <td>1500</td>
                    <td>15%</td>
                    <td>300</td>
                </tr>
                <tr>
                    <td colspan="7">
                        <div class="s-btn-group fr">
                            <button class="btn ml10">
                                <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                                <span >导出</span>
                            </button>
                            <button class="btn ml10">
                                <span>打印</span>
                            </button>
                        </div>
                    </td>
                </tr>
                <tr class="zhanbi-tr " >
                    <td colspan="6"  class="border-lr-none ">
                        门店总计
                    </td>
                    <td class="border-lr-none ">100000</td>
                </tr>
                </tbody>
            </table>
        </div>
        <!--套餐销售排行 -->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">套餐销售排行</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>项目名称</th>
                    <th style="position: relative">
                        <span class="dropdown-toggle" data-toggle="dropdown">
                            所属部门
                            <i class="FontAwesome iconfa-caret-down afont ml8 " ></i>
                        </span>
                        <ul class="dropdown-menu">
                            <li><a href="#">美发</a></li>
                            <li><a href="#">美容</a></li>
                            <li><a href="#">足浴</a></li>
                        </ul>
                    </th>
                    <th>销售数量
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总销售额
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>平均购买单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>本期销售额排行</th>
                    <th>上期销售额排行</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>韩式剪</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>头皮护理</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>头皮护理</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="8">
                        <div class="s-btn-group fr">
                            <button class="btn ml10">
                                <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                                <span >导出</span>
                            </button>
                            <button class="btn ml10">
                                <span>打印</span>
                            </button>
                        </div>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>
        <!--连锁套餐销售排行-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">套餐销售排行</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>项目名称</th>
                    <th style="position: relative">
                        <span class="dropdown-toggle" data-toggle="dropdown">
                            所属部门
                            <i class="FontAwesome iconfa-caret-down afont ml8 " ></i>
                        </span>
                        <ul class="dropdown-menu">
                            <li><a href="#">美发</a></li>
                            <li><a href="#">美容</a></li>
                            <li><a href="#">足浴</a></li>
                        </ul>
                    </th>
                    <th>总销售数量
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总销售业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>平均购买单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>本期销售业绩排行</th>
                    <th>上期销售业绩排行</th>
                    <th>最佳项目门店/数量</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>韩式剪</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                    <td>罗湖店 358</td>
                </tr>
                <tr>
                    <td>头皮护理</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                    <td>罗湖店 358</td>
                </tr>
                <tr>
                    <td>头皮护理</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                    <td>罗湖店 358</td>
                </tr>
                </tbody>
            </table>
        </div>
        <!--连锁店套餐销售总业绩排行-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">连锁店套餐销售总业绩排行</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>门店</th>
                    <th style="position: relative">
                        总销售件数
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总销售业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>平均购买单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>本期销售业绩排行</th>
                    <th>上期销售业绩排行</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>福田店</td>
                    <td>200</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>罗湖店</td>
                    <td>200</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>罗湖店</td>
                    <td>200</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr class="huizong-tr">
                    <td>连锁汇总</td>
                    <td>200</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    jQuery(function () {

        jQuery(function () {
            var colors = Highcharts.getOptions().colors,
                    categories = ['常规项目', '套餐', '疗程', '商品'],
                    name = '收入分布',
                    data = [{
                        y: 55.11,
                        color: colors[0],
                        drilldown: {
                            name: '常规项目',
                            categories: ['洗护产品', '烫发产品', '染发产品', '造型产品', '护理产品'],
                            data: [10.85, 7.35, 33.06, 2.81, 3.1],
                            color: colors[0]
                        }
                    }, {
                        y: 21.63,
                        color: colors[1],
                        drilldown: {
                            name: '套餐',
                            categories: ['Firefox 2.0', 'Firefox 3.0', 'Firefox 3.5', 'Firefox 3.6', 'Firefox 4.0'],
                            data: [0.20, 0.83, 1.58, 13.12, 5.43],
                            color: colors[1]
                        }
                    }, {
                        y: 11.94,
                        color: colors[2],
                        drilldown: {
                            name: '疗程',
                            categories: ['Chrome 5.0', 'Chrome 6.0', 'Chrome 7.0', 'Chrome 8.0', 'Chrome 9.0',
                                'Chrome 10.0', 'Chrome 11.0', 'Chrome 12.0'],
                            data: [0.12, 0.19, 0.12, 0.36, 0.32, 9.91, 0.50, 0.22],
                            color: colors[2]
                        }
                    }, {
                        y: 7.15,
                        color: colors[3],
                        drilldown: {
                            name: '商品',
                            categories: ['Safari 5.0', 'Safari 4.0', 'Safari Win 5.0', 'Safari 4.1', 'Safari/Maxthon',
                                'Safari 3.1', 'Safari 4.1'],
                            data: [4.55, 1.42, 0.23, 0.21, 0.20, 0.19, 0.14],
                            color: colors[3]
                        }
                    }];

            function setChart(name, categories, data, color) {
                /*chart.xAxis[0].setCategories(categories, false);*/
                chart.series[0].remove(false);
                chart.addSeries({
                    name: name,
                    data: data,
                    color: color || 'white'
                }, false);
                chart.redraw();
            }

            var chart = jQuery('#card-sale').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '左右造型连锁东海店卡项销售汇总 2015年2月4日'
                },
                subtitle: {
                    text: '点击柱状条查看分类现金收入分布'
                },
                xAxis: {
                    categories: categories
                },
                yAxis: {
                    title: {
                        text: '卡金收入金额'
                    }
                },
                plotOptions: {
                    column: {
                        cursor: 'pointer',
                        point: {
                            events: {
                                click: function() {
                                    var drilldown = this.drilldown;
                                    if (drilldown) { // drill down
                                        setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                                    } else { // restore
                                        setChart(name, categories, data);
                                    }
                                }
                            }
                        },
                        dataLabels: {
                            enabled: true,
                            color: colors[0],
                            style: {
                                fontWeight: 'bold',
                                boxShadow: 'none'
                            },
                            formatter: function() {
                                return this.y +'%';
                            }
                        }
                    }
                },
                tooltip: {
                    formatter: function() {
                        var point = this.point,
                                s = this.x +':<b>'+ this.y +'% market share</b><br/>';
                        if (point.drilldown) {
                            s += 'Click to view '+ point.category +' versions';
                        } else {
                            s += 'Click to return to browser brands';
                        }
                        return s;
                    }
                },
                series: [{
                    name: name,
                    data: data,
                    color: 'white'
                }],
                exporting: {
                    enabled: true
                }
            }).highcharts(); // return chart

        });
    });

</script>


    </div>



    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
   <!-- <div class="left-show-btn">
        <span class="iconfont icon-quanbu110"></span>
    </div>-->
    <a href="" class="showmenu"></a>




</body>
</html>