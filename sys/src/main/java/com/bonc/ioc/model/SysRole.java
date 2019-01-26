package com.bonc.ioc.model;

import java.util.Date;

/**
 * 系统角色实体类
 */
public class SysRole {
    private String id;
    private String roleCode;
    private String roleName;

    private String roleType;
    private String roleIcon;

    private String roleDesc;

    private Integer sort;
    private Integer isLocked;

    private Integer isValid;

    private String extend1;

    private String extend2;

    private Date createTime;

    private String createAccId;

    private Date updateTime;

    private String updateAccId;

    private String remark;
    private String isLockedCopy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public String getRoleIcon() {
        return roleIcon;
    }

    public void setRoleIcon(String roleIcon) {
        this.roleIcon = roleIcon == null ? null : roleIcon.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
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

	public String getIsLockedCopy() {
		return isLockedCopy;
	}

	public void setIsLockedCopy(String isLockedCopy) {
		this.isLockedCopy = isLockedCopy;
	}
}