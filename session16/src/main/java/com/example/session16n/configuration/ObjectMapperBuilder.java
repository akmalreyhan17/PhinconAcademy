package com.example.session16n.configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class ObjectMapperBuilder {
    private boolean preserveOrder;
    private DateFormat dateFormat;

    public ObjectMapperBuilder dateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        this.dateFormat = simpleDateFormat;
        return this;
    }

    public ObjectMapperBuilder preserveOrder(boolean order) {
        this.preserveOrder = order;
        return this;
    }

    public ObjectMapper build() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(this.dateFormat);
        if (this.preserveOrder) {
            objectMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        }
        return objectMapper;
    }
}
