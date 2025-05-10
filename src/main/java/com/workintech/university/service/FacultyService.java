package com.workintech.university.service;

import com.workintech.university.entity.Faculty;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAll();
    Faculty findById(Long id);
    Faculty create(Faculty faculty);
    void deleteById(Long id);
    Faculty replaceOrCreate(Long id, Faculty faculty);
    Faculty update(Long id, Faculty faculty);
    List<Faculty> searchFacultyByName(String name);
    List<Faculty> searchByFacultyNameOrEmail(String name);
}
