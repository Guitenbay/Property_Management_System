package com.example.pms.service.impl;

import com.example.pms.dao.PropertyMapper;
import com.example.pms.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public void addResident(int residenceID, String residentID, String residentName, String phoneNumber) {
        propertyMapper.createResident(residentName, residentID, phoneNumber);
        propertyMapper.createPropertyRecord(residentID, residenceID);
        propertyMapper.updateResidenceRecord(residenceID, "BUSY");
    }
}
