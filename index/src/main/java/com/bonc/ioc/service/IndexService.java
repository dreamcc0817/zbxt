package com.bonc.ioc.service;

import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.model.DocumentInfo;
import com.bonc.ioc.model.IndexInfo;
import com.bonc.ioc.model.TopIndexInfo;
import com.bonc.ioc.util.AttrsDicsUtil;
import com.bonc.ioc.util.DocumentUtil;
import com.bonc.ioc.util.PinYin;
import com.mysql.jdbc.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class IndexService extends BaseService {
    @Resource
   private com.bonc.ioc.dao.indexDao indexDao;

    /**
     * 查询指标
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/07
     * @return
     * @throws Exception
     */
    public PageResult selectByPageIndexList(HashMap<String, Object> indexMap) throws McpException {
        List<HashMap<String, Object>> indexList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> pageMap = getParamMap();
        String categoryFullcode="";
        String categoryName="";
        indexMap.putAll(pageMap);
        indexList = indexDao.selectByPageIndexList(indexMap);
        if(null!=indexList&&indexList.size()>0){
            for(HashMap<String, Object> resultMap:indexList) {
                categoryFullcode = (String) resultMap.get("categoryFullcode");
                //获得指标所在目录名称
                categoryName=getCategoryfullName(categoryFullcode,">");
                resultMap.put("categoryName", categoryName);
            }
        }
        return new PageResult(indexMap, indexList);
    }
    /**
     * 修改指标状态
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-07
     */
    public  Integer editIndexStatus(HashMap<String,Object> map)throws  McpException{
        Integer result=indexDao.editIndexStatus(map);
        return result;
    }

    /**
     * 删除指标类目字典根据指标主键
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-07
     */
    public  Integer deleteIndexes(String indexId)throws  McpException{
        Integer result1=indexDao.deleteIndexId(indexId);
        Integer result3=indexDao.deleteIndexToDocument(indexId);
        Integer result2=indexDao.deleteindexToAttrsDicsByIndexId(indexId);
        return result1;
    }

    /**
     * 关键字列表查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-08
     */
    public  List<HashMap<String, Object>> selectIndexKeywordsList(String indexId)throws  McpException{
        List<HashMap<String, Object>> KeywordsList = new ArrayList<HashMap<String, Object>>();
        KeywordsList=indexDao.selectIndexKeywordsList(indexId);
        return KeywordsList;
    }

    /**
     * 指标基本信息查询
     *
     * @version 1.0
     * @Author lsd
     * @Date 2018-11-08
     */
    public  HashMap<String, Object> selectIndexBaseInfo(String indexId)throws  McpException{
        HashMap<String, Object> IndexMap = new HashMap<String, Object>();
        IndexMap=indexDao.selectIndexInfo(indexId);
        return IndexMap;
    }

    /**
     * 指标的文件信息分页查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/09
     * @return
     * @throws Exception
     */
    public PageResult getDocumentById(HashMap<String, Object> params) throws McpException {
        List<HashMap<String, Object>> documentList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> pageMap = getParamMap();
        params.putAll(pageMap);
        documentList=indexDao.selectByPageDocumentList(params);
        return new PageResult(params, documentList);
    }

    /**
     * 指标的录属性及属性字典分页查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/09
     * @return
     * @throws Exception
     */
    public PageResult getAttrsDicsById(HashMap<String, Object> params) throws McpException {
        List<HashMap<String, Object>> attrsDicsList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> pageMap = getParamMap();
        params.putAll(pageMap);
        attrsDicsList=indexDao.selectByPageAttrsDicsList(params);
        return new PageResult(params, attrsDicsList);
    }

    /**
     * 目录属性及字典名称分页查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/13
     * @return
     * @throws Exception
     */
    public PageResult getAttrsDicsCode(HashMap<String, Object> params) throws McpException {
        List<HashMap<String, Object>> attrsDicsList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> IndexTopMap = new HashMap<String, Object>();
        HashMap<String, Object> pageMap = getParamMap();
        String categoryFullcode=(String) params.get("categoryFullcode");
        if(!StringUtils.isNullOrEmpty(categoryFullcode)){
            String allCode=getCategoryfullcode(categoryFullcode) ;
            params.put("allCode",allCode);
        }
        params.putAll(pageMap);
        attrsDicsList=indexDao.selectByPageDicsNameList(params);
        return new PageResult(params, attrsDicsList);
    }

    /**
     * 属性字典列表查询
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/13
     * @return
     * @throws Exception
     */
    public   List<HashMap<String, Object>> selectDicList(HashMap<String, Object> params) throws McpException {
        List<HashMap<String, Object>> attrsDicsList = new ArrayList<HashMap<String, Object>>();
        attrsDicsList=indexDao.selectDicList(params);
        return attrsDicsList;
    }

    /**
     * 指标信息增加
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/13
     * @return
     * @throws Exception
     */
    public  Integer addIndex(IndexInfo IndexInfo) throws McpException {
        HashMap<String, Object> docMap=new HashMap<>();
        HashMap<String, Object> attrsDics=new HashMap<>();
        Integer result=null;
        String indexId=IndexInfo.getIndexId();
        String docId=IndexInfo.getDocId();
        String categoryFullcode=IndexInfo.getCategoryFullcode();
        String indexNameCn=IndexInfo.getIndexNameCn();
        String[] dcoArr=docId.split(",");
        if (!StringUtils.isNullOrEmpty(indexNameCn)&&!StringUtils.isNullOrEmpty(categoryFullcode)){
            String indexFullcode=categoryFullcode+"_INDEX_"+PinYin.getPinYinHeadToUpperChar(indexNameCn);
            IndexInfo.setIndexFullcode(indexFullcode);
        }
        result=indexDao.updateIndex(IndexInfo);
        //指标参考依据增加
        if (!StringUtils.isNullOrEmpty(indexId)&&dcoArr.length>0){
            //指标参考依据关联删除
            indexDao.deleteIndexToDocument(indexId);
            docMap.put("indexId",indexId);
            //指标参考依据关联增加（循环增加）
            for (String id:dcoArr){
                docMap.put("docId",id);
                indexDao.addIndexToDocument(docMap);
            }
        }
        //指标类目字典表增加
        if(!StringUtils.isNullOrEmpty(categoryFullcode)){
            attrsDics.put("indexId",indexId);
            //获得本目录code及所有父目录code
            categoryFullcode=getCategoryfullcode(categoryFullcode);
            if (!StringUtils.isNullOrEmpty(categoryFullcode)){
                //指标类目字典表修改指标主键,初次增加时默认指标主键的值为0，需修改
                //indexDao.updateIndexToAttrsDicsIndexId(indexId);
                attrsDics.put("categoryFullcode",categoryFullcode);
                //指标类目字典表删除该指标非传值目录的关联关系
                indexDao.deleteIndexToAttrsDicsByCode(attrsDics);
            }
        }
        return result;
    }

    /**
     * 指标类目字典表删除该指标非传值目录的关联关系
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/13
     * @return
     * @throws Exception
     */
    public  Integer deleteIndexToAttrsDics(HashMap<String, Object> param) throws McpException {
        Integer result=indexDao.deleteIndexToAttrsDicsByCode(param);
        return result;
    }

    /**
     * 指标参考依据关联表增加
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/13
     * @return
     * @throws Exception
     */
    public  Integer addIndexToDocument(HashMap<String, Object> param) throws McpException {
        Integer result=indexDao.addIndexToDocument(param);
        return result;
    }

    /**
     * 指标类目字典表增加
     * <p> 作 者：lsd
     *<p> 创建时间：2018/11/13
     * @return
     * @throws Exception
     */
    public  Integer addIndexToAttrsDics(HashMap<String, Object> param) throws McpException {
        Integer resultCount=0;
        Integer paramCount=0;
        List<HashMap<String, Object>> paramsList = new ArrayList<HashMap<String, Object>>();
        //需要新增的的list
        List<HashMap<String, Object>> addTypeList = new ArrayList<HashMap<String, Object>>();
        String indexId=(String) param.get("indexId");
        String attrId=(String) param.get("attrId");
        String idTypeJosn=(String) param.get("idTypeJosn");
        JSONArray array = JSONArray.fromObject(idTypeJosn);
        paramsList = JSONArray.toList(array, new HashMap<String, Object>(), new JsonConfig());
        if (null!=paramsList&&paramsList.size()>0&&null!=paramsList.get(0)&&!("null".equals(paramsList.get(0)))){
            //数据状态是已存的查询条件map
            HashMap<String, Object> existTypeParamMap=new HashMap<String, Object>();
            //数据状态是暂存的查询条件map
            HashMap<String, Object> stagingTypeParamMap=new HashMap<String, Object>();
            //数据状态是暂存的查询条件map
            HashMap<String, Object> deleteTypeParamMap=new HashMap<String, Object>();
            //需要新增的map
            HashMap<String, Object> addTypeParamMap=null;
            StringBuffer existNotDicIdBuffer = new StringBuffer();
            StringBuffer stagingNotDicIdBuffer = new StringBuffer();
            StringBuffer deleteNotDicIdBuffer = new StringBuffer();
            for (HashMap<String, Object> paramsMap:paramsList){
                String dataType=(String)paramsMap.get("dataType");
                if ("0".equals(dataType)){//数据状态是已存
                    existNotDicIdBuffer.append("'").append((String)paramsMap.get("dicId")).append("',");
                    existTypeParamMap.put("indexId",indexId);
                    existTypeParamMap.put("attrId",attrId);
                    existTypeParamMap.put("dataType","2");
                    existTypeParamMap.put("paramDataType","0");
                }else if ("1".equals(dataType)){//数据状态是暂存
                    stagingNotDicIdBuffer.append("'").append((String)paramsMap.get("dicId")).append("',");
                    stagingTypeParamMap.put("indexId",indexId);
                    stagingTypeParamMap.put("attrId",attrId);
                    stagingTypeParamMap.put("paramDataType","1");
                }else if ("2".equals(dataType)){//数据状态是删除
                    deleteNotDicIdBuffer.append("'").append((String)paramsMap.get("dicId")).append("',");
                    deleteTypeParamMap.put("indexId",indexId);
                    deleteTypeParamMap.put("attrId",attrId);
                    deleteTypeParamMap.put("dataType","0");
                    deleteTypeParamMap.put("paramDataType","2");
                }else if ("".equals(dataType)||null==dataType||"null".equals(dataType)){//添加字典
                    addTypeParamMap=new HashMap<String, Object>();
                    addTypeParamMap.put("indexId",indexId);
                    addTypeParamMap.put("attrId",attrId);
                    addTypeParamMap.put("dicId",(String)paramsMap.get("dicId"));
                    addTypeParamMap.put("dataType","1");
                    addTypeList.add(addTypeParamMap);
                }
            }

            //数据库里该指标的数据状态是暂存的数据，除传过来的字典外，其他的真删除
            if (stagingNotDicIdBuffer.length()>0){
                String  stagingNotDicIdArray= stagingNotDicIdBuffer.substring(0, stagingNotDicIdBuffer.length() - 1);
                paramCount=paramCount+1;
                stagingTypeParamMap.put("notDicIdArray",stagingNotDicIdArray);
                resultCount=resultCount+deleteIndexToAttrsDicsById(stagingTypeParamMap);
            }

           //数据库里该指标的数据状态是已存的数据，除传过来的字典外，其他的改为删除状态
            if (existNotDicIdBuffer.length()>0){
                String  existNotDicIdArray= existNotDicIdBuffer.substring(0, existNotDicIdBuffer.length() - 1);
                paramCount=paramCount+1;
                existTypeParamMap.put("notDicIdArray", existNotDicIdArray);
                resultCount=resultCount+ updateIndexToAttrsDicsIndexId(existTypeParamMap);
            }


            //数据库里该指标的数据状态是删除的数据，传过来的字典的状态修改为已存
            if (deleteNotDicIdBuffer.length()>0){
                String  deleteDicIdArray= deleteNotDicIdBuffer.substring(0, deleteNotDicIdBuffer.length() - 1);
                paramCount=paramCount+1;
                deleteTypeParamMap.put("dicIdArray", deleteDicIdArray);
                resultCount=resultCount+updateIndexToAttrsDicsIndexId(deleteTypeParamMap);
            }

            //新增的数据，数据状态为暂存
            if (null!=addTypeList&&addTypeList.size()>0&&addTypeList.get(0).size()>0){
                paramCount=paramCount+1;
                resultCount=resultCount+addIndexToAttrsDicsByList(addTypeList);
            }
        }

        if(resultCount==paramCount){
            return 1;
        }else{
            return 0;
        }

    }

    /**
     * 指标类目字典表删除根据指标主键字典主键
     * <p> 作 者：lsd
     *<p> 创建时间：2018/12/20
     * @return
     * @throws Exception
     */
    public  Integer deleteIndexToAttrsDicsById(HashMap<String, Object> param) throws McpException {
        String[] dicIdArray=new String[0];
        String dicIdString=(String) param.get("dicIdArray");
        Integer paramSize=0;
        Integer total=indexDao.getIndexToAttrsDicsTotal(param);
        Integer result=indexDao.deleteIndexToAttrsDicsById(param);
        if(null!=dicIdString&&!"".equals(dicIdString)){
            dicIdArray=dicIdString.split(",");
            paramSize=dicIdArray.length;
        }else{
            dicIdString=(String) param.get("notDicIdArray");
            dicIdArray=dicIdString.split(",");
            paramSize=total-dicIdArray.length;
        }
        if(result==paramSize){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 指标类目字典表修改数据状态
     * <p> 作 者：lsd
     *<p> 创建时间：2018/12/20
     * @return
     * @throws Exception
     */
    public  Integer addIndexToAttrsDicsByList(List<HashMap<String, Object>> paramsList) throws McpException {
        Integer result=0;
        for (HashMap<String, Object> paramsMap:paramsList){
            result=result+indexDao.addIndexToAttrsDics(paramsMap);
        }
        if(result==paramsList.size()){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 指标类目字典表修改数据状态
     * <p> 作 者：lsd
     *<p> 创建时间：2018/12/20
     * @return
     * @throws Exception
     */
    public  Integer updateIndexToAttrsDicsIndexId(HashMap<String, Object> param) throws McpException {
        String[] dicIdArray=new String[0];
        String dicIdString=(String) param.get("dicIdArray");
        Integer paramSize=0;
        Integer total=indexDao.getIndexToAttrsDicsTotal(param);
        Integer result=indexDao.updateIndexToAttrsDicsIndexId(param);
        if(null!=dicIdString&&!"".equals(dicIdString)){
            dicIdArray=dicIdString.split(",");
            paramSize=dicIdArray.length;
        }else{
            dicIdString=(String) param.get("notDicIdArray");
            dicIdArray=dicIdString.split(",");
            paramSize=total-dicIdArray.length;
        }
        if(result==paramSize){
            return 1;
        }else{
            return 0;
        }
    }

    //获得指标所在的目录名称
    private String getCategoryfullName(String categoryFullcode,String separateType){
        StringBuffer paramA = null;
        StringBuffer paramB = null;
        List<HashMap<String, Object>> categoryList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> categoryMap =new HashMap<String, Object>();
        StringBuffer categoryNameBuffer = null;
        String categoryName="";
        //获得指标所在目录的所有父目录code
        if (null != categoryFullcode && !"".equals(categoryFullcode)) {
            String[] strArray = categoryFullcode.split("_");
            paramA = new StringBuffer();
            for (int i = 0; i < strArray.length; i++) {
                paramB = new StringBuffer();
                for (int j = 0; j <= i; j++) {
                    paramB.append(strArray[j]).append("_");
                }
                paramA.append("'").append(paramB.substring(0, paramB.length() - 1)).append("',");
            }
            categoryFullcode = paramA.substring(0, paramA.length() - 1);
            //获得指标所在目录的所有父目录name
            categoryMap.put("categoryFullcode", categoryFullcode);
            categoryList = indexDao.selectCategoryList(categoryMap);
            if (null != categoryList && categoryList.size() > 0) {
                categoryNameBuffer = new StringBuffer();
                for (HashMap<String, Object> resultCategory : categoryList) {
                    if (null != resultCategory && resultCategory.size() > 0) {
                        categoryNameBuffer.append((String) resultCategory.get("categoryName")).append(separateType);
                    }
                }
                return categoryName = categoryNameBuffer.substring(0, categoryNameBuffer.length() - 1);
            }else {
                return  "";
            }

        }else{
            return  "";
        }
    }

    //获得本目录code及所有父目录code
    private String getCategoryfullcode(String categoryFullcode){
        StringBuffer paramA = null;
        StringBuffer paramB = null;
        //获得指标所在目录的所有父目录code
        if (null != categoryFullcode && !"".equals(categoryFullcode)) {
            String[] strArray = categoryFullcode.split("_");
            paramA = new StringBuffer();
            for (int i = 0; i < strArray.length; i++) {
                paramB = new StringBuffer();
                for (int j = 0; j <= i; j++) {
                    paramB.append(strArray[j]).append("_");
                }
                paramA.append("'").append(paramB.substring(0, paramB.length() - 1)).append("',");
            }
            categoryFullcode = paramA.substring(0, paramA.length() - 1);
            return categoryFullcode;
        }else{
            return  "";
        }
    }
    /**
     * 查询文件列表（分页）
     * <p> 作 者：zcs
     * <p> 创建时间：2018/12/12
     * @param params
     * @return
     */
    public PageResult getDocumentByTypeAndName(HashMap<String, Object> params) {
        List<HashMap<String,Object>> documentList=new ArrayList<HashMap<String, Object>>();
        HashMap<String,Object> pageMap=getParamMap();
        params.putAll(pageMap);
        documentList=indexDao.selectByPageTypeAndName(params);
        return new PageResult(params,documentList);
    }

    /**
     * 关键字增加
     * <p> 作 者：zcs
     * 创建时间：2018/12/12
     * @param keywordsName
     * @return
     */
    public Integer addKeywords(String keywordsName) {
        Integer result=indexDao.addKeywords(keywordsName);
        return result;
    }

    /**
     * 查询指标详情—顶部查询
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/14</p>
     * @param indexId
     * @return
     */
    public Map<String,Object> getTopDetailsById(String indexId) {
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap=indexDao.selectIndexTopInfo(indexId);
        return resultMap;
    }

    /**
     * 关系表index_to_document添加数据，状态暂存(dataType)。
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/14</p>
     * @param indexId
     * @param docId
     * @return
     */
    public Integer insertIndexToDoc(String indexId, String docId,String dataType) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("indexId",indexId);
        params.put("docId",docId);
        params.put("dataType",dataType);
        Integer result=indexDao.insertIndexToDoc(params);
        return result;
    }

    /**
     * 根据传递过来的param中的dataType判断增加，修改，删除
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/18</p>
     * @return
     * @throws McpException
     */
    public Integer addUpdateDelete(HashMap<String,Object> params) throws McpException{
        String dataType= (String) params.get("dataType");
        if(StringUtils.isNullOrEmpty(dataType)||dataType.equals("null")){
            //状态为空的，状态全是null或""，关系表添加数据，状态暂存(1)。
            dataType="1";
            params.put("dataType",dataType);
            Integer result=indexDao.insertIndexToDoc(params);
            return result;
        }
        if(dataType.equals("2")){
            //状态为已删除的，改为已存（0）
            dataType="0";
            params.put("dataType",dataType);
            Integer result=indexDao.updateIndexToDocument(params);
            return result;
        }
        if(dataType.equals("0")){
            //已存状态改为删除状态
            dataType="2";
            params.put("dataType",dataType);
            Integer result=indexDao.updateIndexToDocument(params);
            return result;
        }
        if(dataType.equals("1")){
            //暂存状态真删除
            Integer result=indexDao.deleteIndexToDocumentByDataType(params);
            return result;
        }
        return 0;
    }

    /**
     * 根据docId获取document
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/17</p>
     * @param docId
     * @return
     */
    public Map<String, Object> getFile(String docId) {
        Map<String,Object> resultMap=indexDao.getFileByDocId(docId);
        return resultMap;
    }

    /**
     * 增加或修改文件
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/21</p>
     * @param documentInfo
     * @return
     * @throws McpException
     */
    public Integer addOrUpdateDocument(DocumentInfo documentInfo) throws McpException{
        String docId=documentInfo.getDocId();
        String docSaveurl=documentInfo.getDocSaveurl();
        //获取除"//"外的第一个"/"的下标
        int split=docSaveurl.indexOf("/",docSaveurl.indexOf("//")+2);
        //得到存储到数据库中的路径
        docSaveurl=docSaveurl.substring(split+1);
        documentInfo.setDocSaveurl(docSaveurl);
        if (StringUtils.isNullOrEmpty(docId)){
            //添加文件,不传递id
            Integer result=indexDao.insertDocument(documentInfo);
            return result;
        }
        Integer result=indexDao.updateDocument(documentInfo);
        return result;
    }

    /**
     * 增加或修改指标
     * <p>作者：zcs</p>
     * <p>创建时间：2018/12/21</p>
     * @param topIndexInfo
     * @return
     */
    public Integer addOrUpdateIndex(TopIndexInfo topIndexInfo){
        String indexId= topIndexInfo.getIndexId();
        if (StringUtils.isNullOrEmpty(indexId)) {
            //增加
            Integer result = indexDao.addTopIndex(topIndexInfo);
            return result;
        }
        //修改
        Integer result = indexDao.updateTopIndex(topIndexInfo);
        return result;
    }

    /**
     * 指标信息修改
     * <p> 作 者：ycy
     *<p> 创建时间：2018/12/20
     * @return
     * @throws Exception
     */
    public  Integer updateIndex(IndexInfo indexInfo) throws McpException {
        String indexId = indexInfo.getIndexId();
        //修改参考依据的关联管理
        indexDao.updateDTByDTeAndII(indexId, DocumentUtil.DocumentStatus.STORAGE_TEMP,DocumentUtil.DocumentStatus.STORAGE_TRUE);
        //删除参考依据的关联管理
        indexDao.deleteITDByDTeAndII(indexId, DocumentUtil.DocumentStatus.DELETE);
        //保存当前字典
        String fullcode = indexInfo.getCategoryFullcode();
        if(!StringUtils.isNullOrEmpty(fullcode)){
            String fullcodes = getCategoryfullcode(fullcode);
            if(!StringUtils.isNullOrEmpty(fullcodes)){
                //先删除非本类目以及父类目下的字典
                indexDao.deleteOtherdDics(indexId, AttrsDicsUtil.AttrsDicsStatus.STORAGE_TEMP,AttrsDicsUtil.AttrsDicsStatus.STORAGE_TRUE, fullcodes);
                //修改本类目以及父类目下的字典关联状态
                indexDao.updateITADByDTeAndIIAndAI(indexId, AttrsDicsUtil.AttrsDicsStatus.STORAGE_TEMP,AttrsDicsUtil.AttrsDicsStatus.STORAGE_TRUE,fullcodes);
            }
        }
        //删除其他字典
        indexDao.deleteITADByDTeAndII(indexId,AttrsDicsUtil.AttrsDicsStatus.DELETE);
        return indexDao.updateIndex(indexInfo);
    }
    /**
     * 指标信息修改
     * <p> 作 者：ycy
     *<p> 创建时间：2018/12/20
     * @return
     * @throws Exception
     */
    public  void cancelOperation(String indexId,String categoryFullcode) throws McpException {
        //把删除状态的参考依据变为真存
        indexDao.updateDTByDTeAndII(indexId, DocumentUtil.DocumentStatus.DELETE,DocumentUtil.DocumentStatus.STORAGE_TRUE);
        //删除暂存状态的参考依据
        indexDao.deleteITDByDTeAndII(indexId, DocumentUtil.DocumentStatus.STORAGE_TEMP);
        //把删除状态的属性字典变成真存
        indexDao.updateITADByDTeAndII(indexId,AttrsDicsUtil.AttrsDicsStatus.DELETE,AttrsDicsUtil.AttrsDicsStatus.STORAGE_TRUE);
        //删除暂存暂存状态的属性字典
        indexDao.deleteITADByDTeAndII(indexId,AttrsDicsUtil.AttrsDicsStatus.STORAGE_TEMP);
    }
}
