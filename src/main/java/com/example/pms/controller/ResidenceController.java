package com.example.pms.controller;

import com.example.pms.bean.PropertyRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/residence")
public class ResidenceController {

    @RequestMapping("/")
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("/index/residence");
        return mav;
    }

    @RequestMapping("/addResident")
    public ModelAndView add(PropertyRecord propertyRecord) {
        ModelAndView mav = new ModelAndView("/showProperty");

        return mav;
    }


}
