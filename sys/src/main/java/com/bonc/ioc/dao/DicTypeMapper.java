package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.DicType;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DicTypeMapper {

	List<DicType> getDicTypeList(@Param("pcode") String pcode) throws McpException;

	List<DicType> selectByPage(HashMap<String, Object> map)throws McpException;

	public Integer countChildrenByCode(String typeCode) throws McpException;
	/**
	 * 根據id查看字典元素信息
	 * @param typeId
	 * @return
	 * @throws McpException
	 */
	DicType findDicTypeByTypeId(@Param("typeId") String typeId)throws McpException;

	DicType findDicTypeByTypeCode(@Param("typeCode") String typeCode) throws McpException;
	
	DicType findDicTypeByTypeCodeAndSort(@Param("typePCode") String typePCode, @Param("sort") Integer sort) throws McpException;
	
	/**
	 * 保存字典分类对象
	 */
	void saveDicType(DicType dicType) throws McpException;
	/**
	 * 修改字典分类对象
	 */
	void updateDicType(DicType dicType)throws McpException;

	void deleteDicType(String[] idArr);
	/**
	 * 检查是否能进行删除操作
	 */
	List<DicType> checkCanDel(String[] idArr) throws McpException;

	Integer findMaxOrderNum() throws McpException;

	void updateOtherTypeSort(@Param("typePCode") String typePCode, @Param("sort") Integer sort) throws McpException;

	List<Map<String,Object>> getDicListByType(Map<String, Object> params) throws McpException;

}
