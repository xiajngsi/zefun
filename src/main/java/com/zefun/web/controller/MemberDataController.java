package com.zefun.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.MemberInfoDataService;

/**
 * 会员管理
* @author 高国藩
* @date 2015年9月8日 下午5:50:53
 */
@Controller
public class MemberDataController extends BaseController{

	/**会员*/
	@Autowired
	private MemberInfoDataService memberInfoDataService;
    
    /**
     * 根据会员信息标识查询会员信息及账户信息
    * @author 王大爷
    * @date 2015年9月12日 下午4:19:03
    * @param memberId 会员信息标识
    * @return 会员信息及账户信息
     */
    @RequestMapping(value = Url.Member.SELECTBY_MEMBERDTO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectByMemberDto(Integer memberId){
        return memberInfoDataService.selectByMemberDto(memberId);
    }
    
    /**
     * 根据会员标识分页查询资金流水
    * @author 王大爷
    * @date 2015年9月14日 下午5:08:48
    * @param pageNo 页数
    * @param pageSize 显示数
    * @param memberId 会员信息标识
    * @param type 分页类型
    * @return BaseDto
     */
    @RequestMapping(value = Url.Member.SELECT_MONEYFLOW, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectMoneyFlow(Integer memberId, Integer pageNo, Integer pageSize, Integer type){
        return memberInfoDataService.selectPage(pageNo, pageSize, memberId, type);
    }
    
}
