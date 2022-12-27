package com.ead.course.dtos;

import lombok.Data;
import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CourseDto {
    @NotBlank
    private String name;
    private String description;
    private String imageUrl;
    @NotNull
    private CourseStatus courseStatus;
    @NotNull
    private UUID userInstructor;
    private CourseLevel courseLevel;
}
