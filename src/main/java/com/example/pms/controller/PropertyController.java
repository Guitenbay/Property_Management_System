package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.*;
import com.example.pms.dao.FeeMapper;
import com.example.pms.dao.PropertyMapper;
import com.example.pms.service.PropertyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.*;

import static com.example.pms.json.JsonUtils.FILE_PATH;
import static com.example.pms.json.JsonUtils.readFile;
import static com.example.pms.json.JsonUtils.stringToJSONObject;

@Controller
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private FeeMapper feeMapper;
    private String str = readFile(FILE_PATH);
    private JSONObject fieldOfObjs = stringToJSONObject(str, "Attrs_zh");
    private Map<String, String> fieldOfObjMap = JSONObject.toJavaObject(fieldOfObjs, Map.class);

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("index/property");
        mav.addObject("title", "房屋住户管理");
        return mav;
    }

    @RequestMapping("/bind")
    public ModelAndView bind(int id) {
        ModelAndView mav = new ModelAndView("property/showProperties");
        mav.addObject("residenceID", id);
        addResidenceObjects(mav, propertyMapper.listProperties(), PropertyRecord.class);
        mav.addObject("title", "房产信息");
        return mav;
    }

    @RequestMapping("/addResident")
    public ModelAndView addResident(@Param("propertyRecord") PropertyRecord propertyRecord) {
        ModelAndView mav = new ModelAndView("property/showProperties");
        propertyService.addResident(propertyRecord.getResidenceID(), propertyRecord.getResidentID(),
                propertyRecord.getResidentName(), propertyRecord.getPhoneNumber());
        addResidenceObjects(mav, propertyMapper.listProperties(), PropertyRecord.class);
        mav.addObject("title", "房产信息");
        return mav;
    }

    @RequestMapping("/idle")
    public ModelAndView listIdleResidences() {
        ModelAndView mav = new ModelAndView("property/showIdleResidences");
        addResidenceObjects(mav, propertyMapper.listIdleResidences(), Residence.class);
        mav.addObject("title", "闲置房屋信息");
        return mav;
    }

    @RequestMapping("/resident")
    public ModelAndView listResidents() {
        ModelAndView mav = new ModelAndView("property/showProperties");
        addResidenceObjects(mav, propertyMapper.listProperties(), PropertyRecord.class);
        mav.addObject("title", "房产信息");
        return mav;
    }

    @RequestMapping("/fee")
    public ModelAndView listFeesOfResident() {
        ModelAndView mav = new ModelAndView("fee/showResidentFees");
        List<Field> propertyFeeFields = Arrays.asList(PropertyFeeRecord.class.getDeclaredFields());
        List<Field> managementFeeFields = Arrays.asList(ManagementFeeRecord.class.getDeclaredFields());
        mav.addObject("FOEMap", fieldOfObjMap);
        mav.addObject("propertyFeeFields", propertyFeeFields);
        mav.addObject("managementFeeFields", managementFeeFields);
        mav.addObject("propertyFeeList", feeMapper.listProFeesNeeded());
        mav.addObject("managementFeeList", feeMapper.listManFeesNeeded());
        mav.addObject("title", "住户需缴纳费用账单");
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView searchIdleResidences(String communityName, int unitNumber, int floorNumber) {
        ModelAndView mav = new ModelAndView("property/showIdleResidences");
        System.out.println(communityName + ": " + unitNumber + ": " + floorNumber);
        addResidenceObjects(mav, propertyMapper.searchIdleResidences(communityName, unitNumber, floorNumber), Residence.class);
        mav.addObject("title", "筛选闲置房屋信息");
        return mav;
    }

    @RequestMapping(value = "/searchFee", method = RequestMethod.POST)
    public ModelAndView searchFeesOfResident(ResidentFeeSearch residentFeeSearch) {
        ModelAndView mav = new ModelAndView("fee/showResidentFees");
        Date from, to;
        GregorianCalendar gc = new GregorianCalendar();
        if (residentFeeSearch.getReportType().equals("m")) {//查询月度报表
            from = residentFeeSearch.getFromMonthly();
            gc.setTime(from);
            gc.set(Calendar.DAY_OF_MONTH, 1);
            from = gc.getTime();
            gc.add(Calendar.MONTH, 1);//月份加一
            to = gc.getTime();
        } else {//查询季度报表
            from = residentFeeSearch.getFromQuarter();
            gc.setTime(from);
            gc.set(Calendar.DAY_OF_MONTH, 1);
            from = gc.getTime();
            gc.add(Calendar.MONTH, 4);//月份加4
            to = gc.getTime();
        }
        java.sql.Date sqlFrom = new java.sql.Date(from.getTime());
        java.sql.Date sqlTo = new java.sql.Date(to.getTime());
        System.out.println(residentFeeSearch.getReportType() + "-> from: " + sqlFrom + " to: " + sqlTo + "  paid: " + residentFeeSearch.isPaid());
        List<Field> propertyFeeFields = Arrays.asList(PropertyFeeRecord.class.getDeclaredFields());
        List<Field> managementFeeFields = Arrays.asList(ManagementFeeRecord.class.getDeclaredFields());
        mav.addObject("FOEMap", fieldOfObjMap);
        mav.addObject("propertyFeeFields", propertyFeeFields);
        mav.addObject("managementFeeFields", managementFeeFields);
        if (residentFeeSearch.isPaid()) {
            mav.addObject("propertyFeeList", feeMapper.listProFees(sqlFrom, sqlTo));
            mav.addObject("managementFeeList", feeMapper.listManFees(sqlFrom, sqlTo));
        } else {
            mav.addObject("propertyFeeList", feeMapper.listProFeesNeeded());
            mav.addObject("managementFeeList", feeMapper.listManFeesNeeded());

        }
        mav.addObject("title", "筛选住户需缴纳费用账单");
        return mav;
    }

    private <T> void addResidenceObjects(ModelAndView mav, List<T> residences, Class type) {
        List<Field> fields = Arrays.asList(type.getDeclaredFields());
        mav.addObject("residenceList", residences);
        mav.addObject("fields", fields);
        mav.addObject("FOEMap", fieldOfObjMap);
    }
}
