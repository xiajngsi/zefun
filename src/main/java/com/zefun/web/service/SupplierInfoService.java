package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.common.consts.App;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.SupplierInfo;
import com.zefun.web.mapper.SupplierInfoMapper;

/**
 * 供应商信息
* @author 洪秋霞
* @date 2015年8月11日 下午4:56:54
 */
@Service
public class SupplierInfoService {

    /** 供应商信息 */
    @Autowired private SupplierInfoMapper supplierInfoMapper;
    
    /**
     * 分页查询供应商 change
    * @author 洪秋霞
    * @date 2015年8月11日 下午5:30:33
    * @param supplierInfo 供应商信息
    * @param pageNo 页码
    * @param pageSize 每页显示数
    * @return BaseDto
     */
    public BaseDto listAction(SupplierInfo supplierInfo, int pageNo, int pageSize){
        Page<SupplierInfo> page = querySupplierInfoPage(supplierInfo, pageNo, pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    /**
     * 分页查询供应商信息
    * @author 洪秋霞
    * @date 2015年8月11日 下午5:21:16
    * @param supplierInfo 供应商信息
    * @param pageNo 页码
    * @param pageSize 每页显示数
    * @return Page<SupplierInfo>
     */
    public Page<SupplierInfo> querySupplierInfoPage(SupplierInfo supplierInfo, int pageNo, int pageSize){
        Page<SupplierInfo> page = new Page<SupplierInfo>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("supplierInfo", supplierInfo);
        page.setParams(params);
        List<SupplierInfo> supplierInfoList = supplierInfoMapper.selectByPropertyPage(page);
        page.setResults(supplierInfoList);
        return page;
    }
    
    /**
     * 根据门店id查询供应商列表
    * @author 洪秋霞
    * @date 2015年9月6日 上午10:29:23
    * @param storeId 门店id
    * @return List<SupplierInfo>
     */
    public List<SupplierInfo> querySupplierInfoByStoreId(Integer storeId){
        return supplierInfoMapper.selectByStoreId(storeId);
    }
    
    /**
     * 根据id查询供应商信息
    * @author 洪秋霞
    * @date 2015年8月11日 下午5:43:22
    * @param supplierId 供应商id
    * @return SupplierInfo
     */
    public SupplierInfo querySupplierInfoById(Integer supplierId){
        return supplierInfoMapper.selectByPrimaryKey(supplierId);
    }
    
    /**
     * 保存供应商信息
    * @author 洪秋霞
    * @date 2015年8月11日 下午5:13:21
    * @param supplierInfo 供应商信息
     */
    public void saveSupplierInfo(SupplierInfo supplierInfo){
        supplierInfoMapper.insertSelective(supplierInfo);
    }
    
    /**
     * 编辑供应商信息
    * @author 洪秋霞
    * @date 2015年8月11日 下午5:14:16
    * @param supplierInfo 供应商信息
     */
    public void editSupplierInfo(SupplierInfo supplierInfo){
        supplierInfoMapper.updateByPrimaryKeySelective(supplierInfo);
    }
    
    /**
     * 删除供应商信息
    * @author 洪秋霞
    * @date 2015年8月12日 下午1:56:33
    * @param supplierId 供应商id
     */
    public void deleteSupplierInfo(Integer supplierId){
        SupplierInfo supplierInfo = new SupplierInfo();
        supplierInfo.setSupplierId(supplierId);
        supplierInfo.setIsDeleted(1);
        supplierInfoMapper.updateByPrimaryKeySelective(supplierInfo);
    }
    
}
