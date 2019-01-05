package com.example.pms.service.impl;

import com.example.pms.bean.PaymentRecord;
import com.example.pms.dao.FeeMapper;
import com.example.pms.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl implements FeeService {
    @Autowired
    private FeeMapper feeMapper;

    @Override
    public void addPropertyFee(String residentID, int residenceID, double fee, boolean paid) {
        PaymentRecord paymentRecord = new PaymentRecord();
        feeMapper.insertPaymentRecord(fee, 1, paid ? "YES" : "NO", "MONTH", paymentRecord);
        feeMapper.bindPropertyPaymentRecord(residentID, residenceID, paymentRecord.getPaymentID());
    }

    @Override
    public void addManagementFee(String residentID, int pksID, double fee, boolean paid) {
        PaymentRecord paymentRecord = new PaymentRecord();
        feeMapper.insertPaymentRecord(fee, 1, paid ? "YES" : "NO", "MONTH", paymentRecord);
        feeMapper.bindManagementPaymentRecord(pksID, pksID);
    }
}
