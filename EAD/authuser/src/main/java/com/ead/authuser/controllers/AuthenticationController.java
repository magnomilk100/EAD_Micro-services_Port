package com.ead.authuser.controllers;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.ead.authuser.dtos.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.validation.annotation.Validated;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    //Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    //Logger log = LogManager.getLogger(AuthenticationController.class); // Log4j2
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
                                                   @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto){

        log.debug("POST registerUser UserDto received - {}", userDto.toString());
        if(userService.existsByUsername(userDto.getUsername())){
            log.warn("POST registerUser UserDto received - Username {} already taken.", userDto.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username " + userDto.getUsername() + " already taken.");
        }
        if(userService.existsByEmail(userDto.getEmail())){
            log.warn("POST registerUser UserDto received - Email {} already exists.", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email " + userDto.getEmail() + " already exists.");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);
        log.debug("POST registerUser UserDto saved - {}", userModel.toString());
        log.info("User UserDto saved - {}", userModel.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
    @GetMapping("/")
    public String index(){
        log.trace("TRACE Mode");
        log.debug("DEBUG  Mode");
        log.info("INFO Mode");
        log.warn("WARN Mode");
        log.error("ERROR Mode");
        try{
            throw new Exception("--- FORCED ERROR --- ");
        }catch(Exception e){
            log.error("****************** {} ************************ ", e.toString() );
        }

        return "Logging Spring Boot";
    }
}
