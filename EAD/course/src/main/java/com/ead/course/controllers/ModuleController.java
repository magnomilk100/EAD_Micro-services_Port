package com.ead.course.controllers;

import com.ead.course.dtos.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import com.ead.course.specifications.SpecificationTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ead.course.services.ModuleService;
import com.ead.course.models.ModuleModel;
import com.ead.course.dtos.ModuleDto;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    @Autowired
    CourseService courseService;

    @PostMapping("/api/v1/courses/{courseId}/modules")
    public ResponseEntity<Object> saveModule(@PathVariable(value="courseId") UUID courseId,
            @RequestBody @Valid ModuleDto moduleDto){
        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);

        if(!courseModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found with this id " + courseId);
        }
        var moduleModel = new ModuleModel();
        BeanUtils.copyProperties(moduleDto, moduleModel);
        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        moduleModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        moduleModel.setCourse(courseModelOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.save(moduleModel));
    }

    @DeleteMapping("/api/v1/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> deleteModule(@PathVariable(value="courseId") UUID courseId,
                                               @PathVariable(value="moduleId") UUID moduleId
                                             ){
        Optional<ModuleModel> moduleModelOptional = moduleService.findOneInCourse(courseId, moduleId);

        if(!moduleModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this courseId " + courseId);
        }
        moduleService.delete(moduleModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Module successfully deleted.");
    }

    @PutMapping("/api/v1/courses/{courseId}/module/{moduleId}")
    public ResponseEntity<Object> updateModule(@PathVariable(value="courseId") UUID courseId,
                                               @PathVariable(value="moduleId") UUID moduleId,
                                               @RequestBody @Valid ModuleDto moduleDto){
        Optional<ModuleModel> moduleModelOptional = moduleService.findOneInCourse(courseId, moduleId);

        if(!moduleModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this courseId " + courseId);
        }
        var moduleModel = moduleModelOptional.get();
        moduleModel.setTitle(moduleDto.getTitle());
        moduleModel.setTitle(moduleDto.getDescription());
        moduleModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.save(moduleModel));
    }

    @GetMapping("/api/v1/courses/{courseId}/modules")
    public ResponseEntity<Page<ModuleModel>> getAllModuleByCourse(@PathVariable(value="courseId") UUID courseId,
                                                                  SpecificationTemplate.ModuleSpec spec,
                                                                  @PageableDefault(page = 0, size = 10, sort = "moduleId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.findAllByCourse(SpecificationTemplate.moduleCourseId(courseId).and(spec), pageable));
    }

    @GetMapping("/api/v1/modules")
    public ResponseEntity<Page<ModuleModel>> getAllModules(SpecificationTemplate.ModuleSpec spec,
                                                           @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.findAll(spec, pageable));
    }

    @GetMapping("/api/v1/modules/{moduleId}")
    public ResponseEntity<Object> getOneModule(@PathVariable(value="moduleId") UUID moduleId){
        Optional<ModuleModel> moduleModel = moduleService.findById(moduleId);
        if(moduleModel == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module Not Found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(moduleModel);
    }

    @GetMapping("/api/v1/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> getOneModuleInCourse(@PathVariable(value="courseId") UUID courseId,
                                                       @PathVariable(value="moduleId") UUID moduleId){
        Optional<ModuleModel> moduleModelOptional = moduleService.findOneInCourse(courseId, moduleId);
        if(!moduleModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this courseId " + courseId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(moduleModelOptional.get());
    }
}
