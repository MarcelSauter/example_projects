package com.proQrent.proQrentGold.controller;

import com.proQrent.proQrentGold.model.GoodNews;
import com.proQrent.proQrentGold.service.GoodNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class GoodNewsController {

    @Autowired
    private GoodNewsService goodNewsService;

    @GetMapping("/good-news")
    public ResponseEntity<Iterable<GoodNews>> getGoodNews() {
        return ResponseEntity
                .ok()
                .body(this.goodNewsService.getGoodNews());
    }
}
