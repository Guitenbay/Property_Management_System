package com.example.pms.bean;

public class PurchasePks {
    private int pksID;
    private String residentID;
    private double payment;
    private double manFee;

    public int getPksID() {
        return pksID;
    }

    public void setPksID(int pksID) {
        this.pksID = pksID;
    }

    public String getResidentID() {
        return residentID;
    }

    public void setResidentID(String residentID) {
        this.residentID = residentID;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getManFee() {
        return manFee;
    }

    public void setManFee(double manFee) {
        this.manFee = manFee;
    }
}
