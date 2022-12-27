package com.ead.authuser.services;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService{
    List<UserModel> findAll();
    Optional<UserModel> findById(UUID userId);
    public void delete(UserModel userModel);
    public void save(UserModel userModel);

    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);

    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);
}
