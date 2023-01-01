package com.ead.course.services;

import com.ead.course.models.CourseModel;
import com.ead.course.specifications.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    CourseModel save(CourseModel courseModel);

    @Transactional
    void delete(CourseModel courseModel);

    public Optional<CourseModel> findById(UUID courseId);

    public Page<CourseModel> findAll(Specification<CourseModel> spec, Pageable pageable);

}
