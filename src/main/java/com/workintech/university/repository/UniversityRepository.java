package com.workintech.university.repository;

import com.workintech.university.entity.Role;
import com.workintech.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {
    @Query("SELECT r FROM Role r WHERE r.authority = :authority")
    Optional<Role> findRoleByAuthority(@Param("authority") String authority);
}
