<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>预约服务</title>
    <!--导航滑动的css-->
    <link rel="stylesheet" href="<%=basePath%>css/mobile/swiper.min.css"/>
    <!--字体图标库-->
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <!--整体的样式-->
    <link rel="stylesheet" href="<%=basePath%>css/mobile/member.css"/>
  </head>
<body>
<div class="content">
    <div class="bd-white bodyoh">
		<c:if test="${typeNumber >= 2 }">
	       <div class="swiper-container-daohang tab">
	            <div class="swiper-wrapper">
	                <c:forEach items="${serviceList }" var="service" varStatus="status">
	                    <div class="swiper-slide score-shop-li" onclick="chooseDept('${service.deptId }')">
	                        <img src="<%=basePath%>/images/mobile/active-new.png" class="hide"/>
	                        <div class="tab-word">
	                            <div class="medium-font line-height80">${service.deptName }</div>
	                        </div>
	                    </div>
	                </c:forEach>
	            </div>
	        </div>
		</c:if>
		<c:if test="${typeNumber == 1 }">
		   <c:set var="oneClass" value="pt1r" />
		</c:if>
		<c:forEach items="${serviceList }" var="service" varStatus="status">
            <div class="d-select-project ${oneClass } hide" deptService="${service.deptId }">
	            <div class="d-content">
	                <div class="project-type">
	                    <ul class="project-type-ul">
	                       <c:forEach items="${service.projectCategoryList }" var="projectCategory">
	                            <li class="type-item j-tab" categoryTitle="${projectCategory.categoryId }" >
	                                <span>${projectCategory.categoryName }</span>
	                            </li>
	                       </c:forEach>
	                    </ul>
	                </div>
	                
	                <div class="project-list j-tab">
	                   <c:forEach items="${service.projectCategoryList }" var="projectCategory">
	                       <div class="project-list-part" categoryContent="${projectCategory.categoryId }">
	                            <div class="list-part-title">
	                                <span>${projectCategory.categoryName }</span>
	                            </div>
	                            <div class="list-item">
	                                <ul>
	                                   <c:forEach items="${projectCategory.projectList }" var="project">
	                                       <c:if test="${project.isAppointment == 1 }">
		                                       <a href="<%=basePath%>memberCenter/view/projectDetail?projectId=${project.projectId}">
			                                       <li>
			                                            <img src="<%=picPath%>${project.projectImage}?imageView2/1/w/220/h/220" width="109" height="109" alt=""/>
			                                            <div class="item-desc">
			                                                <div class="name">${project.projectName}</div>
			                                                <div class="d-price font-666">服务次数：${project.salesCount}</div>
			                                                <div class="origin-price"><span class="text-through">价格: ￥${project.projectPrice}</span></div>
			                                            </div>
			            
			                                            <div class="shop-num-control">
			                                                <span class="num-emphasie">￥${project.projectPrice - project.appointmentPrice}</span>
			                                            </div>
			                                        </li>
		                                        </a>
	                                        </c:if>
	                                   </c:forEach>
	                                </ul>
	                            </div>
	                        </div>
	                   </c:forEach>
	                   <div class="h15r"></div>
	                </div>
	            </div>
	        </div>               
        </c:forEach>
	</div>
	<div>
      <ul class="footer">
        <li>
          <a href="<%=basePath %>memberCenter/view/home/${session_key_store_id}/1">
            <span class="iconfont icon-wode"></span>
            <span class="word">我的</span>
          </a>
        </li>
        <li class="active">
          <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_id}/1">
            <span class="iconfont icon-yuyue6"></span>
            <span>预约</span>
          </a>
        </li>
        <li >
          <a href="<%=basePath%>memberCenter/view/shopCenter">
            <span class="iconfont icon-jifen"></span>
            <span>积分</span>
          </a>
        </li>
        <li >
          <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_id}/1">
            <span class="iconfont icon-dianpu2"></span>
            <span>门店</span>
          </a>
        </li>
      </ul>
    </div>
</div>
<!--jquery-->
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"> </script>
<!--导航滑动所需的js-->
<script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
<!--会员端js-->
<script type="text/javascript" src="<%=basePath%>js/mobile/member.js"> </script>
<script type="text/javascript">
    $(function(){
    	//初始化页面时默认让第一个部门选中，
    	$(".swiper-slide:first").addClass("active").find("img").removeClass("hide");
    	$(".d-select-project:first").removeClass("hide");
    	$(".project-type-ul").find("li:first").addClass("active");
    	
    	/*手机端导航滑动*/
        var swiper = new Swiper('.swiper-container-daohang', {
            pagination: '.swiper-pagination',
            slidesPerView: '${typeNumber}',
            paginationClickable: true,
            spaceBetween: 0,
            freeMode: true
        });

        /*导航滑动选中状态*/
        $('.swiper-slide').click(function(){
            $(".swiper-slide").removeClass("active");
            $(".swiper-slide img").addClass("hide");
            $(this).addClass("active");
            $(this).find("img").removeClass("hide");
        });

        var contentHeight = function(){
            var height = document.documentElement.clientHeight;
            var projectUlHeight = height;
            $(".project-list").css("height", projectUlHeight);
            $(".project-type-ul").css("height", projectUlHeight);
        }

        contentHeight();
        
        var offset = $(".d-content").offset().top;
        var top = offset;
        $(".type-item").click(function(){
            $(this).addClass("active").siblings().removeClass("active");
            var sub = $("[categoryContent='" + $(this).attr("categorytitle") + "']");
            var container = sub.parent();
            offset = sub.offset().top - container.offset().top + container.scrollTop();
            container.scrollTop(offset);
        });
    });
    
    
    function chooseDept(deptId){
        $(".d-select-project").addClass("hide");
        $("[deptService='" + deptId + "']").removeClass("hide");
    }
</script>
</body>
</html>