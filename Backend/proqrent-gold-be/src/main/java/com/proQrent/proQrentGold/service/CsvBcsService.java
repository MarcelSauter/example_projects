package com.proQrent.proQrentGold.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.proQrent.proQrentGold.model.BcsCsv;
import com.proQrent.proQrentGold.model.GoodNews;
import com.proQrent.proQrentGold.model.ReachedTime;
import com.proQrent.proQrentGold.repository.ReachedTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CsvBcsService {

    @Autowired
    private ReachedTimeRepository reachedTimeRepository;

    @Autowired
    private GoldCourseService goldCourseService;

    @Autowired
    private GoodNewsService goodNewsService;

    public Map<String, Integer> uploadCsvBcsFile(MultipartFile file) throws IOException {
        LocalDate dateFilter = LocalDate.now().withDayOfMonth(1);
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CsvToBean<BcsCsv> csvToBean = new CsvToBeanBuilder<BcsCsv>(reader)
                .withType(BcsCsv.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<BcsCsv> bcsList = csvToBean.parse();

        Map<String, Integer> groupedBcsMap = bcsList.stream()
                .filter(entryDate -> entryDate.getDate().isAfter(dateFilter) | entryDate.getDate().equals(dateFilter))
                .collect(Collectors.groupingBy(BcsCsv::getEmail, Collectors.summingInt(BcsCsv::getMinutes)));

        groupedBcsMap.forEach((k, v) -> {
            reachedTimeRepository.save(new ReachedTime(v, k, dateFilter));
        });

        return groupedBcsMap;
    }
}
