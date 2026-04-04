package org.example.sr_group_03_spring_mini_project.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheUtils {
    public enum OTP_CACHE_NAME {
        EMAIL_CACHE,
        USER_CACHE
    }

    private final CacheManager cacheManager;
    private final ObjectMapper objectMapper;

    public CacheUtils(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public <T> void put(OTP_CACHE_NAME cacheName, String key, T value) {
        Cache cache = cacheManager.getCache(cacheName.name());
        if (cache != null) {
            try {
                String stored = (value instanceof String)
                        ? (String) value
                        : objectMapper.writeValueAsString(value);
                cache.put(key, stored);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to serialize cache value", e);
            }
        }
    }
    public <T> T get(OTP_CACHE_NAME cacheName, String key, Class<T> type) {
        Cache cache = cacheManager.getCache(cacheName.name());
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(key);
            if (wrapper != null) {
                try {
                    String json = (String) wrapper.get();
                    if (type == String.class) return type.cast(json);
                    return objectMapper.readValue(json, type);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Failed to deserialize cache value", e);
                }
            }
        }
        return null;
    }

    public void evict(OTP_CACHE_NAME cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName.name());
        if (cache != null) {
            cache.evict(key);
        }
    }
}