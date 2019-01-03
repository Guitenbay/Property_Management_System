package com.example.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        ModelAndView modelAndView = new ModelAndView("index/index");
        modelAndView.addObject("title", "物业管理系统");
        modelAndView.addObject("desc", "了解各个小区物业管理团队经营情况");
        return modelAndView;
    }
}
