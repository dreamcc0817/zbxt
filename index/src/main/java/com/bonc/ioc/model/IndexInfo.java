package com.bonc.ioc.model;

import com.bonc.ioc.constraint.IsPositiveInteger;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @Author: lsd
 * @Date: 2018/11/12 14:31
 * @Description:
 */
public class IndexInfo {

    private String indexDesc;

    @Length(max=50,message="indexAliasCn字段长度不能超过{max}个字数")
    private String indexAliasCn;

    @Length(max=50,message="indexAliasEn字段长度不能超过{max}个字数")
    private String indexAliasEn;

    @Length(max=50,message="indexShortnameEn字段长度不能超过{max}个字数")
    private String indexShortnameEn;

    @Length(max=50,message="indexUnit字段长度不能超过{max}个字数")
    private String indexUnit;

    @Length(max=50,message="indexType字段长度不能超过{max}个字数")
    private String indexType;

    @Length(max=50,message="indexAttr字段长度不能超过{max}个字数")
    private String indexAttr;

    @Length(max=50,message="indexIsstandard字段长度不能超过{max}个字数")
    private String indexIsstandard;

    @Length(max=20,message="indexStandardfileid字段长度不能超过{max}个字数")
    @IsPositiveInteger(message = "indexStandardfileid字段必须是正整数")
    private String indexStandardfileid;

    @Length(max=50,message="indexStandardcode字段长度不能超过{max}个字数")
    private String indexStandardcode;

    @Length(max=50,message="indexStandardname字段长度不能超过{max}个字数")
    private String indexStandardname;

    @Length(max=11,message="indexIscalculation字段长度不能超过{max}个字数")
    @IsPositiveInteger(message = "indexIscalculation字段必须是正整数")
    private String indexIscalculation;

    @Length(max=50,message="indexTechnical字段长度不能超过{max}个字数")
    private String indexTechnical;

    @Length(max=20,message="indexCalculatefileid字段长度不能超过{max}个字数")
    @IsPositiveInteger(message = "indexCalculatefileid字段必须是正整数")
    private String indexCalculatefileid;

    @Length(max=11,message="indexIssecret字段长度不能超过{max}个字数")
    @IsPositiveInteger(message = "indexIssecret字段必须是正整数")
    private String indexIssecret;

    @Length(max=50,message="indexSecuritylevel字段长度不能超过{max}个字数")
    private String indexSecuritylevel;

    @Length(max=20,message="indexSectetfileid字段长度不能超过{max}个字数")
    @IsPositiveInteger(message = "indexSectetfileid字段必须是正整数")
    private String indexSectetfileid;

    @Length(max=50,message="indexRemark字段长度不能超过{max}个字数")
    private String indexRemark;

    @Length(max=200,message="categoryFullcode字段长度不能超过{max}个字数")
    private String categoryFullcode;

    @NotBlank(message = "indexNameCn字段不能为空")
    @Length(max=50,message="indexNameCn字段长度不能超过{max}个字数")
    private String indexNameCn;

    @Length(max=255,message="indexKeywords字段长度不能超过{max}个字数")
    private String indexKeywords;

    @IsPositiveInteger(message = "indexId字段必须是正整数")
    @NotBlank(message = "indexId字段不能为空")
    @Length(max=20,message="indexId字段长度不能超过{max}个字数")
    private String indexId;

    private String docId;

    private String indexFullcode;

    private String indexCode;

    public String getIndexDesc() {
        return indexDesc;
    }

    public void setIndexDesc(String indexDesc) {
        this.indexDesc = indexDesc;
    }

    public String getIndexAliasCn() {
        return indexAliasCn;
    }

    public void setIndexAliasCn(String indexAliasCn) {
        this.indexAliasCn = indexAliasCn;
    }

    public String getIndexAliasEn() {
        return indexAliasEn;
    }

    public void setIndexAliasEn(String indexAliasEn) {
        this.indexAliasEn = indexAliasEn;
    }

    public String getIndexShortnameEn() {
        return indexShortnameEn;
    }

    public void setIndexShortnameEn(String indexShortnameEn) {
        this.indexShortnameEn = indexShortnameEn;
    }

    public String getIndexUnit() {
        return indexUnit;
    }

    public void setIndexUnit(String indexUnit) {
        this.indexUnit = indexUnit;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getIndexAttr() {
        return indexAttr;
    }

    public void setIndexAttr(String indexAttr) {
        this.indexAttr = indexAttr;
    }

    public String getIndexIsstandard() {
        return indexIsstandard;
    }

    public void setIndexIsstandard(String indexIsstandard) {
        this.indexIsstandard = indexIsstandard;
    }

    public String getIndexStandardfileid() {
        return indexStandardfileid;
    }

    public void setIndexStandardfileid(String indexStandardfileid) {
        this.indexStandardfileid = indexStandardfileid;
    }

    public String getIndexStandardcode() {
        return indexStandardcode;
    }

    public void setIndexStandardcode(String indexStandardcode) {
        this.indexStandardcode = indexStandardcode;
    }

    public String getIndexStandardname() {
        return indexStandardname;
    }

    public void setIndexStandardname(String indexStandardname) {
        this.indexStandardname = indexStandardname;
    }

    public String getIndexIscalculation() {
        return indexIscalculation;
    }

    public void setIndexIscalculation(String indexIscalculation) {
        this.indexIscalculation = indexIscalculation;
    }

    public String getIndexTechnical() {
        return indexTechnical;
    }

    public void setIndexTechnical(String indexTechnical) {
        this.indexTechnical = indexTechnical;
    }

    public String getIndexCalculatefileid() {
        return indexCalculatefileid;
    }

    public void setIndexCalculatefileid(String indexCalculatefileid) {
        this.indexCalculatefileid = indexCalculatefileid;
    }

    public String getIndexIssecret() {
        return indexIssecret;
    }

    public void setIndexIssecret(String indexIssecret) {
        this.indexIssecret = indexIssecret;
    }

    public String getIndexSecuritylevel() {
        return indexSecuritylevel;
    }

    public void setIndexSecuritylevel(String indexSecuritylevel) {
        this.indexSecuritylevel = indexSecuritylevel;
    }

    public String getIndexSectetfileid() {
        return indexSectetfileid;
    }

    public void setIndexSectetfileid(String indexSectetfileid) {
        this.indexSectetfileid = indexSectetfileid;
    }

    public String getIndexRemark() {
        return indexRemark;
    }

    public void setIndexRemark(String indexRemark) {
        this.indexRemark = indexRemark;
    }

    public String getCategoryFullcode() {
        return categoryFullcode;
    }

    public void setCategoryFullcode(String categoryFullcode) {
        this.categoryFullcode = categoryFullcode;
    }

    public String getIndexNameCn() {
        return indexNameCn;
    }

    public void setIndexNameCn(String indexNameCn) {
        this.indexNameCn = indexNameCn;
    }

    public String getIndexKeywords() {
        return indexKeywords;
    }

    public void setIndexKeywords(String indexKeywords) {
        this.indexKeywords = indexKeywords;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getIndexFullcode() {
        return indexFullcode;
    }

    public void setIndexFullcode(String indexFullcode) {
        this.indexFullcode = indexFullcode;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }
}
