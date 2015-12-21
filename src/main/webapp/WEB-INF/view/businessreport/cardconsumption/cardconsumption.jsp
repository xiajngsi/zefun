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
                    <p>本<span name="searchType">日</span>项目划卡</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>本<span name="searchType">日</span>套餐划卡</p>
                </li>
                <li>
                    <h1>3326</h1>
                    <p>本<span name="searchType">日</span>商品划卡</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>本<span name="searchType">日</span>年卡消费</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>本<span name="searchType">日</span>划卡总额</p>
                </li>
                <li>
                    <h1 class="red">18.5%</h1>
                    <p>划卡总额<span name="searchType">日</span>增长</p>
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
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="cash-day" style="min-width:700px;height:400px"></div><!--cash-day-->
        </div>

        <div class="widgetcontent">
            <div style="margin-top: 20px;">
                <!--<div class="fl" style="width: 49%">-->
                    <div class="more-toolbar" >
                        <div class="table-toolbar">
                            <span class="font-size-16 btn-color mr10">储值卡消费分布</span>
                            <span class="fr">时间：<span name="tableDate">2015年2月4日</span> 单位：元</span>
                        </div>
                        <div class="clearfix"></div>
                    </div><!--more-toolbar-->

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>部门</th>
                            <th>项目</th>
                            <th>套餐</th>
                            <th>商品</th>
                            <th>划卡金额汇总</th>
                            <th>金卡消费人数</th>
                            <th>银卡消费人数</th>
                            <th>钻卡消费人数</th>
                            <th>用卡人数汇总</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>美发部</td>
                            <td>239824</td>
                            <td>23442</td>
                            <td>12332</td>
                            <td>12332</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>60</td>
                        </tr>
                        <tr>
                            <td>美容部</td>
                            <td>239824</td>
                            <td>23442</td>
                            <td>42342</td>
                            <td>42342</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>60</td>
                        </tr>
                        <tr>
                            <td>美甲部</td>
                            <td>239824</td>
                            <td>23442</td>
                            <td>12332</td>
                            <td>42342</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>60</td>
                        </tr>
                        <tr class="zhanbi-tr">
                            <td>汇总</td>
                            <td>239824</td>
                            <td>23442</td>
                            <td>12332</td>
                            <td>42342</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>60</td>
                        </tr>
                        <tr class="huizong-tr">
                            <td>占比</td>
                            <td>20%</td>
                            <td>20%</td>
                            <td>20%</td>
                            <td>20%</td>
                            <td>20%</td>
                            <td>20%</td>
                            <td>20%</td>
                            <td>60%</td>
                        </tr>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="13">
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
                <!--</div>-->

                <!--<div class="fr" style="width: 49%">
                    <div class="more-toolbar" >
                        <div class="table-toolbar">
                            <span class="font-size-16 btn-color mr10">储值卡卡金统计</span>
                            <span class="fr">时间：<span name="tableDate">2015年2月4日</span> 单位：元</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>&lt;!&ndash;more-toolbar&ndash;&gt;

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>储值卡类别</th>
                            <th>新增会员</th>
                            <th>新增卡金</th>
                            <th>划卡人数</th>
                            <th>划卡金额</th>
                            <th>卡金余额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>白金卡</td>
                            <td>12</td>
                            <td>239824</td>
                            <td>12</td>
                            <td>12332</td>
                            <td>42342</td>
                        </tr>
                        <tr>
                            <td>铂金卡</td>
                            <td>12</td>
                            <td>239824</td>
                            <td>12</td>
                            <td>12332</td>
                            <td>42342</td>
                        </tr>
                        <tr>
                            <td>钻石卡</td>
                            <td>12</td>
                            <td>239824</td>
                            <td>12</td>
                            <td>12332</td>
                            <td>42342</td>
                        </tr>
                        <tr class="zhanbi-tr">
                            <td>汇总</td>
                            <td>36</td>
                            <td>239824</td>
                            <td>36</td>
                            <td>23442</td>
                            <td>12332</td>
                        </tr>
                        <tr class="huizong-tr">
                            <td>占比</td>
                            <td>20%</td>
                            <td>40%</td>
                            <td>40%</td>
                            <td>40%</td>
                            <td>40%</td>
                        </tr>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="13">
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
                </div>-->
            </div>
        </div>

        <div class="widgetcontent">
            <div>
                <div class="fl" style="width: 49%">
                    <div id="project-type-pie" style="min-width:450px;height:305px"></div>
                </div>
                <div class="fr" style="width: 49%">
                    <div id="card-type-pie" style="min-width:450px;height:305px"></div>
                </div>
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

        jQuery('#project-type-pie').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false
            },
            title: {
                text: '',
                align: 'center',
                verticalAlign: 'middle'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '所占比重',
                innerSize: '50%',
                data: [
                    ['项目', 10.38],
                    ['套餐', 56.33],
                    ['商品', 24.03],
                    {
                        y: 0.2,
                        dataLabels: {
                            enabled: false
                        }
                    }
                ]
            }]
        });

        jQuery('#card-type-pie').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false
            },
            title: {
                text: '',
                align: 'center',
                verticalAlign: 'middle'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '所占比重',
                innerSize: '50%',
                data: [
                    ['金卡消费人数', 10.38],
                    ['银卡消费人数', 56.33],
                    ['钻卡消费人俗', 24.03],
                    {
                        y: 0.2,
                        dataLabels: {
                            enabled: false
                        }
                    }
                ]
            }]
        });

        /*jQuery('#card-type-pie').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Historic World Population by Region'
            },
            subtitle: {
                text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
            },
            xAxis: {
                categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Population (millions)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' millions'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 80,
                floating: true,
                borderWidth: 1,
                backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '充值',
                data: [107, 31, 635, 203]
            }, {
                name: '消费',
                data: [133, 156, 947, 408]
            }]
        });*/

        jQuery('#cash-type-pie').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false
            },
            title: {
                text: '',
                align: 'center',
                verticalAlign: 'middle'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.0f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '所占比重',
                innerSize: '50%',
                data: [
                    ['现金', 40],
                    ['银联', 16],
                    ['网络团购', 34],
                    ['支付宝+微信', 10],
                    {
                        y: 0.2,
                        dataLabels: {
                            enabled: false
                        }
                    }
                ]
            }]
        });
    });

    function initDay(){
        jQuery("#startDate").val(new Date().dateFormat('Y/m/d'));
        jQuery("#endDate").val(new Date().dateFormat('Y/m/d'));
        jQuery("span[name='tableDate']").html(sdate);
        jQuery("span[name='searchType']").html('日');
        jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '划卡消费(日)'
            },
            subtitle: {
                text: sdate
            },
            xAxis: {
                categories: ['美发部', '美容部', '美甲部']
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Total fruit consumption'
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
            },
            legend: {
                align: 'right',
                x: -30,
                verticalAlign: 'top',
                y: 25,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.x + '</b><br/>' +
                            this.series.name + ': ' + this.y + '<br/>' +
                            'Total: ' + this.point.stackTotal;
                }
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                        style: {
                            textShadow: '0 0 3px black'
                        }
                    }
                }
            },
            series: [{
                name: '项目',
                data: [2342, 3324, 4411]
            }, {
                name: '套餐',
                data: [1342, 2324, 3411]
            }, {
                name: '商品',
                data: [5342, 7324, 3411]
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
                jQuery("span[name='tableDate']").html(sweek);
                jQuery("span[name='searchType']").html('周');

                jQuery('#cash-day').highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: '划卡消费(周)'
                    },
                    subtitle: {
                        text: sweek
                    },
                    xAxis: {
                        categories: ['美发部', '美容部', '美甲部']
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Total fruit consumption'
                        },
                        stackLabels: {
                            enabled: true,
                            style: {
                                fontWeight: 'bold',
                                color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                            }
                        }
                    },
                    legend: {
                        align: 'right',
                        x: -30,
                        verticalAlign: 'top',
                        y: 25,
                        floating: true,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                        borderColor: '#CCC',
                        borderWidth: 1,
                        shadow: false
                    },
                    tooltip: {
                        formatter: function () {
                            return '<b>' + this.x + '</b><br/>' +
                                    this.series.name + ': ' + this.y + '<br/>' +
                                    'Total: ' + this.point.stackTotal;
                        }
                    },
                    plotOptions: {
                        column: {
                            stacking: 'normal',
                            dataLabels: {
                                enabled: true,
                                color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                                style: {
                                    textShadow: '0 0 3px black'
                                }
                            }
                        }
                    },
                    series: [{
                        name: '项目',
                        data: [2342, 3324, 4411]
                    }, {
                        name: '套餐',
                        data: [1342, 2324, 3411]
                    }, {
                        name: '商品',
                        data: [5342, 7324, 3411]
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
                jQuery("span[name='tableDate']").html(smonth);
                jQuery("span[name='searchType']").html('月');

                jQuery('#cash-day').highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: '划卡消费(月)'
                    },
                    subtitle: {
                        text: smonth
                    },
                    xAxis: {
                        categories: ['美发部', '美容部', '美甲部']
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Total fruit consumption'
                        },
                        stackLabels: {
                            enabled: true,
                            style: {
                                fontWeight: 'bold',
                                color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                            }
                        }
                    },
                    legend: {
                        align: 'right',
                        x: -30,
                        verticalAlign: 'top',
                        y: 25,
                        floating: true,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                        borderColor: '#CCC',
                        borderWidth: 1,
                        shadow: false
                    },
                    tooltip: {
                        formatter: function () {
                            return '<b>' + this.x + '</b><br/>' +
                                    this.series.name + ': ' + this.y + '<br/>' +
                                    'Total: ' + this.point.stackTotal;
                        }
                    },
                    plotOptions: {
                        column: {
                            stacking: 'normal',
                            dataLabels: {
                                enabled: true,
                                color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                                style: {
                                    textShadow: '0 0 3px black'
                                }
                            }
                        }
                    },
                    series: [{
                        name: '项目',
                        data: [2342, 3324, 4411]
                    }, {
                        name: '套餐',
                        data: [1342, 2324, 3411]
                    }, {
                        name: '商品',
                        data: [5342, 7324, 3411]
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
                jQuery("span[name='tableDate']").html(smonth);
                jQuery("span[name='searchType']").html('月');

                jQuery('#cash-day').highcharts({
                    chart: {
                        type: 'spline'
                    },
                    title: {
                        text: '划卡消费(月走势)'
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
                        name: '项目',
                        data: [742, 621, 914, 134, 418, 121, 425, 226, 323, 618, 183, 929, 929, 929, 929,
                            742, 621, 914, 134, 418, 121, 425, 226, 323, 618, 183, 929, 929, 929, 929, 328]
                    }, {
                        name: '套餐',
                        data: [212, 211, 374, 94, 128, 182, 214, 412, 142, 123, 89, 284, 325, 216, 310,
                            127, 431, 209, 237, 128, 432, 213, 431, 512, 341, 431, 124, 231, 291, 287, 298]
                    }, {
                        name: '商品',
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
                jQuery("span[name='tableDate']").html(syear);
                jQuery("span[name='searchType']").html('年');

                jQuery('#cash-day').highcharts({
                    chart: {
                        type: 'spline'
                    },
                    title: {
                        text: '划卡消费(年走势)'
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
                        name: '项目',
                        data: [742, 621, 914, 134, 418, 121, 425, 226, 323, 618, 183, 929]
                    }, {
                        name: '商品',
                        data: [212, 211, 374, 94, 128, 182, 214, 412, 142, 123, 89, 284]
                    }, {
                        name: '套餐',
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
 


<script type="text/javascript" src="<%=basePath %>js/employee/shift.js"></script>





</body>
</html>