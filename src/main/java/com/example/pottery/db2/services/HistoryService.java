package com.example.pottery.db2.services;

import com.example.pottery.db2.models.History;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HistoryService {
    List<History> getAllHistories();
    History saveHistory(String ipAddress,String uri);
}
