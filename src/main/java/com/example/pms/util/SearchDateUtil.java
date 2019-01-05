package com.example.pms.util;

import com.example.pms.bean.Search;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SearchDateUtil {
    public static void getFromToBySearchType(Date from, Date to, Search search) {
        GregorianCalendar gc = new GregorianCalendar();
        if (search.getReportType().equals("m")) {//查询月度报表
            gc.setTime(search.getFromMonthly());
            gc.set(Calendar.DAY_OF_MONTH, 1);
            from.setTime(gc.getTimeInMillis());
            gc.add(Calendar.MONTH, 1);//月份加一
            to.setTime(gc.getTimeInMillis());
        } else {//查询季度报表
            gc.setTime(search.getFromQuarter());
            gc.set(Calendar.DAY_OF_MONTH, 1);
            from.setTime(gc.getTimeInMillis());
            gc.add(Calendar.MONTH, 4);//月份加4
            to.setTime(gc.getTimeInMillis());
        }
    }
}
