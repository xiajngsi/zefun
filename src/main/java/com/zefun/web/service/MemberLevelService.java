package com.zefun.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
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
import com.zefun.common.utils.ExcleUtils;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.Page;
import com.zefun.web.mapper.MemberLevelMapper;


/**
 * 会员等级(会员卡)管理
 * @author 张进军
 * @date Aug 5, 2015 6:28:51 PM 
 */
@Service
public class MemberLevelService {

    /** 会员等级数据操作对象 */
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    /***/
    private static Logger log = Logger.getLogger(MemberLevelService.class);

    /**
     * 为某个门店新增会员等级
    * @author 张进军
    * @date 2015年8月11日 下午3:15:41
    * @param storeId 门店id
    * @param userId 用户id
    * @param memberLevel 会员等级
    * @return BaseDto
     */
    @Transactional
    public BaseDto addAction(Integer storeId, Integer userId,
            MemberLevel memberLevel) {
        memberLevel.setStoreId(storeId);
        String curTime = DateUtil.getCurTime();
        memberLevel.setLastOperatorId(userId);
        memberLevel.setUpdateTime(curTime);
        if (memberLevel.getLevelId() != null) {
            memberLevelMapper.updateByPrimaryKey(memberLevel);
        } 
        else {
            memberLevel.setCreateTime(curTime);
            memberLevel.setIsDeleted(0);
            memberLevelMapper.insert(memberLevel);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 查询某个店铺的会员等级信息
     * 默认返回该门店最前面10条数据
    * @author 张进军
    * @date Aug 5, 2015 7:58:33 PM
    * @param storeId	门店标识
    * @return ModelAndView
    */
    public ModelAndView listView(Integer storeId) {
        Page<MemberLevel> page = selectPageForMemberLevel(storeId, 1, App.System.API_DEFAULT_PAGE_SIZE);
        ModelAndView mav = new ModelAndView("member/memberLevel/list");
        mav.addObject("page", page);
        return mav;
    }

    /**
     * 分页查询某个门店的会员等级信息
    * @author 张进军
    * @date Aug 5, 2015 7:58:53 PM
    * @param storeId	门店标识
    * @param pageNo		页码
    * @param pageSize	每页显示数
    * @return BaseDto
     */
    public BaseDto listAction(Integer storeId, int pageNo, int pageSize) {
        Page<MemberLevel> page = selectPageForMemberLevel(storeId, pageNo,
                pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }

    /**
     * 分页查询某个门店的会员等级信息
    * @author 张进军
    * @date Aug 5, 2015 8:01:41 PM
    * @param storeId	店铺标识
    * @param pageNo		页码
    * @param pageSize	每页显示数
    * @return Page<MemberLevel>
     */
    private Page<MemberLevel> selectPageForMemberLevel(Integer storeId,
            int pageNo, int pageSize) {
        Page<MemberLevel> page = new Page<MemberLevel>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<MemberLevel> list = memberLevelMapper.selectByPage(page);
        page.setResults(list);
        return page;
    }

    /**
     * 根据等级标识查询等级信息
    * @author 张进军
    * @date Aug 5, 2015 11:45:13 PM
    * @param levelId	会员等级标识
    * @return BaseDto
     */
    public BaseDto infoAction(Integer levelId) {
        MemberLevel memberLevel = memberLevelMapper.selectByPrimaryKey(levelId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, memberLevel);
    }

    /**
     * 根据等级标识删除等级信息,逻辑删除
    * @author 张进军
    * @date Aug 5, 2015 11:45:13 PM
    * @param levelId	会员等级标识
    * @return BaseDto
     */
    public BaseDto deleteAction(Integer levelId) {
        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setLevelId(levelId);
        memberLevel.setIsDeleted(1);
        memberLevelMapper.updateDeleteByLevelId(memberLevel);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 根据门店id查询会员等级列表
    * @author 洪秋霞
    * @date 2015年8月10日 下午3:51:20
    * @param storeId 门店id
    * @return List<MemberLevel>
     */
    public List<MemberLevel> queryByStoreId(Integer storeId) {
        return memberLevelMapper.selectByStoreId(storeId);
    }

    /**
     * 将指定等级标识设为门店的默认等级
    * @author 张进军
    * @date Oct 12, 2015 8:53:36 AM
    * @param storeId    门店标识
    * @param levelId    等级标识
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
    */
    @Transactional
    public BaseDto defaultAction(Integer storeId, Integer levelId) {
        // 先将门店的所有等级修改为非默认等级
        memberLevelMapper.updateNonDefaultByStoreId(storeId);
        // 再将指定的会员等级设为默认
        memberLevelMapper.updateDefaultByLevelId(levelId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 盛传会员卡导入
    * @author 高国藩
    * @date 2015年10月19日 上午9:37:26
    * @param file 文件
    * @param storeId 门店
    * @param lastOperatorId 最后操作人员ID
    * @return BaseDto
     * @throws IOException 
     */
    @SuppressWarnings("unused")
    @Transactional
    public BaseDto importExcle(MultipartFile file, Integer storeId, Integer lastOperatorId) throws IOException {
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return null;
        }
        boolean isE2007 = false;
        if (name.endsWith("xlsx")) {
            isE2007 = true;
        }
        InputStream input = file.getInputStream();
        Workbook wb = null;
        if (isE2007) {
            wb = new XSSFWorkbook(input);
        } 
        else {
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        List<MemberLevel> levels = new ArrayList<>();
        List<MemberLevel> memberLevels = memberLevelMapper.selectByStoreId(storeId);
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberLevels.size(); i++) {
            hasStr.add(memberLevels.get(i).getLevelName());
        }
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (xssfRow != null) {
                MemberLevel level = new MemberLevel();
                for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    try {
                        Cell cell = xssfRow.getCell(cellNum);
                        String str = ExcleUtils.changeCellToString(cell);
                        if (cellNum == 1) {
                            if (hasStr.contains(str)){
                                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "第"+(rowNum+1)+"行"+"第"+(cellNum+1)+"列  "+str+" 会员卡名称已存在");
                            }
                            level.setLevelName(str);
                        }
                        if (cellNum == 3) {
                            Integer projectDiscount = (int)Double.parseDouble(str)*10;
                            level.setProjectDiscount(projectDiscount);
                        }
                        if (cellNum == 4) {
                            Integer goodsDiscount = (int)Double.parseDouble(str)*10;
                            level.setGoodsDiscount(goodsDiscount);
                        }
                        if (cellNum == 10) {
                            if (str.equals("开卡门店")){
                                level.setChargeBelongType(1); 
                            }
                            else {
                                level.setChargeBelongType(2); 
                            }
                        }
                        level.setStoreId(storeId);
                        level.setLastOperatorId(lastOperatorId);
                        level.setCreateTime(DateUtil.getCurDate());
                        level.setIsDefault(0);
                        level.setIsDeleted(0);
                    } 
                    catch (Exception e) {
                        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "出错位置:"+"第"+(rowNum+1)+"行"+"第"+(cellNum+1)+"列"); 
                    }
                }
                levels.add(level);
            }
        }
        log.info(JSONArray.fromObject(levels).toString());
        //插入数据
        for (int i = 0; i < levels.size(); i++) {
            int ok = memberLevelMapper.insert(levels.get(i));
            log.info(ok);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }
}
