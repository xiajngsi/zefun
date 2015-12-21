package com.zefun.web.entity;


/**
 * 用户账户表
 * @author 张进军
 * @date 2015年08月13日 PM 18:26:36
 */
public class UserAccount {
    /** 员工标识 */
    private Integer userId;

    /** 角色标识 */
    private Integer roleId;

    /** 用户账号 */
    private String userName;

    /** 用户密码 */
    private String userPwd;

    /** 密码盐值 */
    private String pwdSalt;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;
    /** 门店标识 */
    private Integer storeId;

    

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /** @param userId   员工标识 */
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    /** @return 员工标识 */
    public Integer getUserId(){
        return userId;
    }

    /** @param roleId   角色标识 */
    public void setRoleId(Integer roleId){
        this.roleId = roleId;
    }

    /** @return 角色标识 */
    public Integer getRoleId(){
        return roleId;
    }

    /** @param userName 用户账号 */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /** @return 用户账号 */
    public String getUserName(){
        return userName;
    }

    /** @param userPwd  用户密码 */
    public void setUserPwd(String userPwd){
        this.userPwd = userPwd;
    }

    /** @return 用户密码 */
    public String getUserPwd(){
        return userPwd;
    }

    /** @param pwdSalt  密码盐值 */
    public void setPwdSalt(String pwdSalt){
        this.pwdSalt = pwdSalt;
    }

    /** @return 密码盐值 */
    public String getPwdSalt(){
        return pwdSalt;
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

}