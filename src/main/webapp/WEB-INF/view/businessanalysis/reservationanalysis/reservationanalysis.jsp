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
                    <p>本<span name="searchType">日</span> 预约总人次</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>本<span name="searchType">日</span>赴约总人次</p>
                </li>
                <li>
                    <h1>3326</h1>
                    <p>本<span name="searchType">日</span> 预约取消率</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>上<span name="searchType">日</span>预约业绩</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>上<span name="searchType">日</span> 占门店总业绩比例</p>
                </li>
                <li>
                    <h1 class="red">18.85%</h1>
                    <p>营业实收<span name="searchType">日</span>增长率</p>
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

        <!--顾客预约分析-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">顾客预约分析</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>顾客类型</th>
                    <th style="position: relative">
                            总预约人次
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>赴约总人次
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约取消率
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约总业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约客单价</th>
                    <th>贡献占比</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>金卡会员</td>
                    <td>500</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>20%</td>
                </tr>
                <tr>
                    <td>金卡会员</td>
                    <td>500</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>20%</td>
                </tr>
                <tr>
                    <td>金卡会员</td>
                    <td>500</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>20%</td>
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
        <!--预约总体分析   -->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">预约总体分析  </span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>部门</th>
                    <th style="position: relative">
                        总预约人次
                    </th>
                    <th>赴约总人次
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约取消率
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约总业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约客单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>贡献占比</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>70%</td>
                </tr>
                <tr>
                    <td>美发部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>70%</td>
                </tr>
                <tr>
                    <td>美甲</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>70%</td>
                </tr>
                <tr class="huizong-tr">
                    <td>汇总</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>70%</td>
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
        <!--项目预约汇总   -->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">项目预约汇总  </span>
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
                    <th>总预约人次
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>赴约总人次
                    </th>
                    <th>预约取消率
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约总业绩
                    </th>
                    <th>预约客单价
                    </th>
                    <th>业绩贡献排行
                    </th>
                    <th>上期业绩排行</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>洗剪吹</td>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>洗剪吹</td>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>洗剪吹</td>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
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
        <!--连锁顾客预约分析-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">连锁顾客预约分析</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>顾客类型</th>
                    <th style="position: relative">
                            总预约人次
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>赴约总人次
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约取消率
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约总业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约客单价</th>
                    <th>贡献占比</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>金卡会员</td>
                    <td>500</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>20%</td>
                </tr>
                <tr>
                    <td>金卡会员</td>
                    <td>500</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>20%</td>
                </tr>
                <tr>
                    <td>金卡会员</td>
                    <td>500</td>
                    <td>80</td>
                    <td>15%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>20%</td>
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
        <!--连锁预约总体分析   -->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">连锁预约总体分析  </span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>部门</th>
                    <th style="position: relative">
                        总预约人次
                    </th>
                    <th>赴约总人次
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约取消率
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约总业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约客单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>贡献占比</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>70%</td>
                </tr>
                <tr>
                    <td>美发部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>70%</td>
                </tr>
                <tr>
                    <td>美甲</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>70%</td>
                </tr>
                <tr class="huizong-tr">
                    <td>汇总</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>70%</td>
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
        <!--连锁项目预约汇总   -->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">连锁项目预约汇总</span>
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
                    <th>总预约人次
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>赴约总人次
                    </th>
                    <th>预约取消率
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>预约总业绩
                    </th>
                    <th>预约客单价
                    </th>
                    <th>业绩贡献排行
                    </th>
                    <th>上期业绩排行</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>洗剪吹</td>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>洗剪吹</td>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>洗剪吹</td>
                    <td>美容部</td>
                    <td>100</td>
                    <td>60 </td>
                    <td>40%</td>
                    <td>13000 </td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
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


</div><!--mainwrapper-->
 <script type="text/javascript">
    jQuery(function () {
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


</body>
</html>