package com.bonc.ioc.model;

import java.util.Date;

/**
 * desc:字典实体类
 */
public class Dictionary {
private String id;
private String typeCode;
private String elemCode;
private String elemName;
private String elemDesc;
private Integer sort;
private Integer isValid;
private String extend1;
private String extend2;
private Date createTime;
private String createAccId;
private Date updateTime;
private String updateAccId;
private String remark;

    public Dictionary(String id, String typeCode, String elemCode, String elemName, String elemDesc, Integer sort, Integer isValid, String extend1, String extend2, Date createTime, String createAccId, Date updateTime, String updateAccId, String remark) {
        this.id = id;
        this.typeCode = typeCode;
        this.elemCode = elemCode;
        this.elemName = elemName;
        this.elemDesc = elemDesc;
        this.sort = sort;
        this.isValid = isValid;
        this.extend1 = extend1;
        this.extend2 = extend2;
        this.createTime = createTime;
        this.createAccId = createAccId;
        this.updateTime = updateTime;
        this.updateAccId = updateAccId;
        this.remark = remark;
    }

    public Dictionary() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getElemCode() {
        return elemCode;
    }

    public void setElemCode(String elemCode) {
        this.elemCode = elemCode;
    }

    public String getElemName() {
        return elemName;
    }

    public void setElemName(String elemName) {
        this.elemName = elemName;
    }

    public String getElemDesc() {
        return elemDesc;
    }

    public void setElemDesc(String elemDesc) {
        this.elemDesc = elemDesc;
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
}
