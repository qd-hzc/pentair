package com.pentair.showcase.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pentair.showcase.common.dao.FileUploadDao;
import com.pentair.showcase.common.entity.FileUpload;
import com.pentair.utils.FileUtil;
import com.pentair.utils.PropertiesReader;

@Namespace("/upload")
@InterceptorRefs( { @InterceptorRef("paramsPrepareParamsStack") })
@Results({
	@Result(name = "upload_iframe", location = "/WEB-INF/content/upload/upload_iframe.jsp"),
	@Result(type = "stream", params = { "contentType", "application/octet-stream;charset=ISO8859-1", "inputName",
                "download", "contentDisposition", "attachment;filename=${uploadFileName}", "bufferSize", "4096" })
})
public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 7462698893684460122L;
	
	private String id;
	
	@Autowired
	private FileUploadDao fileUploadDao;
	
	// 封装返回的消息提示
	private String message;

	// 封装上传文件域的属性
	private File upload;

	// 封装上传文件类型的属性
	private String uploadContentType;

	// 封装上传文件名的属性
	private String uploadFileName;
	
	// 一级目录
	private String dir1;
	
	// 二级目录
	private String dir2;
	
	// 三级目录
	private String dir3;
	
	// 返回的已上传附件列表
	private List<FileUpload> fileUploads;


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

	// 返回消息提示的setter和getter方法
	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return this.message;
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
	public String init(){
		fileUploads = fileUploadDao.findFileUploadByPackAndDirectoty(dir1, dir2, dir3);
		return "upload_iframe";
	}
	
	/**
	 * 上传文件
	 * @return
	 * @throws Exception
	 */
	public String upload(){
		
		//目标相对存储目录
		String relDir="";
		if(dir1!=null && !"".equals(dir1)){
			relDir+="/"+dir1;
		}
		if(dir2!=null && !"".equals(dir2)){
			relDir+="/"+dir2;
		}
		if(dir3!=null && !"".equals(dir3)){
			relDir+="/"+dir3;
		}		
		relDir+="/";

		//目标相对存储路径
		String relPath = relDir + uploadFileName;
				
		//目标物理存储目录
		String dstDir = savePath +relDir;
		
		//目标物理存储路径
		String dstPath = dstDir + uploadFileName;
		
		//检查同名附件是否存在
		FileUpload fileUpload = fileUploadDao.getFileUploadByPackAndName(uploadFileName, dir1, dir2, dir3);
		if(fileUpload!=null){  //如果附件存在
			message = "该附件已存在，请重新选择！";			
		}else{		
			//目标存储目录不存在则先建目录
			File dirFile = new File(dstDir);
			boolean isDir = dirFile.isDirectory();		
			if(!isDir){
				dirFile.mkdirs();
			}
	
			// 以服务器的文件保存地址和原文件名建立上传文件输出流
			FileOutputStream fos =null;
	
			// 以上传文件建立一个文件上传流
			FileInputStream fis = null;
			
			try {
				fos=new FileOutputStream(dstPath);
				fis=new FileInputStream(upload);
	
				// 将上传文件的内容写入服务器
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0){
					fos.write(buffer, 0, len);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			//存入数据库记录
			fileUpload = new FileUpload();
			fileUpload.setFileSaveName(uploadFileName);
			fileUpload.setFileUrl(relPath);
			fileUpload.setFileSrcName(dir1);
			fileUpload.setFileDirectory(dir2);
			fileUpload.setFileSubDirectory(dir3);
			fileUpload.setUploadTime(new Date());
			fileUpload.setDeleteFlag(0);
			
			fileUploadDao.save(fileUpload);
			
			this.id=fileUpload.getId();
			
			message = "文件上传成功！";
		}
		fileUploads = fileUploadDao.findFileUploadByPackAndDirectoty(dir1, dir2, dir3);
		return "upload_iframe";

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
	
	/**
	 * 删除文件
	 * @return
	 */
	public String delete(){
		FileUpload fileUpload = fileUploadDao.get(id);
		String fileUrl = savePath + fileUpload.getFileUrl();
		FileUtil.delFile(fileUrl);//删除文件夹中的文件
		fileUploadDao.delete(id);//删除数据库中的文件	
		
		message = "文件删除成功！";
		fileUploads = fileUploadDao.findFileUploadByPackAndDirectoty(dir1, dir2, dir3);
		return "upload_iframe";
	}


	public List<FileUpload> getFileUploads() {
		return fileUploads;
	}

	public void setFileUploads(List<FileUpload> fileUploads) {
		this.fileUploads = fileUploads;
	}


	public String getDir1() {
		return dir1;
	}

	public void setDir1(String dir1) {
		this.dir1 = dir1;
	}

	public String getDir2() {
		return dir2;
	}

	public void setDir2(String dir2) {
		this.dir2 = dir2;
	}

	public String getDir3() {
		return dir3;
	}

	public void setDir3(String dir3) {
		this.dir3 = dir3;
	}
}
