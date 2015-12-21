<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String chatPath = request.getContextPath();
			String chatPasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ chatPath + "/";
%>
<!-- <div class="new-news-wrap">
    <div class="alert alert-info">
        <button data-dismiss="alert" class="close" type="button">×</button>
        <span name="newMessagesTip"></span>
    </div>
</div> -->
<script src="<%=chatPath%>/js/common/socket.io.js"></script>
<script type="text/javascript">
var userId = '${session_key_user_id}';
var storeId = '${session_key_store_id}';
var user = {"userId" : userId, "storeId" : storeId};
if(!isEmpty(userId)){
	var socket = io.connect('ws://121.201.14.117:3000');
    socket.on('connect', function(data) {
    	console.log(data);
    	//登录聊天室
        socket.emit('initUser', user);
    	
    	//接收消息
        socket.on('message', function(data) {
        	console.log("message : " + JSON.stringify(data));
        	var fid = data.fid;
        	//PC通知类处理
        	if (fid == 2) {
        		var type = data.data.type;
        		//新预约或者服务交接
        		if (type == 2) {
        			//播放语音
        			textToVoice(0, data.data.msg);
        		} else if (type == 4) {
        			//播放语音
        			textToVoice(0, data.data.msg);
        		}
        	}
        });
    });
}
</script>
