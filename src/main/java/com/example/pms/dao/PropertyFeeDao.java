package com.example.pms.dao;


import com.example.pms.bean.PropertyFeeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * 物业费缴纳接口
 */
@Mapper
@Component("propertyFeeMapper")
public interface PropertyFeeDao {

    void create(String residentID, String residenceID, double fee);

    List<PropertyFeeRecord> listFees();
}
