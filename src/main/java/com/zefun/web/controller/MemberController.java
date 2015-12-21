package com.zefun.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberInfoDto;
import com.zefun.web.dto.ScreeningDto;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberScreening;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.service.MemberInfoService;

/**
 * 会员管理
* @author 高国藩
* @date 2015年9月8日 下午5:50:53
 */
@Controller
public class MemberController extends BaseController{

	/**会员*/
	@Autowired
	private MemberInfoService memberInfoService;
    /**门店设置*/
    @Autowired
    private StoreSettingMapper storeSettingMapper;
	
	
	/**
	 * 会员数据展示
	* @author 高国藩
	* @date 2015年9月8日 下午5:53:46
	* @param request 请求数据封装
	* @return 跳转页面
	 */
	@RequestMapping(value = Url.Member.MEMBER_VIEW_LIST, method = RequestMethod.GET)
	public ModelAndView memberListView(HttpServletRequest request){
	    Integer storeId = getStoreId(request);
		return memberInfoService.selectMemberByStoreId(storeId);
	}
	
	/**
	 * 新增选择器
	* @author 高国藩
	* @date 2015年9月8日 下午8:10:31
	* @param memberScreening  实体
	* @param request          请求数据封装
	* @param branchOffice     总店查询分店
	* @return                 状态
	 */
	@RequestMapping(value = Url.Member.MEMBER_SCREEN_ADD, method = RequestMethod.POST)
	@ResponseBody
    public BaseDto addMemberScreening(MemberScreening memberScreening, HttpServletRequest request, String branchOffice){
        Integer storeId = getStoreId(request);
        return memberInfoService.addMemberScreening(storeId, memberScreening, branchOffice);
    }
	
	/**
     * 通过预设的条件查询会员数据
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param screeningDto  查询条件
    * @param page          分页
    * @param request       请求数据封装
    * @param branchOffice  分店storeId数组
    * @return              状态
     */
    @RequestMapping(value = Url.Member.SERCH_MEMBER_BY_TREM, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto listMemberInfosByTerm(ScreeningDto screeningDto, Page<MemberInfoDto> page, 
            HttpServletRequest request, String branchOffice){
        Integer storeId = getStoreId(request);
        screeningDto.setStoreId(storeId);
        return memberInfoService.listMemberInfosByTerm(screeningDto, page, branchOffice);
    } 
    
    /**
     * 通过姓名电话条件查询会员数据
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param content 查询条件
    * @param page    分页
    * @param sex     性别
    * @param levelId 会员等级
    * @param request 请求数据封装
    * @return        状态
     */
    @RequestMapping(value = Url.Member.SERCH_MEMBER_BY_CONTENT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto listMemberInfosByContent(String content, Page<MemberInfoDto> page, HttpServletRequest request, String sex, Integer levelId){
        Integer storeId = getStoreId(request);
        return memberInfoService.listMemberInfosByContent(storeId, content, page, sex, levelId);
    } 
	
	/**
     * 会员分组页面
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param request 请求数据封装
    * @return        跳转页面 
     */
    @RequestMapping(value = Url.Member.VIEW_CENSUS_LIST, method = RequestMethod.GET)
    public ModelAndView viewCensusList(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return memberInfoService.viewCensusList(storeId);
    }
	
    /**
     * 根据选择器来查询会员数据
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param groupId   筛选器
    * @param page      分页
    * @param request   请求数据封装
    * @return          状态
     */
    @RequestMapping(value = Url.Member.VIEW_LIST_BY_SCREEN, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto viewListMemberByScreen(Integer groupId, Page<MemberInfoDto> page, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return memberInfoService.viewListMemberByScreen(storeId, groupId, page);
    }
    
    /**
     * 会员修改资料
    * @author 高国藩
    * @date 2015年12月18日 下午3:44:44
    * @param request       请求
    * @param memberInfo    会员数据
    * @return              修改状态
     */
    @RequestMapping(value = Url.Member.ACTION_UPDATE_MEMBER, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionUpdateMemberInfo(HttpServletRequest request, MemberInfo memberInfo){
        Integer storeId = getStoreId(request);
        return memberInfoService.updateMemberInfo(memberInfo, storeId);
    }
    
    /**
     * 校验会员是否存在
    * @author 王大爷
    * @date 2015年9月12日 下午2:55:04
    * @param phone     手机号码
    * @param request   请求数据封装
    * @return BaseDto
     */
    @RequestMapping(value = Url.Member.SELECT_MEMBER_BYPHONE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectMemberByPhone(String phone, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        MemberBaseDto memberBaseDto = memberInfoService.selectMemberInfoByPhone(phone, storeId);
        if (memberBaseDto == null) {
            return new BaseDto(41007, "会员号码不存在，努力回忆下");
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, memberBaseDto);
        }
    }
    
    /**
     * 删除会员分组
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param groupId 筛选器
    * @return 状态
     */
    @RequestMapping(value = Url.Member.DELETE_CENSUS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deleteMemberCensus(Integer groupId){
        return memberInfoService.deleteMemberCensus(groupId);
    }
    
    /**
     * 会员导入项
    * @author 高国藩
    * @date 2015年11月18日 下午3:41:59
    * @param file           excle文件
    * @param request        请求
    * @param response       相应
    * @return               状态
     * @throws Exception    异常
     */
    @RequestMapping(value = Url.Member.IMPORTEXCLE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto importExcle(@RequestParam("file") MultipartFile[] file,
              HttpServletRequest request, HttpServletResponse response) throws Exception   {
        Integer storeId=getStoreId(request);
        Integer lastOperatorId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        String storeName = request.getParameter("storeName");
        StoreSetting setting = storeSettingMapper.selectByPrimaryKey(storeId);
        if (setting.getLastFacilitator()!=null&&!"".equals(setting.getLastFacilitator())){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "不可重复导入");
        }
        memberInfoService.updateStoreSet(storeId, storeName);
        if (storeName.equals("盛传")){
            return memberInfoService.importExcleSc(file[0], storeId, lastOperatorId, response);
        }
        else if (storeName.equals("左右")){
            return memberInfoService.importExcleZy(file[0], storeId, lastOperatorId, response);
        }
        else if (storeName.equals("云浩企汇通")){
            return memberInfoService.importExcleHt(file[0], storeId, lastOperatorId, response);
        }
        else if (storeName.equals("博卡")){
            return memberInfoService.importExcleBk(file[0], file[1], storeId, lastOperatorId, response);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "暂不支持其他服务商数据导入");
        }
    }
    
    /**
     * 进入错误会员统计页面
    * @author 高国藩
    * @date 2015年12月7日 上午10:37:39
    * @param request      请求
    * @param response     结果
    * @return             返回页面 
     */
    @RequestMapping(value = Url.Member.VIEW_ERROR_MEMBER, method = RequestMethod.GET)
    public ModelAndView viewErrorMember(HttpServletRequest request, HttpServletResponse response){
        Integer storeId=getStoreId(request);
        return memberInfoService.viewErrorMember(storeId);
    }
    
    /**
     * 会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param request                请求数据
    * @param id                     异常id
    * @param memberId               会员id
    * @param type                   类型(1:盛传 2:左右 3:企汇通 4:博卡)
    * @return                       状态提示
     */
    @RequestMapping(value = Url.Member.MEMBER_BALAND_MOVE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto balandMemberMove(HttpServletRequest request, Integer type, Integer id, Integer memberId){
        Integer storeId=getStoreId(request);
        Integer lastOperatorId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        return memberInfoService.balandMemberMove(storeId, lastOperatorId, type, id, memberId);
    }
    
    /**
     *  异常会员套餐迁移
    * @author 高国藩
    * @date 2015年12月19日 下午1:47:11
    * @param request          请求数据
    * @param type             类型(1:盛传 2:左右 3:企汇通 4:博卡)
    * @param id               异常会员id
    * @param memberId         迁移会员id
    * @param comboName        套餐名称
    * @param overdueTime      过期时间
    * @param projectId        项目id
    * @param projectCount     项目数量
    * @return                 状态
     */
    @RequestMapping(value = Url.Member.MEMBER_COMBO_MOVE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto comboMemberMove(HttpServletRequest request, Integer type, Integer id, Integer memberId, String comboName, 
            String overdueTime, Integer projectId, Integer projectCount){
        Integer storeId=getStoreId(request);
        Integer lastOperatorId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        return memberInfoService.comboMemberMove(storeId, lastOperatorId, type, id, memberId, comboName, overdueTime, projectId, projectCount);
    }
    
    /**
     * 分页查询异常会员
    * @author 高国藩
    * @date 2015年12月7日 下午2:28:01
    * @param request   请求
    * @param pageNo    页码
    * @param pageSize  一夜
    * @param content   搜索条件
    * @return          集合
     */
    @RequestMapping(value = Url.Member.VIEW_ERROR_MEMBER, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto viewErrorMember(HttpServletRequest request, Integer pageNo, Integer pageSize, String content){
        Integer storeId=getStoreId(request);
        return memberInfoService.viewErrorMember(storeId, pageNo, pageSize, content);
    }
    
    
    /**
     * 会员错误数据删除操作
    * @author 张进军
    * @date Dec 13, 2015 12:59:05 PM
    * @param type     服务商类型(1:盛传，2:左右，3:云浩)
    * @param id       错误数据标识
    * @param request  请求
    * @return         成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.Member.ACTION_ERROR_MEMBER_DELETE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deleteErrorMemberAction(HttpServletRequest request, Integer type, Integer id){
        Integer userId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        return memberInfoService.deleteErrorMemberAction(type, id, userId);
    }
    
    
    /**
     * 总店查看分店会员数据
    * @author 高国藩
    * @date 2015年12月7日 上午10:37:39
    * @param request      请求
    * @param response     结果
    * @return             返回页面 
     */
    @RequestMapping(value = Url.Member.VIEW_BASE_MEMBER, method = RequestMethod.GET)
    public ModelAndView viewBaserMember(HttpServletRequest request, HttpServletResponse response){
        Integer storeId=getStoreId(request);
        return memberInfoService.viewBaseMember(storeId);
    }
    
    /**
     * 导出异常会员数据
    * @author 高国藩
    * @date 2015年12月17日 下午5:24:26
    * @param request         请求
    * @return                导出
    * @throws IOException    异常 
     */
    @RequestMapping(value = Url.Member.DOWN_ERROR_MEMBER, method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadErrorMember(HttpServletRequest request) throws IOException {
        Integer storeId=getStoreId(request);
        return memberInfoService.downloadErrorMember(storeId);
    }

}
