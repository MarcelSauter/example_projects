package com.proQrent.proQrentGold.service;

import com.proQrent.proQrentGold.model.GoodNews;
import com.proQrent.proQrentGold.repository.GoodNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GoodNewsService {

    @Autowired
    private GoodNewsRepository goodNewsRepository;

    public Iterable<GoodNews> getGoodNews() {
        return goodNewsRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public void addGoodNews(GoodNews goodNews) {
        this.goodNewsRepository.save(goodNews);
    }
}
