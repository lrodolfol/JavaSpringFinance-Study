package com.tinosnegocios.financas.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisService {
    public void setValue() {
        JedisPool pool = connect();
        try (Jedis jedis = pool.getResource()) {
            jedis.set("foo", "bar");
            System.out.println(jedis.get("foo")); // prints bar
        }
    }

    private JedisPool connect(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool pool = new JedisPool(poolConfig, "localhost", 6379, 2000, "sinqia123");

        return pool;
    }
}
