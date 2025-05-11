package com.workintech.university.controller;

import com.workintech.university.dto.CoursePatchRequestDto;
import com.workintech.university.dto.CourseRequestDto;
import com.workintech.university.dto.CourseResponseDto;
import com.workintech.university.dto.InstructorResponseDto;
import com.workintech.university.entity.Course;

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
@RequestMapping("/courses")
@Slf4j
public class CourseController {
    private final CourseService courseService;
    private final InstructorService instructorService;

    @Autowired
    public CourseController(CourseService courseService, InstructorService instructorService){
        this.courseService=courseService;
        this.instructorService=instructorService;
    }

    @GetMapping
    public List<CourseResponseDto> getCourses(){
        log.debug("Getting all courses...");
        return courseService
                .getAll()
                .stream()
                .map((course)->new CourseResponseDto(
                        course.getName(),
                        course.getCode(),
                        course.getCredit(),
                        course.getDepartment() == null ? "" : course.getDepartment().getName()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public CourseResponseDto findByIdCourse(@Positive @PathVariable("id") Long id){
        log.debug("Getting course by ID {}... ", id);
        Course course = courseService.findById(id);

        return new CourseResponseDto(
                course.getName(),
                course.getCode(),
                course.getCredit(),
                course.getDepartment() == null ? "" : course.getDepartment().getName()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponseDto createCourse(@Validated @RequestBody CourseRequestDto courseDto){
        log.debug("Saving the course...");

        Course course = new Course();
        course.setName(courseDto.getName());
        course.setCode(courseDto.getCode());
        course.setCredit(courseDto.getCredit());
        course= courseService.create(course);

        return new CourseResponseDto(
                course.getName(),
                course.getCode(),
                course.getCredit(),
                course.getDepartment() == null ? "" : course.getDepartment().getName()
        );

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseById(@Positive @PathVariable("id") Long id){
        courseService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Course replaceOrCreate(@Positive @PathVariable("id") Long id,
                                  @Validated @RequestBody Course course){

        return courseService.replaceOrCreate(id, course);
    }

    @PatchMapping("/{id}")
    public Course update(@Positive @PathVariable("id") Long id,
                         @Validated @RequestBody CoursePatchRequestDto courseDto){

        Course course = new Course();

        if(courseDto.getName() != null)
            course.setName(courseDto.getName());
        if(courseDto.getCode() != null)
            course.setCode(courseDto.getCode());
        if(courseDto.getCredit() != null)
            course.setCredit(courseDto.getCredit());

        return courseService.update(id, course);
    }

    @GetMapping("/{id}/instructors")
    public List<InstructorResponseDto> getAllInstructorForCourseId(@Positive @PathVariable("id") Long id){
        Course course = courseService.findById(id);

        return course.getInstructors()
                .stream()
                .map((instructor)->new InstructorResponseDto(
                        instructor.getFirstName(),
                        instructor.getLastName(),
                        instructor.getEmail(),
                        instructor.getPhoneNumber()
                ))
                .toList();
    }

    @PatchMapping("/{courseId}/instructors/{instructorId}")
    public CourseResponseDto assignInstructorToourse(@Positive @PathVariable("courseId") Long courseId,
                                                     @Positive @PathVariable("instructorId") Long instructorId){
        Course course = courseService.findById(courseId);

        Instructor instructor = instructorService.findById(instructorId);

        course.addInstructor(instructor);

        courseService.update(courseId, course);

        return new CourseResponseDto(
                course.getName(),
                course.getCode(),
                course.getCredit(),
                course.getDepartment() == null ? "" : course.getDepartment().getName()
        );
    }

    //PUT/courses/courseId/instructors [1,2,3]
}
