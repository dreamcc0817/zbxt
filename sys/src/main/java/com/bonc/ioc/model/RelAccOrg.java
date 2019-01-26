package com.bonc.ioc.model;

import java.util.Date;

public class RelAccOrg {
    private String id;

    private String accId;

    private String orgId;

    private String createAccId;

    private Date createTime;

    private String updateAccId;

    private Date updateTime;

    private String ts;

    private Integer isValid;
    
    //=====================
    
    private String accName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId == null ? null : accId.trim();
    }

    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}
    
}