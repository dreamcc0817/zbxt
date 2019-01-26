package com.bonc.ioc.controller;

import com.bonc.ioc.constraint.IsPositiveInteger;
import com.bonc.ioc.constraint.IsRealPositiveInteger;
import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.model.DocumentInfo;
import com.bonc.ioc.model.IndexInfo;
import com.bonc.ioc.model.TopIndexInfo;
import com.bonc.ioc.service.IndexService;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Validated
public class indexController {
    @Resource
    private IndexService indexService;
    /**
     * 查询指标
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/07
     * @return
     * @throws Exception
     */
    @GetMapping("/getIndexes")
    @ApiOperation(value = "查询指标（分页查询）")
    public AppReply getIndexes(
            @ApiParam(value = "分页、每页大小",required = true) @NotBlank(message = "pageSize不能为空") @IsRealPositiveInteger(message = "pageSize必须是正整数") String pageSize,
            @ApiParam(value = "分页、当前页数",required = true) @NotBlank(message = "pageNumber不能为空") @IsRealPositiveInteger(message = "pageNumber必须是正整数") String pageNumber,
            @ApiParam(value = "指标编号",required = false) @Length(max = 200,message = "indexCode最大长度不能超过{max}") String indexCode,
            @ApiParam(value = "指标名称",required = false) @Length(max = 50,message = "indexNameCn最大长度不能超过{max}") String indexNameCn,
            @ApiParam(value = "指标状态",required = false)@Length(max = 11,message = "indexStatus最大长度不能超过{max}") @IsPositiveInteger(message = "indexStatus必须是自然数")String indexStatus,
            @ApiParam(value = "指标分类(目录)",required = false) @Length(max = 200,message = "categoryFullcode最大长度不能超过{max}") String categoryFullcode,
            @ApiParam(value = "排序字段",required = false) @Length(max = 20,message = "sortby最大长度不能超过{max}") String sortby,
            @ApiParam(value = "排序顺序",required = false) @Length(max = 4,message = "order最大长度不能超过{max}") String order
    ) throws  McpException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        PageResult pageResult = new PageResult();
        params.put("indexCode",indexCode);
        params.put("indexNameCn",indexNameCn);
        params.put("indexStatus",indexStatus);
        params.put("categoryFullcode",categoryFullcode);
        params.put("sortby",sortby);
        params.put("order",order);
        pageResult=indexService.selectByPageIndexList(params);
        return AppReply.success(pageResult);

    }
    /**
     * 修改指标状态
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-07
     */
    @PutMapping("/updateIndexStatus")
    @ApiOperation(value = "修改指标状态")
    public  AppReply updateIndexStatus(
            @ApiParam(value = "指标id",required = true) @NotBlank(message = "指标id不能为空！") String indexId,
            @ApiParam(value = "指标状态(1为可用，0为停用)",required = true) @NotBlank(message = "指标状态不能为空！") String indexStatus
    )throws  McpException{
        HashMap<String, Object> params = new HashMap<String, Object>();
        if (indexStatus.length() > 1 ||(!"0".equals(indexStatus)&&!"1".equals(indexStatus))) {
            return AppReply.error("indexStatus不能为空或者传值不正确");
        }
        params.put("indexId",indexId);
        params.put("indexStatus",indexStatus);
        Integer result=indexService.editIndexStatus(params);
        if (result==1){
            return AppReply.success("成功");
        }else{
            return AppReply.error("未修改成功，参数输入错误或不存在的参数值");
        }
    }

    /**
     * 删除指标
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-08
     */
    @DeleteMapping("/deleteIndexes/{indexId}")
    @ApiOperation(value = "删除指标")
    public @ResponseBody  AppReply deleteIndexes(@ApiParam(value = "指标id",required = true) @NotBlank(message = "指标id不能为空！") @PathVariable String indexId)throws  McpException{
        Integer result=indexService.deleteIndexes(indexId);
        if (result==1){
            return AppReply.success("成功");
        }else{
            return AppReply.error("未全部删除成功，参数输入错误或不存在的参数值");
        }
    }

    /**
     * 关键字列表查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/08
            * @return
            * @throws Exception
     */
    @GetMapping("/getIndexKeywords/{keywordsName}")
    @ApiOperation(value = "查询关键字")
    public @ResponseBody AppReply getIndexKeywords(@ApiParam(value = "关键字名称",required = true) @NotBlank(message = "keywordsName不能为空！") @PathVariable String keywordsName) throws  McpException {
        List<HashMap<String, Object>> KeywordsList = new ArrayList<HashMap<String, Object>>();
        KeywordsList=indexService.selectIndexKeywordsList(keywordsName);
        return AppReply.success(KeywordsList);

    }

    /**
     * 指标基本信息查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/09
     * @return
     * @throws Exception
     */
    @GetMapping("/getDetailsById/{indexId}")
    @ApiOperation(value = "查询指标详情")
    public @ResponseBody AppReply getDetailsById(
            @ApiParam(value = "指标主键",required = true) @NotBlank(message = "indexId不能为空！") @PathVariable String indexId
    ) throws  McpException {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap=indexService.selectIndexBaseInfo(indexId);
        return AppReply.success(resultMap);

    }

    /**
     * 指标的文件信息分页查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/09
     * @return
     * @throws Exception
     */
    @GetMapping("/getDocumentById")
    @ApiOperation(value = "指标的文件查询（分页查询）")
    public  AppReply getDocumentById(
            @ApiParam(value = "指标主键", required = true) @NotBlank(message = "指标主键参数缺省！") @Length(max=50,message="类目全码字段长度不能超过{max}个字数")  String indexId
    ) throws  McpException {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("indexId",indexId);
        pageResult=indexService.getDocumentById(params);
        return AppReply.success(pageResult);

    }

    /**
     * 指标的录属性及属性字典分页查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/09
     * @return
     * @throws Exception
     */
    @GetMapping("/getAttrsDicsById")
    @ApiOperation(value = "查询指标详情—类目属性模块")
    public  AppReply getAttrsDicsById(
            @ApiParam(value = "指标主键",required = true) @NotBlank(message = "indexId不能为空！") String indexId
    ) throws  McpException {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("indexId",indexId);
        pageResult=indexService.getAttrsDicsById(params);
        return AppReply.success(pageResult);

    }

    /**
     * 录属性及属性字典分页查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/13
     *<p>
     * @return
     * @throws Exception
     */
    @GetMapping("/getAttrsDicsCode")
    @ApiOperation(value = "指标增加修改—目录属性分页查询")
    public  AppReply getAttrsDicsCode(
            @ApiParam(value = "指标主键", required = true) @NotBlank(message = "指标主键参数缺省！") @Length(max=20,message="类目全码字段长度不能超过{max}个字数")  String indexId,
            @ApiParam(value = "类目录全码主键", required = false) String categoryFullcode
    ) throws  McpException {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("indexId",indexId);
        params.put("categoryFullcode",categoryFullcode);
        pageResult=indexService.getAttrsDicsCode(params);
        return AppReply.success(pageResult);

    }

    /**
     * 属性字典列表查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/13
     *<p>
     * @return
     * @throws McpException
     */
    @GetMapping("/selectDicList")
    @ApiOperation(value = "属性字典列表查询")
    public  AppReply selectDicList(
            @ApiParam(value = "属性主键", required = true) @NotBlank(message = "属性主键参数缺省！") @Length(max=20,message="类目全码字段长度不能超过{max}个字数")  String attrId,
            @ApiParam(value = "指标主键", required = true) @NotBlank(message = "指标主键参数缺省！") String indexId
    ) throws  McpException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        List<HashMap<String, Object>> dicList = new ArrayList<HashMap<String, Object>>();
        params.put("attrId",attrId);
        params.put("indexId",indexId);
        dicList=indexService.selectDicList(params);
        return AppReply.success(dicList);

    }
    /**
     * 指标信息修改
     *<p> 作 者：ycy
     *<p> 创建时间：2018/12/20
     *<p>
     * @return
     * @throws McpException
     */
    @PostMapping("/updateIndex")
    public  AppReply updateIndex(@Validated IndexInfo indexInfo) throws  McpException {
        if (1 == indexService.updateIndex(indexInfo)){
            return AppReply.success("修改成功");
        }else{
            return AppReply.error("修改失败");
        }
    }

    /**
     * 指标信息修改取消
     *<p> 作 者：ycy
     *<p> 创建时间：2018/12/20
     *<p>
     * @return
     * @throws McpException
     */
    @PostMapping("/cancelOperation")
    public  AppReply cancelOperation(@NotBlank(message = "indexId不能为空")
                                     @IsPositiveInteger(message = "indexId必须是正整数")
                                     @Length(max=20,message="主键ID字段长度不能超过{max}个字数")
                                             String indexId,
                                     String categoryFullcode) throws  McpException {
        indexService.cancelOperation(indexId,categoryFullcode);
        return AppReply.success("取消成功");
    }
    /**
     * 指标顶部—新增和修改指标
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/13</p>
     *
     * @param topIndexInfo
     * @return
     * @throws McpException
     */
    @PostMapping("/addTopIndex")
    @ApiOperation(value = "指标顶部—新增和修改指标")
    @AppAuthAnnotation
    public AppReply addTopIndex(@Validated TopIndexInfo topIndexInfo) throws McpException {
        Integer result = indexService.addOrUpdateIndex(topIndexInfo);
        if (result == 1) {
            String indexId=topIndexInfo.getIndexId();
            Map<String,Object> resultMap=new HashMap<String,Object>();
            resultMap.put("indexId",indexId);
            return AppReply.success(resultMap);
        } else {
            return AppReply.error("失败");
        }
    }

    /**
     * 指标类目字典表增加
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/13
     *<p>
     * @return
     * @throws McpException
     */
    @PostMapping("/addIndexToAttrsDics")
    @ApiOperation(value = "属性字典列表查询")
    public  AppReply addIndexToAttrsDics(
            @ApiParam(value = "指标主键", required = true) @NotBlank(message = "指标主键参数缺省！") @Length(max=50,message="类目全码字段长度不能超过{max}个字数")  String indexId,
            @ApiParam(value = "属性主键", required = true) @NotBlank(message = "属性主键参数缺省！") @Length(max=50,message="类目全码字段长度不能超过{max}个字数")  String attrId,
            @ApiParam(value = "字典主键数据状态list", required = true) @NotBlank(message = "idTypeJosn参数缺省！")  String idTypeJosn
    ) throws  McpException {
        HashMap<String, Object> param=new HashMap<>();
        param.put("indexId",indexId);
        param.put("attrId",attrId);
        param.put("idTypeJosn",idTypeJosn);
        Integer result=indexService.addIndexToAttrsDics(param);
        if (result==1){
            return AppReply.success("添加成功");
        }else{
            return AppReply.error("添加失败");
        }

    }

    /**
     *  标准文件修改
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/17
     *<p>
     * @return
     * @throws McpException
     */
    @PutMapping("/updateDocument")
    @ApiOperation(value = "编辑文件")
    public  AppReply updateDocument(DocumentInfo documentInfo) throws  McpException {
        Integer result=indexService.addOrUpdateDocument(documentInfo);
        if (result==1){
            return AppReply.success("成功");
        }else{
            return AppReply.error("失败");
        }
    }
    /**
     * 查询文件列表（分页）
     * <p> 作 者：zcs
     * <p> 创建时间：2018/12/12
     * @param
     * @return
     * @throws McpException
     */
    @GetMapping("/getFiles")
    @ApiOperation(value = "查询文件列表（分页）")
    public AppReply getFiles(
            @ApiParam(value = "分页、每页大小", required = true) @NotBlank(message = "pageSize参数不能为空") @IsRealPositiveInteger(message = "请输入正整数") String pageSize,
            @ApiParam(value = "分页、当前页数",required = true) @NotBlank(message = "pageNumber参数不能为空")@IsRealPositiveInteger(message = "请输入正整数") String pageNumber,
            @ApiParam(value = "指标主键", required = false) @Length(max=20,message="indexId长度不能超过{max}个字数") @IsRealPositiveInteger(message = "indexId必须传入int类型的字符串") String indexId,
            @ApiParam(value = "文件类型", required = false) @Length(max=50,message="docType长度不能超过{max}个字数") String docType,
            @ApiParam(value = "文件名称", required = false) @Length(max=50,message="docName长度不能超过{max}个字数") String docName
    ) throws McpException {
        PageResult pageResult=new PageResult();
        HashMap<String,Object> params=new HashMap<String,Object>();
        if(!StringUtils.isNullOrEmpty(indexId)){
            params.put("indexId",indexId);
        }
        if(!StringUtils.isNullOrEmpty(docType)){
            params.put("docType",docType);
        }
        if(!StringUtils.isNullOrEmpty(docName)){
            params.put("docName",docName);
        }
        pageResult=indexService.getDocumentByTypeAndName(params);
        return AppReply.success(pageResult);
    }

    /**
     * 关键字增加
     * <p> 作 者：zcs
     * 创建时间：2018/12/12
     * @param keywordsName
     * @return
     * @throws McpException
     */
    @PutMapping("/addKeywords")
    @ApiOperation(value = "关键字增加")

    public AppReply addKeywords(@ApiParam(value = "关键字名称",required = true) @NotBlank(message = "关键字参数缺省！") @Length(max=50,message="keywordsName长度不能超过{max}个字数") String keywordsName) throws McpException{
        Integer result=indexService.addKeywords(keywordsName);
        if(result==1){
            return AppReply.success("成功");
        }else{
            return AppReply.error("失败");
        }
    }

    /**
     * 查询指标详情—顶部查询
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/14</p>
     * @return
     * @throws McpException
     */
    @GetMapping("/getTopDetailsById/{indexId}")
    @ApiOperation(value = "查询指标详情-顶部查询")
    @AppAuthAnnotation
    public AppReply getTopDetailsById(
            @ApiParam(value = "指标主键",required = true)
            @Length(max = 20,message = "长度不能超过{max}个长度")
            @IsRealPositiveInteger(message = "indexId必须是正整数")
            @PathVariable String indexId)
            throws McpException{
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap=indexService.getTopDetailsById(indexId);
        return AppReply.success(resultMap);
    }

    /**
     * 指标基础属性修改--参考依据--新建关联或取消关联
     * <p>作者：zcs</p>
     * <p>创建时间：2019/1/2</p>
     * @param indexId
     * @param docId
     * @param dataType
     * @return
     * @throws McpException
     */
    @PostMapping("/addOrUpdateDocToIndex")
    @ApiOperation(value = "指标基础属性修改--参考依据--新建关联或取消关联")
    @AppAuthAnnotation
    public AppReply addOrUpdateDocToIndex(
            @ApiParam(value = "指标主键",required = true) @NotBlank(message = "indexId不能为空") @Length(max = 20,message = "indexId不能超过{max}个长度") @IsRealPositiveInteger(message = "indexId必须是正整数") String indexId,
            @ApiParam(value = "文件主键",required = true) @NotBlank(message = "docId不能为空") @Length(max = 20,message = "docId不能超过{max}个长度") @IsRealPositiveInteger(message = "docId必须是正整数") String docId,
            @ApiParam(value = "数据状态",required = true) @NotNull(message = "dataType参数必须传") @Length(max = 50,message = "dataType不能超过{max}个长度") String dataType) throws McpException{
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("indexId",indexId);
        params.put("docId",docId);
        params.put("dataType",dataType);
        Integer result=indexService.addUpdateDelete((HashMap<String, Object>) params);
        if(result==1){
            return AppReply.success("成功");
        }else{
            return AppReply.error("您传入的参数数据库中不存在，操作失败");
        }
    }

    /**
     * 查询文件详情
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/17</p>
     * @param docId
     * @return
     * @throws McpException
     */
    @GetMapping("/getFileDetails")
    @ApiOperation(value = "查询文件详情")
    @AppAuthAnnotation
    public AppReply getFile(
            @ApiParam(value = "文件主键",required = true) @NotBlank(message ="docId不能为空" ) @Length(max = 20,message = "docId不能超过{max}个长度") @IsRealPositiveInteger(message = "docId必须是正整数") String docId)
            throws McpException{
        Map<String,Object> resultMap=indexService.getFile(docId);
        return AppReply.success(resultMap);
    }
}