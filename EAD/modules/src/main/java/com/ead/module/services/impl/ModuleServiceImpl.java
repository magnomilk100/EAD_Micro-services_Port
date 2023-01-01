package com.ead.module.services.impl;

import com.ead.module.models.ModuleModel;
import com.ead.module.services.ModuleService;
import com.ead.module.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleRepository moduleRepository;


    @Override
    public ModuleModel save(ModuleModel moduleModel) {
        return moduleRepository.save(moduleModel);
    }

    @Override
    @Transactional
    public void delete(ModuleModel moduleModel) {
        Optional<ModuleModel> moduleModel1 = moduleRepository.findById(moduleModel.getModuleId());
        if(moduleModel1 != null){
            moduleRepository.delete(moduleModel1.get());
        }
    }

    @Override
    public Optional<ModuleModel> findById(UUID moduleId) {
        return moduleRepository.findById(moduleId);
    }

    @Override
    public List<ModuleModel> findlAll() {
        return moduleRepository.findAll();
    }
}
