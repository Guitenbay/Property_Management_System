package com.example.pms.dao;


import com.example.pms.bean.PropertyFeeRecord;
import com.example.pms.bean.PropertyRecord;
import com.example.pms.bean.Residence;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component("propertyMapper")
public interface PropertyMapper {

    @Insert("insert into resident(resident_id, resident_name, phonenumber) values(#{residentID}, #{residentName}, #{phoneNumber}) " +
            "on duplicate key update resident_name = #{residentName}, phonenumber = #{phoneNumber}")
    int createResident(@Param("residentName") String residentName, @Param("residentID") String residentID, @Param("phoneNumber") String phoneNumber);

    @Insert("insert into property_record " +
            "values(#{residentID}, " +
            "#{residenceID}, " +
            "current_timestamp, " +
            "(select area * residence_price from residence_record natural join community natural join residence " +
            " where residence_id = #{residenceID})) " +
            "on duplicate key update " +
            "resident_id = #{residentID}, " +
            "issue_date = current_timestamp, " +
            "purchased_fee = " +
            "(select area * residence_price from residence_record natural join community natural join residence " +
            " where residence_id = #{residenceID})")
    int createPropertyRecord(@Param("residentID") String residentID, @Param("residenceID") int residenceID);

    @Update("update residence_record set residence_state = #{state} where residence_id = #{residenceID}")
    int updateResidenceRecord(@Param("residenceID") int residenceID, @Param("state") String state);

    @Select("select residence_id as residenceID, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "floor_num as floor, " +
            "unit_num as unit, " +
            "room_num as room, " +
            "area, " +
            "residence_man_price * area as manFee " +
            "from residence_record " +
            "natural join residence " +
            "natural join community " +
            "where residence_state='IDLE' " +
            "order by community_id, residence_id")
    List<Residence> listIdleResidences();

    @Select("select resident_id as residentID, " +
            "resident_name as residentName, " +
            "phonenumber as phoneNumber, " +
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
            "natural join community " +
            "order by community_id, residence_id")
    List<PropertyRecord> listProperties();


    List<PropertyFeeRecord> listPropertyFees();
}
