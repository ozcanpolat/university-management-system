package com.workintech.university.service;

import com.workintech.university.entity.Course;
import com.workintech.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAll() {
        return List.of();
    }

    @Override
    public Course findById(Long id) {
        return null;
    }

    @Override
    public Course create(Course course) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
