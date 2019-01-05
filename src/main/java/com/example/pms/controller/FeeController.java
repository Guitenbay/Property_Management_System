package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.PropertyRecord;
import com.example.pms.dao.FeeMapper;
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
@RequestMapping("/fee")
public class FeeController {
    @Autowired
    FeeMapper feeMapper;

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("index/fee");
        mav.addObject("title", "费用管理");
        return mav;
    }

    @RequestMapping("residentFees")
    public ModelAndView listResidentFees() {
        ModelAndView mav = new ModelAndView("fee/showResidentFees");
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
