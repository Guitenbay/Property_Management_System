package com.example.pms.bean;

import java.sql.Date;

public class Search {
    private Date fromMonthly;
    private Date fromQuarter;
    private String reportType;

    public Date getFromMonthly() {
        return fromMonthly;
    }

    public void setFromMonthly(Date fromMonthly) {
        this.fromMonthly = fromMonthly;
    }

    public Date getFromQuarter() {
        return fromQuarter;
    }

    public void setFromQuarter(Date fromQuarter) {
        this.fromQuarter = fromQuarter;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
