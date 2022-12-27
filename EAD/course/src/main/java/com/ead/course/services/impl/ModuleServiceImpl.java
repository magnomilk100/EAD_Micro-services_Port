package com.ead.course.services.impl;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.ModuleRespository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ead.course.repositories.ModuleRespository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService{
    @Autowired
    ModuleRespository moduleRespository;

    @Autowired
    LessonRepository lessonRespository;

    @Transactional
    @Override
    public void delete(ModuleModel moduleModel){
        List<LessonModel> lessonModelList = lessonRespository.findAllLessonsIntoModule(moduleModel.getModuleId());
        if(!lessonModelList.isEmpty()){
            lessonRespository.deleteAll(lessonModelList);
        }
        moduleRespository.delete(moduleModel);
    }
}
