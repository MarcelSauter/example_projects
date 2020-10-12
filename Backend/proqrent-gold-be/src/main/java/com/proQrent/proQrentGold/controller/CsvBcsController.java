package com.proQrent.proQrentGold.controller;

import com.proQrent.proQrentGold.service.CsvBcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/csv/bcs")
public class CsvBcsController {

    @Autowired
    private CsvBcsService csvBcsService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Integer>> uploadCsvBcsFile(@RequestParam("file")MultipartFile file) throws Exception {
        return ResponseEntity
                .ok()
                .body(csvBcsService.uploadCsvBcsFile(file));
    }
}
