package com.workintech.university.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="faculty", schema = "university_management")
public class Faculty {
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
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Column(name="email")
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name="university_id")
    private University university;

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Department> departments;

    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;

        if(obj == null || obj.getClass() != getClass())
            return false;

        Faculty faculty = (Faculty) obj;

        return faculty.getId().equals(this.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.id);
    }
}
