package org.africa.semicolon.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ApiResponse{
    private boolean success;
    private Object data;
}
