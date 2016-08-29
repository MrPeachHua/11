package com.boxiang.share.sample.student.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.sample.student.po.Student;

public interface IStudentService {
	Student queryById(int stu_id);

	Page<Student> queryListPage(Page<Student> page);

	void insert(Student student);
	
	void batchUpdate(List<Student> studentList);

	void dynamicCreateTmpTable();
}
