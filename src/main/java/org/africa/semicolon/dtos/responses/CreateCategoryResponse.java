package org.africa.semicolon.dtos.responses;

import lombok.Data;

@Data
public class CreateCategoryResponse {
    private String id;
    private String name;
    private String message;
}
