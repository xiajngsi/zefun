package com.zefun.web.entity;


/**
 * 项目类别
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ProjectCategory {
	/** 类别标识 */
	private Integer categoryId;

	/** 门店标识 */
	private Integer storeId;
	
	/**部门标识*/
    private Integer deptId;

	/** 类别名称 */
	private String categoryName;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;
	
	/** 是否删除(0:未删除,1:已删除)*/
	private Integer isDeleted;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /** @param categoryId	类别标识 */
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}

	/** @return	类别标识 */
	public Integer getCategoryId(){
		return categoryId;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/**@return 部门标识*/
    public Integer getDeptId() {
        return deptId;
    }

    /**@param deptId 部门标识*/
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
	
	/** @param categoryName	类别名称 */
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	/** @return	类别名称 */
	public String getCategoryName(){
		return categoryName;
	}

	/** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}

}