package com.example.pms.bean;

import java.sql.Date;

public class Equipment {
    private int equipmentID;
    private String category;
    private String equipmentType;
    private String equipmentDesc;
    private double repairFee;
    private Date startTime;
    private String neededRepair;

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentDesc() {
        return equipmentDesc;
    }

    public void setEquipmentDesc(String equipmentDesc) {
        this.equipmentDesc = equipmentDesc;
    }

    public double getRepairFee() {
        return repairFee;
    }

    public void setRepairFee(double repairFee) {
        this.repairFee = repairFee;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getNeededRepair() {
        return neededRepair;
    }

    public void setNeededRepair(String neededRepair) {
        this.neededRepair = neededRepair;
    }
}
