jQuery(".rating").rating({showCaption : false, step : 0.5});

jQuery(function(){
	//将已经选择的员工打上标记
	var ea = jQuery(".employeeSelected li");
    for (var i = 0; i < ea.length; i++) {
        jQuery(".employeeAll li[employeeId='" + jQuery(ea[i]).attr("employeeId") + "']").addClass("p-selected");
    }
});

//设置项切换
jQuery(".setting_option").click(function(){
    jQuery(".shop-edit-area").addClass("hide").removeClass("active");
    jQuery("." + jQuery(this).attr("name")).removeClass("hide").addClass("active");
    jQuery(".setting_option").removeClass("active");
    jQuery(this).addClass("active");
});

//选择名师的操作控制
jQuery(".employeeAll li").on("click",function(){
    var employeeId = jQuery(this).attr("employeeId");
    var tmp = jQuery(".employeeSelected [employeeId='" + employeeId + "']");
    if (!isEmpty(tmp) && tmp.length > 0) {
        tmp[0].remove();
        jQuery(this).removeClass("p-selected");
    } else {
        var se = jQuery(this).prop('outerHTML');
        jQuery(".employeeSelected").prepend(se);
        jQuery(".employeeSelected [employeeId='" + employeeId + "']").prepend('<div class="shanchu-icon"><span class="iconfont icon-shanchujilu"></span></div>');
        jQuery(this).addClass("p-selected");
    }
});

//移除选择的名师
jQuery(".yixuanze").delegate(".shanchu-icon", "click", function(e){
    var se = jQuery(this).parent();
    jQuery("[employeeId='" + se.attr("employeeId") + "']").removeClass("p-selected");
    se.remove();
});

//移除轮播图片
jQuery(".edit-img").delegate(".icon-guanbi", "click", function(e){
    jQuery(this).parent().remove();
});

//提交名师信息
function saveEmployee(){
    var ea = jQuery(".employeeSelected li");
    if(ea == null || ea.length == 0){
        dialog("请选择需要展示的名师");
        return;
    }
    var teacherIntroduction = "";
    for (var i = 0; i < ea.length; i++) {
    	teacherIntroduction += jQuery(ea[i]).attr("employeeId") + ",";
	}
    teacherIntroduction = teacherIntroduction.substring(0, teacherIntroduction.length - 1);
    var data = "teacherIntroduction=" + teacherIntroduction;
    submit(data, "保存成功，已更新您的名师信息");
}

//提交店铺信息
function saveStoreInfo(){
	var storeLogo = jQuery("#storeLogo").val();
	var storeName = jQuery("#storeName").val();
	var storeTel = jQuery("#storeTel").val();
	var storeAddress = jQuery("#storeAddress").val();
	var storeLinkname = jQuery("#storeLinkname").val();
	var storeLinkphone = jQuery("#storeLinkphone").val();
	if (isEmpty(storeLogo)) {
        dialog("请上传您的店铺Logo");
        return;
    }
	if (isEmpty(storeName)) {
		dialog("请填写您的店铺名称");
		return;
	}
	if (isEmpty(storeAddress)) {
		dialog("请填写您的店铺地址");
		return;
	}
	if (isEmpty(storeTel)) {
        dialog("请填写您的店铺电话");
        return;
    }
	if (isEmpty(storeLinkname)) {
        dialog("请填写您的店铺负责人姓名");
        return;
    }
	if (isEmpty(storeLinkphone)) {
        dialog("请填写您的店铺负责人电话");
        return;
    }
	var data = "storeLogo=" + storeLogo + "&storeName=" + storeName + "&storeTel=" + storeTel + "&storeAddress=" + storeAddress
		+ "&storeLinkname=" + storeLinkname + "&storeLinkphone=" + storeLinkphone;
	submit(data, "保存成功，已更新您的店铺信息");
}

//提交店铺轮播图片
function saveCarousel(){
	var carouselList = jQuery("[name='carouselPicture']");
	if(carouselList == null || carouselList.length == 0){
        dialog("请至少上传一张店铺照片");
        return;
    }
	carousel = "";
	for (var i = 0; i < carouselList.length; i++) {
		carousel += jQuery(carouselList[i]).val() + ",";
	}
	carousel = carousel.substring(0, carousel.length - 1);
	var data = "carouselPicture=" + carousel;
	submit(data, "保存成功，已更新您的店铺信息");
}

//提交门店介绍
function saveDescription(){
	var content = editorDescription.getContent();
	content = content.replace(/%/g, "%25");  
	content = content.replace(/\&/g, "%26");  
	content = content.replace(/\+/g, "%2B");
	console.log(content);
	if(content == null || content.length == 0){
        dialog("请为您的门店介绍添加内容");
        return;
    }
	var data = "storeDesc=" + content;
	submit(data, "保存成功，已更新您的店铺信息");
}

//提交技术展示
function saveTechnical(){
	var content = editorTechnical.getContent();
	content = content.replace(/%/g, "%25");  
	content = content.replace(/\&/g, "%26");  
	content = content.replace(/\+/g, "%2B");
	if(content == null || content.length == 0){
        dialog("请为您门店的技术展示添加内容");
        return;
    }
	var data = "technical=" + content;
	submit(data, "保存成功，已更新您的店铺信息");
}

//提交特色服务
function saveCharacteristic(){
	var content = editorCharacteristic.getContent();
	content = content.replace(/%/g, "%25");  
	content = content.replace(/\&/g, "%26");  
	content = content.replace(/\+/g, "%2B");
	if(content == null || content.length == 0){
        dialog("请为您门店的特色服务添加内容");
        return;
    }
	var data = "characteristic=" + content;
	submit(data, "保存成功，已更新您的店铺信息");
}

//数据提交
function submit(data, msg){
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/storeSetting",
        data: data,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(e.msg);
                return;
            }
        	dialog(msg);
        }
    });
}

var toolbars = [['fullscreen', 'source', '|', 'undo', 'redo', '|',
                   'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript','|', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                   'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                   'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                   'directionalityltr', 'directionalityrtl', 'indent', '|',
                   'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase','preview'
            ]];
           
var editorDescription = UE.getEditor('editorDescription', 
		{toolbars : toolbars, 
    autoHeightEnabled: false, 
    autoFloatEnabled: false,
    elementPathEnabled : false,
    wordCount : false,
    initialFrameWidth : 600,
    initialFrameHeight : 500,
    zIndex: 10000});

var editorTechnical = UE.getEditor('editorTechnical', 
		{toolbars : toolbars, 
    autoHeightEnabled: false, 
    autoFloatEnabled: false,
    elementPathEnabled : false,
    wordCount : false,
    initialFrameWidth : 600,
    initialFrameHeight : 500,
    zIndex: 10000});

var editorCharacteristic = UE.getEditor('editorCharacteristic', 
		{toolbars : toolbars, 
    autoHeightEnabled: false, 
    autoFloatEnabled: false,
    elementPathEnabled : false,
    wordCount : false,
    initialFrameWidth : 600,
    initialFrameHeight : 500,
    zIndex: 10000});

/**店铺介绍图片上传的处理*/
new QiniuJsSDK().uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'descriptionImg',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl + 'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'descriptionImgContent',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
    max_retries : 3, //上传失败最大重试次数
    dragdrop : true, //开启可拖曳上传
    drop_element : 'descriptionImgContent', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
                jQuery("#loadingWrap").fadeOut();
            }
        },
        'BeforeUpload' : function(up, file) {
            jQuery("#loadingWrap").fadeIn();
            console.log("BeforeUpload invoked ...");
        },
        'UploadProgress' : function(up, file) {
            console.log("UploadProgress invoked ...");
        },
        'FileUploaded' : function(up, file, info) {
            console.log("FileUploaded invoked ...");
            var domain = up.getOption('domain');
            var res = eval('(' + info + ')');
            var sourceLink = domain + res.key;
            editorDescription.execCommand('insertHtml', '<img src="'+sourceLink+'">');
        },
        'Error' : function(up, err, errTip) {
            dialog(errTip);
            jQuery("#loadingWrap").fadeOut();
        },
        'UploadComplete' : function() {
            console.log("UploadComplete invoked ...");
            jQuery("#loadingWrap").fadeOut();
        },
        'Key' : function(up, file) {
            console.log("Key invoked ...");
            var key = "zefun/storelogo/" + new Date().getTime();
            return key;
        }
    }
});

/**技术展示图片上传的处理*/
new QiniuJsSDK().uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'technicalImg',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl + 'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'technicalImgContent',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
    max_retries : 3, //上传失败最大重试次数
    dragdrop : true, //开启可拖曳上传
    drop_element : 'technicalImg', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
                jQuery("#loadingWrap").fadeOut();
            }
        },
        'BeforeUpload' : function(up, file) {
            jQuery("#loadingWrap").fadeIn();
            console.log("BeforeUpload invoked ...");
        },
        'UploadProgress' : function(up, file) {
            console.log("UploadProgress invoked ...");
        },
        'FileUploaded' : function(up, file, info) {
            console.log("FileUploaded invoked ...");
            var domain = up.getOption('domain');
            var res = eval('(' + info + ')');
            var sourceLink = domain + res.key;
            editorTechnical.execCommand('insertHtml', '<img src="'+sourceLink+'">');
        },
        'Error' : function(up, err, errTip) {
            dialog(errTip);
            jQuery("#loadingWrap").fadeOut();
        },
        'UploadComplete' : function() {
            console.log("UploadComplete invoked ...");
            jQuery("#loadingWrap").fadeOut();
        },
        'Key' : function(up, file) {
            console.log("Key invoked ...");
            var key = "zefun/storelogo/" + new Date().getTime();
            return key;
        }
    }
});

/**特色服务图片上传的处理*/
new QiniuJsSDK().uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'characteristicImg',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl + 'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'characteristicImgContent',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
    max_retries : 3, //上传失败最大重试次数
    dragdrop : true, //开启可拖曳上传
    drop_element : 'characteristicImgContent', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
                jQuery("#loadingWrap").fadeOut();
            }
        },
        'BeforeUpload' : function(up, file) {
            jQuery("#loadingWrap").fadeIn();
            console.log("BeforeUpload invoked ...");
        },
        'UploadProgress' : function(up, file) {
            console.log("UploadProgress invoked ...");
        },
        'FileUploaded' : function(up, file, info) {
            console.log("FileUploaded invoked ...");
            var domain = up.getOption('domain');
            var res = eval('(' + info + ')');
            var sourceLink = domain + res.key;
            editorCharacteristic.execCommand('insertHtml', '<img src="'+sourceLink+'">');
        },
        'Error' : function(up, err, errTip) {
            dialog(errTip);
            jQuery("#loadingWrap").fadeOut();
        },
        'UploadComplete' : function() {
            console.log("UploadComplete invoked ...");
            jQuery("#loadingWrap").fadeOut();
        },
        'Key' : function(up, file) {
            console.log("Key invoked ...");
            var key = "zefun/storelogo/" + new Date().getTime();
            return key;
        }
    }
});

/**店铺logo图片*/
new QiniuJsSDK().uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'storeLogoButton',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl + 'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'storeLogoContent',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
    max_retries : 3, //上传失败最大重试次数
    dragdrop : true, //开启可拖曳上传
    drop_element : 'carousel-father', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
                jQuery("#loadingWrap").fadeOut();
            }
        },
        'BeforeUpload' : function(up, file) {
            jQuery("#loadingWrap").fadeIn();
            console.log("BeforeUpload invoked ...");
        },
        'UploadProgress' : function(up, file) {
            console.log("UploadProgress invoked ...");
        },
        'FileUploaded' : function(up, file, info) {
            console.log("FileUploaded invoked ...");
            var domain = up.getOption('domain');
            var res = eval('(' + info + ')');
            var sourceLink = domain + res.key + "?imageView2/1/w/400/h/400";
            jQuery("#storeLogoImg").attr("src", sourceLink);
            jQuery("#storeLogo").val(res.key);
        },
        'Error' : function(up, err, errTip) {
            dialog(errTip);
            jQuery("#loadingWrap").fadeOut();
        },
        'UploadComplete' : function() {
            console.log("UploadComplete invoked ...");
            jQuery("#loadingWrap").fadeOut();
        },
        'Key' : function(up, file) {
            console.log("Key invoked ...");
            var key = "zefun/storelogo/" + new Date().getTime();
            return key;
        }
    }
});

/**加入裁剪*/
/*var  $image, cropBoxData, canvasData;
var options = {
		  aspectRatio: 1 / 1,
	      minContainerWidth: 1000,
	      minContainerHeight: 580,
	      minCanvasHeight:580,
	      minCropBoxWidth: 580,
	      minCropBoxHeight: 580,
	      width:580,
	      height:580,
	      movable: true,
	      responsive:false,
	      dragCrop: false,
	      cropBoxMovable: false,
	      cropBoxResizable: false,
	      viewMode: 1,
	      dragMode: 'move'
	};

var $image = jQuery('.crop-container > img');
$image.cropper(options);
function cavsen(obj){
  jQuery("#jietu").modal(); 
  
  启用截图
  var $inputImage = jQuery('.inputfile');
  var URL = window.URL || window.webkitURL;
  var blobURL;

  if (URL) {
    $inputImage.change(function () {
      var files = this.files;
      var file;

      if (!$image.data('cropper')) {
        return;
      }

      if (files && files.length) {
        file = files[0];

        if (/^image\/\w+$/.test(file.type)) {
            blobURL = URL.createObjectURL(file);
            $image.one('built.cropper', function () {
              // Revoke when load complete
              URL.revokeObjectURL(blobURL);
            }).cropper('reset').cropper('replace', blobURL);
            $inputImage.val('');
          } else {
            window.alert('请选择一张图片');
          }
      }
    });
  } else {
    $inputImage.prop('disabled', true).parent().addClass('disabled');
  }
  jQuery(".btn.save").one("click", function () {
	  var result = $image.cropper('getCroppedCanvas', options);
	  var base64Str = result.toDataURL("image/png");
	  var key = "zefun/project/" + new Date().getTime();
	  
	  var sourceLink = domain + key + "?imageView2/1/w/400/h/400";
      var html = '<div class="img-wrap">'+
                      '<span class="iconfont icon-guanbi"></span>'+
                      '<img src="' + sourceLink + '">'+
                      '<input type="hidden" name="carouselPicture" value="'+res.key+'">'+
                  '</div>';
      jQuery("#carousel-father").before(html);
	  
	  objImage.find("img[name='headImg']").attr("src", base64Str);
	  objImage.find("input[name='goodsImage']").val(key);
	  objImage.find("img[name='affiliatedHeadImg']").attr("src", base64Str);
	  objImage.find("input[name='affiliatedImage']").val(key);
	  
	  var data = "stringBase64=" + encodeURIComponent(base64Str) + "&key=" + key;
	  jQuery("#jietu").modal('hide');
	  jQuery.ajax({
			type: "POST",
			url: baseUrl+"qiniu/base64",
		       data: data,
		       contentType: "application/x-www-form-urlencoded",
		       dataType: "json",
		       async:true,  
		       beforeSend:function(){
		       	console.log("beforeSend upload image");
		       },
		       error:function (){
		       	console.log("upload image error");
		       },
		       complete :function (){
		       	console.log("complete upload image");
		       },
		       success: function(data) {
		       	var imageUrl = data.msg.imageUrl;
		       	var key = data.msg.key;
	        	 objImage.find("img[name='headImg']").attr("src",imageUrl); 
	        	objImage.find("input[name='comboImage']").val(key); 
		       }
     });
});
}

jQuery('#jietu').on('hidden.bs.modal', function () {
      cropBoxData = $image.cropper('getCropBoxData');
      canvasData = $image.cropper('getCanvasData');
      $image.cropper('clear').cropper('reset');
      jQuery(".btn.save").unbind("click");
});*/

/**七牛上传图片，一定要将这个js放在自定义按钮的内容实现*/
new QiniuJsSDK().uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'carousel',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl + 'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'carousel-father',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
    max_retries : 3, //上传失败最大重试次数
    dragdrop : true, //开启可拖曳上传
    drop_element : 'carousel-father', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
                jQuery("#loadingWrap").fadeOut();
            }
        },
        'BeforeUpload' : function(up, file) {
        	jQuery("#loadingWrap").fadeIn();
            console.log("BeforeUpload invoked ...");
        },
        'UploadProgress' : function(up, file) {
            console.log("UploadProgress invoked ...");
        },
        'FileUploaded' : function(up, file, info) {
            console.log("FileUploaded invoked ...");
            var domain = up.getOption('domain');
            var res = eval('(' + info + ')');
            var sourceLink = domain + res.key + "?imageView2/1/w/400/h/400";
            var html = '<div class="img-wrap">'+
                            '<span class="iconfont icon-guanbi"></span>'+
                            '<img src="' + sourceLink + '">'+
                            '<input type="hidden" name="carouselPicture" value="'+res.key+'">'+
                        '</div>';
            jQuery("#carousel-father").before(html);
        },
        'Error' : function(up, err, errTip) {
            dialog(errTip);
            jQuery("#loadingWrap").fadeOut();
        },
        'UploadComplete' : function() {
            console.log("UploadComplete invoked ...");
            jQuery("#loadingWrap").fadeOut();
        },
        'Key' : function(up, file) {
            console.log("Key invoked ...");
            var key = "zefun/wechat/" + new Date().getTime();
            return key;
        }
    }
});