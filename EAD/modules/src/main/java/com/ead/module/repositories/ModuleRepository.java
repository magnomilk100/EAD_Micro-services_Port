package com.ead.modules.repositories;

import com.ead.modules.models.ModuleModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
@Repository
public interface ModuleRepository<ModuleModel> extends JpaRepository<ModuleModel, UUID>{

}
