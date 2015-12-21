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
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.ShiftDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShiftInfo;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.ShiftMapper;
/**
 * 班次相关信息
* @author chendb
* @date 2015年8月28日 上午9:39:05
 */
@Service
public class ShiftService {
    /**班次*/
    @Autowired
    private ShiftMapper shiftMapper;
    /**人员*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    /**部门*/
    @Autowired
    private DeptInfoMapper deptInfoMapper;
    /**
     * 查询某个店铺的班次信息
     * 默认返回该门店最前面10条数据
    * @author chendb
    * @date 2015年8月28日 下午1:59:44
    * @param params 参数
    * @return ModelAndView
     */
    public ModelAndView queryShift(Map<String, Object> params){
        Integer storeId=Integer.parseInt(params.get("storeId").toString());
        Page<ShiftDto> page=selectPageForShift(params, 1, App.System.API_DEFAULT_PAGE_SIZE);
        ModelAndView mav = new ModelAndView("employee/shift/shift");
        mav.addObject("page", page);
        //获取部门信息
        List<DeptInfo> deptlist=deptInfoMapper.getDeptInfo(storeId);
        mav.addObject("deptlist", deptlist);
        //获取班次信息
       /* List<ShiftDto> list=shiftMapper.getShiftInfo(storeId);
        mav.addObject("list", list);*/
        //获取人员   选择推荐人要用的下拉框
        List<EmployeeInfo> employeeList=employeeInfoMapper.getRecommendlist(storeId);
        mav.addObject("employeeList", employeeList);

        return mav;
    }
    
    /**
     * 翻页功能
    * @author 陈端斌
    * @date 2015年8月28日 上午10:55:59
    * @param params 参数
    * @param pageNo 页码
    * @param pageSize 每页参数
    * @return BaseDto
     */
    public BaseDto listAction(Map<String, Object> params, int pageNo, int pageSize){
        Page<ShiftDto> page = selectPageForShift(params, pageNo, pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    /**
     * 
    * @author chendb
    * @date 2015年8月27日 上午11:23:47
    * @param params 参数
    * @param pageNo 页码
    * @param pageSize 每页数量
    * @return Page<AttendanceDto>
     */
    private Page<ShiftDto> selectPageForShift(Map<String, Object> params, int pageNo, int pageSize){
        Page<ShiftDto> page = new Page<ShiftDto>();
        page.setParams(params);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        List<ShiftDto> list = shiftMapper.getEmployeeShift(page);
        page.setResults(list);
        return page;
    }
    /**
     * 人员班次
    * @author chendb
    * @date 2015年8月28日 下午4:24:23
    * @param params 参数
    * @return int
     */
    public int employeeShift(Map<String, Object> params){
        //判断之前是否已经新增过了
        int count=shiftMapper.countIsshift(params);
        if (count>0){
            return 1;
        }
        //重新录入新的员工班次
        shiftMapper.employeeShift(params);
        return 0;
    }
    /**
     * 修改人员班次信息
    * @author chendb
    * @date 2015年8月31日 下午5:01:16
    * @param params 参数
    * @return int
     */
    public int updateemployeeShift(Map<String, Object> params){
        //删除之前的数据
        shiftMapper.deleteShift(params);
        //重新录入新的员工班次
        shiftMapper.employeeShift(params);
        return 0;
    }
    /**
     * 
    * @author chendb
    * @date 2015年8月31日 下午3:56:55
    * @param map 参数
    * @return ShiftDto
     */
    public ShiftDto queryEmployeeShift(Map<String, Object> map){
        ShiftDto info=shiftMapper.queryEmployeeShift(map);
        return info;
    }
    /**
     * 删除人员班次信息
    * @author chendb
    * @date 2015年8月31日 下午6:42:18
    * @param map 参数
    * @return int
     */
    public int deleteEmployeeShift(Map<String, Object> map){
        shiftMapper.deleteShift(map);
        return 0;
    }
    /**
     * 修改班次信息
    * @author chendb
    * @date 2015年9月10日 下午4:37:23
    * @param zao 参数
    * @param zhong 参数
    * @param wan 参数
    * @param quan 参数
    * @return int
     */
    @Transactional
    public int updateShift(ShiftInfo zao, ShiftInfo zhong, ShiftInfo wan, ShiftInfo quan){
        shiftMapper.updateShift(zao);
        shiftMapper.updateShift(zhong);
        shiftMapper.updateShift(wan);
        shiftMapper.updateShift(quan);
        return 0;
    }
    /**
     * 新增班次信息
    * @author chendb
    * @date 2015年9月11日 下午4:37:23
    * @param zao 参数
    * @param zhong 参数
    * @param wan 参数
    * @param quan 参数
    * @return int
     */
    @Transactional
    public int addShift(ShiftInfo zao, ShiftInfo zhong, ShiftInfo wan, ShiftInfo quan){
        shiftMapper.insertShiftinfo(zao);
        shiftMapper.insertShiftinfo(zhong);
        shiftMapper.insertShiftinfo(wan);
        shiftMapper.insertShiftinfo(quan);
        return 0;
    }
    
    /**
     * 根据部门标识获取班次信息
    * @author chendb
    * @date 2015年9月9日 下午5:08:48
    * @param deptId 部门标识
    * @return List<DeptInfo>
     */
    public List<ShiftInfo> getShiftinfo(Integer deptId){
        List<ShiftInfo> list=shiftMapper.getShiftInfo(deptId);
        return list;
    } 
    /**
     * 
    * @author chendb
    * @date 2015年10月20日 上午10:52:31
    * @param file file
    * @param temp temp
    * @param storeId storeId
    * @return BaseDto
     */
    @Transactional
    public BaseDto importExcle(MultipartFile file, String temp, Integer storeId){
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
        try {  
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
            List<ShiftDto> listDto=new ArrayList<ShiftDto>();
            while (rows.hasNext()) {  
                ShiftDto shiftDto = new ShiftDto();
                ShiftDto copyshiftDto = new ShiftDto();
                //objectiveDto.setStoreId(storeId);
                //获得行数据 
                Row row = rows.next(); 
                
                if (row.getRowNum() > 0){
                    //获得行号从0开始  
                    //获得第一行的迭代器 
                    Iterator<Cell> cells = row.cellIterator(); 
                    Integer employeeId=0;
                    Integer deptId=0;
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (cell.getColumnIndex() == 0){
                            String returnValue = changeCellToString(cell);
                            if (returnValue!=""&&returnValue!=null){
                                copyshiftDto.setEmployeeId("1");
                            }
                        }
                        else if (cell.getColumnIndex() == 2){
                            String returnValue = changeCellToString(cell);
                            if (returnValue!=""&&returnValue!=null){
                                copyshiftDto.setShifNamea(returnValue);
                            }
                        }
                        else if (cell.getColumnIndex() == 3){
                            String returnValue = changeCellToString(cell);
                            if (returnValue!=""&&returnValue!=null){
                                copyshiftDto.setShifNameb(returnValue);
                            }
                        }
                        else if (cell.getColumnIndex() == 4){
                            String returnValue = changeCellToString(cell);
                            if (returnValue!=""&&returnValue!=null){
                                copyshiftDto.setShifNamec(returnValue);
                            }
                        }
                        else if (cell.getColumnIndex() == 5){
                            String returnValue = changeCellToString(cell);
                            if (returnValue!=""&&returnValue!=null){
                                copyshiftDto.setShifNamed(returnValue);
                            }
                        }
                        else if (cell.getColumnIndex() == 6){
                            String returnValue = changeCellToString(cell);
                            if (returnValue!=""&&returnValue!=null){
                                copyshiftDto.setShifNamee(returnValue);
                            }
                        }
                    }
                    if (copyshiftDto.getEmployeeId()==null&&copyshiftDto.getShifNamea()==null&&copyshiftDto.getShifNameb()==null
                            &&copyshiftDto.getShifNamec()==null&&copyshiftDto.getShifNamed()==null&&copyshiftDto.getShifNamee()==null
                            &&copyshiftDto.getShifNamef()==null){
                        break;
                        
                    }
                    Iterator<Cell> cellss = row.cellIterator();
                    while (cellss.hasNext()) {  
                        Cell cell = cellss.next();  
                        
                        if (cell.getColumnIndex() == 0){
                            
                            String returnValue = changeCellToString(cell);
                            Map<String, Object> map=new HashMap<String, Object>();
                            map.put("storeId", storeId);
                            map.put("employeeCode", returnValue);
                            EmployeeDto employeeInfo =employeeInfoMapper.getEmployeeDetail(map);
                            if (employeeInfo==null){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行该门店下没有此人员！");
                            }
                            employeeId=employeeInfo.getEmployeeId();
                            map.put("employeeId", employeeId);
                            deptId=employeeInfo.getDeptId();
                            int count=shiftMapper.countIsshift(map);
                            if (count>0){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行该人员已经存在班次了不能重复添加！");
                            }
                            
                            shiftDto.setEmployeeId(employeeId.toString());
                        }
                        else if (cell.getColumnIndex() == 2){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行周一班次不能为空！");
                            }
                            if (!returnValue.equals("休息日")&&!returnValue.equals("早班")&&!returnValue.equals("中班")
                                    &&!returnValue.equals("晚班")&&!returnValue.equals("全班")){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
                            }
                            Integer a=0;
                            if (!returnValue.equals("休息日")){
                                a=getshiftId(deptId, returnValue);
                                if (a==0){
                                    return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行没设置"+returnValue+"班次"); 
                                }
                            }
                            shiftDto.setShifIda(a.toString());
                        }
                        else if (cell.getColumnIndex() == 3){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行周二班次不能为空！");
                            }
                            if (!returnValue.equals("休息日")&&!returnValue.equals("早班")&&!returnValue.equals("中班")
                                    &&!returnValue.equals("晚班")&&!returnValue.equals("全班")){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
                            }
                            Integer a=0;
                            if (!returnValue.equals("休息日")){
                                a=getshiftId(deptId, returnValue); 
                                if (a==0){
                                    return new BaseDto(-1, "请先设置"+returnValue+"班次时间"); 
                                }
                            }
                            shiftDto.setShifIdb(a.toString());
                        }
                        else if (cell.getColumnIndex() == 4){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行周三班次不能为空！");
                            }
                            if (!returnValue.equals("休息日")&&!returnValue.equals("早班")&&!returnValue.equals("中班")
                                    &&!returnValue.equals("晚班")&&!returnValue.equals("全班")){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
                            }
                            Integer a=0;
                            if (!returnValue.equals("休息日")){
                                a=getshiftId(deptId, returnValue);
                                if (a==0){
                                    return new BaseDto(-1, "请先设置"+returnValue+"班次时间");  
                                }
                            }
                            shiftDto.setShifIdc(a.toString());
                        }
                        else if (cell.getColumnIndex() == 5){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行周四班次不能为空！");
                            }
                            if (!returnValue.equals("休息日")&&!returnValue.equals("早班")&&!returnValue.equals("中班")
                                    &&!returnValue.equals("晚班")&&!returnValue.equals("全班")){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
                            }
                            Integer a=0;
                            if (!returnValue.equals("休息日")){
                                a=getshiftId(deptId, returnValue);
                                if (a==0){
                                    return new BaseDto(-1, "请先设置"+returnValue+"班次时间"); 
                                }
                            }
                            shiftDto.setShifIdd(a.toString());
                        }
                        else if (cell.getColumnIndex() == 6){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行周五班次不能为空！");
                            }
                            if (!returnValue.equals("休息日")&&!returnValue.equals("早班")&&!returnValue.equals("中班")
                                    &&!returnValue.equals("晚班")&&!returnValue.equals("全班")){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
                            }
                            Integer a=0;
                            if (!returnValue.equals("休息日")){
                                a=getshiftId(deptId, returnValue); 
                                if (a==0){
                                    return new BaseDto(-1, "请先设置"+returnValue+"班次时间"); 
                                }
                            }
                            shiftDto.setShifIde(a.toString());
                        }
                        else if (cell.getColumnIndex() == 7){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行周六班次不能为空！");
                            }
                            if (!returnValue.equals("休息日")&&!returnValue.equals("早班")&&!returnValue.equals("中班")
                                    &&!returnValue.equals("晚班")&&!returnValue.equals("全班")){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
                            }
                            Integer a=0;
                            if (!returnValue.equals("休息日")){
                                a=getshiftId(deptId, returnValue);
                                if (a==0){
                                    return new BaseDto(-1, "请先设置"+returnValue+"班次时间"); 
                                }
                            }
                            shiftDto.setShifIdf(a.toString());
                        }
                        else if (cell.getColumnIndex() == 8){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行周日班次不能为空！");
                            }
                            if (!returnValue.equals("休息日")&&!returnValue.equals("早班")&&!returnValue.equals("中班")
                                    &&!returnValue.equals("晚班")&&!returnValue.equals("全班")){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
                            }
                            Integer a=0;
                            if (!returnValue.equals("休息日")){
                                a=getshiftId(deptId, returnValue);
                                if (a==0){
                                    return new BaseDto(-1, "请先设置"+returnValue+"班次时间"); 
                                }
                            }
                            shiftDto.setShifIdg(a.toString());
                        }
                        
                    }
                    //
                    listDto.add(shiftDto);
                }
            } 
            
            for (int i = 0; i < listDto.size(); i++) {
                ShiftDto info=listDto.get(i);
                
                shiftMapper.insertInfo(info);
            }
            return new BaseDto(0, "导入成功！");
        }
        catch (IOException ex) {  
            ex.printStackTrace();  
        }
        return null;
    }
    /**
     * 
    * @author chendb
    * @date 2015年10月12日 下午2:06:21
    * @param cell exlce数据对象
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
    /**
     * 
    * @author chendb
    * @date 2015年10月21日 上午9:25:02
    * @param deptId 部门标识
    * @param shiftName 班次名称
    * @return int
     */
    private int getshiftId(Integer deptId, String shiftName){
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("deptId", deptId);
        map.put("shifName", shiftName);
        ShiftInfo info=shiftMapper.queryshiftinfo(map);
        if (info==null){
            return 0;
        }
        return info.getShifId();
    }
    
}
