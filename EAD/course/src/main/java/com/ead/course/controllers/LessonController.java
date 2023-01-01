package com.ead.course.controllers;

import com.ead.course.dtos.LessonDto;
import com.ead.course.dtos.ModuleDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.services.ModuleService;
import com.ead.course.services.LessonService;
import com.ead.course.specifications.SpecificationTemplate;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ead.course.models.LessonModel;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonController {
    @Autowired
    ModuleService moduleService;
    @Autowired
    LessonService lessonService;
    @PostMapping("/api/v1/modules/{moduleId}/lessons")
    public ResponseEntity<Object> saveLesson(@PathVariable(value="moduleId") UUID moduleId,
                                             @RequestBody @Valid LessonDto lessonDto){

        Optional<ModuleModel> moduleModelOptional = moduleService.findById(moduleId);

        if(!moduleModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found with this id " + moduleId);
        }
        var lessonModel = new LessonModel();
        BeanUtils.copyProperties(lessonDto, lessonModel);
        lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        lessonModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        lessonModel.setModule(moduleModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.save(lessonModel));
    }

    @DeleteMapping("/api/v1/modules/{moduleId}/lesson/{lessonId}")
    public ResponseEntity<Object> deleteLesson(@PathVariable(value="moduleId") UUID moduleId,
                                               @PathVariable(value="lessonId") UUID lessonId
    ){
        Optional<LessonModel> lessonModelOptional = lessonService.findOneInModule(moduleId, lessonId);

        if(!lessonModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this moduleId " + moduleId);
        }
        lessonService.delete(lessonModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Lesson successfully deleted.");
    }

    @PutMapping("/api/v1/modules/{moduleId}/lesson/{lessonId}")
    public ResponseEntity<Object> updateLesson(@PathVariable(value="moduleId") UUID moduleId,
                                               @PathVariable(value="lessonId") UUID lessonId,
                                               @RequestBody @Valid LessonDto lessonDto){
        Optional<LessonModel> lessonModelOptional = lessonService.findOneInModule(moduleId, lessonId);

        if(!lessonModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this moduleId " + moduleId);
        }
        var lessonModel = lessonModelOptional.get();
        lessonModel.setTitle(lessonDto.getTitle());
        lessonModel.setTitle(lessonDto.getDescription());
        lessonModel.setTitle(lessonDto.getVideoUrl());
        lessonModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.save(lessonModel));
    }

    @GetMapping("/api/v1/modules/{moduleId}/lessons")
    public ResponseEntity<Page<LessonModel>> getAllLessonsByModule(@PathVariable(value="moduleId") UUID moduleId,
                                                                   SpecificationTemplate.LessonSpec spec,
                                                                   @PageableDefault(page = 0, size = 10, sort = "lessonId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAllByModule(SpecificationTemplate.lessonModuleId(moduleId).and(spec),pageable));
    }

    @GetMapping("/api/v1/lessons")
    public ResponseEntity<Page<LessonModel>> getAllLessons(SpecificationTemplate.LessonSpec spec,
                                                           @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAll(spec, pageable));
    }

    @GetMapping("/api/v1/lesson/{lessonId}")
    public ResponseEntity<Object> getOneLesson(@PathVariable(value="lessonId") UUID lessonId){
        Optional<LessonModel> lessonModel = lessonService.findById(lessonId);
        if(lessonModel == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson Not Found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(lessonModel);
    }

    @GetMapping("/api/v1/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> getOneLessonInModule(@PathVariable(value="moduleId") UUID moduleId,
                                                       @PathVariable(value="lessonId") UUID lessonId){
        Optional<LessonModel> lessonModelOptional = lessonService.findOneInModule(moduleId, lessonId);
        if(!lessonModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this moduleId " + moduleId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(lessonModelOptional.get());
    }

}
