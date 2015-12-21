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
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">员工工资表<small>左右连锁东海店</small></span>
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
                    <span class="video fr">视频帮助 <span class="iconfont icon-shipin" style="margin-top: 1px;"></span></span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th colspan="2">员工信息</th>
                    <th colspan="2" class="text-center">基本工资</th>
                    <th colspan="2" class="text-center">劳动业绩提成</th>
                    <th colspan="2" class="text-center">售/充卡提成</th>
                    <th colspan="2" class="text-center">套餐销售提成</th>
                    <th colspan="2" class="text-center">商品销售提成</th>
                    <th rowspan="2" class="text-center">推荐奖</th>
                    <th rowspan="2" class="text-center">考勤奖惩</th>
                    <th rowspan="2" class="text-center">服务奖惩</th>
                    <th rowspan="2" class="text-center">应发合计</th>
                    <th rowspan="2" class="text-center">预支扣除</th>
                    <th rowspan="2" class="text-center">实际发放</th>
                </tr>
                <tr>
                    <th>工号</th>
                    <th>姓名</th>
                    <th>工资</th>
                    <th>津贴</th>
                    <th>提成</th>
                    <th>考核</th>
                    <th>提成</th>
                    <th>考核</th>
                    <th>提成</th>
                    <th>考核</th>
                    <th>提成</th>
                    <th>考核</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1001</td>
                    <td>张三</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>150</td>
                    <td>20</td>
                    <td>50</td>
                    <td>3000</td>
                    <td>1500</td>
                    <td>1500</td>
                </tr>
                <tr>
                    <td>1001</td>
                    <td>张三</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>150</td>
                    <td>20</td>
                    <td>50</td>
                    <td>3000</td>
                    <td>1500</td>
                    <td>1500</td>
                </tr>
                <tr>
                    <td>1001</td>
                    <td>张三</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>150</td>
                    <td>20</td>
                    <td>50</td>
                    <td>3000</td>
                    <td>1500</td>
                    <td>1500</td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="huizong-tr">
                    <td colspan="2" class="text-center">汇总</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>150</td>
                    <td>20</td>
                    <td>50</td>
                    <td>3000</td>
                    <td>1500</td>
                    <td>1500</td>
                </tr>
                <tr>
                    <td colspan="18">
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
        <!--连锁-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">员工工资表 </span>
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
                            <li><a href="#">全部</a></li>
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
                    <input type="text" placeholder="姓名"/><button class="btn button-search">搜索</button>
                    <span class="video fr">视频帮助 <span class="iconfont icon-shipin" style="margin-top: 1px;"></span></span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th colspan="2">员工信息</th>
                    <th rowspan="2">分店</th>
                    <th colspan="2" class="text-center">基本工资</th>
                    <th colspan="2" class="text-center">劳动业绩提成</th>
                    <th colspan="2" class="text-center">售/充卡提成</th>
                    <th colspan="2" class="text-center">套餐销售提成</th>
                    <th colspan="2" class="text-center">商品销售提成</th>
                    <th rowspan="2" class="text-center">推荐奖</th>
                    <th rowspan="2" class="text-center">考勤奖惩</th>
                    <th rowspan="2" class="text-center">服务奖惩</th>
                    <th rowspan="2" class="text-center">应发合计</th>
                    <th rowspan="2" class="text-center">预支扣除</th>
                    <th rowspan="2" class="text-center">实际发放</th>
                </tr>
                <tr>
                    <th>工号</th>
                    <th>姓名</th>
                    <th>工资</th>
                    <th>津贴</th>
                    <th>提成</th>
                    <th>考核</th>
                    <th>提成</th>
                    <th>考核</th>
                    <th>提成</th>
                    <th>考核</th>
                    <th>提成</th>
                    <th>考核</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1001</td>
                    <td>张三</td>
                    <td>宝安店</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>150</td>
                    <td>20</td>
                    <td>50</td>
                    <td>3000</td>
                    <td>1500</td>
                    <td>1500</td>
                </tr>
                <tr>
                    <td>1001</td>
                    <td>张三</td>
                    <td>宝安店</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>150</td>
                    <td>20</td>
                    <td>50</td>
                    <td>3000</td>
                    <td>1500</td>
                    <td>1500</td>
                </tr>
                <tr>
                    <td>1001</td>
                    <td>张三</td>
                    <td>宝安店</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>150</td>
                    <td>20</td>
                    <td>50</td>
                    <td>3000</td>
                    <td>1500</td>
                    <td>1500</td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="huizong-tr">
                    <td colspan="2" class="text-center">汇总</td>
                    <td></td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>1000</td>
                    <td>500</td>
                    <td>150</td>
                    <td>20</td>
                    <td>50</td>
                    <td>3000</td>
                    <td>1500</td>
                    <td>1500</td>
                </tr>
                <tr>
                    <td colspan="18">
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
 

</body>
</html>