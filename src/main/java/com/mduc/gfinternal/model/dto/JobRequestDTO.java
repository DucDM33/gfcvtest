package com.mduc.gfinternal.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JobRequestDTO {
    @NotEmpty(message = "Title is required")
    private String title;

    @NotNull(message = "Position is required")
    private String position;

    @NotEmpty(message = "Description is required")
    @Size(max = 1500, message = "Description must be less than 1500 characters")
    private String description;

    @NotEmpty(message = "Requirements are required")
    @Size(max = 1500, message = "Requirements must be less than 1500 characters")
    private String requirements;

    @Size(max = 1500, message = "Other information must be less than 1500 characters")
    private String other;

    @NotEmpty(message = "Salary is required")
    private String salary;

    @Size(max = 1500, message = "Benefit must be less than 1500 characters")
    private String benefit;

    @NotEmpty(message = "Deadline is required")
    private String deadline;

    @NotEmpty(message = "Working time is required")
    private String workingTime;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotEmpty(message = "Interview format is required")
    private String interviewFormat;

    @NotEmpty(message = "Location is required")
    private String location;
}
