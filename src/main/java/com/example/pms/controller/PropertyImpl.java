package com.example.pms.controller;


import com.example.pms.bean.PropertyRecord;
import com.example.pms.dao.PropertyDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

/**
 * 房产登记实现类
 */
public class PropertyImpl implements PropertyDao {

    @Override
    public void create(String residentID, String residenceID, double fee) {

    }

    @Override
    public List<PropertyRecord> listProperties() {
        return null;
    }
}
