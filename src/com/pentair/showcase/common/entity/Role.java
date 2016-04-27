package com.pentair.showcase.common.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;

/**
 * 角色.
 * 
 * @author calvin
 */
@Entity
@Table(name = "SS_ROLE")
public class Role extends IdEntity {

	private String name;
	private String englishName;
	private String shortName;
	
	private List<Privilege> privileges = Lists.newArrayList(); //有序的关联对象集合
	private List<User> users = Lists.newArrayList(); //有序的关联对象集合
	
	@Column(nullable = false, unique = true)
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@Column(nullable = false, unique = true)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	//多对多定义
	@ManyToMany
	//中间表定义,表名采用默认命名规则
	@JoinTable(name = "SS_ROLE_PRIVILEGE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PRIVILEGE_ID") })
	//Fetch策略定义
	@Fetch(FetchMode.SUBSELECT)
	//集合按id排序
	@OrderBy("id ASC")
	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	//多对多定义
	@ManyToMany
	//中间表定义,表名采用默认命名规则
	@JoinTable(name = "SS_USER_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	//Fetch策略定义
	@Fetch(FetchMode.SUBSELECT)
	//集合按id排序
	@OrderBy("id ASC")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
