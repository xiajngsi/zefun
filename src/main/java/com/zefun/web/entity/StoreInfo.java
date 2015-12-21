package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月24日 PM 19:48:40
 */
public class StoreInfo {
	/** 门店标识 */
	private Integer storeId;

	/** 总店标识(总店为本身id) */
	private Integer hqStoreId;

	/** 门店类型(1：单店，2：连锁总店，3：连锁分店) */
	private Integer storeType;

	/** 门店名称 */
	private String storeName;

	/** 门店联系人 */
	private String storeLinkname;

	/** 门店联系电话 */
	private String storeLinkphone;

	/** 门店所属城市 */
	private String storeCity;

	/** 门店所属省份 */
	private String storeProvince;

	/** 店铺地址 */
	private String storeAddress;

	/** 店铺电话 */
	private String storeTel;

	/** 店铺LOGO */
	private String storeLogo;

	/** 店铺轮播图片 */
	private String carouselPicture;

	/** 门店介绍 */
	private String storeDesc;

	/** 技术展示 */
	private String technical;

	/** 特色服务 */
	private String characteristic;

	/** 名师介绍 */
	private String teacherIntroduction;

	/** 门店状态(1:申请中，2:试运营，3:正常营业，4:已过期，5:已删除) */
	private Integer storeStatus;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param hqStoreId	总店标识(总店为本身id) */
	public void setHqStoreId(Integer hqStoreId){
		this.hqStoreId = hqStoreId;
	}

	/** @return	总店标识(总店为本身id) */
	public Integer getHqStoreId(){
		return hqStoreId;
	}

	/** @param storeType	门店类型(1：单店，2：连锁总店，3：连锁分店) */
	public void setStoreType(Integer storeType){
		this.storeType = storeType;
	}

	/** @return	门店类型(1：单店，2：连锁总店，3：连锁分店) */
	public Integer getStoreType(){
		return storeType;
	}

	/** @param storeName	门店名称 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	/** @return	门店名称 */
	public String getStoreName(){
		return storeName;
	}

	/** @param storeLinkname	门店联系人 */
	public void setStoreLinkname(String storeLinkname){
		this.storeLinkname = storeLinkname;
	}

	/** @return	门店联系人 */
	public String getStoreLinkname(){
		return storeLinkname;
	}

	/** @param storeLinkphone	门店联系电话 */
	public void setStoreLinkphone(String storeLinkphone){
		this.storeLinkphone = storeLinkphone;
	}

	/** @return	门店联系电话 */
	public String getStoreLinkphone(){
		return storeLinkphone;
	}

	/** @param storeCity	门店所属城市 */
	public void setStoreCity(String storeCity){
		this.storeCity = storeCity;
	}

	/** @return	门店所属城市 */
	public String getStoreCity(){
		return storeCity;
	}

	/** @param storeProvince	门店所属省份 */
	public void setStoreProvince(String storeProvince){
		this.storeProvince = storeProvince;
	}

	/** @return	门店所属省份 */
	public String getStoreProvince(){
		return storeProvince;
	}

	/** @param storeAddress	店铺地址 */
	public void setStoreAddress(String storeAddress){
		this.storeAddress = storeAddress;
	}

	/** @return	店铺地址 */
	public String getStoreAddress(){
		return storeAddress;
	}

	/** @param storeTel	店铺电话 */
	public void setStoreTel(String storeTel){
		this.storeTel = storeTel;
	}

	/** @return	店铺电话 */
	public String getStoreTel(){
		return storeTel;
	}

	/** @param storeLogo	店铺LOGO */
	public void setStoreLogo(String storeLogo){
		this.storeLogo = storeLogo;
	}

	/** @return	店铺LOGO */
	public String getStoreLogo(){
		return storeLogo;
	}

	/** @param carouselPicture	店铺轮播图片 */
	public void setCarouselPicture(String carouselPicture){
		this.carouselPicture = carouselPicture;
	}

	/** @return	店铺轮播图片 */
	public String getCarouselPicture(){
		return carouselPicture;
	}

	/** @param storeDesc	门店介绍 */
	public void setStoreDesc(String storeDesc){
		this.storeDesc = storeDesc;
	}

	/** @return	门店介绍 */
	public String getStoreDesc(){
		return storeDesc;
	}

	/** @param technical	技术展示 */
	public void setTechnical(String technical){
		this.technical = technical;
	}

	/** @return	技术展示 */
	public String getTechnical(){
		return technical;
	}

	/** @param characteristic	特色服务 */
	public void setCharacteristic(String characteristic){
		this.characteristic = characteristic;
	}

	/** @return	特色服务 */
	public String getCharacteristic(){
		return characteristic;
	}

	/** @param teacherIntroduction	名师介绍 */
	public void setTeacherIntroduction(String teacherIntroduction){
		this.teacherIntroduction = teacherIntroduction;
	}

	/** @return	名师介绍 */
	public String getTeacherIntroduction(){
		return teacherIntroduction;
	}

	/** @param storeStatus	门店状态(1:申请中，2:试运营，3:正常营业，4:已过期，5:已删除) */
	public void setStoreStatus(Integer storeStatus){
		this.storeStatus = storeStatus;
	}

	/** @return	门店状态(1:申请中，2:试运营，3:正常营业，4:已过期，5:已删除) */
	public Integer getStoreStatus(){
		return storeStatus;
	}

	/** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

}