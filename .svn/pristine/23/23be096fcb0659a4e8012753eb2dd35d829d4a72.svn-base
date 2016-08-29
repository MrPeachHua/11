package com.boxiang.share.sample.student.po;

import java.io.Serializable;
import java.util.Date;

import com.boxiang.share.utils.json.DateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Student implements Serializable {
	private static final long serialVersionUID = 3569072097635391009L;

	/** 学生主键ID */
	private int stuId;
	
	/** 学生姓名 */
	private String stuName;
	
	private Integer age;

	@JsonSerialize(using=DateJsonSerializer.class)
	private Date createDate;

	/** 学生主键ID */
	public int getStuId() {
		return stuId;
	}

	/** 学生主键ID */
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	/** 学生姓名 */
	public String getStuName() {
		return stuName;
	}

	/** 学生姓名 */
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
