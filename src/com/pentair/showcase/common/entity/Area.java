package com.pentair.showcase.common.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pentair.showcase.catalog.dao.BrandDao;
import com.pentair.showcase.catalog.entity.Brand;
import com.pentair.showcase.common.dao.AreaDao;

/**
 * 地区
 *
 * @author Jiangshilin(36811928@qq.com)
 */
@Entity
@Table(name = "DD_AREA")
public class Area extends IdEntity {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
