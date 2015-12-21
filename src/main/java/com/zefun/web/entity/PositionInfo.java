package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月20日 PM 15:24:14
 */
public class PositionInfo {
    /** 岗位标识 */
    private Integer positionId;

    /** 门店标识 */
    private Integer storeId;

    /** 部门标识 */
    private Integer deptId;
    
    /** 部门编码 */
    private Integer deptCode;

    /** 岗位编号 */
    private Integer positionCode;

    /** 岗位名称 */
    private String positionName;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 0正常1删除 */
    private Integer isDeleted;

    /** 0不可跨部门1可以跨 */
    private Integer isDept;

    /** @param positionId   岗位标识 */
    public void setPositionId(Integer positionId){
        this.positionId = positionId;
    }

    /** @return 岗位标识 */
    public Integer getPositionId(){
        return positionId;
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

    public Integer getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    /** @param positionCode 岗位编号 */
    public void setPositionCode(Integer positionCode){
        this.positionCode = positionCode;
    }

    /** @return 岗位编号 */
    public Integer getPositionCode(){
        return positionCode;
    }

    /** @param positionName 岗位名称 */
    public void setPositionName(String positionName){
        this.positionName = positionName;
    }

    /** @return 岗位名称 */
    public String getPositionName(){
        return positionName;
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

    /** @param lastOperatorId   最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId){
        this.lastOperatorId = lastOperatorId;
    }

    /** @return 最后操作人标识 */
    public Integer getLastOperatorId(){
        return lastOperatorId;
    }

    /** @param isDeleted    0正常1删除 */
    public void setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
    }

    /** @return 0正常1删除 */
    public Integer getIsDeleted(){
        return isDeleted;
    }

    /** @param isDept   0不可跨部门1可以跨 */
    public void setIsDept(Integer isDept){
        this.isDept = isDept;
    }

    /** @return 0不可跨部门1可以跨 */
    public Integer getIsDept(){
        return isDept;
    }

}