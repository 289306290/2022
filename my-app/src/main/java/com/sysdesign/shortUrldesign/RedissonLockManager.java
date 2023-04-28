package com.sysdesign.shortUrldesign;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedissonLockManager {
    private RedissonClient redissonClient;
    public RedissonLockManager(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public RLock getLock(String lockKey) {
        return redissonClient.getLock(lockKey);
    }
}
