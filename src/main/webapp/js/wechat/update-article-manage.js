    var qiniu = new QiniuJsSDK();
    qiniu.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: 'container',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
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
				jQuery('#mask').remove();
				
				jQuery("#showImg1").attr("src", sourceLink).show();
				jQuery("#imgUrl1").val(sourceLink);
				jQuery("#img-one").attr("src", sourceLink);
				/*将微信图片和七牛图片更新，并且将图片换算为thumbMediaId后更新*/
				jQuery("#qiniuImg1").attr("src", sourceLink);
				jQuery("#qiniuImg1").val(sourceLink);
				changeThumbMediaId("thumb1","picUrl1",sourceLink);
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
    
    /** 第二个图文消息上传七牛*/
    var qiniu2 = new QiniuJsSDK();
    qiniu2.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles2',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: 'container2',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container2', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
				jQuery('#mask').remove();
				jQuery("#showImg2").attr("src", sourceLink).show();
				jQuery("#imgUrl2").val(sourceLink);
				/** 缩略图处理*/
				jQuery("#news-item2").children("img").attr("class","news-thumb default news-word");
				jQuery("#news-item2").children("img").attr("src",sourceLink);
				jQuery("#news-item2").children("i").remove();
				/*将微信图片和七牛图片更新，并且将图片换算为thumbMediaId后更新*/
				jQuery("#qiniuImg2").attr("src", sourceLink);
				jQuery("#qiniuImg2").val(sourceLink);
				changeThumbMediaId("thumb2","picUrl2",sourceLink);
				
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
    
    /** 第三个图文消息上传七牛*/
    var qiniu3 = new QiniuJsSDK();
    qiniu3.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles3',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: 'container3',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container3', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
				jQuery('#mask').remove();
				jQuery("#showImg3").attr("src", sourceLink).show();
				jQuery("#imgUrl3").val(sourceLink);
				/** 缩略图处理*/
				jQuery("#news-item3").children("img").attr("class","news-thumb default news-word");
				jQuery("#news-item3").children("img").attr("src",sourceLink);
				jQuery("#news-item3").children("i").remove();
				/*将微信图片和七牛图片更新，并且将图片换算为thumbMediaId后更新*/
				jQuery("#qiniuImg3").attr("src", sourceLink);
				jQuery("#qiniuImg3").val(sourceLink);
				changeThumbMediaId("thumb3","picUrl3",sourceLink);
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
    
    /** 第四个图文消息上传七牛*/
    var qiniu4 = new QiniuJsSDK();
    qiniu4.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles4',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: 'container4',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container4', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
				jQuery('#mask').remove();
				jQuery("#showImg4").attr("src", sourceLink).show();
				jQuery("#imgUrl4").val(sourceLink);
				/** 缩略图处理*/
				jQuery("#news-item4").children("img").attr("class","news-thumb default news-word");
				jQuery("#news-item4").children("img").attr("src",sourceLink);
				jQuery("#news-item4").children("i").remove();
				/*将微信图片和七牛图片更新，并且将图片换算为thumbMediaId后更新*/
				jQuery("#qiniuImg4").attr("src", sourceLink);
				jQuery("#qiniuImg4").val(sourceLink);
				changeThumbMediaId("thumb4","picUrl4",sourceLink);
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
    
    /** 第五个图文消息上传七牛*/
    var qiniu5 = new QiniuJsSDK();
    qiniu5.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles5',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: 'container5',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container5', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
				jQuery('#mask').remove();
				jQuery("#showImg5").attr("src", sourceLink).show();
				jQuery("#imgUrl5").val(sourceLink);
				/** 缩略图处理*/
				jQuery("#news-item5").children("img").attr("class","news-thumb default news-word");
				jQuery("#news-item5").children("img").attr("src",sourceLink);
				jQuery("#news-item5").children("i").remove();
				/*将微信图片和七牛图片更新，并且将图片换算为thumbMediaId后更新*/
				jQuery("#qiniuImg5").attr("src", sourceLink);
				jQuery("#qiniuImg5").val(sourceLink);
				changeThumbMediaId("thumb5","picUrl5",sourceLink);
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
    
    /** 第六个图文消息上传七牛*/
    var qiniu6 = new QiniuJsSDK();
    qiniu6.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles6',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: 'container6',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container6', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
				jQuery('#mask').remove();
				jQuery("#showImg6").attr("src", sourceLink).show();
				jQuery("#imgUrl6").val(sourceLink);
				/** 缩略图处理*/
				jQuery("#news-item6").children("img").attr("class","news-thumb default news-word");
				jQuery("#news-item6").children("img").attr("src",sourceLink);
				jQuery("#news-item6").children("i").remove();
				/*将微信图片和七牛图片更新，并且将图片换算为thumbMediaId后更新*/
				jQuery("#qiniuImg6").attr("src", sourceLink);
				jQuery("#qiniuImg6").val(sourceLink);
				changeThumbMediaId("thumb6","picUrl6",sourceLink);
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
    
    /** 第七个图文消息上传七牛*/
    var qiniu7 = new QiniuJsSDK();
    qiniu7.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles7',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: 'container7',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container7', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
				jQuery('#mask').remove();
				jQuery("#showImg7").attr("src", sourceLink).show();
				jQuery("#imgUrl7").val(sourceLink);
				/** 缩略图处理*/
				jQuery("#news-item7").children("img").attr("class","news-thumb default news-word");
				jQuery("#news-item7").children("img").attr("src",sourceLink);
				jQuery("#news-item7").children("i").remove();
				/*将微信图片和七牛图片更新，并且将图片换算为thumbMediaId后更新*/
				jQuery("#qiniuImg7").attr("src", sourceLink);
				jQuery("#qiniuImg7").val(sourceLink);
				changeThumbMediaId("thumb7","picUrl7",sourceLink);
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
    
    /** 第八个图文消息上传七牛*/
    var qiniu8 = new QiniuJsSDK();
    qiniu8.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles8',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: 'container8',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container8', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
				jQuery('#mask').remove();
				jQuery("#showImg8").attr("src", sourceLink).show();
				jQuery("#imgUrl8").val(sourceLink);
				/** 缩略图处理*/
				jQuery("#news-item8").children("img").attr("class","news-thumb default news-word");
				jQuery("#news-item8").children("img").attr("src",sourceLink);
				jQuery("#news-item8").children("i").remove();
				/*将微信图片和七牛图片更新，并且将图片换算为thumbMediaId后更新*/
				jQuery("#qiniuImg8").attr("src", sourceLink);
				jQuery("#qiniuImg8").val(sourceLink);
				changeThumbMediaId("thumb8","picUrl8",sourceLink);
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
  
    /*将图片换算为thumbId*/
    function changeThumbMediaId(doc1,doc2,imgUrl){
		jQuery.ajax({
    		url: baseUrl+"wechat/update/thumb/id",
            data: "qiniuImg="+imgUrl,
            type:"POST",
            success: function(data) {
                if(data.code == 0){
                	jQuery("#"+doc1).val(data.msg.thumbMediaId);
                	jQuery("#"+doc2).val(data.msg.picUrl);
                	console.log(data.msg.picUrl);
                	console.log(data.msg.thumbMediaId);
                }else{
               	   dialog('添加失败', '错误信息');
                }
            }
    	});
    }
    /*图片库选择*/
    jQuery(function(){
	    jQuery("#from-picture1").on("click",function(){
	    	jQuery("#photo-list-modal1").modal();
	    	jQuery(".photo-item").on("click",function(){
	            /*点击别的,复原以前的*/
	            jQuery(".photo-item").children(".appmsg_mask").hide();
	            jQuery(".photo-item").children(".icon_card_selected").hide();
	            var picItem = jQuery(this);
	            picItem.children(".appmsg_mask").show();
	            picItem.children(".icon_card_selected").show();
	            var src = picItem.find("img").eq(0).attr("src");
	            jQuery("#confirm-menu-url1").one("click",function(){
	            	  jQuery("#news-item1").find("img").attr("src",src);
		           	  jQuery("#qiniuImg1").val(src);
		           	  jQuery("#thumb1").val(picItem.find("input[name='thumbMediaId']").val());
		              jQuery("#picUrl1").val(picItem.find("input[name='pictureWechat']").val());
		           	  jQuery("#showImg1").attr("src",src);
		           	  jQuery("#showImg1").show();
	            });
	        });
	    });
	    jQuery("#from-picture2").on("click",function(){
	    	jQuery("#photo-list-modal2").modal();
	    	jQuery(".photo-item").on("click",function(){
	            /*点击别的,复原以前的*/
	            jQuery(".photo-item").children(".appmsg_mask").hide();
	            jQuery(".photo-item").children(".icon_card_selected").hide();
	            var picItem = jQuery(this);
	            picItem.children(".appmsg_mask").show();
	            picItem.children(".icon_card_selected").show();
	            var src = picItem.find("img").eq(0).attr("src");
	            jQuery("#confirm-menu-url2").one("click",function(){
	            	  jQuery("#news-item2").find("img").attr("src",src);
		           	  jQuery("#qiniuImg2").val(src);
		           	  jQuery("#thumb2").val(picItem.find("input[name='thumbMediaId']").val());
		              jQuery("#picUrl2").val(picItem.find("input[name='pictureWechat']").val());
		           	  jQuery("#showImg2").attr("src",src);
		           	  jQuery("#showImg2").show();
	            });
	        });
	    });
	    jQuery("#from-picture3").on("click",function(){
	    	jQuery("#photo-list-modal3").modal();
	    	jQuery(".photo-item").on("click",function(){
	            /*点击别的,复原以前的*/
	            jQuery(".photo-item").children(".appmsg_mask").hide();
	            jQuery(".photo-item").children(".icon_card_selected").hide();
	            var picItem = jQuery(this);
	            picItem.children(".appmsg_mask").show();
	            picItem.children(".icon_card_selected").show();
	            var src = picItem.find("img").eq(0).attr("src");
	            jQuery("#confirm-menu-url3").one("click",function(){
	            	  jQuery("#news-item3").find("img").attr("src",src);
		           	  jQuery("#qiniuImg3").val(src);
		           	  jQuery("#thumb3").val(picItem.find("input[name='thumbMediaId']").val());
		              jQuery("#picUrl3").val(picItem.find("input[name='pictureWechat']").val());
		           	  jQuery("#showImg3").attr("src",src);
		           	  jQuery("#showImg3").show();
	            });
	        });
	    });
	    jQuery("#from-picture4").on("click",function(){
	    	jQuery("#photo-list-modal4").modal();
	    	jQuery(".photo-item").on("click",function(){
	            /*点击别的,复原以前的*/
	            jQuery(".photo-item").children(".appmsg_mask").hide();
	            jQuery(".photo-item").children(".icon_card_selected").hide();
	            var picItem = jQuery(this);
	            picItem.children(".appmsg_mask").show();
	            picItem.children(".icon_card_selected").show();
	            var src = picItem.find("img").eq(0).attr("src");
	            jQuery("#confirm-menu-url4").one("click",function(){
	            	  jQuery("#news-item4").find("img").attr("src",src);
		           	  jQuery("#qiniuImg4").val(src);
		           	  jQuery("#thumb4").val(picItem.find("input[name='thumbMediaId']").val());
		              jQuery("#picUrl4").val(picItem.find("input[name='pictureWechat']").val());
		           	  jQuery("#showImg4").attr("src",src);
		           	  jQuery("#showImg4").show();
	            });
	        });
	    });
	    jQuery("#from-picture5").on("click",function(){
	    	jQuery("#photo-list-modal5").modal();
	    	jQuery(".photo-item").on("click",function(){
	            /*点击别的,复原以前的*/
	            jQuery(".photo-item").children(".appmsg_mask").hide();
	            jQuery(".photo-item").children(".icon_card_selected").hide();
	            var picItem = jQuery(this);
	            picItem.children(".appmsg_mask").show();
	            picItem.children(".icon_card_selected").show();
	            var src = picItem.find("img").eq(0).attr("src");
	            jQuery("#confirm-menu-url5").one("click",function(){
	            	  jQuery("#news-item5").find("img").attr("src",src);
		           	  jQuery("#qiniuImg5").val(src);
		           	  jQuery("#thumb5").val(picItem.find("input[name='thumbMediaId']").val());
		              jQuery("#picUrl5").val(picItem.find("input[name='pictureWechat']").val());
		           	  jQuery("#showImg5").attr("src",src);
		           	  jQuery("#showImg5").show();
	            });
	        });
	    });
	    jQuery("#from-picture6").on("click",function(){
	    	jQuery("#photo-list-modal6").modal();
	    	jQuery(".photo-item").on("click",function(){
	            /*点击别的,复原以前的*/
	            jQuery(".photo-item").children(".appmsg_mask").hide();
	            jQuery(".photo-item").children(".icon_card_selected").hide();
	            var picItem = jQuery(this);
	            picItem.children(".appmsg_mask").show();
	            picItem.children(".icon_card_selected").show();
	            var src = picItem.find("img").eq(0).attr("src");
	            jQuery("#confirm-menu-url6").one("click",function(){
	            	  jQuery("#news-item6").find("img").attr("src",src);
		           	  jQuery("#qiniuImg6").val(src);
		           	  jQuery("#thumb6").val(picItem.find("input[name='thumbMediaId']").val());
		              jQuery("#picUrl6").val(picItem.find("input[name='pictureWechat']").val());
		           	  jQuery("#showImg6").attr("src",src);
		           	  jQuery("#showImg6").show();
	            });
	        });
	    });
	    jQuery("#from-picture7").on("click",function(){
	    	jQuery("#photo-list-modal7").modal();
	    	jQuery(".photo-item").on("click",function(){
	            /*点击别的,复原以前的*/
	            jQuery(".photo-item").children(".appmsg_mask").hide();
	            jQuery(".photo-item").children(".icon_card_selected").hide();
	            var picItem = jQuery(this);
	            picItem.children(".appmsg_mask").show();
	            picItem.children(".icon_card_selected").show();
	            var src = picItem.find("img").eq(0).attr("src");
	            jQuery("#confirm-menu-url7").one("click",function(){
	            	  jQuery("#news-item7").find("img").attr("src",src);
		           	  jQuery("#qiniuImg7").val(src);
		           	  jQuery("#thumb7").val(picItem.find("input[name='thumbMediaId']").val());
		              jQuery("#picUrl7").val(picItem.find("input[name='pictureWechat']").val());
		           	  jQuery("#showImg7").attr("src",src);
		           	  jQuery("#showImg7").show();
	            });
	        });
	    });
	    jQuery("#from-picture8").on("click",function(){
	    	jQuery("#photo-list-modal8").modal();
	    	jQuery(".photo-item").on("click",function(){
	            /*点击别的,复原以前的*/
	            jQuery(".photo-item").children(".appmsg_mask").hide();
	            jQuery(".photo-item").children(".icon_card_selected").hide();
	            var picItem = jQuery(this);
	            picItem.children(".appmsg_mask").show();
	            picItem.children(".icon_card_selected").show();
	            var src = picItem.find("img").eq(0).attr("src");
	            jQuery("#confirm-menu-url8").one("click",function(){
	            	  jQuery("#news-item8").find("img").attr("src",src);
		           	  jQuery("#qiniuImg8").val(src);
		           	  jQuery("#thumb8").val(picItem.find("input[name='thumbMediaId']").val());
		              jQuery("#picUrl8").val(picItem.find("input[name='pictureWechat']").val());
		           	  jQuery("#showImg8").attr("src",src);
		           	  jQuery("#showImg8").show();
	            });
	        });
	    });
  });
    
    
    //新增图文消息
    var itemNum = 1;
    function addArtile(){
  	  if(itemNum >= 8){
  		  return ;
  	  }
  	  itemNum = itemNum + 1;
  	  jQuery("#item"+(itemNum-1)).hide();
  	  jQuery("#item"+itemNum).show();
  	  /**左侧显示缩略图信息*/
  	  jQuery("#news-item"+itemNum).show();
  	  jQuery("#itemNum").val(itemNum);
    }
    
    //设置标题
    jQuery(document).ready(function(){
  	  jQuery("#title1").blur(function(e){
	   	     jQuery("#t1").text(jQuery(this).val());
		 });
  	  jQuery("#title2").blur(function(e){
       	 jQuery("#t2").text(jQuery(this).val());
  	 });
  	  jQuery("#title3").blur(function(e){
           jQuery("#t3").text(jQuery(this).val());
     	 }); 
  	  jQuery("#title4").blur(function(e){
           jQuery("#t4").text(jQuery(this).val());
     	 }); 
  	  jQuery("#title5").blur(function(e){
           jQuery("#t5").text(jQuery(this).val());
     	 }); 
  	  jQuery("#title6").blur(function(e){
           jQuery("#t6").text(jQuery(this).val());
     	 }); 
  	  jQuery("#title7").blur(function(e){
           jQuery("#t7").text(jQuery(this).val());
     	 });
  	  jQuery("#title8").blur(function(e){
           jQuery("#t8").text(jQuery(this).val());
     	 });  
    });

    //点击编辑
    jQuery(document).ready(function(){
  	  jQuery("#news-item1").on("click",function(){
  	  hideItems();
  	  jQuery("#item1").show();
  	  });
  	  jQuery("#news-item2").on("click",function(){
      	  hideItems();
      	  jQuery("#item2").show();
       });
  	  jQuery("#news-item3").on("click",function(){
      	  hideItems();
      	  jQuery("#item3").show();
       });
  	  jQuery("#news-item4").on("click",function(){
      	  hideItems();
      	  jQuery("#item4").show();
       });
  	  jQuery("#news-item5").on("click",function(){
      	  hideItems();
      	  jQuery("#item5").show();
       });
  	  jQuery("#news-item6").on("click",function(){
      	  hideItems();
      	  jQuery("#item6").show();
       });
  	  jQuery("#news-item7").on("click",function(){
      	  hideItems();
      	  jQuery("#item7").show();
       });
  	  jQuery("#news-item8").on("click",function(){
      	  hideItems();
      	  jQuery("#item8").show();
       });
    });
    //隐藏所有的图文消息
    function hideItems(){
  	  for (var i = 0; i <= 8; i++) {
  		  jQuery("#item"+i).hide();
		}
    }
    //表单提交，记得做校验
    jQuery("#baocun").on("click",function(){
  	  //下面的方法为同步数据,如果不是这样的话,content的值不会传入后台中.
  	  /* if(typeof(editor1) != "undefined"){
  		  editor1.sync();
  	  }
  	  if(typeof(editor2) != "undefined"){
  		  editor2.sync();
  	  }
  	  if(typeof(editor3) != "undefined"){
  		  editor3.sync();
  	  }
  	  if(typeof(editor4) != "undefined"){
  		  editor4.sync();
  	  }
  	  if(typeof(editor5) != "undefined"){
  		  editor5.sync();
  	  }
  	  if(typeof(editor6) != "undefined"){
  		  editor6.sync();
  	  }
  	  if(typeof(editor7) != "undefined"){
  		  editor7.sync();
  	  }
  	  if(typeof(editor8) != "undefined"){
  		  editor8.sync();
  	  } */
  	  var data = serializeData();
  	  console.log(data);
		  jQuery.ajax({
				type : "post",
				async: false,
				url : baseUrl + "wechat/items/update",
				data : data,
				dataType : "json",
				success : function(e){
					dialog("提交成功,即将刷新页面...");
					window.location.href=baseUrl+"wechat/items/manage"; 
				}
			});
  	  //jQuery(".am-form").submit();
    });
    //序列化数据
    function serializeData(){
  		//给个默认值
  		var data = "data=data";
  		for (var i = 0; i < jQuery("input[name='replyId']").length; i++) {
  			if(i==jQuery("input[name='replyId']").length-1){
  				data = data+"&replyId="+jQuery("input[name='replyId']").eq(i).val();
  			}else{
  				data = data+"&replyId="+jQuery("input[name='replyId']").eq(i).val()+"@!";
  			}
  		}
  		for (var i = 0; i < jQuery("input[name='media_id']").length; i++) {
  			if(i == jQuery("input[name='media_id']").length-1){
  				data = data+"&media_id="+jQuery("input[name='media_id']").eq(i).val();
  			}else{
  				data = data+"&media_id="+jQuery("input[name='media_id']").eq(i).val()+"@!";;
  			}
  		}
  		for (var i = 0; i < jQuery("input[name='index']").length; i++) {
  			data = data+"&index="+jQuery("input[name='index']").eq(i).val();
  		}
  		for (var i = 0; i < jQuery("input[name='title']").length; i++) {
  			if(i == jQuery("input[name='title']").length-1){
  				data = data+"&title="+jQuery("input[name='title']").eq(i).val();
  			}else{
  				data = data+"&title="+jQuery("input[name='title']").eq(i).val()+"@!";
  			}
  		}
  		for (var i = 0; i < jQuery("input[name='thumb_media_id']").length; i++) {
  			if(i == jQuery("input[name='thumb_media_id']").length-1){
  				data = data+"&thumb_media_id="+jQuery("input[name='thumb_media_id']").eq(i).val();
  			}else{
  				data = data+"&thumb_media_id="+jQuery("input[name='thumb_media_id']").eq(i).val()+"@!";
  			}
  		}
  		for (var i = 0; i < jQuery("input[name='author']").length; i++) {
  			if(i == jQuery("input[name='author']").length-1){
  				data = data+"&author="+jQuery("input[name='author']").eq(i).val();
  			}else{
  				data = data+"&author="+jQuery("input[name='author']").eq(i).val()+"@!";
  			}
  		}
  		for (var i = 0; i < jQuery("textarea[name='digest']").length; i++) {
  			if(i == jQuery("textarea[name='digest']").length-1){
  				data = data+"&digest="+jQuery("textarea[name='digest']").eq(i).val();
  			}else{
  				data = data+"&digest="+jQuery("textarea[name='digest']").eq(i).val()+"@!";
  			}
  		}
  		for (var i = 0; i < jQuery("input[name='content_source_url']").length; i++) {
  			if(i == jQuery("input[name='content_source_url']").length-1){
  				data = data+"&content_source_url="+jQuery("input[name='content_source_url']").eq(i).val();
  			}else{
  				data = data+"&content_source_url="+jQuery("input[name='content_source_url']").eq(i).val()+"@!";
  			}
  		}
  		for (var i = 0; i < jQuery("input[name='picUrl']").length; i++) {
  			if(i == jQuery("input[name='picUrl']").length-1){
  				data = data+"&picUrl="+jQuery("input[name='picUrl']").eq(i).val();
  			}else{
  				data = data+"&picUrl="+jQuery("input[name='picUrl']").eq(i).val()+"@!";
  			}
  		}
  		for (var i = 0; i < jQuery("input[name='qiniuImg']").length; i++) {
  			if(i == jQuery("input[name='qiniuImg']").length-1){
  				data = data+"&qiniuImg="+jQuery("input[name='qiniuImg']").eq(i).val();
  			}else{
  				data = data+"&qiniuImg="+jQuery("input[name='qiniuImg']").eq(i).val()+"@!";
  			}
  		}
  		  /* if(typeof(editor1) != "undefined"){
  			  var c1 = editor1.html();
  			  data = data + "&content="+c1+"@!";
	      	  }
	      	  if(typeof(editor2) != "undefined"){
	      		var c2 = editor2.html();
	      		data = data + "&content="+c2+"@!";
	      	  }
	      	  if(typeof(editor3) != "undefined"){
	      		var c3 = editor3.html();
	      		data = data + "&content="+c3+"@!";
	      	  }
	      	  if(typeof(editor4) != "undefined"){
	      		var c4 = editor4.html();
	      		data = data + "&content="+c4+"@!";
	      	  }
	      	  if(typeof(editor5) != "undefined"){
	      		var c5 = editor5.html();
	      		data = data + "&content="+c5+"@!";
	      	  }
	      	  if(typeof(editor6) != "undefined"){
	      		var c6 = editor6.html();
	      		data = data + "&content="+c6+"@!";
	      	  }
	      	  if(typeof(editor7) != "undefined"){
	      		var c7 = editor7.html();
	      		data = data + "&content="+c7+"@!";
	      	  }
	      	  if(typeof(editor8) != "undefined"){
	      		var c8 = editor8.html();
	      		data = data + "&content="+c8+"@!";
	      	  } */
	      	  if(jQuery("input[name='title']").length == 1){
	      		var c1 = UE.getEditor('editor1').getContent();
	  			c1 = c1.replace(/%/g, "%25");  
	  			c1 = c1.replace(/\&/g, "%26");  
	  			c1 = c1.replace(/\+/g, "%2B"); 
			    data = data + "&content="+c1;
	      	  }
	      	 if(jQuery("input[name='title']").length == 2){
		      		var c1 = UE.getEditor('editor1').getContent();
	    			c1 = c1.replace(/%/g, "%25");  
	    			c1 = c1.replace(/\&/g, "%26");  
	    			c1 = c1.replace(/\+/g, "%2B"); 
	  			    data = data + "&content="+c1+"@!";
	  			    
	  			  var c2 = UE.getEditor('editor2').getContent();
	    			c2 = c2.replace(/%/g, "%25");  
	    			c2 = c2.replace(/\&/g, "%26");  
	    			c2 = c2.replace(/\+/g, "%2B"); 
	  			    data = data + "&content="+c2;
		      }
	      	if(jQuery("input[name='title']").length == 3){
	      		var c1 = UE.getEditor('editor1').getContent();
		  			c1 = c1.replace(/%/g, "%25");  
		  			c1 = c1.replace(/\&/g, "%26");  
		  			c1 = c1.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c1+"@!";
			  var c2 = UE.getEditor('editor2').getContent();
		  			c2 = c2.replace(/%/g, "%25");  
		  			c2 = c2.replace(/\&/g, "%26");  
		  			c2 = c2.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c2+"@!";
			  var c3 = UE.getEditor('editor3').getContent();
					c3 = c3.replace(/%/g, "%25");  
					c3 = c3.replace(/\&/g, "%26");  
					c3 = c3.replace(/\+/g, "%2B"); 
					data = data + "&content="+c3;
	      }
	      	if(jQuery("input[name='title']").length == 4){
	      		var c1 = UE.getEditor('editor1').getContent();
		  			c1 = c1.replace(/%/g, "%25");  
		  			c1 = c1.replace(/\&/g, "%26");  
		  			c1 = c1.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c1+"@!";
			  var c2 = UE.getEditor('editor2').getContent();
		  			c2 = c2.replace(/%/g, "%25");  
		  			c2 = c2.replace(/\&/g, "%26");  
		  			c2 = c2.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c2+"@!";
			  var c3 = UE.getEditor('editor3').getContent();
					c3 = c3.replace(/%/g, "%25");  
					c3 = c3.replace(/\&/g, "%26");  
					c3 = c3.replace(/\+/g, "%2B"); 
					data = data + "&content="+c3+"@!";
			  var c4 = UE.getEditor('editor4').getContent();
					c4 = c4.replace(/%/g, "%25");  
					c4 = c4.replace(/\&/g, "%26");  
					c4 = c4.replace(/\+/g, "%2B"); 
					data = data + "&content="+c4;
	      }
	      	if(jQuery("input[name='title']").length == 5){
	      		var c1 = UE.getEditor('editor1').getContent();
		  			c1 = c1.replace(/%/g, "%25");  
		  			c1 = c1.replace(/\&/g, "%26");  
		  			c1 = c1.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c1+"@!";
			  var c2 = UE.getEditor('editor2').getContent();
		  			c2 = c2.replace(/%/g, "%25");  
		  			c2 = c2.replace(/\&/g, "%26");  
		  			c2 = c2.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c2+"@!";
			  var c3 = UE.getEditor('editor3').getContent();
					c3 = c3.replace(/%/g, "%25");  
					c3 = c3.replace(/\&/g, "%26");  
					c3 = c3.replace(/\+/g, "%2B"); 
					data = data + "&content="+c3+"@!";
			  var c4 = UE.getEditor('editor4').getContent();
					c4 = c4.replace(/%/g, "%25");  
					c4 = c4.replace(/\&/g, "%26");  
					c4 = c4.replace(/\+/g, "%2B"); 
					data = data + "&content="+c4+"@!";
			  var c5 = UE.getEditor('editor5').getContent();
					c5 = c5.replace(/%/g, "%25");  
					c5 = c5.replace(/\&/g, "%26");  
					c5 = c5.replace(/\+/g, "%2B"); 
					data = data + "&content="+c5;
	      }
	      	if(jQuery("input[name='title']").length == 6){
	      		var c1 = UE.getEditor('editor1').getContent();
		  			c1 = c1.replace(/%/g, "%25");  
		  			c1 = c1.replace(/\&/g, "%26");  
		  			c1 = c1.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c1+"@!";
			  var c2 = UE.getEditor('editor2').getContent();
		  			c2 = c2.replace(/%/g, "%25");  
		  			c2 = c2.replace(/\&/g, "%26");  
		  			c2 = c2.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c2+"@!";
			  var c3 = UE.getEditor('editor3').getContent();
					c3 = c3.replace(/%/g, "%25");  
					c3 = c3.replace(/\&/g, "%26");  
					c3 = c3.replace(/\+/g, "%2B"); 
					data = data + "&content="+c3+"@!";
			  var c4 = UE.getEditor('editor4').getContent();
					c4 = c4.replace(/%/g, "%25");  
					c4 = c4.replace(/\&/g, "%26");  
					c4 = c4.replace(/\+/g, "%2B"); 
					data = data + "&content="+c4+"@!";
			  var c5 = UE.getEditor('editor5').getContent();
					c5 = c5.replace(/%/g, "%25");  
					c5 = c5.replace(/\&/g, "%26");  
					c5 = c5.replace(/\+/g, "%2B"); 
					data = data + "&content="+c5+"@!";
			  var c6 = UE.getEditor('editor6').getContent();
					c6 = c6.replace(/%/g, "%25");  
					c6 = c6.replace(/\&/g, "%26");  
					c6 = c6.replace(/\+/g, "%2B"); 
					data = data + "&content="+c6;
	      }
	      	if(jQuery("input[name='title']").length == 7){
	      		var c1 = UE.getEditor('editor1').getContent();
		  			c1 = c1.replace(/%/g, "%25");  
		  			c1 = c1.replace(/\&/g, "%26");  
		  			c1 = c1.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c1+"@!";
			  var c2 = UE.getEditor('editor2').getContent();
		  			c2 = c2.replace(/%/g, "%25");  
		  			c2 = c2.replace(/\&/g, "%26");  
		  			c2 = c2.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c2+"@!";
			  var c3 = UE.getEditor('editor3').getContent();
					c3 = c3.replace(/%/g, "%25");  
					c3 = c3.replace(/\&/g, "%26");  
					c3 = c3.replace(/\+/g, "%2B"); 
					data = data + "&content="+c3+"@!";
			  var c4 = UE.getEditor('editor4').getContent();
					c4 = c4.replace(/%/g, "%25");  
					c4 = c4.replace(/\&/g, "%26");  
					c4 = c4.replace(/\+/g, "%2B"); 
					data = data + "&content="+c4+"@!";
			  var c5 = UE.getEditor('editor5').getContent();
					c5 = c5.replace(/%/g, "%25");  
					c5 = c5.replace(/\&/g, "%26");  
					c5 = c5.replace(/\+/g, "%2B"); 
					data = data + "&content="+c5+"@!";
			  var c6 = UE.getEditor('editor6').getContent();
					c6 = c6.replace(/%/g, "%25");  
					c6 = c6.replace(/\&/g, "%26");  
					c6 = c6.replace(/\+/g, "%2B"); 
					data = data + "&content="+c6+"@!";
			  var c7 = UE.getEditor('editor7').getContent();
					c7 = c7.replace(/%/g, "%25");  
					c7 = c7.replace(/\&/g, "%26");  
					c7 = c7.replace(/\+/g, "%2B"); 
					data = data + "&content="+c7;
	      }	
	      	if(jQuery("input[name='title']").length == 8){
	      		var c1 = UE.getEditor('editor1').getContent();
		  			c1 = c1.replace(/%/g, "%25");  
		  			c1 = c1.replace(/\&/g, "%26");  
		  			c1 = c1.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c1+"@!";
			  var c2 = UE.getEditor('editor2').getContent();
		  			c2 = c2.replace(/%/g, "%25");  
		  			c2 = c2.replace(/\&/g, "%26");  
		  			c2 = c2.replace(/\+/g, "%2B"); 
		  			data = data + "&content="+c2+"@!";
			  var c3 = UE.getEditor('editor3').getContent();
					c3 = c3.replace(/%/g, "%25");  
					c3 = c3.replace(/\&/g, "%26");  
					c3 = c3.replace(/\+/g, "%2B"); 
					data = data + "&content="+c3+"@!";
			  var c4 = UE.getEditor('editor4').getContent();
					c4 = c4.replace(/%/g, "%25");  
					c4 = c4.replace(/\&/g, "%26");  
					c4 = c4.replace(/\+/g, "%2B"); 
					data = data + "&content="+c4+"@!";
			  var c5 = UE.getEditor('editor5').getContent();
					c5 = c5.replace(/%/g, "%25");  
					c5 = c5.replace(/\&/g, "%26");  
					c5 = c5.replace(/\+/g, "%2B"); 
					data = data + "&content="+c5+"@!";
			  var c6 = UE.getEditor('editor6').getContent();
					c6 = c6.replace(/%/g, "%25");  
					c6 = c6.replace(/\&/g, "%26");  
					c6 = c6.replace(/\+/g, "%2B"); 
					data = data + "&content="+c6+"@!";
			  var c7 = UE.getEditor('editor7').getContent();
					c7 = c7.replace(/%/g, "%25");  
					c7 = c7.replace(/\&/g, "%26");  
					c7 = c7.replace(/\+/g, "%2B"); 
					data = data + "&content="+c7+"@!";
			  var c8 = UE.getEditor('editor8').getContent();
					c8 = c8.replace(/%/g, "%25");  
					c8 = c8.replace(/\&/g, "%26");  
					c8 = c8.replace(/\+/g, "%2B"); 
					data = data + "&content="+c8;
	      }
  		/* if(typeof(editor1) != "undefined"){
  			var c1 = UE.getEditor('editor1').getContent();
  			c1 = c1.replace(/%/g, "%25");  
  			c1 = c1.replace(/\&/g, "%26");  
  			c1 = c1.replace(/\+/g, "%2B"); 
			    data = data + "&content="+c1+"@!";
	      	  }
	      	  if(typeof(editor2) != "undefined"){
	      		var c2 = editor2.html();
	      		data = data + "&content="+c2+"@!";
	      	  }
	      	  if(typeof(editor3) != "undefined"){
	      		var c3 = editor3.html();
	      		data = data + "&content="+c3+"@!";
	      	  }
	      	  if(typeof(editor4) != "undefined"){
	      		var c4 = editor4.html();
	      		data = data + "&content="+c4+"@!";
	      	  }
	      	  if(typeof(editor5) != "undefined"){
	      		var c5 = editor5.html();
	      		data = data + "&content="+c5+"@!";
	      	  }
	      	  if(typeof(editor6) != "undefined"){
	      		var c6 = editor6.html();
	      		data = data + "&content="+c6+"@!";
	      	  }
	      	  if(typeof(editor7) != "undefined"){
	      		var c7 = editor7.html();
	      		data = data + "&content="+c7+"@!";
	      	  }
	      	  if(typeof(editor8) != "undefined"){
	      		var c8 = editor8.html();
	      		data = data + "&content="+c8+"@!";
	      	  } */
  		data = data + "&status="+"1" +"&itemNum="+jQuery("input[name='title']").length;
  		return data;
  	}  