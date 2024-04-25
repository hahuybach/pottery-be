package com.example.pottery.db2.repository;

import com.example.pottery.db2.model.History;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HistoryRepository extends MongoRepository<History, ObjectId> {
}
