package com.example.session9.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.session9.model.Student;
import com.example.session9.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void saveStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
        studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void updateStudent() {
        Student student = studentRepository.findById(1L).orElse(null);
        if (student != null) {
            student.setEmail("new.email@example.com");
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> pageStudent() {
        Page<Student> student = studentRepository.findAll(PageRequest.of(0, 10));
        return student.getContent();
    }

    public List<Student> sortStudent() {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

}
