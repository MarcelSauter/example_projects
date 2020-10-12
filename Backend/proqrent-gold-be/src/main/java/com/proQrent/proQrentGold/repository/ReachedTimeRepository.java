package com.proQrent.proQrentGold.repository;

import com.proQrent.proQrentGold.model.ReachedTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReachedTimeRepository extends CrudRepository<ReachedTime, Long> {
    List<ReachedTime> findAllByTimePeriod(LocalDate timePeriod);
}
