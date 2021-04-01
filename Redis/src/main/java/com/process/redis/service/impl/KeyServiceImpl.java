package com.process.redis.service.impl;

import com.process.redis.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KeyServiceImpl implements KeyService {

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Override
	public void save(Map map) {
		redisTemplate.opsForValue().setIfAbsent("map", "v");
	}

	@Override
	public Map load(Object key) {
		return null;
	}

}
