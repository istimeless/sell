package com.timeless.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.core.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author lijiayin
 */
@Slf4j
@Commit
public class RedisLock {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value){
        if(stringRedisTemplate.opsForValue().setIfAbsent(key, value)){
            return true;
        }
        //锁过期
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isNotBlank(currentValue) 
                && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //获取上一个锁的时间（防止多线程同时解锁）
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if(StringUtils.isNotBlank(oldValue)
                && StringUtils.equals(currentValue, oldValue)){
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value){
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(currentValue)
                    && StringUtils.equals(currentValue, value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【redis分布式锁】解锁异常：{}", e.getMessage());
        }
    }
}
