package com.process.redis.service;


import java.util.concurrent.TimeUnit;

public interface RedisService {
	/**
	 * 令牌桶算法，允许一定突发速率，只要桶内还有token
	 *
	 * @param among       总token数
	 * @param timeUnit    时间单位
	 * @param restoreTime 生产token时间
	 * @return
	 */
	boolean tokenBucket(Long among, TimeUnit timeUnit, Long restoreTime);

	/**
	 * 漏桶算法，严格控制速率
	 *
	 * @param among       总token算法
	 * @param restoreTime 生产token时间
	 * @param timeUnit    时间单位
	 * @return
	 */
	boolean leakBucket(Long among, TimeUnit timeUnit, Long restoreTime);
}
