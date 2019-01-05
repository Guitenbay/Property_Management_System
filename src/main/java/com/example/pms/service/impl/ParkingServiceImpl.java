package com.example.pms.service.impl;

import com.example.pms.bean.PaymentRecord;
import com.example.pms.bean.PurchasePks;
import com.example.pms.bean.RentalPks;
import com.example.pms.bean.TemporaryPks;
import com.example.pms.dao.ParkingMapper;
import com.example.pms.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private ParkingMapper parkingMapper;

    @Override
    public void addRentalPks(RentalPks rentalPks) {
        double discount = 1;
        if (rentalPks.getPksMonths() >= 12)
            discount = 0.8;
        PaymentRecord paymentRecord = new PaymentRecord();
        parkingMapper.insertPaymentRecord(rentalPks.getPayment(), rentalPks.getPksMonths(),
                "YES", "MONTH", paymentRecord);
        parkingMapper.insertRentedPks(rentalPks.getPksID(), rentalPks.getResidentID(),
                discount, rentalPks.getPayment(), rentalPks.getManFee());
        parkingMapper.bindPksPaymentRecord(rentalPks.getPksID(), paymentRecord.getPaymentID(),
                "租用车位");
        parkingMapper.updatePksState(rentalPks.getPksID(), "BUSY", "RENTED");
    }

    @Override
    public void addPurchasePks(PurchasePks purchasePks) {
        PaymentRecord paymentRecord = new PaymentRecord();
        parkingMapper.insertPaymentRecord(purchasePks.getPayment(), 0,
                "YES", "PERMANENT", paymentRecord);
        parkingMapper.insertPurchasePks(purchasePks.getPksID(), purchasePks.getResidentID(), purchasePks.getManFee());
        parkingMapper.bindPksPaymentRecord(purchasePks.getPksID(), paymentRecord.getPaymentID(),
                "购买车位");
        parkingMapper.updatePksState(purchasePks.getPksID(), "BUSY", "PURCHASED");
    }

    @Override
    public void addTemporaryPks(TemporaryPks temporaryPks) {
        PaymentRecord paymentRecord = new PaymentRecord();
        parkingMapper.insertVehicle(temporaryPks.getLicensePlate(), temporaryPks.getOwnerName());
        parkingMapper.insertPaymentRecord(temporaryPks.getPayment(), temporaryPks.getPksHours(),
                "YES", "HOUR", paymentRecord);
        parkingMapper.insertTemporaryPks(temporaryPks.getPksID(), temporaryPks.getLicensePlate());
        parkingMapper.bindPksPaymentRecord(temporaryPks.getPksID(), paymentRecord.getPaymentID(),
                "临时车位");
        parkingMapper.updatePksState(temporaryPks.getPksID(), "BUSY", "TEMPORARY");
    }
}
