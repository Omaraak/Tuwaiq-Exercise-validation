package com.example.lab5q2.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotNull(message = "id can't be empty")
    @Min(value = 3, message = "id must me more then 2")
    private int id;

    @NotNull(message = "title can't be empty")
    @Min(value = 9, message = "title must me more then 8")
    private String title;

    @NotNull(message = "description can't be empty")
    @Min(value = 16, message = "description must me more then 15")
    private String description;

    @NotNull(message = "status can't be empty")
    @Pattern(regexp = "(Not Started|In Progress|Completed)", message = "the status has to be Not Started or in Progress or Completed")
    private String status;

    @NotNull(message = "company name can't be empty")
    @Min(value = 7, message = "company name has to be more then 7")
    private String companyName;
}
