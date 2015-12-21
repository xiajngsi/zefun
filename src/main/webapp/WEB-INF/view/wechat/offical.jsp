<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
<body>
	<div class="mainwrapper">
		<!--loading start-->
		<%@ include file="/loading.jsp"%>
		<!--loading end-->

		<!--left-panel start-->
		<%@ include file="/menu.jsp"%>
		<!--left-panel end-->

		<!--RIGHT PANEL开始 -->
		<div class="rightpanel" style="margin-left: 200px;">
			<%@ include file="/top.jsp"%>
			<!-- 页面内容开始 -->
				<div class="more-toolbar">
				    <div class="table-toolbar">
				        <button class="btn btn-primary" data-target="#wechatMarketing" data-toggle="modal">开启微信营销</button>&nbsp;&nbsp;&nbsp;&nbsp;
				        <button class="btn btn-primary" data-target="#wechatPay" data-toggle="modal">开启微信支付</button>
				    </div>
				    <div class="clearfix"></div>
				</div>
				
				<div class="maincontent" style="overflow-y: auto">
				    <div class="contentinner">
				        <iframe src="https://mp.weixin.qq.com/" style="width: 100%;height: 900px" allowtransparency="true">
				            您的浏览器不支持嵌入式框架，或者当前配置为不显示嵌入式框架。
				        </iframe>
				    </div>
				</div>
				
				<div class="modal hide" id="wechatMarketing" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				    <div class="modal-dialog" role="document">
				        <div class="modal-content add-member-card">
				            <div class="modal-header">
				                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
				                <h4 class="modal-title" id="myModalLabel">开启微信支付</h4>
				            </div>
				            <div class="modal-body">
				                <form action="" class="editprofileform" id="openWechatWechatForm" method="post">
				                <c:if test="${storeWechat == null}">
				               		<p>
				                        <label>微信ID:</label>
				                        <input type="text" name="wechatId" class="input-xlarge" value="">
				                    </p>
				                    <p>
				                        <label>应用ID:</label>
				                        <input type="text" name="wechatAppId" class="input-xlarge" value="">
				                    </p>
				                    <p>
				                        <label>应用密钥:</label>
				                        <input type="text" name="wechatAppsecret" class="input-xlarge" value="">
				                    </p>
				                </c:if>
				                <c:if test="${storeWechat != null}">
				               		<p>
				                        <label>微信ID:</label>
				                        <input type="text" name="wechatId" class="input-xlarge" value="${storeWechat.wechatId}">
				                    </p>
				                    <p>
				                        <label>应用ID:</label>
				                        <input type="text" name="wechatAppId" class="input-xlarge" value="${storeWechat.wechatAppId}">
				                    </p>
				                    <p>
				                        <label>应用密钥:</label>
				                        <input type="text" name="wechatAppsecret" class="input-xlarge" value="${storeWechat.wechatAppsecret}">
				                    </p>				                
				                </c:if>
				                </form>
				            </div>
				            <div class="modal-footer">
				                <a class="btn modal-cancel" href="#">取消</a>
				                <a id="openWechatMarketing" class="btn btn-primary modal-confirm" href="#">确定</a>
				            </div>
				        </div>
				    </div>
				</div>
				
				<div class="modal hide" id="wechatPay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				    <div class="modal-dialog" role="document">
				        <div class="modal-content add-member-card">
				            <div class="modal-header">
				                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
				                <h4 class="modal-title" id="myModalLabel">开启微信支付</h4>
				            </div>
				            <div class="modal-body">
				                <form action="" class="editprofileform" method="post">
				                    <p>
				                        <label>支付商户号:</label>
				                        <input type="text" name="cart-name" id="group_name" class="input-xlarge" value="">
				                    </p>
				                    <p>
				                        <label>API密钥:</label>
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
<%-- 			<div class="maincontent" style="overflow-y: auto">
				<div class="contentinner">
	          <div class="n-tab">
	            <ul>
	                <li class="active n-sub-tab" data-target="#tab1">
	                    <span>门店微信绑定</span>
	                    <div class="border-bottom"></div>
	                </li>
	            </ul>
	            <div class="clearfix"></div>
	        </div>
	<form action="" class="am-form">
	        <div class="n-tab-content">
	            <div id="tab1" class="target-tab ">
	                <div class="tab-form">
	                <p></p>
	                <div class="p-part-first">
	                        <label class="w60 ml10" for="">微信号:</label>
	                        <c:if test="${storeWechat == null}">
	                        <input type="text" name="wechatId" class="w185">
	                        </c:if>
	                        <c:if test="${storeWechat != null}">
	                        <input type="text" name="wechatId" class="w185" value="${storeWechat.wechatId }" readonly="readonly">
	                        </c:if>
	                </div>
	                <p></p>
	                <div class="clearfix"></div>
	                <p></p>
	                <div class="p-part-first">
	                        <label class="w60 ml10" for="">appID:</label>
	                        <c:if test="${storeWechat == null}">
	                        <input type="text" name="wechatAppId" class="w185">
	                        </c:if>
	                        <c:if test="${storeWechat != null}">
	                        <input type="text" name="wechatAppId" class="w185" value="${storeWechat.wechatAppId }" readonly="readonly">
	                        </c:if>
	                </div>
	                <p></p>
	                <div class="clearfix"></div>
	                <p></p>
	                <div class="p-part-first">
	                        <label class="w60 ml10" for="">appsecret:</label>
	                        <c:if test="${storeWechat == null}">
	                        <input type="text" name="wechatAppsecret" class="w185">
	                        </c:if>
	                        <c:if test="${storeWechat != null}">
	                        <input type="text" name="wechatAppsecret" class="w185" value="${storeWechat.wechatAppsecret }" readonly="readonly">
	                        </c:if>
	                </div>
	                <p></p>
	                <div class="clearfix"></div>
	
	                <p></p><div class="p-part-first">
	                        <label class="w60 ml10" for="">TM:</label>
	                        <c:if test="${storeWechat == null}">
	                        <input type="text" name="couponsTm" class="w185">
	                        </c:if>
	                        <c:if test="${storeWechat != null}">
	                        <input type="text" name="couponsTm" class="w185" value="${storeWechat.couponsTm }" readonly="readonly">
	                        </c:if>
	                    </div>
	                <p></p>
	                <div class="clearfix"></div>
	                <p></p><div class="p-part-first">
	                        <label class="w60 ml10" for="">TM:</label>
	                        <c:if test="${storeWechat == null}">
	                        <input type="text" name="remindTm" class="w185">
	                        </c:if>
	                        <c:if test="${storeWechat != null}">
	                        <input type="text" name="remindTm" class="w185" value="${storeWechat.remindTm }" readonly="readonly">
	                        </c:if>
	                    </div>
	                <p></p>
	                <div class="clearfix"></div>
	                <div class="confirm-part mt50 ml80">
	                    <a class="btn w80" id="add">保存</a>
	                    <a class="btn w80" id="delete">清除</a>
	                </div>
	               </div>
	            </div><!--tab1-->
	    </div>
	    </form>
			</div>
			<!-- 页面内容结束 -->

		</div>
		<!--RIGHT PANEL结束 -->
	 --%>
		<div class="clearfix"></div>
	
		<div id="star"></div>
	</div>
</div>	
</body>
<script type="text/javascript">
jQuery("#openWechatMarketing").on("click",function(){
	var data = jQuery("#openWechatWechatForm").serialize();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "wechat/add/store/wechat",
		data : data,
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				dialog(e.msg);
			}else{
				dialog(e.msg);
			}
		}
	});
});
jQuery("#delete").on("click",function(){
	jQuery.ajax({
		type : "get",
		url : baseUrl + "wechat/delete/store/wechat",
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				dialog(e.msg);
				location.reload();
			}else{
				dialog(e.msg);
			}
		}
	});
});
</script>
</html>