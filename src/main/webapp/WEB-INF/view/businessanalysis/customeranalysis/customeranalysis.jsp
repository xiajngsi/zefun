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
            <span class="dingdanzhuantai">客情分析</span>
            <span class="video" style="float: right; font-weight: 400;color: #333;">视频帮助 <span class="iconfont icon-video" style="margin-top: 1px;"></span></span>
        </h4>
        <div class="report-title">
            <ul>
                <li>
                    <h1>3326</h1>
                    <p>本<span name="searchType">日</span>会员到店人数</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>本<span name="searchType">日</span>散客到店数</p>
                </li>
                <li>
                    <h1>3326</h1>
                    <p>本<span name="searchType">日</span>总客数</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>上<span name="searchType">日</span>总服务客次</p>
                </li>
                <li>
                    <h1 class="red">18.85%</h1>
                    <p>营业实收<span name="searchType">日</span>客数增长</p>
                </li>
            </ul>
        </div>

        <div id="custom-toolbar" >
            <div class="table-toolbar">
                <span class="mr10">客情分析</span>
                <input type="search" placeholder="往期劳动业绩分析/选择月期" id="history-time" name="date"/>
                <button class="button-search btn" style="margin-left: -10px;">查询</button>
                <div class="select-target-report">
                    <span class="curent-report ml30"><a href="report-client-analyze.html">日</a></span>
                    <span class="report-category ml10"><a href="report-client-analyze-week.html">周</a></span>
                    <span class="report-category ml10"><a href="report-client-analyze-month.html">月</a></span>
                    <span class="report-category ml10"><a href="report-client-analyze-month-trend.html">月走势</a></span>
                    <span class="report-category ml10"><a href="report-client-analyze-year-trend.html">年走势</a></span>
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent"style="background-color: #fff;">
            <div class="row-fluid">
                <div id="cash-day" class="span12" style="height:400px"></div><!--cash-day-->
            </div>
        </div>

        <!--客情分析-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">客情分析</span>
                    <span class="fr">汇总时段：2015年2月1-7月 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table card-income-table">
                <thead>
                <tr>
                    <th>部门</th>
                    <th>总服务客次</th>
                    <th>会员客次/占比</th>
                    <th>散客客此/占比</th>
                    <th>会员业绩贡献/占比</th>
                    <th>散客业绩贡献/占比</th>
                    <th>男客次/占比</th>
                    <th>女客次/占比</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
                </tr>
                <tr>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
                </tr>
                <tr>
                    <td>美发部</td>
                    <td>100</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
                </tr>
                <tr>
                    <td>美甲部</td>
                    <td>100</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
                </tr>
                <tr class="huizong-tr">
                    <td>门店汇总</td>
                    <td>450</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
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
        <!--会员消费力分析-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">会员消费力分析</span>
                    <span class="fr">汇总时段：2015年2月1-7月 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table card-income-table">
                <thead>
                <tr>
                    <th>顾客类型</th>
                    <th>会员总数</th>
                    <th>来店人数</th>
                    <th>来店人数占该类会员比例</th>
                    <th>会员人均消费
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>来店会员消费业绩</th>
                    <th>业绩贡献占比</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>金卡会员500</td>
                    <td>100</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>1500</td>
                    <td>350</td>
                    <td>25%</td>
                </tr>
                <tr>
                    <td>金卡会员500</td>
                    <td>100</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>1500</td>
                    <td>350</td>
                    <td>25%</td>
                </tr>
                <tr>
                    <td>金卡会员500</td>
                    <td>100</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>1500</td>
                    <td>350</td>
                    <td>25%</td>
                </tr>
                <tr class="huizong-tr">
                    <td>汇总</td>
                    <td>100</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>1500</td>
                    <td>350</td>
                    <td>25%</td>
                </tr>
                </tbody>
                <tfoot>
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
                </tfoot>
            </table>
        </div>
        <!--连锁客情分析-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">客情分析</span>
                    <span class="fr">汇总时段：2015年2月1-7月 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table card-income-table">
                <thead>
                <tr>
                    <th>门店</th>
                    <th>总服务客次</th>
                    <th>会员客次/占比</th>
                    <th>散客客此/占比</th>
                    <th>会员业绩贡献/占比</th>
                    <th>散客业绩贡献/占比</th>
                    <th>男客次/占比</th>
                    <th>女客次/占比</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>南山店</td>
                    <td>100</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
                </tr>
                <tr>
                    <td>南山店</td>
                    <td>100</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
                </tr>
                <tr>
                    <td>南山店</td>
                    <td>100</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
                </tr>
                <tr>
                    <td>南山店</td>
                    <td>100</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
                </tr>
                <tr class="huizong-tr">
                    <td>汇总</td>
                    <td>450</td>
                    <td>60  <span class="ml30">60%</span></td>
                    <td>40  <span class="ml30">40%</span></td>
                    <td>13000  <span class="ml30">40%</span></td>
                    <td>3000  <span class="ml30">40%</span></td>
                    <td>23  <span class="ml30">23%</span></td>
                    <td>77  <span class="ml30">77%</span></td>
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
        <!--连锁会员消费力分析-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">会员消费力分析</span>
                    <span class="fr">汇总时段：2015年2月1-7月 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table card-income-table">
                <thead>
                <tr>
                    <th>顾客类型</th>
                    <th>会员总数</th>
                    <th>来店人数</th>
                    <th>来店人数占该类会员比例</th>
                    <th>会员人均消费
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>来店会员消费业绩</th>
                    <th>业绩贡献占比</th>
                    <th>本期会员消费业绩排行</th>
                    <th>上期会员消费业绩排行</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>金卡会员500</td>
                    <td>100</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>1500</td>
                    <td>350</td>
                    <td>25%</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>金卡会员500</td>
                    <td>100</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>1500</td>
                    <td>350</td>
                    <td>25%</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>金卡会员500</td>
                    <td>100</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>1500</td>
                    <td>350</td>
                    <td>25%</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr class="huizong-tr">
                    <td>汇总</td>
                    <td>100</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>1500</td>
                    <td>350</td>
                    <td>25%</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="10">
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



    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
   <!-- <div class="left-show-btn">
        <span class="iconfont icon-quanbu110"></span>
    </div>-->
    <a href="" class="showmenu"></a>


<script type="text/javascript">
    jQuery(function () {
        jQuery('#history-time').datetimepicker({
            lange: "ch",
            timepicker:false,
            format:'Y.m.d'
        });

        jQuery(".table-up-down").on("click", function(){
            jQuery(".tbody-up-down").toggle();
        });

        jQuery('#cash-day').highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: '左右造型连锁东海店客情分析 2015年2月4日'
            },
            xAxis: {
                categories: ['早于11:00', '12：00', '13：00', '14：00', '15：00', '16：00', '17：00', '18：00', '19：00', '20：00', '21：00', '晚于22：00']

            },
            yAxis: {
                title: {
                    text: '来店顾客人数'
                }
            },
            tooltip: {
                enabled: true,
                formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y;
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            series: [{
                data: [999, 500, 800, 1100, 1300, 1500, 1800, 3900, 3500, 4600, 999, 500]
            },
                {
                    data: [899, 400, 600, 100, 100, 100, 1200, 100, 1000, 1200, 699, 500]
                }]
        });/*line*/

    });



</script>


</div><!--mainwrapper-->

</body>
</html>