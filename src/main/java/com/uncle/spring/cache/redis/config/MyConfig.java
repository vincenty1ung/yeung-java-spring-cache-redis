package com.uncle.spring.cache.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨戬
 * @className MyConfig
 * @email yangb@chaosource.com
 * @date 2019/6/6 16:48
 */
@Configuration
public class MyConfig {

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //参照StringRedisTemplate内部实现指定序列化器
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());

        return redisTemplate;
    }

    @Primary
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        //spring-boot 1.5.x版本
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        //默认超时时间，单位秒
        redisCacheManager.setDefaultExpiration(30);
        //缓存超时时间Map，key为cacheName，value为超时,单位是秒
        Map<String, Long> expiresMap = new HashMap<>();
        //缓存用户信息的cacheName和超时时间
        redisCacheManager.setExpires(expiresMap);
       /*
       spring-boot 2.x版本
        //缓存配置对象
        RedisCacheManager redisCacheConfiguration = RedisCacheManager.defaultCacheConfig();
        //设置缓存的默认超时时间：30分钟
        //如果是空值，不缓存
        //设置key序列化器
        //设置value序列化器
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(30L))
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((valueSerializer())));

        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();*/
       return redisCacheManager;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    //使用Jackson序列化器
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
