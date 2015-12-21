jQuery(function(){
	
	/*发型的展示与隐藏*/
    jQuery('.project-name').on("click", function(){
        var th = jQuery(this)
        var target = th.siblings();
        target.toggle();
    });
    
    //富文本按钮
    var items = [
        'source', '|', 'undo', 'redo', '|', 'cut', 'copy', 'paste',
        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','|','picture',
        'insertorderedlist','insertunorderedlist','fontsize','forecolor','hilitecolor','bold'
    ];
    var editor5 ;
    KindEditor.ready(function(K) {
        editor5 = K.create('#hairstyleContent', {
            width:'430px',
            minHeight:300,
            minWidth:580,
            items:items
        });
    });
	
    
    /**产品选择 start*/
    jQuery(".file-box-product").on("click",function(){
    	jQuery(".file-box-product").find("input[name='click']").val(0);
    	jQuery(this).find("input[name='click']").val(1);
    });
    
    jQuery(".photo-item").on("click",function(){
        /*点击别的,复原以前的*/
        jQuery(".photo-item").children(".appmsg_mask").hide();
        jQuery(".photo-item").children(".icon_card_selected").hide();
        var picItem = jQuery(this);
        picItem.children(".appmsg_mask").show();
        picItem.children(".icon_card_selected").show();
        var goodsId = picItem.find("input[name='goodsId']").val();
        var src = picItem.find("img").eq(0).attr("src");
        
        jQuery("#confirm-product").one("click",function(){
        	var divItem = jQuery(".file-box-product").find("input[name='click'][value="+1+"]");
        	divItem.next().attr("src",src);
        	divItem.next().next().val(goodsId);
        });
    });
    /**产品选择 end*/
    
    /**项目选择 start*/
    jQuery(".file-box-project").on("click",function(){
    	jQuery(".file-box-project").find("input[name='click']").val(0);
    	jQuery(this).find("input[name='click']").val(1);
    });
    jQuery(".photo-item").on("click",function(){
        /*点击别的,复原以前的*/
        jQuery(".photo-item").children(".appmsg_mask").hide();
        jQuery(".photo-item").children(".icon_card_selected").hide();
        var picItem = jQuery(this);
        picItem.children(".appmsg_mask").show();
        picItem.children(".icon_card_selected").show();
        var projectId = picItem.find("input[name='projectId']").val();
        var src = picItem.find("img").eq(0).attr("src");
        console.log("projectId:"+projectId);
        jQuery("#confirm-project").one("click",function(){
        	var divItem = jQuery(".file-box-project").find("input[name='click'][value="+1+"]");
        	divItem.next().attr("src",src);
        	divItem.next().next().val(projectId);
        });
    });
    /**项目选择 end*/
});

/**添加发型类别*/
function addHairstyleCategory(obj){
	stopBubble(obj);
	jQuery("#addHairstyleCategoryLi").show();
}

/**删除发型类别*/
function deleteHairstyleCategory(obj){
	jQuery("#addHairstyleCategoryLi").hide();
}

/**动态删除发型类别*/
function deleteCategory(hairstyleCategoryId){
	jQuery.ajax({
		url: baseUrl+"hairstyleDesign/deleteHairstyleCategory",
        data: "hairstyleCategoryId="+hairstyleCategoryId,
        success: function(data) {
            if(data.code == 0){
            	window.location.href = baseUrl+"hairstyleDesign/view/toHairstyleDesign";
            }else{
            	dialog("error");
            }
        }
	});
}

/**显示编辑*/
function showEditCategory(obj){
	jQuery(obj).parent().parent().hide();
	jQuery(obj).parent().parent().siblings().show();
}

/**取消编辑*/
function cancelEditCategory(obj){
	jQuery(obj).parent().hide();
	jQuery(obj).parent().prev().show();
}

/**编辑类别*/
function editCategory(hairstyleCategoryId){
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"hairstyleDesign/editHairstyleCategory",
        data: "hairstyleCategoryId="+hairstyleCategoryId + "&hairstyleCategoryName="+jQuery("#inputHairstyleCategoryName").val(),
        success: function(data) {
            if(data.code == 0){
            	window.location.href = baseUrl+"hairstyleDesign/view/toHairstyleDesign";
            }else{
            	dialog("error");
            }
        }
	});
}

/**保存发型类别*/
function saveHairstyleCategory(){
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"hairstyleDesign/saveHairstyleCategory",
        data: "hairstyleCategoryName=" + jQuery("#hairstyleCategoryName").val(),
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	window.location.href = baseUrl+"hairstyleDesign/view/toHairstyleDesign";
            }else{
            	dialog("没有权限修改该项目，请联系总店");
            }
        }
    });
}


function queryHairstyleDesignById(hairstyleId,obj){
	jQuery(".project-selected").removeClass("project-selected");
    jQuery(obj).addClass("project-selected");
    
    jQuery.ajax({
        cache: true,
        type: "GET",
        async: false,
        url: baseUrl+"hairstyleDesign/queryHairstyleDesignById?hairstyleId="+hairstyleId,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
        	var map = data.msg;
        	var hairstyleDesign = map["hairstyleDesign"];
        	jQuery("#hairstyleId").val(hairstyleDesign.hairstyleId);
        	jQuery("#hairstyleTitle").val(hairstyleDesign.hairstyleTitle);
        	jQuery("#hairstyleContent").val(hairstyleDesign.hairstyleContent);
        }
    });
}

/** 保存发型设置 */
function saveHairstyleDesign(){
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"hairstyleDesign/saveHairstyleDesign",
        data: jQuery("#hairstyleDesignForm").serialize(),
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	window.location.href = baseUrl+"hairstyleDesign/view/toHairstyleDesign";
            }else{
            	dialog("error");
            }
        }
    });
}



/** *************************图片上传************************* */
var qiniu = new QiniuJsSDK();

qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'hairStyleHeadImg',       //上传选择的点选按钮，**必需**
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
			jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery("img[name='headImg']").attr("src",sourceLink);
			jQuery("input[name='hairstyleCover']").val(res.key);
			jQuery('#mask').remove();
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
			jQuery('#mask').remove();
		},
		'UploadComplete' : function() {
			//dialog("UploadComplete");
			jQuery('#mask').remove();
		},
		'Key' : function(up, file) {
			var key = "zefun/idCard/" + new Date().getTime();
			return key;
		}
	}
});

qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'affiliatedHeadImg1',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'container-affiliatedImg1',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
	max_retries : 3, //上传失败最大重试次数
	dragdrop : true, //开启可拖曳上传
	drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
	chunk_size : '2mb', //分块上传时，每片的体积
	auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
	init : {
		'FilesAdded' : function(up, files) {
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
			jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery("img[name='affiliatedImg1']").attr("src",sourceLink);
			jQuery("#container-affiliatedImg1").find("input[name='affiliatedImg']").val(res.key);
			jQuery('#mask').remove();
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
			jQuery('#mask').remove();
		},
		'UploadComplete' : function() {
			//dialog("UploadComplete");
			jQuery('#mask').remove();
		},
		'Key' : function(up, file) {
			var key = "zefun/idCard/" + new Date().getTime();
			return key;
		}
	}
});

qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'affiliatedHeadImg2',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'container-affiliatedImg2',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
	max_retries : 3, //上传失败最大重试次数
	dragdrop : true, //开启可拖曳上传
	drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
	chunk_size : '2mb', //分块上传时，每片的体积
	auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
	init : {
		'FilesAdded' : function(up, files) {
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
			jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery("img[name='affiliatedImg2']").attr("src",sourceLink);
			jQuery("#container-affiliatedImg2").find("input[name='affiliatedImg']").val(res.key);
			jQuery('#mask').remove();
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
			jQuery('#mask').remove();
		},
		'UploadComplete' : function() {
			//dialog("UploadComplete");
			jQuery('#mask').remove();
		},
		'Key' : function(up, file) {
			var key = "zefun/idCard/" + new Date().getTime();
			return key;
		}
	}
});

qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'affiliatedHeadImg3',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'container-affiliatedImg3',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
	max_retries : 3, //上传失败最大重试次数
	dragdrop : true, //开启可拖曳上传
	drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
	chunk_size : '2mb', //分块上传时，每片的体积
	auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
	init : {
		'FilesAdded' : function(up, files) {
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
			jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery("img[name='affiliatedImg3']").attr("src",sourceLink);
			jQuery("#container-affiliatedImg3").find("input[name='affiliatedImg']").val(res.key);
			jQuery('#mask').remove();
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
			jQuery('#mask').remove();
		},
		'UploadComplete' : function() {
			//dialog("UploadComplete");
			jQuery('#mask').remove();
		},
		'Key' : function(up, file) {
			var key = "zefun/idCard/" + new Date().getTime();
			return key;
		}
	}
});

qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'affiliatedHeadImg4',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'container-affiliatedImg4',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
	max_retries : 3, //上传失败最大重试次数
	dragdrop : true, //开启可拖曳上传
	drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
	chunk_size : '2mb', //分块上传时，每片的体积
	auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
	init : {
		'FilesAdded' : function(up, files) {
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
			jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery("img[name='affiliatedImg4']").attr("src",sourceLink);
			jQuery("#container-affiliatedImg4").find("input[name='affiliatedImg']").val(res.key);
			jQuery('#mask').remove();
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
			jQuery('#mask').remove();
		},
		'UploadComplete' : function() {
			//dialog("UploadComplete");
			jQuery('#mask').remove();
		},
		'Key' : function(up, file) {
			var key = "zefun/idCard/" + new Date().getTime();
			return key;
		}
	}
});