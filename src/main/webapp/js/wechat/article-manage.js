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
				console.log(sourceLink);
				jQuery("#showImg1").attr("src", sourceLink).show();
				jQuery("#imgUrl1").val(sourceLink);
				jQuery("#img-one").attr("src", sourceLink);
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
  