package com.bonc.ioc.service;

import com.bonc.ioc.common.fastdfs.FastDFSClient;
import com.bonc.ioc.common.fastdfs.FastDFSFile;
import com.bonc.ioc.core.base.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;


/**
 * 图片上传
 */
@Service
public class FileService extends BaseService{
	
	/**
	 * 上传图片
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	public String saveFile(MultipartFile multipartFile) throws IOException {
		String[] fileAbsolutePath={};
		String fileName=multipartFile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		byte[] file_buff = null;
		InputStream inputStream=multipartFile.getInputStream();
		if(inputStream!=null){
			int len1 = inputStream.available();
			file_buff = new byte[len1];
			inputStream.read(file_buff);
		}
		inputStream.close();
		FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
		try {
			fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (fileAbsolutePath==null) {//上传失败
			return null;
		}
		String path=FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
		return path;
	}
	
	/**
	 * 下载文件
	 * @param fileUrl
	 * @return
	 * @throws IOException
	 */
	public byte[] downFile(String fileUrl) throws IOException {
		fileUrl = getUrlSubString(fileUrl);
		String group = fileUrl.substring(0, fileUrl.indexOf("/"));
		String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
		return FastDFSClient.downFile(group,path);
	}
	
	private String getUrlSubString(String fileUrl){
		int i1=fileUrl.indexOf('/');
		//第一次出现','的索引
		int i2=fileUrl.indexOf('/',i1+1);
		//第二次出现
		int i3=fileUrl.indexOf('/',i2+1);
		//第三次出现
		String IfileUrl=fileUrl.substring(i3+1,fileUrl.length());
		return IfileUrl;
	}
}
