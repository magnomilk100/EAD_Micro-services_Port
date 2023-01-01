package com.ead.module.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ead.module.models.ModuleModel;

import java.util.UUID;
@Repository
public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>{

}
