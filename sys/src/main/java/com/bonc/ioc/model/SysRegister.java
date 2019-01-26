/*
 * Copyright(C)2016-2020 BONC Software Co. Ltd. All rights reserved.
 * 系统名称：bonc_ioc_imp 1.0
 * 作者：左明强  && 手机：13522126905
 * 版本号                      日   期                                作     者                    变更说明
 * Sys-V1.0   2016/08/22     左明强                      新建
*/
package com.bonc.ioc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 名       称：系统注册实体类
 * <p> 功       能：持久化系统注册信息
 * <p> 作       者： 左明强
 * <p> 联系方式：13522126905
 * <p> 创建时间：2016/08/22 10:00:00 
 * <p> 特殊情形： 无
 */
public class SysRegister implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private String id;
	/** 系统编码 */
	private String sysCode;
	/** 系统名称 */
	private String sysName;
	/** 系统标识 */
	private String sysType;
	/** 访问地址 */
	private String sysUrl;
	/** 系统图标 */
	private String sysIcon;
	/** 系统描述 */
	private String sysDesc;
	/** 单点登录令牌 */
	private String sysSsoToken;
	/** 是否接入单点（0：否，1：是） */
	private Integer isSso;
	/** 单点服务端id */
	private String casId;
	/** 是否发布（0：已发布，1：已弃用，2：待发布） */
	private Integer isPublish;
	/** 是否锁定（0：否，1：是） */
	private Integer isLocked;
	/** 排序号 */
	private Integer sort;
	/** 所属组织机构 */
	private String orgId;
	/** 数据状态（0：逻辑删除，1,：正常） */
	private String isValid;
	/** 拓展字段1 */
	private String extend1;
	/** 拓展字段2 */
	private String extend2;
	/** 创建账户 */
	private String createAccId;
	/** 创建时间（yyyy-MM-dd hh:mi:ss） */
	private Date createTime;
	/** 修改账户 */
	private String updateAccId;
	/** 修改时间（yyyy-MM-dd hh:mi:ss） */
	private Date updateTime;
	/** 备注 */
	private String remark;
	/**是否需要后台管理系统*/
	private Integer isCmsNeed;
	/***======================================*/
	/** 机构名称 */
	private String orgName;
	/** 系统分类名称 */
	private String sysTypeName;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getSysType() {
		return sysType;
	}
	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
	public String getSysUrl() {
		return sysUrl;
	}
	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}
	public String getSysIcon() {
		return sysIcon;
	}
	public void setSysIcon(String sysIcon) {
		this.sysIcon = sysIcon;
	}
	public String getSysDesc() {
		return sysDesc;
	}
	public void setSysDesc(String sysDesc) {
		this.sysDesc = sysDesc;
	}
	public String getSysSsoToken() {
		return sysSsoToken;
	}
	public void setSysSsoToken(String sysSsoToken) {
		this.sysSsoToken = sysSsoToken;
	}
	public Integer getIsSso() {
		return isSso;
	}
	public void setIsSso(Integer isSso) {
		this.isSso = isSso;
	}
	public String getCasId() {
		return casId;
	}
	public void setCasId(String casId) {
		this.casId = casId;
	}
	public Integer getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}
	public Integer getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
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
	public String getCreateAccId() {
		return createAccId;
	}
	public void setCreateAccId(String createAccId) {
		this.createAccId = createAccId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateAccId() {
		return updateAccId;
	}
	public void setUpdateAccId(String updateAccId) {
		this.updateAccId = updateAccId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSysTypeName() {
		return sysTypeName;
	}
	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}
	public Integer getIsCmsNeed() {
		return isCmsNeed;
	}
	public void setIsCmsNeed(Integer isCmsNeed) {
		this.isCmsNeed = isCmsNeed;
	}
}