package com.example.pms.bean;

public class BusyPks {
    private int pksID;
    private int communityID;
    private String communityName;
    private String pksState;
    private String pksType;
    private double costs;
    private String paid;
    private String holderID;
    private String holder;

    public int getPksID() {
        return pksID;
    }

    public void setPksID(int pksID) {
        this.pksID = pksID;
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

    public String getPksState() {
        return pksState;
    }

    public void setPksState(String pksState) {
        this.pksState = pksState;
    }

    public String getPksType() {
        return pksType;
    }

    public void setPksType(String pksType) {
        this.pksType = pksType;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getHolderID() {
        return holderID;
    }

    public void setHolderID(String holderID) {
        this.holderID = holderID;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
