package com.example.pottery.db2.service;

import com.example.pottery.db2.model.History;
import com.example.pottery.db2.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
    public List<History> getAllHistories(){
        System.out.println(historyRepository.findAll());
        return historyRepository.findAll();
    }
    public History saveHistory(){
        return historyRepository.save(History.builder().ipAddress("test ip address").sqlQuery("").accountId(1L).build());
    }
}
