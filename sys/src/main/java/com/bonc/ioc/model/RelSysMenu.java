package com.bonc.ioc.model;

import java.io.Serializable;
import java.util.Date;

public class RelSysMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private String id;
	/** 系统id */
	private String sysId;
	/** 菜单id */
	private String menuId;
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
	
	/**********========================================*/
	private String menuCode;
	
	private String menuPcode;
	
	private String menuName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSysId() {
		return sysId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
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
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuPcode() {
		return menuPcode;
	}
	public void setMenuPcode(String menuPcode) {
		this.menuPcode = menuPcode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
}
