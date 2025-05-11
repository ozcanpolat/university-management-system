package com.workintech.university.controller;

import com.workintech.university.entity.Instructor;
import com.workintech.university.service.CourseService;
import com.workintech.university.service.InstructorService;

import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
@Slf4j
public class InstructorController {
    private final InstructorService instructorService;
    private final CourseService courseService;

    @Autowired
    public InstructorController(InstructorService instructorService, CourseService courseService){
        this.instructorService = instructorService;
        this.courseService=courseService;
    }

    @GetMapping
    public List<Instructor> getAll(){
        return instructorService.getAll();
    }

    @GetMapping("/{id}")
    public Instructor getById(@Positive @PathVariable("id") Long id){
        return instructorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Instructor create(@Validated @RequestBody Instructor instructor){
        return instructorService.create(instructor);
    }

    @PutMapping("/{id}")
    public Instructor replaceOrCreate(@Positive @PathVariable("id") Long id,
                                      @Validated @RequestBody Instructor instructor){
        return instructorService.replaceOrCreate(id, instructor);
    }

    @PatchMapping("/{id}")
    public Instructor update(@Positive @PathVariable("id") Long id,
                             @Validated @RequestBody Instructor instructor){
        return instructorService.update(id, instructor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Positive @PathVariable Long id){
        instructorService.deleteById(id);
    }




}
