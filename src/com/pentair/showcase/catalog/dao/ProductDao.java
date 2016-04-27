package com.pentair.showcase.catalog.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.pentair.showcase.catalog.entity.Product;

@Component
public class ProductDao extends HibernateDao<Product, String> {
}
