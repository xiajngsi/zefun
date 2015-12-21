package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.WechatMenuDto;
import com.zefun.web.entity.Menu;
/**
 * 菜单
* @author 高国藩
* @date 2015年8月11日 下午3:31:07
 */
public interface MenuMapper {
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:31:24
    * @param menuId 主键
    * @return 状态
     */
    int deleteByPrimaryKey(Integer menuId);
    /**
     * 插入
    * @author 高国藩
    * @date 2015年8月11日 下午3:31:45
    * @param record 实体
    * @return 状态
     */
    int insert(Menu record);
    /**
     * 插入
    * @author 高国藩
    * @date 2015年8月11日 下午3:31:45
    * @param record 实体
    * @return 状态
     */
    int insertSelective(Menu record);
    /**
     * 查询
    * @author 高国藩
    * @date 2015年8月11日 下午3:31:45
    * @param menuId 主键
    * @return 实体
     */
    Menu selectByPrimaryKey(Integer menuId);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年8月11日 下午3:31:45
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKeySelective(Menu record);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年8月11日 下午3:31:45
    * @param record 实体
    * @return 状态
     */
    int updateByPrimaryKey(Menu record);
    /**
     * 查询菜单列表
    * @author 高国藩
    * @date 2015年8月11日 下午3:33:32
    * @param storeId 门店ID
    * @return 菜单列表
     */
	List<WechatMenuDto> selectAllMenu(Integer storeId);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年8月11日 下午3:34:09
    * @param map 参数
    * @return 状态
     */
	int deleteByParam(Map<String, Integer> map);
	
	/**
	 * 通过storeId为0查询出所有开发商指定的菜单信息
	* @author 高国藩
	* @date 2015年9月20日 下午4:07:59
	* @param storeId 0
	* @return 没有层级关系的菜单,只为了能插入复制数据
	 */
    List<Menu> selectAllMenus(int storeId);
    /**
     * 批量新增,复制菜单
    * @author 高国藩
    * @date 2015年9月20日 下午4:10:06
    * @param menus 菜单
     */
    void insertByList(List<Menu> menus);
}