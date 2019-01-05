package com.example.pms.dao;


import com.example.pms.bean.FeeReport;
import com.example.pms.bean.ManagementFeeRecord;
import com.example.pms.bean.PaymentRecord;
import com.example.pms.bean.PropertyFeeRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * 物业费缴纳接口
 */
@Mapper
@Component("feeMapper")
public interface FeeMapper {


    @Insert("insert into payment_record(payment, duration, start_time, checked, math) " +
            "values(#{payment}, #{duration}, current_timestamp, #{checked}, #{math})")
    @Options(useGeneratedKeys = true, keyProperty = "paymentRecord.paymentID")
    void insertPaymentRecord(@Param("payment") double payment, @Param("duration") int duration,
                             @Param("checked") String checked, @Param("math") String math,
                             @Param("paymentRecord") PaymentRecord paymentRecord);

    @Update("update payment_record set checked = 'YES' where payment_id = #{paymentID}")
    void updatePaymentRecordState(@Param("paymentID") int paymentID);

    @Insert("insert into property_fee_record values(#{paymentID}, #{residentID}, #{residenceID})")
    void bindPropertyPaymentRecord(@Param("residentID") String residentID, @Param("residenceID") int residenceID,
                                   @Param("paymentID") int paymentID);

    @Insert("insert into pks_management_fee_record values(#{paymentID}, #{pksID})")
    void bindManagementPaymentRecord(@Param("pksID") int pksID, @Param("paymentID") int paymentID);


    @Insert("insert into other_income(income_src_desc, income_issue_date, income_fee) " +
            "values(#{srcDesc}, #{incomeFee}, current_timestamp) ")
    void insertOtherIncomeRecord(@Param("incomeFee") double incomeFee, @Param("srcDesc") String srcDesc);

    @Select({
            "<script>",
            "select resident_id as residentID, " +
                    "resident_name as residentName, " +
                    "community_id as communityID, " +
                    "community_name as communityName, " +
                    "residence_id as residenceID, " +
                    "floor_num as floor, " +
                    "unit_num as unit, " +
                    "room_num as room, " +
                    "area, " +
                    "area * residence_man_price as propertyFee, " +
                    "start_time as startTime, " +
                    "checked as paid " +
                    "from property_fee_record natural join payment_record " +
                    "natural join property_record " +
                    "natural join residence_record " +
                    "natural join resident " +
                    "natural join residence " +
                    "natural join community ",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> where start_time between #{sqlFrom} and #{sqlTo} </if>",
            "order by payment_id desc",
            "</script>"
    })
    List<PropertyFeeRecord> listProFees(@Param("sqlFrom") Date sqlFrom, @Param("sqlTo") Date sqlTo);


    @Select({
            "<script>",
            "select resident_id as residentID, " +
                    "resident_name as residentName, " +
                    "community_id as communityID, " +
                    "community_name as communityName, " +
                    "pks_id as pksID, " +
                    "pks_type as pksType, " +
                    "man_fee as manFee, " +
                    "start_time as startTime, " +
                    "checked as paid " +
                    "from pks_management_fee_record natural join payment_record " +
                    "natural join property_record " +
                    "natural join parking_space " +
                    "natural join (select * from (select pks_id, resident_id, man_fee from purchased_pks) p_holder " +
                    "union (select pks_id, resident_id, man_fee from rented_pks )) pr_holder " +
                    "natural join resident " +
                    "natural join residence " +
                    "natural join community ",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> where start_time between #{sqlFrom} and #{sqlTo} </if>",
            "order by payment_id desc",
            "</script>"
    })
    List<ManagementFeeRecord> listManFees(@Param("sqlFrom") Date sqlFrom, @Param("sqlTo") Date sqlTo);

    @Select({
            "<script>",
            "select resident_id as residentID, " +
                    "resident_name as residentName, " +
                    "community_id as communityID, " +
                    "community_name as communityName, " +
                    "residence_id as residenceID, " +
                    "floor_num as floor, " +
                    "unit_num as unit, " +
                    "room_num as room, " +
                    "area, " +
                    "area * residence_man_price as propertyFee, " +
                    "current_timestamp as startTime, " +
                    "'NO' as paid " +
                    "from property_record natural join residence_record " +
                    "natural join resident " +
                    "natural join residence " +
                    "natural join community " +
                    "natural left outer join property_fee_record " +
                    "where payment_id is null " +
                    "order by residence_id ",
            "</script>"
    })
    List<PropertyFeeRecord> listProFeesNeeded();

    @Select({
            "<script>",
            "select resident_id as residentID, " +
                    "resident_name as residentName, " +
                    "community_id as communityID, " +
                    "community_name as communityName, " +
                    "pks_id as pksID, " +
                    "pks_type as pksType, " +
                    "man_fee as manFee, " +
                    "current_timestamp as startTime, " +
                    "'NO' as paid " +
                    "from (select * from (select pks_id, resident_id, man_fee from purchased_pks) p_holder " +
                    "union (select pks_id, resident_id, man_fee from rented_pks )) pr_holder " +
                    "natural join property_record " +
                    "natural join parking_space " +
                    "natural join resident " +
                    "natural join residence " +
                    "natural join community " +
                    "natural left outer join pks_management_fee_record " +
                    "where payment_id is null " +
                    "order by residence_id",
            "</script>"
    })
    List<ManagementFeeRecord> listManFeesNeeded();

    @Select({
            "<script>",
            "select payment as fee,  " +
                    "'停车收入' as description, " +
                    "start_time as issueDate  " +
                    "from payment_record " +
                    "natural join pks_fee_record ",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> where start_time between #{sqlFrom} and #{sqlTo} </if>",
            "</script>"
    })
    List<FeeReport> listPksIncome(@Param("sqlFrom") Date sqlFrom, @Param("sqlTo") Date sqlTo);

    @Select({
            "<script>",
            "select payment as fee,  " +
                    "'停车管理费收入' as description,  " +
                    "start_time as issueDate  " +
                    "from payment_record " +
                    "natural join pks_management_fee_record ",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> where start_time between #{sqlFrom} and #{sqlTo} </if>",
            "</script>"
    })
    List<FeeReport> listPksManagementIncome(@Param("sqlFrom") Date sqlFrom, @Param("sqlTo") Date sqlTo);

    @Select({
            "<script>",
            "select payment as fee,  " +
                    "'物业费收入' as description,  " +
                    "start_time as issueDate  " +
                    "from payment_record " +
                    "natural join property_fee_record ",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> where start_time between #{sqlFrom} and #{sqlTo} </if>",
            "</script>"
    })
    List<FeeReport> listPropertyIncome(@Param("sqlFrom") Date sqlFrom, @Param("sqlTo") Date sqlTo);


    @Select({
            "<script>",
            "select purchased_fee as fee, " +
                    "'购房收入' as description, " +
                    "issue_date as issueDate  " +
                    "from property_record",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> where issue_date between #{sqlFrom} and #{sqlTo} </if>",
            "</script>"
    })
    List<FeeReport> listResidenceIncome(@Param("sqlFrom") Date sqlFrom, @Param("sqlTo") Date sqlTo);

    @Select({
            "<script>",
            "select income_fee as fee, " +
                    "income_src_desc as description, " +
                    "income_issue_date as issueDate  " +
                    "from other_income",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> where income_issue_date between #{sqlFrom} and #{sqlTo} </if>",
            "</script>"
    })
    List<FeeReport> listOtherIncome(@Param("sqlFrom") Date sqlFrom, @Param("sqlTo") Date sqlTo);

    @Select({
            "<script>",
            "select repair_fee as fee, " +
                    "'维修费用' as description, " +
                    "issue_date asissueDate  " +
                    "from maintenance_record " +
                    "natural join equipment " +
                    "natural join equipment_info ",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> where issue_date between #{sqlFrom} and #{sqlTo} </if>",
            "</script>"
    })
    List<FeeReport> listOutcome(@Param("sqlFrom") Date sqlFrom, @Param("sqlTo") Date sqlTo);

}
