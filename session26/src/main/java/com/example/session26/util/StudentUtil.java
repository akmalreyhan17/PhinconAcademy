package com.example.session26.util;

import org.springframework.stereotype.Component;

import com.example.session26.model.Student;
import com.example.session26.model.StudentCache;

@Component
public class StudentUtil {
    public Student convertCache(StudentCache cache) {
        Student student = new Student();
        student.setId(cache.getId());
        student.setName(cache.getName());
        student.setGpa(cache.getGpa());
        return student;
    }
    
    public StudentCache convertObject(Student student) {
        StudentCache cache = new StudentCache();
        cache.setId(student.getId());
        cache.setName(student.getName());
        cache.setGpa(student.getGpa());
        return cache;
    }
}
