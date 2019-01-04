package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.BusyPks;
import com.example.pms.bean.ParkingSpace;
import com.example.pms.dao.ParkingMapper;
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
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    ParkingMapper parkingMapper;

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        return new ModelAndView("index/parking");
    }

    @RequestMapping("/idle")
    public ModelAndView listIdlePks() {
        ModelAndView mav = new ModelAndView("pks/showPkses");
        addPksObjects(mav, parkingMapper.listIdlePkses(), ParkingSpace.class);
        mav.addObject("type", "i");
        return mav;
    }

    @RequestMapping("/purchase")
    public ModelAndView addPurchasePks() {
        ModelAndView mav = new ModelAndView("pks/showPkses");
        addPksObjects(mav, parkingMapper.listPurchasePkses(), BusyPks.class);
        mav.addObject("type", "p");
        return mav;
    }

    @RequestMapping("/rent")
    public ModelAndView addRentalPks() {
        ModelAndView mav = new ModelAndView("pks/showPkses");
        addPksObjects(mav, parkingMapper.listRentalPkses(), BusyPks.class);
        mav.addObject("type", "r");
        return mav;
    }


    @RequestMapping("/temporary")
    public ModelAndView addTemporaryPks() {
        ModelAndView mav = new ModelAndView("pks/showPkses");
        addPksObjects(mav, parkingMapper.listTemporaryPkses(), BusyPks.class);
        mav.addObject("type", "t");
        return mav;
    }


    private void addPksObjects(ModelAndView mav, List pkses, Class type) {
        List<Field> fields = Arrays.asList(type.getDeclaredFields());
        String str = readFile(FILE_PATH);
        JSONObject fieldOfPks = stringToJSONObject(str, "ParkingSpace_zh");
        Map<String, String> fieldOfPksMap = JSONObject.toJavaObject(fieldOfPks, Map.class);
        mav.addObject("pksList", pkses);
        mav.addObject("fields", fields);
        mav.addObject("FOEMap", fieldOfPksMap);
    }

}
