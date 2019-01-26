package com.bonc.ioc.dao;

import com.bonc.ioc.core.base.mapper.BaseDao;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.DocumentInfo;
import com.bonc.ioc.model.IndexInfo;
import com.bonc.ioc.model.TopIndexInfo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface indexDao extends BaseDao<HashMap<String, Object>> {

    /**
     * 查询指标
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-07
     */
    List<HashMap<String, Object>> selectByPageIndexList(HashMap<String, Object> params) throws McpException;

    /**
     * 目录查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-07
     */
    List<HashMap<String, Object>> selectCategoryList(HashMap<String, Object> params) throws McpException;

    /**
     * 修改指标状态
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-07
     */
    Integer editIndexStatus(HashMap<String, Object> params) throws McpException;

    /**
     * 删除指标基本信息
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-07
     */
    Integer deleteIndexId(String indexId) throws McpException;


    /**
     * 删除指标参考依据关联
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-07
     */
    Integer deleteIndexToDocument(String indexId) throws McpException;


    /**
     * 删除指标类目字典根据指标主键
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-07
     */
    Integer deleteindexToAttrsDicsByIndexId(String indexId) throws McpException;

    /**
     * 关键字列表查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-08
     */
    List<HashMap<String, Object>> selectIndexKeywordsList(String indexId) throws McpException;

    /**
     * 指标基本信息查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-09
     */
    HashMap<String, Object> selectIndexInfo(String indexId) throws McpException;

    /**
     * 指标顶部基本信息查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-09
     */
    HashMap<String, Object> selectIndexTopInfo(String indexId) throws McpException;


    /**
     * 指标的文件信息分页查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-09
     */
    List<HashMap<String, Object>> selectByPageDocumentList(HashMap<String, Object> params) throws McpException;

    /**
     * 指标的录属性及属性字典分页查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-09
     */
    List<HashMap<String, Object>> selectByPageAttrsDicsList(HashMap<String, Object> params) throws McpException;

    /**
     * 目录属性及字典名称分页查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-13
     */
    List<HashMap<String, Object>> selectByPageDicsNameList(HashMap<String, Object> params) throws McpException;

    /**
     * 属性字典列表查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-13
     */
    List<HashMap<String, Object>> selectDicList(HashMap<String, Object> params) throws McpException;

    /**
     * 指标信息增加
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-13
     */
    Integer addIndex(IndexInfo IndexInfo) throws McpException;

    /**
     * 指标类目字典表删除该指标非传值目录的关联关系
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-13
     */
    Integer deleteIndexToAttrsDicsByCode(HashMap<String, Object> param) throws McpException;

    /**
     * 指标参考依据关联表增加
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-13
     */
    Integer addIndexToDocument(HashMap<String, Object> param) throws McpException;

    /**
     * 指标类目字典表增加
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-13
     */
    Integer addIndexToAttrsDics(HashMap<String, Object> param) throws McpException;

    /**
     * 指标信息修改
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-14
     */
    Integer updateIndex(IndexInfo IndexInfo) throws McpException;

    /**
     * 指标类目字典表修改数据状态
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-14
     */
    Integer updateIndexToAttrsDicsIndexId(HashMap<String, Object> param) throws McpException;

    /**
     * 标准文件修改
     * *
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-17
     */
    Integer updateDocument(DocumentInfo documentInfo) throws McpException;

    /**
     * 查询文件列表（分页）
     *
     * @param params
     * @return
     * @throws McpException
     */
    List<HashMap<String, Object>> selectByPageTypeAndName(HashMap<String, Object> params) throws McpException;

    /**
     * 关键字增加
     * <p> 作 者：zcs
     * 创建时间：2018/12/12
     *
     * @param keywordsName
     * @return
     * @throws McpException
     */
    Integer addKeywords(@Param("keywordsName") String keywordsName) throws McpException;

    /**
     * 文件增加
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/13</p>
     *
     * @param documentInfo
     * @return
     */
    Integer insertDocument(DocumentInfo documentInfo) throws McpException;

    /**
     * 修改顶部指标
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/13</p>
     *
     * @param topIndexInfo
     * @return
     * @throws McpException
     */
    Integer updateTopIndex(TopIndexInfo topIndexInfo) throws McpException;

    /**
     * 新增顶部指标
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/13</p>
     *
     * @param topIndexInfo
     * @return
     * @throws McpException
     */
    Integer addTopIndex(TopIndexInfo topIndexInfo) throws McpException;

    /**
     *关系表index_to_document添加数据，状态暂存(1)。
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/14</p>
     * @param params
     * @return
     */
    Integer insertIndexToDoc(Map<String, Object> params) throws McpException;

    /**
     * 根据indexId和docId和dataType删除index_to_document表中的记录
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/17</p>
     * @param params
     * @return
     */
    Integer deleteIndexToDocumentByDataType(Map<String, Object> params) throws McpException;

    /**
     * 根据docId从document中查询记录
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/17</p>
     * @param docId
     * @return
     */
    Map<String,Object> getFileByDocId(@Param("docId") String docId) throws McpException;

    /**
     * 指标参考依据关联表改数据状态
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-12-18
     */
    Integer updateIndexToDocument(HashMap<String, Object> param) throws McpException;

    /**
     * 指标类目字典表删除根据指标主键字典主键
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-12-19
     */
    Integer deleteIndexToAttrsDicsById(HashMap<String, Object> param) throws McpException;

    /**
     * 查询指标类目字典行数
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-12-21
     */
    Integer getIndexToAttrsDicsTotal(HashMap<String, Object> param) throws McpException;


    /**
     * 修改index_to_document表的文件状态通过文件状态和指标id
     *
     * @version 1.0
     * @Author ycy
     * @Date 2018-12-19
     */
    Integer updateDTByDTeAndII(@Param(value = "indexId") String indexId,
                               @Param(value = "beforDataType") String beforDataType,
                               @Param(value = "afterDataType") String afterDataType) throws McpException;

    /**
     *  通过index_to_document表的指标id和指标状态删除数据
     *
     * @version 1.0
     * @Author ycy
     * @Date 2018-12-19
     */
    Integer deleteITDByDTeAndII(@Param(value = "indexId") String indexId,
                                @Param(value = "dataType") String dataType) throws McpException;
    /**
     * 修改index_to_attrs_dics表删除非本类目下的字典
     *
     * @version 1.0
     * @Author ycy
     * @Date 2018-12-19
     */
    Integer deleteOtherdDics(@Param(value = "indexId") String indexId,
                             @Param(value = "beforDataType") String beforDataType,
                             @Param(value = "afterDataType") String afterDataType,
                             @Param(value = "fullcodes") String fullcodes) throws McpException;
    /**
     * 通过属性id、指标id、属性字典状态修改index_to_attrs_dics表
     *
     * @version 1.0
     * @Author ycy
     * @Date 2018-12-19
     */
    Integer updateITADByDTeAndIIAndAI(@Param(value = "indexId") String indexId,
                                      @Param(value = "beforDataType") String beforDataType,
                                      @Param(value = "afterDataType") String afterDataType,
                                      @Param(value = "fullcodes")  String fullcodes) throws McpException;
    /**
     * 通过指标id、属性字典状态修改index_to_attrs_dics表
     *
     * @version 1.0
     * @Author ycy
     * @Date 2018-12-19
     */
    Integer updateITADByDTeAndII(@Param(value = "indexId") String indexId,
                                 @Param(value = "beforDataType") String beforDataType,
                                 @Param(value = "afterDataType") String afterDataType) throws McpException;
    /**
     *  通过index_to_attrs_dics表的指标id和字典关联关系状态删除数据
     *
     * @version 1.0
     * @Author ycy
     * @Date 2018-12-19
     */
    Integer deleteITADByDTeAndII(@Param(value = "indexId") String indexId,
                                 @Param(value = "dataType") String dataType) throws McpException;


}
