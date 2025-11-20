package com.redisPractice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(Long key, Class<T> entityClass){
        try{
            Object o= redisTemplate.opsForValue().get(key.toString());
            ObjectMapper mapper = new ObjectMapper();
            if (o != null) {
                return mapper.readValue(o.toString(), entityClass);
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("Exception " ,e);
            return null;
        }
    }

    public void set(Long key, Object o, Long ttl){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonVal = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key.toString(), jsonVal, ttl, TimeUnit.SECONDS);
        }catch (Exception e){
            log.error("Exception " ,e);
        }
    }

}
