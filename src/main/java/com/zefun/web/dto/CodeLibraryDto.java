package com.zefun.web.dto;

import java.util.List;

/**
 * 查询子集数据字典
 * @author laowang
 * @date 2015年8月6日 下午4:42:19
 *
 */
public class CodeLibraryDto {
	
    /**
     * 类型编号
     */
	private Integer typeNo;
	/**
	 * 父代码编号
	 */
	private Integer fatherCodeNo;
	
	/**
	 * 代码名称
	 */
	private String codeName;
	/***/
	private String typeName;
	/***/
	private List<CodeLibraryDto> codeLibraryDtos;
	
	public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<CodeLibraryDto> getCodeLibraryDtos() {
        return codeLibraryDtos;
    }

    public void setCodeLibraryDtos(List<CodeLibraryDto> codeLibraryDtos) {
        this.codeLibraryDtos = codeLibraryDtos;
    }

    public Integer getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(Integer typeNo) {
		this.typeNo = typeNo;
	}

	public Integer getFatherCodeNo() {
		return fatherCodeNo;
	}

	public void setFatherCodeNo(Integer fatherCodeNo) {
		this.fatherCodeNo = fatherCodeNo;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	
}
