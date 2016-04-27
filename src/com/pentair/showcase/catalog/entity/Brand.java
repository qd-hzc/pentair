package com.pentair.showcase.catalog.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.pentair.showcase.common.entity.IdEntity;
import com.pentair.showcase.common.entity.User;

/**
 * 品牌
 * @author Jiangshilin(36811928@qq.com)
 *
 */
@Entity
@Table(name = "CATALOG_BRAND")
public class Brand extends IdEntity {


	private String name;//名称
	private String code;//编码
	private String logo;//logo url
	private Integer version;
	
	//与用户的多对一映射
	private User ownerAM;//APP Manager	
	private User ownerCM;//CE Manager
	private User ownerMM;//Market Manager
	private User ownerMD;//Market Director
	private User ownerGM;//General Manager
	private User ownerCSM;//General Manager

	private List<Series> series = Lists.newArrayList();//对应的产品系列列表
	//private List<Product> products = Lists.newArrayList();//对应的产品列表

	//Hibernate自动维护的Version字段
	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "am_id")
	public User getOwnerAM() {
		return ownerAM;
	}

	public void setOwnerAM(User ownerAM) {
		this.ownerAM = ownerAM;
	}

	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cm_id")
	public User getOwnerCM() {
		return ownerCM;
	}

	public void setOwnerCM(User ownerCM) {
		this.ownerCM = ownerCM;
	}

	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "csm_id")
	public User getOwnerCSM() {
		return ownerCSM;
	}

	public void setOwnerCSM(User ownerCSM) {
		this.ownerCSM = ownerCSM;
	}

	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mm_id")
	public User getOwnerMM() {
		return ownerMM;
	}

	public void setOwnerMM(User ownerMM) {
		this.ownerMM = ownerMM;
	}

	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "md_id")
	public User getOwnerMD() {
		return ownerMD;
	}

	public void setOwnerMD(User ownerMD) {
		this.ownerMD = ownerMD;
	}

	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gm_id")
	public User getOwnerGM() {
		return ownerGM;
	}

	public void setOwnerGM(User ownerGM) {
		this.ownerGM = ownerGM;
	}

	//与产品系列的一对多关系
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	@OrderBy(value = "name")
	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
