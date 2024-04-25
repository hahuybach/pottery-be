package com.example.pottery.db2.controller;

import com.example.pottery.db2.model.History;
import com.example.pottery.db2.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final HistoryService historyService;
    @Autowired
    public TestController(HistoryService historyService){
        this.historyService = historyService;
    }

    @GetMapping("/test-mongodb-connection")
    public String testMongoDBConnection() {
        try {
            // Just fetch some basic information to test the connection
            String dbName = mongoTemplate.getDb().getName();
            return "Successfully connected to MongoDB database: " + dbName;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to connect to MongoDB: " + e.getMessage();
        }
    }
    @PostMapping("/save-history")
    public ResponseEntity<History> saveHistory(){
        return ResponseEntity.ok(historyService.saveHistory()) ;
    }
    @GetMapping("/get-all-history")
    public ResponseEntity<List<History>> getListHistories(){
        return ResponseEntity.ok(historyService.getAllHistories());
    }
}
