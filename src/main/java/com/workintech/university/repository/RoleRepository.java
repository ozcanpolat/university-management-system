package com.workintech.university.repository;

import com.workintech.university.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.authority = :authority")
    Optional<Role> findRoleByAuthority(@Param(("authority")) String authority);
}
