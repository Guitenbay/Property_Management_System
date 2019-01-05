package com.example.pms.bean;

import java.sql.Timestamp;

public class ManagementFeeRecord {
    private String residentID;
    private String residentName;
    private int communityID;
    private String communityName;
    private int pksID;
    private String pksType;
    private double manFee;
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

    public int getPksID() {
        return pksID;
    }

    public void setPksID(int pksID) {
        this.pksID = pksID;
    }

    public String getPksType() {
        return pksType;
    }

    public void setPksType(String pksType) {
        this.pksType = pksType;
    }

    public double getManFee() {
        return manFee;
    }

    public void setManFee(double manFee) {
        this.manFee = manFee;
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
