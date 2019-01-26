package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.DicElement;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DicElementMapper {
    public List<DicElement> selectByPage(HashMap<String, Object> paramMap) throws McpException;
    
    public List<DicElement> findForList(String TypeCode) throws McpException;
    
    //public void delete(String[] ids);

	//DicElement findById(@Param(value="id")String id)throws McpException;

	/**
	 * 根據id查看字典元素信息
	 * @param elemId
	 * @return
	 * @throws McpException
	 */
	public DicElement getDicElementByElemId(@Param("elemId") String elemId) throws McpException;
	
	public DicElement findDicElementByElemCode(@Param("elemCode") String elemCode) throws McpException;
	
	public void saveDicElement(DicElement dicElement) throws McpException;
	
	public void updateDicElement(DicElement dicElement) throws McpException;
	
	public void deleteDicElement(String[] idArr) throws McpException;
	
	public List<DicElement> getDicEleListByTypeCode(@Param("typeCode") String typeCode)throws McpException;
	
	public Integer findMaxOrderNum()throws McpException;
	
	public void updateOtherEleSort(Integer sort)throws McpException;
}