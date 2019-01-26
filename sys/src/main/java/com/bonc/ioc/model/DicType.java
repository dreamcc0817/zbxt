package com.bonc.ioc.model;

import java.io.Serializable;
import java.util.Date;

public class DicType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private String id;
	/** 字典类型父编码 */
	private String typePcode;
	/** 字典类型编码 */
	private String typeCode;
	/** 字典类型名称 */
	private String typeName;
	/** 字典类型描述 */
	private String typeDesc;
	/** 节点等级 */
	private Integer typeLevel;
	/** 是否末节点(1:是，0否) */
	private Integer isLast;
	/** 排序 */
	private Integer sort;
	/** 数据状态 */
	private Integer isValid;
	/** 扩展字段1 */
	private String extend1;
	/** 扩展字段2 */
	private String extend2;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private String createAccId;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private String updateAccId;
	/** 备注 */
	private String remark;
	/** 父节点名称 */
	private String pTypeName;
	/** 字典元素统计*/
	private int elemCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypePcode() {
		return typePcode;
	}
	public void setTypePcode(String typePcode) {
		this.typePcode = typePcode;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public Integer getTypeLevel() {
		return typeLevel;
	}
	public void setTypeLevel(Integer typeLevel) {
		this.typeLevel = typeLevel;
	}
	public Integer getIsLast() {
		return isLast;
	}
	public void setIsLast(Integer isLast) {
		this.isLast = isLast;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	public String getExtend2() {
		return extend2;
	}
	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateAccId() {
		return createAccId;
	}
	public void setCreateAccId(String createAccId) {
		this.createAccId = createAccId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateAccId() {
		return updateAccId;
	}
	public void setUpdateAccId(String updateAccId) {
		this.updateAccId = updateAccId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getpTypeName() {
		return pTypeName;
	}
	public void setpTypeName(String pTypeName) {
		this.pTypeName = pTypeName;
	}
	public int getElemCount() {
		return elemCount;
	}
	public void setElemCount(int elemCount) {
		this.elemCount = elemCount;
	}
	
}
