package com.boxiang.share.sample.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.sample.student.dao.IStudentDao;
import com.boxiang.share.sample.student.po.Student;
import com.boxiang.share.sample.student.service.IStudentService;

/**
 * 学生示例Service接口实现
 * @author junior.pan
 * @date 2016-1-2
 */
@DataSource(DataSourceEnum.MASTER)
@Service("studentService")
public class StudentServiceImpl implements IStudentService {
	@Resource(name="studentDao")
	private IStudentDao studentDao;
	
	/**
	 * 根据学生主键ID查询学生信息
	 * @param 	stu_id		学生主键ID	
	 * @return	Student		学生信息
	 */
	@Override
	public Student queryById(int stu_id) {
		return studentDao.queryById(stu_id);
	}
	
	/**
	 * 查询学生分页列表
	 * @param 	page			分页参数信息
	 * @return	Page<Student>	学生分页列表
	 */
	@Override
	public Page<Student> queryListPage(Page<Student> page) {
		page.setResultList(studentDao.queryListPage(page));
		return page;
	}
	
	/**
	 * 保存学生信息
	 * @param student		学生信息
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void insert(Student student) {
		studentDao.insert(student);
	}
	
	/**
	 * 批量修改学生信息
	 * @param studentList	学生信息列表
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Student> studentList) {
		studentDao.batchUpdate(studentList);
	}

	@Override
	public void dynamicCreateTmpTable() {
		//studentDao.dynamicCreateTmpTable("create global temporary table tb(id varchar(19)) on commit delete rows");
		//studentDao.dynamicCreateTmpTable("insert into tb values('1')");
		//studentDao.dynamicCreateTmpTable("insert into tb values('1')");
		//studentDao.selectTmpTable();
		//studentDao.dynamicCreateTmpTable("drop table tb");
	}
}
