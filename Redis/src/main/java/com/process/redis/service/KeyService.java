package com.process.redis.service;


import java.util.Map;

public interface KeyService<K, V> {

	void save(Map<K, V> map);

	Map<K, V> load(K key);

}
