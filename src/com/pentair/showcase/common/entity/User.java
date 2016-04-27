package com.pentair.showcase.common.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.pentair.showcase.orm.hibernate.AuditableEntity;
import org.springside.modules.utils.reflection.ConvertUtils;

import com.google.common.collect.Lists;

/**
 * 用户.
 * 
 * @author calvin
 */
@Entity
@Table(name = "SS_USER")
public class User extends AuditableEntity {
	private String loginName;
	private String plainPassword;
	private String shaPassword;
	private String name;
	private String email;
	private String status="启用";
	private Integer version;
	private String englishName;
	private String shortName;
	private String mac;

	private Area area;
	private User asm;
	
	private List<Role> roles = Lists.newArrayList(); //有序的关联对象集合

	//Hibernate自动维护的Version字段
	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(nullable = false, unique = true)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 明文密码.
	 */
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	/**
	 * SHA1散列密码.
	 */
	public String getShaPassword() {
		return shaPassword;
	}

	public void setShaPassword(String shaPassword) {
		this.shaPassword = shaPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	//多对多定义
	@ManyToMany
	//中间表定义,表名采用默认命名规则
	@JoinTable(name = "SS_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	//Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	//集合按id排序
	@OrderBy("id ASC")
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	//与地区的多对一关系
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "area")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Transient
	@JsonIgnore
	public String getRoleNames() {
		return ConvertUtils.convertElementPropertyToString(roles, "name", ",");
	}
	
	@Transient
	@JsonIgnore
	public String getRoleShortNames() {
		return ConvertUtils.convertElementPropertyToString(roles, "shortName", ",");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	//与地区的多对一关系
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "asm")
	public User getAsm() {
		return asm;
	}

	public void setAsm(User asm) {
		this.asm = asm;
	}
}