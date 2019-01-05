package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.FeeReport;
import com.example.pms.bean.FeeSearch;
import com.example.pms.bean.ManagementFeeRecord;
import com.example.pms.bean.PropertyFeeRecord;
import com.example.pms.dao.FeeMapper;
import com.example.pms.service.FeeService;
import com.example.pms.util.SearchDateUtil;
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
@RequestMapping("/fee")
public class FeeController {
    @Autowired
    private FeeMapper feeMapper;

    @Autowired
    private FeeService feeService;
    private String str = readFile(FILE_PATH);
    private JSONObject fieldOfObjs = stringToJSONObject(str, "Attrs_zh");
    private Map<String, String> fieldOfObjMap = JSONObject.toJavaObject(fieldOfObjs, Map.class);

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("index/fee");
        mav.addObject("title", "费用管理");
        return mav;
    }

    @RequestMapping("/propertyFee")
    public ModelAndView listPropertyFees() {
        ModelAndView mav = new ModelAndView("fee/propertyFee");
        List<Field> propertyFeeFields = Arrays.asList(PropertyFeeRecord.class.getDeclaredFields());
        mav.addObject("FOEMap", fieldOfObjMap);
        mav.addObject("propertyFeeFields", propertyFeeFields);
        mav.addObject("propertyFeeList", feeMapper.listProFeesNeeded());
        mav.addObject("title", "物业费收入情况");
        return mav;
    }


    @RequestMapping("/pksFee")
    public ModelAndView listResidentFees() {
        ModelAndView mav = new ModelAndView("fee/pksFee");
        List<Field> managementFeeFields = Arrays.asList(ManagementFeeRecord.class.getDeclaredFields());
        mav.addObject("FOEMap", fieldOfObjMap);
        mav.addObject("managementFeeFields", managementFeeFields);
        mav.addObject("managementFeeList", feeMapper.listManFeesNeeded());
        mav.addObject("title", "停车收费情况");
        return mav;
    }

    @RequestMapping("/ieReport")
    public ModelAndView listFeeReport() {
        FeeSearch feeSearch = new FeeSearch();
        feeSearch.setReportType("m");
        Date today = new Date();
        feeSearch.setFromMonthly(new java.sql.Date(today.getTime()));
        return searchFeeReport(feeSearch);
    }

    @RequestMapping(value = "/searchFeeReport", method = RequestMethod.POST)
    public ModelAndView searchFeeReport(FeeSearch feeSearch) {
        ModelAndView mav = new ModelAndView("fee/ieReport");
        Date from = new Date(), to = new Date();
        SearchDateUtil.getFromToBySearchType(from, to, feeSearch);
        java.sql.Date sqlFrom = new java.sql.Date(from.getTime());
        java.sql.Date sqlTo = new java.sql.Date(to.getTime());
        List<FeeReport> incomeList = feeMapper.listPksIncome(sqlFrom, sqlTo);
        incomeList.addAll(feeMapper.listPksManagementIncome(sqlFrom, sqlTo));
        incomeList.addAll(feeMapper.listPropertyIncome(sqlFrom, sqlTo));
        incomeList.addAll(feeMapper.listResidenceIncome(sqlFrom, sqlTo));
        incomeList.addAll(feeMapper.listOtherIncome(sqlFrom, sqlTo));
        List<FeeReport> outcomeList = feeMapper.listOutcome(sqlFrom, sqlTo);
        List<Field> fields = Arrays.asList(FeeReport.class.getDeclaredFields());
        mav.addObject("FOEMap", fieldOfObjMap);
        mav.addObject("feeFields", fields);
        mav.addObject("incomeList", incomeList);
        mav.addObject("outcomeList", outcomeList);
        mav.addObject("title", "收支报表");
        double totalIn = 0, totalOut = 0;
        for (FeeReport feeReport : incomeList) {
            totalIn += feeReport.getFee();
        }
        for (FeeReport feeReport : outcomeList) {
            totalOut += feeReport.getFee();
        }
        mav.addObject("totalIn", totalIn);
        mav.addObject("totalOut", totalOut);
        return mav;
    }

    private void addPksObjects(ModelAndView mav, List list, Class type) {
        List<Field> fields = Arrays.asList(type.getDeclaredFields());
        String str = readFile(FILE_PATH);
        JSONObject fieldOfObjs = stringToJSONObject(str, "Attrs_zh");
        Map<String, String> fieldOfObjMap = JSONObject.toJavaObject(fieldOfObjs, Map.class);
        mav.addObject("FeeList", list);
        mav.addObject("fields", fields);
        mav.addObject("FOEMap", fieldOfObjMap);
    }
}
