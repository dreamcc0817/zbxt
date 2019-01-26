package com.bonc.ioc.model;

import java.io.Serializable;
import java.util.Date;

public class RelSysRole implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private String id;
	/** 系统id */
	private String sysId;
	/** 角色id */
	private String roleId;
	/** 数据状态（0：逻辑删除，1,：正常） */
	private String isValid;
	/** 创建账户 */
	private String createAccId;
	/** 创建时间（yyyy-MM-dd hh:mi:ss） */
	private Date createTime;
	/** 修改账户 */
	private String updateAccId;
	/** 修改时间（yyyy-MM-dd hh:mi:ss） */
	private Date updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
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
	
	
}
