package com.ead.modules.services.impl;
import com.ead.modules.models.ModuleModel;
import com.ead.modules.services.ModuleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ModuleServiceImpl implements ModuleService {

    @Override
    public ModuleModel save(ModuleModel moduleModel) {
        return null;
    }

    @Override
    public void delete(ModuleModel moduleModel) {

    }

    @Override
    public Optional<ModuleModel> findById(UUID moduleId) {
        return Optional.empty();
    }

    @Override
    public List<ModuleModel> findlAll() {
        return null;
    }
}
