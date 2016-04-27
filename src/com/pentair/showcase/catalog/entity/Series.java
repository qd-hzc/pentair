package com.pentair.showcase.catalog.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import com.google.common.collect.Lists;
import com.pentair.showcase.common.entity.IdEntity;
import com.pentair.showcase.common.entity.User;

/**
 * 产品系列
 * @author Jiangshilin(36811928@qq.com)
 *
 */
@Entity
@Table(name = "CATALOG_SERIES")
public class Series extends IdEntity {
	private String name;//名称
	private String code;//四班号
	private int sn;//序列号
	private String shortName;
	
	private Integer version;
	
	private Brand brand;//对应的品牌

	private User ownerPM;//产品经理
	private User ownerDM;//设计工程部经理
	private User ownerCE;//报价工程师

	private Float targetProfit;
	private Float discountRate;

	private List<Product> products = Lists.newArrayList();//对应的产品列表
	
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
	
	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pm_id")
	public User getOwnerPM() {
		return ownerPM;
	}

	public void setOwnerPM(User ownerPM) {
		this.ownerPM = ownerPM;
	}

	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dm_id")
	public User getOwnerDM() {
		return ownerDM;
	}

	public void setOwnerDM(User ownerDM) {
		this.ownerDM = ownerDM;
	}
	
	//与用户的多对一映射
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ce_id")
	public User getOwnerCE() {
		return ownerCE;
	}

	public void setOwnerCE(User ownerCE) {
		this.ownerCE = ownerCE;
	}
	
	//与品牌的多对一关系
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	//与产品的一对多关系
	@OneToMany(mappedBy = "series", fetch = FetchType.LAZY)
	@OrderBy(value = "name")
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Float getTargetProfit() {
		return targetProfit;
	}

	public void setTargetProfit(Float targetProfit) {
		this.targetProfit = targetProfit;
	}

	public Float getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Float discountRate) {
		this.discountRate = discountRate;
	}
	
}
