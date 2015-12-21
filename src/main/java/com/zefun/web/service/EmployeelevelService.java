package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.EmployeeLevelDto;
import com.zefun.web.entity.EmployeeLevel;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.mapper.EmployeeLevelMapper;
import com.zefun.web.mapper.PositioninfoMapper;

/**
 * 职位信息
* @author 陈端斌
* @date 2015年8月5日 下午1:36:12 
*
 */
@Service
public class EmployeelevelService {
    /**
     * 职位信息
     */
	@Autowired
	private EmployeeLevelMapper employeelevelMapper;
	/**
     * 岗位信息
     */
	@Autowired
	private PositioninfoMapper positioninfoMapper;
	/**
	 * 
	 */
	@Autowired
    private RedisService redisService;
	/**
     * 轮牌接口
     */
    @Autowired
    ShiftMahjongService shiftMahjongService;
	/**
	 * 查询某个店铺的职位信息
	 * 默认返回该门店最前面10条数据
	* @author 陈端斌
	* @date 2015年8月5日 下午1:59:44
	* @param params 参数
	* @return ModelAndView
	 */
	public ModelAndView queryEmployeelevelinfo(Map<String, Object> params){
		Page<EmployeeLevelDto> list=selectPageForEmployeelevel(params, 1, App.System.API_DEFAULT_PAGE_SIZE);
		ModelAndView mav = new ModelAndView(View.Employeelevel.EMPLOYEELEVEL);
		mav.addObject("list", list);
		//获取岗位信息一起返回到页面
		PositionInfo positionInfo=new PositionInfo();
		int storeId=Integer.parseInt(params.get("storeId").toString());
		positionInfo.setStoreId(storeId);
		List<PositionInfo> positionlist=positioninfoMapper.queryposition(positionInfo);
		mav.addObject("positionlist", positionlist);
		return mav;
	}
	/**
	 * 翻页功能
	* @author chendb
	* @date 2015年8月7日 上午10:55:59
	* @param params 参数
	* @param pageNo 页码
	* @param pageSize 每页数量
	* @return BaseDto
	 */
	public BaseDto listAction(Map<String, Object> params, int pageNo, int pageSize){
		Page<EmployeeLevelDto> page = selectPageForEmployeelevel(params, pageNo, pageSize);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
	}
	/**
	 * 
	* @author chendb
	* @date 2015年8月11日 上午11:12:40
	* @param params 参数
	* @param pageNo 页码
	* @param pageSize 每页数量
	* @return Page<EmployeeLevelDto>
	 */
	private Page<EmployeeLevelDto> selectPageForEmployeelevel(Map<String, Object> params, int pageNo, int pageSize){
		Page<EmployeeLevelDto> page = new Page<EmployeeLevelDto>();
		page.setParams(params);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		List<EmployeeLevelDto> list = employeelevelMapper.queryEmployeelevelinfo(page);
		page.setResults(list);
		return page;
	}
	/**
	 * 新增功能
	* @author chendb
	* @date 2015年8月11日 上午11:13:26
	* @param map map
	* @return int
	 */
	public int addEmployeelevel(Map<String, Object> map){
		//判断职位名称是否已经存在
		List<EmployeeLevel> list=employeelevelMapper.islevelName(map);
		if (list.size()>0){
			//职位名称已经存在  返回
			return 1;	
		}
		employeelevelMapper.insert(map);
		return 0;
	}
	/**
	 * 修改岗位信息
	* @author chendb
	* @date 2015年8月11日 上午11:14:02
	* @param map bean
	* @return int
	 */
	public int updateEmployeelevel(Map<String, Object> map){
		//判断职位名称是否已经存在
		List<EmployeeLevel> list=employeelevelMapper.islevelName(map);
		if (list.size()>0){
			if (list.size()>1){
				//职位名称已经存在  返回
				return 1;
			}
			int id=list.get(0).getLevelId();
			//判断当前的id和数据库的id不是同一条数据  就说明已经存在了
			int id1=Integer.parseInt(map.get("levelId").toString());
			if (id!=id1){
				//职位名称已经存在  返回
				return 1;	
			}
		}
		employeelevelMapper.updateByPrimaryKeySelective(map);
		//获取职位底下的人员然后调用老王接口
		List<EmployeeDto> list1=employeelevelMapper.getlevelemployee(map);
		for (int i = 0; i < list1.size(); i++) {
		    EmployeeDto employeeDto=list1.get(i);
		    shiftMahjongService.addShiftMahjongEmployee(employeeDto);
        }
		return 0;
	}
	/**
	 * 删除岗位信息
	* @author chendb
	* @date 2015年8月11日 上午11:14:52
	* @param employeelevel bean
	* @return int
	 */
	public int deleteEmployeelevel(EmployeeLevel employeelevel){
		//判断职位是否被人员引用了
		int count=employeelevelMapper.islevelIdcount(employeelevel);
		if (count>0){
			//已经有人员使用了
			return 1;
		}
		employeelevelMapper.deleteEmployeelevel(employeelevel);
		return 0;
	}
	/**
	 * 公共下拉框获取职位信息
	* @author chendb
	* @date 2015年8月11日 上午11:15:18
	* @param employeelevel bean
	* @return  List<EmployeeLevel>
	 */
	public List<EmployeeLevel> queryEmployeelevel(EmployeeLevel employeelevel){
	    List<EmployeeLevel>list=employeelevelMapper.queryEmployeeLevel(employeelevel);
		return list;
	}
	/**
	 * 获取职位详情
	* @author chendb
	* @date 2015年8月11日 上午11:15:45
	* @param levelId 职位标识
	* @return EmployeeLevelDto
	 */
	public EmployeeLevelDto leveldetail(Integer levelId){
		EmployeeLevelDto info=employeelevelMapper.leveldetail(levelId);
		return info;
	}
	
	/**
	 * 根据职位获取底下的员工
	* @author chendb
	* @date 2015年9月9日 上午10:05:14
	* @param levelId 职位标识
	* @param storeId 门店标识
	* @return  List<EmployeeLevelDto>
	 */
	public List<EmployeeDto>getlevelemployee(Integer levelId, Integer storeId){
	    Map<String, Object> map=new HashMap<String, Object>();
	    map.put("levelId", levelId);
	    map.put("storeId", storeId);
	    List<EmployeeDto> list=employeelevelMapper.getlevelemployee(map);
	    return list;
	    
	}
	
	/**
     * 根据多个等级Id查询列表
    * @author 洪秋霞
    * @date 2015年9月21日 下午2:12:13
    * @param levelIds 会员等级标识
    * @return List<MemberLevel>
     */
    public List<EmployeeLevel> queryByLevelIds(List<Integer> levelIds){
        return employeelevelMapper.selectByLevelIds(levelIds);
    }
}
