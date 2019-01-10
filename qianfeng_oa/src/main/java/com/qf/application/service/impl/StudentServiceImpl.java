package com.qf.application.service.impl;

import com.qf.application.dao.StudentMapper;
import com.qf.application.model.Student;
import com.qf.application.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:cyj
 * @Data:2019/1/9
 * @Version:1.0
 */
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student queryById(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Student> getList() {
        return studentMapper.getList();
    }

    @Override
    public int getTotal() {
        return studentMapper.getTotal();
    }
}
