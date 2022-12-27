package com.ead.authuser.services.impl;
import com.ead.authuser.repository.UserRepository;
import com.ead.authuser.services.UserService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ead.authuser.models.UserModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(UUID userId){
        return userRepository.findById(userId);
    }
    @Override
    public void delete(UserModel userModel){
        userRepository.delete(userModel);
    }
    public void save(UserModel userModel){
        userRepository.save(userModel);
    }
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable){
        return userRepository.findAll(spec,pageable);
    }

}
