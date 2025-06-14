package org.africa.semicolon.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ApiResponse{
    private boolean success;
    private Object data;
}
