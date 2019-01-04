package com.example.pms.dao;


import com.example.pms.bean.ManagementFeeRecord;
import com.example.pms.bean.PropertyFeeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * 物业费缴纳接口
 */
@Mapper
@Component("propertyFeeMapper")
public interface FeeMapper {
    void create(String residentID, String residenceID, double fee);

    @Select("select resident_id as residentID, " +
            "resident_name as residentName, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "residence_id as residenceID, " +
            "floor_num as floor, " +
            "unit_num as unit, " +
            "room_num as room, " +
            "area, " +
            "area * residence_man_price as propertyFee, " +
            "checked as paid " +
            "from property_fee_record natural join payment_record " +
            "natural join property_record " +
            "natural join residence_record " +
            "natural join resident " +
            "natural join residence " +
            "natural join community " +
            "order by payment_id desc")
    List<PropertyFeeRecord> listProFees();

    @Select("select resident_id as residentID, " +
            "resident_name as residentName, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "checked as paid " +
            "from payment_record natural join parking_space " +
            "natural join property_record " +
            "natural join resident " +
            "natural join residence " +
            "natural join community " +
            "order by payment_id desc")
    List<ManagementFeeRecord> listManFees();
}
