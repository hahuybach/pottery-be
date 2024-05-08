package com.example.pottery.db2.serviceImpl;

import com.example.pottery.db2.models.History;
import com.example.pottery.db2.repositories.HistoryRepository;
import com.example.pottery.db2.services.HistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
    public List<History> getAllHistories(){
        System.out.println(historyRepository.findAll());
        return historyRepository.findAll();
    }
    public History saveHistory(String ipAddress,String uri){
        return historyRepository.save(History.builder().ipAddress(ipAddress).uri(uri).accountId(1L).build());
    }
}
