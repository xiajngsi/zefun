package com.zefun.web.dto;

/**
 * 员工职位基础传输对象
* @author 张进军
* @date Oct 15, 2015 12:59:32 AM 
*/
public class EmployeeLevelBaseDto {
    /** 级别标识 */
    private Integer levelId;
    
    /** 级别名称 */
    private String levelName;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    
}
