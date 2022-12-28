package com.ead.course.services;

import com.ead.course.models.ModuleModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {
    public ModuleModel save(ModuleModel moduleModel);
    public void delete(ModuleModel moduleModel);
    Optional<ModuleModel> findOneInCourse(UUID courseId, UUID moduleId);
    Optional<ModuleModel> findById(UUID moduleId);

    List<ModuleModel> findAll();

    List<ModuleModel> findAllByCourse(UUID courseId);
}
