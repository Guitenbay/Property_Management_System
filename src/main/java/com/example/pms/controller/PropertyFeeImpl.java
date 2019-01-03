package com.example.pms.controller;


import com.example.pms.bean.PropertyFeeRecord;
import com.example.pms.dao.PropertyFeeDao;

import javax.sql.DataSource;
import java.util.List;

/**
 * 物业费缴纳实现类
 */
public class PropertyFeeImpl implements PropertyFeeDao {

    @Override
    public void create(String residentID, String residenceID, double fee) {

    }

    @Override
    public List<PropertyFeeRecord> listFees() {
        return null;
    }
}
