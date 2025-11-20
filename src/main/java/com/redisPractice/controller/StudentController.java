package com.redisPractice.controller;

import com.redisPractice.entity.Student;
import com.redisPractice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get-student")
    public Student getStudent(@RequestParam Long id){
        return studentService.getStudentById(id);
    }

}
