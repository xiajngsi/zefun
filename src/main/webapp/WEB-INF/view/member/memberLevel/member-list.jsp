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
      	<!-- 页面代码 -->
		<div class="maincontent">
		    <div class="contentinner">
		    	<div class="widgetcontent">
			    <div class="more-toolbar" >
		            <div class="table-toolbar">
		                <span class="font-size-16 btn-color mr10">会员数据</span>
		            </div>
		            <div class="clearfix"></div>
		        </div><!--more-toolbar-->
		        <div class="more-toolbar" >
		            <div class="table-toolbar">
		                <button class="btn" data-toggle="modal" data-target="#toLeadModal">导入</button>
		                <input type="search" placeholder="手机号码/姓名" id="serchMemberByNameOrPhone"/>
		                <button class="button-search btn width100 ml-10" id="serchMemberByNameOrPhoneDoc">查询</button>
		                <div class="show-more" id="show_more">
		                    <img src="<%=basePath %>/img/common/d-triangle.png" alt="" class="ml10 mr10 more-img"/><span>更多筛选信息</span>
		                </div>
		            </div>
		            <div class="clearfix"></div>
		        </div>
	            <table class="table more-condition-table hide">
	                <tbody>
	                    <tr>
	                        <td class="width8">储值余额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="chuzhi1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="chuzhi2"/>
	                        </td>
	                        <td class="width8">积分余额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="jifenye1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="jifenye2"/>
	                        </td>
	                    </tr>
	
	                    <tr>
	                        <td class="width8">生　　日</td>
	                        <td class="">
	                            <input type="text" class="input80 timePickerClean" id="birthday-start"/>
	                            <span>-</span>
	                            <input type="text" class="input80 timePickerClean" id="birthday-end"/>
	                        </td>
	                        <td class="width8">注册日期</td>
	                        <td class="">
	                            <input type="text" class="input80 timePickerClean" id="register-start"/>
	                            <span>-</span>
	                            <input type="text" class="input80 timePickerClean" id="register-end"/>
	                        </td>
	                    </tr>
	
	                    <tr>
	                        <td class="width8">消费均额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="xiaofje1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="xiaofje2"/>
	                        </td>
	                        <td class="width8">累计消费</td>
	                        <td class="">
	                            <input type="number" class="input80" id="leijixf1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="leijixf2"/>
	                        </td>
	                    </tr>
	                    
	                    <tr>
	                        <td class="width8">距上次消费</td>
	                        <td class="">
	                            <input type="number" class="input80" id="scxf1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="scxf2"/>
	                        </td>
	                    </tr>
	                    
	                    <tr>
	                        <td>
	
	                        </td>
	                        <td class="">
	                            <input type="hidden" class="input80" />
	                        </td>
	                        <td class="width8"></td>
	                        <td class="">
	                            <input style="float: right;margin-right: 20px;" type="button" class="btn" id="serch" value="搜索"/><span>&nbsp;&nbsp;&nbsp;</span>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	            <!--保存筛选信息-->
	            <table style="border: 1px solid #ddd;margin-top: 10px;margin-bottom: 0;width: 100%;display: none;"  id="tishi">
	                <tbody>
	                <tr>
	                    <td style="padding-left: 10px;"><label>您要保存筛选信息么?</label></td>
	                    <td></td>
	                    <td></td>
	                    <td style="padding-top: 5px;"><input type="button" class="btn" id="fangqi" style="float: right;margin-right: 20px;" value="放弃"/></td>
	                </tr>
	                <tr>
	                    <td style="padding-left: 10px;" id="member_serch_count">当前满足条件 0 人</td>
	                    <td></td>
	                    <td></td>
	                    <td>
	                        <input type="button" class="btn" id="baocun2" style="float: right;margin-right: 20px;" value="保存"/>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	            </div>
	            <!--保存筛选信息-->
		        <!-- </div> -->
		        
		        <div class="more-toolbar" >
			        <div class="table-toolbar">
					    <div class="table-pagination">
						    <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
	                        ${page.pageSize} <span class="iconfa-caret-down afont"></span>
	                        </button>
		                    <ul class="dropdown-menu">
		                        <li><a href="javascript:changePageSize(5)">5</a></li>
		                        <li><a href="javascript:changePageSize(10)">10</a></li>
		                        <li><a href="javascript:changePageSize(20)">20</a></li>
		                        <li><a href="javascript:changePageSize(50)">50</a></li>
		                        <li><a href="javascript:changePageSize(100)">100</a></li>
		                    </ul>
	                        <div class="left-page" id="previousPageButton" onclick="previous()"><i class="FontAwesome iconfa-caret-left afont"></i></div>
	                        <div class="right-page" id="nextPageButton" onclick="next()"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                    	</div>
					</div>
					<div class="clearfix"></div>
				</div>
	            
	            <div class="more-toolbar">
	                <div class="table-toolbar">
	                    <span class="font-size-16 btn-color" id="searchDate">会员信息统计</span>
	
	                    <div class="table-pagination">
	                        <table class="ls-detail fr">
	                            <tbody><tr>
	                                <td>会员总人数：</td>
	                                <td id="receivableAmount">${page.totalRecord }</td>
	                                <td>累计储值金额：</td>
	                                <td id="giftAmount">${storeMember.totalAmount }</td>
	                                <td>当前储值余额：</td>
	                                <td id="couponAmount">${storeMember.balanceAmount }</td>
	                            </tr>
	                        </tbody></table>
	                    </div><!--table-pagination-->
	                </div><!--table-toolbar-->
	                <div class="clearfix"></div>
	             </div>
	            
	                
		        <table class="table table-bordered table-striped member-list-table">
		            <thead>
		            <tr>
		                <th>手机号</th>
		                <th>姓名</th>
		                <th class="drop-th">
		                    <span class="dropdown-toggle" data-toggle="dropdown">性别 <span class="caret"></span></span>
		                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
		                    	<li><a tabindex="-1" onclick="serchMemberBySex('全部',this)" href="javascript:void(0)">全部</a></li>
		                        <li><a tabindex="-1" onclick="serchMemberBySex('男',this)" href="javascript:void(0)">男</a></li>
		                        <li><a tabindex="-1" onclick="serchMemberBySex('女',this)" href="javascript:void(0)">女</a></li>
		                    </ul>
		                </th>
		                <th class="drop-th">
		                    <span class="dropdown-toggle" data-toggle="dropdown">会员等级 <span class="caret"></span></span>
		                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
		                        <li><a tabindex="-1" onclick="serchMemberByLevel('0','全部',this)" href="javascript:void(0)">全部</a></li>
		                        <c:forEach items="${levels }" var="level">
		                        	<li><a tabindex="-1" onclick="serchMemberByLevel('${level.levelId}','${level.levelName }',this)" href="javascript:void(0)">${level.levelName }</a></li>
		                        </c:forEach>
		                    </ul>
		                </th>
		                <th>生日</th>
		                <th>储值余额</th>
		                <th>积分余额</th>
		                <th>累计消费</th>
		                <th>消费均额</th>
		                <th>注册日期</th>
		                <th>修改资料</th>
		            </tr>
		            </thead>
		            <tbody id="init_member">
		            <c:forEach items="${page.results }" var="member">
		            <tr>
		                <td>${member.phone }</td>
		                <td class="can-click" data-target="#member-data" data-toggle="modal" onclick="selectMemberInfo(${member.memberId })" id="${member.memberId }">${member.name }</td>
		                <td>${member.sex }</td>
		                <td>${member.levelName }</td>
		                <td>${member.birthday }</td>
		                <td class="red">${member.balanceAmount }</td>
		                <td class="blue">${member.balanceIntegral }</td>
		                <td>${member.totalConsumeAmount }</td>
		                <td>${member.avgConsumeAmount }</td>
		                <td>${member.createTime }</td>
		                <td class="can-click m-btn update"  style="text-decoration: none">
		                	<span class="iconfont icon-icon07 "></span>
	                		<span class="cursor memberlevel-default-setting hide">修改</span>
                		</td>
		            </tr>
		            </c:forEach>
		            </tbody>
		        </table>
	       </div><!-- contentinner -->
       </div><!-- maincontent -->
    </div><!-- rightpanel -->

    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<!--会员筛选器模态框-->
<div class="modal hide" id="add-member-group" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-member-card">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增会员分组</h4>
            </div>
            <div class="modal-body">
                <form action="" class="editprofileform" method="post">
                    <p>
                        <label>筛选器名称:</label>
                        <input type="text" name="cart-name" id="group_name" class="input-xlarge" value="">
                    </p>
                </form>
            </div>
            <div class="modal-footer">
                <a class="btn modal-cancel" href="#">取消</a>
                <a id="baocun" class="btn btn-primary modal-confirm" href="#">确定</a>
            </div>
        </div>
    </div>
</div>
<div class="modal hide in" id="toLeadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account" style="width: 450px;height: 180px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="location.reload(true);"><span aria-hidden="true">×</span></button>
                <h5 class="modal-title" id="myModalLabel">上传文件</h5>
            </div>
            <div class="modal-body" style="height: 150px;">
                <form action="http://localhost:8080/zefun/objective/action/importExcle" class="editprofileform" method="post" enctype="multipart/form-data" id="excleForm">
                    <p>
                        <div>
                        <label>之前的服务商:</label>
                        <select data-placeholder="服务商名称" class="chzn-select" name="storeName" onchange="changeLastHelp(this)">
			            		<option value="左右">左右服务商</option>
			            		<option value="盛传">盛传服务商</option>
			            		<option value="云浩企汇通">云浩企汇通</option>
			            		<option value="博卡">博卡</option>
			            </select>
                        </div>
                        <br>
			            <div>
                        <label>选择会员数据:</label>
                        <input type="file" name="filename" id="file1" class="input-large" value="sdf"><br>
                        </div>
                        <div id="boka" style="display: none">
                        <label>会员资料表:</label>
                        <input type="file" name="filename" id="file2" class="input-large"><br>
                        </div>
                </form>
                <div class="hide" id="errorMessage">
                </div>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn btn-primary modal-confirm" href="#" id="confirm"  onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>

<!-- 修改会员资料 -->
<div class="modal hide" id="member-xiugai" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-xiugai">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">修改资料</h4>
            </div>
            <div class="modal-body">
                <div style="border-bottom:1px solid #fff">

                    <!-- 基本信息开始 -->
                    <div id="tabs-1">
                        <div class="xg-title">
                            <ul>
                                <li><span>姓名：</span><input type="text" value="" onblur="changeName(this)"></li>
                                <li><span>性别：</span><label class="radio">男<input type="radio" checked="checked" onclick="changeSex('男')"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label class="radio">女<input type="radio" onclick="changeSex('女')"></label></li>
                                <li><span>生日：</span><input type="text" value="" class="timePickerClean" onblur="changeBirthday(this)"></li>
                                <li><span>手机号：</span><input type="text" value="" onblur="changePhone(this)"></li>
                            </ul>
                        </div>
                        <table class="table table-bordered table-alter member-base-info-table">
                            <tbody>
                            <tr>
                                <td class="width20 td-bkg-col">会员等级</td>
                                <td class="width30">爱心卡</td>
                                <td class="td-bkg-col">储值余额</td>
                                <td>18900</td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">积分余额</td>
                                <td class="red">899</td>
                                <td class="td-bkg-col">累计消费</td>
                                <td>899</td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">消费均额</td>
                                <td>32103</td>
                                <td class="td-bkg-col">创建日期</td>
                                <td>32103</td>
                            </tr>
                        </tbody>
                    </table>
                    <form id="updateMemberInfo">
                    	<input type="hidden" name="memberId" >
	                    <input type="hidden" name="name" >
	                    <input type="hidden" name="sex" >
	                    <input type="hidden" name="birthday" >
	                    <input type="hidden" name="phone" >
                    </form>
                  </div>
                  <!-- 基本信息结束 -->
              </div>

            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" onclick="updateMember()">确定</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div>
<%@ include file="/template/memberData.jsp" %>
</body>

<script src="<%=basePath %>js/member/member-list.js" type="text/javascript"></script>
<script src="<%=basePath %>js/member/memberUpdate.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery("select").chosen();
//日期
jQuery('#register-start, #register-end').datetimepicker({
	lang:'ch',
	timepicker:false,
	format:'Y-m-d'
});
jQuery('#birthday-start, #birthday-end').datetimepicker({
	lang:'ch',
	timepicker:false,
	format:'m-d'
});
	
//获取加载页面时的页码信息
var pageNo = '${page.pageNo}';	
var pageSize = '${page.pageSize}';	
var totalPage = '${page.totalPage}';	

function UpladFile() {
	jQuery("#loadingWrap").fadeIn();
	var storeName = jQuery("select[name='storeName']").val();
    var fileObj = document.getElementById("file1").files[0];
    console.log(fileObj)
    var FileController = baseUrl +"member/action/importexcle";                    // 接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("file", fileObj);     // 文件对象
    if(storeName=="博卡"){
    	var fileObj2 = document.getElementById("file2").files[0];
    	form.append("file", fileObj2);
    }
    form.append("storeName", storeName);
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (e) {
    	jQuery("#loadingWrap").fadeOut();
    	/*dialog(xhr.responseText);*/
    	var json = eval("("+xhr.responseText+")");
    	jQuery("#errorMessage").hide();
    	jQuery("#importCombo").hide();
    	if(json.code!=0){
    		jQuery("#errorMessage").text(json.msg);
    		jQuery("#errorMessage").show();
    		return;
    	}else{
    		if(json.msg == '导入成功'){
    			dialog(json.msg);
    	        setTimeout(function(){
    	        	location.reload();
    	    	},300);
    		}else{
	    		jQuery("#importCombo").find("a").attr("href", picUrl + json.msg);
	    		jQuery("#importCombo").show();
	    		return;
    		}
    	}
    };
    xhr.send(form);
}
function changeLastHelp(obj){
	if (jQuery(obj).val() == "博卡"){
		jQuery("#excleForm").find("label").eq(1).text("消费余额表");
		jQuery("#boka").show();
	}else {
		jQuery("#excleForm").find("label").eq(1).text("选择会员数据");
		jQuery("#boka").hide();
	}
}
</script>

</html>