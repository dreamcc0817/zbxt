package com.bonc.ioc.model;
import com.bonc.ioc.constraint.IsChinese;
import com.bonc.ioc.constraint.IsPositiveInteger;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: zcs
 * @Date: 2018/12/21 17:44
 * @Description:
 */
public class TopIndexInfo {

    @NotBlank(message = "indexNameCn参数不能为空")
    @Length(max=50,message="indexNameCn字段长度不能超过{max}个字数")
    @IsChinese(message = "indexNameCn参数必须是中文")
    private String indexNameCn;

    @NotBlank(message = "indexCode参数不能为空")
    @Length(max=200,message="indexCode字段长度不能超过{max}个字数")
    private String indexCode;

    @Length(max=255,message="indexKeywords字段长度不能超过{max}个字数")
    private String indexKeywords;

    @IsPositiveInteger(message = "indexId参数里面不是整数")
    @Length(max=20,message="indexId字段长度不能超过{max}个字数")
    private String indexId;

    public String getIndexNameCn() {
        return indexNameCn;
    }

    public void setIndexNameCn(String indexNameCn) {
        this.indexNameCn = indexNameCn;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
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
}
