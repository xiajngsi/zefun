<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/base.jsp" %>
<title>上传测试</title>
</head>
<body>
    <div class="modal-dialog" role="document">
        <div class="modal-content identity">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">身份证</h3>
            </div>
            <div class="modal-body">
                <div class="display-frame" id="container">
                	<img id="showImg" src="" style="width: 100%; height: 100%" />
                </div>
                <div class="selected-show">
                    <ul>
                        <li><img src="" alt=""/><img src="<%=basePath%>img/delete.png" alt="" class="delete"/></li>
                        <li><img src="" alt=""/><img src="<%=basePath%>img/delete.png" alt="" class="delete"/></li>
                    </ul>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="select-file" id="pickfiles">选择文件</button>
                <button type="button" class="btn cancel" data-dismiss="modal">取消</button>
                <button type="button" class="btn confirm">确定</button>
            </div>
        </div>
    </div>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>js/common/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/plupload.full.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
    <script type="text/javascript">
    var qiniu = new QiniuJsSDK();
    qiniu.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles',       //上传选择的点选按钮，**必需**
	    uptoken_url: '<%=basePath%>qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: 'container',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: '<%=basePath%>js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
		chunk_size : '2mb', //分块上传时，每片的体积
		auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
		init : {
			'FilesAdded' : function(up, files) {
				console.log("FilesAdded invoked ...");
				var err = false;
				plupload.each(files, function(file) {
					if(!qiniu.isImage(file.name)){
						err = true;
						return false;
					}
				});
				if(err) {
					dialog("只能上传图片文件");
					up.removeFile(files[0]);
					up.stop();
					jQuery('#mask').remove();
				}
			},
			'BeforeUpload' : function(up, file) {
				console.log("BeforeUpload invoked ...");
				jQuery(document).find('.mainwrapper').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
			},
			'UploadProgress' : function(up, file) {
				console.log("UploadProgress invoked ...");
			},
			'FileUploaded' : function(up, file, info) {
				console.log("FileUploaded invoked ...");
				var domain = up.getOption('domain');
				var res = eval('(' + info + ')');
				var sourceLink = domain + res.key;
				jQuery('#mask').remove();
				console.log(sourceLink);
				jQuery("#showImg").attr("src", sourceLink);
			},
			'Error' : function(up, err, errTip) {
				dialog(errTip);
				jQuery('#mask').remove();
			},
			'UploadComplete' : function() {
				console.log("UploadComplete invoked ...");
				dialog("UploadComplete");
			},
			'Key' : function(up, file) {
				console.log("Key invoked ...");
				var key = "zefun/wechat/" + new Date().getTime();
				return key;
			}
		}
	});
    </script>
</body>
</html>