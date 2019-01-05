package com.example.pms.service;

import org.springframework.transaction.annotation.Transactional;

public interface FeeService {

    @Transactional
    void addPropertyFee(String residentID, int residenceID, double fee, boolean paid);

    @Transactional
    void addManagementFee(String residentID, int pksID, double fee, boolean paid);
}
