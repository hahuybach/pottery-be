package com.example.pottery.db2.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "history")
@Builder
@ToString
@Getter
@Setter
public class History {
    @Id
    private ObjectId _id;
    private String ipAddress;
    private String sqlQuery;
    private Long accountId;
}
