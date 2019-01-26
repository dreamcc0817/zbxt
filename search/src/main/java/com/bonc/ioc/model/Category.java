package com.bonc.ioc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Administrator on 2018/11/8.
 */
public class Category {
    private String categoryId;//主键ID
    private String categoryPid;//父级节点ID
    private String categoryName;//类目名称
    private String categoryFullcode;//utf8
    private String categoryCode;//类目编码
    private String categoryLevel;//指标级别
    private String categoryDesc;//类目描述
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String categoryCreator;//创建人员
    private String categoryCreattime;//创建时间
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String categoryUpdator;//更新人员
    private String categoryUpdatetime;//更新时间

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryPid() {
        return categoryPid;
    }

    public void setCategoryPid(String categoryPid) {
        this.categoryPid = categoryPid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryFullcode() {
        return categoryFullcode;
    }

    public void setCategoryFullcode(String categoryFullcode) {
        this.categoryFullcode = categoryFullcode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(String categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryCreator() {
        return categoryCreator;
    }

    public void setCategoryCreator(String categoryCreator) {
        this.categoryCreator = categoryCreator;
    }

    public String getCategoryCreattime() {
        return categoryCreattime;
    }

    public void setCategoryCreattime(String categoryCreattime) {
        this.categoryCreattime = categoryCreattime;
    }

    public String getCategoryUpdator() {
        return categoryUpdator;
    }

    public void setCategoryUpdator(String categoryUpdator) {
        this.categoryUpdator = categoryUpdator;
    }

    public String getCategoryUpdatetime() {
        return categoryUpdatetime;
    }

    public void setCategoryUpdatetime(String categoryUpdatetime) {
        this.categoryUpdatetime = categoryUpdatetime;
    }
}
