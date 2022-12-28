package com.ead.modules.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ead.modules.services.ModuleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/modules")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {
    @Autowired
    ModuleService moduleService;
    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid ModuleDto moduleDto){


    }

}
