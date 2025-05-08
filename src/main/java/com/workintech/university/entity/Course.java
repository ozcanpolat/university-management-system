package com.workintech.university.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course", schema = "university-management")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 150)
    private String name;

    @Size(max = 25)
    private String code;

    @Column(name = "credit")
    private Double credit;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinTable(
            name = "course_instructor",
            schema = "university_management",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private Set<Instructor> instructors;

    public void addInstructor(Instructor instructor){
        if(instructors == null)
            instructors = new HashSet<>();

        instructors.add(instructor);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(obj == null || obj.getClass() != getClass())
            return false;

        Course course = (Course) obj;

        return course.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
