package com.example.pms.dao;

import com.example.pms.bean.Equipment;
import com.example.pms.bean.RepairOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Mapper
@Component("equipmentMapper")
public interface EquipmentMapper {

    @Select({"select equipment_id as equipmentID, " +
            "category, " +
            "equipment_type as equipmentType, " +
            "description as equipmentDesc, " +
            "repair_fee as repairFee, " +
            "issue_date as startTime," +
            "'YES' as neededRepair " +
            "from " +
            "(select * from " +
            "(select equipment_id, issue_date " +
            "from inspect_record where waiting_for_repair ='YES') i_order " +
            "union (select equipment_id, issue_date " +
            "from repair_order where processing = 'TODO')) ir_order " +
            "natural join equipment " +
            "natural join equipment_info "
    })
    List<Equipment> listWaitForRepair();


    @Select({
            "<script>",
            "select " +
                    "equipment_id as equipmentID, " +
                    "category, " +
                    "equipment_type as equipmentType, " +
                    "description as equipmentDesc, " +
                    "resident_id as residentID, " +
                    "resident_name as residentName, " +
                    "repair_fee as repairFee, " +
                    "issue_date as repairDate " +
                    "from repair_order natural join equipment " +
                    "natural join equipment_info " +
                    "natural join resident " +
                    "where equipment_id is not null ",
            "<if test='#{residentID} != null'> and resident_id like concat('%', #{residentID}, '%') </if>",
            "<if test='#{equipmentType} != null'> and equipment_type like concat('%', #{equipmentType}, '%') </if>",
            "<if test='#{sqlFrom} != null and #{sqlTo} != null'> and issue_date between #{sqlFrom} and #{sqlTo} </if>",
            "</script>"
    })
    List<RepairOrder> listRepairOrderRecord(@Param("residentID") String residentID,
                                            @Param("equipmentType") String equipmentType,
                                            @Param("sqlFrom") Date sqlFrom, @Param("sqlTo") Date sqlTo11);
}
