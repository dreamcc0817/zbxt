package com.bonc.ioc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private String id;
	/** 上级菜单编码*/
    private String menuPcode;
    /** 菜单编码 */
    private String menuCode;
    /** 菜单名称 */
    private String menuName;
    /** 菜单标识 */
    private String menuType;
    /** 菜单地址 */
    private String menuUrl;
    /** 菜单图标 */
    private String menuIcon;
    /** 菜单深度 */
    private Integer menuLevel;
    /** 菜单有效期 */
    private Date menuEffectiveDate;
    /** 是否锁定（0：否，1：是） */
    private Integer isLocked;
    /** 排序号 */
    private Integer sort;
    /** 数据状态（0：逻辑删除，1,：正常） */
    private Integer isValid;
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
    
	/** 菜单id */
	private String menuId;
	
	/** 父节点名称 */
	private String pMenueName;
	
	/** 系统名称 */
	private String sysName;
	
	/** 系统分类*/
	private String sysType;
	/** 系统分类名称*/
	private String sysTypeName;
	
	private String menuTypeCopy;
	
	private List<SysMenu> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenuPcode() {
        return menuPcode;
    }

    public void setMenuPcode(String menuPcode) {
        this.menuPcode = menuPcode == null ? null : menuPcode.trim();
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Date getMenuEffectiveDate() {
        return menuEffectiveDate;
    }

    public void setMenuEffectiveDate(Date menuEffectiveDate) {
        this.menuEffectiveDate = menuEffectiveDate;
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

    public String getCreateAccId() {
        return createAccId;
    }

    public void setCreateAccId(String createAccId) {
        this.createAccId = createAccId == null ? null : createAccId.trim();
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
        this.updateAccId = updateAccId == null ? null : updateAccId.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getpMenueName() {
		return pMenueName;
	}

	public void setpMenueName(String pMenueName) {
		this.pMenueName = pMenueName;
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

	public String getSysTypeName() {
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMenuTypeCopy() {
		return menuTypeCopy;
	}

	public void setMenuTypeCopy(String menuTypeCopy) {
		this.menuTypeCopy = menuTypeCopy;
	}

}