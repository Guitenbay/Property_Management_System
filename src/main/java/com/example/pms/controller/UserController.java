package com.example.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pms.bean.User;
import com.example.pms.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.pms.json.JsonUtils.FILE_PATH;
import static com.example.pms.json.JsonUtils.readFile;
import static com.example.pms.json.JsonUtils.stringToJSONObject;

@Controller
@RequestMapping({"/user"})
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/")
    public ModelAndView getUsersInfoByName() {
        ModelAndView modelAndView = new ModelAndView("/showUser");

        List<User> users = userMapper.findUsers();
        List<Field> fields = Arrays.asList(User.class.getDeclaredFields());
//        for(Field f : fields){
//            User user = new User();
//            f.getName(); //name
//            User.class.getMethod("getName");
//        }
        String str = readFile(FILE_PATH);
        JSONObject fieldOfUser = stringToJSONObject(str, "User");
        Map<String, String> fieldOfUserMap = JSONObject.toJavaObject(fieldOfUser, Map.class);
        modelAndView.addObject("userList", users);
        modelAndView.addObject("fields", fields);
        modelAndView.addObject("FOEMap", fieldOfUserMap);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/info")
    public String getUserInfoByName(String id) {
        JSONObject jsonObject = new JSONObject();

        User user = userMapper.findUserById(id);
        jsonObject.put("info", user);

        return jsonObject.toJSONString();
    }

}
