package com.ead.module.controllers;

import com.ead.module.dtos.ModuleDto;
import com.ead.module.models.ModuleModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ead.module.services.ModuleService;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/modules")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {
    @Autowired
    ModuleService moduleService;
    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid ModuleDto moduleDto){
        var moduleModel = new ModuleModel();
        BeanUtils.copyProperties(moduleDto, moduleModel);
        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        moduleModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.save(moduleModel));

    }

}
