package com.example.session36.components;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.session36.model.MyEntity;

public class MyMapper implements FieldSetMapper<MyEntity> {
    @Override
    public MyEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        MyEntity entity = new MyEntity();
        entity.setId(fieldSet.readInt("id"));
        entity.setName(fieldSet.readString("name"));
        return entity;
    }
}
