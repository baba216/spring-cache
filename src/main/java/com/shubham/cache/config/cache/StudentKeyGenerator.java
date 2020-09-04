package com.shubham.cache.config.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class StudentKeyGenerator implements KeyGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentKeyGenerator.class);

    private final String prefix = "customKey.student.";

    @Override
    public Object generate(Object target, Method method, Object... params) {
        Object param0 = params[0];
        if (param0 == null) {
            LOGGER.error("Invalid Param for caching:{}", param0);
            throw new IllegalArgumentException("Invalid Key");
        }
        Long id = (Long) params[0];
        String key = prefix.concat(String.valueOf(id));
        LOGGER.info("Key for Student Cache:{}", key);
        return key;
    }
}
