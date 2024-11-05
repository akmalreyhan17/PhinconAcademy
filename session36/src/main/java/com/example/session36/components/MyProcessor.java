package com.example.session36.components;

import org.springframework.batch.item.ItemProcessor;

import com.example.session36.model.MyEntity;

public class MyProcessor implements ItemProcessor<MyEntity, MyEntity> {
    @Override
    public MyEntity process(MyEntity item) throws Exception {
        if (item.getName().startsWith("A")) {
            return null;
        }
        String uppercaseName = item.getName().toUpperCase();
        item.setName(uppercaseName);
        return item;
    }
}
