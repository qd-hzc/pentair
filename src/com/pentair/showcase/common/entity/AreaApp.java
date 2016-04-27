package com.pentair.showcase.common.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.pentair.showcase.catalog.dao.BrandDao;
import com.pentair.showcase.catalog.entity.Brand;
import com.pentair.showcase.common.dao.AreaDao;

/**
 * 地区
 * @author Jiangshilin(36811928@qq.com)
 *
 */
@Entity
@Table(name = "DD_AREA_APP")
public class AreaApp extends IdEntity {

	private Area area;//地区
	private Brand brand;//品牌
	private User ownerApp;//应用工程师
	private User ownerCS;//客户服务专员
	private Integer version;

	//Hibernate自动维护的Version字段
	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "area_id")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
		
	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "app_id")
	public User getOwnerApp() {
		return ownerApp;
	}

	public void setOwnerApp(User ownerApp) {
		this.ownerApp = ownerApp;
	}

	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cs_id")
	public User getOwnerCs() {
		return ownerCS;
	}

	public void setOwnerCs(User ownerCS) {
		this.ownerCS = ownerCS;
	}

	
}
