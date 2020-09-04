package com.shubham.cache.config.cache;

import com.shubham.cache.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeCacheIdentifier implements ICacheIdentifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeCacheIdentifier.class);

    @Override
    public String getCacheName() {
        return "employee";
    }

    @Override
    public Duration getTTL() {
        return Duration.ofSeconds(30);
    }

    @Override
    public Map<String, KeyGenerator> keyGenerators() {
        Map<String, KeyGenerator> generatorMap = new HashMap<>();
        generatorMap.put(getCacheName() + "KeyGenerator-1", (target, method, params) -> {
            String prefix = getCacheName() + "->";
            Object param0 = params[0];
            if (param0 == null) {
                LOGGER.error("Invalid Param for caching:{}", param0);
                throw new IllegalArgumentException("Invalid Key");
            }
            Long id = (Long) params[0];
            String key = prefix.concat(String.valueOf(id));
            LOGGER.info("Key for Employee Cache:{}", key);
            return key;
        });
        generatorMap.put(getCacheName() + "KeyGenerator-2", (target, method, params) -> {
            Object param0 = params[0];
            Employee employee = (Employee) param0;
            String prefix = getCacheName() + "->";
            if (param0 == null) {
                LOGGER.error("Invalid Param for caching:{}", param0);
                throw new IllegalArgumentException("Invalid Key");
            }
            String key = prefix.concat(String.valueOf(employee.getId()));
            LOGGER.info("Key for Employee Cache:{}", key);
            return key;
        });
        return generatorMap;
    }

}
