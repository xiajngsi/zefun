package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class CodeLibrary {
	/** 类型编号 */
	private Integer typeNo;

	/** 类型名称 */
	private String typeName;

	/** 代码编号 */
	private Integer codeNo;

	/** 代码名称 */
	private String codeName;

	/** 排序号 */
	private Integer sortNo;

	/** @param typeNo	类型编号 */
	public void setTypeNo(Integer typeNo){
		this.typeNo = typeNo;
	}

	/** @return	类型编号 */
	public Integer getTypeNo(){
		return typeNo;
	}

	/** @param typeName	类型名称 */
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}

	/** @return	类型名称 */
	public String getTypeName(){
		return typeName;
	}

	/** @param codeNo	代码编号 */
	public void setCodeNo(Integer codeNo){
		this.codeNo = codeNo;
	}

	/** @return	代码编号 */
	public Integer getCodeNo(){
		return codeNo;
	}

	/** @param codeName	代码名称 */
	public void setCodeName(String codeName){
		this.codeName = codeName;
	}

	/** @return	代码名称 */
	public String getCodeName(){
		return codeName;
	}

	/** @param sortNo	排序号 */
	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}

	/** @return	排序号 */
	public Integer getSortNo(){
		return sortNo;
	}
}