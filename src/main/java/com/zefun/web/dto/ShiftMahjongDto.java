package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.ShiftMahjongEmployee;

/**
 * 轮牌信息DTO
* @author 王大爷
* @date 2015年8月11日 上午11:17:14
 */
public class ShiftMahjongDto {
	
    /** 轮牌信息标识 */
    private Integer shiftMahjongId;

    /** 轮牌名称 */
    private String shiftMahjongName;

    /** 店铺标识 */
    private Integer storeId;
    
    /** 部门标识*/
    private Integer deptId;

    /** 上牌规则（1：考勤轮牌、2：持续轮牌） */
    private Integer shiftMahjongUp;

    /** 轮牌规则（1：指定不轮牌、2：指定某只后轮牌） */
    private Integer shiftMahjongRule;

    /** 轮牌性质(1：助理轮牌、2：技师轮牌)*/
    private Integer nature;
    
    /** 轮牌指定人数 */
    private Integer appointNumber;

    /** 创建时间 */
    private String createTime;

    /** 操作人标识 */
    private Integer operatorId;
    
    /** 轮牌员工信息集合*/
    private List<ShiftMahjongEmployee> shiftMahjongEmployeeList;

    /**
     * 
    * @author 王大爷
    * @date 2015年9月21日 下午5:12:05
     */
    public ShiftMahjongDto() {
        super();
    }


    /**
     * 
    * @author 王大爷
    * @date 2015年9月21日 下午4:16:09
    * @param shiftMahjongId 轮牌信息标识
    * @param shiftMahjongName 轮牌名称 
    * @param storeId 店铺标识
    * @param deptId 部门标识
    * @param shiftMahjongUp 上牌规则（1：考勤轮牌、2：持续轮牌）
    * @param shiftMahjongRule 轮牌规则（1：指定不轮牌、2：指定某只后轮牌）
    * @param nature 轮牌性质(1：助理轮牌、2：技师轮牌)
    * @param appointNumber 轮牌指定人数
    * @param createTime 创建时间 
    * @param operatorId 操作人标识
    * @param shiftMahjongEmployeeList 轮牌员工信息集合
     */
    public ShiftMahjongDto(Integer shiftMahjongId, String shiftMahjongName,
            Integer storeId, Integer deptId, Integer shiftMahjongUp,
            Integer shiftMahjongRule, Integer nature, Integer appointNumber,
            String createTime, Integer operatorId,
            List<ShiftMahjongEmployee> shiftMahjongEmployeeList) {
        super();
        this.shiftMahjongId = shiftMahjongId;
        this.shiftMahjongName = shiftMahjongName;
        this.storeId = storeId;
        this.deptId = deptId;
        this.shiftMahjongUp = shiftMahjongUp;
        this.shiftMahjongRule = shiftMahjongRule;
        this.nature = nature;
        this.appointNumber = appointNumber;
        this.createTime = createTime;
        this.operatorId = operatorId;
        this.shiftMahjongEmployeeList = shiftMahjongEmployeeList;
    }



    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    
    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public Integer getShiftMahjongId() {
        return shiftMahjongId;
    }

    public void setShiftMahjongId(Integer shiftMahjongId) {
        this.shiftMahjongId = shiftMahjongId;
    }

    public String getShiftMahjongName() {
        return shiftMahjongName;
    }

    public void setShiftMahjongName(String shiftMahjongName) {
        this.shiftMahjongName = shiftMahjongName == null ? null : shiftMahjongName.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getShiftMahjongUp() {
        return shiftMahjongUp;
    }

    public void setShiftMahjongUp(Integer shiftMahjongUp) {
        this.shiftMahjongUp = shiftMahjongUp;
    }

    public Integer getShiftMahjongRule() {
        return shiftMahjongRule;
    }

    public void setShiftMahjongRule(Integer shiftMahjongRule) {
        this.shiftMahjongRule = shiftMahjongRule;
    }

    public Integer getAppointNumber() {
        return appointNumber;
    }

    public void setAppointNumber(Integer appointNumber) {
        this.appointNumber = appointNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

	public List<ShiftMahjongEmployee> getShiftMahjongEmployeeList() {
		return shiftMahjongEmployeeList;
	}

	public void setShiftMahjongEmployeeList(List<ShiftMahjongEmployee> shiftMahjongEmployeeList) {
		this.shiftMahjongEmployeeList = shiftMahjongEmployeeList;
	}
    
    
}