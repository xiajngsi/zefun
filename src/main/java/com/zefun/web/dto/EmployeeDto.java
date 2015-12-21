package com.zefun.web.dto;


/**
 * 人员信息
* @author 陈端斌
* @date 2015年8月7日 下午5:22:24 
*
 */
public class EmployeeDto {
	
    /** 岗位名称 */
    private String positionName;
    
    /** 职位名称*/
    private String levelName;
    
    /** 部门名称*/
    private String deptName;
    
    /** 部门标识*/
    private Integer deptId;
    
    /** 员工标识 */
    private Integer employeeId;
    
    /** 门店标识 */
    private Integer storeId;

    /** 岗位标识 */
    private Integer positionId;

    /** 级别标识 */
    private Integer levelId;

    /** 介绍人标识 */
    private Integer recommendId;

    /** 员工编码 */
    private Integer employeeCode;

    /** 姓名 */
    private String name;

    /** 头像 */
    private String headImage;

    /** 性别 */
    private String sex;

    /** 生日 */
    private String birthday;

    /** 手机号码 */
    private String phone;

    /** 身份证号码 */
    private String identityCard;

    /** 健康证号码 */
    private String healthCard;

    /** 住址 */
    private String address;

    /** 紧急联系电话 */
    private String emergencyPhone;

    /** 基本工资 */
    private Integer baseSalaries;

    /** 岗位工资 */
    private Integer positionSalaries;

    /** 员工状态 */
    private String employeeStatus;

    /** 入职日期 */
    private String entryDate;

    /** 离职日期 */
    private String leaveDate;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 最后操作人标识 */
    private Integer lastOperatorId;
    /** 角色 */
    private Integer roleId;
    /** 密码 */
    private String userPwd;
    /** 研值 */
    private String pwdSalt;

    /** 授权码 */
    private Integer accreditCode;
    /** 人员目标 */
    private ObjectiveDto employeeObjective;
    /**目标 月份*/
    private String month;
    /**导入时候的行数*/
    private int num;
    /**员工账号*/
    private String userName;
    
    /***/
    private String servicePeoples;
    
    /***/
    private String serviceCount;
    
    /***/
    private String serviceScore;
    /**人员简介*/
    private String employeeDesc;
  

    

    public String getEmployeeDesc() {
        return employeeDesc;
    }

    public void setEmployeeDesc(String employeeDesc) {
        this.employeeDesc = employeeDesc;
    }

    public String getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(String serviceCount) {
        this.serviceCount = serviceCount;
    }

    public String getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(String serviceScore) {
        this.serviceScore = serviceScore;
    }

    public String getServicePeoples() {
        return servicePeoples;
    }

    public void setServicePeoples(String servicePeoples) {
        this.servicePeoples = servicePeoples;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getPwdSalt() {
        return pwdSalt;
    }

    public void setPwdSalt(String pwdSalt) {
        this.pwdSalt = pwdSalt;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public ObjectiveDto getEmployeeObjective() {
        return employeeObjective;
    }

    public void setEmployeeObjective(ObjectiveDto employeeObjective) {
        this.employeeObjective = employeeObjective;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public Integer getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Integer employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public Integer getBaseSalaries() {
        return baseSalaries;
    }

    public void setBaseSalaries(Integer baseSalaries) {
        this.baseSalaries = baseSalaries;
    }

    public Integer getPositionSalaries() {
        return positionSalaries;
    }

    public void setPositionSalaries(Integer positionSalaries) {
        this.positionSalaries = positionSalaries;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Integer getAccreditCode() {
        return accreditCode;
    }

    public void setAccreditCode(Integer accreditCode) {
        this.accreditCode = accreditCode;
    }

	
    

}
