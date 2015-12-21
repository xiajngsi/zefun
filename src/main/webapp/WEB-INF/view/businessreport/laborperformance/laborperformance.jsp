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
        <div class="report-title">
            <ul>
                <li>
                    <h1>3326</h1>
                    <p>本<span name="searchType">日</span>总服务人数</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>本<span name="searchType">日</span>总劳动业绩</p>
                </li>
                <li>
                    <h1>3326</h1>
                    <p>本<span name="searchType">日</span>平均客单</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>上<span name="searchType">日</span>上期劳动业绩</p>
                </li>
                <li>
                    <h1 class="red">18.85%</h1>
                    <p>营业实收<span name="searchType">日</span>增长率</p>
                </li>
            </ul>
        </div>

        <div id="custom-toolbar" >
            <div class="table-toolbar">
                <span class="mr10">日期</span>
                <input type="text" class="datetimepicker input80" daysOffset="0" id="startDate" name="startDate"/>－
                <input type="text" class="datetimepicker input80" daysOffset="0" id="endDate" name="endDate"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="button-search btn" style="margin-left: -10px;">查询</button>
                <div class="select-target-report">
                    <span onclick="changeDate(1,this);" class="curent-report ml30"><a href="javascript:void(0);">日</a></span>
                    <span onclick="changeDate(2,this);" class="report-category ml10"><a href="javascript:void(0);">周</a></span>
                    <span onclick="changeDate(3,this);" class="report-category ml10"><a href="javascript:void(0);">月</a></span>
                    <span onclick="changeDate(4,this);" class="report-category ml10"><a href="javascript:void(0);">月趋势</a></span>
                    <span onclick="changeDate(5,this);" class="report-category ml10"><a href="javascript:void(0);">年趋势</a></span>
                    <span class="report-category ml10"><a href="yingyehuizong-zongdian.html">总部汇总</a></span>
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="cash-day" style="min-width:700px;height:400px"></div><!--cash-day-->
        </div>

        <!--门店劳动业绩分类汇总-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店劳动业绩分类汇总</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->

            <table class="table table-bordered" style="border-bottom:1px solid #123234;">
                <thead>
                <tr>
                    <th >部门</th>
                    <th >系列</th>
                    <th >项目</th>
                    <th >数量</th>
                    <th >数量占比</th>
                    <th >金额</th>
                    <th >金额占比</th>
                    <th >客单价</th>
                </tr>
                </thead>
                <tbody>
                  <tr>
                      <td rowspan="9">美发</td>
                      <td rowspan="3">洗剪吹系列</td>
                      <td>洗剪吹</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td>韩式剪</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr class="light-tr">
                      <td>洗剪吹系列合计</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td rowspan="3">护理系列</td>
                      <td>头皮护理</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td>头发护理</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr class="light-tr">
                      <td>护理系列合计</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td rowspan="3">烫染系列</td>
                      <td>施华蔻烫</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td>欧莱雅烫</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr class="light-tr">
                      <td>烫染系列合计</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr class="weight-tr">
                      <td></td>
                      <td></td>
                      <td>美发部门合计</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td rowspan="9">美容</td>
                      <td rowspan="3">洗剪吹系列</td>
                      <td>洗剪吹</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td>韩式剪</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr class="light-tr">
                      <td>洗剪吹系列合计</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td rowspan="3">护理系列</td>
                      <td>头皮护理</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td>头发护理</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr class="light-tr">
                      <td>护理系列合计</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td rowspan="3">烫染系列</td>
                      <td>施华蔻烫</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr>
                      <td>欧莱雅烫</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr class="light-tr">
                      <td>烫染系列合计</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
                  <tr class="weight-tr">
                      <td></td>
                      <td></td>
                      <td>美发部门合计</td>
                      <td>5</td>
                      <td>5%</td>
                      <td>1500</td>
                      <td>15%</td>
                      <td>300</td>
                  </tr>
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
                  <tr class="zhanbi-tr " >
                      <td colspan="7"  class="border-lr-none ">
                          门店总计
                      </td>
                      <td class="border-lr-none ">100000</td>
                  </tr>
                </tbody>
            </table>
        </div>
        <!--项目劳动业绩排行-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">项目劳动业绩排行</span>
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
                    <th>数量
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>客单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>平均服务时长</th>
                    <th>本期业绩店内排行</th>
                    <th>上期业绩店内排行</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>韩式剪</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>30分钟</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>头皮护理</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>30分钟</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>头皮护理</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>30分钟</td>
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
        <!--项目劳动业绩排行-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">项目劳动业绩排行</span>
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
                    <th>数量
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>客单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>平均服务时长</th>
                    <th>本期业绩店内排行</th>
                    <th>上期业绩店内排行</th>
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
                    <td>30分钟</td>
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
                    <td>30分钟</td>
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
                    <td>30分钟</td>
                    <td>1</td>
                    <td>3</td>
                    <td>罗湖店 358</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="9">
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
        <!--门店劳动业绩排行-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店劳动业绩排行</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>门店</th>
                    <th style="position: relative">
                        总客数
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>客单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>本期总业绩排行</th>
                    <th>上期总业绩排行</th>
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
                <tfoot>
                <tr>
                    <td colspan="6">
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
    </div>

</div>

<script type="text/javascript">
    var date = new Date();
    var syear = date.getFullYear() + '年';
    var smonth = date.getFullYear() + '年' + (date.getMonth() + 1) + '月1日 － ' + date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDaysInMonth() + '日';
    var sweekStart = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + (1 - date.getDay() + date.getDate()) + '日';
    var sweekEnd = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + (7 - date.getDay() + date.getDate()) + '日';
    var sweek = sweekStart + ' － ' + sweekEnd;
    var sdate = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日';
    jQuery(function () {
//        initDay();
        var colors = Highcharts.getOptions().colors,
                categories = ['美发部', '美容部'],
                name = '劳动业绩汇总',
                data = [{
                    y: 25511,
                    color: colors[0],
                    drilldown: {
                        name: '美发部',
                        categories: ['洗剪吹', '烫发', '染发', '护发'],
                        data: [1085, 7135, 3306, 2181],
                        color: colors[0]
                    }
                }, {
                    y: 21363,
                    color: colors[1],
                    drilldown: {
                        name: '美容部',
                        categories: ['面部', '手足', '全身'],
                        data: [2020, 3083, 1858],
                        color: colors[1]
                    }
                }];

        function setChart(name, categories, data, color) {
            chart.series[0].remove(false);
            chart.addSeries({
                name: name,
                data: data,
                color: color || 'white'
            }, false);
            chart.redraw();
        }

        var chart = jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '劳动业绩汇总'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                title: {
                    text: '金额'
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
                            return this.y;
                        }
                    }
                }
            },
            tooltip: {
                formatter: function() {
                    var point = this.point,
                            s = this.x +'劳动业绩合计'+':<b>'+ this.y +'</b><br/>';
                    if (point.drilldown) {
                        s += '点击查看'+ point.category +'各系列详情';
                    } else {
                        s += '点击返回';
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

    function initDay(){
        jQuery("#startDate").val(new Date().dateFormat('Y/m/d'));
        jQuery("#endDate").val(new Date().dateFormat('Y/m/d'));
        jQuery("#tableDate").html(sdate);
        jQuery("span[name='searchType']").html('日');



        jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '项目消费统计'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                type: 'category'
            },
            yAxis: {
                title: {
                    text: '金额 (元)'
                }

            },
            legend: {
                enabled: false
            },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
                        format: '{point.y}'
                    }
                }
            },

            tooltip: {
                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
            },

            series: [{
                name: "部门",
                colorByPoint: true,
                data: [{
                    name: "美发部",
                    y: 5633,
                    drilldown: "meifabu"
                }, {
                    name: "美容部",
                    y: 2403,
                    drilldown: "meirongbu"
                }]
            }],
            drilldown: {
                series: [{
                    name: "美发部",
                    id: "meifabu",
                    data: [
                        [
                            "洗剪吹",
                            2413
                        ],
                        [
                            "烫发",
                            1720
                        ],
                        [
                            "染发",
                            8110
                        ],
                        [
                            "护发",
                            5331
                        ]
                    ]
                }, {
                    name: "美容部",
                    id: "meirongbu",
                    data: [
                        [
                            "面部",
                            5123
                        ],
                        [
                            "手足",
                            4322
                        ],
                        [
                            "全身",
                            3168
                        ]
                    ]
                }
            ]}
        });
    }
    function changeDate(type, obj) {
        jQuery(".curent-report").removeClass('curent-report').addClass('report-category');
        console.log(jQuery(obj).parent());
        jQuery(obj).removeClass('report-category').addClass('curent-report');
        switch(type) {
            //周
            case 2:
                var d = new Date();
                d.setDate(1 - date.getDay() + date.getDate());
                jQuery("#startDate").val(d.dateFormat('Y/m/d'));
                d.setDate(7 - date.getDay() + date.getDate());
                jQuery("#endDate").val(d.dateFormat('Y/m/d'));
                jQuery("#tableDate").html(sweek);
                jQuery("span[name='searchType']").html('周');

                jQuery('#cash-day').highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: '门店营业汇总(周)'
                    },
                    subtitle: {
                        text: sweek
                    },
                    xAxis: {
                        categories: [
                            '美容部',
                            '美发部',
                            '美甲部'
                        ],
                        crosshair: true
                    },
                    yAxis: {
                        title: {
                            text: '金额 (元)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y} 元</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0
                        }
                    },
                    series: [{
                        name: '营业收入',
                        data: [49342, 74231, 10623]

                    }, {
                        name: '营业扣减',
                        data: [82342, 71448, 94234]

                    }, {
                        name: '营业实收',
                        data: [43943, 38320, 39432]

                    }]
                });
                break;
            //月
            case 3:
                var d = new Date();
                d.setDate(1);
                jQuery("#startDate").val(d.dateFormat('Y/m/d'));
                d.setDate(date.getDaysInMonth());
                jQuery("#endDate").val(d.dateFormat('Y/m/d'));
                jQuery("#tableDate").html(smonth);
                jQuery("span[name='searchType']").html('月');

                jQuery('#cash-day').highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: '门店营业汇总(月)'
                    },
                    subtitle: {
                        text: smonth
                    },
                    xAxis: {
                        categories: [
                            '美容部',
                            '美发部',
                            '美甲部'
                        ],
                        crosshair: true
                    },
                    yAxis: {
                        title: {
                            text: '金额 (元)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y} 元</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0
                        }
                    },
                    series: [{
                        name: '营业收入',
                        data: [49342, 74231, 10623]

                    }, {
                        name: '营业扣减',
                        data: [82342, 71448, 94234]

                    }, {
                        name: '营业实收',
                        data: [43943, 38320, 39432]

                    }]
                });
                break;
            //月趋势
            case 4:
                var d = new Date();
                d.setDate(1);
                jQuery("#startDate").val(d.dateFormat('Y/m/d'));
                d.setDate(date.getDaysInMonth());
                jQuery("#endDate").val(d.dateFormat('Y/m/d'));
                jQuery("#tableDate").html(smonth);
                jQuery("span[name='searchType']").html('月');

                jQuery('#cash-day').highcharts({
                    chart: {
                        type: 'spline'
                    },
                    title: {
                        text: '门店营业汇总(月走势)'
                    },
                    subtitle: {
                        text: smonth
                    },
                    xAxis: {
                        categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15',
                            '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31']
                    },
                    yAxis: {
                        title: {
                            text: '金额 (元)'
                        }
                    },
                    tooltip: {
                        crosshairs: true,
                        headerFormat: '<span style="font-size:10px">' + sdate + '{point.key}日</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y} </b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        line: {
                            dataLabels: {
                                enabled: true
                            },
                            enableMouseTracking: false
                        },
                        spline: {
                            lineWidth: 2,
                            states: {
                                hover: {
                                    lineWidth: 3
                                }
                            },
                            marker: {
                                enabled: false
                            }
                        }
                    },
                    series: [{
                        name: '营业收入',
                        data: [742, 621, 914, 134, 418, 121, 425, 226, 323, 618, 183, 929, 929, 929, 929,
                            742, 621, 914, 134, 418, 121, 425, 226, 323, 618, 183, 929, 929, 929, 929, 328]
                    }, {
                        name: '营业扣减',
                        data: [212, 211, 374, 94, 128, 182, 214, 412, 142, 123, 89, 284, 325, 216, 310,
                            127, 431, 209, 237, 128, 432, 213, 431, 512, 341, 431, 124, 231, 291, 287, 298]
                    }, {
                        name: '营业实收',
                        data: [112, 311, 474, 194, 328, 282, 314, 112, 542, 223, 289, 384, 425, 816, 910,
                            227, 831, 909, 1037, 228, 632, 413, 331, 712, 241, 131, 724, 531, 491, 387, 798]
                    }]
                });
                break;
            //年趋势
            case 5:
                var d = new Date();
                d.setMonth(0);
                d.setDate(1);
                jQuery("#startDate").val(d.dateFormat('Y/m/d'));
                d.setMonth(11);
                d.setDate(date.getDaysInMonth());
                jQuery("#endDate").val(d.dateFormat('Y/m/d'));
                jQuery("#tableDate").html(syear);
                jQuery("span[name='searchType']").html('年');

                jQuery('#cash-day').highcharts({
                    chart: {
                        type: 'spline'
                    },
                    title: {
                        text: '门店营业汇总(年走势)'
                    },
                    subtitle: {
                        text: syear
                    },
                    xAxis: {
                        categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']
                    },
                    yAxis: {
                        title: {
                            text: '金额 (元)'
                        }
                    },
                    tooltip: {
                        crosshairs: true,
                        headerFormat: '<span style="font-size:10px">' + sdate + '{point.key}月</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y} </b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    spline: {
                        lineWidth: 2,
                        states: {
                            hover: {
                                lineWidth: 3
                            }
                        },
                        marker: {
                            enabled: false
                        }
                    },
                    series: [{
                        name: '营业收入',
                        data: [742, 621, 914, 134, 418, 121, 425, 226, 323, 618, 183, 929]
                    }, {
                        name: '营业扣减',
                        data: [212, 211, 374, 94, 128, 182, 214, 412, 142, 123, 89, 284]
                    }, {
                        name: '营业实收',
                        data: [412, 611, 774, 394, 428, 182, 314, 512, 842, 1123, 1089, 2184]
                    }]
                });
                break;
            //默认为日
            default:
                initDay();
        }
    }
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


</div><!--mainwrapper-->
 

<script>
var pageNo = "${page.pageNo}";
var pageSize = "${page.pageSize}";
var totalPage = "${page.totalPage}";
jQuery(function(){
    jQuery(".n-sub-tab").on("click", function(){
      jQuery(".n-sub-tab").removeClass("active");
      jQuery(this).addClass("active");
      var targetTab = jQuery(this).data("target");
      if(targetTab == "#tab2"){
        jQuery(".tab-word").css("border","0px");
      }else{
        jQuery(".tab-word").css("border","");
      }
      var id=jQuery(this).attr("id");
      jQuery("#xdeptId").val(id);
  	shiftinfo(id);
  	changePage();
      jQuery(".target-tab").addClass("hide");
      jQuery(targetTab).removeClass("hide");
    })
  })
jQuery(function(){
	jQuery(".n-sub-tab").eq(0).addClass("active");
//jQuery(".page-item").eq(0).children(".trangle-css").removeClass("hide");
//dialog("==========="+jQuery(".n-sub-tab").eq(0).attr("id"));
var id=jQuery(".n-sub-tab").eq(0).attr("id");
//dialog(id);
jQuery("#xdeptId").val(id);
shiftinfo(id);
changePage();
//时间
 jQuery('.datetime').datetimepicker({
           datepicker:false,
           format:'H:i',
           step:5
       });

  })
    


jQuery("input[type='radio']").on("click", function(){
            var th = jQuery(this);
             var tr = th.parents("tr");
            tr.find(".check-radio").removeClass("check-after");
            if(th.is(":checked")){
                th.siblings(".check-radio").addClass("check-after");
            }
        });
</script>
<script type="text/javascript" src="<%=basePath %>js/employee/shift.js"></script>

</div>
    
    
    <!--RIGHT PANEL结束 -->


</div><!--mainwrapper-->

</body>
</html>