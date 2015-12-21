package com.zefun.web.dto;

/**
 * 收益基础类
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月28日
 */
public class IncomeDto {

    /**
     * 时间
     */
    protected String time;

    /**
     * 对应时间内的收益
     */
    protected double income;

    /**
     * 获取时间
     * @author gebing
     * @date 2015年12月4日
     * @return 时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置时间
     * @author gebing
     * @date 2015年12月4日
     * @param time 时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取收益
     * @author gebing
     * @date 2015年12月4日
     * @return 收益
     */
    public double getIncome() {
        return income;
    }

    /**
     * 设置收益
     * @author gebing
     * @date 2015年12月4日
     * @param income 收益
     */
    public void setIncome(double income) {
        this.income = ((int)(income*100))/100.0;
    }

}
