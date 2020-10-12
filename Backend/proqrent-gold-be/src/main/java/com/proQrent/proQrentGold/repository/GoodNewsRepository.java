package com.proQrent.proQrentGold.repository;

import com.proQrent.proQrentGold.model.GoodNews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodNewsRepository extends PagingAndSortingRepository<GoodNews, Long> {
}
