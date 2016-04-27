package com.pentair.showcase.common.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.pentair.showcase.common.entity.Privilege;


/**
 * 权限对象的泛型Hibernate Dao.
 * 
 * @author calvin
 */
@Component
public class PrivilegeDao extends HibernateDao<Privilege, String> {
}
