package com.workintech.university.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role", schema = "university-management")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String authority;

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(obj == null || obj.getClass() != this.getClass())
            return false;

        Role role = (Role)obj;
        return this.id.equals(role.getId());
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.id);
    }
}
