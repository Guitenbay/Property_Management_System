package com.example.pms.bean;

public class TemporaryPksRecord {
    //临时停车位收费记录
    private String pksID;
    private String licensePlate;
    private String startTime;
    private double pkFee;
    private int duration;

    public String getPksID() {
        return pksID;
    }

    public void setPksID(String pksID) {
        this.pksID = pksID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public double getPkFee() {
        return pkFee;
    }

    public void setPkFee(double pkFee) {
        this.pkFee = pkFee;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



}
