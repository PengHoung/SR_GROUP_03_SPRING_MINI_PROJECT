package org.example.sr_group_03_spring_mini_project.utils;


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheUtils {
    public enum OTP_CACHE_NAME{
        EMAIL_CACHE
    }
    private final CacheManager cacheManager;

    public CacheUtils(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public <T> void put(OTP_CACHE_NAME cacheName, String key, T value) {
        Cache cache = cacheManager.getCache(cacheName.name());
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public <T> T get(OTP_CACHE_NAME cacheName, String key, Class<T> type) {
        Cache cache = cacheManager.getCache(cacheName.name());
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(key);
            if (wrapper != null && type.isInstance(wrapper.get())) {
                return type.cast(wrapper.get());
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