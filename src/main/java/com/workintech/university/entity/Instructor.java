package com.workintech.university.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "instructor", schema = "university-management")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Column(name = "first_name")
    @JsonProperty(value = "first_name")
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(max = 70)
    @Column(name = "last_name")
    @JsonProperty(value = "last_name")

    private String lastName;

    @Email
    @Size(max = 100)
    private String email;

    @Column(name = "phone_number")
    @JsonProperty(value = "phone_number")
    @Size(max = 20)
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "course_instructor",
            schema = "university_management",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

    public void addCourse(Course course){
        if(courses == null)
            courses = new HashSet<>();

        courses.add(course);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(obj == null || obj.getClass() != getClass())
            return false;

        Instructor instructor = (Instructor) obj;

        return instructor.getId().equals(this.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.id);
    }

}
