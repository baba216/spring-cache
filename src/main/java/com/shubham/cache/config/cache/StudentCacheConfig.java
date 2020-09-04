package com.shubham.cache.config.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

//@Configuration
public class StudentCacheConfig extends RedisCacheConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentCacheConfig.class);


    @Bean(value = "studentCacheManager")
    public CacheManager cacheManager() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(30))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(jedisConnectionFactory())
                .cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean("studentKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new StudentKeyGenerator();
    }
}
