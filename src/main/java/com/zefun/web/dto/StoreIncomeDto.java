package com.zefun.web.dto;

/**
 * 门店收益类
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月28日
 */
public class StoreIncomeDto extends IncomeDto {

    /**
     * 门店的id
     */
    private Integer storeId;

    /**
     * 获取门店id
     * @author gebing
     * @date 2015年12月4日
     * @return 门店id
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * 这只门店id
     * @author gebing
     * @date 2015年12月4日
     * @param storeId 门店id
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

}
