package com.zefun.web.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.ExcelUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.CodeLibraryDto;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.StoreFlowBaseDto;
import com.zefun.web.dto.StoreFlowDto;
import com.zefun.web.entity.CodeLibrary;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreFlow;
import com.zefun.web.mapper.CodeLibraryMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.StoreFlowMapper;

/**
 * 开支记账Service
* @author 王大爷
* @date 2015年8月11日 上午11:24:41
 */
@Service
public class StoreFlowService {
	
    /**
     * 开支记账mapper
     */
	@Autowired 
	private StoreFlowMapper storeFlowMapper;
	
	/**
	 * 数据字典mapper
	 */
	@Autowired
	private CodeLibraryMapper codeLibraryMapper;

	/** 部门*/
	@Autowired
	private DeptInfoMapper deptInfoMapper;
	
	/**
	 * 保存开支记账
	* @author laowang
	* @date 2015年8月4日 下午4:52:48
	* @param storeFlow 开支记账
	* @param userId 操作人员标识
	* @return BaseDto
	 */
	public BaseDto addStoreFlow(StoreFlow storeFlow, Integer userId){
	    Integer flowNum = storeFlow.getFlowNum();
	    storeFlow.setOperatorId(userId);
	    if (flowNum == 0) {
	        storeFlowMapper.insertSelective(storeFlow);
	    }
	    else {
	        BigDecimal flowAmount = new BigDecimal(Float.valueOf(String.valueOf(storeFlow.getFlowAmount()))/flowNum);
	        storeFlow.setFlowAmount(flowAmount);
	        for (int i = 0; i < flowNum; i++) {
	            try {
	                storeFlowMapper.insertSelective(storeFlow);
                    String flowTime = DateUtil.subMonth(storeFlow.getFlowTime());
                    storeFlow.setFlowTime(flowTime);
                } 
	            catch (ParseException e) {
                    e.printStackTrace();
                }
	        }
	    }
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	
	/**
	 * 修改开支记账
	* @author laowang
	* @date 2015年8月4日 下午4:52:48
	* @param storeFlow 开支记账
	* @return BaseDto
	 */
	public BaseDto updateStoreFlow(StoreFlow storeFlow){
		storeFlowMapper.updateByPrimaryKeySelective(storeFlow);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	
	/**
	 * 批量保存开支记账
	* @author laowang
	* @date 2015年8月5日 下午10:11:59
	* @param list
	* @return
	 */
	/**
	 * 
	* @author 王大爷
	* @date 2015年8月11日 上午11:27:28
	* @param file fileupload对象
	* @param temp 临时目录
	* @param storeId 门店标识
	* @return BaseDto
	 */
	public BaseDto insertStoreFlowList(MultipartFile file, String temp, Integer storeId){
		
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
            
            List<StoreFlow> sList = new ArrayList<StoreFlow>();
            
            while (rows.hasNext()) {  
            	StoreFlow storeFlow = new StoreFlow();
            	storeFlow.setStoreId(1);
            	//获得行数据 
                Row row = rows.next();   
                if (row.getRowNum() > 0){
                	//获得行号从0开始  
                	//获得第一行的迭代器 
                    Iterator<Cell> cells = row.cellIterator();     
                    while (cells.hasNext()) {  
                        Cell cell = cells.next();                          
                        if (cell.getColumnIndex() == 0){
                        	String returnValue = changeCellToString(cell);
                        	storeFlow.setBusinessType(returnValue);
                        }
                        else if (cell.getColumnIndex() == 1){
                        	String returnValue = changeCellToString(cell);
                        	storeFlow.setBusinessProject(returnValue);
                        }
                        else if (cell.getColumnIndex() == 2){
                        	String returnValue = changeCellToString(cell);
                        	storeFlow.setFlowSource(returnValue);
                        }
                        else if (cell.getColumnIndex() == 3){
                        	String returnValue = changeCellToString(cell);
                        	if (returnValue != ""){
                        		storeFlow.setFlowType(2);
                        		BigDecimal num=new BigDecimal(returnValue); 
                        		storeFlow.setFlowAmount(num);
                        	}
                        	
                        }
                        else if (cell.getColumnIndex() == 4){
                        	String returnValue = changeCellToString(cell);
                        	if (returnValue != ""){
                        		storeFlow.setFlowType(1);
                        		BigDecimal num=new BigDecimal(returnValue); 
                        		storeFlow.setFlowAmount(num);
                        	}
                        }
                        else if (cell.getColumnIndex() == 5){
                        	String returnValue = changeCellToString(cell);
                        	storeFlow.setPrincipalId(Integer.parseInt(returnValue));
                        }
                        else if (cell.getColumnIndex() == 6){
                        	String returnValue = changeCellToString(cell);
                        	storeFlow.setOperatorId(Integer.parseInt(returnValue));
                        }
                        else if (cell.getColumnIndex() == 7){
                        	String returnValue = changeCellToString(cell);
                        	storeFlow.setFlowTime(returnValue);
                        }
                        else if (cell.getColumnIndex() == 8){
                        	String returnValue = changeCellToString(cell);
                        	storeFlow.setBusinessDesc(returnValue);
                        }
                    }
                    storeFlow.setStoreId(storeId);
                    storeFlow.setIsDeleted(0);
                    sList.add(storeFlow);
                }
            }  
            storeFlowMapper.insertStoreFlowList(sList);
        }
        catch (IOException ex) {  
            ex.printStackTrace();  
        }
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	
	
	
	/**
	 * 转换读取数据类型
	* @author laowang
	* @date 2015年8月6日 上午10:47:45
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
	 * 导出excle
	* @author 中文姓名
	* @date 2015年8月6日 下午7:09:51
	* @param response 返回
	* @return BaseDto
	 */
	public BaseDto downloadExcle(HttpServletResponse response){
		String fileName="开支记账";
        //填充projects数据
		StoreFlow storeFlow = new StoreFlow();
        List<StoreFlow> storeFlowList= storeFlowMapper.selectByStoreFlow(storeFlow);
        List<Map<String, Object>> list=createExcelRecord(storeFlowList);
        //列名
        String [] columnNames={"收支类别", "收支项目", "收支来源/去向", "收入金额", "支出金额", "经手人", "记账人", "记账时间", "备注说明"};
        //map中的key
        String [] keys ={"businessType", "businessProject", "flowSource", "income", "outcome", 
            "principalId", "operatorId", "flowTime", "businessDesc"};
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
	 * 创建excle对象
	* @author 王大爷
	* @date 2015年8月11日 上午11:43:26
	* @param storeFlowList 开支记账集合
	* @return List<Map<String, Object>>
	 */
	private List<Map<String, Object>> createExcelRecord(List<StoreFlow> storeFlowList) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        StoreFlow storeFlow=null;
        for (int j = 0; j < storeFlowList.size(); j++) {
        	storeFlow = storeFlowList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("businessType", storeFlow.getBusinessType());
            mapValue.put("businessProject", storeFlow.getBusinessProject());
            mapValue.put("flowSource", storeFlow.getFlowSource());
            if (storeFlow.getFlowType() == 2){
            	mapValue.put("income", storeFlow.getFlowAmount());
            	mapValue.put("outcome", "");
            }
            else {
            	mapValue.put("income", "");
            	mapValue.put("outcome", storeFlow.getFlowAmount());
            }
            mapValue.put("principalId", storeFlow.getPrincipalId());
            mapValue.put("operatorId", storeFlow.getOperatorId());
            mapValue.put("flowTime", storeFlow.getFlowTime());
            mapValue.put("businessDesc", storeFlow.getBusinessDesc());
            
            listmap.add(mapValue);
        }
        return listmap;
	}
	
	/**
	 * 初始化开支记账页面
	* @author 王大爷
	* @date 2015年8月11日 上午11:45:06
	* @param storeId 门店标识
	* @param beginTime 开始时间
	* @param endTime 结束时间
	* @return ModelAndView
	 */
	public ModelAndView initializeStoreFlow(Integer storeId, Integer beginTime, Integer endTime){
		
		Page<StoreFlowBaseDto> page = selectPageForMemberLevel(storeId, 1, App.System.API_DEFAULT_PAGE_SIZE, beginTime, endTime);
		ModelAndView mav = new ModelAndView();
		List<CodeLibrary> businessTypeList =  codeLibraryMapper.selectByTypeNo(101);
		List<CodeLibrary> flowSourceList = codeLibraryMapper.selectByTypeNo(103);
		List<DeptInfoDto> deptInfoDtoList = deptInfoMapper.selectByshiftMahjong(storeId);
		CodeLibraryDto codeLibraryDto =  new CodeLibraryDto();
		codeLibraryDto.setFatherCodeNo(1001);
		codeLibraryDto.setTypeNo(102);
		List<CodeLibrary> businessProjectList = codeLibraryMapper.selectBySunCodeName(codeLibraryDto);
		mav.addObject("page", page);
		mav.addObject("businessTypeList", businessTypeList);
		mav.addObject("businessProjectList", businessProjectList);
		mav.addObject("flowSourceList", flowSourceList);
		mav.addObject("beginDate", DateUtil.getMinMonthDateStr());
		mav.addObject("endDate", DateUtil.getMaxMonthDateStr());
		mav.addObject("toDate", DateUtil.getCurDateChine());
		mav.addObject("deptInfoDtoList", deptInfoDtoList);
		
		Map<String, Object> map = selectCome(storeId, beginTime, endTime);
		
		mav.addObject("outComeAll", map.get("outComeAll"));
		mav.addObject("inComeAll", map.get("inComeAll"));
		
		mav.setViewName(View.KeepAccounts.STOREFLOW);
		
		return mav;
	}
	
	/**
	 * 动态生成项目类别
	* @author laowang
	* @date 2015年8月7日 下午2:33:23
	* @param codeName 数据字典名称
	* @return BaseDto
	 */
	public BaseDto trendCodeLibrary(String codeName){
		CodeLibraryDto codeLibraryDto =  new CodeLibraryDto();
		codeLibraryDto.setCodeName(codeName);
		codeLibraryDto.setTypeNo(101);
		CodeLibrary codeLibrary = codeLibraryMapper.selectByCodeName(codeLibraryDto);
		CodeLibraryDto codeLibraryDtos =  new CodeLibraryDto();
		codeLibraryDtos.setFatherCodeNo(codeLibrary.getCodeNo());
		codeLibraryDtos.setTypeNo(102);
		List<CodeLibrary> businessProjectList = codeLibraryMapper.selectBySunCodeName(codeLibraryDtos);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, businessProjectList);
	}
	
	/**
     * 分页查询某个门店的开卡记账界面
    * @author laowang
    * @date Aug 5, 2015 7:58:53 PM
    * @param storeId 门店标识
    * @param pageNo     页码
    * @param pageSize   每页显示数
    * @param beginTime 开始时间
    * @param endTime 结束时间
    * @return BaseDto
     */
	public BaseDto storeFlowList(Integer storeId, int pageNo, int pageSize, Integer beginTime, Integer endTime){
		Page<StoreFlowBaseDto> page = selectPageForMemberLevel(storeId, pageNo, pageSize, beginTime, endTime);
		Map<String, Object> map = selectCome(storeId, beginTime, endTime);
		map.put("page", page);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
	}
	
	/**
	 * 分页查询某个门店的开卡记账信息
	* @author laowang
	* @date Aug 5, 2015 8:01:41 PM
	* @param storeId	店铺标识
	* @param pageNo		页码
	* @param pageSize	每页显示数
	* @param beginTime 开始时间
    * @param endTime 结束时间
	* @return Page<StoreFlow>
	 */
	private Page<StoreFlowBaseDto> selectPageForMemberLevel(Integer storeId, int pageNo, int pageSize, Integer beginTime, Integer endTime){
		Page<StoreFlowBaseDto> page = new Page<StoreFlowBaseDto>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", storeId);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		page.setParams(params);
		List<StoreFlowBaseDto> list = storeFlowMapper.selectByPage(page);
		page.setResults(list);
		return page;
	}
	
	/**
	 * 删除开支记账
	* @author 王大爷
	* @date 2015年8月11日 上午11:48:36
	* @param flowId 开支记账ID
	* @return BaseDto
	 */
	public BaseDto deleteStoreFlow(Integer flowId){
		storeFlowMapper.deleteByPrimaryKey(flowId);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	
	/**
	 * 金额汇总
	* @author 王大爷
	* @date 2015年8月11日 上午11:49:21
	* @param storeId 门店标识
	* @param beginTime 开始时间
	* @param endTime 结束时间
	* @return Map<String, Object>
	 */
	private Map<String, Object> selectCome(Integer storeId, Integer beginTime, Integer endTime){
		Map<String, Object> map = new HashMap<String, Object>();
		StoreFlowDto outStoreFlowDto = new StoreFlowDto();
		outStoreFlowDto.setFlowType(1);
		outStoreFlowDto.setStoreId(storeId);
		outStoreFlowDto.setBeginTime(beginTime);
		outStoreFlowDto.setEndTime(endTime);
		Integer outComeAll = storeFlowMapper.selectByCome(outStoreFlowDto);
		
		StoreFlowDto inStoreFlowDto = new StoreFlowDto();
		inStoreFlowDto.setFlowType(2);
		inStoreFlowDto.setStoreId(storeId);
		inStoreFlowDto.setBeginTime(beginTime);
		inStoreFlowDto.setEndTime(endTime);
		Integer inComeAll = storeFlowMapper.selectByCome(inStoreFlowDto);
		
		map.put("inComeAll", inComeAll);
		map.put("outComeAll", outComeAll);
		
		return map;
	}
}
