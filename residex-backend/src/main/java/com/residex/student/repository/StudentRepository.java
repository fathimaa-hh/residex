package com.residex.student.repository;

import com.residex.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    boolean existsByRegisterNumber(String registerNumber);
}