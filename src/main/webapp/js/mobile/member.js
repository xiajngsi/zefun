$(function(){
  /*定义页面的高度*/
  var height = document.documentElement.clientHeight;
  var contentHeight = function(){
    $(".content").css("min-height", height);//不写会出现页面背景不全部现实的问题，写成height会出现ios上滚动缓慢,且当height大于页面高度时会出现只有这么高的高度的问题。
    $(".per-center-login").css("min-height", height);
    $(".white-bg").css("min-height", height);
    $(".s-modal").css("height", height);//对于模态框必须要只有固定高度，因为模态框没有滚动的说法
    $(".bd-white").css("min-height", height);
    $(".bd-gray").css("min-height", height);
  }

  /*模态框出来*/
  $(".s-modal-control").on("click", function(){
    $('body').css({"overflow":"hidden"});
    var targetModal = $(this).data("target");
    /*console.log("targetModal" + targetModal);*/
    $(targetModal).removeClass("hide");
  });

  /*模态框消失*/
  $(".s-modal-miss").on("click", function(){
    $('body').css("overflow-y","auto");
    $(".s-modal").addClass("hide");
  });
  contentHeight();

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

  /*页面返回的js*/
  $(".back").on("click", function(){
    history.go(-1);
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
})
