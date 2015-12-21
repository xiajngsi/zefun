package com.zefun.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.enums.EmployeeRewardTypeEnum;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeRewardDto;
import com.zefun.web.entity.EmployeeReward;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreManageRule;
import com.zefun.web.mapper.EmployeeRewardMapper;
import com.zefun.web.mapper.StoreManageRuleMapper;
import com.zefun.web.vo.EmployeeRewardVo;

/**
 * 员工奖惩记录service
 * @author lzc
 *
 */
@Service
public class EmployeeRewardService {
	
	/** 员工奖惩映射 */
    @Autowired
    private EmployeeRewardMapper employeeRewardMapper;
    
    /** 门店管理制度映射 */
    @Autowired
    private StoreManageRuleMapper storeManageRuleMapper;
    
    /**
     * 查询员工奖惩汇总
     * @param vo  员工奖惩vo
     * @return  ModelAndView
     */
    public ModelAndView findCountEmployeeRewardHome(EmployeeRewardVo vo) {
    	vo.setPageNo(1);
    	vo.setPageSize(App.System.API_DEFAULT_PAGE_SIZE);
    	Page<EmployeeRewardDto> page = findCountEmployeeRewardByPage(vo);
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("page", page);
    	mav.setViewName(View.EmployeeReward.HOME);
    	return mav;
    }
    
    /**
     * 分页查询员工奖惩汇总
     * @param page 分页
     * @param vo 查询条件
     * @return 奖惩汇总
     */
    public BaseDto findListAction(Page<EmployeeRewardDto> page, EmployeeRewardVo vo) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("storeId", vo.getStoreId());
		params.put("rewardType", vo.getRule());
		params.put("time", vo.getTime());
		params.put("employeeName", vo.getEmployeeName());
		page.setParams(params);
		List<EmployeeRewardDto> list = employeeRewardMapper.selectCountRewardByPage(page);
		page.setResults(classify(list));
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    /**
     * 员工奖惩记录汇总分页查询
     * @param vo 员工奖惩vo
     * @return Page<EmployeeRewardDto>
     */
    public Page<EmployeeRewardDto> findCountEmployeeRewardByPage(EmployeeRewardVo vo) {
    	Page<EmployeeRewardDto> page = new Page<EmployeeRewardDto>();
    	page.setPageNo(vo.getPageNo());
		page.setPageSize(vo.getPageSize());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", vo.getStoreId());
		params.put("rewardType", vo.getRule());
		params.put("time", vo.getTime());
		params.put("employeeName", vo.getEmployeeName());
		page.setParams(params);
		List<EmployeeRewardDto> list = employeeRewardMapper.selectCountRewardByPage(page);
		//给查询的奖惩总数归类
		page.setResults(classify(list));
        return page;
    }
    
    /**
     * 分页查询奖惩详细action
     * @param page  页码页距查询条件
     * @param vo  其他查询条件
     * @return  带状态奖惩分页查询结果
     */
    public BaseDto findEmployeeRewardByPageAction(Page<EmployeeReward> page, EmployeeRewardVo vo) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("type", vo.getType());
		params.put("employeeId", vo.getEmployeeId());
		params.put("time", vo.getTime());
		page.setParams(params);
		List<EmployeeReward> list = employeeRewardMapper.selectRewardByPage(page);
		page.setResults(list);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    /**
     * 分页查询奖惩详细服务
     * @param vo  查询条件
     * @return  奖惩分页内容
     */
    public Page<EmployeeReward> findEmployeeRewardByPage(EmployeeRewardVo vo) {
    	Page<EmployeeReward> page = new Page<EmployeeReward>();
    	page.setPageNo(vo.getPageNo());
		page.setPageSize(vo.getPageSize());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", vo.getType());
		params.put("employeeId", vo.getEmployeeId());
		params.put("time", vo.getTime());
		page.setParams(params);
		List<EmployeeReward> list = employeeRewardMapper.selectRewardByPage(page);
		page.setResults(list);
		return page;
    }
    
    /**
     * 添加奖惩
     * @param userId  当前操作用户id
     * @param storeId  店铺id
     * @param vo  添加参数详情
     * @return  添加结果
     */
    public BaseDto addEmployeeReward(int userId, int storeId, EmployeeRewardVo vo) {
    	EmployeeReward er = new EmployeeReward();
    	er.setEmployeeId(vo.getEmployeeId());
    	er.setType(vo.getType());
    	er.setIsReward(decideIsReward(vo.getType()));
    	er.setModifytime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	er.setReasons(vo.getReasons());
    	er.setModifyer(userId);
    	er.setNumber(getMoneyByType(vo.getType(), storeId));
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "添加成功");
    }
    
    /**
     * 给查询的奖惩总数归类
     * @param employeeRewardDtoList  List<EmployeeRewardDto>
     * @return  汇总好的List<EmployeeRewardDto>
     */
    public List<EmployeeRewardDto> classify(List<EmployeeRewardDto> employeeRewardDtoList) {
    	Map<Integer, EmployeeRewardDto> map = new HashMap<>();
    	List<EmployeeRewardDto> list = new ArrayList<EmployeeRewardDto>();
    	for (int i=0; i<employeeRewardDtoList.size(); i++) {
    		//EmployeeRewardDto mapDto = map.get(employeeRewardDtoList.get(i).getEmployeeId());
    		//遍历map看是否存在该员工(与employeeRewardDtoList.get(i).getEmployeeId()比对)
    		EmployeeRewardDto mapDto = null;
    		for (Integer key : map.keySet()) {
    			if (key == employeeRewardDtoList.get(i).getEmployeeId()) {
    				mapDto = map.get(employeeRewardDtoList.get(i).getEmployeeId());
    				break;
    			}
    		}
    		if (mapDto != null) {
    			map.put(employeeRewardDtoList.get(i).getEmployeeId(), dataFill(employeeRewardDtoList.get(i), mapDto, true));
    		}
    		else {
    			map.put(employeeRewardDtoList.get(i).getEmployeeId(), dataFill(employeeRewardDtoList.get(i), mapDto, false));
    		}
    	}
    	for (Integer key : map.keySet()) {
    		list.add(map.get(key));
    	}
    	return list;
    }
    
    /**
     * 填充数据
     * @param queryDto  数据库查询出的dto
     * @param tempDto  临时(转移)存放dto
     * @param exist  true存在，false不存在
     * @return  临时(转移)存放dto(tempDto)
     */
    private EmployeeRewardDto dataFill(EmployeeRewardDto queryDto, EmployeeRewardDto tempDto, boolean exist) {
    	if (!exist) {
    		tempDto = new EmployeeRewardDto();
    	}
    	if (queryDto.getType() != null && queryDto.getCount() != null) {
    		if (queryDto.getType().equals("1")) {
    			tempDto.setCountOfLate(queryDto.getCount());
    			if (queryDto.getMoney() != null) {
    				tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
    			}
    		} 
    		else if (queryDto.getType().equals("2")) {
    			tempDto.setCountOfEarlyLeave(queryDto.getCount());
    			if (queryDto.getMoney() != null) {
    				tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
    			}
    		}
			else if (queryDto.getType().equals("3")) {
				tempDto.setCountOfHoliday(queryDto.getCount());
				if (queryDto.getMoney() != null) {
					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
    			}
			}
			else if (queryDto.getType().equals("4")) {
				tempDto.setCountOfAbsenteeism(queryDto.getCount());
				if (queryDto.getMoney() != null) {
					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
    			}
			}
			else if (queryDto.getType().equals("5")) {
				tempDto.setCountOfAttendance(queryDto.getCount());
				if (queryDto.getMoney() != null) {
    				tempDto.setMoneyOfReward(queryDto.getMoney() + tempDto.getMoneyOfReward());
    			}
			}
			else if (queryDto.getType().equals("6")) {
				tempDto.setCountOfSmallMistake(queryDto.getCount());
				if (queryDto.getMoney() != null) {
					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
    			}
			}
			else if (queryDto.getType().equals("7")) {
				tempDto.setCountOfSeriousMistake(queryDto.getCount());
				if (queryDto.getMoney() != null) {
					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
    			}
			}
			else if (queryDto.getType().equals("8")) {
				tempDto.setCountOfWarning(queryDto.getCount());
				if (queryDto.getMoney() != null) {
					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
    			}
			}
			else if (queryDto.getType().equals("9")) {
				tempDto.setCountOfFavourable(queryDto.getCount());
				if (queryDto.getMoney() != null) {
					tempDto.setMoneyOfReward(queryDto.getMoney() + tempDto.getMoneyOfReward());
    			}
			}
			else if (queryDto.getType().equals("10")) {
				tempDto.setCountOfBadpost(queryDto.getCount());
				if (queryDto.getMoney() != null) {
					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
    			}
			}
			else if (queryDto.getType().equals("11")) {
				tempDto.setCountOfComplaint(queryDto.getCount());
				if (queryDto.getMoney() != null) {
					tempDto.setMoneyOfPunishment(queryDto.getMoney() + tempDto.getMoneyOfPunishment());
    			}
			}
    	}
    	if (!exist) {
    		tempDto.setEmployeeId(queryDto.getEmployeeId());
    		//tempDto.setType(queryDto.getType());
    		if (queryDto.getIsReward() != null) {
        		tempDto.setIsReward(queryDto.getIsReward());
        	}
        	if (queryDto.getNumber() != null) {
        		tempDto.setNumber(queryDto.getNumber());
        	}
        	if (queryDto.getModifyer() != null) {
        		tempDto.setModifyer(queryDto.getModifyer());
        	}
        	if (queryDto.getModifytime() != null) {
        		tempDto.setModifytime(queryDto.getModifytime());
        	}
        	if (queryDto.getReasons() != null) {
        		tempDto.setReasons(queryDto.getReasons());
        	}
        	if (queryDto.getEmployeeName() != null) {
        		tempDto.setEmployeeName(queryDto.getEmployeeName());
        	}
        	if (queryDto.getEmployeeCode() != null) {
        		tempDto.setEmployeeCode(queryDto.getEmployeeCode());
        	}
    	}
    	return tempDto;
    }
    
    /**
     * 根据奖惩类型判断是否奖励
     * @param type  奖惩类型
     * @return  "1"(奖励), "0"(惩罚)
     */
    private String decideIsReward(String type) {
    	if (type.equals(String.valueOf(EmployeeRewardTypeEnum.FAVOURABLE.getEmployeeRewardType())) 
    			  || type.equals(String.valueOf(EmployeeRewardTypeEnum.ATTENDANCE.getEmployeeRewardType()))) {
    		return "1";
    	}
    	return "0";
    }
    
    /**
     * 查询店铺规则违规金额
     * @param type  奖惩类型
     * @param storeId  店铺id
     * @return  该店铺规则违规金额
     */
    private double getMoneyByType(String type, int storeId) {
    	String ruleName = null;
    	if ("1".equals(type)) {
    		ruleName = "迟到";
    	}
    	else if ("2".equals(type)) {
    		ruleName = "早退";
    	}
    	else if ("3".equals(type)) {
    		ruleName = "请假";
    	}
    	else if ("4".equals(type)) {
    		ruleName = "旷工";
    	}
    	else if ("5".equals(type)) {
    		ruleName = "全勤";
    	}
    	else if ("6".equals(type)) {
    		ruleName = "小过";
    	}
    	else if ("7".equals(type)) {
    		ruleName = "大过";
    	}
    	else if ("8".equals(type)) {
    		ruleName = "警告";
    	}
    	else if ("9".equals(type)) {
    		ruleName = "好评";
    	}
    	else if ("10".equals(type)) {
    		ruleName = "差评";
    	}
    	else if ("11".equals(type)) {
    		ruleName = "投诉";
    	}
    	StoreManageRule smr = storeManageRuleMapper.selectRuleByRuleNameAndStoreId(storeId, ruleName);
    	return smr.getProcessMoney().doubleValue();
    }
    
}
