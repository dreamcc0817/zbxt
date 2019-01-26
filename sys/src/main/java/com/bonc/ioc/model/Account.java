/*
 * Copyright(C)2016-2020 BONC Software Co. Ltd. All rights reserved.
 * 系统名称：bonc_ioc_ompV1.1
 * 作者：左明强  && 手机：13522126905
 * 版本号                           日   期                          作     者                    变更说明
 * Account-V1.0   2017/02/23     左明强                      新建
*/
package com.bonc.ioc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 名       称：系统账号实体类
 * <p> 功       能：持久化账号信息
 * <p> 作       者： 左明强
 * <p> 联系方式：13522126905
 * <p> 创建时间：2017/02/23 10:00:00 
 * <p> 特殊情形： 无
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private String id;
	/** 登录账号 */
	private String accNum;
	/** 账户名称 */
	private String accName;
	/** 登录密码 */
	private String accPwd;
	/** 加密干扰值 */
	private String salt;
	/** 性别 */
	private String sex;
	/** 账户组编码 */
	private String accGroupId;
	/** 岗位编码 */
	private String jobId;
	/** 行政区划编码 */
	private String areaId;
	/** 账户头像 */
	private String accPhoto;
	/** 联系地址 */
	private String contactAdd;
	/** 联系电话 */
	private String contactMobile;
	/** 联系邮箱 */
	private String contactMail;
	/** 登录IP */
	private String loginIp;
	/** 排序号 */
	private Integer sort;
	/** 账户类型 */
	private Integer accType;
	/** 在线时长-最后一次 */
	private String onlineTimeLast;
	/** 在线时长-累计 */
	private String onlineTimeSum;
	/** 最后登录时间 */
	private Date loginTimeLast;
	/** 是否在线 */
	private Integer isOnline;
	/** 是否超级管理员 */
	private String isAdmin;
	/** 是否锁定 */
	private Integer isLocked;
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
	
	/***======================================*/
	/** 行政区划名称 */
	private String areaName;
	private String orgName;
	
	private String orgCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccPwd() {
		return accPwd;
	}

	public void setAccPwd(String accPwd) {
		this.accPwd = accPwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAccGroupId() {
		return accGroupId;
	}

	public void setAccGroupId(String accGroupId) {
		this.accGroupId = accGroupId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAccPhoto() {
		return accPhoto;
	}

	public void setAccPhoto(String accPhoto) {
		this.accPhoto = accPhoto;
	}

	public String getContactAdd() {
		return contactAdd;
	}

	public void setContactAdd(String contactAdd) {
		this.contactAdd = contactAdd;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getAccType() {
		return accType;
	}

	public void setAccType(Integer accType) {
		this.accType = accType;
	}

	public String getOnlineTimeLast() {
		return onlineTimeLast;
	}

	public void setOnlineTimeLast(String onlineTimeLast) {
		this.onlineTimeLast = onlineTimeLast;
	}

	public String getOnlineTimeSum() {
		return onlineTimeSum;
	}

	public void setOnlineTimeSum(String onlineTimeSum) {
		this.onlineTimeSum = onlineTimeSum;
	}

	public Date getLoginTimeLast() {
		return loginTimeLast;
	}

	public void setLoginTimeLast(Date loginTimeLast) {
		this.loginTimeLast = loginTimeLast;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCredentialsSalt() {
		return this.accNum + salt;
	}
}