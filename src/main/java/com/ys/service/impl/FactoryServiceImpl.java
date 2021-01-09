package com.ys.service.impl;

import com.ys.model.dao.FactoryMapper;
import com.ys.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    FactoryMapper factoryMapper;

    @Override
    public int insertCFName(String fName) {
        return factoryMapper.insertCFName(fName);
    }

    @Override
    public int getFid(String fName, Integer fType) {
        if(factoryMapper.getFid(fName,fType)!=0){
            return 1;
        }
        return 0;
    }

    @Override
    public String getFname(int fId, int fType) {
        return factoryMapper.getFname(fId,fType);
    }
}
