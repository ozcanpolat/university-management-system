package com.workintech.university.service;

import com.workintech.university.entity.Instructor;
import com.workintech.university.entity.University;
import com.workintech.university.exceptions.InstructorNotFoundException;
import com.workintech.university.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService{
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }
    @Override
    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(Long id) {
        return instructorRepository.
                findById(id).
                orElseThrow(()-> new InstructorNotFoundException(id + "'li hoca bulunamadı."));
    }

    @Override
    public Instructor create(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public Instructor replaceOrCreate(Long id, Instructor instructor) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);

        if(optionalInstructor.isPresent()){
            instructor.setId(id);
            return instructorRepository.save(instructor);
        }
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor update(Long id, Instructor instructor) {
        Instructor instructorToUpdate = instructorRepository.
                findById(id).
                orElseThrow(()-> new InstructorNotFoundException(id + "'li hoca bulunamadı."));

        if(instructor.getFirstName() != null)
            instructorToUpdate.setFirstName(instructor.getFirstName());

        return instructorRepository.save(instructorToUpdate);
    }
}
