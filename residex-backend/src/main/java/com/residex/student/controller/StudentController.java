package com.residex.student.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @GetMapping("/profile")
    public String profile() {
        return "Protected Student Profile";
    }
}