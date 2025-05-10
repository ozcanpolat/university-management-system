package com.workintech.university.controller;

import com.workintech.university.entity.Faculty;
import com.workintech.university.service.FacultyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
@Slf4j
public class FacultyController {
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService){
        this.facultyService = facultyService;
    }

    @GetMapping("/search")
    public List<Faculty> searchByName(@Size(min=3) @NotEmpty @RequestParam("name") String name){
        return facultyService.searchFacultyByName(name);
    }

    @GetMapping("/{id}")
    public Faculty getById(@Positive @PathVariable("id") Long id){
        return facultyService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Faculty create(@Validated @RequestBody Faculty faculty){
        return facultyService.create(faculty);
    }

    @PutMapping("/{id}")
    public Faculty replaceOrCreate(@Positive @PathVariable("id") Long id,
                                   @Validated @RequestBody Faculty faculty){
        return facultyService.replaceOrCreate(id, faculty);
    }

    @PatchMapping("/{id}")
    public Faculty update(@Positive @PathVariable("id") Long id,
                          @Validated @RequestBody Faculty faculty){
        return facultyService.update(id, faculty);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Positive @PathVariable("id") Long id){
        facultyService.deleteById(id);
    }




}
