package com.workintech.university.controller;

import com.workintech.university.entity.Department;
import com.workintech.university.service.DepartmentService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAll(){
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public Department getById(@Positive @PathVariable("id") Long id){
        return departmentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department create(@Validated @RequestBody Department department){
        return departmentService.create(department);
    }

    @PutMapping("/{id}")
    public Department replaceOrCreate(@Positive @PathVariable("id") Long id,
                                      @Validated @RequestBody Department department ){
        return departmentService.replaceOrCreate(id, department);
    }

    @PatchMapping("/{id}")
    public Department update(@Positive @PathVariable("id") Long id,
                             @Validated @RequestBody Department department){
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Positive @PathVariable("id") Long id){
        departmentService.deleteById(id);
    }

}
