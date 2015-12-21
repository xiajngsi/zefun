<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
	<div class="mainwrapper">
	
		<!--loading start-->
		<%@ include file="/loading.jsp" %>
		<!--loading end-->
		<%@ include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px;">
			<%@ include file="/top.jsp"%>

			<!-- 页面内容开始 -->
            
            <div class="maincontent" style="overflow-y: auto">
    <div class="contentinner">
        
        <div class="more-toolbar" >
            <div class="table-toolbar">
                <span class="font-size-16 btn-color mr10">推送图文</span>
                <span class="video fr font-333" >视频帮助
                    <span class="iconfont icon-shipin" style="margin-top: 1px;"></span>
                </span>
            </div>
            <div class="clearfix"></div>
        </div><!--more-toolbar-->

        <div class="widgetcontent">
            <div id="tabs">
                <ul style="display: none;">
                    <li><a href="#tabs-1">第一步：选择图文消息</a></li>
                    <li><a href="#tabs-2">第二步：选择发送对象</a></li>
                    <li><a href="#tabs-3">第三步：预览</a></li>
                </ul>
                <div id="tabs-1">
                    <h5 style="margin-top: 10px;">&nbsp;&nbsp;第一步 选择图文消息</h5>
                    <hr>
                    <div class="added-reply two">
                        <table class="table">
                            <thead>
                            <tr>
                                <th></th>
                                <th>标题</th>
                                <th>创建时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            
                            <c:forEach items="${items }" var="item">
                            <tr> 
                                <td>
                                <input type="radio" value="${item.mediaId }" name="selectTitle"/>
                                <input type="hidden" name="fatherMediaId" value="${item.fatherMediaId }">
                                </td>
                                <td>${item.title }</td>
                                <td>${item.createTime }</td>
                            </tr>
                            </c:forEach>
                            
                            </tbody>
                        </table>

                        <div class="opr one fr" style="margin-top: 40px;">
                            <button id="xiayibu1" class="btn ">下一步</button>
                        </div><!--opr-->
                    </div>
                </div><!--tabs-1-->
                
                <div id="tabs-2">
                    <h5 style="margin-top: 10px;">&nbsp;&nbsp;第二步 选择发送对象</h5>
                    <hr>
                    <div style="width: 50%; display: inline-block;">
                        <table class="table">
                            <thead style="background-color: #c9e6e6;">
                            <tr>
                            <tr>
                                <td></td>
                                <td>
                                    <span>选择会员卡等级</span>
                                </td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${level }" var="leves">
                            <tr>
                                <td>
                                    <input type="checkbox" value="${leves.levelId }" name="msg_member_level"/>
                                </td>
                                <td>
                                    <span>${leves.levelName }</span>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                      </div>
                      <div class="added-reply two" style="width: 50%; display: inline-block;float: right">
                          <table class="table">
                              <thead style="background-color: #c9e6e6;">
                              <tr>
                                  <td></td>
                                  <td>
                                      <span>选择会员分组</span>
                                  </td>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${screen }" var="screening">
                              <tr>
                                  <td><input type="checkbox" value="${screening.screeningId }" name="msg_screening"/></td>
                                  <td>
                                      <span>${screening.screeningName }</span>
                                  </td>
                              </tr>
                              </c:forEach>
                              </tbody>
                          </table>
                    </div>
                    <div class="opr two fr" style="margin-top: 40px;">
                        <button id="xiayibu2" class="btn ">下一步</button>
                    </div>
                </div>
                
                <div id="tabs-3">
                    <h5 style="margin-top: 10px;">&nbsp;&nbsp;第三步 确认发送</h5>
                    <hr>
                    <div class="added-reply two">
                        <div style="text-align: center;">
                        <div class="weixin-preview">
				            <div class="mobile_preview" id="mobileDiv" style="display: block;">
				                <div class="mobile_preview_hd">
				                    <strong class="nickname">智放</strong>
				                </div>
				                <div class="mobile_preview_bd">
				                    <ul id="viewShow" class="show_list">
				                        <li>
				                            <div class="img-link" style="margin-left: -10px;margin-right: 10px;">
				                                <a href="">
				                                    <img src="assets/images/weixin1.jpg" alt="微信测试">
				                                    <div class="word" style="width: 218px;">
				                                        	太酷了！文艺程序员给女票做了个智能小风扇...
				                                    </div>
				                                </a>
				
				                            </div>
				                            <div class="word-link" style="margin-left: -10px;margin-right: 10px;">
				                                <a href="">
				                                    <div class="word">
				                                        	轻松玩转后期剪辑Premiere
				                                    </div>
				                                    <div class="img">
				                                        <img src="assets/images/weixin1.jpg" alt="">
				                                    </div>
				                                </a>
				                            </div>
				                            <div class="word-link" style="margin-left: -10px;margin-right: 10px;">
				                                <a href="">
				                                    <div class="word">
				                                        	轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere
				                                    </div>
				                                    <div class="img">
				                                        <img src="assets/images/weixin1.jpg" alt="">
				                                    </div>
				                                </a>
				                            </div>
				                            <div class="word-link" style="margin-left: -10px;margin-right: 10px;">
				                                <a href="">
				                                    <div class="word">
				                                        	轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere
				                                    </div>
				                                    <div class="img">
				                                        <img src="assets/images/weixin1.jpg" alt="">
				                                    </div>
				                                </a>
				                            </div>
				                            <div class="word-link" style="margin-left: -10px;margin-right: 10px;">
				                                <a href="">
				                                    <div class="word">
				                                        	轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere
				                                    </div>
				                                    <div class="img">
				                                        <img src="assets/images/weixin1.jpg" alt="">
				                                    </div>
				                                </a>
				                            </div>
				                            <div class="word-link" style="margin-left: -10px;margin-right: 10px;">
				                                <a href="">
				                                    <div class="word">
				                                        	轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere
				                                    </div>
				                                    <div class="img">
				                                        <img src="assets/images/weixin1.jpg" alt="">
				                                    </div>
				                                </a>
				                            </div>
				                            <div class="word-link" style="margin-left: -10px;margin-right: 10px;">
				                                <a href="">
				                                    <div class="word">
				                                        	轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere
				                                    </div>
				                                    <div class="img">
				                                        <img src="assets/images/weixin1.jpg" alt="">
				                                    </div>
				                                </a>
				                            </div>
				                            <div class="word-link" style="margin-left: -10px;margin-right: 10px;">
				                                <a href="">
				                                    <div class="word">
				                                      	  轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere
				                                    </div>
				                                    <div class="img">
				                                        <img src="assets/images/weixin1.jpg" alt="">
				                                    </div>
				                                </a>
				                            </div>
				                        </li>
				                    </ul>
				                </div>
				                <div class="mobile_preview_ft">
				                    	<!-- 菜单 -->
				                </div>
				            </div>
				        </div>
                        </div>
                        <div class="opr two" align="center">
                            <button id="wangcheng" class="btn ">完成</button>
                        </div>
                    </div>
                </div>
            </div><!--tabs-->
        </div><!--widgetcontent-->
    </div>
</div>

			<!-- 页面内容结束 -->

		</div>
		<div class="clearfix"></div>
		<div id="star"></div>
	</div>
	<!-- 展示没有次数的人员 -->
<div class="modal hide" id="wechatCount" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 580px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	下列名单本月推送图文次数已达上限
                </h4>
            </div>
            <div class="imgword-list">
            <table class="table table-bordered table-striped member-list-table">
		            <thead>
		            <tr>
		                <th>手机号</th>
		                <th>姓名</th>
		                <th>生日</th>
		            </tr>
		            </thead>
		            <tbody id="init_member">
		            </tbody>
		        </table>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn " id="confirm-menu" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    var mediaId ;
    var fatherMediaId ;
    jQuery("#xiayibu1").on("click",function(){
        mediaId = jQuery("input:radio[name='selectTitle']:checked").val();
        fatherMediaId = jQuery("input:radio[name='selectTitle']:checked").parent().children("input[name='fatherMediaId']").val();
        if(typeof(mediaId)=="undefined"){
        	dialog("请选择图文消息");
        	return ;
        }
        console.log(mediaId);
        console.log(fatherMediaId);
        jQuery("#tabs-1").hide();
        jQuery("#tabs-2").show();
    });
    jQuery("#xiayibu2").on("click",function(){
        var ci = jQuery("input:checkbox[name='msg_member_level']:checked");
        var level = "";
        for(i=0;i<ci.length;i++){
            if(i == ci.length-1 ){
            	level = level +jQuery(ci[i]).val();
            }else{
            	level = level +jQuery(ci[i]).val()+ ",";
            }
        }
        var ai = jQuery("input:checkbox[name='msg_screening']:checked");
        var sceenNum = "";
        for(i=0;i<ai.length;i++){
            if(i == ai.length-1 ){
            	sceenNum = sceenNum +jQuery(ai[i]).val();
            }else{
            	sceenNum = sceenNum +jQuery(ai[i]).val()+ ",";
            }
        }
        if(level == ""&&sceenNum == ""){
        	dialog("请至少选择一个分组进行发送");
        	return;
        }
    	//将选择的会员分组进行校验,查询发送次数为0的用户
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/check/member/count",
			data : "level="+level+"&sceening="+sceenNum,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					for ( var s in e.msg) {
						var tr = jQuery("<tr></tr>");
						var td1 = jQuery("<td>"+e.msg[s].phone+"</td>");
						var td2 = jQuery("<td>"+e.msg[s].name+"</td>");
						var td3 = jQuery("<td>"+e.msg[s].birthday+"</td>");
						tr.append(td1);
						tr.append(td2);
						tr.append(td3);
						jQuery("#init_member").append(tr);
					}
					jQuery("#wechatCount").modal();
					jQuery("#confirm-menu").on("click",function(){
						jQuery("#wechatCount").modal("hide");
					});
				}
			}
		});
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/get/item/by/mediaId",
			data : "mediaId="+mediaId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					jQuery("#viewShow").children("li").children("div").hide();
					/**显示图文消息*/
					for (var i = 0; i < e.msg.length; i++) {
						var array_element = e.msg[i];
						jQuery("#viewShow").children("li").children("div").eq(i).show();
						jQuery("#viewShow").children("li").children("div").eq(i).find(".word").text(array_element.title);
						jQuery("#viewShow").children("li").children("div").eq(i).find("img").attr("src",array_element.qiniuImg);
					}
				}
			}
		});
        jQuery("#tabs-2").hide();
        jQuery("#tabs-3").show();
    });
    jQuery("#wangcheng").on("click",function(){
        var ci = jQuery("input:checkbox[name='msg_member_level']:checked");
        var level = "";
        for(i=0;i<ci.length;i++){
            if(i == ci.length-1 ){
            	level = level +jQuery(ci[i]).val();
            }else{
            	level = level +jQuery(ci[i]).val()+ ",";
            }
        }
        var ai = jQuery("input:checkbox[name='msg_screening']:checked");
        var sceenNum = "";
        for(i=0;i<ai.length;i++){
            if(i == ai.length-1 ){
            	sceenNum = sceenNum +jQuery(ai[i]).val();
            }else{
            	sceenNum = sceenNum +jQuery(ai[i]).val()+ ",";
            }
        }
		jQuery.ajax({
			type : "post",
			url : baseUrl + "send/item/openId",
			data : "mediaId="+mediaId+"&level="+level+"&sceening="+sceenNum+"&fatherMediaId="+fatherMediaId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					dialog("发送成功");
				}else{
					dialog(e.msg);
				}
			}
		});
        jQuery("#tabs-1").show();
        jQuery("#tabs-3").hide();
    });
</script>
</html>