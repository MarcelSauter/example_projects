package com.proQrent.proQrentGold.service;

import com.proQrent.proQrentGold.model.BaseData;
import com.proQrent.proQrentGold.repository.BaseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseDataService {

    @Autowired
    private BaseDataRepository baseDataRepository;

    public BaseData getBaseData() {
        return this.baseDataRepository.findAll().iterator().next();
    }

    public BaseData saveGlobalTime(Integer globalTime) {
        BaseData baseData = this.baseDataRepository.findAll().iterator().next();
        baseData.setGlobalTime(globalTime);

        return this.baseDataRepository.save(baseData);
    }
}
