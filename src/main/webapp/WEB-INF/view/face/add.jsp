<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人脸信息录入</title>
<style>
body {
	font-family: sans-serif;
	font-size: 17px;
	line-height: 24px;
	color: #fff;
	width: 100%;
	height: 100%;
	margin: 0;
	text-align: center;
	background-color: #111;
}

#info {
	position: absolute;
	width: 100%;
	height: 30px;
	top: 50%;
	margin-top: -15px;
}

#output {
	width: auto;
	height: 100%;
	background: black;
	-webkit-transform: scale(-1, 1);
}

#github {
	position: absolute;
	top: 0;
	right: 0;
	border: 0;
}
</style>
</head>
<body>
	<p id="info">Please allow access to your camera!</p>
	<canvas id="output"></canvas>
	<br><input type="button" value="Capture" onclick="addFace()" />
	<script type="text/javascript" src="<%=basePath %>face/ccv.js"></script>
	<script type="text/javascript" src="<%=basePath %>face/face.js"></script>
	<script type="text/javascript" src="<%=basePath %>face/facepp-sdk.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>face/handle.js"></script>
	<script type="text/javascript">
		var isLoad = false;
		function addFace(){
			if(isLoad){
				return;
			}
			var k = capture();
			if(!k){
				dialog("未识别到人脸");
				return;
			}
			isLoad = true;
			api.request('/detection/detect', k, function(err, result) {
		        if (err) {
		      	  dialog("face error : " + JSON.stringify(err));
		      	  isLoad = false;
		          return;
		        }
		        console.log("detect result : " + JSON.stringify(result));
		        faceId = result.face[0].face_id;
		        $.ajax({
		    		url : "<%=basePath%>face/action/add",	        	
		    		type : "POST",
		    		data : "faceId=" + faceId,
		    		success : function(e){
		    			isLoad = false;
		    			if(e.code != 0){
		    				dialog(e.msg);
		    			}
		    		}
		        });
		    });
		}
	</script>
</body>
</html>