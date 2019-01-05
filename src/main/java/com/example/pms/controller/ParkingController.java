package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.*;
import com.example.pms.dao.ParkingMapper;
import com.example.pms.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
    private ParkingMapper parkingMapper;
    @Autowired
    private ParkingService parkingService;

    private String str = readFile(FILE_PATH);
    private JSONObject fieldOfPks = stringToJSONObject(str, "Attrs_zh");
    private Map<String, String> fieldOfPksMap = JSONObject.toJavaObject(fieldOfPks, Map.class);

    @RequestMapping({"/index", "/"})
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("index/parking");
        mav.addObject("title", "停车管理");
        return mav;
    }

    @RequestMapping("/idle")
    public ModelAndView listIdlePks() {
        ModelAndView mav = new ModelAndView("pks/showPkses");
        mav.addObject("title", "闲置车位信息");
        addPksObjects(mav, parkingMapper.listIdlePkses(), ParkingSpace.class);
        mav.addObject("inputFields", new ArrayList<Field>());
        return mav;
    }

    @RequestMapping("/purchase")
    public ModelAndView listPurchasePks() {
        ModelAndView mav = new ModelAndView("pks/showPkses");
        mav.addObject("title", "购买车位信息");
        addPksObjects(mav, parkingMapper.listPurchasePkses(), BusyPks.class);
        mav.addObject("inputFields", Arrays.asList(PurchasePks.class.getDeclaredFields()));
        mav.addObject("type", "Purchase");
        return mav;
    }

    @RequestMapping("/rent")
    public ModelAndView listRentalPks() {
        ModelAndView mav = new ModelAndView("pks/showPkses");
        mav.addObject("title", "租用车位信息");
        addPksObjects(mav, parkingMapper.listRentalPkses(), BusyPks.class);
        mav.addObject("inputFields", Arrays.asList(RentalPks.class.getDeclaredFields()));
        mav.addObject("type", "Rental");
        return mav;
    }


    @RequestMapping("/temporary")
    public ModelAndView listTemporaryPks() {
        ModelAndView mav = new ModelAndView("pks/showPkses");
        mav.addObject("title", "临时车位信息");
        addPksObjects(mav, parkingMapper.listTemporaryPkses(), BusyPks.class);
        mav.addObject("inputFields", Arrays.asList(TemporaryPks.class.getDeclaredFields()));
        mav.addObject("type", "Temporary");
        return mav;
    }

    @RequestMapping("/addPurchasePks")
    public ModelAndView addPks(PurchasePks purchasePks) {
        ModelAndView mav = new ModelAndView("redirect:purchase");
        parkingService.addPurchasePks(purchasePks);
        return mav;
    }

    @RequestMapping("/addRentalPks")
    public ModelAndView addPks(RentalPks rentalPks) {
        ModelAndView mav = new ModelAndView("redirect:rent");
        parkingService.addRentalPks(rentalPks);
        return mav;
    }

    @RequestMapping("/addTemporaryPks")
    public ModelAndView addPks(TemporaryPks temporaryPks) {
        ModelAndView mav = new ModelAndView("redirect:temporary");
        parkingService.addTemporaryPks(temporaryPks);
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView search(String communityName) {
        ModelAndView mav = new ModelAndView("pks/showPkses");
        mav.addObject("title", "筛选闲置房屋");
        addPksObjects(mav, parkingMapper.searchIdlePkses(communityName), ParkingSpace.class);
        mav.addObject("inputFields", new ArrayList<Field>());
        return mav;
    }

    private void addPksObjects(ModelAndView mav, List pkses, Class type) {
        List<Field> fields = Arrays.asList(type.getDeclaredFields());
        mav.addObject("pksList", pkses);
        mav.addObject("fields", fields);
        mav.addObject("FOEMap", fieldOfPksMap);
    }

}
