package com.example.pms.service;

import com.example.pms.bean.PurchasePks;
import com.example.pms.bean.RentalPks;
import com.example.pms.bean.TemporaryPks;
import org.springframework.transaction.annotation.Transactional;


public interface ParkingService {
    @Transactional
    void addRentalPks(RentalPks rentalPks);

    @Transactional
    void addPurchasePks(PurchasePks purchasePks);

    @Transactional
    void addTemporaryPks(TemporaryPks temporaryPks);
}
