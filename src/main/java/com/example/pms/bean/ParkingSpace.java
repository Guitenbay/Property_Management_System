package com.example.pms.bean;

public class ParkingSpace {
    private int pksID;
    private int communityID;
    private String communityName;
    private String pksState;
    private String pksType;

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
}
