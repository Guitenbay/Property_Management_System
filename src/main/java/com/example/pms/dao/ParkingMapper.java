package com.example.pms.dao;

import com.example.pms.bean.BusyPks;
import com.example.pms.bean.ParkingSpace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("parkingMapper")
public interface ParkingMapper {
    @Select("select pks_id as id, " +
            "community_id as　communityID, " +
            "community_name as communityName, " +
            "pks_state as state, " +
            "pks_type as type " +
            "from parking_space natural left outer join community " +
            "where pks_id = #{id}")
    ParkingSpace findPksByID(String id);

    @Select("select pks_id as id, " +
            "community_id as　communityID, " +
            "community_name as communityName, " +
            "pks_state as state, " +
            "pks_type as type " +
            "from parking_space natural join community " +
            "where pks_state = 'IDLE'" +
            "order by pks_id")
    List<ParkingSpace> listIdlePkses();

    @Select("select pks_id as id, " +
            "community_id as　communityID, " +
            "community_name as communityName, " +
            "pks_state as state, " +
            "pks_type as type, " +
            "resident_id as holderID, " +
            "resident_name as holder " +
            "from rented_pks natural join community " +
            "natural join parking_space " +
            "natural join resident " +
            "where pks_type = 'RENTED' and pks_state <> 'IDLE'" +
            "order by pks_id")
    List<BusyPks> listRentalPkses();

    @Select("select pks_id as id, " +
            "community_id as　communityID, " +
            "community_name as communityName, " +
            "pks_state as state, " +
            "pks_type as type, " +
            "resident_id as holderID, " +
            "resident_name as holder " +
            "from purchased_pks natural join community " +
            "natural join parking_space " +
            "natural join resident " +
            "where pks_type = 'PURCHASED' and pks_state <> 'IDLE'" +
            "order by pks_id")
    List<BusyPks> listPurchasePkses();

    @Select("select pks_id as id, " +
            "community_id as　communityID, " +
            "community_name as communityName, " +
            "pks_state as state, " +
            "pks_type as type, " +
            "license_plate as holderID, " +
            "owner_name as holder " +
            "from temporary_pks natural left outer join community " +
            "natural join parking_space " +
            "natural join vehicle " +
            "where pks_type = 'TEMPORARY' and pks_state <> 'IDLE'" +
            "order by pks_id")
    List<ParkingSpace> listTemporaryPkses();
}
