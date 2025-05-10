package com.workintech.university.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityErrorResponse {
    private String message;
    private int status;
    private long timestamp;
    private LocalDateTime localDataTime;
}
