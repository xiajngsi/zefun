package com.zefun.web.dto;
/**
 * 班次
* @author chendb
* @date 2015年8月28日 上午9:48:19
 */
public class ShiftDto {
    /**人员标识*/
    private String employeeId;
    /**人员编码*/
    private String employeeCode;
    /**人员编码*/
    private String name;
    /**周一班次*/
    private String shifIda;
    /**周一班次名称*/
    private String shifNamea;
    /**周二班次*/
    private String shifIdb;
    /**周二班次名称*/
    private String shifNameb;
    /**周三班次*/
    private String shifIdc;
    /**周三班次名称*/
    private String shifNamec;
    /**周四班次*/
    private String shifIdd;
    /**周四班次名称*/
    private String shifNamed;
    /**周五班次*/
    private String shifIde;
    /**周五班次名称*/
    private String shifNamee;
    /**周六班次*/
    private String shifIdf;
    /**周六班次名称*/
    private String shifNamef;
    /**周日班次*/
    private String shifIdg;
    /**周日班次名称*/
    private String shifNameg;
    /**班次标识*/
    private Integer shifId;
    /**门店标识*/
    private Integer storeId;
    /**班次名称*/
    private String shifName;
    /**开始时间*/
    private String startTime;
    /**结束时间*/
    private String endTime;
    /**创建时间*/
    private String createTime;
    /**修改时间*/
    private String updateTime;
    /**最后修改人*/
    private Integer lastOperatorId;
    /**班次*/
    private ShiftDto shiftDto;

    public ShiftDto getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }

    public String getShifNamea() {
        return shifNamea;
    }

    public void setShifNamea(String shifNamea) {
        this.shifNamea = shifNamea;
    }

    public String getShifNameb() {
        return shifNameb;
    }

    public void setShifNameb(String shifNameb) {
        this.shifNameb = shifNameb;
    }

    public String getShifNamec() {
        return shifNamec;
    }

    public void setShifNamec(String shifNamec) {
        this.shifNamec = shifNamec;
    }

    public String getShifNamed() {
        return shifNamed;
    }

    public void setShifNamed(String shifNamed) {
        this.shifNamed = shifNamed;
    }

    public String getShifNamee() {
        return shifNamee;
    }

    public void setShifNamee(String shifNamee) {
        this.shifNamee = shifNamee;
    }

    public String getShifNamef() {
        return shifNamef;
    }

    public void setShifNamef(String shifNamef) {
        this.shifNamef = shifNamef;
    }

    public String getShifNameg() {
        return shifNameg;
    }

    public void setShifNameg(String shifNameg) {
        this.shifNameg = shifNameg;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShifIda() {
        return shifIda;
    }

    public void setShifIda(String shifIda) {
        this.shifIda = shifIda;
    }

    public String getShifIdb() {
        return shifIdb;
    }

    public void setShifIdb(String shifIdb) {
        this.shifIdb = shifIdb;
    }

    public String getShifIdc() {
        return shifIdc;
    }

    public void setShifIdc(String shifIdc) {
        this.shifIdc = shifIdc;
    }

    public String getShifIdd() {
        return shifIdd;
    }

    public void setShifIdd(String shifIdd) {
        this.shifIdd = shifIdd;
    }

    public String getShifIde() {
        return shifIde;
    }

    public void setShifIde(String shifIde) {
        this.shifIde = shifIde;
    }

    public String getShifIdf() {
        return shifIdf;
    }

    public void setShifIdf(String shifIdf) {
        this.shifIdf = shifIdf;
    }

    public String getShifIdg() {
        return shifIdg;
    }

    public void setShifIdg(String shifIdg) {
        this.shifIdg = shifIdg;
    }

    public Integer getShifId() {
        return shifId;
    }

    public void setShifId(Integer shifId) {
        this.shifId = shifId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getShifName() {
        return shifName;
    }

    public void setShifName(String shifName) {
        this.shifName = shifName == null ? null : shifName.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
}