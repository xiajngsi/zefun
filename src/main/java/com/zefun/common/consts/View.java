package com.zefun.common.consts;

/**
 * 视图地址常量类，定义时使用根目录/WEB-INF/view/下对绝对地址
* @author 张进军
* @date Aug 4, 2015 9:21:29 AM
 */
public interface View {

    /**
     * 首页处理
    * @author 高国藩
    * @date 2015年10月26日 下午4:54:58
     */
    class Index{
        /** 登陆页面 */
        public static final String LOGIN = "login";
    }

    /**
     * 角色权限控制
    * @author 高国藩
    * @date 2015年11月25日 上午11:53:07
     */
    class Authority{
        /**角色权限配置页面*/
        public static final String VIEW = "authority/view";
    }
    
    
    /**
     * 门店管理制度模块
    * @author 张进军
    * @date Dec 5, 2015 7:17:45 PM
     */
    class StoreManageRule {
        /**管理规则页面*/
        public static final String RULE = "employee/manage/rule";
    }


    /**
     * 设置类模块
    * @author 张进军
    * @date Nov 9, 2015 11:36:48 AM
     */
    class Setting {
        /** 店铺设置 */
        public static final String STORE_SETTING = "setting/storeSetting";

        /** 门店基础设置 */
        public static final String BASE_SETTING = "setting/baseSetting";

        /** 个人账户 */
        public static final String PERSON_SETTING = "setting/personSetting";
        
        /** 分店列表 */
        public static final String BRANCH_LIST = "setting/branchList";
    }


    /**
     * 人脸模块相关页面
    * @author 张进军
    * @date Jul 2, 2015 2:57:02 PM
     */
    class Face{
        /** 添加face页面 */
        public static final String ADD = "face/add";
        /** 搜索face页面 */
        public static final String SEARCH = "face/search";
    }


    /**
     * 会员等级相关页面
    * @author 张进军
    * @date Aug 5, 2015 7:49:53 PM
     */
    class MemberLevel{
        /** 会员等级列表页面 */
        public static final String LIST = "member/memberLevel/list";
    }

    /**
     * 会员信息页面
    * @author 高国藩
    * @date 2015年9月8日 下午6:04:08
     */
    class MemberInfo{
        /** 会员展示*/
        public static final String MEMBER_LIST_VIEW = "member/memberLevel/member-list";
        /**会员展示*/
        public static final String GROUP_LIST_VIEW = "member/memberLevel/group-list";
        /**会员导入错误数据展示页面*/
        public static final String VEIW_ERROR_MEMBER = "member/memberLevel/errorMember";
        /**总店会员页面*/
        public static final String BASE_MEMBER_VIEW = "member/memberLevel/baseMember";
    }


    /**
     * 微信会员中心相关页面
    * @author 张进军
    * @date Aug 19, 2015 5:46:43 PM
     */
    class MemberCenter{
        /** 会员主页面 */
        public static final String HOME = "mobile/member/home";
        /**个人资料*/
        public static final String INFO = "mobile/member/info";
        /**密码设置页面*/
        public static final String SET_PWD = "mobile/member/setPwd";
        /** 会员注册页面 */
        public static final String REGISTER = "mobile/member/register";
        /** 会员充值页面 */
        public static final String ACCOUNT = "mobile/member/account";
        /** 完善会员信息页面 */
        public static final String REGISTER_INFO = "mobile/member/registerInfo";
        /** 分享发型页面 */
        public static final String SHARE_SHOW = "mobile/member/shareShow";
        /** 预约页面 */
        public static final String ORDER_APPOINTMENT = "mobile/member/orderAppointment";
        /**项目详情*/
        public static final String PROJECT_DETAIL = "mobile/member/projectDetail";
        /**时间预约*/
        public static final String DATE_APPOINTMENT = "mobile/member/dateAppointment";
        /**积分流水*/
        public static final String INTEGRAL_FLOW = "mobile/member/integralFlow";
        /**卡金流水记录页面*/
        public static final String CARD_MONEY_FLOW = "mobile/member/cardmoneyFlow";
        /**礼金流水记录页面*/
        public static final String GIFT_MONEY_FLOW = "mobile/member/giftmoneyFlow";
        /**积分商城*/
        public static final String SHOP_CENTER = "mobile/member/shopCenter";
        /**会员优惠券*/
        public static final String MEMBER_COUPON = "mobile/member/memberCoupon";
        /**店铺信息*/
        public static final String STORE_INFO = "mobile/member/storeInfo";
        /**店铺展示*/
        public static final String STORE_SHOW = "mobile/member/storeShow";
        /**会员预约列表*/
        public static final String APPOINTMENT_LIST = "mobile/member/appointmentList";
        /**会员订单列表*/
        public static final String ORDER_LIST = "mobile/member/orderList";
        /**会员订单确认*/
        public static final String ORDER_PAY = "mobile/member/orderPay";
        /**会员订单支付明细*/
        public static final String PAYMENT_DETAIL = "mobile/member/paymentDetail";
        /**会员订单评价*/
        public static final String ORDER_EVALUATE = "mobile/member/orderEvaluate";
        /**会员套餐列表*/
        public static final String COMBO_LIST = "mobile/member/comboList";
        /**门店列表*/
        public static final String STORE_LIST = "mobile/member/storeList";
    }

    /**
     * 员工手机端
    * @author 王大爷
    * @date 2015年8月21日 上午10:15:25
     */
    class StaffPage{
        /** 员工登录页面*/
        public static final String STAFF_LOGIN = "mobile/staff/login";
        /** 员工操作中心*/
        public static final String STAFF_CENTER = "mobile/staff/staffCenter";
        /** 员工个人信息*/
        public static final String STAFF_INFO = "mobile/staff/staffInfo";
        /** 员工密码页面*/
        public static final String UPDATE_PWD = "mobile/staff/updatePwd";
        /** 更多操作界面*/
        public static final String STAFF_MORE = "mobile/staff/more";
        /** 员工预约列表*/
        public static final String STAFF_APPOINT = "mobile/staff/staffAppoint";
        /** 员工业绩排行*/
        public static final String ALL_ERANING = "mobile/staff/allEarning";
        /** 员工个人业绩*/
        public static final String STAFF_ERANING = "mobile/staff/staffEarning";
        /** 员工接待页面*/
        public static final String RECEPTION = "mobile/staff/reception";
        /** 选择类别页面*/
        public static final String PROJECT_CATEGORY = "mobile/staff/projectCategory";
        /** 轮牌指定*/
        public static final String MEMBER_SHIFTMAHJONG_SERVE = "mobile/staff/memberShiftMahjongServe";
        /** 项目列表*/
        public static final String PROJECT_LIST = "mobile/staff/projectList";
        /** 会员结账界面*/
        public static final String MEMBER_PAY = "mobile/staff/memberPay";
        /** 员工工资*/
        public static final String STAFF_SALARY = "mobile/staff/staffSalary";
        /** 员工业绩详情*//*
        public static final String STAFF_DETAILS = "mobile/staff/staffDetails";*/
        /** 员工服务界面*/
        public static final String STAFF_SERVE = "mobile/staff/staffServe";
        /** 等待中订单列表*/
        public static final String WAITING_ORDER = "mobile/staff/waitingOrder";
        /** 已完成订单*/
        public static final String ORDER_LIST = "mobile/staff/orderList";
        /** 订单详情*/
        public static final String ORDER_DETAILS = "mobile/staff/orderDetails";
        /** 服务移交轮牌显示*/
        public static final String TURN_SHIFTMAHJONG_SERVE = "mobile/staff/turnShiftMahjongServe";
        /** 修改项目*/
        public static final String CHANGE_PROJECT = "mobile/staff/changeProject";
        /** 修改项目轮牌*/
        public static final String UPDATE_SHIFTMAHJONG_SERVE = "mobile/staff/updateShiftMahjongServe";
        /** 等待中心轮牌*/
        public static final String WAITING_SHIFTMAHJONG_SERVE = "mobile/staff/waitingShiftMahjongServe";
        /** 订单明细*/
        public static final String ORDER_DETAIL = "mobile/staff/orderdetail";
        /** 所有轮牌界面*/
        public static final String ALL_SHIFTMAHJONG = "mobile/staff/allShiftMahjong";
        /** 我的轮牌界面*/
        public static final String MY_SHIFTMAHJONG = "mobile/staff/myShiftMahjong";
    }

    /** 发型设置 */
    class HairstyleDesign{
        /** 发型设置页面 */
        public static final String HAIRSTYLEDESIGN = "commodity/hairstyleDesign";
    }

    /**
     * 项目
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:04:20
     */
    class Project{
        /** 项目价格设置页面 */
        public static final String PROJECTSETTING = "commodity/projectSetting";
    }


    /**
     * 套餐
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:04:32
     */
    class ComboInfo{
        /**套餐设置页面*/
        public static final String COMBOINFO = "commodity/comboInfo";
    }

    /**
     * 商品
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:04:50
     */
    class GoodsInfo{
        /** 商品设置页面*/
        public static final String GOODSINFO = "commodity/goodsInfo";
        /** 商品库存页面 */
        public static final String GOODSSTOCK = "commodity/goodsStock";
        /** 商品出货记录*/
        public static final String SHIP_MENT_RECORD = "commodity/shipMentRecord";
        /** 品牌管理页面*/
        public static final String BRAND = "commodity/goodsBrand";
        /** 商品进货页面*/
        public static final String GOODS_PURCHASE_RECORDS = "commodity/purchaseRecords";
    }

    /**
     * 供应商
    * @author 洪秋霞
    * @date 2015年8月12日 下午2:40:56
     */
    class SupplierInfo{
        /**供应商设置页面*/
        public static final String SUPPLIERINFO = "commodity/supplierInfo";
        /**进货记录页面*/
        public static final String PURCHASE_RECORDS = "commodity/purchaseRecords";
    }

    /**
     * 岗位信息
    * @author 陈端斌
    * @date 2015年8月4日 下午4:32:30
     */
    class Position{
        /** 岗位信息页面 */
        public static final String POSITION = "employee/positioninfo/positioninfo";
    }
    /**
     * 职位信息页面
    * @author chendb
    * @date 2015年8月11日 上午10:10:38
     */
    class Employeelevel{
        /** 职位信息页面*/
        public static final String EMPLOYEELEVEL = "employee/employeelevel/employeelevel";
    }

    /**
     * 自助收银
    * @author 王大爷
    * @date 2015年8月11日 上午11:21:37
     */
    class KeepAccounts{
        /** 开支记账*/
        public static final String STOREFLOW = "keepAccounts/storeFlow";
        /** 轮职排班*/
        public static final String SHIFT_MAHJONG ="keepAccounts/shiftMahjong";
        /** 开卡充值*/
        public static final String OPEN_CARD ="keepAccounts/openCard";
        /** 手工收银*/
        public static final String MANUALLY_OPEN_ORDER = "keepAccounts/manuallyOpenOrder";
    }

    /**
     * 微信模块
    * @author 高国藩
    * @date 2015年8月7日 上午10:03:21
    *
     */
    class Wechat{
        /**菜单页面*/
        public static final String MENU = "wechat/menu";
        /**新增图文消息页面*/
        public static final String ARTIC_MANAGER = "wechat/article-manage";
        /**展示图文消息*/
        public static final String SHOW_ITEMS = "wechat/items-manage";
        /**修改摸一个图文消息，展示其中一个*/
        public static final String CHATE_ITME = "wechat/update-article-manage";
        /**图文消息发送*/
        public static final String SEND_ITEMS = "wechat/send-items";
        /**图文消息统计*/
        public static final String ITEMS_STATUS = "wechat/items-msg-status";
        /**图文消息设置页面*/
        public static final String VIEW_AUTO_REPLY = "wechat/auto-reply";
        /**门店菜单设置页面*/
        public static final String STORE_MENU = "wechat/store-menu";
        /**我的公众号*/
        public static final String VIEW_OFFICAL = "wechat/offical";

    }
    /**
     * 人员目标
    * @author chendb
    * @date 2015年8月17日 下午3:21:41
     */
    class Objective{
        /** 职位信息页面*/
        public static final String OBJECTIVE = "employee/objective/objective";
    }

    /**
     * 优惠券
    * @author 高国藩
    * @date 2015年8月18日 上午11:40:55
     */
    class Coupon{
        /**优惠券展示页面*/
        public static final String VIEW_COUPON = "coupon/coupon-list";
    }

    /**
     * 自助收银
    * @author luhw
    * @date 2015年10月21日 下午15:27:49
     */
    class SelfCashier {
        /**优惠券展示页面*/
        public static final String VIEW_SELF_CASHIER = "cashier/payment";

        /** 预约列表 */
        public static final String APPOINT_LIST = "cashier/appointList";
    }

    /** 订单流水 */
    class DayBook {
        /** 订单流水查询页面 */
        public static final String VIEW_DAYBOOK_INDEX = "daybook/view/index";

        /** 订单流水查询 */
        public static final String ACTION_DAYBOOK_LIST = "daybook/action/list";
        
    }

    /**
     * 登陆
    * @author 高国藩
    * @date 2015年9月20日 上午11:21:38
     */
    class Login{
    }

    /**
     * 门店
     * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a> 2015年11月24日
     */
    class Store {

        /**
         *
         */
        public static final String STORE_APPLY = "mobile/store/apply";

    }

    /**
     *
     * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a> 2015年11月26日
     */
    class Agent {

        /**
         *
         */
        public static final String AGENT_APPLY = "mobile/agent/apply";

    }

    /**
     *
     * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a> 2015年11月26日
     */
    class StoreDetail {

        /**
         *
         */
        public static final String SINGLE_STORE = "mobile/store/single";

        /**
         *
         */
        public static final String CHAIN_HQ_STORE = "mobile/store/chain_hq";

        /**
         *
         */
        public static final String CHAIN_STORE = "mobile/store/chain";

        /**
         *
         */
        public static final String SINGLE_STORE_INFO = "mobile/store/single_info";

        /**
         *
         */
        public static final String CHAIN_HQ_STORE_INFO = "mobile/store/chain_hq_info";

        /**
         *
         */
        public static final String CHAIN_STORE_INFO = "mobile/store/chain_info";

        /**
         *
         */
        public static final String CHAIN_STORE_CHAINS = "mobile/store/chain_hq_chains";

        /**
         *
         */
        public static final String STORE_RENEW_SYS = "mobile/store/renew_sys";

        /**
        *
        */
        public static final String STORE_RENEW_SMS = "mobile/store/renew_sms";
    }

    /**
     *
     * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a> 2015年11月26日
     */
    class AgentDetail {
        /** 渠道个人账户页面(已审核) */
        public static final String INDEX = "mobile/agent/index";
        
        /** 渠道个人账户页面(审核中) */
        public static final String APPLY_INFO = "mobile/agent/applyInfo";

        /**
         *
         */
        public static final String INFO = "mobile/agent/info";

        /**
         *
         */
        public static final String NEW_STORE_SELF = "mobile/agent/new_store_self";

        /**
         *
         */
        public static final String NEW_STORE_OTHER = "mobile/agent/new_store_other";

        /**
         *
         */
        public static final String STORE_NORMAL = "mobile/agent/store_normal";

        /**
         *
         */
        public static final String STORE_RENEW = "mobile/agent/store_renew";

        /**
         *
         */
        public static final String STORE_OVER = "mobile/agent/store_over";

        /**
         *
         */
        public static final String STORE_MY_RECOMMEND = "mobile/agent/store_my_recommend";

        /**
         *
         */
        public static final String AGENT_MY_RECOMMEND = "mobile/agent/agent_my_recommend";

        /**
         *
         */
        public static final String STORE_RECOMMEMND_TO_ME = "mobile/agent/store_recommend_me";

        /**
         *
         */
        public static final String INCOME = "mobile/agent/income";

    }
    
    /**
     * 员工出勤记录
     * @author lzc
     *
     */
    class AttendanceRecord {
    	/** 员工考勤首页 */
    	public static final String HOME = "employee/attendance/attendance";
    }
    
    /**
     * 员工奖惩
     * @author lzc
     *
     */
    class EmployeeReward {
    	/** 员工奖惩首页 */
    	public static final String HOME = "employee/rewards/rewards";
    }
}
