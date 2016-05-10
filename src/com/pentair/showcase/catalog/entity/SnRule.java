package com.pentair.showcase.catalog.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.pentair.showcase.common.entity.IdEntity;

/**
 * 编码规则
 *
 * @author Yang Jianfeng
 */
@Entity
@Table(name = "CATALOG_SNRULE")
public class SnRule extends IdEntity {
    private Integer version;
    private String name;// 名称
    private String s1;// s1
    private String s2;// s2
    private String s3;// s3
    private String s4;// s4
    private String s5;// s5
    private String s6;// s6
    private String s7;// s7
    private String s8;// s8
    private String s9;// s9
    private String s10;// s10
    private String d1;// d1
    private String d2;// d2
    private String d3;// d3
    private String d4;// d4
    private String d5;// d5
    private String d6;// d6
    private String d7;// d7
    private String d8;// d8
    private String d9;// d9
    private String d10;// d10
    private String i1;// i1
    private String i2;// i2
    private String i3;// i3
    private String i4;// i4
    private String i5;// i5
    private String i6;// i6
    private String i7;// i7
    private String i8;// i8
    private String i9;// i9
    private String i10;// i10
    private String note;// i10

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getI1() {
        return i1;
    }

    public void setI1(String i1) {
        this.i1 = i1;
    }

    public String getI2() {
        return i2;
    }

    public void setI2(String i2) {
        this.i2 = i2;
    }

    public String getI3() {
        return i3;
    }

    public void setI3(String i3) {
        this.i3 = i3;
    }

    public String getI4() {
        return i4;
    }

    public void setI4(String i4) {
        this.i4 = i4;
    }

    public String getI5() {
        return i5;
    }

    public void setI5(String i5) {
        this.i5 = i5;
    }

    public String getI6() {
        return i6;
    }

    public void setI6(String i6) {
        this.i6 = i6;
    }

    public String getI7() {
        return i7;
    }

    public void setI7(String i7) {
        this.i7 = i7;
    }

    public String getI8() {
        return i8;
    }

    public void setI8(String i8) {
        this.i8 = i8;
    }

    public String getI9() {
        return i9;
    }

    public void setI9(String i9) {
        this.i9 = i9;
    }

    public String getI10() {
        return i10;
    }

    public void setI10(String i10) {
        this.i10 = i10;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getD4() {
        return d4;
    }

    public void setD4(String d4) {
        this.d4 = d4;
    }

    public String getD5() {
        return d5;
    }

    public void setD5(String d5) {
        this.d5 = d5;
    }

    public String getD6() {
        return d6;
    }

    public void setD6(String d6) {
        this.d6 = d6;
    }

    public String getD7() {
        return d7;
    }

    public void setD7(String d7) {
        this.d7 = d7;
    }

    public String getD8() {
        return d8;
    }

    public void setD8(String d8) {
        this.d8 = d8;
    }

    public String getD9() {
        return d9;
    }

    public void setD9(String d9) {
        this.d9 = d9;
    }

    public String getD10() {
        return d10;
    }

    public void setD10(String d10) {
        this.d10 = d10;
    }

    // Hibernate自动维护的Version字段
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public String getS8() {
        return s8;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }

    public String getS9() {
        return s9;
    }

    public void setS9(String s9) {
        this.s9 = s9;
    }

    public String getS10() {
        return s10;
    }

    public void setS10(String s10) {
        this.s10 = s10;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
