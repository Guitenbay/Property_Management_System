package com.example.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("equipment")
public class EquipmentController {

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        return new ModelAndView("index/equipment");
    }
}
