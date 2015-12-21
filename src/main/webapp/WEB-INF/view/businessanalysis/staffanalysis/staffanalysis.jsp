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

        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar zhibanbiao">
                    <span class="font-size-16 btn-color mr10">员工业绩分析</span>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">2014年2月 <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">2014年1月</a></li>
                            <li><a href="#">2014年2月</a></li>
                            <li><a href="#">2014年3月</a></li>
                            <li><a href="#">2014年4月</a></li>
                            <li><a href="#">2014年5月</a></li>
                            <li><a href="#">2014年6月</a></li>
                            <li><a href="#">2014年7月</a></li>
                            <li><a href="#">2014年8月</a></li>
                            <li><a href="#">2014年9月</a></li>
                            <li><a href="#">2014年10月</a></li>
                            <li><a href="#">2014年11月</a></li>
                            <li><a href="#">2014年12月</a></li>
                        </ul>
                    </div>

                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">美发部 <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">美发部</a></li>
                            <li><a href="#">美容部</a></li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">岗位 <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">洗发</a></li>
                            <li><a href="#">剪发</a></li>
                            <li><a href="#">烫发</a></li>
                            <li><a href="#">染发</a></li>
                        </ul>
                    </div>

                    <span class="mr10">左右连锁东海店 <span class="time">2014年3月</span></span>
                    <span class="video fr">视频帮助 <span class="iconfont icon-shipin" style="margin-top: 1px;"></span></span>
                </div>
                <div class="clearfix"></div>
            </div>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th rowspan="2">姓名</th>
                    <th colspan="3" class="text-center">劳动业绩</th>
                    <th colspan="2" class="text-center">商品业绩</th>
                    <th colspan="2" class="text-center">套餐销售</th>
                    <th colspan="2" class="text-center">开卡业绩</th>
                    <th colspan="2" class="text-center">充卡业绩</th>
                    <th rowspan="2" class="text-center">服务客次</th>
                    <th rowspan="2" class="text-center">指定客次</th>
                    <th rowspan="2" class="text-center">指定率</th>
                </tr>
                <tr>
                    <th>客次</th>
                    <th>金额</th>
                    <th>客单价</th>
                    <th>件数</th>
                    <th>金额</th>
                    <th>个数</th>
                    <th>金额</th>
                    <th>张数</th>
                    <th>金额</th>
                    <th>张数</th>
                    <th>金额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>李四</td>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42342</td>
                    <td>42232</td>
                    <td>42232</td>
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td>1</td>
                    <td>1</td>
                    <td>20%</td>
                </tr>
                <tr>
                    <td>李四</td>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42342</td>
                    <td>42232</td>
                    <td>42232</td>
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td>1</td>
                    <td>1</td>
                    <td>20%</td>
                </tr>
                <tr>
                    <td>李四</td>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42342</td>
                    <td>42232</td>
                    <td>42232</td>
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td>1</td>
                    <td>1</td>
                    <td>20%</td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="huizong-tr">
                    <td>汇总</td>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42342</td>
                    <td>42232</td>
                    <td>42232</td>
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td>1</td>
                    <td>1</td>
                    <td>100%</td>
                </tr>
                <tr>
                    <td colspan="15">
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

        <!--连锁店-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar zhibanbiao">
                    <span class="font-size-16 btn-color mr10">员工业绩分析</span>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">2014年2月 <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">2014年1月</a></li>
                            <li><a href="#">2014年2月</a></li>
                            <li><a href="#">2014年3月</a></li>
                            <li><a href="#">2014年4月</a></li>
                            <li><a href="#">2014年5月</a></li>
                            <li><a href="#">2014年6月</a></li>
                            <li><a href="#">2014年7月</a></li>
                            <li><a href="#">2014年8月</a></li>
                            <li><a href="#">2014年9月</a></li>
                            <li><a href="#">2014年10月</a></li>
                            <li><a href="#">2014年11月</a></li>
                            <li><a href="#">2014年12月</a></li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">保安分店 <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">罗湖分店</a></li>
                            <li><a href="#">南山分店</a></li>
                            <li><a href="#">全部</a></li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">美发部 <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">美发部</a></li>
                            <li><a href="#">美容部</a></li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">岗位 <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">洗发</a></li>
                            <li><a href="#">剪发</a></li>
                            <li><a href="#">烫发</a></li>
                            <li><a href="#">染发</a></li>
                        </ul>
                    </div>
                    <span class="video fr">视频帮助 <span class="iconfont icon-shipin" style="margin-top: 1px;"></span></span>
                </div>
                <div class="clearfix"></div>
            </div>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th rowspan="2">姓名</th>
                    <th rowspan="2">分店</th>
                    <th colspan="3" class="text-center">劳动业绩</th>
                    <th colspan="2" class="text-center">商品业绩</th>
                    <th colspan="2" class="text-center">套餐销售</th>
                    <th colspan="2" class="text-center">开卡业绩</th>
                    <th colspan="2" class="text-center">充卡业绩</th>
                    <th rowspan="2" class="text-center">服务客次</th>
                    <th rowspan="2" class="text-center">指定客次</th>
                    <th rowspan="2" class="text-center">指定率</th>
                </tr>
                <tr>
                    <th>客次</th>
                    <th>宝安店</th>
                    <th>金额</th>
                    <th>客单价</th>
                    <th>件数</th>
                    <th>金额</th>
                    <th>个数</th>
                    <th>金额</th>
                    <th>张数</th>
                    <th>金额</th>
                    <th>张数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>李四</td>
                    <th>宝安店</th>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42342</td>
                    <td>42232</td>
                    <td>42232</td>
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td>1</td>
                    <td>1</td>
                    <td>20%</td>
                </tr>
                <tr>
                    <td>李四</td>
                    <th>宝安店</th>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42342</td>
                    <td>42232</td>
                    <td>42232</td>
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td>1</td>
                    <td>1</td>
                    <td>20%</td>
                </tr>
                <tr>
                    <td>李四</td>
                    <th>宝安店</th>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42342</td>
                    <td>42232</td>
                    <td>42232</td>
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td>1</td>
                    <td>1</td>
                    <td>20%</td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="huizong-tr">
                    <td>汇总</td>
                    <td></td>
                    <td>239824</td>
                    <td>23442</td>
                    <td>12332</td>
                    <td>42342</td>
                    <td>42232</td>
                    <td>42232</td>
                    <td>234242</td>
                    <td>239</td>
                    <td>23482</td>
                    <td>2342</td>
                    <td>2342342</td>
                    <td>1</td>
                    <td>1</td>
                    <td>100%</td>
                </tr>
                <tr>
                    <td colspan="16">
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



        <!--<div class="more-toolbar" >
            <div class="table-toolbar zhibanbiao">
                <span class="font-size-16 btn-color mr10"></span>
                <div class="btn-group">
                    <button data-toggle="dropdown" class="btn dropdown-toggle">2014年2月 <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="#">2014年1月</a></li>
                        <li><a href="#">2014年2月</a></li>
                        <li><a href="#">2014年3月</a></li>
                        <li><a href="#">2014年4月</a></li>
                        <li><a href="#">2014年5月</a></li>
                        <li><a href="#">2014年6月</a></li>
                        <li><a href="#">2014年7月</a></li>
                        <li><a href="#">2014年8月</a></li>
                        <li><a href="#">2014年9月</a></li>
                        <li><a href="#">2014年10月</a></li>
                        <li><a href="#">2014年11月</a></li>
                        <li><a href="#">2014年12月</a></li>
                    </ul>
                </div>
                <span class="mr10">左右连锁东海店 <span class="time">2014年3月</span></span>
                &lt;!&ndash;<span class="check-before check-radio"></span>
                <input type="radio" name="zhouyi"/>&ndash;&gt;
                <input type="checkbox" name="check2" style="opacity: 0;"> 全店
                <input type="checkbox" name="check2" style="opacity: 0;"> 设计师
                <input type="checkbox" name="check2" style="opacity: 0;"> 技师
                <input type="checkbox" name="check2" style="opacity: 0;"> 助理
                <span class="video fr">视频帮助 <span class="iconfont icon-shipin" style="margin-top: 1px;"></span></span>
            </div>
            <div class="clearfix"></div>
        </div>&lt;!&ndash;more-toolbar&ndash;&gt;

        <div class="widgetcontent">
            <table class="table card-income-table">
                <thead>
                <tr>
                    <th  class="big-th">工作</th>
                    <th  class="big-th">姓名</th>
                    <th  class="big-th">岗位级别</th>
                    <th class="border-right big-th">工龄（月）</th>
                    <th class="red">劳动<br>业绩</th>
                    <th>服务<br>客数</th>
                    <th>指定<br>客数</th>
                    <th>指定<br>比例</th>
                    <th class="border-right">能力<br>评估</th>
                    <th  class="red">商品<br>业绩</th>
                    <th>成交<br>单数</th>
                    <th class="border-right">成交<br>均价</th>
                    <th  class="red">开卡<br>业绩</th>
                    <th class="border-right">开卡<br>人数</th>
                    <th  class="red">充值<br>业绩</th>
                    <th class="border-right">充卡<br>人数</th>
                </tr>
                </thead>
                <tbody class="" >
                <tr>
                    <td>SJ001 </td>
                    <td>刘德华</td>
                    <td>三星设计师 </td>
                    <td class="border-right">36 </td>
                    <td>13600 </td>
                    <td>28 </td>
                    <td>19 </td>
                    <td>68% </td>
                    <td class="border-right"><span class="iconfa-ok" data-target="#skill-analyze" data-toggle="modal"></span> </td>
                    <td>3600 </td>
                    <td>8 </td>
                    <td class="border-right">450 </td>
                    <td>6300 </td>
                    <td class="border-right">10 </td>
                    <td>2300 </td>
                    <td class="border-right">2</td>
                </tr>
                <tr>
                    <td>SJ002 </td>
                    <td>郭富城</td>
                    <td>二星设计师 </td>
                    <td class="border-right">36 </td>
                    <td>13600 </td>
                    <td>28 </td>
                    <td>19 </td>
                    <td>68% </td>
                    <td class="border-right"><span class="iconfa-ok"></span> </td>
                    <td>3600 </td>
                    <td>8 </td>
                    <td class="border-right">450 </td>
                    <td>6300 </td>
                    <td class="border-right">10 </td>
                    <td>2300 </td>
                    <td class="border-right">2</td>
                </tr>
                <tr class="bg-gray">
                    <td></td>
                    <td></td>
                    <td>门店平均值 </td>
                    <td class="border-right">22 </td>
                    <td>8828 </td>
                    <td>21 </td>
                    <td>12 </td>
                    <td>64% </td>
                    <td class="border-right"></td>
                    <td>2100 </td>
                    <td>4 </td>
                    <td class="border-right">398 </td>
                    <td>4200 </td>
                    <td class="border-right">3 </td>
                    <td>7000 </td>
                    <td class="border-right">2.5</td>
                </tr>
                </tbody>
            </table>
        </div>-->
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
 


</body>
</html>