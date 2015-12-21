package com.zefun.web.dto;
/**
 * 人员目标相关信息
* @author chendb
* @date 2015年8月17日 下午4:21:08
 */
public class ObjectiveDto {

    /** 目标标识 */
    private Integer objectiveId;

    /** 员工标识 */
    private Integer employeeId;
    
    /** 员工标姓名*/
    private String name;
    
    /** 员工编号 */
    private String employeeCode;
    
    /** 员工岗位 */
    private String positionName;
    
    /** 员工职位 */
    private String levelName;

    /** 总体项目目标 */
    private Integer totalProjectSales;

    /** 指定项目目标 */
    private Integer assignProjectSales;

    /** 套餐目标 */
    private Integer comboSales;

    /** 商品目标 */
    private Integer goodsSales;

    /** 充值目标 */
    private Integer chargeSales;
    
    /** 开卡目标 */
    private Integer cardSales;

    /** 目标月份 */
    private String objectiveMonth;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;
    /** 门店 */
    private Integer storeId;

   

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCardSales() {
        return cardSales;
    }

    public void setCardSales(Integer cardSales) {
        this.cardSales = cardSales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
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

    public Integer getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Integer objectiveId) {
        this.objectiveId = objectiveId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getTotalProjectSales() {
        return totalProjectSales;
    }

    public void setTotalProjectSales(Integer totalProjectSales) {
        this.totalProjectSales = totalProjectSales;
    }

    public Integer getAssignProjectSales() {
        return assignProjectSales;
    }

    public void setAssignProjectSales(Integer assignProjectSales) {
        this.assignProjectSales = assignProjectSales;
    }

    public Integer getComboSales() {
        return comboSales;
    }

    public void setComboSales(Integer comboSales) {
        this.comboSales = comboSales;
    }

    public Integer getGoodsSales() {
        return goodsSales;
    }

    public void setGoodsSales(Integer goodsSales) {
        this.goodsSales = goodsSales;
    }

    public Integer getChargeSales() {
        return chargeSales;
    }

    public void setChargeSales(Integer chargeSales) {
        this.chargeSales = chargeSales;
    }



    public String getObjectiveMonth() {
        return objectiveMonth;
    }

    public void setObjectiveMonth(String objectiveMonth) {
        this.objectiveMonth = objectiveMonth;
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

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
    

}
