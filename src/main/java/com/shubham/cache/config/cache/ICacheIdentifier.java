package com.shubham.cache.config.cache;

import org.springframework.cache.interceptor.KeyGenerator;

import java.time.Duration;
import java.util.Map;

public interface ICacheIdentifier {

    String getCacheName();

    Duration getTTL();

    Map<String,KeyGenerator> keyGenerators();


}
