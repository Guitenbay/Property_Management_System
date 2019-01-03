package com.example.pms.dao;


import com.example.pms.bean.PropertyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * 房产登记接口
 */
@Mapper
@Component("propertyMapper")
public interface PropertyDao {


    void create(String residentID, String residenceID, double fee);


    List<PropertyRecord> listProperties();
}
