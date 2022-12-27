package com.ead.course.services.impl;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;

import com.ead.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.ModuleRespository;
import com.ead.course.repositories.LessonRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ModuleRespository moduleRepository;
    @Autowired
    LessonRepository lessonRepository;

    public CourseModel save(CourseModel courseModel){
        return courseRepository.save(courseModel);
    }
    @Transactional
    @Override
    public void delete(CourseModel courseModel){
        List<ModuleModel> moduleModelList = moduleRepository.findAllModulesIntoCourse(courseModel.getCourseId());
        if(!moduleModelList.isEmpty()){
            for(ModuleModel module : moduleModelList){
                List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
                if(!lessonModelList.isEmpty()){
                    lessonRepository.deleteAll(lessonModelList);
                }
            }
            moduleRepository.deleteAll(moduleModelList);
        }
        courseRepository.delete(courseModel);
    }
    @Override
    public Optional<CourseModel> findById(UUID courseId){
        return courseRepository.findById(courseId);
    }

    public List<CourseModel> findAll(){
        return courseRepository.findAll();
    }
}