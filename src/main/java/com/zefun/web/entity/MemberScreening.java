package com.zefun.web.entity;

/**
 * 会员筛选器
* @author 高国藩
* @date 2015年9月8日 下午7:51:43
 */
public class MemberScreening {

    /**主键*/
    private Integer screeningId;
    /**筛选器名称*/
    private String screeningName;
    /**筛选器创建时间*/
    private String createTime;
    /**储值余额*/
    private Integer unusedBalanceStart;
    /**储值余额*/
    private Integer unusedBalanceEnd;
    /**积分余额*/
    private Integer integralBalanceStart;
    /**积分余额*/
    private Integer integralBalanceEnd;
    /**生日*/
    private String birthdayStart;
    /**生日*/
    private String birthdayEnd;
    /**注册日期*/
    private String registrationDateStart;
    /**注册日期*/
    private String registrationDateEnd;
    /**消费均额*/
    private Integer consumptionAverageStart;
    /**消费均额*/
    private Integer consumptionAverageEnd;
    /**累计消费*/
    private Integer cumulativeConsumptionStart;
    /**累计消费*/
    private Integer cumulativeConsumptionEnd;
    /**距离上次消费*/
    private Integer lastConsumeTimeStart;
    /**距离上次消费*/
    private Integer lastConsumeTimeEnd;
    /**门店*/
    private Integer storeId;
    /**分店门店id*/
    private String branchStoreIds;

    public String getBranchStoreIds() {
        return branchStoreIds;
    }
    public void setBranchStoreIds(String branchStoreIds) {
        this.branchStoreIds = branchStoreIds;
    }

    public Integer getScreeningId() {
        return screeningId;
    }

    public Integer getLastConsumeTimeStart() {
        return lastConsumeTimeStart;
    }

    public void setLastConsumeTimeStart(Integer lastConsumeTimeStart) {
        this.lastConsumeTimeStart = lastConsumeTimeStart;
    }

    public Integer getLastConsumeTimeEnd() {
        return lastConsumeTimeEnd;
    }

    public void setLastConsumeTimeEnd(Integer lastConsumeTimeEnd) {
        this.lastConsumeTimeEnd = lastConsumeTimeEnd;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public String getScreeningName() {
        return screeningName;
    }

    public void setScreeningName(String screeningName) {
        this.screeningName = screeningName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getUnusedBalanceStart() {
        return unusedBalanceStart;
    }

    public void setUnusedBalanceStart(Integer unusedBalanceStart) {
        this.unusedBalanceStart = unusedBalanceStart;
    }

    public Integer getUnusedBalanceEnd() {
        return unusedBalanceEnd;
    }

    public void setUnusedBalanceEnd(Integer unusedBalanceEnd) {
        this.unusedBalanceEnd = unusedBalanceEnd;
    }

    public Integer getIntegralBalanceStart() {
        return integralBalanceStart;
    }

    public void setIntegralBalanceStart(Integer integralBalanceStart) {
        this.integralBalanceStart = integralBalanceStart;
    }

    public Integer getIntegralBalanceEnd() {
        return integralBalanceEnd;
    }

    public void setIntegralBalanceEnd(Integer integralBalanceEnd) {
        this.integralBalanceEnd = integralBalanceEnd;
    }

    public String getBirthdayStart() {
        return birthdayStart;
    }

    public void setBirthdayStart(String birthdayStart) {
        this.birthdayStart = birthdayStart;
    }

    public String getBirthdayEnd() {
        return birthdayEnd;
    }

    public void setBirthdayEnd(String birthdayEnd) {
        this.birthdayEnd = birthdayEnd;
    }

    public String getRegistrationDateStart() {
        return registrationDateStart;
    }

    public void setRegistrationDateStart(String registrationDateStart) {
        this.registrationDateStart = registrationDateStart;
    }

    public String getRegistrationDateEnd() {
        return registrationDateEnd;
    }

    public void setRegistrationDateEnd(String registrationDateEnd) {
        this.registrationDateEnd = registrationDateEnd;
    }

    public Integer getConsumptionAverageStart() {
        return consumptionAverageStart;
    }

    public void setConsumptionAverageStart(Integer consumptionAverageStart) {
        this.consumptionAverageStart = consumptionAverageStart;
    }

    public Integer getConsumptionAverageEnd() {
        return consumptionAverageEnd;
    }

    public void setConsumptionAverageEnd(Integer consumptionAverageEnd) {
        this.consumptionAverageEnd = consumptionAverageEnd;
    }

    public Integer getCumulativeConsumptionStart() {
        return cumulativeConsumptionStart;
    }

    public void setCumulativeConsumptionStart(
            Integer cumulativeConsumptionStart) {
        this.cumulativeConsumptionStart = cumulativeConsumptionStart;
    }

    public Integer getCumulativeConsumptionEnd() {
        return cumulativeConsumptionEnd;
    }

    public void setCumulativeConsumptionEnd(Integer cumulativeConsumptionEnd) {
        this.cumulativeConsumptionEnd = cumulativeConsumptionEnd;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer sql = new StringBuffer();
        if (unusedBalanceStart != null) {
            sql.append("储值余额:" + unusedBalanceStart + "-");
            if (unusedBalanceEnd != null) {
                sql.append(unusedBalanceEnd);
            }
            sql.append(",");
        }
        if (integralBalanceStart != null) {
            sql.append("积分余额:" + integralBalanceStart + "-");
            if (integralBalanceEnd != null) {
                sql.append(integralBalanceEnd);
            }
            sql.append(",");
        }
        if (birthdayStart != null) {
            sql.append("生日:" + birthdayStart + "-");
            if (birthdayEnd != null) {
                sql.append(birthdayEnd);
            }
            sql.append(",");
        }
        if (registrationDateStart != null) {
            sql.append("注册日期:" + registrationDateStart + "-");
            if (registrationDateEnd != null) {
                sql.append(registrationDateEnd);
            }
            sql.append(",");
        }
        if (consumptionAverageStart != null) {
            sql.append("消费均额:" + consumptionAverageStart + "-");
            if (consumptionAverageEnd != null) {
                sql.append(consumptionAverageEnd);
            }
            sql.append(",");
        }
        if (cumulativeConsumptionStart != null) {
            sql.append("累计消费:" + cumulativeConsumptionStart + "-");
            if (cumulativeConsumptionEnd != null) {
                sql.append(cumulativeConsumptionEnd);
            }
            sql.append(",");
        }
        if (lastConsumeTimeStart != null) {
            sql.append("上次消费时间:" + lastConsumeTimeStart + "-");
            if (lastConsumeTimeEnd != null) {
                sql.append(lastConsumeTimeEnd);
            }
            sql.append(",");
        }
        return sql.toString();
    }

}