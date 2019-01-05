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

    @RequestMapping("/inspect")
    public ModelAndView inspect() {
        ModelAndView mav = new ModelAndView("index/inspect");
        mav.addObject("title", "排查管理");
        return mav;
    }

    @RequestMapping("/maintenance")
    public ModelAndView maintenance() {
        ModelAndView mav = new ModelAndView("index/maintenance");
        mav.addObject("title", "维修管理");
        return mav;
    }

    @RequestMapping("/repairRecord")
    public ModelAndView listRepairRecord() {
        ModelAndView mav = new ModelAndView("index/repairRecord");
        mav.addObject("title", "报修报表");
        return mav;
    }

}
