package com.workintech.university.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "university", schema="university-management")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max=150)
    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Size(max=20)
    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    @Email
    private String email;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private Set<Faculty> faculties;

    public void addFaculty(Faculty faculty){
        if(faculties==null)
            faculties= new HashSet<>();

        faculties.add(faculty);
    }

}
