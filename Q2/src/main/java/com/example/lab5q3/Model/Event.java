package com.example.lab5q3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotNull(message = "id can't be empty")
    @Min(value = 3, message = "id has to be more then 2")
    private int id;

    @NotNull(message = "capacity can't be empty")
    @Positive(message = "capacity has to be positive number")
    @Min(value = 26, message = "capacity must be more the 25")
    private int capacity;

    @NotNull(message = "description can't be empty")
    @Min(value = 16, message = "description has to be more then 15 char")
    private String description;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate,endDate;
}
