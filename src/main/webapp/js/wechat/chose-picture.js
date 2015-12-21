    /**选择图片-弹出模态框*/
    jQuery("#from-picture1").on("click",function(){
    	jQuery("#photo-list-modal1").modal();
    	jQuery(".photo-item").on("click",function(){
            /*点击别的,复原以前的*/
            jQuery(".photo-item").children(".appmsg_mask").hide();
            jQuery(".photo-item").children(".icon_card_selected").hide();
            var picItem = jQuery(this);
            picItem.children(".appmsg_mask").show();
            picItem.children(".icon_card_selected").show();
            var src = picItem.find("img").eq(0).attr("src");
            jQuery("#confirm-menu-url1").one("click",function(){
            	  jQuery("#news-item1").find("img").attr("src",src);
	           	  jQuery("#imgUrl1").val(src);
	           	  jQuery("#showImg1").attr("src",src);
	           	  jQuery("#showImg1").show();
            });
        });
    });
    jQuery("#from-picture2").on("click",function(){
    	jQuery("#photo-list-modal2").modal();
    	jQuery(".photo-item").on("click",function(){
            /*点击别的,复原以前的*/
            jQuery(".photo-item").children(".appmsg_mask").hide();
            jQuery(".photo-item").children(".icon_card_selected").hide();
            var picItem = jQuery(this);
            picItem.children(".appmsg_mask").show();
            picItem.children(".icon_card_selected").show();
            var src = picItem.find("img").eq(0).attr("src");
            jQuery("#confirm-menu-url2").one("click",function(){
	           	  jQuery("#imgUrl2").val(src);
	           	  jQuery("#showImg2").attr("src",src);
	           	  jQuery("#showImg2").show();
	           	  //缩略图处理
	           	  jQuery("#news-item2").children("img").attr("class","news-thumb default news-word");
				  jQuery("#news-item2").children("img").attr("src",src);
				  jQuery("#news-item2").children("i").remove();
            });
        });
    });
    jQuery("#from-picture3").on("click",function(){
    	jQuery("#photo-list-modal3").modal();
    	jQuery(".photo-item").on("click",function(){
            /*点击别的,复原以前的*/
            jQuery(".photo-item").children(".appmsg_mask").hide();
            jQuery(".photo-item").children(".icon_card_selected").hide();
            var picItem = jQuery(this);
            picItem.children(".appmsg_mask").show();
            picItem.children(".icon_card_selected").show();
            var src = picItem.find("img").eq(0).attr("src");
            jQuery("#confirm-menu-url3").on("click",function(){
	           	  jQuery("#imgUrl3").val(src);
	           	  jQuery("#showImg3").attr("src",src);
	           	  jQuery("#showImg3").show();
	           	  //缩略图处理
	           	  jQuery("#news-item3").children("img").attr("class","news-thumb default news-word");
				  jQuery("#news-item3").children("img").attr("src",src);
				  jQuery("#news-item3").children("i").remove();
            });
        });
    });
    jQuery("#from-picture4").on("click",function(){
    	jQuery("#photo-list-modal4").modal();
    	jQuery(".photo-item").on("click",function(){
            /*点击别的,复原以前的*/
            jQuery(".photo-item").children(".appmsg_mask").hide();
            jQuery(".photo-item").children(".icon_card_selected").hide();
            var picItem = jQuery(this);
            picItem.children(".appmsg_mask").show();
            picItem.children(".icon_card_selected").show();
            var src = picItem.find("img").eq(0).attr("src");
            jQuery("#confirm-menu-url4").on("click",function(){
	           	  jQuery("#imgUrl4").val(src);
	           	  jQuery("#showImg4").attr("src",src);
	           	  jQuery("#showImg4").show();
	           	  //缩略图处理
	           	  jQuery("#news-item4").children("img").attr("class","news-thumb default news-word");
				  jQuery("#news-item4").children("img").attr("src",src);
				  jQuery("#news-item4").children("i").remove();
            });
        });
    });
    jQuery("#from-picture5").on("click",function(){
    	jQuery("#photo-list-modal5").modal();
    	jQuery(".photo-item").on("click",function(){
            /*点击别的,复原以前的*/
            jQuery(".photo-item").children(".appmsg_mask").hide();
            jQuery(".photo-item").children(".icon_card_selected").hide();
            var picItem = jQuery(this);
            picItem.children(".appmsg_mask").show();
            picItem.children(".icon_card_selected").show();
            var src = picItem.find("img").eq(0).attr("src");
            jQuery("#confirm-menu-url5").on("click",function(){
	           	  jQuery("#imgUrl5").val(src);
	           	  jQuery("#showImg5").attr("src",src);
	           	  jQuery("#showImg5").show();
	           	  //缩略图处理
	           	  jQuery("#news-item5").children("img").attr("class","news-thumb default news-word");
				  jQuery("#news-item5").children("img").attr("src",src);
				  jQuery("#news-item5").children("i").remove();
            });
        });
    });
    jQuery("#from-picture6").on("click",function(){
    	jQuery("#photo-list-modal6").modal();
    	jQuery(".photo-item").on("click",function(){
            /*点击别的,复原以前的*/
            jQuery(".photo-item").children(".appmsg_mask").hide();
            jQuery(".photo-item").children(".icon_card_selected").hide();
            var picItem = jQuery(this);
            picItem.children(".appmsg_mask").show();
            picItem.children(".icon_card_selected").show();
            var src = picItem.find("img").eq(0).attr("src");
            jQuery("#confirm-menu-url6").on("click",function(){
	           	  jQuery("#imgUrl6").val(src);
	           	  jQuery("#showImg6").attr("src",src);
	           	  jQuery("#showImg6").show();
	           	  //缩略图处理
	           	  jQuery("#news-item6").children("img").attr("class","news-thumb default news-word");
				  jQuery("#news-item6").children("img").attr("src",src);
				  jQuery("#news-item6").children("i").remove();
            });
        });
    });
    jQuery("#from-picture7").on("click",function(){
    	jQuery("#photo-list-modal7").modal();
    	jQuery(".photo-item").on("click",function(){
            /*点击别的,复原以前的*/
            jQuery(".photo-item").children(".appmsg_mask").hide();
            jQuery(".photo-item").children(".icon_card_selected").hide();
            var picItem = jQuery(this);
            picItem.children(".appmsg_mask").show();
            picItem.children(".icon_card_selected").show();
            var src = picItem.find("img").eq(0).attr("src");
            jQuery("#confirm-menu-url7").on("click",function(){
	           	  jQuery("#imgUrl7").val(src);
	           	  jQuery("#showImg7").attr("src",src);
	           	  jQuery("#showImg7").show();
	           	  //缩略图处理
	           	  jQuery("#news-item7").children("img").attr("class","news-thumb default news-word");
				  jQuery("#news-item7").children("img").attr("src",src);
				  jQuery("#news-item7").children("i").remove();
            });
        });
    });
    jQuery("#from-picture8").on("click",function(){
    	jQuery("#photo-list-modal8").modal();
    	jQuery(".photo-item").on("click",function(){
            /*点击别的,复原以前的*/
            jQuery(".photo-item").children(".appmsg_mask").hide();
            jQuery(".photo-item").children(".icon_card_selected").hide();
            var picItem = jQuery(this);
            picItem.children(".appmsg_mask").show();
            picItem.children(".icon_card_selected").show();
            var src = picItem.find("img").eq(0).attr("src");
            jQuery("#confirm-menu-url8").on("click",function(){
	           	  jQuery("#imgUrl8").val(src);
	           	  jQuery("#showImg8").attr("src",src);
	           	  jQuery("#showImg8").show();
	           	  //缩略图处理
	           	  jQuery("#news-item8").children("img").attr("class","news-thumb default news-word");
				  jQuery("#news-item8").children("img").attr("src",src);
				  jQuery("#news-item8").children("i").remove();
            });
        });
    });