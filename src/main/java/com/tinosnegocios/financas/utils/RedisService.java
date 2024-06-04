package com.tinosnegocios.financas.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisService {
    public void setValueJson(String title, String json) {
        JedisPool pool = connect();
        try (Jedis jedis = pool.getResource()) {
            jedis.set(title, json);
        }
    }

    public String getValue(String key) {
        JedisPool pool = connect();
        try (Jedis jedis = pool.getResource()) {
            String result = jedis.get(key);
            return result;
        }
    }

    private JedisPool connect(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool pool = new JedisPool(poolConfig, "localhost", 6379, 2000, "sinqia123");

        return pool;
    }
}
