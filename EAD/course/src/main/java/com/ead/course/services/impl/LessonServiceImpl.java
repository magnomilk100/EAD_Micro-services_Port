package com.ead.course.services.impl;

import com.ead.course.repositories.CourseRepository;
import com.ead.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ead.course.repositories.LessonRepository;

@Service
public class LessonServiceImpl implements LessonService{
    @Autowired
    LessonRepository lessonRepository;
}
