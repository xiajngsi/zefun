<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String memberBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/";
String memberPicPath = "7xkv8r.com1.z0.glb.clouddn.com";
%>
<!--会员资料模态框-->
<div class="modal hide" id="member-data" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-data">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">会员资料</h4>
            </div>
            <div class="modal-body">
                <!--modal-body-->
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->

<script type="text/javascript" src="<%=memberBasePath %>js/member/memberData.js"></script>