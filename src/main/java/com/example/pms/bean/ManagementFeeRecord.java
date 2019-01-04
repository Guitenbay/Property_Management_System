package com.example.pms.bean;

public class ManagementFeeRecord {
    private String residentID;
    private String residentName;
    private int communityID;
    private String communityName;
    private int pks_id;
    private double manFee;
    private boolean paid;


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

    public int getPks_id() {
        return pks_id;
    }

    public void setPks_id(int pks_id) {
        this.pks_id = pks_id;
    }

    public double getManFee() {
        return manFee;
    }

    public void setManFee(double manFee) {
        this.manFee = manFee;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
