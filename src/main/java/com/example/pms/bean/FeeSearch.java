package com.example.pms.bean;


import java.sql.Date;

public class FeeSearch extends Search {
    private String reportType;
    private boolean paid;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
