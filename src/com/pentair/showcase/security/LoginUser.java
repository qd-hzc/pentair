package com.pentair.showcase.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.pentair.showcase.common.entity.Role;

/**
 * 扩展SpringSecurity的WebAuthenticationDetails类, 增加登录时间属性和角色属性.
 * 
 * @author calvin
 */
public class LoginUser extends User {

	private static final long serialVersionUID = 1919464185097508773L;

	private String id;
	
	private String name;
		
	private Date loginTime;

	private List<Role> roles;


	public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
