package com.workintech.university.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {
    @NotNull
    @NotEmpty
    @Size(max = 150)
    private String name;

    @Size(max = 25)
    private String code;

    @Column(name = "credit")
    private Double credit;

}
