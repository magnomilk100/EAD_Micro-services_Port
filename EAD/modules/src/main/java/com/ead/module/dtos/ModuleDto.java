package com.ead.module.dtos;

import lombok.Data;

import javax.persistence.Column;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
public class ModuleDto {
    @NotBlank
    private String title;
    @Column(nullable = false, length = 250)
    private String description;
    @NotNull
    private LocalDateTime creationDate;
}
