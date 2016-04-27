package com.pentair.showcase.common.dao;

import org.springframework.stereotype.Component;
import com.pentair.showcase.common.entity.Role;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 角色对象的泛型Hibernate Dao.
 * 
 * @author calvin
 */
@Component
public class RoleDao extends HibernateDao<Role, String> {
}
