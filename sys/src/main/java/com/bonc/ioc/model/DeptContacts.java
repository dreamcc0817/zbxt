package com.bonc.ioc.model;

import com.bonc.ioc.core.base.model.BaseEntity;

import java.util.Date;

/**
 * 组织通讯录实体类
 */
public class DeptContacts extends BaseEntity {
	/** 部门名称 */
	private String deptName;
	/** 部门代码 */
	private String deptCode;
	/** 部门电话 */
	private String deptPhone;
	/** 是否可留言 */
	private String isAbeldComent;
	/** 上级组织 */
	private String parentId;
	/** 创建时间 */
	private Date createDate;
	/** 创建人 */
	private String createBy;
	/** 修改人 */
	private String updateBy;
	/** 修改时间 */
	private Date updateDate;
	/** 留言部门id */
	private String depId;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptPhone() {
		return deptPhone;
	}

	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}

	public String getIsAbeldComent() {
		return isAbeldComent;
	}

	public void setIsAbeldComent(String isAbeldComent) {
		this.isAbeldComent = isAbeldComent;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}
}
