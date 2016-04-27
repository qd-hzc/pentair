package com.pentair.showcase.catalog.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.pentair.showcase.common.entity.IdEntity;

/**
 * 产品
 * @author Jiangshilin(36811928@qq.com)
 *
 */
@Entity
@Table(name = "CATALOG_PRODUCT")
public class Product extends IdEntity {
	private String name;//名称
	private String code;//编码
	private Integer version;
	
	private Series series;//对应的产品系列
	
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
	
	//与产品系列的多对一关系
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id")
	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

}
