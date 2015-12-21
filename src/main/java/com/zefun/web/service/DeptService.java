package com.zefun.web.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.OrganizationDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.EmployeeLevel;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.entity.ShiftInfo;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeLevelMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ShiftMapper;

/**
 * 关于部门
* @author chendb
* @date 2015年9月8日 上午11:11:02
 */
@Service
public class DeptService {
    /**班次*/
    @Autowired
    private ShiftMapper shiftMapper;
    /**
     * 关于部门
     */
    @Autowired
    private DeptInfoMapper deptInfoMapper;
    /**
     *  岗位
     */
    @Autowired
    private PositioninfoMapper positioninfoMapper;
    /**
     * 职位信息
     */
    @Autowired
    private EmployeeLevelMapper employeelevelMapper;
    /**
     * 新增部门
    * @author chendb
    * @date 2015年9月8日 上午11:14:09
    * @param deptInfo 参数
    * @return int
     */
    public int adddDept(DeptInfo deptInfo){
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("deptCode", deptInfo.getDeptCode());
        map.put("storeId", deptInfo.getStoreId());
        //判断编码是否存在
        int count =deptInfoMapper.getCount(map);
        if (count>0){
            return 1;  
        }
        //判断名称是否存在
        Map<String, Object>map1=new HashMap<String, Object>();
        map1.put("deptName", deptInfo.getDeptName());
        map1.put("storeId", deptInfo.getStoreId());
        int count1 =deptInfoMapper.getCount(map1);
        
        if (count1>0){
            return 2;  
        }
        deptInfoMapper.insert(deptInfo);
        //新增部门后顺便一起新增下班次时间
        addshift(deptInfo.getStoreId(), deptInfo.getDeptId());
        return 0;
    }
    /**
     * 新增部门时候班次初始化
    * @author chendb
    * @date 2015年11月9日 下午5:49:57
    * @param storeId 门店
    * @param deptId 部门
    * @return int
     */
    @Transactional
    private int addshift(Integer storeId, Integer deptId){
        ShiftInfo shiftInfo=new ShiftInfo();
        shiftInfo.setStoreId(storeId);
        shiftInfo.setDeptId(deptId);
        shiftInfo.setStartTime("08:00");
        shiftInfo.setEndTime("12:00");
        shiftInfo.setShifName("早班");
        shiftInfo.setCreateTime(DateUtil.getCurTime());
        shiftMapper.insertShiftinfo(shiftInfo);
        shiftInfo.setStartTime("12:00");
        shiftInfo.setEndTime("18:00");
        shiftInfo.setShifName("中班");
        shiftMapper.insertShiftinfo(shiftInfo);
        shiftInfo.setStartTime("18:00");
        shiftInfo.setEndTime("22:00");
        shiftInfo.setShifName("晚班");
        shiftMapper.insertShiftinfo(shiftInfo);
        shiftInfo.setStartTime("08:00");
        shiftInfo.setEndTime("22:00");
        shiftInfo.setShifName("全班");
        shiftMapper.insertShiftinfo(shiftInfo);
        return 0;
    }
    /**
     * 修改部门
    * @author chendb
    * @date 2015年9月8日 下午3:27:50
    * @param deptInfo 参数
    * @return int
     */
    public int updatedDept(DeptInfo deptInfo){
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("deptCode", deptInfo.getDeptCode());
        map.put("deptId", deptInfo.getDeptId());
        map.put("storeId", deptInfo.getStoreId());
        //判断编码是否存在
        int count =deptInfoMapper.getCount(map);
        if (count>0){
            return 1;  
        }
        //判断名称是否存在
        Map<String, Object>map1=new HashMap<String, Object>();
        map1.put("deptName", deptInfo.getDeptName());
        map1.put("deptId", deptInfo.getDeptId());
        map1.put("storeId", deptInfo.getStoreId());
        int count1 =deptInfoMapper.getCount(map1);
        if (count1>0){
            return 2;  
        }
        deptInfoMapper.updateByPrimaryKeySelective(deptInfo);
        return 0;
    }
    /**
     * 删除部门
    * @author chendb
    * @date 2015年9月8日 下午3:29:51
    * @param deptId 
    * @param storeId 
    * @return int
     */
    public int deleteDept(Integer deptId, Integer storeId){
        //判断部门是否被引用了
        int count =deptInfoMapper.isQuote(deptId);
        if (count>0){
            return 1;  
        }
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("storeId", storeId);
        map.put("deptId", deptId);
        int count1=deptInfoMapper.countProjectDept(map);
        if (count1>0){
            return 2;  
        }
        int count2=deptInfoMapper.countGoodsDept(map);
        if (count2>0){
            return 3;  
        }
        int count3=deptInfoMapper.countComboDept(map);
        if (count3>0){
            return 4;  
        }
        int count4=deptInfoMapper.countCategoryDept(map);
        if (count4>0){
            return 5;  
        }
        //只是修改状态
        deptInfoMapper.deleteByPrimaryKey(deptId);
        return 0;
    }
    /**
     * 关于获取部门信息
    * @author chendb
    * @date 2015年9月16日 下午12:14:55
    * @param storeId 门店标识
    * @return List<DeptInfo>
     */
    public List<DeptInfo> getDeptInfo(Integer storeId){
        List<DeptInfo> list=deptInfoMapper.getDeptInfo(storeId); 
        return list;
    }
    /**
     * 
    * @author chendb
    * @date 2015年10月19日 上午9:37:26
    * @param file 文件
    * @param temp 临时
    * @param storeId 门店
    * @return BaseDto
     * @throws IOException 
     */
    @Transactional
    public BaseDto importExcle(MultipartFile file, String temp, Integer storeId) throws IOException{
        File tempFile = new File(temp);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }

        if (file == null){
            return null;
        }
        // 获取上传文件名,包括路径
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0){
            return null;
        }
        //判断是否是excel2007格式 
        boolean isE2007 = false;     
        if (name.endsWith("xlsx")){
            isE2007 = true;
        }
        //建立输入流  
        InputStream input = file.getInputStream();  
        Workbook wb  = null;  
        //根据文件格式(2003或者2007)来初始化  
        if (isE2007){
            wb = new XSSFWorkbook(input); 
        }
        else {
            wb = new HSSFWorkbook(input);
        }
        //获得第一个表单
        Sheet sheet = wb.getSheetAt(0); 
        //获得第一个表单的迭代器  
        Iterator<Row> rows = sheet.rowIterator(); 
        List<OrganizationDto> listDto=new ArrayList<OrganizationDto>();
        while (rows.hasNext()) {  
            OrganizationDto org = new OrganizationDto();
            //获得行数据 
            Row row = rows.next(); 
            if (row.getRowNum() > 0){
                //获得行号从0开始  
                //获得第一行的迭代器 
                Iterator<Cell> cells = row.cellIterator(); 
                OrganizationDto copyorg = new OrganizationDto();
                //先判断数据是否有  如果没有了就不继续运行下去
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    if (cell.getColumnIndex() == 0){
                        String returnValue = changeCellToString(cell);
                        if (returnValue!=null &&returnValue!=""){
                            copyorg.setDeptCode(Integer.parseInt(returnValue));
                        }
                    }
                    else if (cell.getColumnIndex() == 1){
                        String returnValue = changeCellToString(cell);
                        if (returnValue!=null &&returnValue!=""){
                            copyorg.setDeptName(returnValue);
                        }
                        
                    }
                    else if (cell.getColumnIndex() == 2){
                        String returnValue = changeCellToString(cell);
                        if (returnValue!=null &&returnValue!=""){
                            if (returnValue.equals("是")){
                                returnValue="1";
                            }
                            else if (returnValue.equals("否")){
                                returnValue="2";
                            }
                            copyorg.setIsResults(Integer.parseInt(returnValue));
                        }
                    }
                    else if (cell.getColumnIndex() == 3){
                        String returnValue = changeCellToString(cell);
                        if (returnValue!=null &&returnValue!=""){
                            copyorg.setPositionCode(Integer.parseInt(returnValue));
                        }
                    }
                    else if (cell.getColumnIndex() == 4){
                        String returnValue = changeCellToString(cell);
                        if (returnValue!=null &&returnValue!=""){
                            copyorg.setPositionName(returnValue);
                        }
                    }
                    else if (cell.getColumnIndex() == 5){
                        String returnValue = changeCellToString(cell);
                        if (returnValue!=null &&returnValue!=""){
                            copyorg.setLevelName(returnValue);
                        }
                    }
                }
                //如果没有数据了就不要继续了
                if (copyorg.getDeptCode()==null&&copyorg.getDeptName()==null&&copyorg.getIsResults()==null&&copyorg.getPositionCode()==null
                        &&copyorg.getPositionName()==null&&copyorg.getLevelName()==null){
                    break;
                }
                Iterator<Cell> cellss = row.cellIterator(); 
                while (cellss.hasNext()) {
                    Cell cell = cellss.next();
                    
                    if (cell.getColumnIndex() == 0){
                        
                        String returnValue = changeCellToString(cell);
                        int code=Integer.parseInt(returnValue);
                        if (code<1||code>9){
                            return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行部门编码只能填写1-9！");
                        }
                        if (returnValue==null||returnValue==""){
                            return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行部门编码不能为空！");
                        }
                        /*Map<String, Object>map=new HashMap<String, Object>();
                        map.put("storeId", storeId);
                        map.put("deptCode", returnValue);
                        int count =deptInfoMapper.getCount(map);
                        if (count>0){
                            return new BaseDto(-1, "第 "+row.getRowNum()+"行部门编码已经存在了，不能重复！");  
                        }*/
                       
                        org.setDeptCode(Integer.parseInt(returnValue));
                    }
                    else if (cell.getColumnIndex() == 1){
                        String returnValue = changeCellToString(cell);
                        if (returnValue==null||returnValue==""){
                            return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行部门名称不能为空！");
                        }
                        if (returnValue.length()>10){
                            return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行部门名称不能十个字符！");
                        }
                        org.setDeptName(returnValue);
                        
                    }
                    else if (cell.getColumnIndex() == 2){
                        String returnValue = changeCellToString(cell);
                        if (returnValue==null||returnValue==""){
                            return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行是否产生业绩不能为空！");
                        }
                        if (returnValue.equals("是")){
                            returnValue="1";
                        }
                        else if (returnValue.equals("否")){
                            returnValue="2";
                        }
                        else {
                            return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行是否产生业绩只能填写是或否！");
                        }
                        org.setIsResults(Integer.parseInt(returnValue));
                    }
                    else if (cell.getColumnIndex() == 3){
                        String returnValue = changeCellToString(cell);
                        if (returnValue!=null && returnValue!=""){
                            int code=Integer.parseInt(returnValue);
                            if (code<1||code>9){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行岗位编码只能填写1-9！");
                            }
                        }
                        org.setPositionCode(Integer.parseInt(returnValue));
                    }
                    else if (cell.getColumnIndex() == 4){
                        String returnValue = changeCellToString(cell);
                        if (org.getPositionCode()!=null){
                            if (returnValue==null||returnValue==""){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行岗位编码不为空所以岗位名称也不能为空！");   
                            }
                            if (returnValue.length()>10){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行岗位名称不能十个字符！");
                            }
                        }
                        org.setPositionName(returnValue);
                    }
                    else if (cell.getColumnIndex() == 5){
                        String returnValue = changeCellToString(cell);
                        if (org.getPositionCode()==null){
                            if (returnValue!=null||returnValue!=""){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行岗位信息填写后才能填写职位名称！"); 
                            }
                        }
                        if (returnValue.length()>10){
                            return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行职位名称不能十个字符！");
                        }
                        org.setLevelName(returnValue);
                    }
                    
                }
                //--------
                org.setStoreId(storeId);
                org.setNum(row.getRowNum());
                listDto.add(org);
            }
        }
        //插入
        for (int i = 0; i < listDto.size(); i++) {
            OrganizationDto org=listDto.get(i);
            DeptInfo deptInfo=new DeptInfo();
            deptInfo.setStoreId(storeId);
            deptInfo.setDeptCode(org.getDeptCode());
            deptInfo.setDeptName(org.getDeptName());
            deptInfo.setIsResults(org.getIsResults());
            DeptInfo info=deptInfoMapper.getDeptDetail(deptInfo);
            //判断数据库是否存在数据了
            if (info==null){
                //循环判断是否在这些没插入的数据中有存在重复
                for (int j = 0; j < listDto.size(); j++) {
                    OrganizationDto org1=listDto.get(j);
                    //编码不相等  但是名称相等
                    if (org1.getNum()!=org.getNum()){
                        if (!org1.getDeptCode().equals(org.getDeptCode())&&org1.getDeptName().equals(org.getDeptName())){
                            return new BaseDto(-1, "第 "+(org1.getNum()+1)+"行部门名称已经存在不能重复！"); 
                        }
                        if (org1.getDeptCode().equals(org.getDeptCode())&&!org1.getDeptName().equals(org.getDeptName())){
                            return new BaseDto(-1, "第 "+(org1.getNum()+1)+"行部门编码已经存在不能重复！"); 
                        }  
                    }
                }
                Map<String, Object>map=new HashMap<String, Object>();
                map.put("storeId", storeId);
                map.put("deptCode", org.getDeptCode());
                int count =deptInfoMapper.getCount(map);
                if (count>0){
                    return new BaseDto(-1, "第 "+(org.getNum()+1)+"行部门编码已经存在！"); 
                }
                Map<String, Object>map1=new HashMap<String, Object>();
                map1.put("deptName", org.getDeptName());
                map1.put("storeId", storeId);
                int count1 =deptInfoMapper.getCount(map1);
                if (count1>0){
                    return new BaseDto(-1, "第 "+(org.getNum()+1)+"行部门名称已经存在！"); 
                }
                deptInfoMapper.insert(deptInfo);
                org.setDeptId(deptInfo.getDeptId());
            } 
            else {
                org.setDeptId(info.getDeptId());
            }
            if (org.getPositionCode()!=null){
                PositionInfo positioninfo=new PositionInfo();
                positioninfo.setPositionCode(org.getPositionCode());
                positioninfo.setPositionName(org.getPositionName());
                positioninfo.setStoreId(storeId);
                positioninfo.setDeptId(org.getDeptId());
                PositionInfo info1=positioninfoMapper.queryPositiondetail(positioninfo);
                if (info1==null){
                  //循环判断是否在这些没插入的数据中有存在重复
                    for (int j = 0; j < listDto.size(); j++) {
                        OrganizationDto org1=listDto.get(j); 
                        //编码不相等  但是名称相等
                        if (org1.getNum()!=org.getNum()){
                            if (org.getDeptName().equals(org1.getDeptName())){
                                if (!org1.getPositionCode().equals(org.getPositionCode())&&org1.getPositionName().equals(org.getPositionName())){
                                    return new BaseDto(-1, "第 "+(org.getNum()+1)+"行岗位名称已经存在不能重复！"); 
                                }
                                if (org1.getPositionCode().equals(org.getPositionCode())&&!org1.getPositionName().equals(org.getPositionName())){
                                    return new BaseDto(-1, "第 "+(org.getNum()+1)+"行岗位编码已经存在不能重复！"); 
                                }  
                            }
                             
                        }
                        
                    }
                  //判断岗位编码或者岗位名称是否已经存在
                    PositionInfo positioninfo1=new PositionInfo();
                    positioninfo1.setStoreId(storeId);
                    positioninfo1.setPositionCode(org.getPositionCode());
                    List<PositionInfo> list1=positioninfoMapper.isPositionCode(positioninfo1);
                    if (list1.size()>0){
                        //岗位编码已经存在了
                        return new BaseDto(-1, "第 "+(org.getNum()+1)+"行岗位编码已经存在！"); 
                    }
                    PositionInfo positioninfo2=new PositionInfo();
                    positioninfo2.setStoreId(storeId);
                    positioninfo2.setPositionName(org.getPositionName());
                    List<PositionInfo> list=positioninfoMapper.isPositionName(positioninfo2);
                    if (list.size()>0){
                        //岗位名称已经存在了
                        return new BaseDto(-1, "第 "+(org.getNum()+1)+"行岗位名称已经存在！");
                    }
                    positioninfoMapper.insert(positioninfo);   
                }
                else {
                    positioninfo.setPositionId(info1.getPositionId());
                }
                if (org.getLevelName()!=null&&org.getLevelName()!=""){
                    Map<String, Object>map=new HashMap<String, Object>();
                    map.put("storeId", storeId);
                    map.put("positionId", positioninfo.getPositionId());
                    map.put("storeId", storeId);
                    map.put("levelName", org.getLevelName());
                    List<EmployeeLevel> info2=employeelevelMapper.islevelName(map);
                    if (info2.size()==0){
                        List<EmployeeLevel> list=employeelevelMapper.islevelName(map);
                        if (list.size()>0){
                            return new BaseDto(-1, "第 "+(org.getNum()+1)+"行该岗位下的职位名称已经存在！");
                        }
                        employeelevelMapper.insert(map);
                    }
                    
                }
            }
            
            
        }
        return new BaseDto(0, "导入成功！"); 
       
    }
    /**
     * 
    * @author chendb
    * @date 2015年10月19日 上午9:41:49
    * @param cell 参数
    * @return String
     */
    public String changeCellToString(Cell cell){

        String returnValue = "";

        if (null != cell){

            switch(cell.getCellType()){
                //数字
                case HSSFCell.CELL_TYPE_NUMERIC:
        
                    Double doubleValue = cell.getNumericCellValue();
            
                    String str = doubleValue.toString();
            
                    if (str.contains(".0")){
            
                        str = str.replace(".0", "");
            
                    }
            
                    Integer intValue = Integer.parseInt(str);
            
                    returnValue = intValue.toString();
            
                    break;
        
                case HSSFCell.CELL_TYPE_STRING:    
                    //字符串
                    returnValue = cell.getStringCellValue();
            
                    break;
        
                case HSSFCell.CELL_TYPE_BOOLEAN:   
                    //布尔
                    Boolean booleanValue=cell.getBooleanCellValue();
            
                    returnValue = booleanValue.toString();
        
                    break;
                // 空值
                case HSSFCell.CELL_TYPE_BLANK:     
            
                    returnValue = "";
        
                    break;
                // 公式
                case HSSFCell.CELL_TYPE_FORMULA:   
            
                    returnValue = cell.getCellFormula();
        
                    break;
                // 故障
                case HSSFCell.CELL_TYPE_ERROR:     
            
                    returnValue = "";
        
                    break;
        
                default:
                
                    break;

            }

        }

        return returnValue;

    }
}
