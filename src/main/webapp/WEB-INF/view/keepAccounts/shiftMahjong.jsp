<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<body>

  <div class="mainwrapper">
   <!--loading start-->
   <%@ include file="/loading.jsp" %>
    <!--loading end-->
   <%@ include file="/menu.jsp" %>
   <div class="rightpanel" style="margin-left: 200px;">
      <%@ include file="/top.jsp" %>
      <style>
	    .bx-wrapper{
	        width: 100%;
	        margin: 0px;
	       /* max-width: 1000px !important;*/
	    }
	</style>
	<div class="maincontent">
	    <div class="contentinner">
	       <!--轮牌部门-->
	       <div class="lunpai-head">
	            <ul class="lunpai-tab fl">
	                <c:forEach items="${deptList}" var="dept" varStatus="status">
	                    <c:if test="${status.index == 0}">
	                        <li class="tab active" onclick="updateDept(this,${dept.deptId})">
			                    <span>${dept.deptName}</span>
			                </li>
	                    </c:if>
	                    <c:if test="${status.index != 0}">
	                        <li class="tab" onclick="updateDept(this,${dept.deptId})">
			                    <span>${dept.deptName}</span>
			                </li>
	                    </c:if>
	                </c:forEach>
	            </ul>
	
	            <div class="add-paiwei-btn fr " data-target="#rotating-setting-modal" data-toggle="modal" id="shiftModel">
	                <i class="iconfa-plus"></i>
	                <span class="ml10">新增排位</span>
	            </div>
	       </div>
	
	        <div class="clearfix"></div>
	
	        <!--动态轮牌内容-->
	
	        <div class="lunpai-dynamic" id = "lunpaiDIV">
	
	        </div><!--lunpai-dynamic-->
	    </div><!--contentinner-->
	</div><!--maincontent-->
	
	
	<!--轮牌设置-->
	<div class="modal hide" id="rotating-setting-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content rotating-setting-modal" style="width: 750px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h5 class="modal-title" id="myModalLabel">轮牌设置</h5>
	            </div>
	            <div class="modal-body">
	                <form action="" class="editprofileform" method="post" style="padding-bottom: 42px;">
	                    <table class="table">
	                      <tbody id = "modelTbody">
	                        <tr>
	                          <td colspan="4">轮牌名称: <input type="text" name="shiftMahjongName" placeholder="轮牌名称"/></td>
	                        </tr>
	                        <tr>
	                            <td>轮牌规则:</td>
	                            <td>
	                              <div class="ch-checker fl">
	                                <div class="beau-checker">
	                                  <span class="iconfont icon-gou"></span>
	                                </div>
	                                <input type="radio" class="yellow-checker" name="shiftMahjongRule" value="1"/>
	                              </div>
	                              <span class="fl ml30">指定不动牌</span>
	                            </td>
	                            <td colspan="2">
	                              <div class="ch-checker fl">
	                                <div class="beau-checker">
	                                  <span class="iconfont icon-gou"></span>
	                                </div>
	                                <input type="radio" class="yellow-checker" name="shiftMahjongRule" value="2"/>
	                              </div>
	                              <span class="fl ml30">指定动牌</span>
	                            </td>
	                        </tr>
	                        
	                        <tr>
	                            <td>上牌规则:</td>
	                            <td>
	                              <div class="ch-checker fl">
	                                <div class="beau-checker">
	                                  <span class="iconfont icon-gou"></span>
	                                </div>
	                                <input type="radio" class="yellow-checker" name="shiftMahjongUp" value="1"/>
	                              </div>
	                              <span class="fl ml30">考勤上牌</span>
	                            </td>
	                            <td colspan="2">
	                              <div class="ch-checker fl">
	                                <div class="beau-checker">
	                                  <span class="iconfont icon-gou"></span>
	                                </div>
	                                <input type="radio" class="yellow-checker" name="shiftMahjongUp" value="2"/>
	                              </div>
	                              <span class="fl ml30">持续上牌</span>
	                            </td>
	                        </tr>
	                        
	                        <tr>
	                            <td>离开规则:</td>
	                            <td>
	                              <div class="ch-checker fl">
	                                <div class="beau-checker">
	                                  <span class="iconfont icon-gou"></span>
	                                </div>
	                                <input type="radio" class="yellow-checker" name="nature" value="1"/>
	                              </div>
	                              <span class="fl ml30">离开动牌</span>
	                            </td>
	                            <td colspan="2">
	                              <div class="ch-checker fl">
	                                <div class="beau-checker">
	                                  <span class="iconfont icon-gou"></span>
	                                </div>
	                                <input type="radio" class="yellow-checker" name="nature" value="2"/>
	                              </div>
	                              <span class="fl ml30">离开不动牌</span>
	                            </td>
	                        </tr>
	
	                        <tr>
	                            <td colspan="4">
	                                                                                               请选择该排位下的排班岗位
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
	                </form>
	            </div><!--modal body-->
	
	            <div class="modal-footer">
	                <a class="btn modal-cancel" href="#" data-dismiss="modal" aria-label="Close">取消</a>
	                <a class="btn btn-primary modal-confirm" href="#" id= "confirm">确定</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<script>
     	var deptId = "${deptId}";
     	var deptListStr = '${deptListJson}';
 	    var deptDtoList = eval("(" + deptListStr + ")");
	</script>
	
	
	<script type="text/javascript" src="<%=basePath %>js/keepAccounts/shiftMahjong.js"></script>
   </div>
     <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
    
	<script>
	  window.onload = function(){
	    var oTop = document.getElementById("return-top");
	    oTop.onclick = function(){
	      document.documentElement.scrollTop = document.body.scrollTop =0;
	    }
	  }
	</script>
    
  </div>
  <!--轮牌的状态变化选择下拉-->
	<ul class="select-zhuangtai hide">
	    <li>
	        <div class="select-icon fl">
	            <div class="center kongxianzhong" onclick="updateState(1)">
	                <div class="zhuangtai">
	                    <img src="<%=basePath %>img/lunpai/coffee.png" alt="工作中"/>
	                </div>
	            </div>
	            <div class="center zanshilikai" onclick="updateState(2)">
	                <div class="zhuangtai">
	                    <img src="<%=basePath %>img/lunpai/alarm.png" alt="工作中"/>
	                </div>
	            </div>
	            <div class="center likai" onclick="updateState(3)">
	                <div class="zhuangtai">
	                    <img src="<%=basePath %>img/lunpai/clock.png" alt="工作中"/>
	                </div>
	            </div>
	        </div>
	        <div class="select-zt-word fl">
	            <div class="select1 cursor" onclick="updateState(1)">空闲中</div>
	            <div class="select1 cursor" onclick="updateState(2)">暂时离开</div>
	            <div class="select1 cursor" onclick="updateState(3)">离开</div>
	        </div>
	    </li>
	    <em></em>
	    <span></span>
	</ul>
</body>

</html>