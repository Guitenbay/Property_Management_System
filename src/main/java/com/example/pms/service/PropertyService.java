package com.example.pms.service;

import org.springframework.transaction.annotation.Transactional;

public interface PropertyService {
    @Transactional
    public abstract void addResident(int residenceID, String residentID, String residentName, String phoneNumber);
}
