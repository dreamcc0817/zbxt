package com.bonc.ioc.model;

import java.io.Serializable;
import java.util.Date;

public class RelAccRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private String id;
	/** 用户id */
	private String accId;
	/** 角色id */
	private String roleId;
	/** 创建人 */
	private String createAccId;
	/** 创建时间 */
	private Date createTime;
	/** 修改人 */
	private String updateAccId;
	/** 修改时间 */
	private Date updateTime;
	/** 时间戳 */
	private String ts;
	/** 删除标记 1=删除，0=未删除 */
	private Integer isValid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
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
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
	
}
