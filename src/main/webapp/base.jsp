<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String picPath = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
<script type="text/javascript">
	var baseUrl = "<%=basePath%>";
	var picUrl = "<%=picPath%>";
</script>

