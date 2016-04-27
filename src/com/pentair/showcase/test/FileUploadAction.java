package com.pentair.showcase.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pentair.showcase.common.dao.FileUploadDao;
import com.pentair.showcase.common.entity.FileUpload;
import com.pentair.utils.PropertiesReader;

@Namespace("/testupload")
@InterceptorRefs( { @InterceptorRef("paramsPrepareParamsStack") })
@Results({ 
	@Result(name = "done", location = "/js/testupload.jsp"),
	@Result(type = "stream", params = { "contentType", "application/octet-stream;charset=ISO8859-1", "inputName",
                "download", "contentDisposition", "attachment;filename=${uploadFileName}", "bufferSize", "4096" })
})
public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = -8207683933115991817L;

	private String id;
	
	@Autowired
	private FileUploadDao fileUploadDao;
	
	// 封装文件标题请求参数的属性
	private String title;

	// 封装上传文件域的属性
	private File upload;

	// 封装上传文件类型的属性
	private String uploadContentType;

	// 封装上传文件名的属性
	private String uploadFileName;

	// 接受依赖注入的属性
	private String savePath=PropertiesReader.getIntance().getProperty("upload.root_path");

	// 接受依赖注入的方法
	public void setSavePath(String value){
		this.savePath = value;
	}

	// 返回上传文件的保存位置
	public String getSavePath(){
		return this.savePath;
	}

	// 文件标题的setter和getter方法
	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	// 上传文件对应文件内容的setter和getter方法

	public void setUpload(File upload){
		this.upload = upload;
	}

	public File getUpload(){
		return this.upload;
	}

	// 上传文件的文件类型的setter和getter方法
	public void setUploadContentType(String uploadContentType){
		this.uploadContentType = uploadContentType;
	}

	public String getUploadContentType(){
		return this.uploadContentType;
	}

	// 上传文件的文件名的setter和getter方法
	public void setUploadFileName(String uploadFileName){
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileName(){
		return this.uploadFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String upload() throws Exception{
		
		//目标相对存储目录
		String relDir="/test/";

		//目标相对存储路径
		String relPath = relDir + uploadFileName;
				
		//目标物理存储目录
		String dstDir = savePath +relDir;
		
		//目标物理存储路径
		String dstPath = dstDir + uploadFileName;
		
		
		//目标存储目录不存在则先建目录
		File dirFile = new File(dstDir);
		boolean isDir = dirFile.isDirectory();		
		if(!isDir){
			dirFile.mkdirs();
		}

		// 以服务器的文件保存地址和原文件名建立上传文件输出流
		FileOutputStream fos = new FileOutputStream(dstPath);

		// 以上传文件建立一个文件上传流
		FileInputStream fis = new FileInputStream(upload);

		// 将上传文件的内容写入服务器
		byte[] buffer = new byte[1024];

		int len = 0;

		while ((len = fis.read(buffer)) > 0){
			fos.write(buffer, 0, len);
		}
		fis.close();
		fos.close();
		
		//存入数据库
		FileUpload fileUpload = new FileUpload();
		fileUpload.setFileSaveName(uploadFileName);
		fileUpload.setFileSrcName(relDir);
		fileUpload.setFileUrl(relPath);
		fileUpload.setUploadTime(new Date());
		fileUpload.setDeleteFlag(0);
		
		fileUploadDao.save(fileUpload);
		
		this.id=fileUpload.getId();

		return "done";

	}
	
	/**
	 * 下载文件
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public InputStream getDownload() throws UnsupportedEncodingException, FileNotFoundException {
		FileUpload fileUpload=fileUploadDao.get(id);
		uploadFileName= URLEncoder.encode(fileUpload.getFileSaveName(), "UTF-8");
		String fileUrl = savePath + fileUpload.getFileUrl();
		File file = new File(fileUrl);
		InputStream is = new FileInputStream(file);

		return is;
	}
}