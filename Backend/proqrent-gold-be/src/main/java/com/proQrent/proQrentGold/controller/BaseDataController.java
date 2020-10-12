package com.proQrent.proQrentGold.controller;


import com.proQrent.proQrentGold.model.BaseData;
import com.proQrent.proQrentGold.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/base-data")
public class BaseDataController {

    @Autowired
    private BaseDataService baseDataService;

    @GetMapping("/get-base-data")
    public ResponseEntity<BaseData> getBaseData() {
        return ResponseEntity
                .ok()
                .body(this.baseDataService.getBaseData());
    }

    @PostMapping("/set-global-time")
    public ResponseEntity<BaseData> setGlobalTime(@RequestBody Integer globalTime) {
        return ResponseEntity
                .ok()
                .body(this.baseDataService.saveGlobalTime(globalTime));
    }
}
