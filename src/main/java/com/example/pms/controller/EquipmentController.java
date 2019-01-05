package com.example.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("equipment")
public class EquipmentController {

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("index/equipment");
        mav.addObject("title", "设备管理");
        return mav;
    }
}
