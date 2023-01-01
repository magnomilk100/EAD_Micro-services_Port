package com.ead.course.services.impl;

import com.ead.course.models.LessonModel;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public LessonModel save(LessonModel lessonModel) {
        return lessonRepository.save(lessonModel);
    }

    @Transactional
    @Override
    public void delete(LessonModel lessonModel){
        //Optional<LessonModel> lessonModelOptional = lessonRepository.findById(lessonModel.getLessonId());
        //if(!lessonModelOptional.isPresent()){
        //    lessonRepository.delete(lessonModelOptional.get());
        lessonRepository.delete(lessonModel);
        //}
    }

    @Override
    public Optional<LessonModel> findOneInModule(UUID moduleId, UUID lessonId) {
        return lessonRepository.findOneInModule(moduleId, lessonId);
    }

    @Override
    public Page<LessonModel> findAllByModule(Specification<LessonModel> spec , Pageable pageable) {
        return lessonRepository.findAll(spec, pageable);
    }

    public Optional<LessonModel> findById(UUID lessonId){
        return lessonRepository.findById(lessonId);
    }

    public Page<LessonModel> findAll(Specification<LessonModel> spec, Pageable pageable){
        return lessonRepository.findAll(spec, pageable);
    }
}
