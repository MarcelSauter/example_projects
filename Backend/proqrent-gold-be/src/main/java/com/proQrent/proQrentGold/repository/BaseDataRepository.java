package com.proQrent.proQrentGold.repository;

import com.proQrent.proQrentGold.model.BaseData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDataRepository extends CrudRepository<BaseData, Long> {
}
