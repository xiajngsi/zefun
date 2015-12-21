package com.zefun.web.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.EntityJsonConverter;
import com.zefun.common.utils.ExcleUtils;
import com.zefun.common.utils.MessageUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberGroupDto;
import com.zefun.web.dto.MemberInfoDto;
import com.zefun.web.dto.ScreeningDto;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberComboProject;
import com.zefun.web.entity.MemberComboRecord;
import com.zefun.web.entity.MemberErrorBk;
import com.zefun.web.entity.MemberErrorHt;
import com.zefun.web.entity.MemberErrorSc;
import com.zefun.web.entity.MemberErrorZy;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberScreening;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.TempTableSc;
import com.zefun.web.entity.TempTableZy;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberComboProjectMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.mapper.MemberErrorBkMapper;
import com.zefun.web.mapper.MemberErrorHtMapper;
import com.zefun.web.mapper.MemberErrorScMapper;
import com.zefun.web.mapper.MemberErrorZyMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.TempTableZyMapper;

/**
 * 会员信息业务逻辑类
* @author 张进军
* @date Aug 19, 2015 4:36:14 PM 
*/
@Service
@Transactional
public class MemberInfoService {
    /** 会员信息数据库操作对象 */
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    /** redis操作类 */
    @Autowired
    private RedisService redisService;
    /**筛选器*/
    @Autowired
    private MemberScreeningMapper memberScreeningMapper;
    /**会员等级*/
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    /**会员账务表*/
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    /**会员数据临时表数据-左右*/
    @Autowired
    private TempTableZyMapper tempTableXyMapper;
    /**会员数据临时表数据-左右*/
    @Autowired
    private MemberErrorZyMapper memberErrorZyMapper;
    /**会员数据临时表数据-盛传*/
    @Autowired
    private MemberErrorScMapper memberErrorScMapper;
    /**门店设置*/
    @Autowired
    private StoreSettingMapper storeSettingMapper;
    /**处理云浩错误数据的导入*/
    @Autowired
    private MemberErrorHtMapper memberErrorHtMapper;
    /**处理博卡错误数据的导入*/
    @Autowired
    private MemberErrorBkMapper memberErrorBkMapper;
    /**查询门店信息*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    /**余额流水表*/
    @Autowired
    private MoneyFlowMapper moneyFlowMapper;
    /**余额流水表*/
    @Autowired
    private IntegralFlowMapper integralFlowMapper;
    /**礼金流水*/
    @Autowired
    private GiftmoneyDetailMapper giftmoneyDetailMapper;
    /**礼金流水*/
    @Autowired
    private GiftmoneyFlowMapper giftmoneyFlowMapper;
    /**项目*/
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    /**项目*/
    @Autowired
    private MemberComboProjectMapper memberComboProjectMapper;
    /**项目*/
    @Autowired
    private MemberComboRecordMapper memberComboRecordMapper;
    /**日志记录对象*/
    private static Logger log = Logger.getLogger(MemberLevelService.class);

    /**
     * 根据会员标识查询会员基本信息
    * @author 张进军
    * @date Aug 19, 2015 5:42:21 PM
    * @param memberId   会员标识
    * @return           会员基本信息
     */
    public MemberBaseDto getMemberBaseInfo(Integer memberId) {
        if (memberId == null) {
            return null;
        }

        String memberBaseInfoJson = redisService.hget(App.Redis.MEMBER_BASE_INFO_KEY_HASH, memberId);
        MemberBaseDto memberBaseInfo = null;
        // 首先从缓存中获取，如果缓存中不存在，则从数据库查出并缓存
        if (StringUtils.isBlank(memberBaseInfoJson)) {
            memberBaseInfo = memberInfoMapper.selectMemberBaseInfo(memberId);
            if (memberBaseInfo == null) {
                return null;
            }
            redisService.hset(App.Redis.MEMBER_BASE_INFO_KEY_HASH, memberId,
                    EntityJsonConverter.entity2Json(memberBaseInfo));
        }
        // 缓存中存在则直接转换为对象
        else {
            memberBaseInfo = EntityJsonConverter.json2Entity(memberBaseInfoJson,
                    MemberBaseDto.class);
        }
        return memberBaseInfo;
    }


    /**
     * 检查是否有存在该手机号的会员
    * @author 张进军
    * @date Aug 19, 2015 7:54:03 PM
    * @param phone      手机号码
    * @param storeId 门店标识
    * @return           true:存在,false:不存在
     */
    public boolean isExists(String phone, Integer storeId) {
        Integer result = selectMemberIdByPhone(phone, storeId);
        if (result == null) {
            return false;
        }
        return true;
    }

    /**
     * 根据手机号码查询会员标识
    * @author 张进军
    * @date Aug 19, 2015 7:49:56 PM
    * @param phone  手机号码
    * @param storeId 门店标识
    * @return       如果存在结果返回会员标识，不存在返回null
     */
    public Integer selectMemberIdByPhone(String phone, Integer storeId) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setPhone(phone);
        memberInfo.setStoreId(storeId);
        return memberInfoMapper.selectMemberIdByPhone(memberInfo);
    }

    /**
     * 根据手机号码查询会员基本信息
    * @author 王大爷
    * @date Aug 19, 2015 7:49:56 PM
    * @param phone  手机号码
    * @param storeId 门店标识
    * @return       如果存在结果返回会员基本信息，不存在返回null
     */
    public MemberBaseDto selectMemberInfoByPhone(String phone,
            Integer storeId) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setPhone(phone);
        memberInfo.setStoreId(storeId);
        return memberInfoMapper.selectMemberInfoByPhone(memberInfo);
    }

    /**
     * 查询所有会员
    * @author 高国藩
    * @date 2015年9月8日 下午6:01:15
    * @param storeId  门店标示
    * @return         跳转
     */
    public ModelAndView selectMemberByStoreId(Integer storeId) {
        Page<MemberInfoDto> page = new Page<MemberInfoDto>();
        page.setPageNo(1);
        page.setPageSize(50);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId);
        page.setParams(map);
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper
                .selectMemberInfosByPage(page);
        page.setResults(memberInfoDtos);
        List<MemberLevel> levels = memberLevelMapper.selectByStoreId(storeId);
        ModelAndView view = new ModelAndView(View.MemberInfo.MEMBER_LIST_VIEW);
        view.addObject("page", page);
        view.addObject("levels", levels);
        MemberInfoDto dto = memberInfoMapper.selectStoreMemberAmount(storeId);
        view.addObject("storeMember", dto);
        return view;
    }

    /**
     * 新增一个筛选器
    * @author 高国藩
    * @date 2015年9月8日 下午8:14:18
    * @param storeId         门店
    * @param memberScreening 实体
    * @param branchOffice    总店查询分店
    * @return                状态
     */
    public BaseDto addMemberScreening(Integer storeId, MemberScreening memberScreening, String branchOffice) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = sdf.format(new Date());
        memberScreening.setCreateTime(createTime);
        memberScreening.setStoreId(storeId);
        if (branchOffice!=null&&!branchOffice.equals("null")){
            memberScreening.setBranchStoreIds(branchOffice);
        }
        else if (branchOffice!=null&&branchOffice.equals("null")){
            List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(storeId);
            List<Integer> stores = new ArrayList<>();
            if (storeList!=null&&storeList.size()>0){
                for (int i = 0; i < storeList.size(); i++) {
                    stores.add(storeList.get(i).getStoreId());
                }
            }
            memberScreening.setBranchStoreIds(stores.toString().substring(1, stores.toString().length()-1));
        }
        else if (branchOffice==null){
            //这里是空的话呢,就说明是单点的分组信息
            memberScreening.setBranchStoreIds(null);
        }

        int ok = memberScreeningMapper.insertSelective(memberScreening);
        if (ok > 0) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                    App.System.API_RESULT_MSG_FOR_SUCCEES);
        } 
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL,
                    App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 进入会员分组页面
    * @author 高国藩
    * @date 2015年9月11日 上午10:13:35
    * @param storeId   门店信息
    * @return          跳转页面
     */
    public ModelAndView viewCensusList(Integer storeId) {
        StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
        if (storeInfo.getStoreType()!=2){
            List<ScreeningDto> dtos = memberScreeningMapper.selectDtosByStoreId(storeId);
            List<MemberGroupDto> groupDtos = memberInfoMapper.selectGroupDtos(storeId);
            for (int i = 0; i < dtos.size(); i++) {
                MemberGroupDto groupDto = groupDtos.get(i);
                groupDto.setGroupCondition(dtos.get(i).toString());
                List<Integer> memberIdList = groupDto.getMemberIds();
                groupDto.setMemberCount(groupDto.getMemberIds().size()+"");
                // 根据当前时间判断该月份新增的人数
                Map<String, Object> dateMap = new HashMap<String, Object>();
                dateMap.put("startDate", MessageUtil.getMinMonthDate());
                dateMap.put("stopDate", MessageUtil.getMaxMonthDate());
                if (memberIdList.size() <= 0) {
                    memberIdList.add(0);
                }
                dateMap.put("memberList", memberIdList);
                Integer memberuNewNum = memberInfoMapper.selectMemberCountByDate(dateMap);
                groupDto.setNewMember(memberuNewNum.toString());
            }
            ModelAndView view = new ModelAndView(View.MemberInfo.GROUP_LIST_VIEW);
            view.addObject("groupDtos", groupDtos);
            view.addObject("type", 1);
            return view;
        }
        else {
            List<ScreeningDto> dtos = memberScreeningMapper.selectDtosByStoreId(storeId);
            List<MemberGroupDto> groupDtos = memberInfoMapper.selectGroupDtos(storeId);
            for (int i = 0; i < dtos.size(); i++) {
                MemberGroupDto groupDto = groupDtos.get(i);
                groupDto.setGroupCondition(dtos.get(i).toString());
                groupDto.setMemberCount(groupDto.getMemberIds().size()+"");
            }
            ModelAndView view = new ModelAndView(View.MemberInfo.GROUP_LIST_VIEW);
            view.addObject("groupDtos", groupDtos);
            view.addObject("type", 2);
            return view;
        }
        
    }

    /**
     * 根据选择器来查询会员数据
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param groupId 筛选器
    * @param storeId 门店
    * @param page 分页
    * @return 数据信息
     */
    public BaseDto viewListMemberByScreen(Integer storeId, Integer groupId, Page<MemberInfoDto> page) {
        ScreeningDto dto = memberScreeningMapper.selectByDto(groupId);
        return listMemberInfosByTerm(dto, page, null);
    }

    /**
     * 通过预设的条件查询会员数据
    * @author 高国藩
    * @date 2015年9月12日 上午9:54:15
    * @param screeningDto 查询条件
    * @param page 分页
    * @param branchOffice 分店信息
    * @return 会员数据
     */
    public BaseDto listMemberInfosByTerm(ScreeningDto screeningDto, Page<MemberInfoDto> page, String branchOffice) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (branchOffice!=null&&!"null".equals(branchOffice)){
            List<StoreInfo> storeList = new ArrayList<>();
            for (int i = 0; i < branchOffice.split(",").length; i++) {
                StoreInfo info = new StoreInfo();
                info.setStoreId(Integer.parseInt(branchOffice.split(",")[i]));
                storeList.add(info);
            }
            params.put("storeList", storeList);
            screeningDto.setStoreId(null);
        }
        else if (branchOffice!=null&&"null".equals(branchOffice)){
            List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(screeningDto.getStoreId());
            if (storeList!=null&&storeList.size()>0){
                params.put("storeList", storeList);
                screeningDto.setStoreId(null);
            }
        }
        else if (branchOffice==null&&screeningDto.getBranchStoreIds()!=null){
            //如果screeningDto中的branchStoreIds不是空,那么说明点击分组中的查询进行查询的
            screeningDto.setStoreId(null);
        }
        else if (branchOffice==null&&screeningDto.getBranchStoreIds()==null){
          //这里什么都不做代表的就不是连锁店
        }
        params.put("dto", screeningDto);
        page.setParams(params);
        List<MemberInfoDto> ls = memberInfoMapper.selectByPageParams(page);
        page.setResults(ls);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }

    /**
     * 删除会员分组
    * @author 高国藩
    * @date 2015年9月12日 下午2:35:22
    * @param groupId 分组标示
    * @return 删除状态
     */
    public BaseDto deleteMemberCensus(Integer groupId) {
        int ok = memberScreeningMapper.deleteByPrimaryKey(groupId);
        if (ok > 0) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                    App.System.API_RESULT_MSG_FOR_SUCCEES);
        } 
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL,
                    App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 通过手机或者姓名进行查询会员数据
    * @author 高国藩
    * @date 2015年10月9日 下午8:23:52
    * @param storeId 门店
    * @param sex 性别
    * @param levelId 会员等级
    * @param content 查询条件
    * @param page 分页
    * @return 返回数据
     */
    public BaseDto listMemberInfosByContent(Integer storeId, String content,
            Page<MemberInfoDto> page, String sex, Integer levelId) {
        List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(storeId);
        Map<String, Object> params = new HashMap<String, Object>();
        if (storeList!=null&&storeList.size()>0){
            params.put("storeList", storeList);
        }
        else {
            params.put("storeId", storeId);
        }
        params.put("content", content);
        params.put("sex", sex);
        params.put("levelId", levelId);
        page.setParams(params);
        List<MemberInfoDto> ls = memberInfoMapper
                .selectByPageOrderNameOrPhone(page);
        page.setResults(ls);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    
    /**
     * 清除redis中的缓存信息
     * 
    * @param memberId   会员标识
    * @author 张进军
    * @date Nov 21, 2015 5:05:48 PM
     */
    public void wipeCache(int memberId){
        //更新会员缓存信息
        redisService.hdel(App.Redis.MEMBER_BASE_INFO_KEY_HASH, memberId);
    }

    /**
     * 盛传数据格式导入会员数据
    * @author 高国藩
    * @date 2015年10月19日 上午9:37:26
    * @param file 文件
    * @param storeId 门店
    * @param lastOperatorId 最后操作人员ID
    * @param response       输出流 
    * @return BaseDto
     * @throws Exception 
     */
    @SuppressWarnings("unused")
    @Transactional
    public BaseDto importExcleSc(MultipartFile file, Integer storeId,
            Integer lastOperatorId, HttpServletResponse response)
                    throws Exception {
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
        List<MemberInfo> memberInfos = new ArrayList<>();
        List<MemberAccount> accounts = new ArrayList<>();
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        // 会员等级和名称对应,方便取值
        Map<String, Integer> levelMap = packLevelMap(storeId);
        // 将其中的套餐的行数保存下载,用于生成error数据,提供查询
        List<Integer> rowNums = new ArrayList<>();
        List<TempTableSc> tableScs = new ArrayList<>();
        a: for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (xssfRow != null) {
                MemberInfo memberInfo = new MemberInfo();
                MemberAccount memberAccount = new MemberAccount();
                TempTableSc tableSc = new TempTableSc();
                b: for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    try {
                        Cell cell = xssfRow.getCell(cellNum);
                        String str = ExcleUtils.changeCellToString(cell);
                        // 先封装一层会员数据
                        if (cellNum == 0) {
                            if (str.length() < 11) {
                                rowNums.add(rowNum);
                                continue a;
                            }
                            if (hasStr.contains(str)) {
                                return new BaseDto(
                                        App.System.API_RESULT_CODE_FOR_FAIL, "第" + (rowNum + 1) + "行" + "第" + (cellNum + 1) + "列" + "   手机号码已存在");
                            } 
                            else {
                                Pattern p1 = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
                                Matcher m = p1.matcher(str); 
                                if (m.matches()){
                                    tableSc.setPhone(str);
                                    memberInfo.setPhone(str);
                                }
                                else {
                                    rowNums.add(rowNum);
                                    log.info("phone num is not a true phone Num,this is not a memberInfo....");
                                    continue a;
                                }
                            }
                        }
                        if (cellNum == 1) {
                            tableSc.setName(str);
                            memberInfo.setName(str);
                        }
                        if (cellNum == 2) {
                            tableSc.setSex(str);
                            memberInfo.setSex(str);
                        }
                        if (cellNum == 6) {
                            tableSc.setLevelName(str);
                        }
                        tableSc.setStoreId(storeId);
                        tableSc.setCreateTime(DateUtil.getCurDate());
                        
                        memberInfo.setStoreId(storeId);
                        memberInfo.setCreateTime(DateUtil.getCurDate());
                        memberInfo.setIsDeleted(0);
                        memberInfo.setLastOperatorId(lastOperatorId);
                        //再封装一层会员消费数据
                        if (cellNum == 4) {
                            tableSc.setCreateTime(str);
                            memberAccount.setCreateTime(str);
                        }
                        if (cellNum == 9) {
                            tableSc.setTotalAmount(new BigDecimal(str));
                            memberAccount.setTotalAmount(new BigDecimal(str));
                        }
                        if (cellNum == 10) {
                            tableSc.setTotalConsumeAmount(new BigDecimal(str));
                            memberAccount.setTotalConsumeAmount(new BigDecimal(str));
                        }
                        if (cellNum == 11) {
                            tableSc.setBalanceAmount(new BigDecimal(str));
                            memberAccount.setBalanceAmount(new BigDecimal(str));
                        }
                        if (cellNum == 15) {
                            tableSc.setBalanceIntegral(new BigDecimal(str));
                            memberAccount.setBalanceIntegral(Integer.parseInt(str));
                        }
                        if (cellNum == 16) {
                            tableSc.setLastConsumeTime(str);
                            memberAccount.setLastConsumeTime(str);
                        }
                        memberAccount.setLastOperatorId(lastOperatorId);
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "出错位置:" + "第" + (rowNum + 1) + "行" + "第" + (cellNum + 1) + "列");
                    }
                }
                memberInfos.add(memberInfo);
                accounts.add(memberAccount);
                tableScs.add(tableSc);
            }
        }
        // 将套餐类型的数据输出为新的excle
        if (rowNums.size()>0){
            executeExcleSc(sheet, rowNums, storeId);
        }
        // 插入数据
        Set<String> levels = new HashSet<>();
        for (int i = 0; i < tableScs.size(); i++) {
            if (levelMap.get(tableScs.get(i).getLevelName())==null){
                levels.add(tableScs.get(i).getLevelName());
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        //先插入会员卡等级数据
        for (String level : levels) {
            MemberLevel level1 = new MemberLevel();
            level1.setLevelName(level);
            level1.setStoreId(hsqStoreId);
            level1.setIsDeleted(0);
            memberLevelMapper.insert(level1);
        }
        levelMap = packLevelMap(hsqStoreId);
        for (int k = 0; k < tableScs.size(); k++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(tableScs.get(k).getName());
            memberInfo.setSex(tableScs.get(k).getSex());
            memberInfo.setPhone(tableScs.get(k).getPhone());
            memberInfo.setIsDeleted(0);
            memberInfo.setStoreId(storeId);
            memberInfo.setCreateTime(DateUtil.getCurDate());
            memberInfo.setLevelId(levelMap.get(tableScs.get(k).getLevelName()));
            memberInfo.setLastOperatorId(lastOperatorId);
            memberInfoMapper.insert(memberInfo);
            MemberAccount account = new MemberAccount();
            account.setAccountId(memberInfo.getMemberId());
            account.setBalanceAmount(tableScs.get(k).getBalanceAmount());
            account.setTotalAmount(tableScs.get(k).getTotalAmount());
            account.setTotalConsumeAmount(tableScs.get(k).getTotalConsumeAmount());
            account.setBalanceIntegral(tableScs.get(k).getBalanceAmount().intValue());
            account.setTotalIntegral(tableScs.get(k).getBalanceAmount().intValue());
            account.setLastConsumeTime(tableScs.get(k).getLastConsumeTime());
            memberAccountMapper.insert(account);
            changeMoneyFlow(memberInfo, account, App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }
    
    
    /**
     * 盛传的会员数据中存在套餐格式的数据,将其提取出啦,生成excle,以供下载
    * @author 高国藩
    * @date 2015年11月19日 下午5:04:58
    * @param sheet  表单数据
    * @param rowNums  满足条件的行数
    * @param storeId  门店
    * @throws Exception 抛出异常
     */
    public void executeExcleSc(Sheet sheet, List<Integer> rowNums, Integer storeId) throws Exception {
        List<MemberErrorSc> errorScs = new ArrayList<>();
        for (int i = 0; i < rowNums.size(); i++) {
            MemberErrorSc memberErrorSc = new MemberErrorSc();
            Row xssfRow = sheet.getRow(rowNums.get(i));
            for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                Cell cell = xssfRow.getCell(cellNum);
                String str = ExcleUtils.changeCellToString(cell);
                // 先封装一层会员数据
                if (cellNum == 0) {
                    memberErrorSc.setPhone(str);
                }
                if (cellNum == 1) {
                    memberErrorSc.setName(str);
                }
                if (cellNum == 2) {
                    memberErrorSc.setSex(str);
                }
                if (cellNum == 4) {
                    memberErrorSc.setCreateTime(str);
                }
                if (cellNum == 5) {
                    memberErrorSc.setLevelNum(str);
                }
                if (cellNum == 6) {
                    memberErrorSc.setLevelName(str);
                }
                if (cellNum == 7) {
                    memberErrorSc.setLevelType(str);
                }
                if (cellNum == 8) {
                    memberErrorSc.setDiscount(new BigDecimal(str));
                }
                if (cellNum == 9) {
                    memberErrorSc.setTotalAmount(new BigDecimal(str));
                }
                if (cellNum == 10) {
                    memberErrorSc.setTotalConsumeAmount(new BigDecimal(str));
                }
                if (cellNum == 11) {
                    memberErrorSc.setBalanceAmount(new BigDecimal(str));
                }
                if (cellNum == 12) {
                    memberErrorSc.setSendAmount(new BigDecimal(str));
                }
                if (cellNum == 13) {
                    memberErrorSc.setAeadTime(str);
                }
                if (cellNum == 14) {
                    memberErrorSc.setConsumeAmount(Integer.parseInt(str));
                }
                if (cellNum == 15) {
                    memberErrorSc.setBalanceIntegral(Integer.parseInt(str));
                }
                if (cellNum == 16) {
                    memberErrorSc.setLastConsumeTime(str);
                }
            }
            memberErrorSc.setStoreId(storeId);
            errorScs.add(memberErrorSc);
        }
        memberErrorScMapper.insertList(errorScs);
    }
    
    
    /**
     * 左右门店导入数据
    * @author 高国藩
    * @date 2015年10月19日 上午9:37:26
    * @param file 文件
    * @param storeId 门店
    * @param lastOperatorId 最后操作人员ID
    * @param response       输出流 
    * @return BaseDto
     * @throws Exception 
     */
    @Transactional
    public BaseDto importExcleZy(MultipartFile file, Integer storeId, Integer lastOperatorId, HttpServletResponse response)
                    throws Exception {
        List<TempTableZy> tableXies = new ArrayList<>();
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasStr.add(memberInfoDtos.get(i).getPhone());
        }
        InputStream is = file.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(is, "gb2312");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        log.info(line);
        Document doc = Jsoup.parse(line);
        Elements tr = doc.select("tr");
        // 会员等级和名称对应,方便取值
        Map<String, Integer> levelMap = packLevelMap(storeId);
        // saveMemberLevel(tr, levelMap, storeId);
        // levelMap = packLevelMap(storeId);
        // 将其中的异常数据保存如异常数据表中
        List<Integer> rowNums = new ArrayList<>();
        a: for (int i = 1; i < tr.size(); i++) {
            TempTableZy tableXy = new TempTableZy();
            Elements list = tr.get(i).getElementsByTag("td");
            for (int j = 0; j < list.size(); j++) {
                //姓名
                if (j == 1){
                    String n = list.get(j).text();
                    try {
                        String[] nameSex = n.split("【");
                        String memberName1 = nameSex[1].substring(0, nameSex[1].indexOf("】"));
                        String sex = nameSex[2].substring(0, nameSex[2].indexOf("】"));
                        tableXy.setName(memberName1);
                        tableXy.setSex(sex);
                    } 
                    catch (Exception e) {
                        rowNums.add(i);
                        continue a;
                    }
                }
                //会员卡
                if (j == 2){
                    String[] sf = list.get(j).text().split("【");
                    String df = sf[2].substring(0, sf[2].indexOf("】"));
                    String levleName;
                    if (df.indexOf("(")>0){
                        levleName = df.substring(0, df.indexOf("("));
                    }
                    else {
                        levleName = df; 
                    }
                    //Integer levelId = levelMap.get(levleName);
                    tableXy.setLevelName(levleName);
                }
                //卡金
                if (j == 3){
                    String[] sf1 = list.get(j).text().split(":");
                    String balanceAmount =  sf1[1].split("/")[0];
                    tableXy.setBalanceAmount(new BigDecimal(balanceAmount));
                }
                //电话
                if (j == 4){
                    String phone = list.get(j).text();
                    if (phone==null||phone.equals("")){
                        log.info("phone num is not exsit,this is not a memberInfo....");
                        rowNums.add(i);
                        continue a;
                    }
                    if (hasStr.contains(phone)) {
                        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, phone+"手机号码已存在");
                    } 
                    else {
                        Pattern p1 = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
                        Matcher m = p1.matcher(phone); 
                        if (m.matches()){
                            tableXy.setPhone(phone);
                        }
                        else {
                            rowNums.add(i);
                            log.info("phone num is not a true phone Num,this is not a memberInfo....");
                            continue a;
                        }
                    }
                }
            }
            tableXy.setStoreId(storeId);
            tableXies.add(tableXy);
        }
        // 将套餐类型的数据输出为新的excle
        if (rowNums.size()>0){
            errorMemberInsert(tr, rowNums, storeId);
        }
        log.info("成功导入会员:"+tableXies.size()+"会员异常数量"+rowNums.size());
        // 插入数据
        memberInfoMapper.dropTempTableZY();
        memberInfoMapper.updateTempTableZY();
        if (tableXies!=null&&tableXies.size()>0){
            tempTableXyMapper.insertList(tableXies);
        }
        
        List<MemberInfoDto> infoDtos = tempTableXyMapper.selectAllbyStoreId(storeId);
        Set<String> memberLevels = new HashSet<>();
        // 先插入会员等级
        for (int k = 0; k < infoDtos.size(); k++) {
            if (levelMap.get(infoDtos.get(k).getLevelName())==null){
                memberLevels.add(infoDtos.get(k).getLevelName());
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        for (String level : memberLevels) {
            MemberLevel level1 = new MemberLevel();
            level1.setLevelName(level);
            level1.setStoreId(hsqStoreId);
            level1.setIsDeleted(0);
            memberLevelMapper.insert(level1);
        }
        levelMap = packLevelMap(hsqStoreId);
        for (int k = 0; k < infoDtos.size(); k++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setName(infoDtos.get(k).getName());
            memberInfo.setSex(infoDtos.get(k).getSex());
            memberInfo.setPhone(infoDtos.get(k).getPhone());
            memberInfo.setIsDeleted(0);
            memberInfo.setStoreId(storeId);
            memberInfo.setCreateTime(DateUtil.getCurDate());
            memberInfo.setLevelId(levelMap.get(infoDtos.get(k).getLevelName()));
            memberInfo.setLastOperatorId(lastOperatorId);
            memberInfoMapper.insert(memberInfo);
            MemberAccount account = new MemberAccount();
            account.setAccountId(memberInfo.getMemberId());
            account.setBalanceAmount(infoDtos.get(k).getBalanceAmount());
            account.setTotalAmount(infoDtos.get(k).getBalanceAmount());
            memberAccountMapper.insert(account);
            changeMoneyFlow(memberInfo, account, App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
        }
        memberInfoMapper.dropTempTableZY();
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }
    
   
    /**
     * 左右数据中,会员与会员卡同时导入,将会员卡现行插入
    * @author 高国藩
    * @date 2015年11月21日 下午8:18:32
    * @param tr     数据tr
    * @param levelMap  已存在等级
    * @param storeId   门店
     */
    @SuppressWarnings("unused")
    private void saveMemberLevel(Elements tr, Map<String, Integer> levelMap, Integer storeId) {
        Set<String> levelNames = new HashSet<>();
        for (int i = 0; i < tr.size(); i++) {
            Elements list = tr.get(i).getElementsByTag("td");
            for (int j = 0; j < list.size(); j++) {
                //会员卡
                if (j == 2){
                    String[] sf = list.get(j).text().split("【");
                    String df = sf[2].substring(0, sf[2].indexOf("】"));
                    String levleName;
                    if (df.indexOf("(")>0){
                        levleName = df.substring(0, df.indexOf("("));
                    }
                    else {
                        levleName = df; 
                    }
                    if (levelMap.get(levleName)==null){
                        levelNames.add(levleName);
                    }
                }
            }
        }
        for (String str : levelNames) {
            MemberLevel memberLevel = new MemberLevel();
            memberLevel.setLevelName(str);
            memberLevel.setStoreId(storeId);
            log.info(JSONObject.fromObject(memberLevel).toString());
            memberLevelMapper.insert(memberLevel);
        }
    }

    /**
     * 会员等级和等级名称hash
    * @author 高国藩
    * @date 2015年11月18日 下午8:00:04
    * @param storeId 门店的所属归属门店ID
    * @return        map
     */
    public Map<String, Integer> packLevelMap(Integer storeId) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<MemberLevel> levels = memberLevelMapper.selectByStoreId(storeId);
        for (int i = 0; i < levels.size(); i++) {
            map.put(levels.get(i).getLevelName(), levels.get(i).getLevelId());
        }
        return map;
    }

    /**
     * 左右的会员数据中存在套餐格式的数据,将其提取出啦,插入error表中
    * @author 高国藩
    * @date 2015年11月19日 下午5:04:58
    * @param tr  表单数据
    * @param rowNums  满足条件的行数
    * @param storeId  门店
    * @throws Exception 抛出异常
     */
    public void errorMemberInsert(Elements tr, List<Integer> rowNums, Integer storeId) throws Exception {
        List<MemberErrorZy> errorZies = new ArrayList<>();
        a: for (int i = 0; i < rowNums.size(); i++) {
            Elements list = tr.get(rowNums.get(i)).getElementsByTag("td");
            MemberErrorZy errorZy = new MemberErrorZy();
            for (int j = 0; j < list.size(); j++) {
                //姓名和性别
                if (j == 1){
                    String n = list.get(j).text();
                    try {
                        String[] nameSex = n.split("【");
                        String memberName1 = nameSex[1].substring(0, nameSex[1].indexOf("】"));
                        String sex = nameSex[2].substring(0, nameSex[2].indexOf("】"));
                        errorZy.setName(memberName1);
                        errorZy.setSex(sex);
                    } 
                    catch (Exception e) {
                        continue a;
                    }
                }
                //会员卡
                if (j == 2){
                    String[] sf = list.get(j).text().split("【");
                    String df = sf[2].substring(0, sf[2].indexOf("】"));
                    String[] af = sf[1].split("】");
                    String levleName;
                    if (df.indexOf("(")>0){
                        levleName = df.substring(0, df.indexOf("("));
                    }
                    else {
                        levleName = df; 
                    }
                    errorZy.setLevelName(levleName);
                    errorZy.setLevelNum(af[0]);
                }
                //卡金
                if (j == 3){
                    String[] sf1 = list.get(j).text().split(":");
                    String balanceAmount =  sf1[1].split("/")[0];
                    errorZy.setBalanceAmount(new BigDecimal(balanceAmount));
                }
                //电话
                if (j == 4){
                    String phone = list.get(j).text();
                    errorZy.setPhone(phone);
                }
            }
            errorZy.setStoreId(storeId);
            errorZies.add(errorZy);
        }
        if (errorZies!=null&&errorZies.size()>0){
            memberErrorZyMapper.insertList(errorZies);
        }
    }


    /**
     * 修改门店设置中原服务商的名称
    * @author 高国藩
    * @date 2015年12月4日 下午8:32:09
    * @param storeId  门店
    * @param storeName  原服务商名称
     */
    public void updateStoreSet(Integer storeId, String storeName) {
        StoreSetting setting = storeSettingMapper.selectByPrimaryKey(storeId);
        if (setting==null){
            setting = new StoreSetting();
            setting.setStoreId(storeId);
            setting.setLastFacilitator(storeName);
            storeSettingMapper.insert(setting);
        }
        else {
            setting.setLastFacilitator(storeName);
            storeSettingMapper.updateByPrimaryKey(setting);
        }
    }


    /**
     * 云浩企汇通 会员导入,请注意,文件必须是一个.json结尾的文件
    * @author 高国藩
    * @date 2015年12月6日 下午4:53:17
    * @param file                  文件
    * @param storeId               门店
    * @param lastOperatorId        最后操作人
    * @param response              结果
    * @return                      状态
     * @throws IOException         异常处理
     */
    public BaseDto importExcleHt(MultipartFile file, Integer storeId,
            Integer lastOperatorId, HttpServletResponse response) throws IOException {
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        String fileName = file.getOriginalFilename();
        if (!fileName.endsWith(".json")){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "请保证文件.json格式");
        }
        else {
            List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
            // 已经存在的会员手机号码
            List<String> hasStr = new ArrayList<>();
            for (int i = 0; i < memberInfoDtos.size(); i++) {
                hasStr.add(memberInfoDtos.get(i).getPhone());
            }
            Map<String, Integer> levelMap = packLevelMap(storeId);
            Set<String> initLevel = new HashSet<>();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "utf-8"));
            JSONObject msg = JSONObject.fromObject(bufferedReader.readLine());
            JSONArray list = JSONArray.fromObject(msg.get("list"));
            //第一步循环将新的会员卡存进去
            for (int i = 0; i < list.size(); i++) {
                JSONObject memberInfo = JSONObject.fromObject(list.get(i));
                if (levelMap.get(memberInfo.get("levelName")) == null){
                    initLevel.add(memberInfo.get("levelName").toString());
                }
            }
            for (String levelName : initLevel) {
                MemberLevel level = new MemberLevel();
                level.setLevelName(levelName);
                level.setStoreId(hsqStoreId);
                level.setIsDeleted(0);
                memberLevelMapper.insert(level);
            }
            Map<String, String> sexMap = new HashMap<>();
            sexMap.put("1", "男");
            sexMap.put("2", "女");
            levelMap = packLevelMap(hsqStoreId);
            List<MemberInfo> memberInfos = new ArrayList<>();
            List<MemberAccount> accounts = new ArrayList<>();
            List<MemberErrorHt> errorHts = new ArrayList<>();
            a: for (int i = 0; i < list.size(); i++) {
                MemberInfo info = new MemberInfo();
                MemberAccount account = new MemberAccount();
                JSONObject memberInfo = JSONObject.fromObject(list.get(i));
                try {
                    String phone = memberInfo.get("mobilePhone").toString();
                    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
                    Matcher m = p.matcher(phone); 
                    if (m.matches()){
                        info.setPhone(phone);
                    }
                    else {
                        errorHts.add(errorMemberInsertHt(memberInfo, sexMap, storeId));
                        continue a;
                    }
                    if (hasStr.contains(phone)) {
                        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, phone+"手机号码已存在");
                    } 
                    info.setName(memberInfo.get("name").toString());
                    info.setSex(sexMap.get(memberInfo.get("sex").toString()));
                    info.setLevelId(levelMap.get(memberInfo.get("levelName").toString()));
                    info.setStoreId(storeId);
                    info.setIsDeleted(0);
                    info.setLastOperatorId(lastOperatorId);
                    info.setCreateTime(DateUtil.getCurDate());
                    
                    account.setBalanceAmount(new BigDecimal(memberInfo.get("balanceOfCash").toString()));
                    account.setTotalAmount(new BigDecimal(memberInfo.get("balanceOfCash").toString()));
                    
                    account.setBalanceGiftmoneyAmount(new BigDecimal(memberInfo.get("balanceOfBonus").toString()));
                    account.setTotalGiftmoneyAmount(new BigDecimal(memberInfo.get("balanceOfBonus").toString()));
                    
                    account.setBalanceIntegral(Integer.parseInt(memberInfo.get("balanceOfVouchers").toString()));
                    account.setTotalIntegral(Integer.parseInt(memberInfo.get("balanceOfVouchers").toString()));
                    
                    account.setLastConsumeTime(memberInfo.get("lastConsumeDate").toString());
                    account.setConsumeCount(Integer.parseInt(memberInfo.get("totalConsumedTimes").toString()));
                    account.setCreateTime(DateUtil.getCurDate());
                    account.setLastOperatorId(lastOperatorId);
                    memberInfos.add(info);
                    accounts.add(account);
                } 
                catch (Exception e) {
                    errorHts.add(errorMemberInsertHt(memberInfo, sexMap, storeId));
                    continue a;
                }
            }
            for (int i = 0; i < memberInfos.size(); i++) {
                memberInfoMapper.insert(memberInfos.get(i));
                MemberAccount account = accounts.get(i);
                account.setAccountId(memberInfos.get(i).getMemberId());
                memberAccountMapper.insert(account);
                //储值余额流水
                changeMoneyFlow(memberInfos.get(i), account, App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
                //积分余额流水
                changeIntegralFlow(memberInfos.get(i), account, App.Member.IMPORT_MONEY_DECS, 7, storeId, 0, lastOperatorId);
                //礼金流水
                changeGiftMoneyFlow(memberInfos.get(i), account, App.Member.IMPORT_MONEY_DECS, 7, lastOperatorId);
            }
            if (errorHts.size()>0){
                memberErrorHtMapper.insertList(errorHts);
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }
    
    /**
     * 处理错误会员导入-云浩企汇通
    * @author 高国藩
    * @date 2015年12月6日 下午7:28:06
    * @param memberInfo     json
    * @param sexMap         性别
    * @param storeId        门店
    * @return               错误数据类
     */
    public MemberErrorHt errorMemberInsertHt(JSONObject memberInfo, Map<String, String> sexMap, Integer storeId){
        MemberErrorHt info = new MemberErrorHt();
        try {
            info.setPhone(memberInfo.get("phone").toString());
        } 
        catch (Exception e) {
            info.setPhone("");
        }
        try {
            info.setName(memberInfo.get("name").toString());
        } 
        catch (Exception e) {
            info.setName("");
        }
        try {
            info.setLevelNum(memberInfo.get("cardNumber").toString());
        } 
        catch (Exception e) {
            info.setLevelNum("");
        }
        info.setSex(sexMap.get(memberInfo.get("sex").toString()));
        info.setLevelName(memberInfo.get("levelName").toString());
        info.setStoreId(storeId);
        info.setBalanceAmount(new BigDecimal(memberInfo.get("balanceOfCash").toString()));
        info.setBalanceGiftmoneyAmount(new BigDecimal(memberInfo.get("balanceOfBonus").toString()));
        info.setBalanceIntegral(new BigDecimal(memberInfo.get("balanceOfVouchers").toString()));
        try {
            info.setLastConsumeTime(memberInfo.get("lastConsumeDate").toString());
        } 
        catch (Exception e) {
            info.setLastConsumeTime("");
        }
        
        try {
            info.setConsumeCount(Integer.parseInt(memberInfo.get("totalConsumedTimes").toString()));
        } 
        catch (Exception e) {
            info.setConsumeCount(0);
        }
        return info;
    }


    /**
     * 进入会员错误数据统计页面 
    * @author 高国藩
    * @date 2015年12月7日 上午10:39:28
    * @param storeId    门店
    * @return           页面
     */
    public ModelAndView viewErrorMember(Integer storeId) {
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        ModelAndView view = new ModelAndView(View.MemberInfo.VEIW_ERROR_MEMBER);
        if ("盛传".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorSc> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorSc> results = memberErrorScMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "盛传");
            view.addObject("page", page);
            MemberErrorSc errorSc = memberErrorScMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorSc);
            List<ProjectInfo> projectInfos = projectInfoMapper.selectByStoreId(storeId);
            view.addObject("projectInfos", projectInfos);
            return view;
        } 
        else if ("左右".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorZy> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorZy> results = memberErrorZyMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "左右");
            view.addObject("page", page);
            MemberErrorZy errorZy = memberErrorZyMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorZy);
            return view;
        }
        else if ("云浩企汇通".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorHt> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorHt> results = memberErrorHtMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "云浩企汇通");
            view.addObject("page", page);
            MemberErrorHt errorHt = memberErrorHtMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorHt);
            return view;
        }
        else if ("博卡".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorBk> page = new Page<>();
            page.setPageNo(1);
            page.setPageSize(50);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            page.setParams(map);
            List<MemberErrorBk> results = memberErrorBkMapper.selectByPage(page);
            page.setResults(results);
            view.addObject("lastFacilitator", "博卡");
            view.addObject("page", page);
            MemberErrorBk errorBk = memberErrorBkMapper.selectStoreMemberAmount(storeId);
            view.addObject("balanceAmounts", errorBk);
            return view;
        }
        else {
            return view;
        }
    }


    /**
     * 分页查询异常会员数据
    * @author 高国藩
    * @date 2015年12月7日 下午2:30:08
    * @param storeId  门店
    * @param pageNo   页码
    * @param pageSize  液量
    * @param content   搜索条件
    * @return         result
     */
    public BaseDto viewErrorMember(Integer storeId, Integer pageNo,
            Integer pageSize, String content) {
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        BaseDto base = new BaseDto();
        if ("盛传".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorSc> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorSc> results = memberErrorScMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "盛传");
            msg.put("page", page);
            base.setMsg(msg);
            return base;
        } 
        else if ("左右".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorZy> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorZy> results = memberErrorZyMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "左右");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("云浩企汇通".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorHt> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorHt> results = memberErrorHtMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "云浩企汇通");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else if ("博卡".equals(storeSetting.getLastFacilitator())){
            Page<MemberErrorBk> page = new Page<>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            Map<String, Object> map = new HashMap<>();
            map.put("storeId", storeId);
            if (content!=null&&!content.equals("")){
                map.put("content", content);
            }
            page.setParams(map);
            List<MemberErrorBk> results = memberErrorBkMapper.selectByPage(page);
            page.setResults(results);
            Map<String, Object> msg = new HashMap<>();
            base.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            msg.put("lastFacilitator", "博卡");
            msg.put("page", page);
            base.setMsg(msg);
        }
        else {
            base.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
            base.setMsg("暂无您的导入数据");
            return base;
        }
        return base;
    }
    
    
    /**
     * 会员错误数据删除操作
    * @author 张进军
    * @date Dec 13, 2015 12:59:05 PM
    * @param type   服务商类型(1:盛传，2:左右，3:云浩)
    * @param id     错误数据标识
    * @param userId 当前操作人
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto deleteErrorMemberAction(Integer type, Integer id, Integer userId){
        switch (type) {
            case 1:
                MemberErrorSc errorSc = new MemberErrorSc();
                errorSc.setLastOperatorId(userId);
                errorSc.setId(id);
                errorSc.setIsDeleted(1);
                errorSc.setUpdateTime(DateUtil.getCurTime());
                memberErrorScMapper.updateByPrimaryKeySelective(errorSc);
                break;
            case 2:
                MemberErrorZy errorZy = new MemberErrorZy();
                errorZy.setLastOperatorId(userId);
                errorZy.setId(id);;
                errorZy.setIsDeleted(1);
                errorZy.setUpdateTime(DateUtil.getCurTime());
                memberErrorZyMapper.updateByPrimaryKeySelective(errorZy);
                break;            
            case 3:
                MemberErrorHt errorHt = new MemberErrorHt();
                errorHt.setId(id);
                errorHt.setLastOperatorId(userId);
                errorHt.setIsDeleted(1);
                errorHt.setUpdateTime(DateUtil.getCurTime());
                memberErrorHtMapper.updateByPrimaryKeySelective(errorHt);
                break;
            case 4:
                MemberErrorBk errorBk = new MemberErrorBk();
                errorBk.setId(id);
                errorBk.setLastOperatorId(userId);
                errorBk.setIsDeleted(1);
                errorBk.setUpdateTime(DateUtil.getCurTime());
                memberErrorBkMapper.updateByPrimaryKeySelective(errorBk);
                break;
            default:
                break;
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }


    /**
     * 查询总店下所有分店的信息
    * @author 高国藩
    * @date 2015年12月12日 下午3:35:15
    * @param storeId   总店id
    * @return          总店查询会员的页面
     */
    public ModelAndView viewBaseMember(Integer storeId) {
        ModelAndView view = new ModelAndView(View.MemberInfo.BASE_MEMBER_VIEW);
        List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(storeId);
        Page<MemberInfoDto> page = new Page<MemberInfoDto>();
        page.setPageNo(1);
        page.setPageSize(50);
        Map<String, Object> map = new HashMap<String, Object>();
        if (storeList!=null&&storeList.size()>0){
            map.put("storeList", storeList);
        }
        else {
            map.put("storeId", storeId);
        }
        page.setParams(map);
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberInfosByPage(page);
        page.setResults(memberInfoDtos);
        List<MemberLevel> levels = memberLevelMapper.selectByStoreId(storeId);
        view.addObject("page", page);
        view.addObject("levels", levels);
        view.addObject("storeList", storeList);
        return view;
    }


    /**
     * 导入博卡会员数据
    * @author 高国藩
    * @date 2015年12月16日 下午7:35:56
    * @param multipartFile     消费余额统计表
    * @param multipartFile2    会员信息统计表
    * @param storeId           门店
    * @param lastOperatorId    最后操作人
    * @param response          response
    * @return                  状态指示
    * @throws IOException      文件流
    * @throws Exception        异常
     */
    public BaseDto importExcleBk(MultipartFile multipartFile,
            MultipartFile multipartFile2, Integer storeId,
            Integer lastOperatorId, HttpServletResponse response) throws IOException, Exception {
        Map<String, Integer> levelMap = getLevel(multipartFile.getInputStream(), storeId);
        return readInfo(multipartFile2.getInputStream(), levelMap, storeId, lastOperatorId);
    }
    
    /**
     * 对传入的博卡数据进行会员等级的封装
    * @author 高国藩
    * @date 2015年12月16日 下午7:34:43
    * @param is             文件流
    * @param storeId        门店
    * @return               会员等级hash
    * @throws Exception     异常
     */
    public Map<String, Integer> getLevel(InputStream is, Integer storeId) throws Exception {
        Set<String> set = new HashSet<String>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Map<String, String> levelMap = new HashMap<String, String>();
        // 循环工作表Sheet
        @SuppressWarnings("unused")
        StringBuffer sb = new StringBuffer();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null || hssfRow.getLastCellNum() != 16) {
                    continue;
                }
                //检查首列内容
                String firstContent = ExcleUtils.changeCellToString(hssfRow.getCell(0));
                if (StringUtils.isEmpty(firstContent) || firstContent.startsWith("卡号")) {
                    continue;
                }
                firstContent = firstContent.replace("(", ",").replace(")", "");
                String[] levelArr = firstContent.split(",");
                String card = levelArr[0];
                String level = levelArr[1].replaceAll("()", "");
                levelMap.put(card, level);
                set.add(level);
            }
        }
        int hsqStoreId = storeInfoMapper.selectMainIdByStoreId(storeId);
        Map<String, Integer> hasLevelMap = packLevelMap(hsqStoreId);
        for (String levelName : set) {
            if (hasLevelMap.get(levelName)==null){
                MemberLevel level = new MemberLevel();
                level.setLevelName(levelName);
                level.setStoreId(hsqStoreId);
                memberLevelMapper.insert(level);
            }
        }
        List<MemberLevel> levels = memberLevelMapper.selectByStoreId(hsqStoreId);
        Map<String, Integer> map = new HashMap<>();
        //循环将会员卡和等级的id绑定好
        for (String key : levelMap.keySet()) {
            for (@SuppressWarnings("rawtypes")
                Iterator iterator = levels.iterator(); iterator.hasNext();) {
                MemberLevel memberLevel = (MemberLevel) iterator.next();
                if (levelMap.get(key).equals(memberLevel.getLevelName())){
                    map.put(key, memberLevel.getLevelId());
                }
            }
        }  
        return map;
    }
    
    /**
     * 对博卡的数据进行组装存入数据库中
    * @author 高国藩
    * @date 2015年12月16日 下午7:33:45
    * @param in             文件流
    * @param levelMap       会员等级的hash
    * @param storeId        门店
    * @param lastOperatorId 最后操作人
    * @return               状态码
    * @throws Exception     异常
     */
    public BaseDto readInfo(InputStream in, Map<String, Integer> levelMap, Integer storeId, Integer lastOperatorId) throws Exception {
        List<MemberInfo> memberInfos = new ArrayList<>();
        List<MemberAccount> accounts = new ArrayList<>();
        List<MemberErrorBk> errorBks = new ArrayList<>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
        // 循环工作表Sheet
        StringBuffer sb = new StringBuffer();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null || hssfRow.getLastCellNum() != 16) {
                    continue;
                }
                
                //检查首行内容
                String firstContent = ExcleUtils.changeCellToString(hssfRow.getCell(0));
                if (StringUtils.isEmpty(firstContent) || firstContent.startsWith("会员编号") || firstContent.startsWith("储值账户")) {
                    continue;
                }
                String card = ExcleUtils.changeCellToString(hssfRow.getCell(0));
                String name = ExcleUtils.changeCellToString(hssfRow.getCell(1));
                String sex = ExcleUtils.changeCellToString(hssfRow.getCell(2));
                String phone = ExcleUtils.changeCellToString(hssfRow.getCell(4));
                String totalMoney = ExcleUtils.changeCellToString(hssfRow.getCell(10));
                String balanceAmount = ExcleUtils.changeCellToString(hssfRow.getCell(11));
                String cousumerAmount = ExcleUtils.changeCellToString(hssfRow.getCell(12));
                String consumerCount = ExcleUtils.changeCellToString(hssfRow.getCell(13));
                String avgConsumerPrice = ExcleUtils.changeCellToString(hssfRow.getCell(14));
                String lastConsumerDate = ExcleUtils.changeCellToString(hssfRow.getCell(15));
                sb.append(card + "\t" + name + "\t" + sex + "\t" + phone + "\t" + totalMoney + "\t" + balanceAmount + "\t" + cousumerAmount
                        + "\t" + consumerCount + "\t" + avgConsumerPrice + "\t" + lastConsumerDate + "\r\n");
                MemberInfo info = new MemberInfo();
                info.setLevelId(levelMap.get(card));
                info.setName(name);
                info.setSex(sex);
                info.setPhone(phone);
                info.setStoreId(storeId);
                MemberAccount account = new MemberAccount();
                account.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(totalMoney)));
                account.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
                account.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(cousumerAmount)));
                account.setConsumeCount(Integer.parseInt(ExcleUtils.changeValue(consumerCount)));
                account.setAvgConsumeAmount(new BigDecimal(ExcleUtils.changeValue(avgConsumerPrice)));
                account.setLastConsumeTime(lastConsumerDate);
                if (name==null||"".equals(name)){
                    continue;
                }
                if (!ExcleUtils.isPhone(ExcleUtils.changeCellToString(hssfRow.getCell(4)))||info.getLevelId()==null){
                    MemberErrorBk errorBk = new MemberErrorBk();
                    errorBk.setLevelNum(card);
                    errorBk.setName(name);
                    errorBk.setSex(sex);
                    errorBk.setPhone(phone);
                    errorBk.setTotalAmount(new BigDecimal(ExcleUtils.changeValue(totalMoney)));
                    errorBk.setBalanceAmount(new BigDecimal(ExcleUtils.changeValue(balanceAmount)));
                    errorBk.setTotalConsumeAmount(new BigDecimal(ExcleUtils.changeValue(cousumerAmount)));
                    errorBk.setConsumeCount(Integer.parseInt(ExcleUtils.changeValue(consumerCount)));
                    errorBk.setAvgConsumeAmount(new BigDecimal(ExcleUtils.changeValue(avgConsumerPrice)));
                    errorBk.setLastConsumeTime(lastConsumerDate);
                    errorBk.setStoreId(storeId);
                    errorBk.setIsDeleted(0);
                    errorBks.add(errorBk);
                    continue;
                }
                memberInfos.add(info);
                accounts.add(account);
            }
            for (int i = 0; i < memberInfos.size(); i++) {
                memberInfoMapper.insert(memberInfos.get(i));
                MemberAccount account = accounts.get(i);
                account.setAccountId(memberInfos.get(i).getMemberId());
                memberAccountMapper.insert(account);
                //储值余额流水
                changeMoneyFlow(memberInfos.get(i), account, App.Member.IMPORT_MONEY_DECS, 7, storeId, lastOperatorId);
            }
            if (errorBks.size()>0){
                for (int i = 0; i < errorBks.size(); i++) {
                    if (errorBks.get(i) == null){
                        log.info("第:"+i+"个是空");
                    }
                }
                memberErrorBkMapper.insertList(errorBks);
            }
        } 
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }


    /**
     * 导入异常会员数据
    * @author 高国藩
    * @date 2015年12月17日 下午5:22:55
    * @param storeId       门店
    * @return              导出
    * @throws IOException  异常
     */
    public ResponseEntity<byte[]> downloadErrorMember(Integer storeId) throws IOException {
        StoreSetting setting = storeSettingMapper.selectByPrimaryKey(storeId);
        if (setting.getLastFacilitator()==null){
            return null;
        }
        if (setting.getLastFacilitator().equals("盛传")){
            return downloadErrorMemberSc(storeId);
        }
        else if (setting.getLastFacilitator().equals("左右")){
            return downloadErrorMemberZy(storeId);
        }
        else if (setting.getLastFacilitator().equals("云浩企汇通")){
            return downloadErrorMemberHt(storeId);
        }
        else if (setting.getLastFacilitator().equals("博卡")){
            return downloadErrorMemberBk(storeId);
        }
        return null;
        
    }
    
    /**
     * 博卡异常会员数据导出
    * @author 高国藩
    * @date 2015年12月17日 下午5:23:31
    * @param storeId         门店
    * @return                导出
    * @throws IOException    异常
     */
    public ResponseEntity<byte[]> downloadErrorMemberBk(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        HSSFCell cell7 = row.createCell(7);
        HSSFCell cell8 = row.createCell(8);
        HSSFCell cell9 = row.createCell(8);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("卡号");
        cell4.setCellValue("储值总额");
        cell5.setCellValue("储值余额");
        cell6.setCellValue("累计消费总额");
        cell7.setCellValue("累计消费次数");
        cell8.setCellValue("平均消费");
        cell9.setCellValue("最后消费日期");
        List<MemberErrorBk> bks = memberErrorBkMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            HSSFCell cellValue6 = rowValue.createCell(6);
            HSSFCell cellValue7 = rowValue.createCell(7);
            HSSFCell cellValue8 = rowValue.createCell(8);
            HSSFCell cellValue9 = rowValue.createCell(8);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getLevelNum());
            cellValue4.setCellValue(bks.get(i).getTotalAmount().toString());
            cellValue5.setCellValue(bks.get(i).getBalanceAmount().toString());
            cellValue6.setCellValue(bks.get(i).getTotalConsumeAmount().toString());
            cellValue7.setCellValue(bks.get(i).getConsumeCount().toString());
            cellValue8.setCellValue(bks.get(i).getAvgConsumeAmount().toString());
            cellValue9.setCellValue(bks.get(i).getLastConsumeTime());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }

    /**
     * 企汇通异常会员数据导出
    * @author 高国藩
    * @date 2015年12月17日 下午5:23:31
    * @param storeId         门店
    * @return                导出
    * @throws IOException    异常
     */
    public ResponseEntity<byte[]> downloadErrorMemberHt(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        HSSFCell cell7 = row.createCell(7);
        HSSFCell cell8 = row.createCell(8);
        HSSFCell cell9 = row.createCell(8);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("卡名");
        cell4.setCellValue("卡号");
        cell5.setCellValue("储值余额");
        cell6.setCellValue("礼金储值余额 ");
        cell7.setCellValue("累计消费次数");
        cell8.setCellValue("积分余额");
        cell9.setCellValue("最后消费日期");
        List<MemberErrorHt> bks = memberErrorHtMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            HSSFCell cellValue6 = rowValue.createCell(6);
            HSSFCell cellValue7 = rowValue.createCell(7);
            HSSFCell cellValue8 = rowValue.createCell(8);
            HSSFCell cellValue9 = rowValue.createCell(8);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getLevelName());
            cellValue4.setCellValue(bks.get(i).getLevelNum());
            cellValue5.setCellValue(bks.get(i).getBalanceAmount().toString());
            cellValue6.setCellValue(bks.get(i).getBalanceGiftmoneyAmount().toString());
            cellValue7.setCellValue(bks.get(i).getConsumeCount().toString());
            cellValue8.setCellValue(bks.get(i).getBalanceIntegral().toString());
            cellValue9.setCellValue(bks.get(i).getLastConsumeTime());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }

    /**
     * 左右异常会员数据导出
    * @author 高国藩
    * @date 2015年12月17日 下午5:23:31
    * @param storeId         门店
    * @return                导出
    * @throws IOException    异常
     */
    public ResponseEntity<byte[]> downloadErrorMemberZy(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("卡名");
        cell4.setCellValue("卡号");
        cell5.setCellValue("卡内总余额");
        List<MemberErrorZy> bks = memberErrorZyMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getLevelName());
            cellValue4.setCellValue(bks.get(i).getLevelNum());
            cellValue5.setCellValue(bks.get(i).getBalanceAmount().toString());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }

    /**
     * 盛传异常会员数据导出
    * @author 高国藩
    * @date 2015年12月17日 下午5:23:31
    * @param storeId         门店
    * @return                导出
    * @throws IOException    异常
     */
    public ResponseEntity<byte[]> downloadErrorMemberSc(Integer storeId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet("异常会员数据");  
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        HSSFCell cell7 = row.createCell(7);
        HSSFCell cell8 = row.createCell(8);
        HSSFCell cell9 = row.createCell(9);
        HSSFCell cell10 = row.createCell(10);
        HSSFCell cell11 = row.createCell(11);
        HSSFCell cell12 = row.createCell(12);
        HSSFCell cell13 = row.createCell(13);
        HSSFCell cell14 = row.createCell(14);
        HSSFCell cell15 = row.createCell(15);
        cell0.setCellValue("姓名");
        cell1.setCellValue("性别");
        cell2.setCellValue("电话");
        cell3.setCellValue("创建时间");
        cell4.setCellValue("卡号");
        cell5.setCellValue("卡名");
        cell6.setCellValue("卡类型");
        cell7.setCellValue("折扣");
        cell8.setCellValue("储值总额");
        cell9.setCellValue("消费总额");
        cell10.setCellValue("卡内总余额");
        cell11.setCellValue("赠送总余额");
        cell12.setCellValue("失效日期");
        cell13.setCellValue("消费次数");
        cell14.setCellValue("当前积分");
        cell15.setCellValue("最后消费日");
        List<MemberErrorSc> bks = memberErrorScMapper.selectAll(storeId);
        for (int i = 0; i < bks.size(); i++) {
            HSSFRow rowValue = sheet.createRow(i+1);
            HSSFCell cellValue0 = rowValue.createCell(0);
            HSSFCell cellValue1 = rowValue.createCell(1);
            HSSFCell cellValue2 = rowValue.createCell(2);
            HSSFCell cellValue3 = rowValue.createCell(3);
            HSSFCell cellValue4 = rowValue.createCell(4);
            HSSFCell cellValue5 = rowValue.createCell(5);
            HSSFCell cellValue6 = rowValue.createCell(6);
            HSSFCell cellValue7 = rowValue.createCell(7);
            HSSFCell cellValue8 = rowValue.createCell(8);
            HSSFCell cellValue9 = rowValue.createCell(9);
            HSSFCell cellValue10 = rowValue.createCell(10);
            HSSFCell cellValue11 = rowValue.createCell(11);
            HSSFCell cellValue12 = rowValue.createCell(12);
            HSSFCell cellValue13 = rowValue.createCell(13);
            HSSFCell cellValue14 = rowValue.createCell(14);
            HSSFCell cellValue15 = rowValue.createCell(15);
            cellValue0.setCellValue(bks.get(i).getName());
            cellValue1.setCellValue(bks.get(i).getSex());
            cellValue2.setCellValue(bks.get(i).getPhone());
            cellValue3.setCellValue(bks.get(i).getCreateTime());
            cellValue4.setCellValue(bks.get(i).getLevelNum());
            cellValue5.setCellValue(bks.get(i).getLevelName());
            cellValue6.setCellValue(bks.get(i).getLevelType());
            cellValue7.setCellValue(bks.get(i).getDiscount().toString());
            cellValue8.setCellValue(bks.get(i).getTotalAmount().toString());
            cellValue9.setCellValue(bks.get(i).getTotalConsumeAmount().toString());
            cellValue10.setCellValue(bks.get(i).getBalanceAmount().toString());
            cellValue11.setCellValue(bks.get(i).getSendAmount().toString());
            cellValue12.setCellValue(bks.get(i).getAeadTime());
            cellValue13.setCellValue(bks.get(i).getConsumeAmount().toString());
            cellValue14.setCellValue(bks.get(i).getBalanceIntegral().toString());
            cellValue15.setCellValue(bks.get(i).getLastConsumeTime());
        }
        workbook.write(byteArrayOutputStream);
        byte [] body = null;  
        body = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData("attachment", "dict.xls");  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);
    }
    
    /**
     * 更新储值流水表
    * @author 高国藩
    * @date 2015年12月17日 下午8:23:28
    * @param info                   会员信息
    * @param account                会员明细信息
    * @param desc                   流水业务描述
    * @param businessType           流水类型(7:导入,8:迁移)
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作人员
     */
    public void changeMoneyFlow(MemberInfo info, MemberAccount account, String desc, Integer businessType, Integer storeId 
            , Integer lastOperatorId){
        MoneyFlow moneyFlow = new MoneyFlow();
        moneyFlow.setAccountId(info.getMemberId());
        moneyFlow.setBalanceAmount(new BigDecimal(0));
        moneyFlow.setBusinessType(businessType);
        moneyFlow.setFlowType(2);
        moneyFlow.setFlowAmount(account.getBalanceAmount());
        moneyFlow.setStoreId(storeId);
        moneyFlow.setIsDeleted(0);
        moneyFlow.setLastOperatorId(lastOperatorId);
        moneyFlow.setFlowTime(DateUtil.getCurDate());
        moneyFlow.setBusinessDesc(desc);
        moneyFlowMapper.insert(moneyFlow);
    }
    /**
     * 更新积分流水表
    * @author 高国藩
    * @date 2015年12月17日 下午8:23:28
    * @param info                   会员信息
    * @param account                会员明细信息
    * @param desc                   流水业务描述
    * @param businessType           流水类型(7:导入,8:迁移)
    * @param storeId                门店信息
    * @param balanceAmount          当前余额(0:导入,迁移要查询)
    * @param lastOperatorId         最后操作人员
     */
    public void changeIntegralFlow(MemberInfo info, MemberAccount account, String desc, Integer businessType, Integer storeId 
            , Integer balanceAmount, Integer lastOperatorId){
        IntegralFlow integralFlow = new IntegralFlow();
        integralFlow.setAccountId(info.getMemberId());
        integralFlow.setFlowAmount(account.getBalanceIntegral());
        integralFlow.setBalanceAmount(balanceAmount);
        integralFlow.setBusinessType(businessType.toString());
        integralFlow.setFlowTime(DateUtil.getCurDate());
        integralFlow.setBusinessDesc(desc);
        integralFlow.setIsDeleted(0);
        integralFlowMapper.insert(integralFlow);
    }
    
    /**
     * 礼金流水变动
    * @author 高国藩
    * @date 2015年12月17日 下午9:22:19
    * @param info              会员信息
    * @param account           会员数据信息
    * @param desc              流水业务描述
    * @param businessType      流水类型(7:导入,8:迁移)
    * @param lastOperatorId    最后操作人员
     */
    public void changeGiftMoneyFlow(MemberInfo info, MemberAccount account, String desc, Integer businessType, Integer lastOperatorId){
        GiftmoneyDetail giftmoneyDetail = new GiftmoneyDetail();
        giftmoneyDetail.setAccountId(info.getMemberId());
        giftmoneyDetail.setTotalAmount(account.getTotalGiftmoneyAmount());
        giftmoneyDetail.setNowMoney(account.getBalanceGiftmoneyAmount());
        giftmoneyDetail.setResidueNowMoney(new BigDecimal(0));
        giftmoneyDetail.setPartNumber(0);
        giftmoneyDetail.setStartDate(DateUtil.getCurDate());
        giftmoneyDetail.setStartDate("永久");
        giftmoneyDetail.setCreateTime(DateUtil.getCurDate());
        giftmoneyDetail.setIsDeleted(0);
        giftmoneyDetail.setIsPresent(1);
        giftmoneyDetail.setLastOperatorId(lastOperatorId);
        giftmoneyDetailMapper.insertSelective(giftmoneyDetail);
        GiftmoneyFlow giftmoneyFlow = new GiftmoneyFlow();
        giftmoneyFlow.setAccountId(account.getAccountId());
        giftmoneyFlow.setFlowType(2);
        giftmoneyFlow.setFlowAmount(account.getBalanceGiftmoneyAmount());
        giftmoneyFlow.setBusinessType(businessType.toString());
        giftmoneyFlow.setBusinessDesc(desc);
        giftmoneyFlow.setFlowTime(DateUtil.getCurDate());
        giftmoneyFlow.setIsDeleted(0);
        giftmoneyFlowMapper.insertSelective(giftmoneyFlow);
    }


    /**
     * 修改会员资料
    * @author 高国藩
    * @date 2015年12月18日 下午3:46:00
    * @param memberInfo    会员信息
    * @param storeId       门店信息
    * @return              修改状态
     */
    public BaseDto updateMemberInfo(MemberInfo memberInfo, Integer storeId) {
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberByStoreId(storeId);
        // 已经存在的会员手机号码
        List<String> hasPhones = new ArrayList<>();
        Map<String, Integer> memberPhone =  new HashMap<>();
        for (int i = 0; i < memberInfoDtos.size(); i++) {
            hasPhones.add(memberInfoDtos.get(i).getPhone());
            memberPhone.put(memberInfoDtos.get(i).getPhone(), memberInfoDtos.get(i).getMemberId());
        }
        if (hasPhones.contains(memberInfo.getPhone())&&(memberInfo.getMemberId().intValue()!=memberPhone.get(memberInfo.getPhone()).intValue())){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该手机号已存在");
        }
        int ok = memberInfoMapper.updateByPrimaryKey(memberInfo);
        switch(ok){
            case 1: return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "更新成功");
            case 0: return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "更新失败");
            default:
                break;
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "更新失败");
    }

    /**
     * 会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @param type                   类型(1:盛传 2:左右 3:企汇通 4:博卡)
    * @return                       状态提示
     */
    public BaseDto balandMemberMove(Integer storeId, Integer lastOperatorId,
            Integer type, Integer id, Integer memberId) {
        switch(type){
            case 1: return balandMemberMoveSc(storeId, lastOperatorId, id, memberId);
            case 2: return balandMemberMoveZy(storeId, lastOperatorId, id, memberId);
            case 3: return balandMemberMoveHt(storeId, lastOperatorId, id, memberId);
            case 4: return balandMemberMoveBk(storeId, lastOperatorId, id, memberId);
            default:
                break;
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "迁移失败,请稍后重试");
    }
    
    /**
     * 盛传会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @return                       状态提示
     */
    public BaseDto balandMemberMoveSc(Integer storeId, Integer lastOperatorId, Integer id, Integer memberId){
        MemberErrorSc errorSc = memberErrorScMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorSc.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccount.setBalanceIntegral(memberAccount.getBalanceIntegral()+errorSc.getBalanceIntegral());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo, memberAccount, App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        changeIntegralFlow(memberInfo, memberAccount, App.Member.MOVE_MONEY_DECS, 8, storeId, 0, lastOperatorId);
        errorSc.setUpdateTime(DateUtil.getCurDate());
        errorSc.setLastOperatorId(lastOperatorId);
        errorSc.setIsDeleted(1);
        memberErrorScMapper.updateByPrimaryKeySelective(errorSc);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }
    
    /**
     * 左右会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @return                       状态提示
     */
    public BaseDto balandMemberMoveZy(Integer storeId, Integer lastOperatorId, Integer id, Integer memberId){
        MemberErrorZy errorZy = memberErrorZyMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorZy.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo, memberAccount, App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        errorZy.setUpdateTime(DateUtil.getCurDate());
        errorZy.setLastOperatorId(lastOperatorId);
        errorZy.setIsDeleted(1);
        memberErrorZyMapper.updateByPrimaryKeySelective(errorZy);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }
    
    /**
     * 企汇通会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @return                       状态提示
     */
    public BaseDto balandMemberMoveHt(Integer storeId, Integer lastOperatorId, Integer id, Integer memberId){
        MemberErrorHt errorHt = memberErrorHtMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorHt.getBalanceAmount()));
        memberAccount.setBalanceIntegral(memberAccount.getBalanceIntegral()+errorHt.getBalanceIntegral().intValue());
        memberAccount.setBalanceGiftmoneyAmount(memberAccount.getBalanceGiftmoneyAmount().add(errorHt.getBalanceGiftmoneyAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo, memberAccount, App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        changeIntegralFlow(memberInfo, memberAccount, App.Member.MOVE_MONEY_DECS, 8, storeId, 0, lastOperatorId);
        changeGiftMoneyFlow(memberInfo, memberAccount, App.Member.IMPORT_MONEY_DECS, 8, lastOperatorId);
        errorHt.setUpdateTime(DateUtil.getCurDate());
        errorHt.setLastOperatorId(lastOperatorId);
        errorHt.setIsDeleted(1);
        memberErrorHtMapper.updateByPrimaryKeySelective(errorHt);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }
    
    /**
     * 博卡会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param storeId                门店信息
    * @param lastOperatorId         最后操作
    * @param id                     异常id
    * @param memberId               会员id
    * @return                       状态提示
     */
    public BaseDto balandMemberMoveBk(Integer storeId, Integer lastOperatorId, Integer id, Integer memberId){
        MemberErrorBk errorBk = memberErrorBkMapper.selectByPrimaryKey(id);
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        memberAccount.setBalanceAmount(memberAccount.getBalanceAmount().add(errorBk.getBalanceAmount()));
        memberAccount.setLastOperatorId(lastOperatorId);
        memberAccount.setUpdateTime(DateUtil.getCurDate());
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        changeMoneyFlow(memberInfo, memberAccount, App.Member.MOVE_MONEY_DECS, 8, storeId, lastOperatorId);
        errorBk.setUpdateTime(DateUtil.getCurDate());
        errorBk.setLastOperatorId(lastOperatorId);
        errorBk.setIsDeleted(1);
        memberErrorBkMapper.updateByPrimaryKeySelective(errorBk);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }


    /**
     *  异常会员套餐迁移
    * @author 高国藩
    * @date 2015年12月19日 下午1:47:11
    * @param storeId          门店信息
    * @param lastOperatorId   最后操作人
    * @param type             类型(1:盛传 2:左右 3:企汇通 4:博卡)
    * @param id               异常会员id
    * @param memberId         迁移会员id
    * @param comboName        套餐名称
    * @param overdueTime      过期时间
    * @param projectId        项目id
    * @param projectCount     项目数量
    * @return                 状态
     */
    public BaseDto comboMemberMove(Integer storeId, Integer lastOperatorId,
            Integer type, Integer id, Integer memberId, String comboName,
            String overdueTime, Integer projectId, Integer projectCount) {
        switch(type){
            case 1: return comboMemberMoveSc(storeId, lastOperatorId, type, id, memberId, comboName, overdueTime, projectId, projectCount);
            default:
                break;
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "迁移失败,请稍后重试");
    }


    /**
     *  盛传异常会员套餐迁移
    * @author 高国藩
    * @date 2015年12月19日 下午1:47:11
    * @param storeId          门店信息
    * @param lastOperatorId   最后操作人
    * @param type             类型(1:盛传 2:左右 3:企汇通 4:博卡)
    * @param id               异常会员id
    * @param memberId         迁移会员id
    * @param comboName        套餐名称
    * @param overdueTime      过期时间
    * @param projectId        项目id
    * @param projectCount     项目数量
    * @return                 状态
     */
    public BaseDto comboMemberMoveSc(Integer storeId, Integer lastOperatorId,
            Integer type, Integer id, Integer memberId, String comboName,
            String overdueTime, Integer projectId, Integer projectCount) {
        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
        
        MemberComboRecord memberComboRecord = new MemberComboRecord();
        memberComboRecord.setMemberId(memberId);
        memberComboRecord.setStoreId(storeId);
        memberComboRecord.setComboName(comboName);
        memberComboRecord.setProjectAmount(projectInfo.getProjectPrice());
        memberComboRecord.setRemainingCount(projectCount);
        memberComboRecord.setOverdueTime(overdueTime);
        memberComboRecord.setIsDeleted(0);
        memberComboRecord.setLastOperatorId(lastOperatorId);
        memberComboRecordMapper.insert(memberComboRecord);
        
        
        MemberComboProject memberComboProject = new MemberComboProject();
        memberComboProject.setProjectId(projectId);
        memberComboProject.setRecordId(memberComboRecord.getRecordId());
        memberComboProject.setComboId(0);
        memberComboProject.setProjectName(projectInfo.getProjectName());
        memberComboProject.setProjectPrice(projectInfo.getProjectPrice());
        memberComboProject.setProjectImage(projectInfo.getProjectImage());
        memberComboProject.setProjectCount(projectCount);
        memberComboProject.setRemainingCount(projectCount);
        memberComboProject.setCreateTime(DateUtil.getCurDate());
        memberComboProject.setIsDeleted(0);
        memberComboProject.setLastOperatorId(lastOperatorId);
        memberComboProjectMapper.insert(memberComboProject);
        
        
        
        MemberErrorSc errorSc = memberErrorScMapper.selectByPrimaryKey(id);
        errorSc.setUpdateTime(DateUtil.getCurDate());
        errorSc.setLastOperatorId(lastOperatorId);
        errorSc.setIsDeleted(1);
        memberErrorScMapper.updateByPrimaryKeySelective(errorSc);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "迁移成功");
    }
}
    
