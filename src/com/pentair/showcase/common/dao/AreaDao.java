package com.pentair.showcase.common.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.pentair.showcase.catalog.entity.Brand;
import com.pentair.showcase.common.entity.Area;
import com.pentair.showcase.common.entity.Subject;

@Component
public class AreaDao extends HibernateDao<Area, String> {

}
