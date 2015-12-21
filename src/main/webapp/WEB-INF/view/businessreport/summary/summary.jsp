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
                    <p>本<span name="searchType">日</span>营业收入</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>本<span name="searchType">日</span>营业扣减</p>
                </li>
                <li>
                    <h1>3326</h1>
                    <p>本<span name="searchType">日</span>营业实收</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>上<span name="searchType">日</span>营业实收</p>
                </li>
                <li>
                    <h1 class="red">18.85%</h1>
                    <p>营业实收<span name="searchType">日</span>增长</p>
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
                    <span class="report-category ml10"><a href="yingyehuizong-zongbu.html">总部汇总</a></span>
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="cash-day" style="min-width:700px;height:400px"></div><!--cash-day-->
        </div>


        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店营业汇总</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->

            <table class="table dep-border-table table-bordered table-striped">
                <thead>
                <tr>
                    <th rowspan="2" class="text-center">部门</th>
                    <th colspan="5" class="text-center table-part dep-bottom-botrder">营业收入</th>
                    <th colspan="6" class="text-center  table-part dep-bottom-botrder">营业扣减</th>
                    <th rowspan="2" class="text-center  table-part">现金实收</th>
                </tr>
                <tr>
                    <th class="table-part">项目</th>
                    <th>套餐</th>
                    <th >外卖</th>
                    <th>卡项</th>
                    <th>收入汇总</th>
                    <th class="table-part">划卡</th>
                    <th>礼金</th>
                    <th>挂账</th>
                    <th>优惠券</th>
                    <th>签单</th>
                    <th>扣减汇总</th>
                </tr>
                </thead>
                  <tbody>
                    <tr>
                        <td>美发部</td>
                        <td class="table-part">239824</td>
                        <td>23442</td>
                        <td>12332</td>
                        <td>42342</td>
                        <td>42232</td>
                        <td class="table-part">42232</td>
                        <td>234242</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                        <td>2342342</td>
                        <td class="table-part">2342342</td>
                    </tr>
                    <tr>
                        <td>美容部</td>
                        <td class="table-part">239824</td>
                        <td>23442</td>
                        <td>12332</td>
                        <td>42342</td>
                        <td>42232</td>
                        <td class="table-part">42232</td>
                        <td>234242</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                        <td>2342342</td>
                        <td class="table-part">2342342</td>
                    </tr>

                  </tbody>
                <tfoot>
                <tr class="zhanbi-tr">
                    <td>汇总</td>
                    <td class="table-part">239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42232</td>
                    <td>42342</td>
                    <td class="table-part">42232
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td class="table-part">2342342</td>
                </tr>
                <tr class="huizong-tr dep-bottom-botrder">
                    <td>占比</td>
                    <td class="table-part">5%</td>
                    <td>5%</td>
                    <td>5%</td>
                    <td>50%</td>
                    <td>100%</td>
                    <td class="table-part">5%</td>
                    <td>5%</td>
                    <td>25%</td>
                    <td>20%</td>
                    <td>10%</td>
                    <td>100%</td>
                    <td class="table-part"></td>
                </tr>
                </tfoot>
            </table>
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span >导出</span>
                        </button>
                        <button class="btn ml10">
                            <span>打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
        </div>

        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">连锁营业汇总</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->

            <table class="table dep-border-table table-bordered table-striped">
                <thead>
                <tr>
                    <th rowspan="2" class="text-center">分店</th>
                    <th colspan="5" class="text-center table-part dep-bottom-botrder">营业收入</th>
                    <th colspan="6" class="text-center  table-part dep-bottom-botrder">营业扣减</th>
                    <th rowspan="2" class="text-center  table-part">现金实收</th>
                </tr>
                <tr>
                    <th class="table-part">项目</th>
                    <th>套餐</th>
                    <th >外卖</th>
                    <th>卡项</th>
                    <th>收入汇总</th>
                    <th class="table-part">划卡</th>
                    <th>礼金</th>
                    <th>挂账</th>
                    <th>优惠券</th>
                    <th>签单</th>
                    <th>扣减汇总</th>
                </tr>
                </thead>
                  <tbody>
                    <tr>
                        <td>南山店</td>
                        <td class="table-part">239824</td>
                        <td>23442</td>
                        <td>12332</td>
                        <td>42342</td>
                        <td>42232</td>
                        <td class="table-part">42232</td>
                        <td>234242</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                        <td>2342342</td>
                        <td class="table-part">2342342</td>
                    </tr>
                    <tr>
                        <td>福田店</td>
                        <td class="table-part">239824</td>
                        <td>23442</td>
                        <td>12332</td>
                        <td>42342</td>
                        <td>42232</td>
                        <td class="table-part">42232</td>
                        <td>234242</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                        <td>2342342</td>
                        <td class="table-part">2342342</td>
                    </tr>

                  </tbody>
                <tfoot>
                <tr class="zhanbi-tr">
                    <td>汇总</td>
                    <td class="table-part">239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42232</td>
                    <td>42342</td>
                    <td class="table-part">42232
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td class="table-part">2342342</td>
                </tr>
                <tr class="huizong-tr dep-bottom-botrder">
                    <td>占比</td>
                    <td class="table-part">5%</td>
                    <td>5%</td>
                    <td>5%</td>
                    <td>50%</td>
                    <td>100%</td>
                    <td class="table-part">5%</td>
                    <td>5%</td>
                    <td>25%</td>
                    <td>20%</td>
                    <td>10%</td>
                    <td>100%</td>
                    <td class="table-part"></td>
                </tr>
                </tfoot>
            </table>
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span >导出</span>
                        </button>
                        <button class="btn ml10">
                            <span>打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
        </div>

        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店营业汇总月趋势</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->

            <table class="table dep-border-table table-bordered table-striped">
                <thead>
                <tr>
                    <th>日期</th>
                    <th>营业收入</th>
                    <th>营业扣减</th>
                    <th>营业实收</th>
                </tr>
                </thead>
                  <tbody>
                    <tr>
                        <td>2月1日</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                    </tr>
                    <tr>
                        <td>2月2日</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                    </tr>
                  </tbody>
                <tfoot>
                <tr class="huizong-tr dep-bottom-botrder">
                    <td>汇总</td>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>

                </tr>
                </tfoot>
            </table>
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span >导出</span>
                        </button>
                        <button class="btn ml10">
                            <span>打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
        </div>

        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">连锁营业汇总月趋势</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->

            <table class="table dep-border-table table-bordered table-striped">
                <thead>
                <tr>
                    <th>日期</th>
                    <th>营业收入</th>
                    <th>营业扣减</th>
                    <th>营业实收</th>
                </tr>
                </thead>
                  <tbody>
                    <tr>
                        <td>2月1日</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                    </tr>
                    <tr>
                        <td>2月2日</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                    </tr>
                  </tbody>
                <tfoot>
                <tr class="zhanbi-tr">
                    <td>汇总</td>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>

                </tr>
                <tr class="huizong-tr dep-bottom-botrder">
                    <td>占比</td>
                    <td>5%</td>
                    <td>5%</td>
                    <td>5%</td>
                </tr>
                </tfoot>
            </table>
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span >导出</span>
                        </button>
                        <button class="btn ml10">
                            <span>打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
        </div>

        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店营业汇总年趋势</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->

            <table class="table dep-border-table table-bordered table-striped">
                <thead>
                <tr>
                    <th>月份</th>
                    <th>营业收入</th>
                    <th>营业扣减</th>
                    <th>营业实收</th>
                </tr>
                </thead>
                  <tbody>
                    <tr>
                        <td>1月</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                    </tr>
                    <tr>
                        <td>2月</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                    </tr>
                  </tbody>
                <tfoot>
                <tr class="huizong-tr dep-bottom-botrder">
                    <td>汇总</td>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>

                </tr>
                </tfoot>
            </table>
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span >导出</span>
                        </button>
                        <button class="btn ml10">
                            <span>打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
        </div>

        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">连锁营业汇总年趋势</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->

            <table class="table dep-border-table table-bordered table-striped">
                <thead>
                <tr>
                    <th>月份</th>
                    <th>营业收入</th>
                    <th>营业扣减</th>
                    <th>营业实收</th>
                </tr>
                </thead>
                  <tbody>
                    <tr>
                        <td>1月</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                    </tr>
                    <tr>
                        <td>2月</td>
                        <td>239</td>
                        <td>23482</td>
                        <td>2342</td>
                    </tr>
                  </tbody>
                <tfoot>
                <tr class="huizong-tr dep-bottom-botrder">
                    <td>汇总</td>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>
                </tr>
                </tfoot>
            </table>
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span >导出</span>
                        </button>
                        <button class="btn ml10">
                            <span>打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
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
        initDay();
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
                text: '门店营业汇总(日)'
            },
            subtitle: {
                text: sdate
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