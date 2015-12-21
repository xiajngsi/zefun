/**
 * Created by candice on 2015/9/19.
 */

$(function(){
  /*定义页面的高度*/
  var contentHeight = function(){
    var height = document.documentElement.clientHeight;
    $(".per-center-login").css("height", height);
    $(".content").css("min-height", height);
    $(".bd-white").css("min-height", height);
    $(".bd-gray").css("min-height", height);
    var sModal = document.documentElement.clientHeight + 100;
    $(".s-modal").css("height", sModal);
    $(".s-gouwuche-modal").css("height", sModal);
  }

  /*在订单页面让body不滚动*/
    $(".bodyoh").parents("body").css("overflow", "hidden");

  /*模态框出来*/
  $(".s-modal-control").on("click", function(){
    $("body").css({"overflow":"hidden"});
    var targetModal = $(this).data("target");
    /*console.log("targetModal" + targetModal);*/
    $(targetModal).removeClass("hide");
  });

  /*模态框消失*/
  $(".s-modal-miss").on("click", function(){
    $("body").css("overflow-y","auto");
    $(".s-modal").addClass("hide");
  });
  contentHeight();

  /*页面返回的js*/
  $(".back").on("click", function(){
    history.go(-1);
  });

  $(".j-tab").on("click", function () {
    $(".j-tab").removeClass("active");
    $(this).addClass("active");
    var targetTab = $(this).data("target");
    $(".j-target-tab").addClass("hide");
    $(targetTab).removeClass("hide");
  });

  /*漂亮的单选和多选*/
  $(".yellow-radio").on("click", function () {
    var radio = $(".yellow-radio");
    for(var i=0;i<radio.length;i++)
    {
      if(radio[i].checked)
      {
        $(radio[i]).siblings(".beau-radio").addClass("active");
      }else{
        $(radio[i]).siblings(".beau-radio").removeClass("active");
        
      }
    }
  });

  /*展开与收起part下的内容*/
  $(".toggle-ctl").on("click", function(){
    var th = $(this);
    var thParent = th.parent();
    var tar = thParent.find(".table");
    if(tar.is(":visible")){
      th.find(".iconfont").addClass("rotate");
    }else{
      th.find(".iconfont").addClass("rotate");
    }
    tar.toggle();
  });

  /*判断浏览器类型*/

  $.browser={
    versions:function(){
      var u = navigator.userAgent, app = navigator.appVersion;
      return {
        trident: u.indexOf('Trident') > -1, //IE内核
        presto: u.indexOf('Presto') > -1, //opera内核
        webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
        gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
        mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
        ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
        android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
        iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
        iPad: u.indexOf('iPad') > -1, //是否iPad
        webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
      };
    }(),
    language:(navigator.browserLanguage || navigator.language).toLowerCase()
  }



  /*$.browser = browser;*/

})


