package com.workintech.university.controller;

import com.workintech.university.entity.University;
import com.workintech.university.service.UniversityService;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
@Slf4j
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService){
        this.universityService = universityService;
    }

    @GetMapping
    public List<University> getAll(){
        return universityService.getAll();
    }

    @GetMapping("/{id}")
    public University getById(@Positive @PathVariable("id") Long id){
        return universityService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public University create(@Validated @RequestBody University university){
        return universityService.create(university);
    }

    @PutMapping("/{id}")
    public University replaceOrCreate(@Positive @PathVariable("id") Long id,
                                   @Validated @RequestBody University university){
        return universityService.replaceOrCreate(id, university);
    }

    @PatchMapping("/{id}")
    public University update(@Positive @PathVariable("id") Long id,
                          @Validated @RequestBody University university){
        return universityService.update(id, university);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Positive @PathVariable Long id){
        universityService.deleteById(id);
    }
}

