package com.ead.course.services.impl;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements ModuleService{
    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public ModuleModel save(ModuleModel moduleModel) {
        return moduleRepository.save(moduleModel);
    }

    @Transactional
    @Override
    public void delete(ModuleModel moduleModel){
        List<LessonModel> lessonModelList = lessonRepository.findAllByModule(moduleModel.getModuleId());
        if(!lessonModelList.isEmpty()){
            lessonRepository.deleteAll(lessonModelList);
        }
        moduleRepository.delete(moduleModel);
    }

    @Override
    public Optional<ModuleModel> findOneInCourse(UUID courseId, UUID moduleId) {
        return Optional.ofNullable(moduleRepository.findOneInCourse(courseId, moduleId));
    }

    @Override
    public Page<ModuleModel> findAllByCourse(Specification<ModuleModel> spec , Pageable pageable) {
        return moduleRepository.findAll(spec, pageable);
    }

    public Optional<ModuleModel> findById(UUID moduleId){
        return moduleRepository.findById(moduleId);
    }

    public Page<ModuleModel> findAll(Specification<ModuleModel> spec, Pageable pageable){
        return moduleRepository.findAll(spec, pageable);
    }

}
