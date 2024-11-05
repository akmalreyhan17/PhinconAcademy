package com.example.session26.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.session26.model.Student;
import com.example.session26.model.StudentCache;
import com.example.session26.repository.StudentCacheRepository;
import com.example.session26.repository.StudentRepository;
import com.example.session26.util.StudentUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentCacheRepository cacheRepository;
    private final StudentUtil util;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentCacheRepository cacheRepository,
            StudentUtil util) {
        this.studentRepository = studentRepository;
        this.cacheRepository = cacheRepository;
        this.util = util;
    }

    public Student getStudentById(Integer id) {
        // 1. Check Redis cache first
        Optional<StudentCache> cache = cacheRepository.findById(id);

        if (cache.isPresent()) {
            // Cache hit
            log.debug("Cache hit: returning Student from Redis");
            return util.convertCache(cache.get());
        }

        // 2. Cache miss, retrieve from PostgreSQL database
        log.debug("Cache miss: querying PostgreSQL database");
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            // Store the data in Redis with TTL for future use
            StudentCache newCache = util.convertObject(student.get());
            cacheRepository.save(newCache);
        }

        return student.get();
    }
}
