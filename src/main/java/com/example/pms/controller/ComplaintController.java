package com.example.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/complaint")
public class ComplaintController {

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("index/complaint");
        mav.addObject("title", "投诉意见管理");
        return mav;
    }
}
