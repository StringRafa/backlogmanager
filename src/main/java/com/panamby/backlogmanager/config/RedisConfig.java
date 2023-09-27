package com.panamby.backlogmanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import com.panamby.backlogmanager.consts.RedisConstants;

import java.time.Duration;

@Configuration
public class RedisConfig {
	
	@Value("${backlogmanager.cache.redis.ttl.minutes}")
	private String backlogSubscriberCacheTTL;
	
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration
        		.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(Integer.parseInt(backlogSubscriberCacheTTL)))
                .disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(RedisConstants.BACKLOG_MANAGER_SUBSCRIBE_CACHE,
                        RedisCacheConfiguration
                        	    .defaultCacheConfig()
                        	    .entryTtl(Duration.ofMinutes(Integer.parseInt(backlogSubscriberCacheTTL)))
                                .disableCachingNullValues()
                                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())));
    }
}