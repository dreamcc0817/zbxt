package com.bonc.ioc.controller;

import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.service.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @project_name：zhlq-file
 * @package_name：com.bonc.ioc.controller
 * @describe：***
 * @creater lyh
 * @creat_time 2018/10/31 17:38 
 * @changer   ***  
 * @change_time 2018/10/31 17:38 
 * @remark   ***
 * @version V0.1
 */
@RestController
public class FileController {
	
	@Resource
	FileService fileService;
	
	@PostMapping("/upload")
	@ApiOperation("文件上传")
	public AppReply upload(@ApiParam(value = "文件",required = true)
	                         @RequestParam(value = "file",required = true) MultipartFile file
	                         ) throws Exception{
		    Map map = new HashMap();
		    String url = fileService.saveFile(file);
			if(url==null){
				return AppReply.error("上传文件失败");
			}
			map.put("url",url);
			return AppReply.success(map);
	}
	
	@GetMapping("/download")
	public void downloadFile(@RequestParam(value = "fileUrl",required = true) String fileUrl, HttpServletResponse response) throws IOException {
		byte[] bytes = fileService.downFile(fileUrl);
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/")+1);
		//response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("sb.jpg", "UTF-8"));
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
