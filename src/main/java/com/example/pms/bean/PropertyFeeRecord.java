package com.example.pms.bean;

import java.sql.Timestamp;

public class PropertyFeeRecord {
    //物业费记录
    private String residentID;
    private String residentName;
    private int communityID;
    private String communityName;
    private int residenceID;
    private int unit;
    private int floor;
    private int room;
    private double area;
    private double propertyFee;
    private Timestamp startTime;
    private String paid;

    public String getResidentID() {
        return residentID;
    }

    public void setResidentID(String residentID) {
        this.residentID = residentID;
    }


    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public int getCommunityID() {
        return communityID;
    }

    public void setCommunityID(int communityID) {
        this.communityID = communityID;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public int getResidenceID() {
        return residenceID;
    }

    public void setResidenceID(int residenceID) {
        this.residenceID = residenceID;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(double propertyFee) {
        this.propertyFee = propertyFee;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
