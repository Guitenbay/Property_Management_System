package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.ParkingSpace;
import com.example.pms.bean.PropertyRecord;
import com.example.pms.bean.Residence;
import com.example.pms.dao.PropertyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.pms.json.JsonUtils.FILE_PATH;
import static com.example.pms.json.JsonUtils.readFile;
import static com.example.pms.json.JsonUtils.stringToJSONObject;

@Controller
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    PropertyMapper propertyMapper;

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        return new ModelAndView("index/property");
    }

    @RequestMapping("/addResident")
    public ModelAndView addResident(int id) {
        ModelAndView mav = new ModelAndView("property/showProperties");
        mav.addObject("residenceID", id);
        addResidenceObjects(mav, propertyMapper.listProperties(), PropertyRecord.class);
        return mav;
    }

    @RequestMapping("/idle")
    public ModelAndView listIdleResidences() {
        ModelAndView mav = new ModelAndView("property/showIdleResidences");
        addResidenceObjects(mav, propertyMapper.listIdleResidences(), Residence.class);
        return mav;
    }

    @RequestMapping("/resident")
    public ModelAndView listResidents() {
        ModelAndView mav = new ModelAndView("property/showProperties");
        addResidenceObjects(mav, propertyMapper.listProperties(), PropertyRecord.class);
        return mav;
    }

    @RequestMapping("/fee")
    public ModelAndView listFeesOfResident() {
        ModelAndView mav = new ModelAndView("show");
        return mav;
    }

    private <T> void addResidenceObjects(ModelAndView mav, List<T> residences, Class type) {
        List<Field> fields = Arrays.asList(type.getDeclaredFields());
        String str = readFile(FILE_PATH);
        JSONObject fieldOfResidence = stringToJSONObject(str, "Residence_zh");
        Map<String, String> fieldOfResidenceMap = JSONObject.toJavaObject(fieldOfResidence, Map.class);
        mav.addObject("residenceList", residences);
        mav.addObject("fields", fields);
        mav.addObject("FOEMap", fieldOfResidenceMap);
    }
}
