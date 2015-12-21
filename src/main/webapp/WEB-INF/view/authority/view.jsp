<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<%
   String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
<body>

<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
      	<%@ include file="/top.jsp" %>
      	<!-- 页面代码 -->
		
		<div class="maincontent">
		    <div class="contentinner">
		        <div class="project-list">
		            <div class="project-list-head">
		                <input type="search" placeholder="搜索" class="search-input"/>
		                <button type="button" class="btn search-button" id="search-member" onclick="searchComboInfo(this);">搜索</button>
		            </div>
		            <div class="project-list-ul">
		            		<div class="project-category">
			                    <div class="project-name" id="deptId1">
			                        <i class="afont iconfont icon-iconfontxialaeps"></i>
			                       	 角色
			                    </div>
			                    <ul class="project-sublist">
			                    	<c:forEach items="${infos }" var="role">
				                    	<li class="project-sublist-content" id="comboId294" onclick="queryRoleInfoById(${role.roleId},this);">${role.roleName}
					                            <span class="fr">
					                                <i class="iconfa-trash project-icon" onclick="deleteComboInfo(294,this);"></i>
					                            </span>
						                </li>
			                    	</c:forEach>
			                    </ul>
			                </div>
			                <div class="gap"></div>
		            </div>
		        </div>
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn" onclick="initAuthority()">新增</button>
		        <div class="project-setting">
			        <table class="table ">
	                   <thead>
	                    <tr>
	                        <th>权限名称</th>
	                        <th>权限链接</th>
	                        <th>
	                        <input type="checkbox" name="quanxuan" value="0" onclick="quanxuan(this)">
	                        </th>
	                        <th>
	                        		修改
	                        </th>
	                        <th>
	                        		删除
	                        </th>
	                    </tr>
	                   </thead>
	                   <input type="hidden" name="goodsName">
	                   <tbody id="goodsSetTbody">
	                   <c:forEach items="${authorityRequests }" var="authority">
	                    <tr>
	                        <td>
	                        	<span>${authority.authorityName }</span>
	                        </td>
	                        <td>
	                        	<span class="url">${authority.authorityUrl }</span>
	                        </td>
	                        <td>
	                        	<input class="${authority.authorityName }" name="authority" type="checkbox">
	                        </td>
	                        <td>
	                        	<i class="iconfa-pencil project-icon" onclick="showEditAuthority(this,${authority.authorityId });"></i>
	                        </td>
	                        <td>
	                        	<i class="iconfa-trash project-icon" onclick="deleteAuthority(this,${authority.authorityId });"></i>
	                        </td>
	                    </tr>
	                   </c:forEach>
	                   </tbody>
	               </table>
		     </div>
		</div>
      	<div class="modal-footer">
             <button type="button" class="btn " onclick="baocun()" data-dismiss="modal">确认</button>
        </div>
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->
<!-- 新增权限 -->
<div class="modal hide in" id="add-authority-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-bumen-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" style="">批量添加系列</h4>
            </div>
            <input type="hidden" name="saveCategoryListDeptId" value="1">
            <div class="modal-body" style="overflow:auto">
            		<div>
		     			<div class="desc-title">
		     				新增权限
		                </div>       
		           	</div>
                    <div>
						<span>权限名称：</span>
                        <input type="text" name="initName" class="mr10 ml10 input-medium">
                        <span>权限链接：</span>
                        <input type="text" name="initUrl" class="mr10 ml10 input-medium">
                    </div>
                    <div class="modal-btn-group">
                        <a class="btn btn-primary modal-confirm w80" href="javascript:addsaveauthority()">保存</a>
                    </div>
                <div class="modal-footer text-left">
                <div class="desc-title">
                    	设置说明
                </div>
                <div class="desc-content">
                    <p>1.现在您可以一次添加多个项目的类别</p>
                </div>
            </div>
            </div><!--modal body-->
        </div>
    </div>
</div>

<!-- 修改权限 -->
<div class="modal hide in" id="edit-authority-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-bumen-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" style="">批量添加系列</h4>
            </div>
            <input type="hidden" name="saveCategoryListDeptId" value="1">
            <div class="modal-body" style="overflow:auto">
            		<div>
		     			<div class="desc-title">
		     				新增权限
		                </div>       
		           	</div>
                    <div>
						<span>权限名称：</span>
                        <input type="text" name="authorityName" class="mr10 ml10 input-medium">
                        <span>权限链接：</span>
                        <input type="text" name="authorityUrl" class="mr10 ml10 input-medium">
                        <input type="hidden" name="authorityId">
                        <input type="hidden" name="oldUrl">
                    </div>
                    <div class="modal-btn-group">
                        <a class="btn btn-primary modal-confirm w80" href="javascript:editsaveauthority()">保存</a>
                    </div>
                <div class="modal-footer text-left">
                <div class="desc-title">
                    	设置说明
                </div>
                <div class="desc-content">
                    <p>1.现在您可以一次添加多个项目的类别</p>
                </div>
            </div>
            </div><!--modal body-->
        </div>
    </div>
</div>
<script type="text/javascript"> 
var role = 0;
function queryRoleInfoById(roleId, obj){
	jQuery("input[name='authority']").removeAttr("checked");
	role = roleId;
	jQuery(".project-sublist-content").removeClass("active");
	jQuery(obj).addClass("active");
	var data = "roleId="+roleId;
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"authority/select",
        data: data,
        dataType: "json",
        success: function(data) {
        	for (var i = 0; i < data.msg.length; i++) {
        		jQuery(".url").each(function(){
        			if (jQuery(this).text() == data.msg[i].authorityUrl){
        				jQuery(this).parent("td").next().children("input").attr("checked","true");
        			}
       		    });
        		//jQuery("."+data.msg[i].authorityName).attr("checked","true");
			}
        }
	});
}
function baocun(){
	if(role == 0){
		dialog("请选择一种角色");
		return;
	}
	var data = "roleId="+role;
	for (var i = 0; i < jQuery("input[name='authority']").length; i++) {
		if(jQuery("input[name='authority']").eq(i).attr("checked")=="checked"){
			data = data + "&authorityName=" + jQuery("input[name='authority']").eq(i).parents("tr").children("td").eq(0).children("span").text();
			data = data + "&authorityUrl=" + jQuery("input[name='authority']").eq(i).parents("tr").children("td").eq(1).children("span").text();
		}
	}
	jQuery.ajax({
		type : "POST",
		url: baseUrl+"authority/save",
        data: data,
        dataType: "json",
        success: function(data) {
        	dialog(data.msg);
        }
	});
}
function quanxuan(obj){
	if(obj.checked == true){
		jQuery("input[name='authority']").attr("checked","true");//全选
	}else{
		jQuery("input[name='authority']").removeAttr("checked");//取消全选
	}
}
jQuery('body').delegate("input[name='authority']", "click", function() {
	if(jQuery(this).attr("checked")=="checked"){
		jQuery(this).removeAttr("checked");
	}else{
		jQuery(this).attr("checked","true");
	}
	
});
//新增 后台接口有一个 使用type进行判断 1:新增  2:修改 3:删除
function initAuthority(){
	jQuery("#add-authority-modal").modal();
}
//新增权限
function addsaveauthority(){
	var authorityName = jQuery("input[name='initName']").val();
	var authorityUrl = jQuery("input[name='initUrl']").val();
	console.log(authorityName);
	console.log(authorityUrl);
	var data = "url="+authorityUrl+"&name="+authorityName+"&type="+1;
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"authority/init",
        data: data,
        dataType: "json",
        success: function(data) {
        	if(data.code==0){
        		dialog(data.msg);
        		location.reload(true);
        	}else{
        		dialog(data.msg);
        		jQuery("#add-authority-modal").modal('hide');
        	}
        }
	});
}

function showEditAuthority(obj,authorityId){
	jQuery("input[name='authorityName']").val(jQuery(obj).parents("tr").children("td").eq(0).children("span").text());
	jQuery("input[name='authorityUrl']").val(jQuery(obj).parents("tr").children("td").eq(1).children("span").text());
	jQuery("input[name='authorityId']").val(authorityId);
	jQuery("input[name='oldUrl']").val(jQuery(obj).parents("tr").children("td").eq(1).children("span").text());
	jQuery("#edit-authority-modal").modal();
}
//修改权限
function editsaveauthority(){
	var authorityName = jQuery("input[name='authorityName']").val();
	var authorityUrl = jQuery("input[name='authorityUrl']").val();
	var authorityId = jQuery("input[name='authorityId']").val();
	var oldUrl = jQuery("input[name='oldUrl']").val();
	var data = "url="+authorityUrl+"&id="+authorityId+"&oldUrl="+oldUrl+"&name="+authorityName+"&type="+2;
	console.log(data);
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"authority/init",
        data: data,
        dataType: "json",
        success: function(data) {
        	if(data.code==0){
        		dialog(data.msg);
        		location.reload(true);
        	}else{
        		dialog(data.msg);
        	}
        }
	});
}
//删除权限
function deleteAuthority(obj,authorityId){
	var data = "id="+authorityId+"&type="+3;
	console.log(data);
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"authority/init",
        data: data,
        dataType: "json",
        success: function(data) {
        	if(data.code==0){
        		dialog(data.msg);
        		location.reload(true);
        	}else{
        		dialog(data.msg);
        	}
        }
	});
}
</script>
</body>
</html>