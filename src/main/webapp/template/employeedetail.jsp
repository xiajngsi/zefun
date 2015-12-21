<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal hide" id="employee-detail-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content employee-data-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">员工资料</h5>
                <input type="hidden" id="yuangongid">
            </div>
            <div class="modal-body">
                <div id="tabs">
                    <ul>
                        <li><a href="#tabs-1">基本资料</a></li>
                        <li><a href="#tabs-2">培训经历</a></li>
                        <li><a href="#tabs-3">工作经历</a></li>
                        <li><a href="#tabs-4">擅长项目</a></li>
                        <li><a href="#tabs-5">奖惩记录</a></li>
                        <li><a href="#tabs-6">推介关系图</a></li>
                        <li><a href="#tabs-7">派遣调动</a></li>
                    </ul>
                    <div id="tabs-1">
                        <div class="base-info-img">
                            <img name="touxiang" src="" alt=""/>
                            
                        </div>
                        <table class="table table-bordered base-info-table">
                            <tbody>
                                <tr>
                                    <td class="width15">工号</td>
                                    <td class="input-td gonghao"></td>
                                    <td class="width15">姓名</td>
                                    <td class="input-td xingming"></td>
                                </tr>
                                <tr><td class="">部门</td>
                                    <td class="input-td bumen">
                                    </td>
                                    <td class="">岗位</td>
                                    <td class="input-td gangwei">
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <td class="">职位</td>
                                    <td class="input-td zhiwei">
                                    </td>
                                    <td class="">性别</td>
                                    <td class="input-td xingbie">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="">身份证</td>
                                    <td class="input-td shenfen"></td>
                                        
                                    <td class="">出生日期</td>
                                    <td class="input-td chusheng"></td>
                                </tr>
                                <tr>
                                    <td class="">当前状态</td>
                                    <td class="input-td zhuangtai">
                                    </td>
                                    <td class="">手机号</td>
                                    <td class="input-td shouji"></td>
                                </tr>
                                <tr>
                                    <td class="">到职日期</td>
                                    <td class="input-td daozhi">
                                    </td>
                                    <td class="">住址</td>
                                    <td class="input-td zhuzhi"></td>
                                </tr>
                                <tr>
                                    <td class="">离职日期</td>
                                    <td class="input-td lizhi">
                                    </td>
                                    <td class="">健康证</td>
                                    <td class="input-td jiankang"></td>
                                </tr>
                                <tr>
                                    <td class="">介绍人</td>
                                    <td class="input-td jieshao">
                                    </td>
                                    <td class="">紧急联系人电话</td>
                                    <td class="input-td jinji"></td>
                                </tr>
                                <tr>
                                    <td class="">基本工资</td>
                                    <td class="input-td word-parent jiben"></td>
                                    <td class="">岗位津贴</td>
                                    <td class="input-td word-parent jintie"></td>
                                </tr>

                            </tbody>
                        </table>
                        <div class="clearfix"></div>
                        
                    </div><!--tabs-1-->

                    <div id="tabs-2">
                        <table class="table table-bordered self-table-striped expense-calendar-table peixun">
                            <thead>
                            <tr>
                                <th>培训时间 </th>
                                <th>培训机构</th>
                                <th>培训内容</th>
                                <th>培训成绩(证书)</th>
                            </tr>
                            </thead>
                            <tbody>
                            
                        </table>
                    </div><!--tabs-2-->

                    <div id="tabs-3">
                        <table class="table table-bordered table-striped expense-calendar-table gongzuo">
                            <thead>
                            <tr>
                                <th>工作时间 </th>
                                <th>工作单位</th>
                                <th>职位</th>
                                <th>工作内容</th>
                            </tr>
                            </thead>
                            <tbody>
                        </table>
                    </div><!--tabs-3-->

                    <div id="tabs-4">
                        <table class="table table-bordered table-striped expense-calendar-table shanchang">
                            <thead>
                            <tr>
                                <th>类别名称 </th>
                                <th>擅长项目</th>
                                <th>技能描述</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            
                        </table>
                    </div><!--tabs-4-->

                    <div id="tabs-5">
                        <div id="custom-toolbar" class="clearfix">
                            <select name="" id="jctype" class="chzn-select input80" data-placeholder="选择目标年" onchange="jiangcheng()">
			                    <option value="1">处罚</option>
			                    <option value="2">奖励</option>
			                </select>
                        </div>

                        <table class="table table-bordered self-table-striped jiangcheng">
                            <thead>
                            <tr>
                                <th>时间 </th>
                                <th>原因</th>
                            </tr>
                            </thead>
                            <tbody>
                            
                        </table>
                    </div><!--tabs-5-->

                    <div id="tabs-6">
                       <table class="table tuijie-table benren">
                            <tbody>
                            </tbody>
                       </table>
                       <table class="table tuijie-table tuijianren">
                            <tbody>
                            </tbody>
                       </table>
                       <table class="table tuijie-table beituijian">
                            <tbody>
                            </tbody>
                       </table>
                    </div><!--tabs-6-->

                    <div id="tabs-7">
                        <table class="table table-bordered table-striped jifen-record-table table777">
                            <thead>
                            <tr>
                                <th>调往门店 </th>
                                <th>派遣时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="single">
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div><!--tabs-7-->
                </div>
            </div><!--modal-body-->
        </div><!--modal-content-->
    </div><!--modal-dialog-->
    
</div><!--modal-->
<script type="text/javascript">
    function employeedetail(id){
    	jQuery('#employee-detail-modal').modal();
    	jQuery('#yuangongid').val(id);
    	
    	jQuery.ajax({
    		type : "post",
    		url : baseUrl + "employee/action/getdetail",
    		data : "employeeId="+id,
    		dataType : "json",
    		beforeSend : function(){
    			requestLoading.fadeIn(800);//发起请求前显示加载中...
    		},
    		complete : function(){
    			requestLoading.fadeOut(800);//请求完毕后将加载效果移除
    		},
    		success : function(e){
    			if(e.code != 0){
    				dialog("系统繁忙！");
    			}
    			var last=JSON.stringify(e.msg); 
                //console.log("======="+last);
                jQuery("img[name='touxiang']").attr("src",e.msg.headImage);
               jQuery(".gonghao").text(e.msg.employeeCode);
               jQuery(".xingming").text(e.msg.name);
               jQuery(".bumen").text(e.msg.deptName);
               jQuery(".gangwei").text(e.msg.positionName);
               jQuery(".zhiwei").text(e.msg.levelName);
               if(e.msg.sex==1){
            	   jQuery(".xingbie").text("男");
               }else{
            	   jQuery(".xingbie").text("女");
               }
               
               jQuery(".chusheng").text(e.msg.birthday);
               jQuery(".shenfen").text(e.msg.identityCard);
               if(e.msg.employeeStatus==1){
            	   jQuery(".zhuangtai").text("在职");
               }else{
            	   jQuery(".zhuangtai").text("离职");
               }
               jQuery(".shouji").text(e.msg.phone);
               jQuery(".daozhi").text(e.msg.entryDate);
               jQuery(".zhuzhi").text(e.msg.address);
               jQuery(".lizhi").text(e.msg.leaveDate);
               jQuery(".jiankang").text(e.msg.healthCard);
               jQuery(".jieshao").text(e.msg.recommend);
               jQuery(".jinji").text(e.msg.emergencyPhone);
               jQuery(".jiben").text(e.msg.baseSalaries+"元/月");
               jQuery(".jintie").text(e.msg.positionSalaries+"元/月");
              //擅长信息查询初始化
            	shanchang();
            	//工作经验信息查询初始化
            	gongzuo();
            	//培训经验信息查询初始化
            	peixun();
            	//奖惩信息查询初始化
            	jiangcheng();
            	//关系查询初始化
            	tuijianguanxi();
            	//派遣
            	paiqian();
    		}
    	});
    }
    function peixun(){
    	var id=jQuery('#yuangongid').val();
    	jQuery.ajax({
    		type : "post",
    		url : baseUrl + "employee/action/pxquery",
    		data : "employeeId="+id,
    		dataType : "json",
    		beforeSend : function(){
    			requestLoading.fadeIn(800);//发起请求前显示加载中...
    		},
    		complete : function(){
    			requestLoading.fadeOut(800);//请求完毕后将加载效果移除
    		},
    		success : function(e){
    			jQuery(".peixun tbody tr").remove();
    			if(e.msg.length>0){
    				for (var i = 0; i < e.msg.length; i++) {
    					 var str = jQuery("<tr>"+
    						      "<td>"+e.msg[i].startDate+"<span class='ml10 mr10'>至</span>"+e.msg[i].endDate+"</td>"+
    						      "<td>"+e.msg[i].educationInstitution+"</td>"+
    						      "<td>"+e.msg[i].educationContent+"</td>"+
    						      "<td>"+e.msg[i].educationPerformance+"</td>"+
    						      "</tr>");
    							  jQuery(".peixun tbody").append(str);
    							 
    				}
    			}
    			
    		}
    	});
    }
    function gongzuo(){
    	var id=jQuery('#yuangongid').val();
    	jQuery.ajax({
    		type : "post",
    		url : baseUrl + "employee/action/gzjyquery",
    		data : "employeeId="+id,
    		dataType : "json",
    		beforeSend : function(){
    			requestLoading.fadeIn(800);//发起请求前显示加载中...
    		},
    		complete : function(){
    			requestLoading.fadeOut(800);//请求完毕后将加载效果移除
    		},
    		success : function(e){
    			jQuery(".gongzuo tbody tr").remove();
    			if(e.msg.length>0){
    				for (var i = 0; i < e.msg.length; i++) {
    					var str = jQuery("<tr>"+
    				            "<td>"+e.msg[i].startDate+"<span class='ml10 mr10'>至</span>"+e.msg[i].endDate+"</td>"+
    				            "<td>"+e.msg[i].companyName+"</td>"+
    				            "<td>"+e.msg[i].positionName+"</td>"+
    				            "<td>"+e.msg[i].experienceDesc+"</td>"+
    				        "</tr>");
    					  jQuery(".gongzuo tbody").append(str);
    					  
    				}
    			}
    			
    		}
    	});
    }
    
  //修改时擅长信息查询
    function shanchang(){
    	var id=jQuery('#yuangongid').val();
    	jQuery.ajax({
    		type : "post",
    		url : baseUrl + "employee/action/scquery",
    		data : "employeeId="+id,
    		dataType : "json",
    		beforeSend : function(){
    			requestLoading.fadeIn(800);//发起请求前显示加载中...
    		},
    		complete : function(){
    			requestLoading.fadeOut(800);//请求完毕后将加载效果移除
    		},
    		success : function(e){
    			jQuery(".shanchang tbody tr").remove();
    			if(e.msg.length>0){
    				for (var i = 0; i < e.msg.length; i++) {
    					var str = jQuery("<tr>"+
    	                        "<td>"+e.msg[i].categoryName+"</td>"+
    	                        "<td>"+e.msg[i].skillName+"</td>"+
    	                        "<td>"+e.msg[i].skillDesc+"</td>"+
    	                    "</tr>");
    					jQuery(".shanchang tbody").append(str);
    				}
    			}
    			
    		}
    	});
    }
  //奖惩记录查询
    function jiangcheng(){
    	var id=jQuery('#yuangongid').val();
    	var addData = {};
    	
    	addData["employeeId"] = id;
    	var querytype=jQuery('#jctype').val();
    	if(querytype!=null&&querytype!=""){
    		addData["type"] = querytype;
    	}
    	
    	var addData=JSON.stringify(addData);
    	jQuery.ajax({
    		type : "post",
    		url : baseUrl + "rewards/action/getrewardsrecord",
    		data : 'addData='+addData,
    		dataType : "json",
    		beforeSend : function(){
    			requestLoading.fadeIn(800);//发起请求前显示加载中...
    		},
    		complete : function(){
    			requestLoading.fadeOut(800);//请求完毕后将加载效果移除
    		},
    		success : function(e){
    			jQuery(".jiangcheng tbody").empty();
    			for (var i = 0; i < e.msg.length; i++) {
    				var str = jQuery("<tr>"+
    			            "<td>"+e.msg[i].operateTime+"</td>"+
    			            "<td>"+e.msg[i].des+"</td>"+
    			        "</tr>");
    			        jQuery(".jiangcheng tbody").append(str);
    			}
    		}
    	});
    }
  //推荐关系查询
    function tuijianguanxi(){
    	var id=jQuery('#yuangongid').val();
    	jQuery.ajax({
    		type : "post",
    		url : baseUrl + "employee/action/queryrygx",
    		data : 'employeeId='+id,
    		dataType : "json",
    		beforeSend : function(){
    			requestLoading.fadeIn(800);//发起请求前显示加载中...
    		},
    		complete : function(){
    			requestLoading.fadeOut(800);//请求完毕后将加载效果移除
    		},
    		success : function(e){
    			var selfinfo=e.msg.selfinfo;
    			var referrerinfo=e.msg.referrerinfo;
    			var recommendedlist=e.msg.recommendedlist;
    			jQuery(".benren tbody").empty();
    			if(selfinfo!=null){
    				var str = jQuery("<tr>"+
    	            "<td>本人</td>"+
    	            "<td><div class='detail-img'>"+
    	                    "<img src='"+selfinfo.headImage+"' alt=''/></div>"+
    	                "<div class='detail-content'>"+
    	                    "<span>姓名："+selfinfo.name+"</span>"+
    	                    "<span>工号："+selfinfo.employeeCode+"</span>"+
    	                    "<span>门店：南山店</span></div></td>"+
    	            "<td></td><td></td>"+
    	            "</tr>");
    			        jQuery(".benren tbody").append(str);
    			}
    			jQuery(".tuijianren tbody").empty();
    			if(referrerinfo!=null){
    				var str = jQuery("<tr>"+
    	            "<td>推荐人</td>"+
    	            "<td><div class='detail-img'>"+
    	                    "<img src='"+referrerinfo.headImage+"' alt=''/></div>"+
    	                "<div class='detail-content'>"+
    	                    "<span>姓名："+referrerinfo.name+"</span>"+
    	                    "<span>工号："+referrerinfo.employeeCode+"</span>"+
    	                    "<span>门店：南山店</span></div></td>"+
    	            "<td></td><td></td>"+
    	            "</tr>");
    			        jQuery(".tuijianren tbody").append(str);
    			}
    			jQuery(".beituijian tbody").empty();
    			if(recommendedlist.length>0){
    				var trTD = jQuery("<tr></tr>");
    				trTD.append("<td>被他推荐的人</td>");
    				for (var i = 0; i < recommendedlist.length; i++) {
    					trTD.append("<td><div class='detail-img'>"+
    				                    "<img src='"+recommendedlist[i].headImage+"' alt=''/></div>"+
    				                    "<div class='detail-content'>"+
    				                    "<span>姓名："+recommendedlist[i].name+"</span>"+
    				                    "<span>工号："+recommendedlist[i].employeeCode+"</span>"+
    				                    "<span>门店：南山店</span></div></td>");
    						}
    				jQuery(".beituijian tbody").append(trTD);
    				}
    		}
    	});
    }
  //查询派遣
    function paiqian(){
    	
    	var employeeId=jQuery('#yuangongid').val();
    	jQuery.ajax({
    		type : "post",
    		url : baseUrl + "employee/action/getpqlist",
    		data : 'employeeId='+employeeId,
    		dataType : "json",
    		beforeSend : function(){
    			requestLoading.fadeIn(800);//发起请求前显示加载中...
    		},
    		complete : function(){
    			requestLoading.fadeOut(800);//请求完毕后将加载效果移除
    		},
    		success : function(e){
    			jQuery(".table777 tbody").empty();
    			for (var i = 0; i < e.msg.length; i++) {
    				var str = jQuery("<tr>"+
    			            "<td>"+e.msg[i].pStoreName+"</td>"+
    			            "<td>"+e.msg[i].dispatchTime+"</td>"+
    			        "</tr>");
    			        jQuery(".table777 tbody").append(str);
    			}
    		}
    	});
    }
    </script>