package com.qf.application.controller;

import com.qf.application.model.Student;
import com.qf.application.service.IStudentService;
import com.qf.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author:cyj
 * @Data:2019/1/9
 * @Version:1.0
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Autowired
    private IStudentService iStudentService;

    /**
     * 查询测试
     */
    @RequestMapping("/getList")
    public ModelAndView queryById(){
        List<Student> studentList = iStudentService.getList();
        int total = iStudentService.getTotal();
        ModelAndView modelAndView = new ModelAndView("/student/StudentMana");
        modelAndView.addObject("studentList",studentList);
        modelAndView.addObject("total",total);

        return modelAndView;
    }

    @RequestMapping("/getPageNumber")
    public ModelAndView getPageNumber(HttpServletRequest request){
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int total = Integer.parseInt(request.getParameter("total"));
        int pageStart = Integer.parseInt(request.getParameter("pageStart"));
        ModelAndView modelAndView = new ModelAndView("student/orgPageNumber");
        return this.getPageNumberInfo(total,pageStart,pageSize,modelAndView);
    }

}
