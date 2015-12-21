package com.zefun.web.dto;

import java.util.List;
import java.util.Map;

/**
 * 时间预约传输对象
* @author 张进军
* @date Oct 19, 2015 5:34:10 PM 
*/
public class AppointDateDto {

    /**日期*/
    private String date;
    /**周几，数字*/
    private int weekNumber;
    /**周几，汉字*/
    private String weekName;
    /**时段集合*/
    private List<Map<String, Integer>> timeList;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getWeekNumber() {
        return weekNumber;
    }
    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }
    public String getWeekName() {
        return weekName;
    }
    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }
    public List<Map<String, Integer>> getTimeList() {
        return timeList;
    }
    public void setTimeList(List<Map<String, Integer>> timeList) {
        this.timeList = timeList;
    }
    
}
