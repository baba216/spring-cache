package com.shubham.cache.config.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableCaching
@Configuration
public class GlobalRedisCacheConfig {

    @Autowired
    List<ICacheIdentifier> identifiers;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        SingletonBeanRegistry singletonBeanRegistry = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
        for (ICacheIdentifier iCacheIdentifier : identifiers) {
            iCacheIdentifier.keyGenerators().forEach(singletonBeanRegistry::registerSingleton);
        }
    }

    @Bean
    RedisCacheWriter redisCacheWriter() {
        return RedisCacheWriter.lockingRedisCacheWriter(jedisConnectionFactory());
    }

    private static final int defaultCacheExpiration = 10;

    @Bean
    RedisCacheConfiguration defaultRedisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(defaultCacheExpiration));
    }


    @Bean
    //@Primary
    public CacheManager cacheManager() {
        Map<String, RedisCacheConfiguration> cacheNamesConfigurationMap = new HashMap<>();
        for (ICacheIdentifier identifier : identifiers) {
            cacheNamesConfigurationMap.put(identifier.getCacheName(), RedisCacheConfiguration.defaultCacheConfig().entryTtl(identifier.getTTL()));
        }
        return new RedisCacheManager(redisCacheWriter(), defaultRedisCacheConfiguration(), cacheNamesConfigurationMap);
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
