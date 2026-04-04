    package org.example.sr_group_03_spring_mini_project.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.data.redis.cache.RedisCacheConfiguration;
    import org.springframework.data.redis.cache.RedisCacheManager;
    import org.springframework.data.redis.connection.RedisConnectionFactory;
    import org.springframework.data.redis.serializer.RedisSerializationContext;
    import org.springframework.data.redis.serializer.StringRedisSerializer;

    import java.time.Duration;

    import static org.example.sr_group_03_spring_mini_project.utils.CacheUtils.OTP_CACHE_NAME;

    @Configuration
    public class RedisConfig {


        @Bean
        public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
            RedisSerializationContext.SerializationPair<String> stringSerializer =
                    RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());

            RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofHours(1))
                    .disableCachingNullValues()
                    .serializeKeysWith(stringSerializer)
                    .serializeValuesWith(stringSerializer);

            RedisCacheConfiguration otpConfig = defaultConfig.entryTtl(Duration.ofMinutes(10));
            RedisCacheConfiguration userCacheConfig = defaultConfig.entryTtl(Duration.ofMinutes(15));

            return RedisCacheManager.builder(connectionFactory)
                    .cacheDefaults(defaultConfig)
                    .withCacheConfiguration(OTP_CACHE_NAME.EMAIL_CACHE.name(), otpConfig)
                    .withCacheConfiguration(OTP_CACHE_NAME.USER_CACHE.name(), userCacheConfig)
                    .build();
        }
    }
