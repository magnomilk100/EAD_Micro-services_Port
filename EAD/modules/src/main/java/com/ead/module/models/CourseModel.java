package com.ead.module.models;

import com.ead.module.enums.CourseLevels;
import com.ead.module.enums.CourseStatus;
import com.ead.modules.enums.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@JsonFormat
@Table(name = "TB_COURSE")
public class CourseModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private CourseStatus courseStatus;
    private CourseLevels courseLevel;
    private UUID userInstructor;

}
