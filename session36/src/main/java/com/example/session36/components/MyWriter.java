package com.example.session36.components;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.example.session36.model.MyEntity;

public class MyWriter implements ItemWriter<MyEntity> {
    @Override
    public void write(Chunk<? extends MyEntity> chunk) throws Exception {
        for (MyEntity item : chunk) {
            System.out.println(item.getName());
        }
    }  
}
