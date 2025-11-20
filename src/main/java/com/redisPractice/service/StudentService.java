package com.redisPractice.service;

import com.redisPractice.entity.Student;
import com.redisPractice.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentRepo studentRepo;

    public Student getStudentById(Long id) {
        Student student = redisService.get(id, Student.class);
        if(student != null){
            System.out.println("getting from cache");
            return student;
        }else {
            Optional<Student> s= studentRepo.findById(id);
            Student stu =  s.orElse(null);
            if(stu != null){
                System.out.println("setting to cache");
                redisService.set(id, stu, 60L);
            }
            return stu;
        }
    }
}
