package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.EmployeeEvaluate;
import com.zefun.web.entity.ProjectEvaluate;

/**
* @author 张进军
* @date Nov 7, 2015 10:01:02 PM 
*/
public class OrderEvaluateDto {
    /**订单标识*/
    private int orderId;
    /**项目评价列表*/
    private List<ProjectEvaluate> projectList;
    /**员工评价列表*/
    private List<EmployeeEvaluate> emloyeeList;
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public List<ProjectEvaluate> getProjectList() {
        return projectList;
    }
    public void setProjectList(List<ProjectEvaluate> projectList) {
        this.projectList = projectList;
    }
    public List<EmployeeEvaluate> getEmloyeeList() {
        return emloyeeList;
    }
    public void setEmloyeeList(List<EmployeeEvaluate> emloyeeList) {
        this.emloyeeList = emloyeeList;
    }
}
