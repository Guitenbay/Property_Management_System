package com.example.pms.dao;

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
            "from parking_space natural left outer join community " +
            "where pks_state = 'IDLE'")
    List<ParkingSpace> listIdlePkses();

    @Select("select pks_id as id, " +
            "community_id as　communityID, " +
            "community_name as communityName, " +
            "pks_state as state, " +
            "pks_type as type " +
            "from parking_space natural left outer join community " +
            "where pks_type = 'RENTED'")
    List<ParkingSpace> listRentalPkses();

    @Select("select pks_id as id, " +
            "community_id as　communityID, " +
            "community_name as communityName, " +
            "pks_state as state, " +
            "pks_type as type " +
            "from parking_space natural left outer join community " +
            "where pks_type = 'PURCHASED'")
    List<ParkingSpace> listPurchasePkses();

    @Select("select pks_id as id, " +
            "community_id as　communityID, " +
            "community_name as communityName, " +
            "pks_state as state, " +
            "pks_type as type " +
            "from parking_space natural left outer join community " +
            "where pks_type = 'TEMPORARY'")
    List<ParkingSpace> listTemporaryPkses();
}
