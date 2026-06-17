package com.residex.auth.service.impl;

import com.residex.auth.dto.AuthResponse;
import com.residex.auth.dto.LoginRequest;
import com.residex.auth.dto.RegisterRequest;
import com.residex.auth.service.AuthService;
import com.residex.common.enums.Role;
import com.residex.department.entity.Department;
import com.residex.department.repository.DepartmentRepository;
import com.residex.student.entity.Student;
import com.residex.student.repository.StudentRepository;
import com.residex.user.entity.User;
import com.residex.security.JwtService;
import com.residex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (studentRepository.existsByRegisterNumber(request.getRegisterNumber())) {
            throw new RuntimeException("Register number already exists");
        }

        Department department =
                departmentRepository.findById(request.getDepartmentId())
                        .orElseThrow(() ->
                                new RuntimeException("Department not found"));

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.STUDENT);

        userRepository.save(user);

        Student student = new Student();

        student.setRegisterNumber(request.getRegisterNumber());
        student.setPhone(request.getPhone());

        student.setParentName(request.getParentName());
        student.setParentPhone(request.getParentPhone());

        student.setEmergencyContact(
                request.getEmergencyContact()
        );

        student.setGender(request.getGender());
        student.setAddress(request.getAddress());

        student.setYearOfStudy(
                request.getYearOfStudy()
        );

        student.setDepartment(department);
        student.setUser(user);

        studentRepository.save(student);

        return new AuthResponse(
                "Student registered successfully",
                null
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow(() ->
                new RuntimeException("User not found")
        );

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException(
                    "Invalid credentials"
            );
        }

        String token =
        jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(
                "Login successful",
                token
        );
    }
}