package com.bonc.ioc.controller;

import com.bonc.ioc.constraint.IsRealPositiveInteger;
import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouyu on 2018/11/6.
 */
@RestController
//@RequestMapping("/search")
@Validated
public class SearchController {

	@Autowired
	private SearchService service;

	@GetMapping(value = "/findByKeyWords")
	@ApiOperation(value = "指标字典搜索")
	@AppAuthAnnotation
	public AppReply selectNewsByPage(@ApiParam(value = "查询关键字", required = true) String keywords,
	                                 @ApiParam(value = "分页，当前页数", required = true) @IsRealPositiveInteger @NotBlank(message = "当前页数参数缺省！") @Min(1) String pageNumber,
	                                 @ApiParam(value = "分页，每页数量", required = true) @IsRealPositiveInteger @NotBlank(message = "每页数量参数缺省！") @Min(1) String pageSize,
	                                 @ApiParam(value = "类目编码", required = false) String categroyFullcode,
	                                 @ApiParam(value = "只看涉密", required = false) String isSm,
	                                 @ApiParam(value = "属性Id", required = false) String attrId
	) throws McpException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("keywords", keywords);
		params.put("pageNumber", pageNumber);
		params.put("pageSize", pageSize);
		params.put("categroyFullcode", categroyFullcode);
		params.put("isSm", isSm);
		params.put("attrId", attrId);
		return AppReply.success(service.selectByPageFindByKeyWords(params));
	}

	@GetMapping(value = "/getCategoryProps")
	@ApiOperation(value = "根据类目查询属性")
	@AppAuthAnnotation
	public AppReply getCategoryProps(@ApiParam(value = "类目id", required = true) @NotBlank(message = "类目id参数缺省！") String categoryId) throws McpException {
		List<Map<String, Object>> list = service.getCategoryProps(categoryId);
		return AppReply.success(list);
	}

	@GetMapping(value = "/getIndexsForEcharts")
	@ApiOperation(value = "指标折线图")
	@AppAuthAnnotation
	public AppReply getCategoryProps() throws McpException {
		return AppReply.success(service.getIndexsForEcharts());
	}

	@GetMapping(value = "/getAllTopLevelCategory")
	@ApiOperation(value = "获取顶层类目")
	@AppAuthAnnotation
	public AppReply getAllTopLevelCategory() throws McpException {
		return AppReply.success(service.getAllTopLevelCategory());
	}
}