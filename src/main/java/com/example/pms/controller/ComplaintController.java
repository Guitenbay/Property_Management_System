package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.ComplaintRecord;
import com.example.pms.bean.ComplaintSearch;
import com.example.pms.dao.ComplaintMapper;
import com.example.pms.util.SearchDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.pms.json.JsonUtils.FILE_PATH;
import static com.example.pms.json.JsonUtils.readFile;
import static com.example.pms.json.JsonUtils.stringToJSONObject;

@Controller
@RequestMapping("/complaint")
public class ComplaintController {
    @Autowired
    private ComplaintMapper complaintMapper;

    private String str = readFile(FILE_PATH);
    private JSONObject fieldOfObjs = stringToJSONObject(str, "Attrs_zh");
    private Map<String, String> fieldOfObjMap = JSONObject.toJavaObject(fieldOfObjs, Map.class);


    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("index/complaint");
        mav.addObject("title", "投诉意见管理");
        return mav;
    }

    @RequestMapping("/complaintRecord")
    public ModelAndView complaintRecord() {
        ComplaintSearch complaintSearch = new ComplaintSearch();
        complaintSearch.setReportType("m");
        Date today = new Date();
        complaintSearch.setFromMonthly(new java.sql.Date(today.getTime()));
        return searchComplaintRecord(complaintSearch);
    }

    @RequestMapping(value = "/searchComplaintRecord", method = RequestMethod.POST)
    public ModelAndView searchComplaintRecord(ComplaintSearch complaintSearch) {
        ModelAndView mav = new ModelAndView("complaint/searchComplaintRecord");
        List<Field> complaintRecordFields = Arrays.asList(ComplaintRecord.class.getDeclaredFields());
        Date from = new Date(), to = new Date();
        SearchDateUtil.getFromToBySearchType(from, to, complaintSearch);
        java.sql.Date sqlFrom = new java.sql.Date(from.getTime());
        java.sql.Date sqlTo = new java.sql.Date(to.getTime());
        mav.addObject("FOEMap", fieldOfObjMap);
        mav.addObject("complaintFields", complaintRecordFields);
        mav.addObject("complaintList", complaintMapper.listComplaintRecords(sqlFrom, sqlTo,
                complaintSearch.getCommunityName(), complaintSearch.getComplaintType(),
                complaintSearch.getUnitNum(), complaintSearch.getFloorNum(), complaintSearch.getRoomNum()));
        mav.addObject("title", "投诉意见查询");
        return mav;
    }
}
