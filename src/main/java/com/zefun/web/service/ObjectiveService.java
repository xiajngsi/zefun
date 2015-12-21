package com.zefun.web.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.ExcelUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.ObjectiveDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeObjectiveMapper;

/**
 * 人员目标
* @author chendb
* @date 2015年8月17日 下午3:25:56
 */
@Service
public class ObjectiveService {
    /**
     * 人员目标
     */
    @Autowired
    private EmployeeObjectiveMapper employeeObjectiveMapper;
    /**
     * 人员信息
     */
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    /**
     * 查询某个店铺的职位信息
     * 默认返回该门店最前面10条数据
    * @author 陈端斌
    * @date 2015年8月17日 下午1:59:44
    * @param params 参数
    * @return ModelAndView
     */
    public ModelAndView queryObjective(Map<String, Object> params){
        Page<EmployeeDto> page=selectPageForEmployee(params, 1, App.System.API_DEFAULT_PAGE_SIZE);
        ModelAndView mav = new ModelAndView("employee/objective/objective");
        mav.addObject("page", page);
        
        /*Integer storeId=Integer.parseInt(params.get("storeId").toString()) ;
        List<EmployeeInfo> employeeInfo=employeeInfoMapper.getRecommendlist(storeId);
        mav.addObject("employeeInfo", employeeInfo);*/
        return mav;
    }
    
    /**
     * 翻页功能
    * @author 陈端斌
    * @date 2015年8月17日 上午10:55:59
    * @param params 参数
    * @param pageNo 页码
    * @param pageSize 每页数量
    * @return BaseDto
     */
    public BaseDto listAction(Map<String, Object> params, int pageNo, int pageSize){
        Page<EmployeeDto> page = selectPageForEmployee(params, pageNo, pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    /**
     * 
    * @author chendb
    * @date 2015年8月17日 上午11:20:47
    * @param params 参数
    * @param pageNo 页码
    * @param pageSize 每页数量
    * @return Page<EmployeeDto>
     */
    private Page<EmployeeDto> selectPageForEmployee(Map<String, Object> params, int pageNo, int pageSize){
        Page<EmployeeDto> page = new Page<EmployeeDto>();
        page.setParams(params);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        List<EmployeeDto> list = employeeObjectiveMapper.getEmployee(page);
        page.setResults(list);
        return page;
    }
    /**
     * 
    * @author chendb
    * @date 2015年8月17日 下午4:52:49
    * @param objectiveDto 参数
    * @return int
     */
    public int addObjective(ObjectiveDto objectiveDto){
        //判断当月是不是有记录了
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("employeeId", objectiveDto.getEmployeeId());
        map.put("objectiveMonth", objectiveDto.getObjectiveMonth());
        int count= employeeObjectiveMapper.isexistObjective(map);
        if (count>0){
            //有记录就进行修改
            objectiveDto.setUpdateTime(DateUtil.getCurTime());
            employeeObjectiveMapper.updateByPrimaryKey(objectiveDto);
            return 0; 
        }
        objectiveDto.setCreateTime(DateUtil.getCurTime());
        employeeObjectiveMapper.insert(objectiveDto);
        return 0;
    }
    /**
     * 详情
    * @author chendb
    * @date 2015年8月17日 下午7:16:21
    * @param objectiveId 目标标识
    * @return ObjectiveDto
     */
    public ObjectiveDto queryDetail(Integer objectiveId){
        ObjectiveDto info =employeeObjectiveMapper.queryDetail(objectiveId);
        return info;
    }
    /**
     * 修改
    * @author chendb
    * @date 2015年8月17日 下午8:10:26
    * @param objectiveDto 参数
    * @return int
     */
    public int updateObjective(ObjectiveDto objectiveDto){
        //判断当月是不是有记录了
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("employeeId", objectiveDto.getEmployeeId());
        map.put("objectiveMonth", objectiveDto.getObjectiveMonth());
        int count= employeeObjectiveMapper.isexistObjective(map);
        if (count>0){
            return 1; 
        }
        employeeObjectiveMapper.updateByPrimaryKey(objectiveDto);
        return 0;
    }
    /**
     * 删除目标
    * @author chendb
    * @date 2015年8月18日 上午10:03:26
    * @param objectiveId 目标标识
    * @return int
     */
    public int deleteObjective(Integer objectiveId){
        employeeObjectiveMapper.deleteByPrimaryKey(objectiveId);
        return 0;
    }
    /**
     * 获取月目标相关统计信息
    * @author chendb
    * @date 2015年8月20日 上午11:24:54
    * @param map 参数
    * @return Map<String, Object>
     */
    public Map<String, Object>getsum(Map<String, Object>map){
        Map<String, Object>info=employeeObjectiveMapper.getsum(map);
        return info;
    }
    /**
     * 导出功能
    * @author chendb
    * @date 2015年10月12日 上午11:10:26
    * @param response 返回
    * @param map 参数
    * @return BaseDto
     */
    public BaseDto downloadExcle(HttpServletResponse response, Map<String, Object> map){
        String objectiveMonth=map.get("objectiveMonth").toString();
        String fileName=objectiveMonth+"员工目标";  
    	
    	List<EmployeeDto> objectlist=employeeObjectiveMapper.downloadExcle(map);
    	List<Map<String, Object>> list=createExcelRecord(objectlist);
    	//列名
        String [] columnNames={"员工编码", "员工姓名", "部门", "岗位", "劳动业绩总体目标", "制定业绩目标", "套餐销售目标", "商品销售目标", "开卡/充值目标"};
        //map中的key
        String [] keys ={"employeeCode", "name", "deptName", "positionName", "totalProjectSales", 
            "assignProjectSales", "comboSales", "goodsSales", "chargeSales"};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } 
        catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bis != null){
                try {
                    bis.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null){
                try {
                    bos.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    	return null;
    }

    /**
     * 
    * @author chendb
    * @date 2015年10月12日 上午11:09:57
    * @param objectlist 集合
    * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> createExcelRecord(List<EmployeeDto> objectlist) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        EmployeeDto employeeDto=null;
        for (int j = 0; j < objectlist.size(); j++) {
        	employeeDto = objectlist.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("employeeCode", employeeDto.getEmployeeCode());
            mapValue.put("name", employeeDto.getName());
            mapValue.put("deptName", employeeDto.getDeptName());
            mapValue.put("positionName", employeeDto.getPositionName());
            if (employeeDto.getEmployeeObjective()!=null){
            	if (employeeDto.getEmployeeObjective().getTotalProjectSales()!=null){
            		mapValue.put("totalProjectSales", employeeDto.getEmployeeObjective().getTotalProjectSales());
            	}
            	else {
            		mapValue.put("totalProjectSales", 0);
            	}
            	if (employeeDto.getEmployeeObjective().getAssignProjectSales()!=null){
            		mapValue.put("assignProjectSales", employeeDto.getEmployeeObjective().getAssignProjectSales());
            	}
            	else {
            		mapValue.put("assignProjectSales", 0);
            	}
            	if (employeeDto.getEmployeeObjective().getComboSales()!=null){
            		mapValue.put("comboSales", employeeDto.getEmployeeObjective().getComboSales());
            	}
            	else {
            		mapValue.put("comboSales", 0);
            	}
            	if (employeeDto.getEmployeeObjective().getGoodsSales()!=null){
            		mapValue.put("goodsSales", employeeDto.getEmployeeObjective().getGoodsSales());
            	}
            	else {
            		mapValue.put("goodsSales", 0);
            	}
            	if (employeeDto.getEmployeeObjective().getChargeSales()!=null){
            		mapValue.put("chargeSales", employeeDto.getEmployeeObjective().getChargeSales());	
            	}
            	else {
            		mapValue.put("chargeSales", 0);
            	}
                
            }
            else {
            	mapValue.put("totalProjectSales", 0);
                mapValue.put("assignProjectSales", 0);
                mapValue.put("comboSales", 0);
                mapValue.put("goodsSales", 0);
                mapValue.put("chargeSales", 0);
            }
            
            listmap.add(mapValue);
        }
        return listmap;
    }
    /**
     * 导入模板下载
    * @author chendb
    * @date 2015年10月12日 下午3:36:14
    * @param response 返回
    * @param map 参数
    * @return BaseDto
     */
    public BaseDto downloadImport(HttpServletResponse response, Map<String, Object> map){
        
        String fileName="员工目标导入模板";
        
        List<EmployeeDto> objectlist=employeeObjectiveMapper.downloadExcle(map);
        List<Map<String, Object>> list=createExcelRecord1(objectlist);
        //列名
        String [] columnNames={"员工编码", "员工姓名", "部门", "岗位", "目标日期", "劳动业绩总体目标", "制定业绩目标", "套餐销售目标", "商品销售目标", "开卡/充值目标"};
        //map中的key
        String [] keys ={"employeeCode", "name", "deptName", "positionName", "objectiveMonth", "totalProjectSales", 
            "assignProjectSales", "comboSales", "goodsSales", "chargeSales"};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } 
        catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bis != null){
                try {
                    bis.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null){
                try {
                    bos.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * 导入模板下载
    * @author chendb
    * @date 2015年10月12日 下午3:37:44
    * @param objectlist 集合
    * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> createExcelRecord1(List<EmployeeDto> objectlist) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        EmployeeDto employeeDto=null;
        for (int j = 0; j < objectlist.size(); j++) {
            employeeDto = objectlist.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("employeeCode", employeeDto.getEmployeeCode());
            mapValue.put("name", employeeDto.getName());
            mapValue.put("deptName", employeeDto.getDeptName());
            mapValue.put("positionName", employeeDto.getPositionName());
            mapValue.put("objectiveMonth", DateUtil.getCurDate().substring(0, 7));
            if (employeeDto.getEmployeeObjective()!=null){
                if (employeeDto.getEmployeeObjective().getTotalProjectSales()!=null){
                    mapValue.put("totalProjectSales", employeeDto.getEmployeeObjective().getTotalProjectSales());
                }
                else {
                    mapValue.put("totalProjectSales", 0);
                }
                if (employeeDto.getEmployeeObjective().getAssignProjectSales()!=null){
                    mapValue.put("assignProjectSales", employeeDto.getEmployeeObjective().getAssignProjectSales());
                }
                else {
                    mapValue.put("assignProjectSales", 0);
                }
                if (employeeDto.getEmployeeObjective().getComboSales()!=null){
                    mapValue.put("comboSales", employeeDto.getEmployeeObjective().getComboSales());
                }
                else {
                    mapValue.put("comboSales", 0);
                }
                if (employeeDto.getEmployeeObjective().getGoodsSales()!=null){
                    mapValue.put("goodsSales", employeeDto.getEmployeeObjective().getGoodsSales());
                }
                else {
                    mapValue.put("goodsSales", 0);
                }
                if (employeeDto.getEmployeeObjective().getChargeSales()!=null){
                    mapValue.put("chargeSales", employeeDto.getEmployeeObjective().getChargeSales());   
                }
                else {
                    mapValue.put("chargeSales", 0);
                }
                
            }
            else {
                mapValue.put("totalProjectSales", 0);
                mapValue.put("assignProjectSales", 0);
                mapValue.put("comboSales", 0);
                mapValue.put("goodsSales", 0);
                mapValue.put("chargeSales", 0);
            }
            
            listmap.add(mapValue);
        }
        return listmap;
    }
    /**
     *导入功能
    * @author chendb
    * @date 2015年10月12日 下午1:49:39
    * @param file 对象
    * @param temp 临时目录
    * @param storeId 门店标识
    * @return  BaseDto
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
            List<ObjectiveDto> listDto= new ArrayList<ObjectiveDto>();
            while (rows.hasNext()) {  
                ObjectiveDto objectiveDto = new ObjectiveDto();
                //objectiveDto.setStoreId(storeId);
                //获得行数据 
                Row row = rows.next(); 
                
                if (row.getRowNum() > 0){
                    //获得行号从0开始  
                    //获得第一行的迭代器 
                    Iterator<Cell> cells = row.cellIterator(); 
                    Integer employeeId=0;
                    String objectiveMonth="";
                    while (cells.hasNext()) {  
                        Cell cell = cells.next();  
                        
                        if (cell.getColumnIndex() == 0){
                            
                            String returnValue = changeCellToString(cell);
                            Map<String, Object> map=new HashMap<String, Object>();
                            map.put("storeId", storeId);
                            map.put("employeeCode", returnValue);
                            EmployeeInfo employeeInfo =employeeInfoMapper.queryEmployeeCode(map);
                            employeeId=employeeInfo.getEmployeeId();
                            objectiveDto.setEmployeeId(employeeId);
                        }
                        else if (cell.getColumnIndex() == 4){
                            String returnValue = changeCellToString(cell);
                            objectiveMonth=returnValue;
                            if (objectiveMonth==""||objectiveMonth==null){
                                return new BaseDto(-1, "第 "+(row.getRowNum()+1)+"行目标日期不能为空！");
                            }
                            objectiveDto.setObjectiveMonth(returnValue);
                        }
                        else if (cell.getColumnIndex() == 5){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                returnValue="0";
                            }
                            objectiveDto.setTotalProjectSales(Integer.parseInt(returnValue));
                        }
                        else if (cell.getColumnIndex() == 6){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                returnValue="0";
                            }
                            objectiveDto.setAssignProjectSales(Integer.parseInt(returnValue));
                        }
                        else if (cell.getColumnIndex() == 7){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                returnValue="0";
                            }
                            objectiveDto.setComboSales(Integer.parseInt(returnValue));
                            
                        }
                        else if (cell.getColumnIndex() == 8){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                returnValue="0";
                            }
                            objectiveDto.setGoodsSales(Integer.parseInt(returnValue));
                        }
                        else if (cell.getColumnIndex() == 9){
                            String returnValue = changeCellToString(cell);
                            if (returnValue==""||returnValue==null){
                                returnValue="0";
                            }
                            objectiveDto.setChargeSales(Integer.parseInt(returnValue));
                        }
                    }
                    
                    listDto.add(objectiveDto);
                }
            }  
            
            for (int i = 0; i < listDto.size(); i++) {
                ObjectiveDto objectiveDto=listDto.get(i);
                Map<String, Object> map=new HashMap<String, Object>();
                map.put("employeeId", objectiveDto.getEmployeeId());
                map.put("objectiveMonth", objectiveDto.getObjectiveMonth());
                int count= employeeObjectiveMapper.isexistObjective(map);
                if (count>0){
                    //有记录就进行修改
                    employeeObjectiveMapper.updateByPrimaryKey(objectiveDto);
                }
                else {
                    employeeObjectiveMapper.importExcle(objectiveDto);  
                }
                
            }
        }
        catch (IOException ex) {  
            ex.printStackTrace();  
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功！");
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
    
}
