package com.workintech.university.service;

import com.workintech.university.entity.Faculty;
import com.workintech.university.exceptions.FacultyNotFoundException;
import com.workintech.university.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService{
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository){
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty findById(Long id) {
        return facultyRepository.
                findById(id).
                orElseThrow(()->new FacultyNotFoundException(id + "'li fakülte bulunamadı."));
    }

    @Override
    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteById(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Faculty replaceOrCreate(Long id, Faculty faculty) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);

        if(optionalFaculty.isPresent()){
            faculty.setId(id);
            return facultyRepository.save(faculty);
        }
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty facultyToUpdate = facultyRepository.
                findById(id).
                orElseThrow(()->new FacultyNotFoundException(id + "'li fakülte bulunamadı."));

        if(faculty.getName() != null)
            facultyToUpdate.setName(faculty.getName());

        return facultyRepository.save(facultyToUpdate);
    }

    @Override
    public List<Faculty> searchFacultyByName(String name) {
        return facultyRepository.searchFacultyByName(name);
    }

    @Override
    public List<Faculty> searchByFacultyNameOrEmail(String name) {
        return facultyRepository.searchByFacultyNameOrEmail(name);
    }
}
