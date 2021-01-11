package com.process.redis.service.impl;

import com.process.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Override
    public boolean tokenBucket(Long among, TimeUnit timeUnit, Long restoreTime) {

        return false;
    }

    @Override
    public boolean leakBucket(Long among, TimeUnit timeUnit, Long restoreTime) {
        return false;
    }
}
