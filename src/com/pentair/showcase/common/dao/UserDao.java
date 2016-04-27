package com.pentair.showcase.common.dao;

import java.util.*;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import com.pentair.showcase.common.entity.User;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 用户对象的泛型Hibernate Dao.
 * 
 * @author calvin
 */
@Component
public class UserDao extends HibernateDao<User, String> {

	private static final String QUERY_USER_WITH_ROLE = "select u from User u left join fetch u.roles order by u.name";
	private static final String COUNT_USERS = "select count(u) from User u";
	private static final String DISABLE_USERS = "update User u set u.status='disabled' where id in(:ids)";
	private static final String QUERY_USER_WITH_ROLE1 = "select u from User u inner join Role r where order by u.name";
	
	/**
	 * 批量修改用户状态.
	 */
	public void disableUsers(List<String> ids) {
		Map<String, List<String>> map = Collections.singletonMap("ids", ids);
		batchExecute(UserDao.DISABLE_USERS, map);
	}

	/**
	 * 使用 HQL 预加载lazy init的List<Role>,用DISTINCE_ROOT_ENTITY排除重复数据.
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUserWithRoleByDistinctHql() {
		Query query = createQuery(QUERY_USER_WITH_ROLE);
		return distinct(query).list();
	}

	/**
	 * 使用Criteria 预加载lazy init的List<Role>, 用DISTINCE_ROOT_ENTITY排除重复数据.
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUserWithRolesByDistinctCriteria() {
		Criteria criteria = createCriteria().setFetchMode("roleList", FetchMode.JOIN);
		return distinct(criteria).list();
	}

	/**
	 * 统计用户数.
	 */
	public Long getUserCount() {
		return findUnique(UserDao.COUNT_USERS);
	}

	/**
	 * 初始化User的延迟加载关联roleList.
	 */
	public void initUser(User user) {
		initProxyObject(user.getRoles());
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersByRoleName(String roleName) {
		List<User> users2=new ArrayList<User>();		
		List<User> users=this.getAll("name", true);
		for(User user:users){
			if(user.getRoleShortNames().indexOf(roleName)>=0){
				users2.add(user);
			}
		}
		return users2;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersByRoleNameAndArea(String roleName,String area) {
		List<User> users2=new ArrayList<User>();		
		List<User> users=this.getAll("name", true);
		for(User user:users){
			if(user.getRoleShortNames().indexOf(roleName)>=0&&area.equals(user.getArea().getId())){
				users2.add(user);
			}
		}
		return users2;
	}
	
	public User getUserById(String id) {
		List<User> users=this.getAll("name", true);
		for(User user:users){
			if(user.getId().equals(id)){
				return user;
			}
		}
		return null;
	}
}
