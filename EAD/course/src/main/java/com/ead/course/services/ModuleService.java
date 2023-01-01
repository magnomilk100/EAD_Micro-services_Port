package com.ead.course.services;

import com.ead.course.models.ModuleModel;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {
    public ModuleModel save(ModuleModel moduleModel);
    public void delete(ModuleModel moduleModel);
    Optional<ModuleModel> findOneInCourse(UUID courseId, UUID moduleId);
    Optional<ModuleModel> findById(UUID moduleId);

    Page<ModuleModel> findAll(Specification<ModuleModel> spec, Pageable page);

    Page<ModuleModel> findAllByCourse(Specification<ModuleModel> spec , Pageable pageable);
}
