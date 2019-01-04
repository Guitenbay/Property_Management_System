package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.*;
import com.example.pms.dao.FeeMapper;
import com.example.pms.dao.PropertyMapper;
import org.apache.ibatis.annotations.Param;
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

    @Autowired
    FeeMapper feeMapper;

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        return new ModelAndView("index/property");
    }

    @RequestMapping("/bind")
    public ModelAndView bind(int id) {
        ModelAndView mav = new ModelAndView("property/showProperties");
        mav.addObject("residenceID", id);
        addResidenceObjects(mav, propertyMapper.listProperties(), PropertyRecord.class);
        return mav;
    }

    @RequestMapping("/addResident")
    public ModelAndView addResident(@Param("propertyRecord") PropertyRecord propertyRecord) {
        System.out.println(propertyMapper.createResident(propertyRecord.getResidentName(), propertyRecord.getResidentID(), propertyRecord.getPhoneNumber()));
        System.out.println(propertyMapper.createPropertyRecord(propertyRecord.getResidentID(), propertyRecord.getResidenceID()));
        System.out.println(propertyMapper.updateResidenceRecord(propertyRecord.getResidenceID(), "BUSY"));
        ModelAndView mav = new ModelAndView("property/showProperties");
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
        ModelAndView mav = new ModelAndView("/fee/showResidentFees");
        String str = readFile(FILE_PATH);
        JSONObject fieldOfObjs = stringToJSONObject(str, "Attrs_zh");
        Map<String, String> fieldOfObjMap = JSONObject.toJavaObject(fieldOfObjs, Map.class);
        List<Field> propertyFeeFields = Arrays.asList(PropertyFeeRecord.class.getDeclaredFields());
        List<Field> managementFeeFields = Arrays.asList(ManagementFeeRecord.class.getDeclaredFields());
        mav.addObject("FOEMap", fieldOfObjMap);
        mav.addObject("propertyFeeList", feeMapper.listProFees());
        mav.addObject("propertyFeeFields", propertyFeeFields);
        mav.addObject("managementFeeList", feeMapper.listManFees());
        mav.addObject("managementFeeFields", managementFeeFields);
        return mav;
    }

    private <T> void addResidenceObjects(ModelAndView mav, List<T> residences, Class type) {
        List<Field> fields = Arrays.asList(type.getDeclaredFields());
        String str = readFile(FILE_PATH);
        JSONObject fieldOfObjs = stringToJSONObject(str, "Attrs_zh");
        Map<String, String> fieldOfResidenceMap = JSONObject.toJavaObject(fieldOfObjs, Map.class);
        mav.addObject("residenceList", residences);
        mav.addObject("fields", fields);
        mav.addObject("FOEMap", fieldOfResidenceMap);
    }
}
