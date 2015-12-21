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

			<textarea id="editor_id" name="content" style="width:700px;height:300px;">&lt;strong&gt;HTML内容&lt;/strong&gt;</textarea>

			<!-- 页面内容结束 -->

		</div>
		<!--RIGHT PANEL结束 -->

		<div class="clearfix"></div>

		<div id="star"></div>

	</div>
	<!--mainwrapper-->


</body>
<script src="<%=basePath%>js/wechat/wechat.js"></script>
<script charset="utf-8" src="<%=basePath %>editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath %>editor/lang/zh_CN.js"></script>
		<script>
		var items = [
		             'source', '|', 'undo', 'redo', '|', , 'cut', 'copy', 'paste',
		             'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','|','image'
		     ];
        var editor;
        KindEditor.ready(function(K) {
                editor = K.create('#editor_id', {
                        width:'430px',
                        items:items
                });
        });
		</script>
</html>