package com.example.pms.dao;

import com.example.pms.bean.ComplaintRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Mapper
@Component("complaintMapper")
public interface ComplaintMapper {
    @Select({
            "<script>",
            "select resident_id as residentID, " +
                    "resident_name as residentName, " +
                    "community_id as communityID, " +
                    "community_name as communityName, " +
                    "unit_num as unit, " +
                    "floor_num as floor, " +
                    "room_num as room, " +
                    "complaint_order.issue_date as issueDate, " +
                    "category as complaintCategory, " +
                    "complaint_result as opinion " +
                    "from complaint_order natural join residence_record " +
                    "natural join residence " +
                    "join property_record using (residence_id) " +
                    "natural join resident " +
                    "natural join community " +
                    "where complaint_id is not null ",
            "<if test='#{communityName} != null'> and community_name like concat('%', #{communityName}, '%') </if>",
            "<if test='#{complaintType} != null'> and category like concat('%', #{complaintType}, '%') </if>",
            "<if test='#{unitNum} != null'> and unit_num like concat('%', #{unitNum}, '%') </if>",
            "<if test='#{floorNum} != null'> and floor_num like concat('%', #{floorNum}, '%') </if>",
            "<if test='#{roomNum} != null'> and room_num like concat('%', #{roomNum}, '%') </if>",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> and complaint_order.issue_date between #{sqlFrom} and #{sqlTo} </if>",
            "</script>"
    })
    List<ComplaintRecord> listComplaintRecords(@Param("sqlFrom") Date sqlFrom,
                                               @Param("sqlTo") Date sqlTo,
                                               @Param("communityName") String communityName,
                                               @Param("complaintType") String complaintType,
                                               @Param("unitNum") String unitNum,
                                               @Param("floorNum") String floorNum,
                                               @Param("roomNum") String roomNum);
}
