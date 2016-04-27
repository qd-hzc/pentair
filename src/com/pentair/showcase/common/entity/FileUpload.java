package com.pentair.showcase.common.entity;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 附件
 * @author Jiangshilin(36811928@qq.com)
 *
 */
@Entity
@Table(name = "FILE_UPLOAD")
public class FileUpload extends IdEntity {

	private String fileSrcName;
	private String fileDirectory;
	private String fileSubDirectory;
	private String fileSaveName;
	private String fileUrl;
	private Date uploadTime;
	private Integer deleteFlag;
	private String memo;
	private Integer prepare1;
	private String prepare2;
	private String prepare3;
	
	
	private String name;//文件名称
	private String path;//路径,相对项目根目录的路径 
	private String type;//文件类型
	private double size;//文件大小(单位：字节)	
	private int percent;//计算上传的百分比
	private double uploaded;//已经上传的大小
	public int flagUpload;//上传成功的标记
	public String speedSecond;//上传速度  kb/ms
	
	@Transient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Transient
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Transient
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Transient
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	
	@Transient
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	@Transient
	public double getUploaded() {
		return uploaded;
	}
	public void setUploaded(double uploaded) {
		this.uploaded = uploaded;
	}
	
	@Transient
	public int getFlagUpload() {
		return flagUpload;
	}
	public void setFlagUpload(int flagUpload) {
		this.flagUpload = flagUpload;
	}
	
	@Transient
	public String getSpeedSecond() {
		return speedSecond;
	}
	public void setSpeedSecond(String speedSecond) {
		this.speedSecond = speedSecond;
	}

	public String getFileSrcName() {
		return fileSrcName;
	}
	public void setFileSrcName(String fileSrcName) {
		this.fileSrcName = fileSrcName;
	}
	public String getFileSaveName() {
		return fileSaveName;
	}
	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getPrepare1() {
		return prepare1;
	}
	public void setPrepare1(Integer prepare1) {
		this.prepare1 = prepare1;
	}
	public String getPrepare2() {
		return prepare2;
	}
	public void setPrepare2(String prepare2) {
		this.prepare2 = prepare2;
	}
	public String getPrepare3() {
		return prepare3;
	}
	public void setPrepare3(String prepare3) {
		this.prepare3 = prepare3;
	}

	public String getFileDirectory() {
		return fileDirectory;
	}
	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}
	public String getFileSubDirectory() {
		return fileSubDirectory;
	}
	public void setFileSubDirectory(String fileSubDirectory) {
		this.fileSubDirectory = fileSubDirectory;
	}
}
