package com.example.pms.dao;

import com.example.pms.bean.BusyPks;
import com.example.pms.bean.ParkingSpace;
import com.example.pms.bean.PaymentRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("parkingMapper")
public interface ParkingMapper {
    @Select("select pks_id as pksID, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "pks_state as pksState, " +
            "pks_type as pksType " +
            "from parking_space join community " +
            "where pks_id = #{id}")
    ParkingSpace findPksByID(@Param("id") int id);

    @Insert("insert into rented_pks values(#{pksID}, #{residentID}, #{discount}, " +
            "(#{discount} * #{payment})," +
            "#{manFee})")
    void insertRentedPks(@Param("pksID") int pksID, @Param("residentID") String residentID,
                         @Param("discount") double discount, @Param("payment") double payment,
                         @Param("manFee") double manFee);

    @Insert("insert into purchased_pks values(#{pksID}, #{residentID}, #{manFee})")
    void insertPurchasePks(@Param("pksID") int pksID, @Param("residentID") String residentID,
                           @Param("manFee") double manFee);

    @Insert("insert into temporary_pks values(#{pksID}, #{licensePlate})")
    void insertTemporaryPks(@Param("pksID") int pksID, @Param("licensePlate") String licensePlate);

    @Insert("insert into vehicle values(#{licensePlate}, #{ownerName}) " +
            "on duplicate key update owner_name = #{ownerName}")
    void insertVehicle(@Param("licensePlate") String licensePlate, @Param("ownerName") String ownerName);

    @Update("update parking_space set pks_state = #{state}, pks_type = #{type} where pks_id = #{pksID}")
    void updatePksState(@Param("pksID") int pksID, @Param("state") String state, @Param("type") String type);

    @Insert("insert into payment_record(payment, duration, start_time, checked, math) " +
            "values(#{payment}, #{duration}, current_timestamp, #{checked}, #{math})")
    @Options(useGeneratedKeys = true, keyProperty = "paymentRecord.paymentID")
    void insertPaymentRecord(@Param("payment") double payment, @Param("duration") int duration,
                             @Param("checked") String checked, @Param("math") String math,
                             @Param("paymentRecord") PaymentRecord paymentRecord);

    @Update("update payment_record set checked = 'YES' where payment_id = #{paymentID}")
    void updatePaymentRecordState(@Param("paymentID") int paymentID);

    @Insert("insert into pks_fee_record values(#{paymentID}, #{pksID}, #{desc}) " +
            "on duplicate key update description = #{desc}")
    void bindPksPaymentRecord(@Param("pksID") int pksID, @Param("paymentID") int paymentID, @Param("desc") String desc);


    @Select("select pks_id as pksID, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "pks_state as pksState, " +
            "pks_type as pksType " +
            "from parking_space natural join community " +
            "where pks_state = 'IDLE' " +
            "order by community_id, pks_id")
    List<ParkingSpace> listIdlePkses();

    @Select({
            "<script>",
            "select pks_id as pksID, " +
                    "community_id as communityID, " +
                    "community_name as communityName, " +
                    "pks_state as pksState, " +
                    "pks_type as pksType " +
                    "from parking_space natural join community " +
                    "where pks_state = 'IDLE' ",
            "<if test='#{cName} != null'>and community_name like concat(#{cName}, '%') </if>",
            "</script>"
    })
    List<ParkingSpace> searchIdlePkses(@Param("cName") String cName);

    @Select("select pks_id as pksID, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "pks_state as pksState, " +
            "pks_type as pksType, " +
            "payment as costs, " +
            "checked as paid, " +
            "resident_id as holderID, " +
            "resident_name as holder " +
            "from rented_pks natural join parking_space " +
            "natural join community " +
            "natural join resident " +
            "natural join pks_fee_record " +
            "natural join payment_record " +
            "where pks_type = 'RENTED' and pks_state <> 'IDLE' " +
            "order by community_id, pks_id")
    List<BusyPks> listRentalPkses();

    @Select("select pks_id as pksID, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "pks_state as pksState, " +
            "pks_type as pksType, " +
            "payment as costs, " +
            "checked as paid, " +
            "resident_id as holderID, " +
            "resident_name as holder " +
            "from purchased_pks natural join parking_space " +
            "natural join community " +
            "natural join resident " +
            "natural join pks_fee_record " +
            "natural join payment_record " +
            "where pks_type = 'PURCHASED' and pks_state <> 'IDLE' " +
            "order by community_id, pks_id")
    List<BusyPks> listPurchasePkses();

    @Select("select pks_id as pksID, " +
            "community_id as communityID, " +
            "community_name as communityName, " +
            "pks_state as pksState, " +
            "pks_type as pksType, " +
            "payment as costs, " +
            "checked as paid, " +
            "license_plate as holderID, " +
            "owner_name as holder " +
            "from temporary_pks natural join parking_space " +
            "natural join community " +
            "natural join vehicle " +
            "natural join pks_fee_record " +
            "natural join payment_record " +
            "where pks_type = 'TEMPORARY' and pks_state <> 'IDLE' " +
            "order by community_id, pks_id")
    List<BusyPks> listTemporaryPkses();

}
