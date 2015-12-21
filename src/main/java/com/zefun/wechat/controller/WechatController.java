package com.zefun.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.SignUtil;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.WechatMenuDto;
import com.zefun.web.entity.Menu;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.service.RedisService;
import com.zefun.wechat.dto.ThumbDto;
import com.zefun.wechat.dto.UpdateArticleDto;
import com.zefun.wechat.dto.UpdateChangeThumb;
import com.zefun.wechat.dto.UpdateItemsDto;
import com.zefun.wechat.service.WeixinMessageService;
import com.zefun.wechat.service.WeixinUploadService;

/**
 * 微信消息处理类
* @author 高国藩
* @date 2015年8月6日 下午8:25:55 
*
 */
@Controller
public class WechatController extends BaseController{
    /** 微信消息service */
    @Autowired
    private WeixinMessageService weixinMessageService;
    /** 微信接口调用service*/
    @Autowired
    private WeixinUploadService weixinUploadService;
    /** redis操作类 */
    @Autowired
    private RedisService redisService;
    /**查询门店信息*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /** 日志 */
    private Logger logger = Logger.getLogger(WechatController.class);

    /**
     * 微信端口验证
    * @author 高国藩
    * @date 2015年8月11日 上午10:55:31
    * @param request 请求信息封装
    * @param response 返回信息
    * @throws ServletException 请求异常
    * @throws IOException 读写异常
     */
    @RequestMapping(value = Url.Wechat.CHART, method = RequestMethod.GET)
    public void dochart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        /** 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败*/
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 消息回复
    * @author 高国藩
    * @date 2015年8月11日 上午10:58:15
    * @param request 请求信息封装
    * @param response 返回信息
    * @throws ServletException 请求异常
    * @throws IOException 读写异常
     */
    @RequestMapping(value = Url.Wechat.CHART, method = RequestMethod.POST)
    public void chart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String respMessage = weixinMessageService.processRequest(request);
        logger.info("返回信息:" + respMessage);
        /** 响应消息*/
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

    /**
     * 进入新增图文消息页面
    * @author 高国藩
    * @date 2015年8月14日 上午11:22:01
    * @param request 请求
    * @return 跳转页面
     */
    @RequestMapping(value = Url.Wechat.ARTIC_MANAGER)
    public ModelAndView articleManage(HttpServletRequest request) {
        return weixinUploadService.aritcManager(getStoreId(request));
    }

    /**
    *  图文消息上传,在上传过程中,将媒体id(一个)和各个图文消息的信息存入库中,为自动回复做准备.
    *  关键:各个图文消息存入的字段有:标题、图片素材ID、图片微信URL、图文链接地址、描述、门店ID,总之所有在微信接口中
    *  出现的字段都是要存入数据库中的,因为如果不存入的话,在用户点击了两个按钮拉取信息的时候,就会将接口次数调用完了
    * @author 高国藩
    * @date 2015年8月11日 上午11:15:25
    * @param thumb 单个图文消息
    * @param itemNum 上传图文消息的个数
    * @param request 请求
    * @return 上传状态
     */
    @RequestMapping(value = Url.Wechat.UPLOADNEWS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto uploadNews(ThumbDto thumb, Integer itemNum, HttpServletRequest request) {
        /**storeId=0 代表我们开发商新增的图文消息*/
        Integer storeId = getStoreId(request);
        StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
        String accessToken;
        if (storeInfo.getStoreType()!=3){
            //不是分店,直接通过storeId去除accessToken
            accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        }
        else {
            //分店,通过总店的id进行去除accessToken
            Integer hqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
            accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, hqStoreId.toString());
        }
        return weixinUploadService.uploadNews(accessToken, thumb, storeId,
                itemNum);
    }

    /**
     * 将textarea中的图片换取为微信图片地址
    * @author 高国藩
    * @date 2015年8月28日 上午9:59:32
    * @param request 从请求中获得accessToken
    * @param imgUrl 七牛图片链接
    * @return BaseDto  返回微信图片地址
     */
    @RequestMapping(value = Url.Wechat.UPLOAD_AREA, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto uploadImgForTextArea(HttpServletRequest request, String imgUrl) {
        Integer storeId = getStoreId(request);
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        return weixinUploadService.uploadImg(imgUrl, accessToken);
    }

    /**
     * 跳转某个图文消息修改页面，并展示其信息
    * @author 高国藩
    * @date 2015年8月31日 上午11:21:34
    * @param mediaId 图文消息的id
    * @return 跳转页面
     */
    @RequestMapping(value = Url.Wechat.SEND_UPDATE_ITEM, method = RequestMethod.GET)
    public ModelAndView sendUpdateItem(String mediaId) {
        return weixinMessageService.sendUpdateItem(mediaId);
    }
    
    /**
     * ajax查询图文消息预览
    * @author 高国藩
    * @date 2015年8月31日 上午11:21:34
    * @param mediaId 图文消息的id
    * @return 跳转页面
     */
    @RequestMapping(value = Url.Wechat.GET_ITEM_BY_MEDIA_ID, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto getItemsByMediaId(String mediaId){
        return weixinMessageService.getItemsByMediaId(mediaId);
    }
    /**
     * 更新thumbMediaId
    * @author 高国藩
    * @date 2015年9月1日 上午10:52:37
    * @param qiniuImg 七牛图片地址
    * @param request 请求
    * @return 返回thumbMediaId和微信图片地址
     */
    @RequestMapping(value = Url.Wechat.CHANGE_THUMB_ID, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto changeThumbMediaId(String qiniuImg, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        return weixinUploadService.updateThumbMediaId(qiniuImg, accessToken);
    }
    
    /**
     * 点击单个智放图文消息,进行复制到自身的图文库中
    * @author 高国藩
    * @date 2015年9月23日 下午5:03:59
    * @param mediaId 要复制的图文消息
    * @param request 请求信息封装
    * @return 状态信息
     */
    @RequestMapping(value = Url.Wechat.COPY_ITEMS_ZHIFANG, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionCopyItemsFromZhifang(String mediaId, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        //门店处理,如果当店就返回自身
        Integer hqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, hqStoreId.toString());
        return weixinUploadService.actionCopyItemsFromZhifang(mediaId, accessToken, storeId);
    }

    /**
     * 更新图文消息
    * @author 高国藩
    * @date 2015年8月28日 下午5:13:59
    * @param updateItemsDto 图文消息
    * @param updateChangeThumb 更新数据库
    * @param updateArticleDto 详情
    * @param request 请求信息封装
    * @param itemNum 个数判断
    * @return 跳转
     */
    @RequestMapping(value = Url.Wechat.UPDATE_ITEM, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updateItmes(UpdateItemsDto updateItemsDto, UpdateArticleDto updateArticleDto, 
            UpdateChangeThumb updateChangeThumb, HttpServletRequest request, Integer itemNum) {
        Integer storeId = getStoreId(request);
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        return weixinUploadService.updateItems(updateItemsDto, updateArticleDto, updateChangeThumb, accessToken, itemNum);
    }

    /**
     * 从数据库中获取存储好的图文消息，用于展示
    * @author 高国藩
    * @param request 请求信息封装
    * @param response 返回结果
    * @date 2015年8月28日 上午11:33:19
    * @return 图文消息
     * @throws IOException 
     */
    @RequestMapping(value = Url.Wechat.ITEMS_MANAGE)
    public ModelAndView getItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer storeId = getStoreId(request);
        return weixinMessageService.getItems(storeId, request, response);
    }

    /**
     * 门店删除图文消息库
    * @author 高国藩
    * @date 2015年8月28日 下午5:13:59
    * @param mediaId 图文消息
    * @param request 请求信息封装
    * @param storeId 如果有storeId传入,说明为删除智放图文
    * @return 状态
     */
    @RequestMapping(value = Url.Wechat.DELETE_ITEM, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deleteItmes(String mediaId, Integer storeId, HttpServletRequest request) {
        if (storeId == null){
            storeId = getStoreId(request);
        }
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        return weixinUploadService.deleteItems(mediaId, accessToken);
    }
    
    /**
     * 新增菜单-本地库存储,此为ajax请求链接
    * @author 高国藩
    * @date 2015年8月6日 下午4:51:15
    * @param menu 菜单
    * @return 状态信息
     */
    @RequestMapping(value = Url.Wechat.ADD_MENU, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto addMenu(Menu menu) {
        return weixinMessageService.addMenu(menu);
    }

    /**
     * 获得菜单列表
    * @author 高国藩
    * @date 2015年8月6日 下午5:19:25
    * @param storeId 门店ID
    * @return 查询菜单信息
     */
    @RequestMapping(value = Url.Wechat.GETMENU)
    @ResponseBody
    public List<WechatMenuDto> menuListAction(Integer storeId) {
        return weixinMessageService.getAllMenu(storeId);
    }

    /**
     * 获得单一菜单元素
    * @author 高国藩
    * @date 2015年8月6日 下午5:19:25
    * @param menuId 菜单ID
    * @return 查询单个菜单信息
     */
    @RequestMapping(value = Url.Wechat.GET_ONE_MENU)
    @ResponseBody
    public Menu menuOneAction(Integer menuId) {
        return weixinMessageService.getMenu(menuId);
    }

    /**
     * 调用微信接口更新菜单信息
    * @author 高国藩
    * @date 2015年8月11日 上午11:25:21
    * @param request 请求封装
    * @return 状态信息
     */
    @RequestMapping(value = Url.Wechat.CREATE_MENU)
    @ResponseBody
    public BaseDto createMenu(HttpServletRequest request) {
        Integer storeId = getStoreId(request);
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        return weixinMessageService.createMenu(storeId, accessToken);
    }

    /**
     * 跳转开发商微信设置菜单页面
    * @author 高国藩
    * @date 2015年8月11日 上午11:25:48
    * @param request 请求封装
    * @return 返回页面
     */
    @RequestMapping(value = Url.Wechat.VIEW_LIST_MENU)
    public ModelAndView listAction(HttpServletRequest request) {
        return weixinMessageService.sendViewPage(request);
    }
    
    /**
     * 跳转至门店微信菜单设置页面
    * @author 高国藩
    * @date 2015年9月20日 下午2:25:26
    * @param request 请求
    * @return 附带参数,跳转页面
     */
    @RequestMapping(value = Url.Wechat.VIEW_LIST_STORE_MENU)
    public ModelAndView listStoreMenuAction(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return weixinMessageService.listStoreMenuAction(request, storeId);
    }
    
    /**
     * 复制菜单信息
     * 1)上传至微信
     * 2)新增如数据库
    * @author 高国藩
    * @param request 请求信息封装
    * @date 2015年9月20日 下午4:00:28
    * @return 状态
     */
    @RequestMapping(value = Url.Wechat.COPY_MENUS)
    @ResponseBody
    public BaseDto menuCopyAction(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        return weixinMessageService.menuCopyAction(storeId, accessToken);
    }
    
    /**
     * 关闭微信菜单
    * @author 高国藩
    * @date 2015年9月20日 下午5:36:44
    * @param request 请求信息封装
    * @return 状态
     */
    @RequestMapping(value = Url.Wechat.DELETE_COPY_MENUS)
    @ResponseBody
    public BaseDto closeMenusAction(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return weixinMessageService.closeMenusAction(storeId);
    }

    /**
     * 删除菜单
    * @author 高国藩
    * @date 2015年8月11日 上午11:26:43
    * @param menuId 菜单ID
    * @param request 请求封装
    * @return 状态信息
     */
    @RequestMapping(value = Url.Wechat.DELETE_MENU)
    @ResponseBody
    public BaseDto deleteMenuAction(Integer menuId, HttpServletRequest request) {
        Integer storeId = getStoreId(request);
        return weixinMessageService.deleteMenuAction(menuId, storeId);
    }

    /**
     * 设置菜单跳转链接
    * @author 高国藩
    * @date 2015年8月11日 上午11:27:07
    * @param menu 菜单
    * @return 状态信息
     */
    @RequestMapping(value = Url.Wechat.SET_MENU_URL)
    @ResponseBody
    public BaseDto setMenuUrl(Menu menu) {
        return weixinMessageService.setMenuUrl(menu);
    }
    
    /**
     * 进入发送图文消息页面,封装好数据
    * @author 高国藩
    * @date 2015年9月6日 下午5:24:09
    * @param request 请求信息封装
    * @return 进入页面
     */
    @RequestMapping(value = Url.Wechat.SEND_ITEMS)
    public ModelAndView viewSendItems(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return weixinMessageService.listItemsStatus(storeId);
    }

    /**
     * 根据openId进行发送图文消息
    * @author 高国藩
    * @date 2015年8月10日 下午11:47:39
    * @param level 会员卡等级
    * @param sceening 筛选器
    * @param mediaId 图文
    * @param fatherMediaId 复制的图文消息
    * @param request 请求信息封装
    * @return 接受信息
     */
    @RequestMapping(value = Url.Wechat.SEND_MESSAGE_ITEM)
    @ResponseBody
    public BaseDto sendItemByOpenId(String level, String sceening, String mediaId, String fatherMediaId, HttpServletRequest request) {
        Integer storeId = getStoreId(request);
        StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
        String accessToken;
        if (storeInfo.getStoreType()!=3){
            //不是分店,直接通过storeId去除accessToken
            accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        }
        else {
            //分店,通过总店的id进行去除accessToken
            Integer hqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
            accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, hqStoreId.toString());
        }
        return weixinMessageService.sendMessagesItem(level, sceening, mediaId,
                accessToken, storeId, fatherMediaId);
    }
    
    /**
     * 查询出发送次数为0的人员名单作为提醒
    * @author 高国藩
    * @date 2015年10月7日 下午5:52:27
    * @param level 选择的会员等级
    * @param sceening 筛选器
    * @param request 请求,用于获取session中的信息
    * @return 返回有0的会员列表
     */
    @RequestMapping(value = Url.Wechat.CHECK_WECHAT_COUNT)
    @ResponseBody
    public BaseDto checkWechatCount(String level, String sceening, HttpServletRequest request) {
        Integer storeId = getStoreId(request);
        return weixinMessageService.checkWechatCount(level, sceening, storeId);
    }

    /**
     * 根据openId进行发送文本消息
    * @author 高国藩
    * @date 2015年8月10日 下午11:47:39
    * @param touser 接受者
    * @param text 文本
    * @param request 请求信息封装
    * @return 接受信息
     */
    @RequestMapping(value = Url.Wechat.SEND_MESSAGE_TEXT)
    @ResponseBody
    public BaseDto sendTextByOpenId(List<String> touser, String text, HttpServletRequest request) {
        Integer storeId = getStoreId(request);
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        return weixinMessageService.sendMessagesText(touser, text, accessToken);
    }
    
    /**
     * 上传图片，保存图片至数据库中
    * @author 高国藩
    * @date 2015年9月2日 下午12:08:17
    * @param imgUrl 七牛图片地址
    * @param request 请求信息封装
    * @return 新增状态
     */
    @RequestMapping(value = Url.Wechat.UPDATE_IMG)
    @ResponseBody
    public BaseDto initImg(String imgUrl, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId.toString());
        return weixinUploadService.initImg(imgUrl, accessToken, storeId);
    }
    
    /**
     * 删除图片
    * @author 高国藩
    * @date 2015年9月2日 下午12:08:17
    * @param pictureId 主键标示
    * @return 状态
     */
    @RequestMapping(value = Url.Wechat.DELETE_IMG)
    @ResponseBody
    public BaseDto deleteImg(Integer pictureId){
        return weixinUploadService.deleteImg(pictureId);
    }
    
    
    /**
     * 进入图文消息统计页面
    * @author 高国藩
    * @date 2015年9月8日 上午9:56:55
    * @return 跳转页面
     */
    @RequestMapping(value = Url.Wechat.VIEW_ITEMS_STATUS)
    public ModelAndView viewItemsStatus(){
        return weixinUploadService.viewItemsStatus(0);
    }
    
    /**
     * 设置自动回复页面
    * @author 高国藩
    * @date 2015年9月10日 上午10:21:21
    * @param request 请求信息封装
    * @param response 返回数据
    * @return 跳转页面
     * @throws IOException 
     */
    @RequestMapping(value = Url.Wechat.VIEW_AUTO_REPLY, method=RequestMethod.GET)
    public ModelAndView viewAutoReply(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer storeId = getStoreId(request);
        return weixinMessageService.viewAutoReply(storeId, response);
    }
    
    /**
     * 设置关注回复信息
    * @author 高国藩
    * @date 2015年9月10日 上午11:38:10
    * @param autoType 类型 text item
    * @param message 回复内容
    * @param request 请求信息封装
    * @return 状态
     */
    @RequestMapping(value=Url.Wechat.SET_FOLLOW_REPLY, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionFollowAutoReply(String autoType, String message, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return weixinMessageService.actionFollowAutoReply(autoType, message, storeId);
    }
    
    /**
     * 设置消息回复信息
    * @author 高国藩
    * @date 2015年9月10日 上午11:38:10
    * @param autoType 类型 text item
    * @param message 回复内容
    * @param request 请求信息封装
    * @return 状态
     */
    @RequestMapping(value=Url.Wechat.SET_TEXT_REPLY, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionTextAutoReply(String autoType, String message, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return weixinMessageService.actionTextAutoReply(autoType, message, storeId);
    }

    /**
     * 删除自动回复消息设置
    * @author 高国藩
    * @date 2015年9月10日 下午3:26:01
    * @param msgStatus 删除的类型 1代表关注 2代表消息 
    * @param request 请求信息封装
    * @return 修改状态
     */
    @RequestMapping(value=Url.Wechat.DELETE_MSG_AUTO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionDeleteReply(Integer msgStatus, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return weixinMessageService.actionDeleteReply(msgStatus, storeId);
    }
    
    /**
     * 更新开发商的图文消息
    * @author 高国藩
    * @date 2015年9月17日 上午11:06:14
    * @param request 请求
    * @return 状态
     */
    @RequestMapping(value=Url.Wechat.UPLOAD_ITEMS, method = RequestMethod.GET)
    @ResponseBody
    public BaseDto actionUploadItems(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return weixinUploadService.actionUploadItems(storeId);
    }
    
    /**
     * 我的公众号页面
    * @author 高国藩
    * @date 2015年9月30日 下午3:58:03
    * @param request 请求数据封装
    * @return 跳转页面
     */
    @RequestMapping(value=Url.Wechat.VIEW_OFFICAL, method = RequestMethod.GET)
    public ModelAndView viewOfficialAccounts(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return weixinMessageService.viewOfficialAccounts(storeId);
    }
    
    /**
     * 关键字查询图文消息并用于展示
    * @author 高国藩
    * @date 2015年10月8日 上午10:30:50
    * @param content 关键字
    * @param storeId 门店,如果是0,查询智放,否则查询我的图文库
    * @param request 请求信息封装
    * @return 返回图文消息
     */
    @RequestMapping(value=Url.Wechat.ACTION_SERCH_ITEMS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionSerchItems(String content, Integer storeId, HttpServletRequest request){
        if (storeId == null){
            storeId = getStoreId(request);
        }
        return weixinMessageService.actionSerchItems(content, storeId);
    }
    
    /**
     * 保存门店微信关联
    * @author 高国藩
    * @date 2015年10月8日 上午10:30:50
    * @param storeWechat 信息
    * @param request 请求信息封装
    * @return 返回图文消息
     */
    @RequestMapping(value=Url.Wechat.ADD_STORE_WECHAT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto addStoreWechat(StoreWechat storeWechat, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        storeWechat.setStoreId(storeId);
        return weixinMessageService.addStoreWechat(storeWechat);
    }
    
    /**
     * 保存门店微信关联
    * @author 高国藩
    * @date 2015年10月8日 上午10:30:50
    * @param request 请求信息封装
    * @return 返回图文消息
     */
    @RequestMapping(value=Url.Wechat.DELETE_STORE_WECHAT, method = RequestMethod.GET)
    @ResponseBody
    public BaseDto deleteStoreWechat(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return weixinMessageService.deleteStoreWechat(storeId);
    }
    
    /**
     *                       对智放图文进行点赞操作
    * @author                高国藩
    * @date 2015年11月16日 下午2:23:54
    * @param request         请求封装 
    * @param mediaId         图文媒体的ID
    * @return                操作结束
     */
    @RequestMapping(value=Url.Wechat.PRAISE_ITEMS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto praiseItems(HttpServletRequest request, String mediaId){
        return weixinUploadService.praiseItems(getStoreId(request), mediaId);
    }
    
}
