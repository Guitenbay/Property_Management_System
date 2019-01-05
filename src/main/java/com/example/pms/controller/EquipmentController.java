package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.Equipment;
import com.example.pms.bean.EquipmentSearch;
import com.example.pms.bean.RepairOrder;
import com.example.pms.dao.EquipmentMapper;
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
@RequestMapping("equipment")
public class EquipmentController {
    @Autowired
    private EquipmentMapper equipmentMapper;

    private String str = readFile(FILE_PATH);
    private JSONObject fieldOfObjs = stringToJSONObject(str, "Attrs_zh");
    private Map<String, String> fieldOfObjMap = JSONObject.toJavaObject(fieldOfObjs, Map.class);

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("index/equipment");
        mav.addObject("title", "设备管理");
        return mav;
    }

    @RequestMapping("/inspect")
    public ModelAndView inspect() {
        ModelAndView mav = new ModelAndView("equipment/inspect");
        List<Field> inspectFields = Arrays.asList(Equipment.class.getDeclaredFields());
        mav.addObject("FOEMap", fieldOfObjMap);
        mav.addObject("equipmentFields", inspectFields);
        mav.addObject("equipmentList", equipmentMapper.listWaitForRepair());
        mav.addObject("title", "待维修");
        return mav;
    }

    @RequestMapping("/repairRecord")
    public ModelAndView listRepairRecord() {
        EquipmentSearch equipmentSearch = new EquipmentSearch();
        equipmentSearch.setReportType("m");
        Date today = new Date();
        equipmentSearch.setEquipmentType("");
        equipmentSearch.setResidentID("");
        equipmentSearch.setFromMonthly(new java.sql.Date(today.getTime()));
        return searchRecord(equipmentSearch);
    }

    @RequestMapping(value = "/searchRepairRecord", method = RequestMethod.POST)
    public ModelAndView searchRecord(EquipmentSearch equipmentSearch) {
        ModelAndView mav = new ModelAndView("equipment/repairRecord");
        List<Field> repairOrderFields = Arrays.asList(RepairOrder.class.getDeclaredFields());
        Date from = new Date(), to = new Date();
        SearchDateUtil.getFromToBySearchType(from, to, equipmentSearch);
        java.sql.Date sqlFrom = new java.sql.Date(from.getTime());
        java.sql.Date sqlTo = new java.sql.Date(to.getTime());
        List<RepairOrder> orderList = equipmentMapper.listRepairOrderRecord(equipmentSearch.getResidentID(),
                equipmentSearch.getEquipmentType(),
                sqlFrom, sqlTo);
        mav.addObject("FOEMap", fieldOfObjMap);
        mav.addObject("equipmentFields", repairOrderFields);
        mav.addObject("equipmentList", orderList);
        double totalFee = 0;
        for (RepairOrder order : orderList) {
            totalFee += order.getRepairFee();
        }
        mav.addObject("totalFee", totalFee);
        mav.addObject("title", "报修记录");
        return mav;
    }
}
