package com.boxiang.share.sample.student.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.sample.student.po.Student;

/**
 * 学生示例Dao接口 
 * @author junior.pan
 * @date 2016-1-2
 */
public interface IStudentDao extends IMybatisBaseDao<Student>
{	
	
	/**
	 * 批量修改学生信息
	 * @param studentList	学生信息列表
	 */
	void batchUpdate(List<Student> studentList);
	
	void dynamicCreateTmpTable(String sql);
	
	List<String> selectTmpTable();
}
