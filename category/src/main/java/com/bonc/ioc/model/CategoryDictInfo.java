package com.bonc.ioc.model;

public class CategoryDictInfo {
    Long attrId;
    String dicName;

    public CategoryDictInfo() {
    }

    public CategoryDictInfo(Long attrId, String dicName) {
        super();
        this.attrId = attrId;
        this.dicName = dicName;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }
}
