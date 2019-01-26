package com.bonc.ioc.model;

import java.io.Serializable;
import java.util.Date;

public class DicArea implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键ID */
	private String id;
	/** 区划父编码 */
	private String areaPcode;
	/** 区划编码 */
	private String areaCode;
	/** 区划名称（全称） */
	private String areaNameF;
	/** 区划名称（简称） */
	private String areaNameS;
	/** 父节点名称 */
	private String pareaName;
	/** 节点等级 */
	private Integer areaLevel;
	/** 是否末节点 */
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
	
	private Integer areaCount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaPcode() {
		return areaPcode;
	}
	public void setAreaPcode(String areaPcode) {
		this.areaPcode = areaPcode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaNameF() {
		return areaNameF;
	}
	public void setAreaNameF(String areaNameF) {
		this.areaNameF = areaNameF;
	}
	public String getAreaNameS() {
		return areaNameS;
	}
	public void setAreaNameS(String areaNameS) {
		this.areaNameS = areaNameS;
	}
	public Integer getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
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
	public String getPareaName() {
		return pareaName;
	}
	public void setPareaName(String pareaName) {
		this.pareaName = pareaName;
	}
	public Integer getAreaCount() {
		return areaCount;
	}
	public void setAreaCount(Integer areaCount) {
		this.areaCount = areaCount;
	}
}
