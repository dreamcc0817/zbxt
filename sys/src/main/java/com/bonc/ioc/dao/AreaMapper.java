package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.DicArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface AreaMapper {

	public List<DicArea> selectByPage(HashMap<String, Object> paramMap) throws McpException;

	public Integer countChildrenByCode(String areaCode) throws McpException;

	public DicArea findById(@Param(value = "id") String id)throws McpException;

	public List<String> getAccByAreaId(String areaId) throws McpException;
	 /**
	  * 修改
	 * @param area
	  */
	public void update(DicArea area) throws McpException;

	public List<DicArea> getDicAreaList(@Param("pcode") String pcode)throws McpException;

	/**
	 * 保存行政区划注册
	 */
	public void save(DicArea dicArea) throws McpException;

	public void delete(String[] idArr) throws McpException;
	
	/**
	 * 检查是否能进行删除操作
	 */
	public List<DicArea> checkCanDel(String[] idArr) throws McpException;
	
	/**
	 * 根据areacode查询
	 * @param areaCode
	 * @param areaNameF
	 * @return
	 * @throws McpException
	 */
	public DicArea findDicAreaByAreaCode(@Param("areaCode") String areaCode, String areaNameF)throws McpException;

	/**查询行政区划列表
	 *
	 * @return
	 */
	//public List<PageData> findArea();

	public Integer findMaxOrderNum()throws McpException;

	void updateOtherAreaSort(@Param("areaPCode") String areaPCode, @Param("sort") Integer sort) throws McpException;
	
	DicArea findDicAreaByAreaCodeAndSort(@Param("areaPCode") String areaPCode, @Param("sort") Integer sort) throws McpException;

}
