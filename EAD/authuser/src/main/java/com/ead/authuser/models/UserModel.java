package com.ead.authuser.models;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.EnumType;
import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.Link;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_USERS")
public class UserModel implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 255)
    @JsonIgnore
    private String password;
    @Column(nullable = false, unique = true, length = 150)
    private String fullName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(length = 20)
    private String phoneNumber;
    @Column(length = 20)
    private String cpf;
    @Column
    private String imageUrl;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastUpdateDate;


}
