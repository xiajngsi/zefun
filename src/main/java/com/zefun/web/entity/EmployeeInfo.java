package com.zefun.web.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * @author 张进军
 * @date 2015年11月20日 PM 15:24:14
 */
public class EmployeeInfo {
    /** 员工标识 */
    private Integer employeeId;

    /** 门店标识 */
    private Integer storeId;

    /** 部门标识 */
    private Integer deptId;

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

    /** 员工状态 1在职 2离职 3派遣 */
    private String employeeStatus;

    /** 入职日期 */
    private String entryDate;

    /** 离职日期 */
    private String leaveDate;

    /** 服务人数 */
    private Integer servicePeoples;

    /** 服务次数 */
    private Integer serviceCount;

    /** 服务评分 */
    private Float serviceScore;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 授权码 */
    private Integer accreditCode;

    /** 登录账号 */
    private String username;

    /** 密码 */
    private String password;

    /** 颜值 */
    private String salt;

    /** 个人描述 */
    private String employeeDesc;

    /** @param employeeId   员工标识 */
    public void setEmployeeId(Integer employeeId){
        this.employeeId = employeeId;
    }

    /** @return 员工标识 */
    public Integer getEmployeeId(){
        return employeeId;
    }

    /** @param storeId  门店标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 门店标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param deptId   部门标识 */
    public void setDeptId(Integer deptId){
        this.deptId = deptId;
    }

    /** @return 部门标识 */
    public Integer getDeptId(){
        return deptId;
    }

    /** @param positionId   岗位标识 */
    public void setPositionId(Integer positionId){
        this.positionId = positionId;
    }

    /** @return 岗位标识 */
    public Integer getPositionId(){
        return positionId;
    }

    /** @param levelId  级别标识 */
    public void setLevelId(Integer levelId){
        this.levelId = levelId;
    }

    /** @return 级别标识 */
    public Integer getLevelId(){
        return levelId;
    }

    /** @param recommendId  介绍人标识 */
    public void setRecommendId(Integer recommendId){
        this.recommendId = recommendId;
    }

    /** @return 介绍人标识 */
    public Integer getRecommendId(){
        return recommendId;
    }

    /** @param employeeCode 员工编码 */
    public void setEmployeeCode(Integer employeeCode){
        this.employeeCode = employeeCode;
    }

    /** @return 员工编码 */
    public Integer getEmployeeCode(){
        return employeeCode;
    }

    /** @param name 姓名 */
    public void setName(String name){
        this.name = name;
    }

    /** @return 姓名 */
    public String getName(){
        return name;
    }

    /** @param headImage    头像 */
    public void setHeadImage(String headImage){
        this.headImage = headImage;
    }

    /** @return 头像 */
    public String getHeadImage(){
        return headImage;
    }

    /** @param sex  性别 */
    public void setSex(String sex){
        this.sex = sex;
    }

    /** @return 性别 */
    public String getSex(){
        return sex;
    }

    /** @param birthday 生日 */
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    /** @return 生日 */
    public String getBirthday(){
        return birthday;
    }

    /** @param phone    手机号码 */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /** @return 手机号码 */
    public String getPhone(){
        return phone;
    }

    /** @param identityCard 身份证号码 */
    public void setIdentityCard(String identityCard){
        this.identityCard = identityCard;
    }

    /** @return 身份证号码 */
    public String getIdentityCard(){
        return identityCard;
    }

    /** @param healthCard   健康证号码 */
    public void setHealthCard(String healthCard){
        this.healthCard = healthCard;
    }

    /** @return 健康证号码 */
    public String getHealthCard(){
        return healthCard;
    }

    /** @param address  住址 */
    public void setAddress(String address){
        this.address = address;
    }

    /** @return 住址 */
    public String getAddress(){
        return address;
    }

    /** @param emergencyPhone   紧急联系电话 */
    public void setEmergencyPhone(String emergencyPhone){
        this.emergencyPhone = emergencyPhone;
    }

    /** @return 紧急联系电话 */
    public String getEmergencyPhone(){
        return emergencyPhone;
    }

    /** @param baseSalaries 基本工资 */
    public void setBaseSalaries(Integer baseSalaries){
        this.baseSalaries = baseSalaries;
    }

    /** @return 基本工资 */
    public Integer getBaseSalaries(){
        return baseSalaries;
    }

    /** @param positionSalaries 岗位工资 */
    public void setPositionSalaries(Integer positionSalaries){
        this.positionSalaries = positionSalaries;
    }

    /** @return 岗位工资 */
    public Integer getPositionSalaries(){
        return positionSalaries;
    }

    /** @param employeeStatus   员工状态 1在职 2离职 3派遣 */
    public void setEmployeeStatus(String employeeStatus){
        this.employeeStatus = employeeStatus;
    }

    /** @return 员工状态 1在职 2离职 3派遣 */
    public String getEmployeeStatus(){
        return employeeStatus;
    }

    /** @param entryDate    入职日期 */
    public void setEntryDate(String entryDate){
        this.entryDate = entryDate;
    }

    /** @return 入职日期 */
    public String getEntryDate(){
        return entryDate;
    }

    /** @param leaveDate    离职日期 */
    public void setLeaveDate(String leaveDate){
        this.leaveDate = leaveDate;
    }

    /** @return 离职日期 */
    public String getLeaveDate(){
        return leaveDate;
    }

    /** @param servicePeoples   服务人数 */
    public void setServicePeoples(Integer servicePeoples){
        this.servicePeoples = servicePeoples;
    }

    /** @return 服务人数 */
    public Integer getServicePeoples(){
        return servicePeoples;
    }

    /** @param serviceCount 服务次数 */
    public void setServiceCount(Integer serviceCount){
        this.serviceCount = serviceCount;
    }

    /** @return 服务次数 */
    public Integer getServiceCount(){
        return serviceCount;
    }

    /** @param serviceScore 服务评分 */
    public void setServiceScore(Float serviceScore){
        this.serviceScore = serviceScore;
    }

    /** @return 服务评分 */
    public Float getServiceScore(){
        return serviceScore;
    }

    /** @param createTime   创建时间 */
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    /** @return 创建时间 */
    public String getCreateTime(){
        return createTime;
    }

    /** @param updateTime   修改时间 */
    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    /** @return 修改时间 */
    public String getUpdateTime(){
        return updateTime;
    }

    /** @param isDeleted    是否删除(0:未删除,1:已删除) */
    public void setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
    }

    /** @return 是否删除(0:未删除,1:已删除) */
    public Integer getIsDeleted(){
        return isDeleted;
    }

    /** @param lastOperatorId   最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId){
        this.lastOperatorId = lastOperatorId;
    }

    /** @return 最后操作人标识 */
    public Integer getLastOperatorId(){
        return lastOperatorId;
    }

    /** @param accreditCode 授权码 */
    public void setAccreditCode(Integer accreditCode){
        this.accreditCode = accreditCode;
    }

    /** @return 授权码 */
    public Integer getAccreditCode(){
        return accreditCode;
    }

    /** @param username 登录账号 */
    public void setUsername(String username){
        this.username = username;
    }

    /** @return 登录账号 */
    public String getUsername(){
        return username;
    }

    /** @param password 密码 */
    public void setPassword(String password){
        this.password = password;
    }

    /** @return 密码 */
    public String getPassword(){
        return password;
    }

    /** @param salt 颜值 */
    public void setSalt(String salt){
        this.salt = salt;
    }

    /** @return 颜值 */
    public String getSalt(){
        return salt;
    }

    /** @param employeeDesc 个人描述 */
    public void setEmployeeDesc(String employeeDesc){
        this.employeeDesc = employeeDesc;
    }

    /** @return 个人描述 */
    public String getEmployeeDesc(){
        return employeeDesc;
    }
	
	@Override
	public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
            ToStringStyle.MULTI_LINE_STYLE);
    }

	@Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
	
}