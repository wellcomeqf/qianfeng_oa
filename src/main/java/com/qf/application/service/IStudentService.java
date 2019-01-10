package com.qf.application.service;

import com.qf.application.model.Student;

import java.util.List;
import java.util.Map;

/**
 * @Author:cyj
 * @Data:2019/1/9
 * @Version:1.0
 */
public interface IStudentService {

    public Student queryById(int id);

    List<Student> getList();

    int getTotal();

    List<Student> getPage(Map<String, Object> paramMap);

}
