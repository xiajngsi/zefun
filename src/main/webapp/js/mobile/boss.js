/**
 * Created by Administrator on 2015/8/22.
 */
$(function(){
    /*展开与收起part下的内容*/
    $(".toggle-ctl").on("click", function(){
        var th = $(this);
        var thParent = th.parent();
        var tar = thParent.find(".table");
        if(tar.is(":visible")){
            th.find(".icon-shouqi").removeClass("icon-shouqi").addClass("icon-zhankai");
        }else{
            th.find(".icon-zhankai").removeClass("icon-zhankai").addClass("icon-shouqi");
        }
        tar.toggle();
    });

    /*点击当前页面的具体项在模态框中显示相应的*/
    $(".s-modal-control").on("click", function(){
        var th = $(this);
        var tabTraget = th.data("tab");
        /*var tabName  = th.find("span").text();*///得到当前点击的name
        /*遍历列表，将和当前点击对象一样的值标示为选中状态*/
        /*选中的tab中的li*/
        /*var tabTragetSpan = $(tabTraget).find("span");
        var tabTragetLi = $(tabTraget).find("li");
        for(i = 0; i< $(tabTragetLi).length; i++ ){
            if($(tabTragetSpan)[i].text() == tabName){
                $(tabTragetSpan).parent("li").find(".iconfont").addClass("hide");
                $(tabTragetSpan)[i].parent("li").find(".iconfont").removeClass("hide");
            }
        }*/
        $(".tab-toggle").removeClass("active");
        var tabToggle = $(".tab-toggle");
        for(i = 0; i<tabToggle.length; i++){
            var l = tabToggle.length;
            var a = tabToggle[i];
            var avtiveE = $(tabToggle[i]).data("target");
            if(avtiveE == tabTraget ){
                $(tabToggle[i]).addClass("active");
            }
        }
        $(".tab-target").hide();
        $(tabTraget).show();
    });
});
