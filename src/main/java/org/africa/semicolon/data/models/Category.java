package org.africa.semicolon.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("collection = categories")
@NoArgsConstructor
@AllArgsConstructor

public class Category {
    @Id
    private String id;
    private String name;

}
