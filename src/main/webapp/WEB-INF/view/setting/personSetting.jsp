<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
 <style>
    .hide{
        display: none!important;
    }
</style>
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
        <div class="maincontent">
		    <div class="contentinner">
		        <div class="border-head">
		            <span>账户信息</span>
		        </div>
		        <form id="employeeInfoForm">
			        <div class="border-content">
			            <table class="table nobordered-table">
			                <tr>
			                    <td class="text-right fb">头像:</td>
			                    <td>
			                          <img id="headImg" src="<%=picPath %>${session_key_user_info.headImage }?imageView2/1/w/120/h/120" class="person-img"/>
			                          <input type="hidden" name="headImage" value="${session_key_user_info.headImage }" />
			                    </td>
			                </tr>
			                <tr>
			                    <td class="text-right fb">姓名:</td>
			                    <td><input type="text" name="name" value="${session_key_user_info.name }" class="name"/></td>
			                </tr>
			                <tr>
			                    <td class="text-right fb">性别:</td>
			                    <td><input type="radio" name="sex" value="男"/> <span class="ml5">男</span> <input type="radio" name="sex" value="女" class="ml30"/> <span class="ml5">女</span></td>
			                </tr>
			                <tr>
			                    <td class="text-right fb">登录密码:</td>
			                    <td>****** <span class="can-click" onclick="showUpdatePassword()" data-target="#fix-password" data-toggle="modal">修改密码</span></td>
			                </tr>
			                <tr>
	                            <td class="text-right fb"><a class="btn btn-primary" href="javascript:save();">保存</a></td>
	                            <td></td>
	                        </tr>
	                        <!-- <tr>
                                <td class="text-right fb"><a class="btn btn-primary" href="javascript:testVoice(1);">男声语音测试</a></td>
                                <td><a class="btn btn-primary" href="javascript:testVoice(0);">女声语音测试</a></td>
                            </tr> -->
			            </table>
			        </div>
		        </form>
		    </div>
		</div>
		
		<!--修改密码模态框-->
		<div class="modal hide" id="fix-password" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content fix-password-modal">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
		            </div>
		            <div class="modal-body">
		                <form action="" class="editprofileform" method="post">
		                    <p>
		                        <label for="origin-pass">原密码</label>
		                        <input type="password" id="oldPwd"/>
		                    </p>
		                    <p>
		                        <label for="current-pass">新密码</label>
		                        <input type="password" id="newPwd"/>
		                    </p>
		                    <p>
                                <label for="current-pass">确认密码</label>
                                <input type="password" id="repeatPwd"/>
                            </p>
		                </form>
		            </div><!--modal body-->
		            <div class="modal-footer">
		                <a class="btn modal-cancel" data-dismiss="modal" href="javascript:void();">取消</a>
		                <a class="btn btn-primary" href="javascript:updatePassword();">保存</a>
		            </div>
		        </div>
		    </div>
		</div>
        <!--RIGHT PANEL结束 -->
        <div class="clearfix"></div>

        <div id="star"></div>
    </div>
</div>

<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<script type="text/javascript">
jQuery(function(){
	jQuery("[name='sex'][value='${session_key_user_info.sex}']").attr('checked',true);
});

var audio = null;
function testVoice(per){
	jQuery.ajax({
	    url : baseUrl + "qiniu/textToVoice",
	    type : "POST",
	    data : "text=感谢您选择智放系统&per=" + per,
	    beforeSend : function(){
	    },
	    complete : function(){
	    },
	    success : function(e){
	    	if(e.code != 0){
	    		dialog("出了点小问题，要不重试一次?");
	    		return;
	    	}
	    	if(audio != null) audio.pause();
	    	
            audio = new Audio();
            audio.src = picUrl + e.msg;
            audio.play();
	    }
	});
}

function showUpdatePassword(){
	jQuery("#oldPwd").val('');
    jQuery("#newPwd").val('');
    jQuery("#repeatPwd").val('');
	jQuery("#fix-password").removeClass("hide");
}

function hideUpdatePassword(){
	jQuery("#fix-password").addClass("hide");
}

function updatePassword(){
	var oldPwd = jQuery("#oldPwd").val();
	var newPwd = jQuery("#newPwd").val();
	var repeatPwd = jQuery("#repeatPwd").val();
	
	if (isEmpty(oldPwd)) {
		dialog("请输入您的原密码");
		return;
	}
	
	if (isEmpty(newPwd)) {
        dialog("请输入您的新密码");
        return;
    }
	
	if (newPwd != repeatPwd) {
        dialog("两次密码不一致");
        return;
    }
	
	oldPwd = CryptoJS.MD5(CryptoJS.MD5(oldPwd).toString().toUpperCase()).toString().toUpperCase();
	newPwd = CryptoJS.MD5(CryptoJS.MD5(newPwd).toString().toUpperCase()).toString().toUpperCase();
	jQuery.ajax({
        url : baseUrl + "system/action/updatePwd",
        type : "POST",
        data : "oldPwd=" + oldPwd + "&newPwd=" + newPwd,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("密码修改成功");
            hideUpdatePassword();
        }
    });
}

function save(){
	var data = jQuery("#employeeInfoForm").serialize();
	jQuery.ajax({
		url : baseUrl + "system/action/person",
		type : "POST",
		data : data,
		success : function(e){
			if (e.code != 0) {
                dialog(e.msg);
                return;
            }
			dialog("更新成功");
		}
	});
}

/**七牛上传图片，一定要将这个js放在自定义按钮的内容实现*/
var qiniu = new QiniuJsSDK();
qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'headImg',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl + 'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'employeeInfoForm',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
    max_retries : 3, //上传失败最大重试次数
    dragdrop : true, //开启可拖曳上传
    drop_element : 'employeeInfoForm', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
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
            var sourceLink = domain + res.key + "?imageView2/1/w/120/h/120";
            jQuery("#headImg").attr("src", sourceLink);
            jQuery("[name='headImage']").val(res.key);
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
            var key = "zefun/employee/" + new Date().getTime();
            return key;
        }
    }
});
</script>
</body>
</html>