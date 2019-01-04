package com.example.pms.bean;

public class BusyPks {
    private int id;
    private int communityID;
    private String communityName;
    private String state;
    private String type;
    private String holderID;
    private String holder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
