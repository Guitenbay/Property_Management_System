package com.example.pms.bean;

public class TemporaryPks {
    //临时停车位收费记录
    private int pksID;
    private String licensePlate;
    private int pksHours;
    private double payment;
    private String ownerName;

    public int getPksID() {
        return pksID;
    }

    public void setPksID(int pksID) {
        this.pksID = pksID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getPksHours() {
        return pksHours;
    }

    public void setPksHours(int pksHours) {
        this.pksHours = pksHours;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
