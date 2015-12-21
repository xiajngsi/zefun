<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String memberBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/";
String memberPicPath = "7xkv8r.com1.z0.glb.clouddn.com";
%>
<!--会员资料模态框-->
<div class="modal hide" id="member-data" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-data">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">会员资料</h4>
            </div>
            <div class="modal-body">
                <div id="tabs" style="border-bottom:1px solid #fff">
                    <ul>
                        <li><a href="#tabs-1" id = "tabsLI-1">基本信息</a></li>
                        <li><a href="#tabs-2">项目消费记录</a></li>
                        <li><a href="#tabs-3">套餐购买记录</a></li>
                        <li><a href="#tabs-4">商品购买记录</a></li>
                        <li><a href="#tabs-5" id = "tabsLI-5">资金流水记录</a></li>
                        <li><a href="#tabs-6">积分收支记录</a></li>
                        <!-- <li><a href="#tabs-7">挂账还款记录</a></li> -->
                    </ul>
                    <!-- 基本信息开始 -->
                    <div id="tabs-1">
                        <div id="custom-toolbar" class="clearfix">
                            <div class="table-toolbar">
                                <span name="memberBase"></span>
                            </div>
                        </div>
                        <table class="table table-bordered table-alter member-base-info-table">
                            <tr>
                                <td class="width20 td-bkg-col">手机号码</td>
                                <td class="width30" name="phone"></td>
                                <td class="width20 td-bkg-col">累计消费金额</td>
                                <td class="width30" name="totalConsumeAmount"></td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">会员等级</td>
                                <td name="levelName"></td>
                                <td class="td-bkg-col">累计充值金额</td>
                                <td name= "totalAmount"></td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">会员生日</td>
                                <td name= "birthday"></td>
                                <td class="td-bkg-col">累计获得积分</td>
                                <td name="totalIntegral"></td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">储值余额</td>
                                <td class="red" name="balanceAmount"></td>
                                <td class="td-bkg-col">项目消费均价</td>
                                <td name="avgConsumeAmount"></td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">积分余额</td>
                                <td class="red" name="balanceIntegral"></td>
                                <td class="td-bkg-col">项目消费频率</td>
                                <td name="avgConsumeDays"></td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">开卡门店</td>
                                <td name="storeName"></td>
                                <td class="td-bkg-col">最近来店时间</td>
                                <td name="lastConsumeTime"></td>
                            </tr>
                            <!-- <tr>
                                <td class="td-bkg-col">支付密码</td>
                                <td >******<span class="fr gray cursor">重置密码</span></td>
                                <td class="td-bkg-col">开卡门店</td>
                                <td name="storeName"></td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">安全问题</td>
                                <td name="problem" ></td>
                                <td name="answer" class="hide">您的小狗叫什么<span class="fr gray cursor">查看问题</span></td>
                                <td class="td-bkg-col">最近来店时间</td>
                                <td name="lastConsumeTime"></td>
                            </tr> -->
                        </table>
                    </div>
                    <!-- 基本信息结束 -->

                    <!-- 项目消费信息开始 -->
                    <div id="tabs-2">
                        <div id="custom-toolbar" class="clearfix">
                            <div class="table-toolbar">
                                <span name="memberBase"></span>
                            </div>
                            <div class="table-pagination">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                                    5 <span class="iconfa-caret-down afont"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">10</a></li>
                                    <li><a href="#">20</a></li>
                                    <li><a href="#">50</a></li>
                                </ul>
                                <div class="left-page" onclick="previousPageButton(1)" id = "previousOrderProject"><i class="FontAwesome iconfa-caret-left afont"></i></div>
                                <div class="right-page" onclick="nextPageButton(1)" id = "nextOrderProject"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                            </div>
                        </div>

                        <table class="table table-bordered table-striped expense-calendar-table">
                            <thead>
                              <tr>
                                  <th>水单号</th>
                                  <th>项目名称</th>
                                  <th>项目价格</th>
                                  <th>优惠金额</th>
                                  <th>实收金额</th>
                                  <th>消费时间</th>
                                  <th>消费门店</th>
                              </tr>
                            </thead>
                            <tbody id = "orderProjectTBODY">
                              
                            </tbody>
                        </table>
                    </div>
                    <!-- 项目消费信息结束 -->

                    <!-- 套餐购买信息开始 -->
                    <div id="tabs-3">
                        <div id="custom-toolbar" class="clearfix">
                            <div class="table-toolbar">
                                <span name="memberBase"></span>
                            </div>
                            <div class="table-pagination">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                                    5 <span class="iconfa-caret-down afont"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">10</a></li>
                                    <li><a href="#">20</a></li>
                                    <li><a href="#">50</a></li>
                                </ul>
                                <div class="left-page" onclick="previousPageButton(2)" id = "previousOrderCombo"><i class="FontAwesome iconfa-caret-left afont"></i></div>
                                <div class="right-page" onclick="nextPageButton(2)" id = "nextOrderCombo"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                            </div>
                        </div>

                        <table class="table table-bordered table-striped expense-calendar-table">
                            <thead>
                            <tr>
                                <th>套餐名称</th>
                                <th>项目次数</th>
                                <th>剩余次数</th>
                                <th>项目总价</th>
                                <th>套餐价格</th>
                                <th>推荐员工</th>
                                <th>消费时间</th>
                                <th>过期时间</th>
                                <th>消费门店</th>
                            </tr>
                            </thead>
                            <tbody id = "orderComboTBODY">
                              
                            </tbody>
                        </table>
                    </div>
                    <!-- 套餐购买信息结束 -->

                    <!-- 商品购买信息开始 -->
                    <div id="tabs-4">
                        <div id="custom-toolbar" class="clearfix">
                            <div class="table-toolbar">
                                <span name="memberBase"></span>
                            </div>
                            <div class="table-pagination">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                                    5 <span class="iconfa-caret-down afont"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">10</a></li>
                                    <li><a href="#">20</a></li>
                                    <li><a href="#">50</a></li>
                                </ul>
                                <div class="left-page" onclick="previousPageButton(3)" id = "previousOrderGoods"><i class="FontAwesome iconfa-caret-left afont"></i></div>
                                <div class="right-page" onclick="nextPageButton(3)" id = "nextOrderGoods"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                            </div>
                        </div>

                        <table class="table table-bordered table-striped expense-calendar-table">
                            <thead>
                            <tr>
                                <th>水单号</th>
                                <th>商品名称</th>
                                <th>销售员工</th>
                                <th>商品价格</th>
                                <th>优惠金额</th>
                                <th>实收金额</th>
                                <th>消费时间</th>
                                <th>消费门店</th>
                            </tr>
                            </thead>
                            <tbody id = "orderGoodsTBODY">
                              
                            </tbody>
                        </table>
                    </div>
                    <!-- 商品购买信息结束 -->

                    <!-- 资金流水信息开始 -->
                    <div id="tabs-5">
                        <div id="custom-toolbar" class="clearfix">
                            <div class="table-toolbar">
                                <span name="memberBase"></span>
                            </div>
                            <div class="table-pagination">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                                    5 <span class="iconfa-caret-down afont"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">10</a></li>
                                    <li><a href="#">20</a></li>
                                    <li><a href="#">50</a></li>
                                </ul>
                                <div class="left-page" onclick="previousPageButton(4)" id = "previousMoneyFlow"><i class="FontAwesome iconfa-caret-left afont"></i></div>
                                <div class="right-page" onclick="nextPageButton(4)" id = "nextMoneyFlow"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                            </div>
                        </div>

                        <table class="table table-bordered table-striped recharge-transfer-record-table">
                            <thead>
                              <tr>
                                  <th>序号</th>
                                  <th>交易类型</th>
                                  <!-- <th>充值对象/订单号</th> -->
                                  <!-- <th>储值余额</th> -->
                                  <th>交易金额</th>
                                  <th>交易时间</th>
                                  <th>操作员工</th>
                                  <th>操作门店</th>
                              </tr>
                            </thead>
                            <tbody id="moneyFlowTBODY">
                            
                           </tbody>
                        </table>
                    </div>
                    <!-- 充值转账信息结束 -->

                    <!-- 积分信息开始 -->
                    <div id="tabs-6">
                        <div id="custom-toolbar" class="clearfix">
                            <div class="table-toolbar">
                                <span name="memberBase"></span>
                            </div>
                            <div class="table-pagination">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                                    5 <span class="iconfa-caret-down afont"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">10</a></li>
                                    <li><a href="#">20</a></li>
                                    <li><a href="#">50</a></li>
                                </ul>
                                <div class="left-page" onclick="previousPageButton(5)" id = "previousIntegralFlow"><i class="FontAwesome iconfa-caret-left afont"></i></div>
                                <div class="right-page" onclick="nextPageButton(5)" id = "nextIntegralFlow"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                            </div>
                        </div>

                        <table class="table table-bordered table-striped jifen-record-table">
                            <thead>
                              <tr>
                                  <th>收支类型</th>
                                  <th>收支积分</th>
                                  <th>收支说明</th>
                                  <th>收支时间</th>
                              </tr>
                            </thead>
                            <tbody id="integralFlowTBODY">
                              
                            </tbody>
                        </table>
                    </div>
                    <!-- 积分信息结束 -->

                    <!-- 挂帐信息开始 -->
                    <!-- <div id="tabs-7">
                        <div id="custom-toolbar" class="clearfix">
                            <div class="table-toolbar">
                                <span name="memberBase"></span>
                            </div>
                            <div class="table-pagination">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                                    5 <span class="iconfa-caret-down afont"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">10</a></li>
                                    <li><a href="#">20</a></li>
                                    <li><a href="#">50</a></li>
                                </ul>
                                <div class="left-page"><i class="FontAwesome iconfa-caret-left afont"></i></div>
                                <div class="right-page"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                            </div>
                        </div>

                        <table class="table table-bordered table-striped debt-table">
                            <thead>
                              <tr>
                                  <th>水单号</th>
                                  <th>欠款金额</th>
                                  <th>欠款日期</th>
                                  <th>欠款操作员工</th>
                                  <th>还款日期</th>
                                  <th>还款操作员工</th>
                                  <th>拖欠天数</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr class="single">
                                  <td>0004</td>
                                  <td class="red">100</td>
                                  <td>2015-07-24</td>
                                  <td>201 张老板</td>
                                  <td></td>
                                  <td></td>
                                  <td class="blue">15</td>
                              </tr>
                              <tr class="single">
                                  <td>0003</td>
                                  <td class="red">20</td>
                                  <td>2015-06-24</td>
                                  <td>201 张老板</td>
                                  <td></td>
                                  <td></td>
                                  <td class="red">45</td>
                              </tr>
                              <tr class="single">
                                  <td>0002</td>
                                  <td class="blue">500</td>
                                  <td>2015-04-30</td>
                                  <td>301 王经理</td>
                                  <td>2015-06-20</td>
                                  <td>101 刘德华</td>
                                  <td class="red">51</td>
                              </tr>
                            </tbody>
                        </table>
                    </div> -->
                    <!-- 挂帐信息结束 -->
                </div>

            </div><!--modal-body-->
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->

<script type="text/javascript" src="<%=memberBasePath %>js/template/memberData.js"></script>