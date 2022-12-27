package com.ead.course.services;

import com.ead.course.models.CourseModel;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    CourseModel save(CourseModel courseModel);

    @Transactional
    void delete(CourseModel courseModel);

    public Optional<CourseModel> findById(UUID courseId);

    public List<CourseModel> findAll();
}
