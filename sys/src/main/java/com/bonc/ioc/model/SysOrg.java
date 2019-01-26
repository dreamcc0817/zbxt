package com.bonc.ioc.model;

import java.util.Date;

/**
 * 系统组织机构实体类
 */
public class SysOrg {
	/** 主键 */
	private String id;
	/** 机构编码（父节点） */
	private String orgPcode;
	/** 机构编码 */
	private String orgCode;
	/** 机构名称（全称） */
	private String orgNameF;
	/** 机构名称（简称） */
	private String orgNameS;
	/** 机构类型 */
	private String orgType;
	/** 节点等级 */
	private Integer orgLevel;
	/** 是否末节点 */
	private Integer isLast;
	/** 排序号 */
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
	private String pOrgName;
	
	private String orgTypeName;
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgPcode() {
        return orgPcode;
    }

    public void setOrgPcode(String orgPcode) {
        this.orgPcode = orgPcode == null ? null : orgPcode.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgNameF() {
        return orgNameF;
    }

    public void setOrgNameF(String orgNameF) {
        this.orgNameF = orgNameF == null ? null : orgNameF.trim();
    }

    public String getOrgNameS() {
        return orgNameS;
    }

    public void setOrgNameS(String orgNameS) {
        this.orgNameS = orgNameS == null ? null : orgNameS.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
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
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
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
        this.createAccId = createAccId == null ? null : createAccId.trim();
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
        this.updateAccId = updateAccId == null ? null : updateAccId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getpOrgName() {
		return pOrgName;
	}

	public void setpOrgName(String pOrgName) {
		this.pOrgName = pOrgName;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}
	
}