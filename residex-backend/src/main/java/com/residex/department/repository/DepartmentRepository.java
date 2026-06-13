package com.residex.department.repository;

import com.residex.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DepartmentRepository
        extends JpaRepository<Department, Long> {

    Optional<Department> findById(Long id);
}