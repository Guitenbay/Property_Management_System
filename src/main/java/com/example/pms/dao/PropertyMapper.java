package com.example.pms.dao;


import com.example.pms.bean.PropertyRecord;
import com.example.pms.bean.Residence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;


@Mapper
@Component("propertyMapper")
public interface PropertyMapper {


    void create(String residentID, String residenceID, double fee);



    @Select("select residence_id as residenceID, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "floor_num as floor, " +
            "unit_num as unit, " +
            "room_num as room, " +
            "area, " +
            "residence_price as manFee " +
            "from residence_record " +
            "natural join residence " +
            "natural join community " +
            "where residence_state='IDLE' " +
            "order by char_length(residence_id), residence_id")
    List<Residence> listIdleResidences();

    @Select("select resident_id as residentID, " +
            "resident_name as residentName, " +
            "phonenumber as residentPhoneNumber, " +
            "residence_id as residenceID, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "floor_num as floor, " +
            "unit_num as unit, " +
            "room_num as room, " +
            "area, " +
            "purchased_fee as costs, " +
            "issue_date as issueDate " +
            "from property_record " +
            "natural join residence_record " +
            "natural join resident " +
            "natural join residence " +
            "natural join community")
    List<PropertyRecord> listProperties();
}
