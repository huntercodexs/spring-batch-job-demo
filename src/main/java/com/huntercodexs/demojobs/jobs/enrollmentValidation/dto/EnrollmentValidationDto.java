package com.huntercodexs.demojobs.jobs.enrollmentValidation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentValidationDto {
    private int id;
    private String name;
    private String description;
    private String price;

}
