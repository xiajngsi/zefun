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
                    <p>本<span name="searchType">日</span>新开金额</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>上<span name="searchType">日</span>新开金额</p>
                </li>
                <li>
                    <h1 class="green">-9.2%</h1>
                    <p>本<span name="searchType">日</span>新开金额增长</p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>本<span name="searchType">日</span>续充金额 </p>
                </li>
                <li>
                    <h1>2785</h1>
                    <p>上<span name="searchType">日</span>续充金额 </p>
                </li>
                <li>
                    <h1 class="red">18.85%</h1>
                    <p>本<span name="searchType">日</span>续充金额增长</p>
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
        <!--储值卡分类统计-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">储值卡销售分类统计</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table">
               <thead>
               <tr>
                   <th rowspan="2">卡类别</th>
                   <th colspan="2">新增</th>
                   <th colspan="2">续充</th>
                   <th colspan="2">划卡</th>
                   <th colspan="4">合计</th>
               </tr>
               <tr>
                   <th>人数</th>
                   <th>金额</th>
                   <th>人数</th>
                   <th>金额</th>
                   <th>人数</th>
                   <th>金额</th>
                   <th>会员数量</th>
                   <th>会员数量占比</th>
                   <th>卡金余额</th>
                   <th>余额占比</th>
               </tr>
               </thead>
               <tbody>
               <tr>
                   <td>银卡会员</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>400</td>
                   <td>50%</td>
                   <td>400000</td>
                   <td>30%</td>
               </tr>
               <tr>
                   <td>金卡会员</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>400</td>
                   <td>50%</td>
                   <td>400000</td>
                   <td>30%</td>
               </tr>
               <tr>
                   <td>钻卡会员</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>400</td>
                   <td>50%</td>
                   <td>400000</td>
                   <td>30%</td>
               </tr>
               <tr class="huizong-tr">
                   <td>合计</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>400</td>
                   <td>50%</td>
                   <td>400000</td>
                   <td>30%</td>
               </tr>
               </tbody>
           </table>
        </div>
        <!--非储值卡会员分类统计-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">非储值卡会员分类统计</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table">
               <thead>
               <tr>
                   <th>卡类别</th>
                   <th>新增人数</th>
                   <th>新增金额</th>
                   <th>来店人数</th>
                   <th>来店消费金额</th>
                   <th>累积会员数量</th>
                   <th>累计开卡金额</th>
                   <th>累计消费金额</th>
               </tr>
               </thead>
               <tbody>
               <tr>
                   <td>VIP资格会员</td>
                   <td>10</td>
                   <td>2000</td>
                   <td>22</td>
                   <td>3000</td>
                   <td>200</td>
                   <td>40000</td>
                   <td>80000</td>
               </tr>
               <tr>
                   <td>注册会员</td>
                   <td>10</td>
                   <td>2000</td>
                   <td>22</td>
                   <td>3000</td>
                   <td>200</td>
                   <td>40000</td>
                   <td>80000</td>
               </tr>
               </tbody>
                <tfoot>
                <tr>
                    <td colspan="11">
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

        <div class="widgetcontent">
            <div id="cash-day-liansuo" style="min-width:700px;height:400px"></div><!--cash-day-->
        </div>
        <!--连锁储值卡分类统计-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">连锁储值卡销售统计</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table">
               <thead>
               <tr>
                   <th rowspan="2">门店</th>
                   <th colspan="2">新增</th>
                   <th colspan="2">续充</th>
                   <th colspan="2">划卡</th>
                   <th colspan="4">合计</th>
               </tr>
               <tr>
                   <th>人数</th>
                   <th>金额</th>
                   <th>人数</th>
                   <th>金额</th>
                   <th>人数</th>
                   <th>金额</th>
                   <th>会员数量</th>
                   <th>会员数量占比</th>
                   <th>卡金余额</th>
                   <th>余额占比</th>
               </tr>
               </thead>
               <tbody>
               <tr>
                   <td>南山店</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>400</td>
                   <td>50%</td>
                   <td>400000</td>
                   <td>30%</td>
               </tr>
               <tr>
                   <td>保安店</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>400</td>
                   <td>50%</td>
                   <td>400000</td>
                   <td>30%</td>
               </tr>
               <tr class="huizong-tr">
                   <td>合计</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>60</td>
                   <td>1000</td>
                   <td>400</td>
                   <td>50%</td>
                   <td>400000</td>
                   <td>30%</td>
               </tr>
               </tbody>
                <tfoot>
                <tr>
                    <td colspan="11">
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
        <!--连锁非储值卡会员分类统计-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">非储值卡会员分类统计</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered">
               <thead>
               <tr>
                   <th>门店</th>
                   <th>卡类别</th>
                   <th>新增人数</th>
                   <th>新增金额</th>
                   <th>来店人数</th>
                   <th>来店消费金额</th>
                   <th>累积会员数量</th>
                   <th>累计开卡金额</th>
                   <th>累计消费金额</th>
               </tr>
               </thead>
               <tbody>
               <tr>
                   <td rowspan="2">罗湖店</td>
                   <td>VIP资格会员</td>
                   <td>10</td>
                   <td>2000</td>
                   <td>22</td>
                   <td>3000</td>
                   <td>200</td>
                   <td>40000</td>
                   <td>80000</td>
               </tr>
               <tr>
                   <td>注册会员</td>
                   <td>10</td>
                   <td>2000</td>
                   <td>22</td>
                   <td>3000</td>
                   <td>200</td>
                   <td>40000</td>
                   <td>80000</td>
               </tr>
               <tr>
                   <td rowspan="2">南山店</td>
                   <td>VIP资格会员</td>
                   <td>10</td>
                   <td>2000</td>
                   <td>22</td>
                   <td>3000</td>
                   <td>200</td>
                   <td>40000</td>
                   <td>80000</td>
               </tr>
               <tr>
                   <td>注册会员</td>
                   <td>10</td>
                   <td>2000</td>
                   <td>22</td>
                   <td>3000</td>
                   <td>200</td>
                   <td>40000</td>
                   <td>80000</td>
               </tr>
               <tr class="huizong-tr">
                   <td rowspan="2">汇总</td>
                   <td>VIP资格会员</td>
                   <td>10</td>
                   <td>2000</td>
                   <td>22</td>
                   <td>3000</td>
                   <td>200</td>
                   <td>40000</td>
                   <td>80000</td>
               </tr>
               <tr class="huizong-tr">
                   <td>注册会员</td>
                   <td>10</td>
                   <td>2000</td>
                   <td>22</td>
                   <td>3000</td>
                   <td>200</td>
                   <td>40000</td>
                   <td>80000</td>
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

<script type="text/javascript">
    jQuery(function () {

        jQuery('#history-time').datetimepicker({
            lange: "ch",
            timepicker:false,
            format:'Y.m.d'
        });

        var colors = Highcharts.getOptions().colors,
        categories = ['银卡', '金卡', '钻卡'],
        name = '收入分布',
        data = [{
            y: 55.11,
            color: colors[0],
        }, {
            y: 21.63,
            color: colors[1],
        }, {
            y: 11.94,
            color: colors[2],
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

        var chart = jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '左右造型连锁东海店卡金消费汇总 2015年7月4日'
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
                            s = this.x +'销售金额:<b>'+ this.y +'元</b><br/>';
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

        /*连锁图表*/
        var colors = Highcharts.getOptions().colors,
        categories = ['银卡', '金卡', '钻卡'],
        nameLs = '收入分布',
        dataLs = [{
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
        }];

        function setChartLs(name, categories, data, color) {
            /*chart.xAxis[0].setCategories(categories, false);*/
            chart.series[0].remove(false);
            chart.addSeries({
                name: nameLs,
                data: dataLs,
                color: colorLs || 'white'
            }, false);
            chart.redraw();
        }

        var chart = jQuery('#cash-day-liansuo').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '左右造型连锁东海店卡金消费汇总 2015年7月4日'
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
                        s: {
                            click: function() {
                                var drilldown = this.drilldown;
                                if (drilldown) { // drill down
                                    setChartLs(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);s
                                } else { // restore
                                    setChartLs(name, categories, data);
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
                            s = this.x +'销售金额:<b>'+ this.y +'元</b><br/>';
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