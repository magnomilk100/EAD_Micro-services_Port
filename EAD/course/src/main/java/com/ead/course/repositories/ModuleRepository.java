package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>, JpaSpecificationExecutor<ModuleModel> {
    @Query(value="select * from tb_modules where course_course_id = :courseId and module_id = :moduleId", nativeQuery = true)
    ModuleModel findOneInCourse(@Param("courseId") UUID courseId, @Param("moduleId") UUID moduleId);

    @Query(value="select * from tb_modules where module_id = :moduleId", nativeQuery = true)
    ModuleModel findOneModule(@Param("moduleId") UUID moduleId);

    @Query(value="select * from tb_modules where course_course_id = :courseId", nativeQuery = true)
    List<ModuleModel> findAllByCourse(@Param("courseId") UUID courseId);

}
