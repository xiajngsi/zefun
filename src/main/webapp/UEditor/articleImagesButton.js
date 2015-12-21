UE.registerUI('image',function(editor,uiName){
    //注册按钮执行时的command命令，使用命令默认就会带有回退操作
    editor.registerCommand(uiName,{
        execCommand:function(){
        	if(editor.key == 'editor1'){
        		jQuery("#add-content-image1").modal();
           	    qiniu1.uploader({
           		    runtimes: 'html5,flash,html4',    //上传模式,依次退化
           		    browse_button: 'contentImage1',       //上传选择的点选按钮，**必需**
           		    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
           		    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
           		    container: 'add-content-image1',           //上传区域DOM ID，默认是browser_button的父元素，
           		    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
           		    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
           			max_retries : 3, //上传失败最大重试次数
           			dragdrop : true, //开启可拖曳上传
           			drop_element : 'add-content-image1', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
           					//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
           				},
           				'UploadProgress' : function(up, file) {
           					console.log("UploadProgress invoked ...");
           				},
           				'FileUploaded' : function(up, file, info) {
           					console.log("FileUploaded invoked ...");
           					var domain = up.getOption('domain');
           					var res = eval('(' + info + ')');
           					var sourceLink = domain + res.key;
           					jQuery.ajax({
               			        type: "POST",
               			        url: baseUrl+"wechat/upload/img/textarea",
               			        data: "imgUrl="+sourceLink,
               			        success: function(data) {
               			            if(data.code == 0){
               			            	UE.getEditor('editor1').execCommand('insertHtml', '<img src="'+data.msg+'">');
                   						jQuery("#add-content-image1").modal('hide');
                   						qiniu1=null;
               			            }else{
               			            	alert("error");
               			            	}
               			        	}
           						});
           						return;
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
        	}
        	if(editor.key == 'editor2'){
        		jQuery("#add-content-image2").modal();
           	    qiniu2.uploader({
           		    runtimes: 'html5,flash,html4',    //上传模式,依次退化
           		    browse_button: 'contentImage2',       //上传选择的点选按钮，**必需**
           		    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
           		    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
           		    container: 'add-content-image2',           //上传区域DOM ID，默认是browser_button的父元素，
           		    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
           		    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
           			max_retries : 3, //上传失败最大重试次数
           			dragdrop : true, //开启可拖曳上传
           			drop_element : 'add-content-image2', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
           					//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
           				},
           				'UploadProgress' : function(up, file) {
           					console.log("UploadProgress invoked ...");
           				},
           				'FileUploaded' : function(up, file, info) {
           					console.log("FileUploaded invoked ...");
           					var domain = up.getOption('domain');
           					var res = eval('(' + info + ')');
           					var sourceLink = domain + res.key;
           					jQuery.ajax({
               			        type: "POST",
               			        url: baseUrl+"wechat/upload/img/textarea",
               			        data: "imgUrl="+sourceLink,
               			        success: function(data) {
               			            if(data.code == 0){
               			            	UE.getEditor('editor2').execCommand('insertHtml', '<img src="'+data.msg+'">');
                   						jQuery("#add-content-image2").modal('hide');
                   						qiniu2=null;
               			            }else{
               			            	alert("error");
               			            	}
               			        	}
           						});
           						return;
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
        	}
        	if(editor.key == 'editor3'){
        		jQuery("#add-content-image3").modal();
           	    qiniu3.uploader({
           		    runtimes: 'html5,flash,html4',    //上传模式,依次退化
           		    browse_button: 'contentImage3',       //上传选择的点选按钮，**必需**
           		    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
           		    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
           		    container: 'add-content-image3',           //上传区域DOM ID，默认是browser_button的父元素，
           		    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
           		    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
           			max_retries : 3, //上传失败最大重试次数
           			dragdrop : true, //开启可拖曳上传
           			drop_element : 'add-content-image3', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
           					//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
           				},
           				'UploadProgress' : function(up, file) {
           					console.log("UploadProgress invoked ...");
           				},
           				'FileUploaded' : function(up, file, info) {
           					console.log("FileUploaded invoked ...");
           					var domain = up.getOption('domain');
           					var res = eval('(' + info + ')');
           					var sourceLink = domain + res.key;
           					jQuery.ajax({
               			        type: "POST",
               			        url: baseUrl+"wechat/upload/img/textarea",
               			        data: "imgUrl="+sourceLink,
               			        success: function(data) {
               			            if(data.code == 0){
               			            	UE.getEditor('editor3').execCommand('insertHtml', '<img src="'+data.msg+'">');
                   						jQuery("#add-content-image3").modal('hide');
                   						qiniu3=null;
               			            }else{
               			            	alert("error");
               			            	}
               			        	}
           						});
           						return;
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
        	}
        	if(editor.key == 'editor4'){
        		jQuery("#add-content-image4").modal();
           	    qiniu4.uploader({
           		    runtimes: 'html5,flash,html4',    //上传模式,依次退化
           		    browse_button: 'contentImage4',       //上传选择的点选按钮，**必需**
           		    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
           		    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
           		    container: 'add-content-image4',           //上传区域DOM ID，默认是browser_button的父元素，
           		    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
           		    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
           			max_retries : 3, //上传失败最大重试次数
           			dragdrop : true, //开启可拖曳上传
           			drop_element : 'add-content-image4', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
           					//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
           				},
           				'UploadProgress' : function(up, file) {
           					console.log("UploadProgress invoked ...");
           				},
           				'FileUploaded' : function(up, file, info) {
           					console.log("FileUploaded invoked ...");
           					var domain = up.getOption('domain');
           					var res = eval('(' + info + ')');
           					var sourceLink = domain + res.key;
           					jQuery.ajax({
               			        type: "POST",
               			        url: baseUrl+"wechat/upload/img/textarea",
               			        data: "imgUrl="+sourceLink,
               			        success: function(data) {
               			            if(data.code == 0){
               			            	UE.getEditor('editor4').execCommand('insertHtml', '<img src="'+data.msg+'">');
                   						jQuery("#add-content-image4").modal('hide');
                   						qiniu4=null;
               			            }else{
               			            	alert("error");
               			            	}
               			        	}
           						});
           						return;
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
        	}
        	if(editor.key == 'editor5'){
        		jQuery("#add-content-image5").modal();
           	    qiniu5.uploader({
           		    runtimes: 'html5,flash,html4',    //上传模式,依次退化
           		    browse_button: 'contentImage5',       //上传选择的点选按钮，**必需**
           		    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
           		    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
           		    container: 'add-content-image5',           //上传区域DOM ID，默认是browser_button的父元素，
           		    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
           		    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
           			max_retries : 3, //上传失败最大重试次数
           			dragdrop : true, //开启可拖曳上传
           			drop_element : 'add-content-image5', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
           					//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
           				},
           				'UploadProgress' : function(up, file) {
           					console.log("UploadProgress invoked ...");
           				},
           				'FileUploaded' : function(up, file, info) {
           					console.log("FileUploaded invoked ...");
           					var domain = up.getOption('domain');
           					var res = eval('(' + info + ')');
           					var sourceLink = domain + res.key;
           					jQuery.ajax({
               			        type: "POST",
               			        url: baseUrl+"wechat/upload/img/textarea",
               			        data: "imgUrl="+sourceLink,
               			        success: function(data) {
               			            if(data.code == 0){
               			            	UE.getEditor('editor5').execCommand('insertHtml', '<img src="'+data.msg+'">');
                   						jQuery("#add-content-image5").modal('hide');
                   						qiniu5=null;
               			            }else{
               			            	alert("error");
               			            	}
               			        	}
           						});
           						return;
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
        	}
        	if(editor.key == 'editor6'){
        		jQuery("#add-content-image6").modal();
           	    qiniu6.uploader({
           		    runtimes: 'html5,flash,html4',    //上传模式,依次退化
           		    browse_button: 'contentImage6',       //上传选择的点选按钮，**必需**
           		    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
           		    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
           		    container: 'add-content-image6',           //上传区域DOM ID，默认是browser_button的父元素，
           		    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
           		    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
           			max_retries : 3, //上传失败最大重试次数
           			dragdrop : true, //开启可拖曳上传
           			drop_element : 'add-content-image6', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
           					//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
           				},
           				'UploadProgress' : function(up, file) {
           					console.log("UploadProgress invoked ...");
           				},
           				'FileUploaded' : function(up, file, info) {
           					console.log("FileUploaded invoked ...");
           					var domain = up.getOption('domain');
           					var res = eval('(' + info + ')');
           					var sourceLink = domain + res.key;
           					jQuery.ajax({
               			        type: "POST",
               			        url: baseUrl+"wechat/upload/img/textarea",
               			        data: "imgUrl="+sourceLink,
               			        success: function(data) {
               			            if(data.code == 0){
               			            	UE.getEditor('editor6').execCommand('insertHtml', '<img src="'+data.msg+'">');
                   						jQuery("#add-content-image6").modal('hide');
                   						qiniu6=null;
               			            }else{
               			            	alert("error");
               			            	}
               			        	}
           						});
           						return;
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
        	}
        	
        	if(editor.key == 'editor7'){
        		jQuery("#add-content-image7").modal();
           	    qiniu7.uploader({
           		    runtimes: 'html5,flash,html4',    //上传模式,依次退化
           		    browse_button: 'contentImage7',       //上传选择的点选按钮，**必需**
           		    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
           		    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
           		    container: 'add-content-image7',           //上传区域DOM ID，默认是browser_button的父元素，
           		    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
           		    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
           			max_retries : 3, //上传失败最大重试次数
           			dragdrop : true, //开启可拖曳上传
           			drop_element : 'add-content-image7', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
           					//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
           				},
           				'UploadProgress' : function(up, file) {
           					console.log("UploadProgress invoked ...");
           				},
           				'FileUploaded' : function(up, file, info) {
           					console.log("FileUploaded invoked ...");
           					var domain = up.getOption('domain');
           					var res = eval('(' + info + ')');
           					var sourceLink = domain + res.key;
           					jQuery.ajax({
               			        type: "POST",
               			        url: baseUrl+"wechat/upload/img/textarea",
               			        data: "imgUrl="+sourceLink,
               			        success: function(data) {
               			            if(data.code == 0){
               			            	UE.getEditor('editor7').execCommand('insertHtml', '<img src="'+data.msg+'">');
                   						jQuery("#add-content-image7").modal('hide');
                   						qiniu7=null;
               			            }else{
               			            	alert("error");
               			            	}
               			        	}
           						});
           						return;
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
        	}
        	if(editor.key == 'editor8'){
        		jQuery("#add-content-image8").modal();
           	    qiniu8.uploader({
           		    runtimes: 'html5,flash,html4',    //上传模式,依次退化
           		    browse_button: 'contentImage8',       //上传选择的点选按钮，**必需**
           		    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
           		    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
           		    container: 'add-content-image8',           //上传区域DOM ID，默认是browser_button的父元素，
           		    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
           		    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
           			max_retries : 3, //上传失败最大重试次数
           			dragdrop : true, //开启可拖曳上传
           			drop_element : 'add-content-image8', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
           					//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
           				},
           				'UploadProgress' : function(up, file) {
           					console.log("UploadProgress invoked ...");
           				},
           				'FileUploaded' : function(up, file, info) {
           					console.log("FileUploaded invoked ...");
           					var domain = up.getOption('domain');
           					var res = eval('(' + info + ')');
           					var sourceLink = domain + res.key;
           					jQuery.ajax({
               			        type: "POST",
               			        url: baseUrl+"wechat/upload/img/textarea",
               			        data: "imgUrl="+sourceLink,
               			        success: function(data) {
               			            if(data.code == 0){
               			            	UE.getEditor('editor8').execCommand('insertHtml', '<img src="'+data.msg+'">');
                   						jQuery("#add-content-image8").modal('hide');
                   						qiniu8=null;
               			            }else{
               			            	alert("error");
               			            	}
               			        	}
           						});
           						return;
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
        	}
        }
    });
    var btn;
	//创建一个button
    btn = new UE.ui.Button({
       //按钮的名字
       name:uiName,
       //提示
       title:uiName,
       //需要添加的额外样式，指定icon图标，这里默认使用一个重复的icon
       cssRules :'background-position: -380px 0px;',
       //点击时执行的命令
       onclick:function () {
    	   editor.execCommand(uiName);
            //这里可以不用执行命令,做你自己的操作也可
/*          jQuery("#add-content-image1").modal();
       		var qiniu1 = new QiniuJsSDK();
       	    qiniu1.uploader({
       		    runtimes: 'html5,flash,html4',    //上传模式,依次退化
       		    browse_button: 'contentImage1',       //上传选择的点选按钮，**必需**
       		    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
       		    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
       		    container: 'add-content-image1',           //上传区域DOM ID，默认是browser_button的父元素，
       		    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
       		    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
       			max_retries : 3, //上传失败最大重试次数
       			dragdrop : true, //开启可拖曳上传
       			drop_element : 'add-content-image1', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
       					//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
       				},
       				'UploadProgress' : function(up, file) {
       					console.log("UploadProgress invoked ...");
       				},
       				'FileUploaded' : function(up, file, info) {
       					console.log("FileUploaded invoked ...");
       					var domain = up.getOption('domain');
       					var res = eval('(' + info + ')');
       					var sourceLink = domain + res.key;
       						UE.getEditor('editor1').execCommand('insertHtml', '<img src="'+sourceLink+'">');
       						jQuery("#add-content-image1").modal('hide');
       						qiniu1=null;
       						return;
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
       		});*/
       	}
   });

    //当点到编辑内容上时，按钮要做的状态反射
/*    editor.addListener('selectionchange', function () {
        var state = editor.queryCommandState(uiName);
        if (state == -1) {
            btn.setDisabled(true);
            btn.setChecked(false);
        } else {
            btn.setDisabled(false);
            btn.setChecked(state);
        }
    });*/

    //因为你是添加button,所以需要返回这个button
    return btn;
});