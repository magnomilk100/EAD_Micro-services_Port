package com.ead.modules.services;

import com.ead.modules.models.*;
import java.util.UUID;
import java.util.Optional;
import java.util.List;
public interface ModuleService {
    ModuleModel save(ModuleModel moduleModel);
    void delete(ModuleModel moduleModel);
    public Optional<ModuleModel> findById(UUID moduleId);
    public List<ModuleModel> findlAll();
}
