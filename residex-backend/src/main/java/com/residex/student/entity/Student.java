package com.residex.student.entity;

import com.residex.common.entity.BaseEntity;
import com.residex.department.entity.Department;
import com.residex.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String registerNumber;

    private String phone;

    private String parentName;

    private String parentPhone;

    private String emergencyContact;

    @Column(nullable = false)
    private String gender;

    @Column(columnDefinition = "TEXT")
    private String address;

    private Integer yearOfStudy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}