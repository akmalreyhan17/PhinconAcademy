package com.example.session26.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.session26.model.StudentCache;

@Repository
public interface StudentCacheRepository extends CrudRepository<StudentCache, Integer> {
    
}
