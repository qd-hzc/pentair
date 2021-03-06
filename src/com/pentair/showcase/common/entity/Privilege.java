package com.pentair.showcase.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 权限.
 *
 * @author James
 */
@Entity
@Table(name = "SS_PRIVILEGE")
public class Privilege extends IdEntity {

    private String name;


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